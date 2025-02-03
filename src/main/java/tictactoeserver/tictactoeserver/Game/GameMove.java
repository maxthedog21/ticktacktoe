package tictactoeserver.tictactoeserver.Game;

enum GameState {
    XWINS,
    OWINS,
    CONTINUE,
    DRAW,
}


public class GameMove {
    
    private String gameBoard;

    public GameMove(String gameBoard){
        this.gameBoard = gameBoard;
    }

    public String getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(String gameBoard) {
        this.gameBoard = gameBoard;
    }


    public GameState endGameCheck(){
        String[] gameSplit = this.gameBoard.toLowerCase().split("");

    int x = 0;
    int o = 0;
    //rows
    for(int i = 0; i < gameSplit.length; i = i + 3){
        for(int j = i; j < i + 3; j++){
            String temp = gameSplit[j];
            if(temp.equals("x")){
                x++;
            }else if(temp.equals("o")){
                o++;
            }
        }
        if(x >= 3 || o >= 3){
            return x >= 3 ? GameState.XWINS : GameState.OWINS;
        }
        x = 0;
        o = 0;
    }
    //columns
    for(int i = 0; i < 3; i++){
        for(int j = i; j < gameSplit.length; j = j + 3){
            String temp = gameSplit[j];
            if(temp.equals("x")){
                x++;
            }else if(temp.equals("o")){
                o++;
            }
        }
        if(x >= 3 || o >= 3){
            return x >= 3 ? GameState.XWINS : GameState.OWINS;
        }
    x = 0;
    o = 0;
    }
    //dialnols
    for(int i = 0; i <= 2; i = i + 2){
        for(int j = i; j < (i == 0 ? gameSplit.length : gameSplit.length -1); j = i == 0 ? j + 4 : j + 2){
            String temp = gameSplit[j];
            if(temp.equals("x")){
                x++;
            }else if(temp.equals("o")){
                o++;
            }
        }
        if(x >= 3 || o >= 3){
            return x >= 3 ? GameState.XWINS : GameState.OWINS;
        }
        x = 0;
        o = 0;
    }
    for(int i = 0; i < gameSplit.length; i++){
        if(gameSplit[i].equals("_")){
            return GameState.CONTINUE;
        }
    }
    return GameState.DRAW;
    }
}
