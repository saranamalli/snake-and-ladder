package game.stats;
import players.Player;

public class SnakeLadderGameStats implements GameStats {
    private Player player;

    public SnakeLadderGameStats(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
