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
	 */
	public static void writeIssuesToFile(String filename, List<Issue> issues) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(filename));

		for (int i = 0; i < issues.size(); i++) {
			fileWriter.println(issues.get(i).toString());
		}

		fileWriter.close();
	}
}
