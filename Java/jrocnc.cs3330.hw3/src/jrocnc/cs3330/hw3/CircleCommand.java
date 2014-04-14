/*
 * 	Joshua O'Steen
 * 	CS 3330 - Guilliams, England
 * 	Homework 3
 * 	04/10/2014
 * 	UML, Machine State, Javadoc
 */


package jrocnc.cs3330.hw3;

// TODO: Auto-generated Javadoc
public class CircleCommand extends DIRCommand {
	
	// attributes
	//
	//
	//
	public Turn turn = null;

	
	// constructors
	//
	//
	//
	/**
	 * Instantiates a new circle command.
	 * 
	 * @post Instantiated CircleCommand object that will cause the plane
	 * it is commanding to circle counter clockwise.
	 */
	public CircleCommand() {
		super();
		turn = Turn.LEFT;
	}

	/**
	 * Instantiates a new circle command given an existing circle command.
	 *
	 * @param cc the existing circle command
	 * @pre an existing circle command object that could turn the plane
	 * counter/clockwise.
	 * @post Instantiated CircleCommand object that will have the same 
	 * turn direction as cc.
	 */
	public CircleCommand(CircleCommand cc) {
		super((DIRCommand) cc);
		if (cc.turn != null)
			turn = new Turn(cc.turn);
	}

	/**
	 * Instantiates a new circle command given a Turn obj.
	 *
	 * @param t the Turn obj.
	 * @pre t could be left or right.
	 * @post Instantiated CircleCommand object that will continuously turn the plane
	 * the direction indicated by t.
	 */
	public CircleCommand(Turn t) {
		super();
		if (t != null)
			turn = new Turn(t);
		active_flag = true;
	}

	/**
	 * Instantiates a new circle command given a Turn obj and a position.
	 *
	 * @param t the Turn obj
	 * @param p the position
	 * @pre t can be left or right and p can be any valid coordinates based on the 
	 * radar game field size.
	 * @post Instantiated CircleCommand centered around position p in the direction
	 * indicated by t
	 */
	public CircleCommand(Turn t, Position p) {
		super(p);
		if (t != null)
			turn = new Turn(t);
		active_flag = false;
	}
};
