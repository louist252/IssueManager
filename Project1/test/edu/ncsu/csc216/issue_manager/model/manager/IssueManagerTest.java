/**
 * 
 */
package edu.ncsu.csc216.issue_manager.model.manager;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertDoesNotThrow(() -> manager.saveIssuesToFile("test-files/issues.txt"));

	}

	/**
	 * Test method for loadIssuesFromFile method
	 */
	@Test
	public void testLoadIssuesFromFile() {
        assertDoesNotThrow(() -> manager.loadIssuesFromFile("test-files/issue1.txt"));
	}

	/**
	 * Test method for createNewIssueList method
	 */
	@Test
	public void testCreateNewIssueList() {
		 manager.createNewIssueList();
		 assertEquals(0, manager.getIssueListAsArray().length, "Incorrect length for empty array");
	}

	/**
	 * Test method for getIssueListAsArray method
	 */
	@Test
	public void testGetIssueListAsArray() {
		manager.createNewIssueList();
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
		manager.addIssueToList(IssueType.BUG, "summary2", "note2");
		manager.addIssueToList(IssueType.BUG, "Summary1", "Note1");
		manager.addIssueToList(IssueType.ENHANCEMENT, "Summary2", "Note2");

	    //Test for type not bug or enhancement
	    Object[][] emptyArray = manager.getIssueListAsArrayByIssueType("InvalidType");
	    assertEquals(0, emptyArray.length);
	    
	    
	    Object[][] arr2 = manager.getIssueListAsArrayByIssueType(Issue.I_ENHANCEMENT);
	    assertEquals(1, arr2.length, "Incorrect size");
	    
	    Object[][] arr1 = manager.getIssueListAsArrayByIssueType(Issue.I_BUG);
	    assertEquals(2, arr1.length, "Incorrect size");
	    
	    
	}

	/**
	 * Test method for getIssueById
	 */
	@Test
	public void testGetIssueById() {
		//Id 0 does not exist
		assertNull(manager.getIssueById(0));
	}

	/**
	 * Test method for executeCommand method
	 */
	@Test
	public void testExecuteCommand() {
		manager.createNewIssueList();
		manager.addIssueToList(IssueType.BUG, "summary2", "note2");
		manager.addIssueToList(IssueType.BUG, "Summary1", "Note1");
		manager.addIssueToList(IssueType.ENHANCEMENT, "Summary2", "Note2");
		
		//Check issue ID
		
		
		
	}

	/**
	 * Test method for deleteIssueById
	 */
	@Test
	public void testDeleteIssueById() {
		 
	}

	
}
