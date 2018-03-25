package app.candycrisis.player.heuristic;

import app.candycrisis.EmptyPiece;
import app.candycrisis.Game;
import app.candycrisis.Piece;
import app.candycrisis.player.AutomatedPlayer;

import java.util.HashMap;
import java.util.LinkedList;

public class LaurendyHeuristic {

    /**
     * The estimation function
     *
     * @param game
     * @return
     */
    public static double estimate(Game game) {
        double count = 0;
        Piece[] pieces = game.getPieces();

        for (int i = 0; i < 5; i++) {
            if (pieces[i].getCharacter() != pieces[i + 10].getCharacter()) {
                double top = 100;
                double bottom = 100;

                if (pieces[i].getCharacter() != EmptyPiece.EMPTY_PIECE_CHARACTER)
                    top = computeDistance(pieces[i + 10], getNearest(game, pieces[i]));

                if (pieces[i + 10].getCharacter() != EmptyPiece.EMPTY_PIECE_CHARACTER)
                    bottom = computeDistance(pieces[i], getNearest(game, pieces[i + 10]));
                ;

                if (top <= bottom) {
                    count += top;
                } else {
                    count += bottom;
                }
            }
        }

        return count * 1.6;
    }


    /**
     * Returns the nearest piece that has the same character to the current piece.
     *
     * @param game the board game
     * @param piece the piece
     * @return the piece that has the same character
     */
    private static Piece getNearest(Game game, final Piece piece) {
        Piece[] pieces = game.getPieces();

        LinkedList<Piece> open = new LinkedList<>();
        HashMap<Piece, Piece> closed = new HashMap<>(15);

        open.push(piece);
        Piece current = null;

        do {
            current = open.poll();

            if (piece != current && current.getCharacter() == piece.getCharacter()) {
                return current;
            }

            for (AutomatedPlayer.Action action : AutomatedPlayer.getAvailableActions(current)) {
                Piece successor = null;

                switch (action) {
                    case UP:
                        successor = pieces[current.getNeighboringTopPosition()];
                        break;
                    case DOWN:
                        successor = pieces[current.getNeighboringBottomPosition()];
                        break;
                    case LEFT:
                        successor = pieces[current.getNeighboringLeftPosition()];
                        break;
                    case RIGHT:
                        successor = pieces[current.getNeighboringRightPosition()];
                        break;
                }

                if (closed.containsKey(successor)) {
                    continue;
                }

                if (piece != successor && successor.getCharacter() == piece.getCharacter()) {
                    return successor;
                }

                open.addLast(successor);
            }

            closed.put(current, current);
        } while (!open.isEmpty());

        return current;
    }

    /**
     * Computes the distance between to pieces.
     *
     * @param piece
     * @param other
     * @return
     */
    private static double computeDistance(Piece piece, Piece other) {
        return Math.sqrt((Math.pow(getPieceXPosition(piece) - getPieceXPosition(other), 2)) +
                Math.pow(getPieceYPosition(piece) - getPieceYPosition(other), 2));
    }

    /**
     * Returns the X position of piece if it were represented in 2D matrix
     *
     * @param piece
     * @return
     */
    private static int getPieceXPosition(Piece piece) {
        return piece.getPosition() % 5;
    }

    /**
     * Returns the Y position of piece if it were represented in 2D matrix
     *
     * @param piece
     * @return
     */
    private static int getPieceYPosition(Piece piece) {
        return piece.getPosition() / 5;
    }

}
