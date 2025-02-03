<template>
    <v-btn :disabled="canClick" @click="findGame" variant="outlined" rounded="lg" color="" class="btn">
        <span class="btn-text">
            {{ getText }}
        </span>
    </v-btn>
</template>
<script>
import { findGame } from '@/api/GameQueue';

export default{
    name: "EnterGame",
    props: {
        canClick: {
            type: Boolean,
        },
        gameState: {
            type: String,
        }
    },
    computed: {
        getText(){
            if(this.gameState === 'OWINS' || this.gameState === 'XWINS' || this.gameState === 'DRAW'){
                return "New Game";
            }else{
                return "Search for Game";
            }
        },
    },
    methods: {
        findGame(){
            if(this.gameState === 'OWINS' || this.gameState === 'XWINS' || this.gameState === 'DRAW'){
                this.$emit('findNewGame');
            }else{
                this.$emit('findGame');
            }
        }
    }
}
</script>
<style>
.btn-text{
    color: white;
    font-size: 30px;
}
.btn{
    width: 80% !important;
    height: 80px !important;
}
</style>