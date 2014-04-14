/*
 * 	Joshua O'Steen
 * 	CS 3330 - Guilliams, England
 * 	Homework 3
 * 	04/10/2014
 * 	UML, Machine State, Javadoc
 */


package jrocnc.cs3330.hw3;

// TODO: Auto-generated Javadoc
public class TurnCommand extends DIRCommand {
	
	// attributes
	//
	//
	//
	public Direction dir = null;

	
	// constructors
	//
	//
	//
	/**
	 * Instantiates a new turn command.
	 * 
	 * @post Instantiated TurnCommand object.
	 */
	public TurnCommand() {
		super();
	}

	/**
	 * Instantiates a new turn command.
	 *
	 * @param tc the existing turn command.
	 * @pre tc is an existing TurnCommand object
	 * @post Instantiated TurnCommand object copied from tc.
	 */
	public TurnCommand(TurnCommand tc) {
		super((DIRCommand) tc);
		if (tc.dir != null)
			dir = new Direction(tc.dir);
	}

	/**
	 * Instantiates a new turn command.
	 *
	 * @param d the direction
	 * @pre d is a valid coordinate on the radar game field.
	 * @post Instantiated TurnCommand object with a direction d.
	 */
	public TurnCommand(Direction d) {
		super();
		if (d != null)
			dir = new Direction(d);
		active_flag = true;
	}

	/**
	 * Instantiates a new turn command.
	 *
	 * @param d the d
	 * @param p the p
	 * @pre d and p are valid coordinates on the radar game field.
	 * @post Instantiated TurnCommand with position p and direction d.
	 */
	public TurnCommand(Direction d, Position p) {
		super(p);
		if (d != null)
			dir = new Direction(d);
		active_flag = false;
	}
};
