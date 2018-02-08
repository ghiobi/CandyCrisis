package app.candycrisis;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameTest {

	@Test
	public void testIsNotEndOfGame() {
		Game game = new Game(new Puzzle(MockObjects.piecesMock()));

		assertFalse(game.isEndGame());
	}

	@Test
	public void testIsEndOfGame() {
		Game game = new Game(new Puzzle(new Piece[]{
				new Piece('A', 0, 'w'),
				new Piece('B', 1, 'r'),
				new Piece('C', 2, 'r'),
				new Piece('D', 3, 'r'),
				new Piece('E', 4, 'y'),
				new Piece('F', 5, 'e'),
				new Piece('G', 6, 'b'),
				new Piece('H', 7, 'y'),
				new Piece('I', 8, 'b'),
				new Piece('J', 9, 'b'),
				new Piece('K', 10, 'w'),
				new Piece('L', 11, 'r'),
				new Piece('M', 12, 'r'),
				new Piece('N', 13, 'r'),
				new Piece('O', 14, 'y')
		}));

		assertTrue(game.isEndGame());
	}

}
