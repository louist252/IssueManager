package edu.ncsu.csc216.issue_manager.model.issue;

import java.util.ArrayList;

import edu.ncsu.csc216.issue_manager.model.command.Command;

/**
 * The Issue class
 * @author Louis Ton
 */
public class Issue {
	
	/** IssueType that can be used */
	public enum IssueType { ENHANCEMENT, BUG }
	
	/**  A constant string for the “Enhancement” issue type */
	public static final String I_ENHANCEMENT = "Enhancement";
	
	/** A constant string for the “Bug” issue type */
	public static final String I_BUG = "Bug";
	
	/** A constant string for the new state’s name with the value “New” */
	public static final String NEW_NAME = "Name";
	
	/** A constant string for the working state’s name with the value “Working” */
	public static final String WORKING_NAME = "Working";
	
	/** A constant string for the confirmed state’s name with the value “Confirmed” */
	public static final String CONFIRMED_NAME = "Confirmed";
	
	/** A constant string for the verifying state’s name with the value “Verifying” */
	public static final String VERIFYING_NAME = "Verifying";
	
	/** A constant string for the closeed state’s name with the value “Closed” */
	public static final String CLOSED_NAME = "Closed";
	
	/** Unique issue id for an issue */
	private int issueId;
	
	/** Issue’s summary information from when the issue is created */
	private String summary;
	
	/** User id of the issue owner or null if there is not an assigned owner */
	private String owner;
	
	/** True if the issue is confirmed. The confirmed field can only be set to true if the issue is a bug */
	private boolean confirmed;
	
	/** An ArrayList of notes */
	private ArrayList<String> notes = new ArrayList<String>();

	/**
	 * Constructs a Issue from the provided IssueType, summary, and note. If any parameter is null or  
	 * or string is empty or id less than 1, IllegalArgumentException is thrown
	 * @param id the id of issue
	 * @param issueType the type of issue
	 * @param summary the summary
	 * @param note the note
	 * @throws IllegalArgumentException if parameter is invalid
	 */
	public Issue(int id, IssueType issueType, String summary, String note) {
		if (issueType == null ) {
			throw new IllegalArgumentException("Issue cannot be created");
		}
			
		if (summary == null || summary.length() == 0) {
			throw new IllegalArgumentException("Issue cannot be created");
		}
		
		if (id < 1) {
			throw new IllegalArgumentException("Issue cannot be created");
		}
		setIssueId(id);
		
		
	}
	
	/**
	 * Construct a Issue from all fields
	 * @param id the id of issue
	 * @param state the state of issue
	 * @param issueType the type of issue
	 * @param summary the summary
	 * @param owner the owner
	 * @param confirmed confirmed status
	 * @param resolution the resolution
	 * @param notes the notes
	 */
	public Issue(int id, String state, String issueType, String summary, String owner, 
			boolean confirmed, String resolution, ArrayList<String> notes) {
		setIssueId(id);
		setState(state);
		setIssueType(issueType);
		setSummary(summary);
		setOwner(owner);
		setConfirmed(confirmed);
		setResolution(resolution);
		setNotes(notes);
	}

	/**
	 * Sets the issueId
	 * @param issueId the issueId to set
	 */
	private void setIssueId(int issueId) {
		this.issueId = issueId;
	}
	
	/**
	 * Sets the state
	 * @param state the state to set
	 */
	private void setState(String state) {
		//To be implemented
		
	}
	
	/**
	 * Sets the issueType
	 * @param issueType the issueType to set
	 */
	private void setIssueType (String issueType) {
		//To be implemented
	}

	/**
	 * Sets the summary
	 * @param summary the summary to set
	 */
	private void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * Sets the owner
	 * @param owner the owner to set
	 */
	private void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * Sets the confirmed
	 * @param confirmed the confirmed to set
	 */
	private void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	
	/**
	 * Sets the resolution
	 * @param resolution the resolution to set
	 */
	private void setResolution(String resolution) {
		//To be implemented
	}

	/**
	 * sets the notes
	 * @param notes the notes to set
	 */
	private void setNotes(ArrayList<String> notes) {
		this.notes = notes;
	}

	/**
	 * Gets the issueId
	 * @return the issueId
	 */
	public int getIssueId() {
		return issueId;
	}

	/**
	 * Gets the stateName
	 * @return the stateName
	 */
	public String getStateName() {
		return null;
	}
	
	/**
	 * Gets the issueType
	 * @return the issueType
	 */
	public String getIssueType() {
		return null;
	}
	
	/**
	 * Gets the resolution
	 * @return the resolution;
	 */
	public String getResolution() {
		return null;
	}

	/**
	 * Gets the owner
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}
	
	/**
	 * Gets the summary
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * Gets the notes
	 * @return the notes
	 */
	public ArrayList<String> getNotes() {
		return notes;
	}
	
	/**
	 * Gets the notes as a string
	 * @return the string of notes
	 */
	public String getNotesString() {
		return null;
	}
	
	/**
	 * Gets the confirmed status
	 * @return True if the issue is confirmed
	 */
	public boolean isConfirmed() {
		return confirmed;
	}
	
	/**
	 * method returns the string representation of the Issue
	 * @return the string
	 */
	public String toString() {
		return null;
	}
	
	/**
	 * The method ensures that the note is not null or an empty string.
	 * If the note has contents, then the state name in square brackets is prepended to the note.
	 * @param note the note to add
	 */
	private void addNote(String note) {
		//To be implemented
	}
	
	/**
	 * The update method
	 * @param command command to update
	 */
	public void update (Command command) {
		//To be implemeted
	}
	
	
	
	/**
	 * Interface for states in the Issue State Pattern.  All 
	 * concrete issue states must implement the IssueState interface.
	 * The IssueState interface should be a private interface of the 
	 * Issue class.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu) 
	 */
	private interface IssueState {
		
		/**
		 * Update the Issue based on the given Command.
		 * An UnsupportedOperationException is throw if the Command
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the Issue's
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		void updateState(Command command);
		
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		String getStateName();

	}

	/**
	 * Inner class for the ClosedState
	 * @author Louis Ton
	 */
	private class ClosedState implements IssueState {
		/**
		 * The constructor for ClosedState
		 */
		private ClosedState() {
			
		}
		
		@Override
		public void updateState(Command command) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	/**
	 * Inner class for the VerifyingState
	 * @author Louis Ton
	 */
	private class VerifyingState implements IssueState {
		/**
		 * The constructor for VerifyingState
		 */
		private VerifyingState() {
			
		}
		
		@Override
		public void updateState(Command command) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	/**
	 * Inner class for the ConfirmedState
	 * @author Louis Ton
	 */
	private class ConfirmedState implements IssueState {
		/**
		 * The constructor for ConfirmedState
		 */
		private ConfirmedState() {
			
		}
		
		@Override
		public void updateState(Command command) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	/**
	 * Inner class for the NewState
	 * @author Louis Ton
	 */
	private class NewState implements IssueState {
		/**
		 * The constructor for NewState
		 */
		private NewState() {
			
		}
		
		@Override
		public void updateState(Command command) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	/**
	 * Inner class for the WorkingState
	 * @author Louis Ton
	 */
	private class WorkingState implements IssueState {
		/**
		 * The constructor for WorkingState
		 */
		private WorkingState() {
			
		}
		
		@Override
		public void updateState(Command command) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return null;
		}
			
	}
	
	
	
	
	
	
}
