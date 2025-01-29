package tictactoeserver.tictactoeserver.Game;

public class Game {
   private String playerOne;
   private String playerTwo;

   public Game(int id){
    this.playerOne = id + "1";
    this.playerTwo = id + "2";
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
