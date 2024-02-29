package edu.ncsu.csc216.issue_manager.model.issue;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.issue_manager.model.command.Command;



/**
 * The IssueTest class
 * @author Louis Ton
 */
public class IssueTest {

	/**Id of issue */
	private static final int ID = 2;
	
	/**Invalid id of issue, created for testing */
	private static final int INVALID_ID = 0;
	
	/**Summary */
	private static final String SUMMARY = "Issue description";
	
	/**Note string */
	private static final String NOTE = "notes";
	
	/**Owner id */
	private static final String OWNER = "thton";
	

	
	/**
	 * Test first constructor with valid parameter	
	 */
	@Test
	public void testFirstIssueConstructorValid() {
		Issue i = assertDoesNotThrow (
				() -> new Issue(ID, Issue.IssueType.BUG, SUMMARY, NOTE), 
				"Should not throw exception");
		
		assertAll ("Issue",
				() -> assertEquals(SUMMARY, i.getSummary(), "incorrect summary"),
				() -> assertEquals(ID, i.getIssueId(), "incorrect issue id"));
	}

	
	/**
	 * Test first constructor with invalid parameter	
	 */
	@Test
	public void testFirstIssueConstructorInvalid() {
		//Test with invalid id
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Issue(INVALID_ID, Issue.IssueType.BUG, SUMMARY, NOTE));
		assertEquals("Issue cannot be created.", e1.getMessage(), "Incorrect exception thrown with invalid issue id " + INVALID_ID);
		
		//Test with null summary
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Issue(ID, Issue.IssueType.BUG, null, NOTE));
		assertEquals("Issue cannot be created.", e2.getMessage(), "Incorrect exception thrown with null summary " + null);
		
		//Test with empty summary
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> new Issue(ID, Issue.IssueType.BUG, "", NOTE));
		assertEquals("Issue cannot be created.", e3.getMessage(), "Incorrect exception thrown with empty summary " + "");

		//Test with null issue type
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> new Issue(ID, null, SUMMARY, NOTE));
		assertEquals("Issue cannot be created.", e4.getMessage(), "Incorrect exception thrown with null issue type " + null);
		
	}
	
	
	/**
	 * Test second constructor with valid parameter
	 */
	@Test
	public void testSecondIssueConstructorValid() {
		ArrayList<String> notes = new ArrayList<String>();
		Issue i = assertDoesNotThrow (
				() -> new Issue(ID, Issue.NEW_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_FIXED, notes), 
				"Should not throw exception");
		
		assertAll ("Issue",
				() -> assertEquals(SUMMARY, i.getSummary(), "incorrect summary"),
				() -> assertEquals(ID, i.getIssueId(), "incorrect issue id"),
				() -> assertEquals(Issue.NEW_NAME, i.getStateName(), "incorrect state name"),
				() -> assertEquals(Issue.I_BUG, i.getIssueType(), "incorrect issue type"),
				() -> assertEquals(OWNER, i.getOwner(), "incorrect owner id"),
				() -> assertEquals(true, i.isConfirmed(), "incorrect confirm status"),
				() -> assertEquals(Command.R_FIXED, i.getResolution(), "incorrect issue id"),
				() -> assertEquals(notes, i.getNotes(), "incorrect notes array"));

	}
	
	/**
	 * Test second constructor with invalid parameter	
	 */
	@Test
	public void testSecondIssueConstructorInvalid() {
		ArrayList<String> notes = new ArrayList<String>();
		
		//Test with invalid id
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Issue(INVALID_ID, Issue.NEW_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_FIXED, notes));
		assertEquals("Issue cannot be created.", e1.getMessage(), "Incorrect exception thrown with invalid issue id " + INVALID_ID);
		
		//Test with null state
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Issue(ID, null, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_FIXED, notes));
		assertEquals("Issue cannot be created.", e2.getMessage(), "Incorrect exception thrown with null state " + null);
		
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> new Issue(ID, Issue.NEW_NAME, null, SUMMARY, OWNER, true, Command.R_FIXED, notes));
		assertEquals("Issue cannot be created.", e3.getMessage(), "Incorrect exception thrown with null issue type " + null);
		
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> new Issue(ID, Issue.NEW_NAME, Issue.I_BUG, null, OWNER, true, Command.R_FIXED, notes));
		assertEquals("Issue cannot be created.", e4.getMessage(), "Incorrect exception thrown with null summary " + null);
		
		
		Exception e6 = assertThrows(IllegalArgumentException.class,
				() -> new Issue(ID, Issue.NEW_NAME, Issue.I_BUG, SUMMARY, OWNER, true, null, notes));
		assertEquals("Issue cannot be created.", e6.getMessage(), "Incorrect exception thrown with empty resolution " + null);
		
		Exception e7 = assertThrows(IllegalArgumentException.class,
				() -> new Issue(ID, Issue.NEW_NAME, Issue.I_BUG, SUMMARY, null, true, Command.R_FIXED, null));
		assertEquals("Issue cannot be created.", e7.getMessage(), "Incorrect exception thrown with null notes " + null);
		
		Exception e8 = assertThrows(IllegalArgumentException.class,
				() -> new Issue(ID, "", Issue.I_BUG, SUMMARY, null, true, Command.R_FIXED, null));
		assertEquals("Issue cannot be created.", e8.getMessage(), "Incorrect exception thrown with empty state name " + "");
		
		Exception e9 = assertThrows(IllegalArgumentException.class,
				() -> new Issue(ID, Issue.NEW_NAME, "", SUMMARY, OWNER, true, Command.R_FIXED, notes));
		assertEquals("Issue cannot be created.", e9.getMessage(), "Incorrect exception thrown with empty issue type " + null);
		
		Exception e10 = assertThrows(IllegalArgumentException.class,
				() -> new Issue(ID, Issue.NEW_NAME, Issue.I_BUG, "", null, true, Command.R_FIXED, notes));
		assertEquals("Issue cannot be created.", e10.getMessage(), "Incorrect exception thrown with null notes " + null);
		
		
	}
	
	

	/**
	 * Test method for getIssueId method
	 */
	@Test
	public void testGetIssueId() {
		ArrayList<String> notes = new ArrayList<String>();
		Issue i = new Issue(ID, Issue.NEW_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_FIXED, notes);
		assertEquals(2, i.getIssueId(), "Incorrect issue Id");
	}

	/**
	 * Test method for getStateName method
	 */
	@Test
	public void testGetStateName() {
		ArrayList<String> notes = new ArrayList<String>();
		Issue i = new Issue(ID, Issue.NEW_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_FIXED, notes);
		assertEquals(Issue.NEW_NAME, i.getStateName(), "Incorrect state name");
		
		i = new Issue(ID, Issue.WORKING_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_FIXED, notes);
		assertEquals(Issue.WORKING_NAME, i.getStateName(), "Incorrect state name");
		
		i = new Issue(ID, Issue.CONFIRMED_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_FIXED, notes);
		assertEquals(Issue.CONFIRMED_NAME, i.getStateName(), "Incorrect state name");
		
		i = new Issue(ID, Issue.VERIFYING_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_FIXED, notes);
		assertEquals(Issue.VERIFYING_NAME, i.getStateName(), "Incorrect state name");
		
		i = new Issue(ID, Issue.CLOSED_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_FIXED, notes);
		assertEquals(Issue.CLOSED_NAME, i.getStateName(), "Incorrect state name");
	}

	/**
	 * Test method for getIssueType method
	 */
	@Test
	public void testGetIssueType() {
		ArrayList<String> notes = new ArrayList<String>();
		Issue i = new Issue(ID, Issue.NEW_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_FIXED, notes);
		assertEquals(Issue.I_BUG, i.getIssueType(), "Incorrect issue type");
		
		i = new Issue(ID, Issue.NEW_NAME, Issue.I_ENHANCEMENT, SUMMARY, OWNER, true, Command.R_FIXED, notes);
		assertEquals(Issue.I_ENHANCEMENT, i.getIssueType(), "Incorrect issue type");
	}

	/**
	 * Test method for getResolution method
	 */
	@Test
	public void testGetResolution() {
		ArrayList<String> notes = new ArrayList<String>();
		Issue i = new Issue(ID, Issue.NEW_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_FIXED, notes);
		assertEquals(Command.R_FIXED, i.getResolution(), "Incorrect resolution");
		
		i = new Issue(ID, Issue.NEW_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_WONTFIX, notes);
		assertEquals(Command.R_WONTFIX, i.getResolution(), "Incorrect resolution");
		
		i = new Issue(ID, Issue.NEW_NAME, Issue.I_ENHANCEMENT, SUMMARY, OWNER, true, Command.R_WORKSFORME, notes);
		assertEquals(Command.R_WORKSFORME, i.getResolution(), "Incorrect resolution");
		
		i = new Issue(ID, Issue.NEW_NAME, Issue.I_ENHANCEMENT, SUMMARY, OWNER, true, Command.R_DUPLICATE, notes);
		assertEquals(Command.R_DUPLICATE, i.getResolution(), "Incorrect resolution");
		
	}


	
	/**
	 * Test method for getNotesString method
	 */
	@Test
	public void testGetNotesString() {
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("[New] Note 1");
		notes.add("[Confirmed] Note 2");
		notes.add("[Working] Note 3");
		Issue i = new Issue(ID, Issue.NEW_NAME, Issue.I_ENHANCEMENT, SUMMARY, OWNER, true, Command.R_DUPLICATE, notes);
		assertEquals("- [New] Note 1\n"
					+ "- [Confirmed] Note 2\n"
					+ "- [Working] Note 3", i.getNotesString(), "Incorrect note string");
		
		
		
	}


	/**
	 * Test method for toString method
	 */
	@Test
	public void testToString() {
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("[New] Note 1");
		notes.add("[Confirmed] Note 2");
		notes.add("[Working] Note 3");
		Issue i = new Issue(ID, Issue.NEW_NAME, Issue.I_ENHANCEMENT, SUMMARY, OWNER, true, Command.R_DUPLICATE, notes);
		assertEquals("* 2,New,Enhancement,Issue description,thton,true,Duplicate\n"
					+ "- [New] Note 1\n"
					+ "- [Confirmed] Note 2\n"
					+ "- [Working] Note 3", i.toString(), "incorrect toString");
	}

	/**
	 * Test method for update method
	 */
	@Test
	public void testUpdate() {
		Command c = new Command(Command.CommandValue.ASSIGN, OWNER, Command.Resolution.WONTFIX, NOTE);
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("[New] Note 1");
		notes.add("[Confirmed] Note 2");
		notes.add("[Working] Note 3");
		Issue i = new Issue(ID, Issue.NEW_NAME, Issue.I_ENHANCEMENT, SUMMARY, OWNER, true, Command.R_DUPLICATE, notes);
		i.update(c);
		assertEquals(Issue.WORKING_NAME, i.getStateName(), "Incorrect resolution");
	}

}
