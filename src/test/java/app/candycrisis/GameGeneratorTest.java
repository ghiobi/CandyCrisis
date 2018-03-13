package app.candycrisis;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameGeneratorTest {

    @Test
    public void generate() {
        Game game = GameGenerator.generate(3);

        System.out.println(String.join(" ", game.toString().split("")));
    }
}