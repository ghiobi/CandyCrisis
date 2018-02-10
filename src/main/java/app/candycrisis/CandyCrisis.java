package app.candycrisis;

import app.candycrisis.player.HumanPlayer;
import app.candycrisis.player.Player;
import app.candycrisis.utils.Action;
import app.candycrisis.utils.Event;

import java.util.List;

public class CandyCrisis {
	
	private List<Game> games;

	private Action<String> onEnd;

	private GameLogger logger = new GameLogger();

	public CandyCrisis(List<Game> games) {
		this.games = games;
	}

	/**
	 * Starts the Candy Crisis game.
	 */
	public void start() {

		for (Game game : games) {

			logger.start();
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

			logger.end();
		}

		if (this.onEnd != null) {
			this.onEnd.performAction(new Event<String>(this.logger.toString()));
		}
	}

	public CandyCrisis onEnd(Action<String> action) {
		this.onEnd = action;
		return this;
	}

}
