package app.candycrisis;

public class EmptyPiece extends Piece {
	
	public final static char EMPTY_PIECE_CHARACTER = 'e';
	
	public EmptyPiece(int position) {
		super(position, EmptyPiece.EMPTY_PIECE_CHARACTER);
	}
	
}
