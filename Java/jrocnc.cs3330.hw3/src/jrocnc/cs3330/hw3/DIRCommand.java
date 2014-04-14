/*
 * 	Joshua O'Steen
 * 	CS 3330 - Guilliams, England
 * 	Homework 3
 * 	04/10/2014
 * 	UML, Machine State, Javadoc
 */


package jrocnc.cs3330.hw3;

import java.lang.Object;

// TODO: Auto-generated Javadoc
public class DIRCommand extends Command {
	
	// attributes
	//
	//
	//
	public Position pos = null;
	public StaticObj pos_obj = null;

	
	// constructors
	//
	//
	//
	/**
	 * Instantiates a new DIR command.
	 * 
	 * @post Instantiated active DIRCommand object.
	 */
	public DIRCommand() {
		super();
		active_flag = true;
	}

	/**
	 * Instantiates a new DIR command given an existing DIR command.
	 *
	 * @param dc the existing DIR command
	 * @pre dc is an existing DIRCommand object (a turn or circle command)
	 * @post Instantiated DIRCommand whose position is that of dc's position.
	 */
	public DIRCommand(DIRCommand dc) {
		super((Command) dc);
		if (dc.pos != null)
			pos = new Position(dc.pos);
	}

	/**
	 * Instantiates a new DIR command given a position.
	 *
	 * @param p the position
	 * @pre p is the position of the DIRCommand
	 * @post Instantiated DIRCommand object whose position is p.
	 */
	public DIRCommand(Position p) {
		super();
		if (p != null)
			pos = new Position(p);
	}
};
