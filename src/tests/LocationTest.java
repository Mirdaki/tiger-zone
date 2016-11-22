package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.Location;

public class LocationTest {

	@Test
	public void locationConstructorAndGetterTest() {
		Location l1 = new Location();
		Location l2 = new Location(2, 3);
		Location l3 = new Location(l2);
		
		assertEquals(0, l1.getRow());
		assertEquals(0, l1.getCol());
		assertEquals(7, l2.getRow());
		assertEquals(8, l2.getCol());
		assertEquals(7, l3.getRow());
		assertEquals(8, l2.getCol());
	}
	
	@Test
	public void locationEqualsTest() {
		Location l1 = new Location(2,3);
		Location l2 = new Location(2,4);
		
		assertFalse(l2.equals(l1));
	}

	@Test
	public void locationToStringTest() {
		Location l1 = new Location(2,3);
		
		assertEquals("(2,3)", l1.toString());
	}
}
