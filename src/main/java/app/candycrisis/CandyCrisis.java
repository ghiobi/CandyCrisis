package app.candycrisis;

import app.candycrisis.player.HumanPlayer;
import app.candycrisis.player.Player;

public class CandyCrisis {
	
	private Game[] games;
	
	private Player player;
	
	public CandyCrisis(Game[] games) {
		this.games = games;
	}

	public void start() {
		for (Game game : games) {
			this.player = new HumanPlayer();
			
			while (!game.isEndGame()) {
				PuzzleMove move = this.player.getMove(game);
				
				try {
					game.move(move);
				} catch (IllegalPuzzleMoveException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
