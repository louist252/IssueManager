package edu.ncsu.csc216.issue_manager.model.issue;

import java.util.ArrayList;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.command.Command.Resolution;

/**
 * The Issue class
 * @author Louis Ton
 */
public class Issue {
	
	/** IssueType that can be used */
	public enum IssueType { 
		/** Enhancement IssueType */
		ENHANCEMENT, 
		/** Bug IssueType */
		BUG 
		}
	
	/**  A constant string for the Enhancement issue type */
	public static final String I_ENHANCEMENT = "Enhancement";
	
	/** A constant string for the Bug issue type */
	public static final String I_BUG = "Bug";
	
	/** A constant string for the new state’s name with the value New */
	public static final String NEW_NAME = "New";
	
	/** A constant string for the working state’s name with the value Working */
	public static final String WORKING_NAME = "Working";
	
	/** A constant string for the confirmed state’s name with the value Confirmed */
	public static final String CONFIRMED_NAME = "Confirmed";

	/** A constant string for the verifying state’s name with the value Verifying */
	public static final String VERIFYING_NAME = "Verifying";
	
	/** A constant string for the closeed state’s name with the value Closed */
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
	
	/** An issueType */
	private IssueType issueType;
	
	/** An issueState */
	private IssueState state;
	
	/** A resolution */
	private Resolution resolution;
	
	/** Final instance of NewState class */
	private final IssueState newState = new NewState();
	
	/** Final instance of WorkingState class */
	private final IssueState workingState = new WorkingState();
	
	/** Final instance of ConfirmedState class */
	private final IssueState confirmedState = new ConfirmedState();
	
	/** Final instance of VerifyingState class */
	private final IssueState verifyingState = new VerifyingState();
	
	/** Final instance of ClosedState class */
	private final IssueState closedState = new ClosedState();


	/**
	 * Constructs a Issue from the provided IssueType, summary, and note. If any parameter is null or  
	 * string is empty or id less than 1, IllegalArgumentException is thrown
	 * @param id the id of issue
	 * @param issueType the type of issue
	 * @param summary the summary
	 * @param note the note
	 * @throws IllegalArgumentException if parameter is invalid
	 */
	public Issue(int id, IssueType issueType, String summary, String note) {
		if (issueType == null ) {
			throw new IllegalArgumentException("Issue cannot be created.");
		}
			
		if (summary == null || summary.length() == 0) {
			throw new IllegalArgumentException("Issue cannot be created.");
		}
		
		if (id < 1) {
			throw new IllegalArgumentException("Issue cannot be created.");
		}
		setIssueId(id);
		setSummary(summary);
		setState(Issue.NEW_NAME);
		this.issueType = issueType;
		addNote(note);
		setNotes(notes);
		setResolution("");
		setConfirmed(false);
		setOwner(null);
		
		
		
	}
	
	/**
	 * Construct a Issue from all fields. If any parameter is null or  
	 * string is empty or id less than 1, IllegalArgumentException is thrown
	 * @param id the id of issue
	 * @param state the state of issue
	 * @param issueType the type of issue
	 * @param summary the summary
	 * @param owner the owner
	 * @param confirmed confirmed status
	 * @param resolution the resolution
	 * @param notes the notes
	 * @throws IllegalArgumentException if parameter is invalid
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
	 * @throws IllegalArgumentException if issueId is less than 1
	 */
	private void setIssueId(int issueId) {
		if (issueId < 1) {
			throw new IllegalArgumentException("Issue cannot be created.");
		}
		
		this.issueId = issueId;
	}
	
	/**
	 * Sets the state
	 * @param issueState the state to set
	 * @throws IllegalArgumentException if issueState is null or empty
	 */
	private void setState(String issueState) {
		if (issueState == null || issueState.length() == 0 ) {
			throw new IllegalArgumentException("Issue cannot be created.");
		}
		
		switch(issueState) {
		case NEW_NAME:
			state = newState;
			break;
		case WORKING_NAME:
			state = workingState;
			break;
		case CONFIRMED_NAME:
			state = confirmedState;
			break;
		case VERIFYING_NAME:
			state = verifyingState;
			break;
		case CLOSED_NAME:
			state = closedState;
			break;
		default:
			break;
		}
	}
	
	/**
	 * Sets the issueType
	 * @param type the type to set
	 * @throws IllegalArgumentException if type is null or empty
	 */
	private void setIssueType (String type) {
		if (type == null || type.length() == 0 ) {
			throw new IllegalArgumentException("Issue cannot be created.");
		}
		switch (type) {
		case I_ENHANCEMENT:
			issueType = IssueType.ENHANCEMENT;
			break;
		case I_BUG:
			issueType = IssueType.BUG;
			break;
		default:
			throw new IllegalArgumentException("Issue cannot be created.");
		}
	}

	/**
	 * Sets the summary
	 * @param summary the summary to set
	 * @throws IllegalArgumentException if summary is null or empty
	 */
	private void setSummary(String summary) {
		if (summary == null || summary.length() == 0 ) {
			throw new IllegalArgumentException("Issue cannot be created.");
		}
		
		this.summary = summary;
	}

	/**
	 * Sets the owner
	 * @param owner the owner to set
	 */
	private void setOwner(String owner) {
		if (owner == null) {
			this.owner = "null";
		} else if (owner.length() == 0) {
			this.owner = "";
		} else {
			this.owner = owner;
		}
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
	 * @param r the resolution to set
	 * @throws IllegalArgumentException if r is null or empty
	 */
	private void setResolution(String r) {
		if (r == null) {
			throw new IllegalArgumentException("Issue cannot be created.");
		}
		switch(r) {
		case Command.R_FIXED:
			resolution = Resolution.FIXED;
			break;
		case Command.R_DUPLICATE:
			resolution = Resolution.DUPLICATE;
			break;
		case Command.R_WONTFIX:
			resolution = Resolution.WONTFIX;
			break;
		case Command.R_WORKSFORME:
			resolution = Resolution.WORKSFORME;
			break;
		default:
			resolution = null;
			break;
		}
		
		/*
		if (r == Command.R_FIXED) {
			resolution = Resolution.FIXED;
		} else if (r == Command.R_DUPLICATE) {
			resolution = Resolution.DUPLICATE;
		} else if (r == Command.R_WONTFIX) {
			resolution = Resolution.WONTFIX;
		} else if (r == Command.R_WORKSFORME) {
			resolution = Resolution.WORKSFORME;
		} else if (r == null || r.length() == 0) {
			resolution = null;
		}
		*/
		
	}

	/**
	 * sets the notes
	 * @param notes the notes to set
	 * @throws IllegalArgumentException if notes is null
	 */
	private void setNotes(ArrayList<String> notes) {
		if (notes == null) {
			throw new IllegalArgumentException("Issue cannot be created.");
		}
		this.notes = notes;
	}

	/**
	 * Gets the issueId
	 * @return the int of issueId
	 */
	public int getIssueId() {
		return issueId;
	}

	/**
	 * Gets the stateName
	 * @return the a string of stateName
	 */
	public String getStateName() {
		String stateName = null;
		if (state == newState) {
			stateName = newState.getStateName();
		} else if (state == workingState) {
			stateName = workingState.getStateName();
		} else if (state == confirmedState) {
			stateName = confirmedState.getStateName();
		} else if (state == verifyingState) {
			stateName = verifyingState.getStateName();
		} else if (state == closedState) {
			stateName = closedState.getStateName();
		}

		return stateName;
	}
	
	/**
	 * Gets the issueType
	 * @return the a string of IssueType
	 */
	public String getIssueType() {
		String type = null;
		switch(issueType) {
		case BUG:
			type = I_BUG;
			break;
		case ENHANCEMENT:
			type =  I_ENHANCEMENT;
			break;
		default:
			break;
		}
		return type;
	}

	
	/**
	 * Gets the resolution
	 * @return the string of resolution
	 */
	public String getResolution() {
		
		if (resolution == Resolution.FIXED) {
			return Command.R_FIXED;
		} else if (resolution == Resolution.DUPLICATE) {
			return  Command.R_DUPLICATE;
		} else if (resolution == Resolution.WONTFIX) {
			return  Command.R_WONTFIX;
		} else if (resolution == Resolution.WORKSFORME) {
			return Command.R_WORKSFORME;
		} else if (resolution == null) {
			return  "";
		}
		
		return null;
	}

	/**
	 * Gets the owner
	 * @return the string of owner
	 */
	public String getOwner() {
		return owner;
	}
	
	/**
	 * Gets the summary
	 * @return the string of summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * Gets the notes
	 * @return the ArrayList string of notes
	 */
	public ArrayList<String> getNotes() {
		return notes;
	}
	
	/**
	 * Gets the notes as a string
	 * @return the string of notes
	 */
	public String getNotesString() {
		String noteString = "";
		for (int i = 0; i < notes.size(); i++) {
			noteString += "-" + notes.get(i) + '\r' + '\n'; 
		}
		noteString = noteString.substring(0, noteString.length() - 2);
		return noteString;
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
		return "*" + getIssueId() + "," + getStateName() + "," + getIssueType() + "," + getSummary() + "," + 
				getOwner() +  "," + isConfirmed() + "," + getResolution() + "\r\n" + getNotesString();
	}
	
	/**
	 * The method ensures that the note is not null or an empty string.
	 * If the note has contents, then the state name in square brackets is prepended to the note.
	 * @param note the note to add
	 * @throws IllegalArgumentException if note is null or empty
	 */
	private void addNote(String note) {
		if (note == null) {
			throw new IllegalArgumentException("Invalid information.");
		}
		String noteWithState = "[" + getStateName() + "] " + note;
		notes.add(noteWithState); 
	}
	
	/**
	 * The update method
	 * @param command command to update
	 */
	public void update (Command command) {
		state.updateState(command);
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
			if (command.getCommand() == Command.CommandValue.REOPEN) {
				setResolution("");
				switch(issueType) {
				case BUG:
					if(isConfirmed()) {
						if(!getOwner().equals("null") && !getOwner().equals("")) {
							setState(WORKING_NAME);
						} else if (getOwner().equals("null") || getOwner().equals("")) {
							setOwner(command.getOwnerId());
							setState(CONFIRMED_NAME);
						}
					} else {
						setOwner(command.getOwnerId());
						setState(NEW_NAME);
					}
					break;
				case ENHANCEMENT:
					if(getOwner().length() != 0) {
						setState(WORKING_NAME);
					} else {
						setOwner(command.getOwnerId());
						setState(NEW_NAME);
					}
					break;
				default:
					break;
				}
				addNote(command.getNote());
			} else {
				throw new UnsupportedOperationException("Invalid information.");
			}
		}

		@Override
		public String getStateName() {
			return CLOSED_NAME;
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
			switch(command.getCommand()) {
			case VERIFY:
				if (resolution == Command.Resolution.FIXED) {
					setState(CLOSED_NAME);
				} else {
					throw new UnsupportedOperationException("Invalid information.");
				}
				break;
			case REOPEN:
				setState(WORKING_NAME);
				setResolution("");
				break;
			default:
				throw new UnsupportedOperationException("Invalid information.");
			}
			addNote(command.getNote());
		}

		@Override
		public String getStateName() {
			return VERIFYING_NAME;
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
			switch(command.getCommand()) {
			case ASSIGN:
				setOwner(command.getOwnerId());
				setState(WORKING_NAME);
				break;
			case RESOLVE:
				if (command.getResolution() == Command.Resolution.WONTFIX) {
					setState(CLOSED_NAME);
				} else {
					throw new UnsupportedOperationException("Invalid information.");
				}
				break;
			default:
				throw new UnsupportedOperationException("Invalid information.");
			}
			addNote(command.getNote());
		}

		@Override
		public String getStateName() {
			return CONFIRMED_NAME;
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
			switch(issueType) {
			case ENHANCEMENT:
				if(command.getCommand() == Command.CommandValue.ASSIGN) {
					setState(WORKING_NAME);
					setOwner(command.getOwnerId());
				} else if (command.getCommand() == Command.CommandValue.RESOLVE) {
					resolution = command.getResolution();
					setState(CLOSED_NAME);
				} else {
					throw new UnsupportedOperationException("Invalid information.");
				}
				break;
			case BUG:
				if (command.getCommand() == Command.CommandValue.CONFIRM) {
					setState(CONFIRMED_NAME);
					setConfirmed(true);
				} else if (command.getCommand() == Command.CommandValue.RESOLVE){
					resolution = command.getResolution();
					setState(CLOSED_NAME);
				} else {
					throw new UnsupportedOperationException("Invalid information.");
				}
				break;
			default:
				break;
			}
			addNote(command.getNote());
		}

		@Override
		public String getStateName() {
			return NEW_NAME;
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
			switch(command.getResolution()) {
			case FIXED:
				if (command.getCommand() == Command.CommandValue.RESOLVE) {
					setState(VERIFYING_NAME);
					setResolution(Command.R_FIXED);
				} else {
					throw new UnsupportedOperationException("Invalid information.");
				}
				break;
			case DUPLICATE:
			case WONTFIX:
				if (command.getCommand() == Command.CommandValue.RESOLVE) {
					setState(CLOSED_NAME);
				} else {
					throw new UnsupportedOperationException("Invalid information.");
				}
				break;
			case WORKSFORME:
				if (command.getCommand() == Command.CommandValue.RESOLVE) {
					if (issueType == IssueType.BUG) {
						setState(CLOSED_NAME);
					} else {
						throw new UnsupportedOperationException("Invalid information.");
					}
				} else {
					throw new UnsupportedOperationException("Invalid information.");
				}
				break;
			default:
				break;
			}
			addNote(command.getNote());
		}

		@Override
		public String getStateName() {
			return WORKING_NAME;
		}
			
	}
	
	
	
	
	
	
}
