package app.candycrisis;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class PuzzleTest {

	@Test
	public void testToString() {
		Puzzle puzzle = new Puzzle(MockObjects.piecesMock());
		
		assertEquals("A : e | B : r | C : r | D : r | E : r\n"
				+ "F : r | G : b | H : w | I : b | J : b\n"
				+ "K : w | L : y | M : b | N : r | O : y", puzzle.toString());
	}
	
	@Test
	public void testGetEmptyPiece() {
		Puzzle puzzle = new Puzzle(MockObjects.piecesMock());
	
		assertEquals(EmptyPiece.EMPTY_PIECE_CHARACTER, puzzle.getEmptyPiece().getCharacter());
	}
	
	@Test
	public void testAvailableMoves() {
		Puzzle puzzle = new Puzzle(MockObjects.piecesMock());
		
		List<Piece> available = puzzle.getAvailableMoves();
		assertEquals(2, available.size());

		assertEquals(available.get(0).getId(), 'B');
		assertEquals(available.get(1).getId(), 'F');
	}
	
	@Test
	public void testPieceMove() {
		// Move right
		Puzzle puzzle = new Puzzle(MockObjects.piecesMock());
		Piece piece = puzzle.getPieces()[puzzle.getEmptyPiece().getNeighboringRightPosition()];
		try {
			puzzle.move(piece);
			
			assertEquals(puzzle.getEmptyPiece().getPosition(), 1);
			assertEquals(piece.getPosition(), 0);
			assertEquals(puzzle.getPieces()[0].getId(), piece.getId());
		} catch (IllegalPuzzleMoveException e) {
			fail("Failed to move piece");
		}
		
		// Move bottom
		piece = puzzle.getPieces()[puzzle.getEmptyPiece().getNeighboringBottomPosition()];
		try {
			puzzle.move(piece);
			
			assertEquals(puzzle.getEmptyPiece().getPosition(), 6);
			assertEquals(piece.getPosition(), 1);
			assertEquals(puzzle.getPieces()[1].getId(), piece.getId());
		} catch (IllegalPuzzleMoveException e) {
			fail("Failed to move piece");
		}
	}

	@Test(expected = IllegalPuzzleMoveException.class)
	public void testIllegalPieceMove() throws IllegalPuzzleMoveException{
		Puzzle puzzle = new Puzzle(MockObjects.piecesMock());

		puzzle.move(puzzle.getPieces()[8]);
		fail("Did not throw IllegalPuzzleMoveException.");
	}

}
