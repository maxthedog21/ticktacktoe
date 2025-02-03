package tictactoeserver.tictactoeserver.GameQueue;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tictactoeserver.tictactoeserver.Game.GameQueue;
import tictactoeserver.tictactoeserver.Game.GameService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/queue")
public class GameQueueController {

    
    final private GameService gameService = new GameService();

    @GetMapping("/start")
    public GameQueue getMethodName() {
        return gameService.enterGameQueue();
    }
    
}
