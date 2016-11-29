package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.Location;

public class LocationTest {

	@Test
	public void locationConstructorAndGetterTest() {
		Location l1 = new Location();
		Location l2 = new Location(2, 3);

		assertEquals(5, l1.getX());
		assertEquals(5, l1.getY());
		assertEquals(7, l2.getX());
		assertEquals(2, l2.getY());
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
