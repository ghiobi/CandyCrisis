package app.candycrisis.player;

import app.candycrisis.Game;
import app.candycrisis.PuzzleMove;

public interface Player {
	
	public PuzzleMove getMove(Game game);
	
}
