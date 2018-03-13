package app.candycrisis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

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
	public List<Game> build() {
		BufferedReader in = null;
		
		List<Game> games = new LinkedList<Game>();
		
		try {
			in = new BufferedReader(reader);
			
			String line = in.readLine();
			
			while(line != null) {
				char[] strings = String.join("", line.split(" ")).toCharArray();

				games.add(GameBuilder.buildGameFromChars(strings));
				line = in.readLine();
			}
			
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return games;
	}
	
	/**
	 * Builds game from a one line string.
	 * 
	 * @param strings
	 * @return the candy crisis game
	 */
	public static Game buildGameFromChars(char[] strings) {
		Piece[] pieces = new Piece[15];
		
		for (int i = 0; i < pieces.length; i++) {
			char character = strings[i];
			
			if (character != EmptyPiece.EMPTY_PIECE_CHARACTER) {
				pieces[i] = new Piece(i, character);
			} else {
				pieces[i] = new EmptyPiece(i);
			}
		}
		
		return new Game(pieces);
	}
}
