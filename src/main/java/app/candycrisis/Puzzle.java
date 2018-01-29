package app.candycrisis;

import java.util.ArrayList;
import java.util.List;

public class Puzzle {
	
	private Piece[] pieces;
	private Piece emptyPiece;
	
	public Puzzle(Piece[] pieces) {
		this.pieces = pieces;
		
		for (Piece piece : pieces) {
			if (piece.getCharacter() == EmptyPiece.EMPTY_PIECE_CHARACTER) {
				this.emptyPiece = piece; break;
			}
		}
	}

	public Piece[] getPieces() {
		return pieces;
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
	
	public void move(Piece piece) throws IllegalPuzzleMoveException {
		if (piece == null || !this.isValidMove(piece) ) {
			throw new IllegalPuzzleMoveException();
		}
		
		int emptyPiecePosition = this.getEmptyPiece().getPosition();
		int piecePosition = piece.getPosition();
		
		piece.setPosition(emptyPiecePosition);
		this.getEmptyPiece().setPosition(piecePosition);
		
		this.pieces[piecePosition] = this.emptyPiece;
		this.pieces[emptyPiecePosition] = piece;
	}

	/**
	 * Checks if the current piece is valid move.
	 *
	 * @param moving the moving piece
	 * @return a boolean describing if this is a valid move
	 */
	public boolean isValidMove(Piece moving) {
		for (int index : this.getEmptyPiece().getNeighboringPositions()) {
			if (index == moving.getPosition()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns a list of available pieces that may move.
	 *
	 * @return the list of available pieces to move
	 */
	public List<Piece> getAvailableMoves() {
		List<Piece> moveable = new ArrayList<Piece>(4);
		
		int[] pieces = this.getEmptyPiece().getNeighboringPositions();
		
		for (int index : pieces) {
			if (index != Piece.OUT_OF_BOUNDS_POSITION) {
				moveable.add(this.pieces[index]);
			}
		}
		
		return moveable;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < pieces.length; i++) {
			if (i % 5 != 0) {
				builder.append(" | ");
			} else if (i != 0) {
				builder.append("\n");
			}
			builder.append(pieces[i].toString());
		}
		
		return builder.toString();
	}
	
}
