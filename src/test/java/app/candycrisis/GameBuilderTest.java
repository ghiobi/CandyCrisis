package app.candycrisis;

import static org.junit.Assert.*;

import java.io.StringReader;
import java.util.List;

import org.junit.Test;

public class GameBuilderTest {

	@Test
	public void testNumberOfGames() {
		String file = "e r r r r r b w b b w y b r y\n"
				+ "e g g r r r b w b b w y b b y";
		List<Game> games = GameBuilder.build(new StringReader(file));
		
		char[][] characters = {{'e', 'r', 'r', 'r', 'r', 'r', 'b', 'w', 'b', 'b', 'w', 'y', 'b', 'r', 'y'}, 
				{'e', 'g', 'g', 'r', 'r', 'r', 'b', 'w', 'b', 'b', 'w', 'y', 'b', 'b', 'y'}};
		
		for (int i = 0; i < games.size(); i++) {
			Piece[] pieces = games.get(i).getPieces();
			for (int j = 0; j < 15; j++) {
				assertEquals(characters[i][j], pieces[j].getCharacter());
			}
		}
	}

}
