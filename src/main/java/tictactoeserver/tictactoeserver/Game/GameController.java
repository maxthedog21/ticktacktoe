package tictactoeserver.tictactoeserver.Game;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {



    final private GameService gameService = new GameService();


    @MessageMapping("/queue/start")
    @SendTo("/game/queue")
    public GameQueue enterGameQueue() throws Exception {
        return gameService.enterGameQueue();
    }

    @MessageMapping("/game/move/{id}")
    @SendTo("/game/move/{id}")
    public GameMoveReturn enterGameQueue(@DestinationVariable int id, GameMove gameMove) throws Exception {
        return gameService.gameMove(gameMove, id);
    } 


    
}
