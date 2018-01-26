package app.candycrisis;

public class Puzzle {
	
	private Piece[] pieces;
	private Piece emptyPiece;
	
	public Puzzle(Piece[] pieces) {
		this.pieces = pieces;
		
		for (Piece piece : pieces) {
			if (piece instanceof EmptyPiece) {
				this.emptyPiece = piece; break;
			}
		}
	}

	public Piece[] getPieces() {
		return pieces;
	}

	public void setPieces(Piece[] pieces) {
		this.pieces = pieces;
	}

	public Piece getEmptyPiece() {
		return emptyPiece;
	}

	/**
	 * Checks if this puzzle is correct from the number of pieces.
	 * 
	 * @return
	 */
	protected boolean isValid() {
		return true;
	}
	
}
