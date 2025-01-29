package tictactoeserver.tictactoeserver.Game;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {


    final private GameService gameService = new GameService();


    @MessageMapping("/move/{id}")
    @SendTo("/game/move")
    public String gameMove(String name, @DestinationVariable Long id) throws Exception {
        return "hello " + name + ", id: " + id;
    }


    @MessageMapping("/queue/start")
    @SendTo("/game/queue")
    public GameQueue enterGameQueue() throws Exception {
        return gameService.enterGameQueue();
    }

    @MessageMapping("/queue/join/{id}")
    @SendTo("/game/queue/wait/{id}")
    public String enterGameQueue(@DestinationVariable Long id) throws Exception {
        return "Someone is joining game: " + id;
    } 


    
}
