package app.candycrisis;

public class Game {
	
	private Puzzle puzzle;
	
	public Game(Puzzle puzzle) {
		this.puzzle = puzzle;
	}
	
	public Puzzle getPuzzle() {
		return puzzle;
	}
	
	public boolean isEndGame() {
		return false;
	}

	public void move(PuzzleMove move) throws IllegalPuzzleMoveException {
		
	}

}
