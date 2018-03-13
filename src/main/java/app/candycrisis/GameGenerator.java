package app.candycrisis;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameGenerator {

    private static String[] boards = {
        "r r r r r r b b b b b b w w e",
        "r r r r r r b b b b w w y y e",
        "r r r r b b b b w w y y g g e",
        "r r r r b b w w y y g g p p e"
    };

    /**
     * Generates a game according to level of difficulty. 0 easy to 3 for hardest.
     *
     * @param level 0 - 3
     * @return Game
     */
    public static Game generate(int level) {
        List<String> list = Arrays.asList(boards[level].split(" "));
        Collections.shuffle(list);

        return GameBuilder.buildGameFromChars(String.join("", list).toCharArray());
    }

}
