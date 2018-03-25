package app.candycrisis;

import app.candycrisis.player.AutomatedPlayer;
import app.candycrisis.player.heuristic.LaurendyHeuristic;
import app.candycrisis.player.heuristic.ZiadHeuristic;
import app.candycrisis.search.functions.HeuristicFunction;
import app.candycrisis.utils.Event;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RequirementsTest {

    public static Object[] games = new Object[4];

    HashMap<String, Integer> options = new HashMap<String, Integer>(){{
        put("0-r", 500);
        put("0-t", 100000);

        put("1-r", 500);
        put("1-t", 120000);

        put("2-r", 30);
        put("2-t", 30000);

        put("3-r", 100);
        put("3-t", 2000000);
    }};

    private static void performAction(Event<String> event) {
        System.out.println(event.getSource());
    }

    @Test
    public void noviceRequirementsTestWithZiadHeuristic() {
        assertGameRuntime(0, ZiadHeuristic::estimate);
    }

    @Test
    public void noviceRequirementsTestWithLaurendyHeuristic() {
        assertGameRuntime(0, ZiadHeuristic::estimate);
    }

    @Test
    public void apprenticeRequirementsTestWithZiadHeuristic() {
        assertGameRuntime(1, ZiadHeuristic::estimate);
    }

    @Test
    public void apprenticeRequirementsTestWithLaurendyHeuristic() {
        assertGameRuntime(1, LaurendyHeuristic::estimate);
    }

    @Test
    public void expertRequirementsTestWithZiadHeuristic() {
        assertGameRuntime(2, ZiadHeuristic::estimate);
    }

    @Test
    public void expertRequirementsTestWithLaurendyHeuristic() {
        assertGameRuntime(2, LaurendyHeuristic::estimate);
    }

    @Test
    public void masterRequirementsTestWithZiadHeuristic() {
        assertGameRuntime(3, ZiadHeuristic::estimate);
    }

    @Test
    public void masterRequirementsTestWithLaurendyHeuristic() {
        assertGameRuntime(3, LaurendyHeuristic::estimate);
    }

    protected void assertGameRuntime(int level, HeuristicFunction<Game> heuristicFunction) {
        List<Game> games = (List<Game>) this.games[level];

        if (games == null) {
            games = new LinkedList<>();

            for (int i = 0; i < options.get(level + "-r"); i++) {
                games.add(GameGenerator.generate(level));
            }

            this.games[level] = games;
        }

        games = games.stream().map(Game::clone).collect(Collectors.toList());

        long start = System.currentTimeMillis();

        new CandyCrisis(games, new AutomatedPlayer(heuristicFunction))
                .onEnd(RequirementsTest::performAction)
                .start();

        assertTrue(System.currentTimeMillis() - start < options.get(level + "-t"));
    }

    @Ignore
    @Test
    public void testNonBlockingMasterGames() {
        List<Game> games = new LinkedList<>();

        for (int i = 0; i < 10000; i++) {
            games.add(GameGenerator.generate(3));
        }

        new CandyCrisis(games, new AutomatedPlayer())
                .onEnd(RequirementsTest::performAction)
                .start();
    }

}
