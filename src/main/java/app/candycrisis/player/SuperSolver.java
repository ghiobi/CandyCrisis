package app.candycrisis.player;

import app.candycrisis.EmptyPiece;
import app.candycrisis.Game;
import app.candycrisis.IllegalPuzzleMoveException;
import app.candycrisis.Piece;
import app.candycrisis.search.AStarSearchProblem;

import java.util.LinkedList;
import java.util.List;

public class SuperSolver implements Player {

    enum Action { UP, RIGHT, LEFT, DOWN };

    private List<Action> solution;

    private int step;

    @Override
    public void init(Game init) {

        AStarSearchProblem<Game, Action> problem = new AStarSearchProblem<Game, Action>(init)
                // Get all available moves for a state
                .useActionFunction(SuperSolver::getAvailableActions)
                // Apply action to state
                .useActionStateTransitionFunction(SuperSolver::applyActionToState)
                // Cost of moving
                .useCostFunction((game, action) -> 1)
                // Heuristic estimation
                .useHeuristicFunction(SuperSolver::estimateState);

        AStarSearchProblem<Game, Action>.SearchResult result = problem.search(state -> state.getState().isEndGame());

        solution = result.solution();
        step = 0;
    }

    private static Iterable<Action> getAvailableActions(Game state) {
        List<Action> actions = new LinkedList<>();
        int[] positions = state.getEmptyPiece().getNeighboringPositions();

        for (int i = 0; i < positions.length; i++) {
            if (positions[i] != Piece.OUT_OF_BOUNDS_POSITION) {
                switch (i) {
                    case 0:
                        actions.add(Action.UP);
                        continue;
                    case 1:
                        actions.add(Action.RIGHT);
                        continue;
                    case 2:
                        actions.add(Action.DOWN);
                        continue;
                    case 3:
                        actions.add(Action.LEFT);
                }
            }
        }

        return actions;
    }

    private static Game applyActionToState(Game board, Action action) {
        Game successor = board.clone();
        Piece[] pieces = successor.getPieces();
        Piece move = null;
        switch (action){
            case UP:
                move = pieces[successor.getEmptyPiece().getNeighboringTopPosition()];
                break;
            case DOWN:
                move = pieces[successor.getEmptyPiece().getNeighboringBottomPosition()];
                break;
            case LEFT:
                move = pieces[successor.getEmptyPiece().getNeighboringLeftPosition()];
                break;
            case RIGHT:
                move = pieces[successor.getEmptyPiece().getNeighboringRightPosition()];
                break;
        }
        try {
            successor.move(move);
        } catch (IllegalPuzzleMoveException e) {
            e.printStackTrace();
        }
        return successor;
    }

    private static double estimateState(Game game) {
        double count = 0;
        Piece[] pieces = game.getPieces();

        int[] positions = game.getEmptyPiece().getNeighboringPositions();

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

        return count / 12.3;
    }

    @Override
    public Piece getMove(Game game) {
        Piece[] pieces = game.getArrayOfMoves();
        switch (solution.get(step++)) {
            case UP:
                return pieces[0];
            case RIGHT:
                return pieces[1];
            case DOWN:
                return pieces[2];
        }
        return pieces[3];
    }

    @Override
    public void end() {

    }

}
