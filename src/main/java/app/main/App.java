package app.main;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import app.candycrisis.CandyCrisis;
import app.candycrisis.GameBuilder;
import app.candycrisis.utils.Action;
import app.candycrisis.utils.Event;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	new CandyCrisis((
    			new GameBuilder(new FileReader("boards.txt")))
			.build())
            .onEnd(new Action<String>() {
                public void performAction(Event<String> event) {
                    PrintWriter writer = null;
                    try {
                        writer = new PrintWriter(new FileWriter("output.txt"));
                        writer.println(event.getSource());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (writer != null) {
                            writer.close();
                        }
                    }
                }
            })
            .start();
    }
}
