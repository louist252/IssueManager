/**
 * 
 */
package edu.ncsu.csc216.issue_manager.model.io;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;


import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.issue_manager.model.issue.Issue;


/**
 * The IssueReaderTest class
 * @author Louis Ton
 */
public class IssueReaderTest {

	/**Valid issue test file */
	private final String validTestFile = "test-files/issue1.txt";
	
	/** Expected results for valid issue in issue1.txt - line 1 */
	private final String validIssue1 = "*1,New,Enhancement,Issue description,,false,\r\n"
										+ "-[New] Note 1";
	
	/** Expected results for valid issue in issue1.txt - line 3 */
	private final String validIssue2 = "*3,Confirmed,Bug,Issue description,,true,\r\n"
										+ "-[New] Note 1\r\n"
										+ "-[Confirmed] Note 2\r\n"
										+ "that goes on a new line";
	
	/** Expected results for valid issue in issue1.txt - line 7 */
	private final String validIssue3 = "*7,Working,Bug,Issue description,owner,true,\r\n"
										+ "-[New] Note 1\r\n"
										+ "-[Confirmed] Note 2\r\n"
										+ "-[Working] Note 3";
	
	/** Expected results for valid issue in issue1.txt - line 11 */
	private final String validIssue4 = "*14,Verifying,Enhancement,Issue description,owner,false,Fixed\r\n"
										+ "-[New] Note 1\r\n"
										+ "-[Working] Note 2\r\n"
										+ "that goes on a new line\r\n"
										+ "-[Verifying] Note 3";
	
	/** Expected results for valid issue in issue1.txt - line 16 */
	private final String validIssue5 = "*15,Closed,Enhancement,Issue description,owner,false,WontFix\r\n"
										+ "-[New] Note 1\r\n"
										+ "that goes on a new line\r\n"
										+ "-[Working] Note 2\r\n"
										+ "-[Verifying] Note 3\r\n"
										+ "-[Working] Note 4\r\n"
										+ "-[Closed] Note 6";		
	
	/**Array to hold the expected results */
	private final String[] validIssues = {validIssue1, validIssue2, validIssue3, validIssue4, validIssue5};
	
	
	
	
	/**
	 * Test method for readIssueFromFile method
	 */
	@Test
	public void testReadIssuesFromFile() {
		try {
			ArrayList<Issue> issues = IssueReader.readIssuesFromFile(validTestFile);
			assertEquals(validIssues.length, issues.size(), "Incorrect length");
			for (int i = 0; i < validIssues.length; i++) {
				assertEquals(validIssues[i], issues.get(i).toString());
			}
		
		} catch (Exception e) {
			Exception exception = assertThrows(IllegalArgumentException.class, 
					() -> IssueReader.readIssuesFromFile("Invalid file location"));
			assertEquals("File not found", exception.getMessage());
		}
		
	}
	
	/**
	 * Test with an invalid file
	 */
	@Test
	public void testReadIssuesFromInvalidFile() {
		try {
			ArrayList<Issue> issues = IssueReader.readIssuesFromFile("test-files/invalid_test_file.txt");
			assertEquals(0, issues.size(), "Incorrect length");
		} catch (Exception e) {
			Exception exception = assertThrows(IllegalArgumentException.class, 
					() -> IssueReader.readIssuesFromFile("Invalid file location"));
			assertEquals("File not found", exception.getMessage());
		}
		
		Exception exception = assertThrows(IllegalArgumentException.class, 
				() -> IssueReader.readIssuesFromFile("Invalid file location"));
		assertEquals("File not found", exception.getMessage());
		
	}
	
	/**
	 * Test constructor
	 */
	public void testConstructor() {
		assertDoesNotThrow(() -> new IssueReader());
	}
	

}
