package edu.nyu.pqs.addressbook;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class NameTest {
	private Name name;
	
	@Before
	public void setUp() {
		name = new Name("Prachi", "Ramdas", "Kurkute");
	}
	
	@Test
	public void testGetFirstName() {
		assertEquals("Prachi", name.getFirstName());
	}
	
}
