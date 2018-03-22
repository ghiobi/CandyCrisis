package app.candycrisis;

import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class GameLoggerTest {

	@Test
	public void testTimeLogging() {
		GameLogger logger = new GameLogger();
		logger.start();

		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertTrue(logger.end() >= 50);
	}

	@Test
	public void testToString() {
		GameLogger logger = new GameLogger();
		logger.start();

		logger.recordMove(new Piece(0, 'r').getId());
		logger.recordMove(new Piece(2, 'e').getId());
		logger.recordMove(new Piece(4, 'b').getId());

		logger.end();

		String log = logger.toString();

		assertEquals(log.substring(0, log.indexOf('\n')), "ACE");
		//assertTrue(Pattern.matches("\\d+ms.+", log.substring(log.indexOf('\n') + 1, log.lastIndexOf('\n'))));
	}

}
