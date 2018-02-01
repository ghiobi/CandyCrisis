package app.candycrisis;

import static org.junit.Assert.*;

import org.junit.Test;

public class PieceTest {

	@Test
	public void testToString() {
		Piece piece = new Piece('A', 0, 'r');

		assertEquals("A : r", piece.toString());
	}
	
	@Test 
	public void testNeighboringPositionsAtIndex0() {
		Piece piece = new Piece('A', 0, 'r');
		
		assertEquals(Piece.OUT_OF_BOUNDS_POSITION, piece.getNeighboringTopPosition());
		assertEquals(5, piece.getNeighboringBottomPosition());
		assertEquals(Piece.OUT_OF_BOUNDS_POSITION, piece.getNeighboringLeftPosition());
		assertEquals(1, piece.getNeighboringRightPosition());
	}
	
	@Test 
	public void testNeighboringPositionsAtIndex4() {
		Piece piece = new Piece('A', 4, 'r');
		
		assertEquals(Piece.OUT_OF_BOUNDS_POSITION, piece.getNeighboringTopPosition());
		assertEquals(9, piece.getNeighboringBottomPosition());
		assertEquals(3, piece.getNeighboringLeftPosition());
		assertEquals(Piece.OUT_OF_BOUNDS_POSITION, piece.getNeighboringRightPosition());
	}

	@Test 
	public void testNeighboringPositionsAtIndex6() {
		Piece piece = new Piece('A', 6, 'r');

		assertEquals(1, piece.getNeighboringTopPosition());
		assertEquals(11, piece.getNeighboringBottomPosition());
		assertEquals(5, piece.getNeighboringLeftPosition());
		assertEquals(7, piece.getNeighboringRightPosition());
	}

	@Test
	public void testNeighboringPositionsAtIndex14() {
		Piece piece = new Piece('A', 14, 'r');

		assertEquals(9, piece.getNeighboringTopPosition());
		assertEquals(Piece.OUT_OF_BOUNDS_POSITION, piece.getNeighboringBottomPosition());
		assertEquals(13, piece.getNeighboringLeftPosition());
		assertEquals(Piece.OUT_OF_BOUNDS_POSITION, piece.getNeighboringRightPosition());
	}

	@Test
	public void testEqualsPiece() {
		Piece pieceA = new Piece('A', 6, 'r');
		Piece pieceB = new Piece('A', 6, 'r');

		assertTrue(pieceA.equals(pieceB));
	}

	@Test
	public void testClone() {
		Piece pieceA = new Piece('A', 6, 'r');
		Piece pieceB = pieceA.clone();

		assertFalse(pieceA == pieceB);
	}

}
