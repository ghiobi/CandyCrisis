package app.candycrisis.player.heuristic;

import app.candycrisis.EmptyPiece;
import app.candycrisis.Game;
import app.candycrisis.Piece;

public class ZiadHeuristic {

    public static double estimate(Game state) {
        double count = 0;
        Piece[] pieces = state.getPieces();

        int[] positions = state.getEmptyPiece().getNeighboringPositions();

        for (int i = 0; i < positions.length; i++) {
            if (positions[i] != Piece.OUT_OF_BOUNDS_POSITION) {
                count += i;
            }
        }

        for (int i = 0; i < 15; i++) {
            if ((pieces[i].getCharacter() == EmptyPiece.EMPTY_PIECE_CHARACTER)) {
                if (i < 5 || i > 9) {
                    count++;
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            if (pieces[i].getCharacter() != pieces[i + 10].getCharacter()) {
                if ((pieces[i].getCharacter() != EmptyPiece.EMPTY_PIECE_CHARACTER) ||
                        (pieces[i + 10].getCharacter() != EmptyPiece.EMPTY_PIECE_CHARACTER)) {
                    count += 10;
                    count += i;
                }
            }

            if ((pieces[i].getCharacter() != pieces[i + 10].getCharacter()) &&
                    (pieces[i].getCharacter() != pieces[i + 5].getCharacter())) {

                if ((pieces[i].getCharacter() != EmptyPiece.EMPTY_PIECE_CHARACTER) ||
                        (pieces[i + 5].getCharacter() != EmptyPiece.EMPTY_PIECE_CHARACTER)) {
                    count++;
                }
            }

            if ((pieces[i].getCharacter() != pieces[i + 10].getCharacter()) &&
                    (pieces[i + 5].getCharacter() != pieces[i + 10].getCharacter())) {
                if ((pieces[i + 5].getCharacter() != EmptyPiece.EMPTY_PIECE_CHARACTER) ||
                        (pieces[i + 10].getCharacter() != EmptyPiece.EMPTY_PIECE_CHARACTER)) {
                    count++;
                }
            }
        }

        return count / 7.4;
    }
}
