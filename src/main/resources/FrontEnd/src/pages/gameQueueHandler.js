export const GameMove = {
data(){
    return {
        returnId: 0,
    };
},
methods: {

handleQueueEvent(client, currentSubscription, GameQueueEvent){
    switch(GameQueueEvent.queueEvent){
        case 'WAIT':
            this.handleWaitEvent(client, currentSubscription, GameQueueEvent.game.playerTwo);
            this.playerType = 'WAIT';
            break;
        case 'JOIN':
            this.handleJoinEvent(client, currentSubscription, GameQueueEvent.game.playerOne, GameQueueEvent.game.playerTwo)
            this.playerType = 'JOIN';
            break;
        case 'MAX':
            break;
    }
},


handleWaitEvent(client, currentSubscription, id){
    this.currentSubscription = client.subscribe('/game/move/' + id, (resp) =>{
        let gameMoveReturn = JSON.parse(resp.body);
        this.gameBoard = gameMoveReturn.gameBoard;
        this.returnId = gameMoveReturn.id;
        if(gameMoveReturn.gameState === 'OWINS' || gameMoveReturn.gameState === 'XWINS' || gameMoveReturn.gameState === 'DRAW'){
            this.gameState =  gameMoveReturn.gameState;
            this.sendNewMove(client, this.gameBoard);
            this.currentSubscription.unsubscribe();
        }else{
            this.isTurn = true;
        }
         })
},

handleJoinEvent(client, currentSubscription, id, playerTwoId){
    this.currentSubscription = client.subscribe('/game/move/' + id, (resp) =>{
        let gameMoveReturn = JSON.parse(resp.body);

        this.gameBoard = gameMoveReturn.gameBoard;
        this.returnId = gameMoveReturn.id;
        if(gameMoveReturn.gameState === 'OWINS' || gameMoveReturn.gameState === 'XWINS' || gameMoveReturn.gameState === 'DRAW'){
            this.gameState =  gameMoveReturn.gameState;
            this.sendNewMove(client, this.gameBoard);
            this.currentSubscription.unsubscribe();
        }else{
            this.isTurn = true;
        }
         });
         let gameBoard = "";
         for(let i = 0; i < 9; i++){
            gameBoard += "_";
         }
         this.gameBoard = gameBoard;
         let body = JSON.stringify({gameBoard: gameBoard});

    client.publish({
        destination: "/app/game/move/" + playerTwoId,
        body: body,
    });
},

reset(){
            this.gameBoard = null;
            this.playerType = null;
            this.isTurn = false;
            this.gameState = "CONTINUE";
},

sendNewMove(client, gameBoard){
    let body = JSON.stringify({gameBoard: gameBoard});

    client.publish({
        destination: "/app/game/move/" + this.returnId,
        body: body,
    });
},

handleGameMove(gameMove){
},


handleMaxEvent(client, currentSubscription){

},
}
}
