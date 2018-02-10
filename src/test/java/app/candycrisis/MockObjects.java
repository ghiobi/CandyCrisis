package app.candycrisis;

public class MockObjects {
	
	public static Piece[] piecesMock() {
		return new Piece[]{
			new EmptyPiece(0),
			new Piece(1, 'r'),
			new Piece(2, 'r'),
			new Piece(3, 'r'),
			new Piece(4, 'r'),
			new Piece(5, 'r'),
			new Piece(6, 'b'),
			new Piece(7, 'w'),
			new Piece(8, 'b'),
			new Piece(9, 'b'),
			new Piece(10, 'w'),
			new Piece(11, 'y'),
			new Piece(12, 'b'),
			new Piece(13, 'r'),
			new Piece(14, 'y')
		};
	}
	
}
