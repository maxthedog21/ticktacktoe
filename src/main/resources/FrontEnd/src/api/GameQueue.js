async function findGame(){
    let resp =  await fetch('http://localhost:8080/api/queue/start');
    return resp;
}

export {
    findGame,
}