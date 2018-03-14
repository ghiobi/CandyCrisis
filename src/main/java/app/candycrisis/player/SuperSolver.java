package app.candycrisis.player;

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
                .useActionFunction(SuperSolver::actionsFor)
                // Apply action to state
                .useActionStateTransitionFunction(SuperSolver::apply)
                // Cost of moving
                .useCostFunction((game, action) -> 1)
                // Heuristic estimation
                .useHeuristicFunction(game -> {
                    int count = 0;
                    Piece[] pieces = game.getPieces();

                    for (int i = 0; i < 5; i++) {
                        if (pieces[i].getCharacter() != pieces[i + 10].getCharacter()) {
                            count += 1;
                        }
                    }

                    return count;
                });

        AStarSearchProblem<Game, Action>.SearchResult result = problem.search(state -> state.getState().isEndGame());

        solution = result.solution();
        step = 0;
    }

    private static Iterable<Action> actionsFor(Game state) {
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

    private static Game apply(Game board, Action action) {
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
