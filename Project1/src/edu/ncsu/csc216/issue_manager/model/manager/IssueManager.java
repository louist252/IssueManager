package edu.ncsu.csc216.issue_manager.model.manager;

import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.io.IssueReader;
import edu.ncsu.csc216.issue_manager.model.io.IssueWriter;
import edu.ncsu.csc216.issue_manager.model.issue.Issue;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

/**
 * The IssueManager class
 * @author Louis Ton
 */
public class IssueManager {
	
	/** An issueList*/
	private IssueList issueList;
	
	/** An instance of IssueManager, dealing with Singleton*/
	private static IssueManager singleton;
	
	/**
	 * The constructor for IssueManager. Instantiate a new 
	 * issueList
	 */
	private IssueManager() {
		createNewIssueList();
	}
	
	/**
	 * Get an instance of IssueManager
	 * @return an IssueManager
	 */
	public static IssueManager getInstance() {
		if(singleton == null) {
			singleton = new IssueManager();
		}
		return singleton;
	}
	
	/**
	 * Save issues to a file
	 * @param filename name of the file
	 */
	public void saveIssuesToFile(String filename) {
		try {
			IssueWriter.writeIssuesToFile(filename, issueList.getIssues());
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to save file");
		}
	}
	
	/**
	 * Loads issues from a file
	 * @param filename name of the file
	 */
	public void loadIssuesFromFile(String filename) {
		try {
			issueList.addIssues(IssueReader.readIssuesFromFile(filename));
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Creat a new list of issues
	 */
	public void createNewIssueList() {
		issueList = new IssueList();
	}
	
	/**
	 * Gets the issueList as a 2D array
	 * @return the issue list as an array
	 */
	public Object[][] getIssueListAsArray() {
		ArrayList<Issue> issues = issueList.getIssues();
		Object[][] issueArrayTwoD = new Object[issues.size()][4];
		for(int i = 0; i < issueList.getIssues().size(); i++) {
			issueArrayTwoD [i][0] = issueList.getIssues().get(i).getIssueId();
			issueArrayTwoD [i][1] = issueList.getIssues().get(i).getStateName();
			issueArrayTwoD [i][2] = issueList.getIssues().get(i).getIssueType();
			issueArrayTwoD [i][3] = issueList.getIssues().get(i).getSummary();
		}
		
		
		return issueArrayTwoD;
	}
	
	/**
	 * Gets the issueList as an array by issueType
	 * @param issueType the type of issue
	 * @return the issue list as an array
	 */
	public Object[][] getIssueListAsArrayByIssueType(String issueType) {
		if (issueType == null) {
			throw new IllegalArgumentException("Invalid information.");
		}
		
		ArrayList<Issue> issuesByType = issueList.getIssuesByType(issueType);
		Object[][] issueArrayByType = new Object [issuesByType.size()][4];
	
		int row = 0;
		for(int i = 0; i < issuesByType.size(); i++) {
			Issue issue = issuesByType.get(i);
			issueArrayByType[row][0] = issue.getIssueId();
			issueArrayByType[row][1] = issue.getStateName();
			issueArrayByType[row][2] = issue.getIssueType();
			issueArrayByType[row][3] = issue.getSummary();
			row++;
			
		}
		return issueArrayByType;
	}
	
	/**
	 * Get an issue by its id
	 * @param id the id of issue
	 * @return and Issue that matches the id 
	 */
	public Issue getIssueById(int id) {
		return issueList.getIssueById(id);
	}
	
	/**
	 * Execute the command
	 * @param id the id of
	 * @param command the command to update an issue
	 */
	public void executeCommand(int id, Command command) {
		issueList.executeCommand(id, command);
	}
	
	/**
	 * Delete an issue by id
	 * @param id the id of issue
	 */
	public void deleteIssueById(int id) {
		issueList.deleteIssueById(id);
	}
	
	/**
	 * Add issue to a list
	 * @param issueType the issueType
	 * @param summary the summary
	 * @param note the note
	 */
	public void addIssueToList(IssueType issueType, String summary, String note) {
		issueList.addIssue(issueType, summary, note);
	}
}
