package app.candycrisis.player;

import app.candycrisis.Game;
import app.candycrisis.Piece;

import java.util.List;
import java.util.Scanner;

public class HumanPlayer implements Player {

    /**
     * Manual entry for the player and checks for valid input
     *
     * @param game in position
     * @return valid piece move
     */
	public Piece getMove(Game game) {

        Scanner reader = new Scanner(System.in);
        List<Piece> options = game.getPuzzle().getAvailableMoves();
        Piece choice = null;
        boolean notValid = true;

        System.out.println(options + " (starting from 1 to " + options.size() + ")");

        // loops until user enters a valid index
        while (notValid){
            try{
                System.out.print("Select which index:");
                int n = reader.nextInt();
                choice = options.get(n-1);
                notValid = false;
            } catch (IndexOutOfBoundsException e){
                System.out.println("Index out of bounds. Invalid input, please try again!");
            }
        }

		return choice;
	}

}
