package app.candycrisis;

import app.candycrisis.player.AutomatedPlayer;
import app.candycrisis.player.heuristic.ZiadHeuristic;
import app.candycrisis.utils.Event;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

public class RequirementsTest {

    private static void performAction(Event<String> event) {
        System.out.println(event.getSource());
    }

    @Test
    public void noviceRequirementsTest() {
        assertGameRuntime(500, 0, 100000);
    }

    @Test
    public void apprenticeRequirementsTest() {
        assertGameRuntime(500, 1, 120000);
    }

    @Test
    public void expertRequirementsTest() {
        assertGameRuntime(300, 2, 300000);
    }

    @Test
    public void masterRequirementsTest() {
        assertGameRuntime(100, 3, 200000);
    }

    protected void assertGameRuntime(int runs, int level,  int time) {
        List<Game> games = new LinkedList<>();

        for (int i = 0; i < runs; i++) {
            games.add(GameGenerator.generate(level));
        }

        long start = System.currentTimeMillis();

        new CandyCrisis(games, new AutomatedPlayer(ZiadHeuristic::estimate))
                .onEnd(RequirementsTest::performAction)
                .start();

        assertTrue(System.currentTimeMillis() - start < time);
    }

}
