package app.candycrisis.player.heuristic;

import app.candycrisis.Game;
import app.candycrisis.Piece;

public class HarrisonHeuristic {

    /**
     * The estimation function
     *
     * @param game
     * @return
     */
    public static double estimate(Game game) {
        int count = 0;
        Piece[] pieces = game.getPieces();
        int emptyPosition = game.getEmptyPiece().getPosition();

        for(int pairs =0; pairs < 5; pairs++) { // loop through 5 pairs
            Piece topPiece = pieces[pairs];
            Piece bottomPiece = pieces[pairs+10];
            if (topPiece.getCharacter() == bottomPiece.getCharacter()) {
                continue;
            }
            int topCharacterCount = 1000; // estimate for top number in the pair. Set to exaggerated worst case
            int bottomCharacterCount = 1000; // estimate for bottom number in the pair
            for (int index = pairs + 1; index < 15; index++) {
                if (pieces[index].getCharacter() == topPiece.getCharacter()) {
                    int tempEstimate = Math.abs(getXPosition(pieces[index]) - pairs) + // cost to move left/right
                            +Math.abs(getYPosition(pieces[index]) - 2) // cost to move up
                            + Math.abs(getXPosition(pieces[index]) - getXPosition(pieces[emptyPosition]))  // x diatance to empty piece
                            + Math.abs(getYPosition(pieces[index]) - getYPosition(pieces[emptyPosition]));
                    if (tempEstimate == 0) {
                        topCharacterCount = tempEstimate;
                        break;
                    }
                    if (tempEstimate < topCharacterCount) {
                        topCharacterCount = tempEstimate;
                    }
                }
            }
            for (int index = pairs + 1; index < 15; index++) {
                if (pieces[index].getCharacter() == bottomPiece.getCharacter()) {
                    int tempEstimate = Math.abs(getXPosition(pieces[index]) - pairs) + // cost to move left/right
                            +getYPosition(pieces[index]) // cost to move up
                            + Math.abs(getXPosition(pieces[index]) - getXPosition(pieces[emptyPosition]))  // x diatance to empty piece
                            + Math.abs(getYPosition(pieces[index]) - getYPosition(pieces[emptyPosition]));
                    if (tempEstimate == 0) {
                        bottomCharacterCount = tempEstimate;
                        break;
                    }
                    if (tempEstimate < bottomCharacterCount) {
                        bottomCharacterCount = tempEstimate;
                    }
                }
            }
            count = (topCharacterCount < bottomCharacterCount) ? topCharacterCount : bottomCharacterCount;
            break;
        }
        return count;
    }

    /**
     * Returns the X position of piece if it were represented in 2D matrix
     *
     * @param piece
     * @return
     */
    private static int getXPosition(Piece piece) {
        return piece.getPosition() % 5;
    }

    /**
     * Returns the Y position of piece if it were represented in 2D matrix
     *
     * @param piece
     * @return
     */
    private static int getYPosition(Piece piece) {
        return piece.getPosition() / 5;
    }

}
