package app.candycrisis;

import app.candycrisis.player.SuperSolver;
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
    public void noiveRequirementsTest() {
        List<Game> games = new LinkedList<>();

        for (int i = 0; i < 50; i++) {
            games.add(GameGenerator.generate(0));
        }

        long start = System.currentTimeMillis();

        new CandyCrisis(games, new SuperSolver())
            .onEnd(RequirementsTest::performAction)
            .start();

        assertTrue(System.currentTimeMillis() - start < 10000);
    }

    @Test
    public void apprenticeRequirementsTest() {
        List<Game> games = new LinkedList<>();

        for (int i = 0; i < 50; i++) {
            games.add(GameGenerator.generate(1));
        }

        long start = System.currentTimeMillis();

        new CandyCrisis(games, new SuperSolver())
            .onEnd(RequirementsTest::performAction)
            .start();

        assertTrue(System.currentTimeMillis() - start < 12000);
    }

    @Test
    public void expertRequirementsTest() {
        List<Game> games = new LinkedList<>();

        for (int i = 0; i < 30; i++) {
            games.add(GameGenerator.generate(2));
        }

        long start = System.currentTimeMillis();

        new CandyCrisis(games, new SuperSolver())
            .onEnd(RequirementsTest::performAction)
            .start();

        assertTrue(System.currentTimeMillis() - start < 30000);
    }

    @Test
    public void masterRequirementsTest() {
        List<Game> games = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            games.add(GameGenerator.generate(3));
        }

        long start = System.currentTimeMillis();

        new CandyCrisis(games, new SuperSolver())
            .onEnd(RequirementsTest::performAction)
            .start();

        assertTrue(System.currentTimeMillis() - start < 20000);
    }

}
