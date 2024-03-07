package edu.ncsu.csc216.issue_manager.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

import edu.ncsu.csc216.issue_manager.model.issue.Issue;

/**
 * The IssueWriter class
 * @author Louis Ton
 */
public class IssueWriter {
	
	
	/**
	 * Write the the list of issues into a file
	 * @param filename the file to write into
	 * @param issues the list of issues
	 * @throws IllegalArgumentException if there is any error
	 */
	public static void writeIssuesToFile(String filename, List<Issue> issues) {
		File file = new File(filename);
		
		try (PrintStream fileWriter = new PrintStream(file);){
			for (int i = 0; i < issues.size(); i++) {
				fileWriter.println(issues.get(i).toString());
			}
			fileWriter.close();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Cannot write issue to file");
		}
		
	}
}
