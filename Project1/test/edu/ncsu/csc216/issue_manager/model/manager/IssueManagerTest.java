/**
 * 
 */
package edu.ncsu.csc216.issue_manager.model.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.issue_manager.model.issue.Issue;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

/**
 * The IssueManagerTets
 * @author Louis Ton
 */
class IssueManagerTest {

	/**Summary */
	private static final String SUMMARY = "Issue description";
	
	/**Note */
	private static final String NOTE = "note";
	
	
	/**Owner */
	private static final String OWNER = "thton";
	
	/**An instance of IssueManager */
	 
	private IssueManager manager;
	
	//Set up an instance of IssueManager for testing
	 @BeforeEach
	    void setUp() {
	        manager = IssueManager.getInstance();

	    }
	
	/**
	 * Test method for saveIssuesToFile method
	 */
	@Test
	public void testSaveIssuesToFile() {
		manager.createNewIssueList();
		
		//Check to see if manager is cleared
		assertEquals(0, manager.getIssueListAsArray().length, "Incorrect length");
		
		manager.addIssueToList(IssueType.ENHANCEMENT, SUMMARY, NOTE);
		manager.saveIssuesToFile("test-files/actual_issue.txt");    
		checkFiles("test-files/exp_enhancement_new.txt", "test-files/actual_issue.txt");
	}

	/**
	 * Test method for loadIssuesFromFile method
	 */
	@Test
	public void testLoadIssuesFromFile() {
		IssueManager.getInstance().loadIssuesFromFile("test-files/issue1.txt");
		assertEquals(IssueManager.getInstance().getIssueById(1).getSummary(), "Issue description");
        assertEquals(IssueManager.getInstance().getIssueById(1).getNotes().get(0), "[New] Note 1");
        
        assertThrows(IllegalArgumentException.class, () -> IssueManager.getInstance().loadIssuesFromFile("test-files/issue3.txt"));
        assertThrows(IllegalArgumentException.class, () -> IssueManager.getInstance().loadIssuesFromFile("test-files/issue4.txt"));
        assertThrows(IllegalArgumentException.class, () -> IssueManager.getInstance().loadIssuesFromFile("test-files/issue5.txt"));
        assertThrows(IllegalArgumentException.class, () -> IssueManager.getInstance().loadIssuesFromFile("test-files/issue6.txt"));
        assertThrows(IllegalArgumentException.class, () -> IssueManager.getInstance().loadIssuesFromFile("test-files/issue7.txt"));
        assertThrows(IllegalArgumentException.class, () -> IssueManager.getInstance().loadIssuesFromFile("test-files/issue8.txt"));
        assertThrows(IllegalArgumentException.class, () -> IssueManager.getInstance().loadIssuesFromFile("test-files/issue9.txt"));
        assertThrows(IllegalArgumentException.class, () -> IssueManager.getInstance().loadIssuesFromFile("test-files/issue10.txt"));
        assertThrows(IllegalArgumentException.class, () -> IssueManager.getInstance().loadIssuesFromFile("test-files/issue11.txt"));
        assertThrows(IllegalArgumentException.class, () -> IssueManager.getInstance().loadIssuesFromFile("test-files/issue12.txt"));
        assertThrows(IllegalArgumentException.class, () -> IssueManager.getInstance().loadIssuesFromFile("test-files/issue13.txt"));
        assertThrows(IllegalArgumentException.class, () -> IssueManager.getInstance().loadIssuesFromFile("test-files/issue14.txt"));
        assertThrows(IllegalArgumentException.class, () -> IssueManager.getInstance().loadIssuesFromFile("test-files/issue15.txt"));
        assertThrows(StringIndexOutOfBoundsException.class, () -> IssueManager.getInstance().loadIssuesFromFile("test-files/issue16.txt"));
        assertThrows(IllegalArgumentException.class, () -> IssueManager.getInstance().loadIssuesFromFile("test-files/issue17.txt"));
        assertThrows(IllegalArgumentException.class, () -> IssueManager.getInstance().loadIssuesFromFile("test-files/issue18.txt"));
	}
	
	

	/**
	 * Test method for createNewIssueList method
	 */
	@Test
	public void testCreateNewIssueList() {
		manager.createNewIssueList();
		
		 
		manager.addIssueToList(IssueType.BUG, "summary2", "note2");
		manager.addIssueToList(IssueType.BUG, "Summary1", "Note1");
		manager.addIssueToList(IssueType.ENHANCEMENT, "summary3", "Note3");
		manager.addIssueToList(IssueType.BUG, "summary4", "note4");
		manager.addIssueToList(IssueType.ENHANCEMENT, "summary5", "note5");
		 
		//Check before clearing
		assertEquals(5, manager.getIssueListAsArray().length, "Incorrect length for empty array");
		
		manager.createNewIssueList();
		
		//Check to see if manager is cleared
		assertEquals(0, manager.getIssueListAsArray().length, "Incorrect length for empty array");
	}

	/**
	 * Test method for getIssueListAsArray method
	 */
	@Test
	public void testGetIssueListAsArray() {
		manager.createNewIssueList();
		
		//Check to see if manager is cleared
		assertEquals(0, manager.getIssueListAsArray().length, "Incorrect length");
		
		Object[][] issueArray = manager.getIssueListAsArray();
		
		//Check size of array
		assertEquals(0, issueArray.length);
		 
		manager.addIssueToList(IssueType.BUG, "summary1", "note1");
		manager.addIssueToList(IssueType.ENHANCEMENT, "summary2", "note2");
		
		issueArray = manager.getIssueListAsArray();
		assertEquals(2, issueArray.length);
		 
		assertEquals(1, issueArray[0][0], "Incorrect issue Id");
		assertEquals(Issue.NEW_NAME, issueArray[0][1]); 
		assertEquals(Issue.I_BUG, issueArray[0][2]); 
		assertEquals("summary1", issueArray[0][3]);
		
		assertEquals(2, issueArray[1][0]); 
		assertEquals(Issue.NEW_NAME, issueArray[1][1]); 
		assertEquals(Issue.I_ENHANCEMENT, issueArray[1][2]); 
		assertEquals("summary2", issueArray[1][3]); 
		
				
	}

	/**
	 * Test method for getIssueListArrayByIssueType method
	 */
	@Test
	public void testGetIssueListAsArrayByIssueType() {
		manager.createNewIssueList();
		
		//Check to see if manager is cleared
		assertEquals(0, manager.getIssueListAsArray().length, "Incorrect length");
		
		manager.addIssueToList(IssueType.BUG, "summary1", "note2");
		manager.addIssueToList(IssueType.BUG, "Summary2", "Note1");
		manager.addIssueToList(IssueType.ENHANCEMENT, "summary3", "Note3");
		manager.addIssueToList(IssueType.BUG, "summary4", "note4");
		manager.addIssueToList(IssueType.ENHANCEMENT, "summary5", "note5");
		
		//Test if there are correct number of issues
		assertEquals(5, manager.getIssueListAsArray().length, "Incorrect length");

	    //Test for type not bug or enhancement
	    Object[][] emptyArray = manager.getIssueListAsArrayByIssueType("InvalidType");
	    assertEquals(0, emptyArray.length);
	    
	    //Test for type enhancement
	    Object[][] arr1 = manager.getIssueListAsArrayByIssueType(Issue.I_ENHANCEMENT);
	    assertEquals(2, arr1.length, "Incorrect size");
	    assertEquals(Issue.I_ENHANCEMENT, arr1[0][2], "Incorrect type");
	    assertEquals(Issue.I_ENHANCEMENT, arr1[1][2], "Incorrect type");
	    
	   	    
	    
	    //Test for type bug
	    Object[][] arr2 = manager.getIssueListAsArrayByIssueType(Issue.I_BUG);
	    assertEquals(3, arr2.length, "Incorrect size");
	    assertEquals(Issue.I_BUG, arr2[0][2], "Incorrect type");
	    assertEquals(Issue.I_BUG, arr2[1][2], "Incorrect type");
	    assertEquals(Issue.I_BUG, arr2[2][2], "Incorrect type");
	    
	    Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> manager.getIssueListAsArrayByIssueType(null));
		assertEquals("Invalid information.", e1.getMessage(), "Incorrect exeption thrown wiht null not" + null);
	    
	    
	}

	/**
	 * Test method for getIssueById
	 */
	@Test
	public void testGetIssueById() {
		manager.createNewIssueList();
		//Check to see if manager is cleared
		assertEquals(0, manager.getIssueListAsArray().length, "Incorrect length");
		//Id 0 does not exist
		assertNull(manager.getIssueById(0));
		
		manager.addIssueToList(IssueType.BUG, "summary1", "note1");
		manager.addIssueToList(IssueType.BUG, "Summary2", "note2");
		manager.addIssueToList(IssueType.ENHANCEMENT, "summary3", "note3");
		
		Issue issueToCheck = manager.getIssueById(3);
		Issue expectedIssue = new Issue (3, IssueType.ENHANCEMENT, "summary3", "note3");
		//Check each element of the issues
		assertAll ("Issue",
				() -> assertEquals(expectedIssue.getSummary(), issueToCheck.getSummary(), "incorrect summary"),
				() -> assertEquals(expectedIssue.getIssueId(), issueToCheck.getIssueId(), "incorrect issue id"),
				() -> assertEquals(expectedIssue.getStateName(), issueToCheck.getStateName(), "incorrect state name"),
				() -> assertEquals(expectedIssue.getIssueType(), issueToCheck.getIssueType(), "incorrect issue type"),
				() -> assertEquals(expectedIssue.getOwner(), issueToCheck.getOwner(), "incorrect owner id"),
				() -> assertFalse(issueToCheck.isConfirmed(), "incorrect confirm status"),
				() -> assertEquals(expectedIssue.getResolution(), issueToCheck.getResolution(), "incorrect resolution"),
				() -> assertEquals(expectedIssue.getNotes(), issueToCheck.getNotes(), "incorrect notes array"));
	}

	/**
	 * Test method for executeCommand method
	 */
	@Test
	public void testExecuteCommand() {
		manager.createNewIssueList();
		//Check to see if manager is cleared
		assertEquals(0, manager.getIssueListAsArray().length, "Incorrect length");
		//Id 0 does not exist
		assertNull(manager.getIssueById(0));
		
		manager.addIssueToList(IssueType.BUG, "summary1", "note1");
		manager.addIssueToList(IssueType.BUG, "summary2", "note2");
		manager.addIssueToList(IssueType.ENHANCEMENT, "summary3", "note3");
		
		Command c1 = new Command(CommandValue.CONFIRM, OWNER, null, NOTE);
		
		manager.executeCommand(2, c1);
		//Check issue with id 2 after a command is exectuted.
		assertAll ("Issue",
				() -> assertEquals("null", manager.getIssueById(2).getOwner(), "Incorrect owner"),
				() -> assertTrue(manager.getIssueById(2).isConfirmed(), "Incorrect confirmed status"),
				() -> assertEquals(Issue.CONFIRMED_NAME, manager.getIssueById(2).getStateName(), "Incorrect state name"),
				() -> assertEquals("", manager.getIssueById(2).getResolution(), "Incorrect resolution "),
				() -> assertEquals(2, manager.getIssueById(2).getIssueId(), "Incorrect issue id"),
				() -> assertEquals(Issue.I_BUG, manager.getIssueById(2).getIssueType(), "Incorrect issue type"),
				() -> assertEquals("summary2", manager.getIssueById(2).getSummary(), "Incorrect issue summary"),
				() -> assertEquals("-[New] note2\r\n"
								+ "-[Confirmed] note", manager.getIssueById(2).getNotesString(), "Incorrect notes"));

	}

	/**
	 * Test method for deleteIssueById
	 */
	@Test
	public void testDeleteIssueById() {
		manager.createNewIssueList();
		//Check to see if manager is cleared
		assertEquals(0, manager.getIssueListAsArray().length, "Incorrect length");
		
		
		manager.addIssueToList(IssueType.BUG, "summary1", "note1");
		manager.addIssueToList(IssueType.BUG, "Summary2", "note2");
		manager.addIssueToList(IssueType.ENHANCEMENT, "summary3", "note3");
		
		manager.deleteIssueById(2);
		
		assertEquals(2, manager.getIssueListAsArray().length, "Incorrect length");
		
		manager.deleteIssueById(1);
		
		assertEquals(1, manager.getIssueListAsArray().length, "Incorrect length");
		
		manager.deleteIssueById(3);
		
		assertEquals(0, manager.getIssueListAsArray().length, "Incorrect length");
		
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {
			
			while (actScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			if (expScanner.hasNextLine()) {
				fail();
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

	
}
