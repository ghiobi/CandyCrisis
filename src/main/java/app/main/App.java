package app.main;

import java.io.*;

import app.candycrisis.CandyCrisis;
import app.candycrisis.GameBuilder;
import app.candycrisis.player.HumanPlayer;
import app.candycrisis.player.HumanUIPlayer;
import app.candycrisis.player.Player;
import app.candycrisis.player.SuperSolver;
import org.apache.commons.cli.*;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        long start = System.currentTimeMillis();
        CommandLineParser parser = new DefaultParser();

        Options options = (new Options())
                .addOption("b", "board", true, "The board path file containing games.")
                .addOption("o", "output", true, "The output stats board.")
                .addOption("p", "player", true, "The player type. Enter i, c, a respective for interactive, console, or automated.")
                .addOption("h", "help",false, "Help.");

        final CommandLine line = parser.parse(options, args);

        if (line.hasOption("help") || line.getOptions().length == 0) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "Candy Crisis",  options);
            System.exit(0);
        }

        if (!line.hasOption("board")) {
            System.out.println("--board flag is required. Ex --board=boards.txt. --help for help.");
            System.exit(0);
        }

        Reader reader =  new InputStreamReader(new FileInputStream(line.getOptionValue("board")), "UTF-8");

    	new CandyCrisis((
                new GameBuilder(reader)).build(),
                getPlayer(line.hasOption("player") ? line.getOptionValue("player").charAt(0) : 'c'))
            .onEnd((event) -> {
                PrintWriter writer = null;
                try {
                    String filename = line.hasOption("output") ? line.getOptionValue("output") : "output.txt";
                    writer = new PrintWriter(new FileWriter(filename));

                    writer.println(event.getSource().replaceAll("\n", "\r\n"));
                    System.out.println(event.getSource());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (writer != null) {
                        writer.close();
                    }
                }

                System.out.print("EXECUTION TIME: " + (System.currentTimeMillis() - start) + "ms");
            })
            .start();
    }

    public static Player getPlayer(char option) {
        switch (option) {
            case 'i':
                return new HumanUIPlayer();
            case 'a':
                return new SuperSolver();
            default:
                return new HumanPlayer();
        }
    }

}
