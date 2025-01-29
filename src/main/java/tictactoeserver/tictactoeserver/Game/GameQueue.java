package tictactoeserver.tictactoeserver.Game;

enum QueueEvent {
    JOIN,
    WAIT,
    MAX
}

public class GameQueue {
    public QueueEvent queueEvent;
    public Game game;

    public GameQueue(QueueEvent queueEvent, Game game) {
        this.queueEvent = queueEvent;
        this.game = game;
    }

    public GameQueue(QueueEvent queueEvent ) {
        this.queueEvent = queueEvent;
        this.game = null;
    }
    
}
