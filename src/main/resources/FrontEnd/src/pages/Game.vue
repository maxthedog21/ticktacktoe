<template>
    <v-row class="pb-4 mb-10 mt-10">
    <v-col align="center"  cols="2">
        <p>{{ whosTurn  }}</p>
    </v-col>
    <v-col cols="8" align="center">
        <div class="pa-5">
            <enter-game
            v-if="isConnected"
            :canClick="canClick"
            :gameState="gameState"
            @findGame="searchGame"
            @findNewGame="resetAndSearchGame"
            ></enter-game>
            <v-col v-else>
                <span class="pa-5">Connecting To Server</span>
                <v-progress-circular  indeterminate :size="128" :width="12"></v-progress-circular>
            </v-col>
        </div>
        <div class="pa-5">
            <game-board v-if="gameBoard !== null" @gameClick="gameClick" :gameBoard="gameBoard" playerType="x"></game-board>
        </div>
    </v-col>
    <v-col align="center" cols="2">
        <h2>
           {{ getGameStateMsg }}
        </h2>
    </v-col>
    </v-row>
</template>
<script>
import { Client } from '@stomp/stompjs';
import { GameMove } from './gameQueueHandler';
import { findGame } from '@/api/GameQueue';
import EnterGame from '@/components/EnterGame.vue';
import GameBoard from '@/components/GameBoard.vue';
export default {
    name: "GamePage",
    components: {
        GameBoard,
        EnterGame,
    },
    data(){
        return {
            client: null,
            currentSubscription: null,
            gameBoard: null,
            playerType: null,
            isTurn: false,
            isConnected: false,
            gameState: "CONTINUE",
        };
    },
    mixins: [ GameMove ],
    mounted(){
        this.setupGame();
    },
    computed: {
        getGameStateMsg(){
            switch(this.gameState){
                case "CONTINUE":
                    return "";
                case "OWINS":
                    return "O WINS";
                case "XWINS":
                    return "X WINS";
                case "DRAW":
                    return "Game is a Tie";
                default: 
                    return "";
            }
        },
        playerPiece(){
            return this.playerType === 'WAIT' ? 'x' : 'o';
        },
        canClick(){
            return (this.playerType === 'WAIT' || this.playerType === 'JOIN') && this.gameState === 'CONTINUE';
        },
        whosTurn(){
            if(this.gameBoard !== null){

            if(this.isTurn){
                return "Your Turn";
            }else{
                return "Their Turn";
            }

            }
            return "";

        }
    },
    methods: {
      async resetAndSearchGame(){
        this.reset();
        this.searchGame();
      },
      async  searchGame(){
            let state = await findGame();
             state.json().then((resp) => {
                this.handleQueueEvent(this.client, this.currentSubscription, resp);
            });
        },
        gameClick(val){
            if(this.gameBoard[val] === '_' && this.isTurn){
                this.gameBoard = this.gameBoard.substring(0, val) +  this.playerPiece + this.gameBoard.substring(val + 1, this.gameBoard.length);
                this.sendMove();
                this.isTurn = false;
            }
        },
        sendMove(){
            this.sendNewMove(this.client, this.gameBoard);
        },
        setupGame(){
            this.client = new Client({
                brokerURL: 'ws://localhost:8080/gs-guide-websocket',
                onConnect: (frame) => {
                    this.isConnected = true;
                },
            });
            this.client.activate();
        },
        subscribe(){
            this.currentSubscription = this.client.subscribe('/game/queue', (resp) =>{
                             let body = JSON.parse(resp.body);
                             this.playerType =  body.queueEvent;
                this.handleQueueEvent(this.client, this.currentSubscription,  body, this.gameBoard);
            })
        },
    sendName(){
    this.client.publish({
        destination: "/app/queue/start",
        body: "max",
    });
    }
    },

}
</script>