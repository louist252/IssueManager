package edu.ncsu.csc216.issue_manager.model.io;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import edu.ncsu.csc216.issue_manager.model.issue.Issue;

/**
 * The IssueReader class
 * @author Louis Ton
 */
public class IssueReader {
	
	/**
	 * Read issue from a file
	 * @param filename the file to read
	 * @return an array of Issues
	 * @throws FileNotFoundException if the file is not found
	 */
	public static ArrayList<Issue> readIssuesFromFile(String filename) throws FileNotFoundException {
		ArrayList<Issue> issueList = new ArrayList<Issue>();
		try (Scanner scan = new Scanner(new File(filename))) {
			scan.useDelimiter("\\r?\\n?[*]");
			while(scan.hasNext()) {
				String issueString = scan.next();
				Issue issue = processIssue(issueString);
				issueList.add(issue);
				
			}
			scan.close();
		} catch (Exception e){
			 throw new IllegalArgumentException("File not found");
		}
		
		return issueList;
	}
	
	
	/**
	 * Process the line from the file, then return an issue
	 * @param line the line from the file
	 * @return an issue
	 */
	private static Issue processIssue(String line) {
		
		String firstLine = line.substring(0, line.indexOf("\r\n"));
		Scanner scanner = new Scanner(firstLine);
		scanner.useDelimiter(",");
		int id = scanner.nextInt();
		String state = scanner.next();
		String type = scanner.next();
		String summary =  scanner.next();
		String owner = scanner.next();
		boolean confirmed = scanner.nextBoolean();
		String resolution = "";
		
		
		while (scanner.hasNext()) {
			resolution = scanner.next();
		}
		
		
		String notes = line.substring(line.indexOf("\n") + 2);
		Scanner forNotes = new Scanner(notes);
		forNotes.useDelimiter("\r?\n?[-]");
		
		ArrayList<String> notesArray = new ArrayList<String>();
		
		while (forNotes.hasNext()) {
            notesArray.add(forNotes.next());
        }
		
		forNotes.close();
		scanner.close();
		Issue i = new Issue(id, state, type, summary, owner, confirmed, resolution, notesArray);
		return i;
	
	
	}
}
