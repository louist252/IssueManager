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
	
	/** The list of issues*/
	private ArrayList<Issue> issues;
	
	/**
	 * The contrusctor for IssueList. When a new IssueList is set
	 * the counter reset to 0 and a new IssueList is instantiated
	 */
	public IssueList() {
		counter = 1;
		issues = new ArrayList<Issue>();
	}
	
	/**
	 * Adding an issue to the list
	 * @param issueType the issueType
	 * @param summary the summary
	 * @param note the note
	 * @return the index of the issue
	 */
	public int addIssue(IssueType issueType, String summary, String note) {
		Issue issue = new Issue(counter, issueType, summary, note);
		addIssue(issue);
		return issues.indexOf(issue);
	}
	
	/**
	 * Adding a collection of issue
	 * @param issues the list of issues provided
	 */
	public void addIssues(ArrayList<Issue> issues) {
		for (int i = 0; i < issues.size(); i++) {
			addIssue(issues.get(i));
		}
		if(!issues.isEmpty()) {
			counter = issues.get(issues.size() - 1).getIssueId() + 1;
		}
	}
	
	/**
	 * Help with checking for duplicates and adding a single issue in sorted order
	 * @param issue the issue to add
	 */
	private void addIssue(Issue issue) {
		int index = 0;
		for (int i = 0; i < issues.size(); i++) {
			if (issues.get(i).getIssueId() < issue.getIssueId()) {
				index++;
			}
		}
		issues.add(index, issue);
		counter = issues.get(issues.size() - 1).getIssueId() + 1;
	}
	
	/**
	 * Gets the issues
	 * @return The list of issues
	 */
	public ArrayList<Issue> getIssues() {
		return issues;
	}
	
	/**
	 * Gets the issues by type
	 * @param issueType the type of issue
	 * @return the list of issues
	 */
	public ArrayList<Issue> getIssuesByType(String issueType) {
		ArrayList<Issue> issuesByType = new ArrayList<Issue>();
		for(int i = 0; i < issues.size(); i++) {
			if(issues.get(i).getIssueType().equals(issueType)) {
				issuesByType.add(issues.get(i));
			}
		}
		return issuesByType;
	}
	
	
	/**
	 * Gets the issue by id
	 * @param id the id of issue
	 * @return the issue
	 */
	public Issue getIssueById(int id) {
		for (int i = 0; i < issues.size(); i++) {
			if (issues.get(i).getIssueId() == id) {
				return issues.get(i);
			}
		}
		return null;
	}
	
	
	/**
	 * Execute the command
	 * @param id id of the issue we want to update
	 * @param command the command to update the issue
	 */
	public void executeCommand(int id, Command command) {
		getIssueById(id).update(command);
	}
	
	/**
	 * Delete an issue by id
	 * @param id the id of issue
	 */
	public void deleteIssueById(int id) {
		for (int i = 0; i < issues.size(); i++) {
			if (issues.get(i).getIssueId() == id) {
				issues.remove(i);
			}
		}
	}
	
	
}
