package app.candycrisis;

import app.candycrisis.player.HumanPlayer;
import app.candycrisis.player.Player;

import java.util.List;

public class CandyCrisis {
	
	private List<Game> games;

	public CandyCrisis(List<Game> games) {
		this.games = games;
	}

	/**
	 * Starts the Candy Crisis game.
	 */
	public void start() {
		for (Game game : games) {
			Player player = new HumanPlayer();
			
			while (!game.isEndGame()) {

                System.out.println(game.getPuzzle().toString());
				Piece move = player.getMove(game);

				try {
					game.move(move);
				}
				catch (IllegalPuzzleMoveException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
