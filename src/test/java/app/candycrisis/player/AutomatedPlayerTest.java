package app.candycrisis.player;

import app.candycrisis.Game;
import app.candycrisis.GameBuilder;
import app.candycrisis.player.heuristic.ZiadHeuristic;
import org.junit.Test;

public class AutomatedPlayerTest {

    @Test
    public void test() {
        Player player = new AutomatedPlayer();
        Game game = GameBuilder.build("r e b w r b b b r r r b r b w");

        player.init(game);
    }
}