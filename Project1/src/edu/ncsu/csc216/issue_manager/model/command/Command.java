package edu.ncsu.csc216.issue_manager.model.command;

/**
 * The Command class
 * @author Louis Ton
 */
public class Command {
	/** CommandValue that can be used */
	public enum CommandValue { 
		/** Assign CommandValue */
		ASSIGN, 
		/** Confirm CommandValue */
		CONFIRM, 
		/** Resolve CommandValue */
		RESOLVE,
		/** Verify CommandValue */
		VERIFY, 
		/** Reopen CommandValue */
		REOPEN 
		}

	/** Resolution that can be used */
	public enum Resolution { 
		/** Fixed Resolution */
		FIXED, 
		/** Duplicate Resolution */
		DUPLICATE, 
		/** WontFix Resolution */
		WONTFIX, 
		/** WorksForMe Resolution */
		WORKSFORME 
		}

	/** A constant string for the Fixed resolution */
	public static final String R_FIXED = "Fixed";
	
	/** A constant string for the Duplicate resolution */
	public static final String R_DUPLICATE = "Duplicate";
	
	/** A constant string for the WontFix resolution */
	public static final String R_WONTFIX = "WontFix";
	
	/** A constant string for the WorksForMe resolution */
	public static final String R_WORKSFORME = "WorksForMe";	
	
	/** The id of owner*/
	private String ownerId;
	
	/** The note*/
	private String note;
	
	/** The commandValue */
	private CommandValue c;
	
	/** The resolution */
	private Resolution resolution;
	
	/**
	 * The Command constructor. It has 4 parameters: CommandValue c, String ownerId, 
	 * Resolution r, String note. If a Command has a null CommandValue parameter, or a
	 * Command has a CommandValue of ASSIGN and a null or empty string ownerId, or a
	 * Command has a CommandValue of RESOLVE and a null resolution, or a
	 * Command with a null or empty note parameter, an IllegalArgumentException is thrown
	 * @param c a type of CommandValue 
	 * @param ownerId Id of owner
	 * @param r a type of Resolution
	 * @param note the note 
	 * @throws IllegalArgumetException when the information is invalid
	 */
	public Command(CommandValue c, String ownerId, Resolution r, String note) {
		if (c == null) {
			throw new IllegalArgumentException("Invalid information.");
		}
		if (c == CommandValue.ASSIGN && ownerId == null || ownerId.length() == 0) {
			throw new IllegalArgumentException("Invalid information.");
		}
		if (c == CommandValue.RESOLVE && r == null) {
			throw new IllegalArgumentException("Invalid information.");
		}
		if (note == null || note.length() == 0) {
			throw new IllegalArgumentException("Invalid information.");
		}
		this.ownerId = ownerId;
		this.note = note;
		this.c = c;
		resolution = r;
	}

	/**
	 * Gets the CommandValue
	 * @return the CommandValue
	 */
	public CommandValue getCommand() {
		CommandValue command = null;
		
		switch(c) {
		case ASSIGN:
			command = CommandValue.ASSIGN;
			break;
		case CONFIRM:
			command = CommandValue.CONFIRM;
			break;
		case RESOLVE:
			command = CommandValue.RESOLVE;
			break;
		case VERIFY:
			command = CommandValue.VERIFY;
			break;
		case REOPEN:
			command = CommandValue.REOPEN;
			break;
		default:
			break;
		}
		return command;
	}

	/**
	 * Gets the ownerId
	 * @return the ownerId
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * Gets the Resolution
	 * @return the Resolutin
	 */
	public Resolution getResolution() {
		Resolution r = null;
		
		switch(resolution) {
		case FIXED:
			r = Resolution.FIXED;
			break;
		case DUPLICATE:
			r = Resolution.DUPLICATE;
			break;
		case WONTFIX:
			r = Resolution.WONTFIX;
			break;
		case WORKSFORME:
			r = Resolution.WORKSFORME;
			break;
		default:
			break;
		}
		return r;
	}

	/**
	 * Gets the note
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	
	
}
