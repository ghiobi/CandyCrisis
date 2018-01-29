package app.candycrisis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class GameBuilder {
	
	private Reader reader;

	public GameBuilder(Reader reader) {
		this.reader = reader;
	}
	
	/**
	 * Builds games from the provided reader.
	 * 
	 * @return an array of candy crisis games
	 */
	public Game[] build() {
		BufferedReader in = null;
		
		ArrayList<Game> games = new ArrayList<Game>();
		
		try {
			in = new BufferedReader(reader);
			
			String line = in.readLine();
			
			while(line != null) {	
				games.add(this.buildGameFromString(line));
				line = in.readLine();
			}
			
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return games.toArray(new Game[games.size()]);
	}
	
	/**
	 * Builds game from a one line string.
	 * 
	 * @param string
	 * @return the candy crisis game
	 */
	protected Game buildGameFromString(String string) {
		Piece[] pieces = new Piece[15];
		String[] strings = string.split(" ");
		
		for (int i = 0; i < pieces.length; i++) {
			char id = (char) (i + 65);
			char character = strings[i].charAt(0);
			
			if (character != EmptyPiece.EMPTY_PIECE_CHARACTER) {
				pieces[i] = new Piece(id, i, character);
			} else {
				pieces[i] = new EmptyPiece(id, i);
			}
		}
		
		return new Game(new Puzzle(pieces));
	}
}
