<template>
    <v-row justify="center">
        <v-col align="center" class="pa-0 ma-0">
            <div @click="() => gameClick(0)" class="game-box">{{ gameBoard[0] === '_' ? '' : gameBoard[0]  }}</div>
            <div @click="() => gameClick(3)" class="game-box top-border">{{ gameBoard[3] === '_' ? '' : gameBoard[3] }}</div>
            <div @click="() => gameClick(6)" class="game-box top-border">{{ gameBoard[6] === '_' ? '' : gameBoard[6] }}</div>
        </v-col>
        <v-col align="center" class="middle-column pa-0 ma-0">
            <div @click="() => gameClick(1)" class="game-box">{{ gameBoard[1] === '_' ? '' : gameBoard[1] }}</div>
            <div @click="() => gameClick(4)" class="game-box top-border">{{ gameBoard[4] === '_' ? '' : gameBoard[4] }}</div>
            <div @click="() => gameClick(7)" class="game-box top-border">{{  gameBoard[7] === '_' ? '' : gameBoard[7] }}</div>
        </v-col>
        <v-col align="center" class="pa-0 ma-0">
            <div @click="() => gameClick(2)" class="game-box">{{  gameBoard[2] === '_' ? '' : gameBoard[2] }}</div>
            <div @click="() => gameClick(5)" class="game-box top-border">{{  gameBoard[5] === '_' ? '' : gameBoard[5] }}</div>
            <div @click="() => gameClick(8)" class="game-box top-border">{{  gameBoard[8] === '_' ? '' : gameBoard[8] }}</div>
        </v-col>
    </v-row>
</template>
<script>
export default {
    name: "GameBoard",
    props: {
        gameBoard: {
            type: String,
        },
        playerType: {
            type: String,
        }
    },
    methods: {
        gameClick(val){
            this.$emit('gameClick', val);
        },
    },
}
</script>
<style>
.game-box{
    height: 130px;
    width: 100%;
    font-size: 75px;
}
.game-box:hover{
    background-color: white;
    color: black;
}
.middle-column{
    border-left-style: solid;
    border-right-style: solid;
    border-width: 8px;
    border-color: white;
}
.top-border {
    border-top-style: solid;
    border-width: 8px;
    border-color: white;
}
</style>