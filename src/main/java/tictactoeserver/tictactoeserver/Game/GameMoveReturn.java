package tictactoeserver.tictactoeserver.Game;

public class GameMoveReturn extends GameMove {
    private GameState gameState;
    private int id;
    public GameMoveReturn(String gameBoard, GameState gameState, int id){
        super(gameBoard);
        this.gameState = gameState;
        this.id = id;
    }
    public GameState getGameState() {
        return gameState;
    }
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }
    

}
