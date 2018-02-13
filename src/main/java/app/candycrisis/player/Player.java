package app.candycrisis.player;

import app.candycrisis.Game;
import app.candycrisis.Piece;

public interface Player {

	public void init(Game game);

	public Piece getMove(Game game);

	public void end();
	
}
