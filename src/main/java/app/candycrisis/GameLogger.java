package app.candycrisis;

import java.io.*;

public class GameLogger {

    private long startTime;
    private long endTime;
    private PrintWriter outputMoves;

    public GameLogger() {
        try {
            outputMoves = new PrintWriter(new FileWriter("output.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        startTime = System.nanoTime();
    }

    public long end() {
        endTime = System.nanoTime();
        long duration = endTime - startTime;
        outputMoves.println();
        outputMoves.print(String.valueOf(duration) +" ms");
        outputMoves.close();
        return endTime - startTime;
    }

    public void recordMove(Piece piece) {
        char id = piece.getId();
        outputMoves.print(id);
    }

    public String toString() {
        BufferedReader input = null;
        String output = "";
        try {
            input = new BufferedReader(new FileReader("output.txt"));
            String line = input.readLine();
            while(line != null) {
                output += line;
                output += "\n";
                line = input.readLine();
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
}
