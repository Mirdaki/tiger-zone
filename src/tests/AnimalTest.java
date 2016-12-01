package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.Animal;

public class AnimalTest {

	@Test
	public void animalConstructorAndGetterTest() {
		Animal a = new Animal('P');

		//assertEquals('P', a.getType());
	}

	@Test
	public void animalToStringTest() {
		Animal a = new Animal('P');

		assertEquals("Animal type: P", a.toString());
	}

}
