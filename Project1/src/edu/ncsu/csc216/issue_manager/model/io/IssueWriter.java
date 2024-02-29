package edu.ncsu.csc216.issue_manager.model.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import edu.ncsu.csc216.issue_manager.model.issue.Issue;

/**
 * The IssueWriter class
 * @author Louis Ton
 */
public class IssueWriter {
	/**
	 * The constructor for IssueWriter
	 */
	public IssueWriter() {
		
	}
	
	/**
	 * Write the the list of issues into a file
	 * @param filename the file to write into
	 * @param issues the list of issues
	 * @throws IOException if unable to write to file
	 * @throws IllegalArgumentException if there is any errro
	 */
	public static void writeIssuesToFile(String filename, List<Issue> issues) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(filename));
		try {
			for (int i = 0; i < issues.size(); i++) {
				fileWriter.println(issues.get(i).toString());
			}
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Unable to save file");
		}
		fileWriter.close();
	}
}
