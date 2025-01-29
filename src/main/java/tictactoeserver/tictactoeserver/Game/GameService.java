package tictactoeserver.tictactoeserver.Game;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import org.springframework.stereotype.Service;

@Service
public class GameService {
    final int MAXGAMES = 100;
    private static HashMap<Integer, Game> gameList = new HashMap<Integer, Game>();
    private static Queue<Integer> gameWaitList = new LinkedList<Integer>();
    private static int currentGameId = 0;

    public GameQueue enterGameQueue(){
        if(gameList.size() >= 100 ){
            return new GameQueue(QueueEvent.WAIT);
        }
        if(gameWaitList.size() < 1){
            int gameId = currentGameId++;
            gameWaitList.add(gameId);
            Game game = new Game(gameId);
            return new GameQueue(QueueEvent.WAIT, game);
        }else{
            Game game = new Game(gameWaitList.poll());
            return new GameQueue(QueueEvent.JOIN, game);
        }
    }
}
