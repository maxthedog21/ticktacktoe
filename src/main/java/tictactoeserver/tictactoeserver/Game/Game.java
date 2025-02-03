package tictactoeserver.tictactoeserver.Game;

public class Game {
   private String playerOne;
   private String playerTwo;

   public Game(int id){
    this.playerOne = id + "1";
    this.playerTwo = id + "2";
   }


public static int getOppositeId(int id){
    String[] newId = Integer.toString(id).split("");
    String lastValue = newId[newId.length - 1];
    newId[newId.length - 1] = lastValue.equals("1") ? "2" : "1";
    String returnString = "";
    for(int i = 0; i < newId.length; i++){
        returnString = returnString + newId[i];
    }
    return Integer.parseInt(returnString);
}



   public String getPlayerOne() {
    return playerOne;
}





public void setPlayerOne(String playerOne) {
    this.playerOne = playerOne;
}





public String getPlayerTwo() {
    return playerTwo;
}





public void setPlayerTwo(String playerTwo) {
    this.playerTwo = playerTwo;
}





public String getEndPoint(String player){
    if(this.playerOne.equals(player)){
        return this.playerTwo;
    }else{
        return this.playerOne;
    }
   }






}
