package app.main;

import java.io.FileReader;

import app.candycrisis.CandyCrisis;
import app.candycrisis.GameBuilder;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	new CandyCrisis((
    			new GameBuilder(new FileReader("")))
			.build()).start();
    }
}
