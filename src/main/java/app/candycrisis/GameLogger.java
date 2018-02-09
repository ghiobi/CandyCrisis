package app.candycrisis;

public class GameLogger {

    private long startTime;

    private StringBuilder log;

    public GameLogger() {
        this.log = new StringBuilder();
    }

    /**
     *  Sets up the initial starting time of the game.
     */
    public void start(){
        startTime = System.currentTimeMillis();
    }

    /**
     * Logs the duration of a game when a game is ending.
     *
     * @return the duration of the game.
     */
    public long end() {
        long duration = System.currentTimeMillis() - startTime;

        this.log.append('\n').append(duration).append(" ms\n");
        return duration;
    }

    /**
     * Records a moving piece.
     *
     * @param piece the piece to record.
     */
    public void recordMove(char piece) {
        this.log.append(piece);
    }

    /**
     * Returns the complete string log.
     *
     * @return string log.
     */
    public String toString() {
        return this.log.toString();
    }

}
