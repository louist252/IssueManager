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
				() -> new Issue(ID, Issue.IssueType.ENHANCEMENT, "", NOTE));
		assertEquals("Issue cannot be created.", e3.getMessage(), "Incorrect exception thrown with empty summary " + "");

		//Test with null issue type
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> new Issue(ID, null, SUMMARY, NOTE));
		assertEquals("Issue cannot be created.", e4.getMessage(), "Incorrect exception thrown with null issue type " + null);
		
		//Test with null note
		Exception e5 = assertThrows(IllegalArgumentException.class,
				() -> new Issue(ID, Issue.IssueType.ENHANCEMENT, SUMMARY, null));
		assertEquals("Issue cannot be created.", e5.getMessage(), "Incorrect exeption thrown wiht null not" + null);
	}
	
	
	/**
	 * Test second constructor with valid parameter
	 */
	@Test
	public void testSecondIssueConstructorValid() {
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("[New] Note 1");
		Issue i1 = assertDoesNotThrow (
				() -> new Issue(ID, Issue.NEW_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_FIXED, notes), 
				"Should not throw exception");
		
		assertAll ("Issue",
				() -> assertEquals(SUMMARY, i1.getSummary(), "incorrect summary"),
				() -> assertEquals(ID, i1.getIssueId(), "incorrect issue id"),
				() -> assertEquals(Issue.NEW_NAME, i1.getStateName(), "incorrect state name"),
				() -> assertEquals(Issue.I_BUG, i1.getIssueType(), "incorrect issue type"),
				() -> assertEquals(OWNER, i1.getOwner(), "incorrect owner id"),
				() -> assertTrue(i1.isConfirmed(), "incorrect confirm status"),
				() -> assertEquals(Command.R_FIXED, i1.getResolution(), "incorrect resolution"),
				() -> assertEquals(notes, i1.getNotes(), "incorrect notes array"));
		
		//Empty owner
		Issue i2 = assertDoesNotThrow (
				() -> new Issue(ID, Issue.WORKING_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_WONTFIX, notes), 
				"Should not throw exception");
		
		assertAll ("Issue",
				() -> assertEquals(SUMMARY, i2.getSummary(), "incorrect summary"),
				() -> assertEquals(ID, i2.getIssueId(), "incorrect issue id"),
				() -> assertEquals(Issue.WORKING_NAME, i2.getStateName(), "incorrect state name"),
				() -> assertEquals(Issue.I_BUG, i2.getIssueType(), "incorrect issue type"),
				() -> assertEquals(OWNER, i2.getOwner(), "incorrect owner id"),
				() -> assertTrue(i2.isConfirmed(), "incorrect confirm status"),
				() -> assertEquals(Command.R_WONTFIX, i2.getResolution(), "Incorrect resolution"),
				() -> assertEquals(notes, i2.getNotes(), "incorrect notes array"));
		
		//Null owner
		Issue i3 = assertDoesNotThrow (
				() -> new Issue(ID, Issue.CLOSED_NAME, Issue.I_BUG, SUMMARY, null, true, Command.R_DUPLICATE, notes), 
				"Should not throw exception");
		
		assertAll ("Issue",
				() -> assertEquals(SUMMARY, i3.getSummary(), "incorrect summary"),
				() -> assertEquals(ID, i3.getIssueId(), "incorrect issue id"),
				() -> assertEquals(Issue.CLOSED_NAME, i3.getStateName(), "incorrect state name"),
				() -> assertEquals(Issue.I_BUG, i3.getIssueType(), "incorrect issue type"),
				() -> assertEquals("null", i3.getOwner(), "incorrect owner id"),
				() -> assertTrue(i3.isConfirmed(), "incorrect confirm status"),
				() -> assertEquals(Command.R_DUPLICATE, i3.getResolution(), "Incorrect resolution"),
				() -> assertEquals(notes, i3.getNotes(), "incorrect notes array"));
		
		//Empty resolution
		Issue i4 = assertDoesNotThrow (
				() -> new Issue(ID, Issue.NEW_NAME, Issue.I_ENHANCEMENT, SUMMARY, OWNER, false, "", notes), 
				"Should not throw exception");
		
		assertAll ("Issue",
				() -> assertEquals(SUMMARY, i4.getSummary(), "incorrect summary"),
				() -> assertEquals(ID, i4.getIssueId(), "incorrect issue id"),
				() -> assertEquals(Issue.NEW_NAME, i4.getStateName(), "incorrect state name"),
				() -> assertEquals(Issue.I_ENHANCEMENT, i4.getIssueType(), "incorrect issue type"),
				() -> assertEquals(OWNER, i4.getOwner(), "incorrect owner id"),
				() -> assertFalse(i4.isConfirmed(), "incorrect confirm status"),
				() -> assertEquals("", i4.getResolution(), "Incorrect resolution"),
				() -> assertEquals(notes, i4.getNotes(), "incorrect notes array"));
		
		


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
		
	
		Exception e7 = assertThrows(IllegalArgumentException.class,
				() -> new Issue(ID, Issue.NEW_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_FIXED, null));
		assertEquals("Issue cannot be created.", e7.getMessage(), "Incorrect exception thrown with null notes " + null);
		
		Exception e8 = assertThrows(IllegalArgumentException.class,
				() -> new Issue(ID, "", Issue.I_BUG, SUMMARY, null, true, Command.R_FIXED, notes));
		assertEquals("Issue cannot be created.", e8.getMessage(), "Incorrect exception thrown with empty state name " + "");
		
		Exception e9 = assertThrows(IllegalArgumentException.class,
				() -> new Issue(ID, Issue.NEW_NAME, "", SUMMARY, OWNER, true, Command.R_FIXED, notes));
		assertEquals("Issue cannot be created.", e9.getMessage(), "Incorrect exception thrown with empty issue type " + "");
		
		Exception e10 = assertThrows(IllegalArgumentException.class,
				() -> new Issue(ID, Issue.NEW_NAME, Issue.I_BUG, "", null, true, Command.R_FIXED, notes));
		assertEquals("Issue cannot be created.", e10.getMessage(), "Incorrect exception thrown with null summary " + null);
		
		Exception e11 = assertThrows(IllegalArgumentException.class,
				() -> new Issue(ID, Issue.NEW_NAME, "Weird Type", "", null, true, Command.R_FIXED, notes));
		assertEquals("Issue cannot be created.", e11.getMessage(), "Incorrect exception thrown with a different issue type " + "Weird Type");
		
		
		Exception e12 = assertThrows(IllegalArgumentException.class,
				() -> new Issue(ID, "Weird State", Issue.I_BUG, "", "", true, Command.R_FIXED, notes));
		assertEquals("Issue cannot be created.", e12.getMessage(), "Incorrect exception thrown with a different issue type " + "Weird state");
		
		
		
		
	}
	
	

	/**
	 * Test method for getIssueId method
	 */
	@Test
	public void testGetIssueId() {
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("[New] Note 1");
		Issue i = new Issue(ID, Issue.NEW_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_FIXED, notes);
		assertEquals(2, i.getIssueId(), "Incorrect issue Id");
	}

	/**
	 * Test method for getStateName method
	 */
	@Test
	public void testGetStateName() {
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("[New] Note 1");
		Issue i = new Issue(ID, Issue.NEW_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_FIXED, notes);
		assertEquals(Issue.NEW_NAME, i.getStateName(), "Incorrect state name");
		
		i = new Issue(ID, Issue.WORKING_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_FIXED, notes);
		assertEquals(Issue.WORKING_NAME, i.getStateName(), "Incorrect state name");
		
		i = new Issue(ID, Issue.CONFIRMED_NAME, Issue.I_BUG, SUMMARY, OWNER, true, "", notes);
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
		notes.add("[New] Note 1");
		Issue i = new Issue(ID, Issue.NEW_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_FIXED, notes);
		assertEquals(Issue.I_BUG, i.getIssueType(), "Incorrect issue type");
		
		i = new Issue(ID, Issue.NEW_NAME, Issue.I_ENHANCEMENT, SUMMARY, OWNER, false, Command.R_FIXED, notes);
		assertEquals(Issue.I_ENHANCEMENT, i.getIssueType(), "Incorrect issue type");
	}

	/**
	 * Test method for getResolution method
	 */
	@Test
	public void testGetResolution() {
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("[New] Note 1");
		Issue i = new Issue(ID, Issue.NEW_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_FIXED, notes);
		assertEquals(Command.R_FIXED, i.getResolution(), "Incorrect resolution");
		
		i = new Issue(ID, Issue.NEW_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_WONTFIX, notes);
		assertEquals(Command.R_WONTFIX, i.getResolution(), "Incorrect resolution");
		
		i = new Issue(ID, Issue.NEW_NAME, Issue.I_BUG, SUMMARY, OWNER, false, Command.R_WORKSFORME, notes);
		assertEquals(Command.R_WORKSFORME, i.getResolution(), "Incorrect resolution");
		
		i = new Issue(ID, Issue.NEW_NAME, Issue.I_ENHANCEMENT, SUMMARY, OWNER, false, Command.R_DUPLICATE, notes);
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
		Issue i = new Issue(ID, Issue.NEW_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_DUPLICATE, notes);
		assertEquals("-[New] Note 1\r\n"
					+ "-[Confirmed] Note 2\r\n"
					+ "-[Working] Note 3", i.getNotesString(), "Incorrect note string");
		
		
		
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
		Issue i = new Issue(ID, Issue.NEW_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_DUPLICATE, notes);
		assertEquals("*2,New,Bug,Issue description,thton,true,Duplicate\r\n"
					+ "-[New] Note 1\r\n"
					+ "-[Confirmed] Note 2\r\n"
					+ "-[Working] Note 3", i.toString(), "incorrect toString");
	}

	/**
	 * Test update method in NewState
	 */
	@Test
	public void testUpdateCallToNew() {
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("[New] Note 1");
		notes.add("[Confirmed] Note 2");
		
		
		//Test when CommandValue is Assign when IssueType is Enhancement
		Issue i1 = new Issue(ID, Issue.NEW_NAME, Issue.I_ENHANCEMENT, SUMMARY, "", false, Command.R_DUPLICATE, notes);
		Command c1 = new Command(Command.CommandValue.ASSIGN, OWNER, Command.Resolution.WONTFIX, NOTE);
		i1.update(c1);
		assertEquals(Issue.WORKING_NAME, i1.getStateName(), "Incorrect resolution");
		assertEquals(OWNER, i1.getOwner(), "Incorrect resolution");
		assertEquals("-[New] Note 1\r\n"
				+ "-[Confirmed] Note 2\r\n"
				+ "-[Working] notes", i1.getNotesString(), "Incorrect notes");
		
		
		//Remove the last note in array for testing with next command
		notes.remove(notes.size() - 1);
		//Test when CommandValue is Resolve when IssueType is Enhancement
		Issue i2 = new Issue(ID, Issue.NEW_NAME, Issue.I_ENHANCEMENT, SUMMARY, "", false, Command.R_DUPLICATE, notes);
		Command c2 = new Command(Command.CommandValue.RESOLVE, OWNER, Command.Resolution.WONTFIX, NOTE);
		i2.update(c2);
		assertEquals(Issue.CLOSED_NAME, i2.getStateName(), "Incorrect state");
		assertEquals(Command.R_WONTFIX, i2.getResolution(), "Incorrect resolution");
		assertEquals("-[New] Note 1\r\n"
					+ "-[Confirmed] Note 2\r\n"
					+ "-[Closed] notes", i2.getNotesString(), "Incorrect notes");
		
		
		//Test when CommandValue is not Resolve nor Assign when IssueType is Enhancement
		Issue i3 = new Issue(ID, Issue.NEW_NAME, Issue.I_ENHANCEMENT, SUMMARY, "", false, Command.R_DUPLICATE, notes);
		Command c3 = new Command(Command.CommandValue.VERIFY, OWNER, Command.Resolution.WONTFIX, NOTE);
		Exception e1 = assertThrows(UnsupportedOperationException.class, () -> i3.update(c3));
		assertEquals("Invalid information.", e1.getMessage(), "Incorrect exception thrown with invalid CommandValue");
		
		
		//Remove the last note in array for testing with next command
		notes.remove(notes.size() - 1);
		//Test when CommandValue is Confirmed when IssueType is Bug
		Issue i4 = new Issue(ID, Issue.IssueType.BUG, SUMMARY, NOTE);
		Command c4 = new Command(Command.CommandValue.CONFIRM, OWNER, Command.Resolution.WONTFIX, NOTE);
		i4.update(c4);
		assertEquals(Issue.CONFIRMED_NAME, i4.getStateName(), "Incorrec state name");
		assertTrue(i4.isConfirmed(), "Incorrect confirmed status");
		assertEquals("-[New] Note 1\r\n"
				+ "-[Confirmed] Note 2", i2.getNotesString(), "Incorrect notes");
		
		
		//Remove the last note in array for testing with next command
		notes.remove(notes.size() - 1);
		//Test when CommandValue is Resolve when IssueType is Bug
		Issue i5 = new Issue(ID, Issue.NEW_NAME, Issue.I_BUG, SUMMARY, "", true, Command.R_DUPLICATE, notes);
		Command c5 = new Command(Command.CommandValue.RESOLVE, OWNER, Command.Resolution.FIXED, NOTE);
		i5.update(c5);
		assertEquals(Issue.CLOSED_NAME, i5.getStateName(), "Incorrect state");
		assertEquals(Command.R_FIXED, i5.getResolution(), "Incorrect resolution");
		assertEquals("-[New] Note 1\r\n"
					+ "-[Closed] notes", i5.getNotesString(), "Incorrect notes");
		
		//Test when CommandValue is not Resolve nor Assing when IssueType is Bug
		Issue i6 = new Issue(ID, Issue.NEW_NAME, Issue.I_BUG, SUMMARY, "", true, Command.R_DUPLICATE, notes);
		Command c6 = new Command(Command.CommandValue.REOPEN, OWNER, Command.Resolution.WONTFIX, NOTE);
		Exception e2 = assertThrows(UnsupportedOperationException.class, () -> i6.update(c6));
		assertEquals("Invalid information.", e2.getMessage(), "Incorrect exception thrown with invalid CommandValue");
	
		
	}
		
		/**
		 * Test update method in WorkingState
		 */
		@Test
		public void testUpdateCallToWorking() {
			//Create a note ArrayLits for issue construction
			ArrayList<String> notes = new ArrayList<String>();
			notes.add("[New] Note 1");
			notes.add("[Confirmed] Note 2");
		
			//Test with a command with Duplicate resolution and Resolve CommandValue
			Issue i1 = new Issue(ID, Issue.WORKING_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_DUPLICATE, notes);
			Command dupeRes = new Command (Command.CommandValue.RESOLVE, OWNER, Command.Resolution.DUPLICATE, NOTE);
			i1.update(dupeRes);
			assertEquals(Issue.CLOSED_NAME, i1.getStateName(), "Incorrect resolution");
			assertEquals("-[New] Note 1\r\n"
						+ "-[Confirmed] Note 2\r\n"
						+ "-[Closed] notes", i1.getNotesString(), "Incorrect notes");
			
			notes.remove(notes.size() - 1);
			
			//Test with a command with WontFix resolution and Resolve CommandValue
			Issue i2 = new Issue(ID, Issue.WORKING_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_DUPLICATE, notes);
			Command wontFixRes = new Command (Command.CommandValue.RESOLVE, OWNER, Command.Resolution.WONTFIX, NOTE);
			i2.update(wontFixRes);
			assertEquals(Issue.CLOSED_NAME, i2.getStateName(), "Incorrect resolution");
			assertEquals("-[New] Note 1\r\n"
						+ "-[Confirmed] Note 2\r\n"
						+ "-[Closed] notes", i2.getNotesString(), "Incorrect notes");
			
			notes.remove(notes.size() - 1);
			
			//Test with a command with Fixed resolution and Resolve CommandValue
			Issue i3 = new Issue(ID, Issue.WORKING_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_DUPLICATE, notes);
			Command fixedRes = new Command (Command.CommandValue.RESOLVE, OWNER, Command.Resolution.FIXED, NOTE);
			i3.update(fixedRes);
			assertEquals(Issue.VERIFYING_NAME, i3.getStateName(), "Incorrect state");
			assertEquals(Command.R_FIXED, i3.getResolution(), "Incorrect resolution");
			assertEquals("-[New] Note 1\r\n"
						+ "-[Confirmed] Note 2\r\n"
						+ "-[Verifying] notes", i3.getNotesString(), "Incorrect notes");
			
			notes.remove(notes.size() - 1);
			
			//Test with a command with WorksForMe resolution and Resolve CommandValue, with
			//the IssueType Bug
			Issue i4 = new Issue(ID, Issue.WORKING_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_DUPLICATE, notes);
			Command worksForMeRes = new Command (Command.CommandValue.RESOLVE, OWNER, Command.Resolution.WORKSFORME, NOTE);
			i4.update(worksForMeRes);
			assertEquals(Issue.CLOSED_NAME, i4.getStateName(), "Incorrect resolution");
			assertEquals("-[New] Note 1\r\n"
						+ "-[Confirmed] Note 2\r\n"
						+ "-[Closed] notes", i4.getNotesString(), "Incorrect notes");
			
			
			//Test update with incorrect conditions (CommandValue is not confirm, 
			//IssueType is Enhancement for resolution WorksForMe)
			
			//Test a command with CommandValue Confirm and Duplicate resolution 
			Issue i5 = new Issue(ID, Issue.WORKING_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_DUPLICATE, notes);
			Command confirmVal = new Command (Command.CommandValue.CONFIRM, OWNER, Command.Resolution.DUPLICATE, NOTE);
			Exception e1 = assertThrows(UnsupportedOperationException.class, () -> i5.update(confirmVal));
			assertEquals("Invalid information.", e1.getMessage(), "Incorrect exception thrown with invalid CommandValue");
			
			//Test a command with CommandValue Assign and WontFix resolution
			Issue i6 = new Issue(ID, Issue.WORKING_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_DUPLICATE, notes);
			Command assignVal = new Command (Command.CommandValue.ASSIGN, OWNER, Command.Resolution.WONTFIX, NOTE);
			Exception e2 = assertThrows(UnsupportedOperationException.class, () -> i6.update(assignVal));
			assertEquals("Invalid information.", e2.getMessage(), "Incorrect exception thrown with invalid CommandValue");
			
			//Test a command with CommandValue Assign and Fixed resolution
			Issue i7 = new Issue(ID, Issue.WORKING_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_DUPLICATE, notes);
			Command verifyVal = new Command (Command.CommandValue.VERIFY, OWNER, Command.Resolution.FIXED, NOTE);
			Exception e3 = assertThrows(UnsupportedOperationException.class, () -> i7.update(verifyVal));
			assertEquals("Invalid information.", e3.getMessage(), "Incorrect exception thrown with invalid CommandValue");
			
			//Test a command with CommandValue Reopen and WorksForMe resolution
			Issue i8 = new Issue(ID, Issue.WORKING_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_DUPLICATE, notes);
			Command reopenVal = new Command (Command.CommandValue.REOPEN, OWNER, Command.Resolution.WORKSFORME, NOTE);
			Exception e4 = assertThrows(UnsupportedOperationException.class, () -> i8.update(reopenVal));
			assertEquals("Invalid information.", e4.getMessage(), "Incorrect exception thrown with invalid CommandValue");
			
			
			
			
	}
		
		/**
		 * Test update method in ConfirmeState
		 */
		@Test
		public void testUpdateCallToConfirmed() {
			//Set up note ArrayList for testing
			ArrayList<String> notes = new ArrayList<String>();
			notes.add("[New] Note 1");
			notes.add("[Confirmed] Note 2");
			
			//Test a command with Assign CommandValue
			Issue i1 = new Issue(ID, Issue.CONFIRMED_NAME, Issue.I_BUG, SUMMARY, "", true, "", notes);
			Command assignVal = new Command (Command.CommandValue.ASSIGN, OWNER, Command.Resolution.WONTFIX, NOTE);
			i1.update(assignVal);
			assertEquals(OWNER, i1.getOwner(), "Incorrect owner");
			assertTrue(i1.isConfirmed(), "Incorrect confirmed status");
			assertEquals(Issue.WORKING_NAME, i1.getStateName(), "Incorrect state");
			assertEquals("-[New] Note 1\r\n"
					+ "-[Confirmed] Note 2\r\n"
					+ "-[Working] notes", i1.getNotesString(), "Incorrect notes");
			
			notes.remove(notes.size() - 1);
			
			//Test a command with Resolve CommandValue and WontFix Resolution
			Issue i2 = new Issue(ID, Issue.CONFIRMED_NAME, Issue.I_BUG, SUMMARY, OWNER, true, "", notes);
			Command resolveVal = new Command (Command.CommandValue.RESOLVE, OWNER, Command.Resolution.WONTFIX, NOTE);
			i2.update(resolveVal);
			assertEquals(Issue.CLOSED_NAME, i2.getStateName(), "Incorret state");
			assertTrue(i2.isConfirmed(), "Incorrect confirmed status");
			assertEquals("-[New] Note 1\r\n"
					+ "-[Confirmed] Note 2\r\n"
					+ "-[Closed] notes", i2.getNotesString(), "Incorrect notes");
			
			//Test when CommandValue is Resolve and Resolution is not WontFix
			Issue i3 = new Issue(ID, Issue.CONFIRMED_NAME, Issue.I_BUG, SUMMARY, OWNER, true, "", notes);
			Command diffRes = new Command (Command.CommandValue.RESOLVE, OWNER, Command.Resolution.DUPLICATE, NOTE);
			Exception e3 = assertThrows(UnsupportedOperationException.class, () -> i3.update(diffRes));
			assertEquals("Invalid information.", e3.getMessage(), "Incorrect exception thrown with invalid CommandValue");
			
			//Test when CommandValue is is not Resolve nor Assign
			Issue i4 = new Issue(ID, Issue.CONFIRMED_NAME, Issue.I_BUG, SUMMARY, OWNER, true, "", notes);
			Command diffCommVal = new Command (Command.CommandValue.REOPEN, OWNER, Command.Resolution.DUPLICATE, NOTE);
			Exception e4 = assertThrows(UnsupportedOperationException.class, () -> i4.update(diffCommVal));
			assertEquals("Invalid information.", e4.getMessage(), "Incorrect exception thrown with invalid CommandValue");
		}
		
		/**
		 * Test update method in VerifyingState
		 */
		@Test
		public void testUpdateCallToVerifying() {
			//Set up note ArrayList for testing
			ArrayList<String> notes = new ArrayList<String>();
			notes.add("[New] Note 1");
			notes.add("[Confirmed] Note 2");
			
			//Issue with Fixed resolution and a comamnd with Verify CommandValue
			Issue i1 = new Issue(ID, Issue.VERIFYING_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_FIXED, notes);
			Command c1 = new Command (Command.CommandValue.VERIFY, OWNER, Command.Resolution.FIXED, NOTE);
			i1.update(c1);
			assertEquals(Issue.CLOSED_NAME, i1.getStateName(), "Incorrect state");
			assertEquals("-[New] Note 1\r\n"
					+ "-[Confirmed] Note 2\r\n"
					+ "-[Closed] notes", i1.getNotesString(), "Incorrect notes");
			
			notes.remove(notes.size() - 1);
			
			//CommandValue is Reopen
			Issue i2 = new Issue(ID, Issue.VERIFYING_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_FIXED, notes);
			Command c2 = new Command (Command.CommandValue.REOPEN, OWNER, Command.Resolution.FIXED, NOTE);
			i2.update(c2);
			assertEquals("-[New] Note 1\r\n"
					+ "-[Confirmed] Note 2\r\n"
					+ "-[Working] notes", i1.getNotesString(), "Incorrect notes");
			
			//Test if the Resolution is not Fixed
			Issue i3 = new Issue(ID, Issue.VERIFYING_NAME, Issue.I_BUG, SUMMARY, OWNER, true, Command.R_FIXED, notes);
			Command c3 = new Command (Command.CommandValue.VERIFY, OWNER, Command.Resolution.DUPLICATE, NOTE);
			Exception e1 = assertThrows(UnsupportedOperationException.class, () -> i3.update(c3));
			assertEquals("Invalid information.", e1.getMessage(), "Incorrect exception thrown with invalid CommandValue");
			
			//Test if the CommandValue is not Reopen nor Verify
			Issue i4 = new Issue(ID, Issue.VERIFYING_NAME, Issue.I_BUG, SUMMARY, OWNER, false, Command.R_FIXED, notes);
			Command c4 = new Command (Command.CommandValue.ASSIGN, OWNER, Command.Resolution.FIXED, NOTE);
			Exception e2 = assertThrows(UnsupportedOperationException.class, () -> i4.update(c4));
			assertEquals("Invalid information.", e2.getMessage(), "Incorrect exception thrown with invalid CommandValue");
		}
		

		/**
		 * Test update method in VerifyingState
		 */
		@Test
		public void testUpdateCallToClosed() {
			ArrayList<String> notes = new ArrayList<String>();
			notes.add("[New] Note 1");
			notes.add("[Confirmed] Note 2");
			
			//Test when command has Reopen CommandValue, issue has type Bug, and has an owner
			Issue i1 = new Issue(ID, Issue.CLOSED_NAME, Issue.I_BUG, SUMMARY, "Owner name", true, Command.R_FIXED, notes);
			Command c1 = new Command (Command.CommandValue.REOPEN, OWNER, Command.Resolution.FIXED, NOTE);
			i1.update(c1);
			assertEquals(Issue.WORKING_NAME, i1.getStateName(), "Incorrect state");
			assertEquals("Owner name", i1.getOwner(), "Incorrect state");
			assertEquals("-[New] Note 1\r\n"
					+ "-[Confirmed] Note 2\r\n"
					+ "-[Working] notes", i1.getNotesString(), "Incorrect notes");
			
			notes.remove(notes.size() - 1);
			
			//Test when command has Reopen CommandValue, issue has type Bug, and without an owner
			Issue i2 = new Issue(ID, Issue.CLOSED_NAME, Issue.I_BUG, SUMMARY, "", true, Command.R_FIXED, notes);
			Command c2 = new Command (Command.CommandValue.REOPEN, OWNER, Command.Resolution.FIXED, NOTE);
			i2.update(c2);
			assertEquals(Issue.CONFIRMED_NAME, i2.getStateName(), "Incorrect state");
			assertEquals(OWNER, i2.getOwner(), "Incorrect state");
			assertEquals("-[New] Note 1\r\n"
					+ "-[Confirmed] Note 2\r\n"
					+ "-[Confirmed] notes", i2.getNotesString(), "Incorrect notes");
			
			notes.remove(notes.size() - 1);
			
			//Test when command has Reopen CommandValue, issue has type Bug, is confirmed, and has an owner
			Issue i3 = new Issue(ID, Issue.CLOSED_NAME, Issue.I_BUG, SUMMARY, "Owner name", false, Command.R_FIXED, notes);
			Command c3 = new Command (Command.CommandValue.REOPEN, OWNER, Command.Resolution.FIXED, NOTE);
			i3.update(c3);
			assertEquals(Issue.NEW_NAME, i3.getStateName(), "Incorrect state");
			assertEquals("-[New] Note 1\r\n"
					+ "-[Confirmed] Note 2\r\n"
					+ "-[New] notes", i3.getNotesString(), "Incorrect notes");
			
			notes.remove(notes.size() - 1);
			
			//Test when command has Reopen CommandValue, issue has type Bug, is confirmed, and has no owner
			Issue i4 = new Issue(ID, Issue.CLOSED_NAME, Issue.I_ENHANCEMENT, SUMMARY, "", false, Command.R_FIXED, notes);
			Command c4 = new Command (Command.CommandValue.REOPEN, OWNER, Command.Resolution.FIXED, NOTE);
			i4.update(c4);
			assertEquals(Issue.NEW_NAME, i4.getStateName(), "Incorrect state");
			assertEquals(OWNER, i4.getOwner(), "Incorrect state");
			assertEquals("-[New] Note 1\r\n"
					+ "-[Confirmed] Note 2\r\n"
					+ "-[New] notes", i4.getNotesString(), "Incorrect notes");
			
			notes.remove(notes.size() - 1);
			
			//Test when command has Reopen CommandValue, issue has type Bug, and is not confirmed, and has no owner
			Issue i5 = new Issue(ID, Issue.CLOSED_NAME, Issue.I_ENHANCEMENT, SUMMARY, OWNER, false, Command.R_FIXED, notes);
			Command c5 = new Command (Command.CommandValue.REOPEN, OWNER, Command.Resolution.FIXED, NOTE);
			i5.update(c5);
			assertEquals(Issue.WORKING_NAME, i5.getStateName(), "Incorrect state");
			assertEquals(OWNER, i5.getOwner(), "Incorrect state");
			assertEquals("-[New] Note 1\r\n"
					+ "-[Confirmed] Note 2\r\n"
					+ "-[Working] notes", i5.getNotesString(), "Incorrect notes");
			
			//Test when command has CommandValue that is not Reopen
			Issue i6 = new Issue(ID, Issue.CLOSED_NAME, Issue.I_BUG, SUMMARY, "", false, Command.R_FIXED, notes);
			Command c6 = new Command (Command.CommandValue.RESOLVE, OWNER, Command.Resolution.FIXED, NOTE);
			Exception e1 = assertThrows(UnsupportedOperationException.class, () -> i6.update(c6));
			assertEquals("Invalid information.", e1.getMessage(), "Incorrect exception thrown with incorrect CommandValue");
			
			
			
			
		}
}
