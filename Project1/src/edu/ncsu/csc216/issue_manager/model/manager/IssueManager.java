package edu.ncsu.csc216.issue_manager.model.manager;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.issue.Issue;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

/**
 * The IssueManager class
 * @author Louis Ton
 */
public class IssueManager {
	
	/**
	 * The constructor for IssueManager
	 */
	private IssueManager() {
		
	}
	
	/**
	 * Get an instance of IssueManager
	 * @return an IssueManager
	 */
	public static IssueManager getInstance() {
		return null;
	}
	
	/**
	 * Save issues to a file
	 * @param filename name of the file
	 */
	public void saveIssuesToFile(String filename) {
		
	}
	
	/**
	 * Loads issues from a file
	 * @param filename name of the file
	 */
	public void loadIssuesFromFile(String filename) {
		
	}
	
	/**
	 * Creat a new list of issues
	 */
	public void createNewIssueList() {
		
	}
	
	/**
	 * Gets the issueList as an array
	 * @return the issue list as an array
	 */
	public Object[][] getIssueListAsArray() {
		return null;
	}
	
	/**
	 * Gets the issueList as an array by issueType
	 * @param issueType the type of issue
	 * @return the issue list as an array
	 */
	public Object[][] getIssueListAsArrayByIssueType(String issueType) {
		return null;
	}
	
	/**
	 * Get an issue by its id
	 * @param id the id of issue
	 * @return the issue
	 */
	public Issue getIssueById(int id) {
		return null;
	}
	
	/**
	 * Execute the command
	 * @param state the state
	 * @param command the command 
	 */
	public void executeCommand(int state, Command command) {
		
	}
	
	/**
	 * Delete an issue by id
	 * @param id the id of issue
	 */
	public void deleteIssueById(int id) {
		
	}
	
	/**
	 * Add issue to a list
	 * @param issueType the issueType
	 * @param summary the summary
	 * @param note the note
	 */
	public void addIssueToList(IssueType issueType, String summary, String note) {
		
	}
}
