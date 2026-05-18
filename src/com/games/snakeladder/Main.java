import java.util.ArrayList;
import java.util.List;

import dice.impl.ComputerDiceRollSnakeLadderStrategy;
import dice.impl.RandomDiceRollSnakeLadderStrategy;
import game.Game;
import game.GameFactory;
import players.Audience;
import players.ComputerPlayer;
import players.HumanPlayer;
import players.Player;
import players.User;
import utils.GameController;

public class Main {
    public static void main(String[] args) {
        User salil = new User("Salil");
        User rahul = new User("Rahul");
        Audience swarup = new Audience("Swarup");
        Audience virendra = new Audience("Virendra");

        List<Player> players = new ArrayList<>();
        players.add(new HumanPlayer(salil, "Sal", RandomDiceRollSnakeLadderStrategy.getInstance()));
        players.add(new HumanPlayer(rahul, "Rah", RandomDiceRollSnakeLadderStrategy.getInstance()));
        players.add(new ComputerPlayer("Bot1", "Com", ComputerDiceRollSnakeLadderStrategy.getInstance()));
        Game game = GameFactory.getInstance().createQuickSnakeLadder(players);

        GameController controller = new GameController(game);
        controller.addAudience(swarup);
        controller.addAudience(virendra);

        controller.startGame();
    }
}
