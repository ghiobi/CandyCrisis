package app.candycrisis;

import app.candycrisis.player.HumanPlayer;
import app.candycrisis.player.Player;

import java.util.List;

public class CandyCrisis {
	
	private List<Game> games;
	private GameLogger logger = new GameLogger();

	public CandyCrisis(List<Game> games) {
		this.games = games;
	}

	/**
	 * Starts the Candy Crisis game.
	 */
	public void start() {
		logger.start();
		for (Game game : games) {
			Player player = new HumanPlayer();
			
			while (!game.isEndGame()) {
				Piece move = player.getMove(game);
				
				try {
					game.move(move);
					logger.recordMove(move);
				} catch (IllegalPuzzleMoveException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		logger.end();
	}
}
