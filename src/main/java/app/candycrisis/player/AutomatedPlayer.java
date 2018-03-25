package app.candycrisis.player;

import app.candycrisis.Game;
import app.candycrisis.IllegalPuzzleMoveException;
import app.candycrisis.Piece;
import app.candycrisis.player.heuristic.LaurendyHeuristic;
import app.candycrisis.player.heuristic.ZiadHeuristic;
import app.candycrisis.search.AStarSearchProblem;
import app.candycrisis.search.functions.HeuristicFunction;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class AutomatedPlayer implements Player {

    public enum Action { UP, RIGHT, LEFT, DOWN };

    private List<Action> solution;

    private int step;

    private HeuristicFunction<Game> heuristicFunction;

    public AutomatedPlayer(HeuristicFunction<Game> heuristicFunction) {
        this.heuristicFunction = heuristicFunction;
    }

    public AutomatedPlayer() { }

    @Override
    public void init(Game init) {

        AStarSearchProblem<Game, Action> problem = new AStarSearchProblem<Game, Action>(init)
                // Get all available moves for a state
                .useActionFunction(AutomatedPlayer::getAvailableActions)
                // Apply action to state
                .useActionStateTransitionFunction(AutomatedPlayer::applyActionToState)
                // Cost of moving
                .useCostFunction((game, action) -> 1)
                // Heuristic estimation
                .useHeuristicFunction(this.heuristicFunction == null ?
                        getHeuristicFunction(init) : this.heuristicFunction);

        AStarSearchProblem<Game, Action>.SearchResult result = problem.search(state -> state.getState().isEndGame());

        solution = result.solution();
        step = 0;
    }

    /**
     * Returns available actions to a game.
     *
     * @param game
     * @return
     */
    private static Iterable<Action> getAvailableActions(Game game) {
        return getAvailableActions(game.getEmptyPiece());
    }

    /**
     * Get available actions for a piece.
     *
     * @param piece
     * @return
     */
    public static Iterable<Action> getAvailableActions(Piece piece) {
        List<Action> actions = new LinkedList<>();
        int[] positions = piece.getNeighboringPositions();

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

    /**
     * Returns the successor board with the action applied to it.
     *
     * @param board
     * @param action
     * @return
     */
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

    /**
     * Determines which heuristic function to use depending on the game. If the game is not master use Ziad's Heuristics.
     *
     * @param game
     * @return
     */
    public HeuristicFunction<Game> getHeuristicFunction(Game game) {
        HashMap<Character, Integer> map = new HashMap<>(7);

        for (Piece piece: game.getPieces()) {
            if (!map.containsKey(piece.getCharacter())) {
                map.put(piece.getCharacter(), 1);
            }
        }

        if (map.keySet().size() == 7) {
            return LaurendyHeuristic::estimate;
        }

        return ZiadHeuristic::estimate;
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
