package app.candycrisis;

public class EmptyPiece extends Piece {
	
	final static char EMPTY_PIECE_CHARACTER = 'e';
	
	public EmptyPiece(char id, int position) {
		super(id, position,EmptyPiece.EMPTY_PIECE_CHARACTER);
	}
	
}
