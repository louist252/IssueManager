/**
 * 
 */
package edu.ncsu.csc216.issue_manager.model.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.issue_manager.model.command.Command.Resolution;
import edu.ncsu.csc216.issue_manager.model.issue.Issue;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

/**
 * The IssueListTest class
 * @author Louis Tob
 */
class IssueListTest {
	
	/**Summary */
	private static final String SUMMARY = "Issue description";
	
	/**Note string */
	private static final String NOTE = "notes";
	
	/**
	 * Test method for IssueList constructor
	 */
	@Test
	public void testIssueList() {
		assertDoesNotThrow(() -> new IssueList());
	}

	/**
	 * Test method for addIssue method
	 */
	@Test
	public void testAddIssue() {
		//Check the index of issue
		IssueList issueList = new IssueList();
		int val = issueList.addIssue(IssueType.BUG, SUMMARY, NOTE);
		assertEquals(0, val, "Incorrect issue counter");
		
		//Quick check to see if one of the element is the same
		Issue i = new Issue(1, IssueType.BUG, SUMMARY, NOTE);
		assertEquals(i.getSummary(), issueList.getIssues().get(val).getSummary(), "Incorrect id.");
		
		//Check to see if the index is correct after adding one more issue
		int val2 = issueList.addIssue(IssueType.ENHANCEMENT, SUMMARY, NOTE);
		assertEquals(1, val2, "Incorrect issue counter.");
		

	}

	/**
	 * Test method for addIssues method
	 */
	@Test
	public void testAddIssues() {
		//Creat an issueList and add some issues
		IssueList issueList = new IssueList();
		issueList.addIssue(IssueType.ENHANCEMENT, SUMMARY, NOTE);
		issueList.addIssue(IssueType.BUG, SUMMARY, NOTE);
		
		//Adding an array of Issue into issueList
		ArrayList<Issue> issueArrayToAdd = new ArrayList<Issue>();
		issueArrayToAdd.add(new Issue(3, IssueType.ENHANCEMENT, "Different summary", NOTE));
		issueArrayToAdd.add(new Issue(3, IssueType.ENHANCEMENT, "Different summary", NOTE));
		issueList.addIssues(issueArrayToAdd);
		
		//Compare the size with an expected Issue array to see if addIssues
		//work as inteded
		ArrayList<Issue> expectedIssueArray = new ArrayList<Issue>();
		expectedIssueArray.add(new Issue(1, IssueType.ENHANCEMENT, SUMMARY, NOTE));
		expectedIssueArray.add(new Issue(2, IssueType.BUG, SUMMARY, NOTE));
		expectedIssueArray.add(new Issue(3, IssueType.ENHANCEMENT, "Different summary", NOTE));
		expectedIssueArray.add(new Issue(3, IssueType.ENHANCEMENT, "Different summary", NOTE));
		

		assertEquals(expectedIssueArray.size(), issueList.getIssues().size(), "Incorrect size");
		
		
		//Test the size of issueList if adding an empty Issue array
		issueList.addIssues(new ArrayList<Issue>());
		assertEquals(4, issueList.getIssues().size(), "Incorrect size");
	}


	/**
	 * Test method for getIssuesByType method
	 */
	@Test
	public void testGetIssuesByType() {
		
        
	}

	/**
	 * Test method for getIssueById method
	 */
	@Test
	public void testGetIssueById() {
		Issue i1 = new Issue(12, IssueType.BUG, SUMMARY, NOTE);
		Issue i2 = new Issue(30, IssueType.BUG, SUMMARY, NOTE);
		ArrayList<Issue> issues = new ArrayList<Issue>();
		issues.add(i1);
		issues.add(i2);
		IssueList issueList = new IssueList();
		issueList.addIssues(issues);
		
		Issue issueForTest1 = issueList.getIssueById(12);
		
		assertEquals(i1, issueForTest1, "Incorrect issue" );
		
		//Test for getting an issue with non-existing id
		Issue issueForTest2 = issueList.getIssueById(2);
		
		assertNull(issueForTest2);
	}

	/**
	 * Test method executeCommand method
	 */
	@Test
	public void testExecuteCommand() {
		
	}

	/**
	 * Test method for deleteIssueById method
	 */
	@Test
	public void testDeleteIssueById() {
		//Create an issueList with some issues and varying ids
		Issue i1 = new Issue(1, IssueType.BUG, SUMMARY, NOTE);
		Issue i2 = new Issue(12, IssueType.BUG, SUMMARY, NOTE);
		Issue i3 = new Issue(30, IssueType.BUG, SUMMARY, NOTE);
		ArrayList<Issue> issues = new ArrayList<Issue>();
		issues.add(i1);
		issues.add(i2);
		issues.add(i3);
		IssueList issueList = new IssueList();
		issueList.addIssues(issues);
		
		
		issueList.deleteIssueById(12);
		
		//Check to see if the size changes correctly and the other 2 issues are still there
		//by checking their ids
		
		assertEquals(2, issueList.getIssues().size(), "Incorrect size.");
		assertEquals(1, issueList.getIssues().get(0).getIssueId(), "Incorrect id.");
		assertEquals(30, issueList.getIssues().get(1).getIssueId(), "Incorrect id.");
		
		//Remove one more issue, check size and id of the last issue
		issueList.deleteIssueById(30);
		
		assertEquals(1, issueList.getIssues().size(), "Incorrect size.");
		assertEquals(1, issueList.getIssues().get(0).getIssueId(), "Incorrect id.");
		
		
	
	}

}
