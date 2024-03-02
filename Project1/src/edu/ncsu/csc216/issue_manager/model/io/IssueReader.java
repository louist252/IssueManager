package edu.ncsu.csc216.issue_manager.model.io;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

import edu.ncsu.csc216.issue_manager.model.issue.Issue;

/**
 * The IssueReader class
 * @author Louis Ton
 */
public class IssueReader {
	/**
	 * The IssueReader constructor
	 */
	public IssueReader() {
		
	}
	
	/**
	 * Read issue from a file
	 * @param filename the file to read
	 * @return an array of Issues
	 */
	public static ArrayList<Issue> readIssuesFromFile(String filename) {
		ArrayList<Issue> issueList = new ArrayList<Issue>();
		try (Scanner scan = new Scanner(new File(filename))) {
			scan.useDelimiter("\\r?\\n?[*]");
			while(scan.hasNext()) {
				String issueString = scan.next();
				try {
					Issue issue = processIssue(issueString);
					issueList.add(issue);
				} catch (Exception e) {
					//For other class
				}
			}
			scan.close();
		} catch (Exception e){
			 throw new IllegalArgumentException("Unable to load file.");
		}
		
		return issueList;
	}
	
	
	/**
	 * Process the line from the file, then return an issue
	 * @param line the line from the file
	 * @return an issue
	 */
	private static Issue processIssue(String line) {
		try (Scanner scanner = new Scanner(line)){
			scanner.useDelimiter(",");
			int id = scanner.nextInt();
			String state = scanner.next();
			String type = scanner.next();
			String summary =  scanner.next();
			String owner = scanner.next();
			boolean confirmed = scanner.nextBoolean();
			String resolution = scanner.next();
			String notes = "";
			
			while (scanner.hasNext()) {
	            notes += scanner.next() + ",";
	        }
			
			notes = notes.substring(0, notes.length() - 1);
			
			ArrayList<String> notesArray = new ArrayList<String>();
			try (Scanner forNote = new Scanner(notes)){
				forNote.useDelimiter("\\r?\\n?[-]");
				while(forNote.hasNext()) {
					String note = forNote.next().trim();
					if(!note.isEmpty()) {
						notesArray.add(note);
					}
				}
			
			forNote.close();
			scanner.close();
			}
			Issue i = new Issue(id, state, type, summary, owner, confirmed, resolution, notesArray);
			return i;
			
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}
}
