package edu.ncsu.csc216.issue_manager.model.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



/**
 * The CommandTest class
 * @author Louis Ton
 */
public class CommandTest {
	/** Owner id */
	private static final String ID = "thton";
	
	/**Note */
	private static final String NOTE = "notes";
	
	/**
	 * Test method for Command constructor with correct parameters
	 */
	@Test
	public void testCommandValid() {
		Command c1 = assertDoesNotThrow (
				() -> new Command(Command.CommandValue.ASSIGN, ID, Command.Resolution.FIXED, NOTE), 
				"Should not throw exception");
	
		assertAll ("Command",
				() -> assertEquals(Command.CommandValue.ASSIGN, c1.getCommand(), "incorrect command value"),
				() -> assertEquals(ID, c1.getOwnerId(), "incorrect owner id"),
				() -> assertEquals(Command.Resolution.FIXED, c1.getResolution(), "incorrect resolution"),
				() -> assertEquals(NOTE, c1.getNote(), "incorrect note"));
		
		//Test with Confirm command value and Duplicate Resolution
		Command c2 = assertDoesNotThrow (
				() -> new Command(Command.CommandValue.CONFIRM, ID, Command.Resolution.DUPLICATE, NOTE), 
				"Should not throw exception");
		
		assertAll ("Command",
				() -> assertEquals(Command.CommandValue.CONFIRM, c2.getCommand(), "incorrect command value"),
				() -> assertEquals(ID, c2.getOwnerId(), "incorrect owner id"),
				() -> assertEquals(Command.Resolution.DUPLICATE, c2.getResolution(), "incorrect resolution"),
				() -> assertEquals(NOTE, c2.getNote(), "incorrect note"));
		
		//Test with Verify CommandValue and WONTFIX Resolution
		Command c3 = assertDoesNotThrow (
				() -> new Command(Command.CommandValue.VERIFY, ID, Command.Resolution.WONTFIX, NOTE), 
				"Should not throw exception");
		
		assertAll ("Command",
				() -> assertEquals(Command.CommandValue.VERIFY, c3.getCommand(), "incorrect command value"),
				() -> assertEquals(ID, c3.getOwnerId(), "incorrect owner id"),
				() -> assertEquals(Command.Resolution.WONTFIX, c3.getResolution(), "incorrect resolution"),
				() -> assertEquals(NOTE, c3.getNote(), "incorrect note"));
		
		//Test with Resolve CommandValue and WORKSFORME Resolution
		Command c4 = assertDoesNotThrow (
				() -> new Command(Command.CommandValue.RESOLVE, ID, Command.Resolution.WORKSFORME, NOTE), 
				"Should not throw exception");
		
		assertAll ("Command",
				() -> assertEquals(Command.CommandValue.RESOLVE, c4.getCommand(), "incorrect command value"),
				() -> assertEquals(ID, c4.getOwnerId(), "incorrect owner id"),
				() -> assertEquals(Command.Resolution.WORKSFORME, c4.getResolution(), "incorrect resolution"),
				() -> assertEquals(NOTE, c4.getNote(), "incorrect note"));
		
		//Test with Reopen CommandValue and WORKSFORME Resolution
		Command c5 = assertDoesNotThrow (
				() -> new Command(Command.CommandValue.REOPEN, ID, Command.Resolution.WORKSFORME, NOTE), 
				"Should not throw exception");
		
		assertAll ("Command",
				() -> assertEquals(Command.CommandValue.REOPEN, c5.getCommand(), "incorrect command value"),
				() -> assertEquals(ID, c5.getOwnerId(), "incorrect owner id"),
				() -> assertEquals(Command.Resolution.WORKSFORME, c5.getResolution(), "incorrect resolution"),
				() -> assertEquals(NOTE, c5.getNote(), "incorrect note"));

		
	}
	
	/**
	 * Test method for Command constructor with incorrect parameters
	 */
	@Test
	public void testCommandInvalid() {
		//Test for null command value
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Command(null, ID, Command.Resolution.FIXED, NOTE));
		assertEquals("Invalid information.", e1.getMessage(), "Incorrect exception thrown with invalid command vallue " + null);
		
		//Test for null owner id
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Command(Command.CommandValue.ASSIGN, null, Command.Resolution.FIXED, NOTE));
		assertEquals("Invalid information.", e2.getMessage(), "Incorrect exception thrown with null owner Id " + null);
		
		//Test for Resolve command value with null resolution
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> new Command(Command.CommandValue.RESOLVE, ID, null, NOTE));
		assertEquals("Invalid information.", e3.getMessage(), "Incorrect exception thrown for "
				+ "Resolve command value with null resolution " + null);
		
		//Test for null note
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> new Command(Command.CommandValue.ASSIGN, ID, Command.Resolution.FIXED, null));
		assertEquals("Invalid information.", e4.getMessage(), "Incorrect exception thrown with null note " + null);
		
		//Test for empty owner id
		Exception e5 = assertThrows(IllegalArgumentException.class,
				() -> new Command(Command.CommandValue.ASSIGN, "", Command.Resolution.FIXED, NOTE));
		assertEquals("Invalid information.", e5.getMessage(), "Incorrect exception thrown with empty owner Id " + "");
		
		//Test for null note
		Exception e6 = assertThrows(IllegalArgumentException.class,
				() -> new Command(Command.CommandValue.ASSIGN, ID, Command.Resolution.FIXED, ""));
		assertEquals("Invalid information.", e6.getMessage(), "Incorrect exception thrown with empty note " + "");
	
	} 

}

	