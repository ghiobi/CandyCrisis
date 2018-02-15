package app.candycrisis.player;

import app.candycrisis.Game;
import app.candycrisis.GameBuilder;
import org.junit.Test;

import java.io.StringReader;

import static org.junit.Assert.*;

public class SuperSolverTest {
    @Test
    public void test() {
        Player player = new SuperSolver();
        Game game = new GameBuilder(new StringReader("r e b w r b b b r r r b r b w")).build().get(0);

        player.init(game);
    }
}