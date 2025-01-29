function handleQueueEvent(client, GameQueueEvent){
    console.log('GameQueueEvent.queueEvent: ', GameQueueEvent);
    switch(GameQueueEvent.queueEvent){
        case 'WAIT':
            handleWaitEvent(client);
            console.log('wait: ');
            break;
        case 'JOIN':
            break;
        case 'MAX':
            break;
    }
}

function handleWaitEvent(client){

}

function handleJoinEvent(client){

}

function handleMaxEvent(client){

}

export {
    handleQueueEvent,
}