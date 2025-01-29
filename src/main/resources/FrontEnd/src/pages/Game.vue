<template>
    <v-btn
    @click="subscribe"
    >
            <h1>subscribe</h1>
    </v-btn>
    <v-btn
    @click="sendName"
    >
            <h1>Enter Game Queue</h1>
    </v-btn>
</template>
<script>
import { Client } from '@stomp/stompjs';
import { handleQueueEvent } from './gameQueueHandler';
export default {
    name: "GamePage",
    data(){
        return {
            client: null,
            currentSubscription: null,
        };
    },
    mounted(){
        this.setupGame();
    },
    methods: {
        setupGame(){
            this.client = new Client({
                brokerURL: 'ws://localhost:8080/gs-guide-websocket',
                onConnect: (frame) => {
                console.log('connnected: ', frame);
                },
            });
            this.client.activate();
        },
        subscribe(){
            this.currentSubscription = this.client.subscribe('/game/queue', (resp) =>{
                handleQueueEvent(this.client, JSON.parse(resp.body));
            })
        },
    sendName(){
        console.log('ran');
    this.client.publish({
        destination: "/app/queue/start",
        body: "max",
    });
    }
    },

}
</script>