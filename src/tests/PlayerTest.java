package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import entities.Player;

public class PlayerTest {

	@Test
	public void playerConstructorAndGetterTest() 
	{
		Player p1 = new Player(1);
		
		assertEquals(1, p1.getID());
		assertEquals(7, p1.getTiger());
		assertEquals(0, p1.getScore());
	}

	@Test
	public void playerSetAndAddScoreTest()
	{
		Player p2 = new Player(2);
		
		p2.setScore(300);
		p2.addScore(100);
		assertEquals(400, p2.getScore());
	}
	
	@Test
	public void playersFirstAndAITest()
	{
		Player p1 = new Player(1);
		
		p1.setAI(true);
		p1.setFirst(false);
		assertEquals(true, p1.isAI());
		assertEquals(false, p1.isFirst());
	}
	
	@Test
	public void playersToStringTest()
	{
		Player p1 = new Player(1);
		
		p1.setScore(200);
		p1.setFirst(true);
		p1.setAI(false);
		assertEquals("Player ID: 1\nScore: 200\nTiger: 7\nThis is the first player.\nThis is not an AI player.", p1.toString());
	}
}
