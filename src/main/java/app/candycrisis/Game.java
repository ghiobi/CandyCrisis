package app.candycrisis;

public class Game implements Cloneable {
	
	private Puzzle puzzle;
	
	public Game(Puzzle puzzle) {
		this.puzzle = puzzle;
	}
	
	public Puzzle getPuzzle() {
		return puzzle;
	}

	/**
	 * Checks for end of game.
	 *
	 * @return returns boolean if it is the end of a game
	 */
	public boolean isEndGame() {
		return false;
	}

	public void move(Piece piece) throws IllegalPuzzleMoveException {
		this.puzzle.move(piece);
	}

	@Override
	public Game clone() {
		return new Game(this.puzzle.clone());
	}

	public String getState() {
		StringBuilder builder = new StringBuilder();

		for (Piece piece : this.puzzle.getPieces()) {
			builder.append(piece.getCharacter());
		}

		return builder.toString();
	}

}
