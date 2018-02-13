package app.candycrisis.player;

import app.candycrisis.Game;
import app.candycrisis.Piece;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class HumanPlayer implements Player {

    private Scanner reader;

    public void init(Game game) {
        this.reader = new Scanner(System.in);
    }

    /**
     * Manual entry for the player and checks for valid input
     *
     * @param game in position
     * @return valid piece move
     */
	public Piece getMove(Game game) {
        List<Piece> options = game.getAvailableMoves();
        System.out.println(options);

        // loops until user enters a valid index
        while (true) {
            try {
                System.out.print("Type an option to select (1 to " + options.size() + "): ");
                if (reader.hasNextInt()) {
                    int n = reader.nextInt();

                    System.out.println();
                    return options.get(n - 1);
                }
                reader.next();
                System.out.println();
                throw new InputMismatchException();
            } catch (IndexOutOfBoundsException|InputMismatchException e) {
                System.out.println("Option not valid or incorrect, please try again!");
            }
        }
	}

    public void end() { }

}
