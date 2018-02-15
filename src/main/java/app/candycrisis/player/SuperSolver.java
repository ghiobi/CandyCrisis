package app.candycrisis.player;

import app.candycrisis.*;
import com.google.common.collect.Lists;
import es.usc.citius.hipster.algorithm.Algorithm;
import es.usc.citius.hipster.algorithm.Hipster;
import es.usc.citius.hipster.model.Transition;
import es.usc.citius.hipster.model.function.ActionFunction;
import es.usc.citius.hipster.model.function.ActionStateTransitionFunction;
import es.usc.citius.hipster.model.function.CostFunction;
import es.usc.citius.hipster.model.function.HeuristicFunction;
import es.usc.citius.hipster.model.impl.WeightedNode;
import es.usc.citius.hipster.model.problem.ProblemBuilder;
import es.usc.citius.hipster.model.problem.SearchProblem;
import es.usc.citius.hipster.util.Predicate;

import java.lang.reflect.Array;
import java.util.*;

public class SuperSolver implements Player {

    enum Action { UP, RIGHT, LEFT, DOWN };

    private List<Action> solution;

    private int step;

    @Override
    public void init(Game init) {

        SearchProblem problem = ProblemBuilder.create()
            .initialState(init)
            .defineProblemWithExplicitActions()
            .useActionFunction(new ActionFunction<Action, Game>() {
                @Override
                public Iterable<Action> actionsFor(Game state) {
                    // Here we compute the valid movements for the state
                    return validMovementsFor(state);
                }
            })
            .useTransitionFunction(new ActionStateTransitionFunction<Action, Game>() {
                @Override
                public Game apply(Action action, Game state) {
                    // Here we compute the state that results from doing an action A to the current state
                    return applyActionToState(action, state);
                }
            })
            .useCostFunction(new CostFunction<Action, Game, Double>() {
                @Override
                public Double evaluate(Transition<Action, Game> transition) {
                    // Every movement has the same cost, 1
                    return 1d;
                }
            })
            .useHeuristicFunction(new HeuristicFunction<Game, Double>() {
                @Override
                public Double estimate(Game game) {
                    int count = 0;
                    Piece[] pieces = game.getPieces();
                    
                    for (int i = 0; i < 5; i++) {
                        if (pieces[i].getCharacter() != pieces[i + 10].getCharacter()) {
                            count += 1;
                        }
                    }

                    return (double) count;
                }
            })
            .build();

        Algorithm.SearchResult answer = Hipster.createAStar(problem).search(new Predicate<WeightedNode>() {
            @Override
            public boolean apply(WeightedNode node) {
                return ((Game) node.state()).isEndGame();
            }
        });

        solution = Lists.reverse(Algorithm.recoverActionPath(answer.getGoalNode()));
        step = 0;
    }

    private static Iterable<Action> validMovementsFor(Game state) {
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

    private static Game applyActionToState(Action action, Game board) {
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
