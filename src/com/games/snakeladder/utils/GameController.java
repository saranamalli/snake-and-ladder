package utils;

import dice.Move;
import events.GameEvent;
import events.MoveMade;
import game.Game;
import game.stats.GameStats;
import players.Audience;
import players.Player;

public class GameController {
    private final Game game;
    private final GameEvent event;

    public GameController(Game game) {
        this.game = game;
        this.event = new GameEvent();
    }

    public void startGame() {
        game.initialize();
        while (!game.isOver()) {
            Player player = game.getPlayerWithTurn();
            Move move;
            move = player.generateMove();
            game.makeMove(move);
            event.dispatchEvent(new MoveMade(move));
        }
        if (game.isADraw()) {
            System.out.println("game was a draw!");
        } else {
            GameStats winnerStats = game.getWinningPlayerStats();
            System.out.println("Game was won: " + winnerStats.toString());
        }
    }

    public void addAudience(Audience audience) {
        event.addListener(audience.getGameEventHandler());
    }
}