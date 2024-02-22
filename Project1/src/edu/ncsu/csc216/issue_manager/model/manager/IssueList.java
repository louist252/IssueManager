package edu.ncsu.csc216.issue_manager.model.manager;

import java.util.ArrayList;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.issue.Issue;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

/**
 * The issueList class
 * @author Louis Ton
 */
public class IssueList {
	/** The counter*/
	private int counter;
	
	/**
	 * The contrusctor for IssueList
	 */
	public IssueList() {
		
	}
	
	/**
	 * Adding an issue to the list
	 * @param issueType the issueType
	 * @param summary the summary
	 * @param note the note
	 * @return the index which the issue is added to
	 */
	public int addIssue(IssueType issueType, String summary, String note) {
		return 0;
	}
	
	/**
	 * Adding a collection of issue
	 * @param issues the list of issues provided
	 */
	public void addIssues(ArrayList<Issue> issues) {
		
	}
	
	/**
	 * Help with checking for duplicates and adding a single issue in sorted order
	 * @param issue the issue to add
	 */
	private void addIssue(Issue issue) {
		
	}
	
	/**
	 * Gets the issues
	 * @return The list of issues
	 */
	public ArrayList<Issue> getIssues() {
		return null;
	}
	
	/**
	 * Gets the issues by type
	 * @param issues the list of issues provided
	 * @return the list of issues
	 */
	public ArrayList<Issue> getIssuesByType(ArrayList<Issue> issues) {
		return null;
	}
	
	
	/**
	 * Gets the issue by id
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
	
	
}
