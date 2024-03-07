/**
 * 
 */
package edu.ncsu.csc216.issue_manager.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.issue_manager.model.issue.Issue;


/**
 * The IssueWriterTest method
 * @author Louis Tob
 */
public class IssueWriterTest {

	

	/**
	 * Test method for wrtieIssueToFile method
	 */
	@Test
	public void testWriteIssuesToFile() {
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("[New] Note 1");
		notes.add("[Confirmed] Note 2");
		notes.add("[Working] Note 3");
		ArrayList<Issue> issues = new ArrayList<Issue>();
		issues.add(new Issue(4, "Closed", "Bug", "Issue description", "", false, "WontFix", notes));
		
		try {
			IssueWriter.writeIssuesToFile("test-files/actual_issue.txt", issues);
		} catch (Exception e) {
			fail("Cannot write to course records file");
		}
	}

	/**
	 * Test constructor for coverage
	 */
	@Test
	public void testIssueWriter() {
		assertDoesNotThrow(() -> new IssueWriter());
	}
}
