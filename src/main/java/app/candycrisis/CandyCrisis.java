package app.candycrisis;

import app.candycrisis.player.Player;
import app.candycrisis.utils.Action;
import app.candycrisis.utils.Event;

import java.util.List;

public class CandyCrisis {
	
	private List<Game> games;

	private Action<String> onEnd;

	private GameLogger logger = new GameLogger();

	private Player player;

	public CandyCrisis(List<Game> games, Player player) {
		this.games = games;
		this.player = player;
	}

	/**
	 * Starts the Candy Crisis game.
	 */
	public void start() {

		for (Game game : games) {

			printNewGameMessage();
			logger.start();
			player.init(game);
			
			while (!game.isEndGame()) {

                System.out.println(game.toGameString());
				System.out.println();

				Piece move = player.getMove(game);
				char id = move.getId();

				try {
					game.move(move);
					logger.recordMove(id);
				} catch (IllegalPuzzleMoveException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			player.end();
			logger.end();
		}

		if (this.onEnd != null) {
			this.onEnd.performAction(new Event<>(this.logger.toString()));
		}
	}

	public CandyCrisis onEnd(Action<String> action) {
		this.onEnd = action;
		return this;
	}

	private void printNewGameMessage() {
		System.out.println("^+^+^+^+^+^+^+^+^+^+^+^+^+^+^+^+^+^+^+^+^");
		System.out.println("~~~~~A NEW CANDY CRISIS GAME STARTED~~~~~");
		System.out.println("^+^+^+^+^+^+^+^+^+^+^+^+^+^+^+^+^+^+^+^+^\n");
	}

}
