package app.candycrisis;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Game implements Cloneable {

	private Piece[] pieces;

	private Piece emptyPiece;

	public Game(Piece[] pieces) {
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

		int a = piece.getPosition();
		int b = this.getEmptyPiece().getPosition();

		piece = this.pieces[a];

		this.getEmptyPiece().setPosition(a);
		this.pieces[a] = this.emptyPiece;

		piece.setPosition(b);
		this.pieces[b] = piece;
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
	@Deprecated
	public List<Piece> getAvailableMoves() {
		return Arrays.stream(this.getArrayOfAvailableMoves())
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
	}

	/**
	 * Returns an array of available pieces with in the order of positions - top, right, bottom, left.
	 *
	 * @return the list of pieces to move, may be null to indicate move is out of bounds.
	 */
	public Piece[] getArrayOfMoves() {
		int[] positions = this.getEmptyPiece().getNeighboringPositions();
		Piece[] available = new Piece[positions.length];

		for (int i = 0; i < positions.length; i++) {
			available[i] = positions[i] != Piece.OUT_OF_BOUNDS_POSITION ? this.pieces[positions[i]] : null;
		}

		return available;
	}

	/**
	 * Checks for end of game.
	 *
	 * @return returns boolean if it is the end of a game
	 */
	public boolean isEndGame() {
		Piece[] pieces = this.getPieces();

		for (int i = 0; i <= 4; i++) {
			if (pieces[i].getCharacter() != pieces[10 + i].getCharacter()) {
				return false;
			}
		}

		return true;
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

	@Override
	public Game clone() {
		Piece[] array = new Piece[this.pieces.length];

		for (int i = 0; i < array.length; i++) {
			array[i] = this.pieces[i].clone();
		}

		return new Game(array);
	}

}
