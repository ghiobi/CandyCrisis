package app.candycrisis.player;

import app.candycrisis.Game;
import app.candycrisis.Piece;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HumanUIPlayer implements Player {

    private boolean pressed = false;

    private JFrame frame = new JFrame("Candy Crisis");

    private JPanel container = new JPanel();

    private Piece selected;

    public void init() {
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setContentPane(this.container);
        this.frame.setSize(300, 170);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);

        this.frame.setVisible(true);
        this.container.setVisible(false);
    }

    /**
     * Manual entry for the player and checks for valid input
     *
     * @param game in position
     * @return valid piece move
     */
    public Piece getMove(Game game) {
        this.draw(game);

        while(!pressed){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        pressed = false;

        System.out.println("Selected: " + selected.toString() + "\n");
        return selected;
    }

    public void end() {
        this.frame.dispose();
    }

    protected void draw(final Game game) {
        this.container.removeAll();

        List<Piece> moves = game.getAvailableMoves();

        final Piece[] pieces = game.getPieces();
        JPanel panel = null;
        for (int i = 0; i < pieces.length; i++) {

            if (i % 5 == 0) {
                panel = new JPanel();
                this.container.add(panel);
            }

            final Button button = new Button(Character.toString(pieces[i].getCharacter()), pieces[i].getPosition());

            if (!isEnabled(pieces[i], moves)) {
                button.setEnabled(false);
            }

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (!pressed) {
                        container.setVisible(false);
                        selected = pieces[((Button) e.getSource()).getPosition()];
                        pressed = true;
                    }
                }
            });

            panel.add(button);
        }

        container.setVisible(true);
    }

    private boolean isEnabled(Piece piece, List<Piece> moves) {
        for (Piece move: moves) {
            if (move.equals(piece)) {
                return true;
            }
        }
        return false;
    }

    private class Button extends JButton {

        private int position;

        public Button(String label, int position) {
            super(label);
            this.position = position;
        }

        public int getPosition() {
            return this.position;
        }

    }

}
