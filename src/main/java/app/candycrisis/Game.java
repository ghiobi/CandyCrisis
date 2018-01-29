package app.candycrisis;

public class Game {
	
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

}
