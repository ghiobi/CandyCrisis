package app.candycrisis;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class GameTest {

	@Test
	public void testToString() {
		Game game = new Game(MockObjects.piecesMock());
		
		assertEquals("A : e | B : r | C : r | D : r | E : r\n"
				+ "F : r | G : b | H : w | I : b | J : b\n"
				+ "K : w | L : y | M : b | N : r | O : y", game.toString());
	}
	
	@Test
	public void testGetEmptyPiece() {
		Game game = new Game(MockObjects.piecesMock());
	
		assertEquals(EmptyPiece.EMPTY_PIECE_CHARACTER, game.getEmptyPiece().getCharacter());
	}
	
	@Test
	public void testAvailableMoves() {
		Game game = new Game(MockObjects.piecesMock());
		
		List<Piece> available = game.getAvailableMoves();
		assertEquals(2, available.size());

		assertEquals(available.get(0).getId(), 'B');
		assertEquals(available.get(1).getId(), 'F');
	}
	
	@Test
	public void testPieceMove() {
		// Move right
		Game game = new Game(MockObjects.piecesMock());
		Piece piece = game.getPieces()[game.getEmptyPiece().getNeighboringRightPosition()];
		try {
			game.move(piece);
			
			assertEquals(game.getEmptyPiece().getPosition(), 1);
			assertEquals(piece.getPosition(), 0);
			assertEquals(game.getPieces()[0].getId(), piece.getId());
		} catch (IllegalPuzzleMoveException e) {
			fail("Failed to move piece");
		}
		
		// Move bottom
		piece = game.getPieces()[game.getEmptyPiece().getNeighboringBottomPosition()];
		try {
			game.move(piece);
			
			assertEquals(game.getEmptyPiece().getPosition(), 6);
			assertEquals(piece.getPosition(), 1);
			assertEquals(game.getPieces()[1].getId(), piece.getId());
		} catch (IllegalPuzzleMoveException e) {
			fail("Failed to move piece");
		}
	}

	@Test(expected = IllegalPuzzleMoveException.class)
	public void testIllegalPieceMove() throws IllegalPuzzleMoveException{
		Game game = new Game(MockObjects.piecesMock());

		game.move(game.getPieces()[8]);
		fail("Did not throw IllegalPuzzleMoveException.");
	}


	@Test
	public void testIsNotEndOfGame() {
		Game game = new Game(MockObjects.piecesMock());

		assertFalse(game.isEndGame());
	}

	@Test
	public void testIsEndOfGame() {
		Game game = new Game(new Piece[]{
				new Piece(0, 'w'),
				new Piece(1, 'r'),
				new Piece(2, 'r'),
				new Piece(3, 'r'),
				new Piece(4, 'y'),
				new Piece(5, 'e'),
				new Piece(6, 'b'),
				new Piece(7, 'y'),
				new Piece(8, 'b'),
				new Piece(9, 'b'),
				new Piece(10, 'w'),
				new Piece(11, 'r'),
				new Piece(12, 'r'),
				new Piece(13, 'r'),
				new Piece(14, 'y')
		});

		assertTrue(game.isEndGame());
	}

}
