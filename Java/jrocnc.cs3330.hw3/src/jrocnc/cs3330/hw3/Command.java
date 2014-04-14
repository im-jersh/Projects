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
public class Command extends Object {
	
	// attributes
	//
	//
	//
	public boolean active_flag;

	
	// constructors
	//
	//
	//
	/**
	 * Instantiates a new command.
	 * 
	 * @post Instantiated Command object.
	 */
	public Command() {
		super();
	}

	/**
	 * Instantiates a new command given an existing command.
	 *
	 * @param c the existing command
	 * @pre c is an existing command such as an altitude or direction
	 * @post Instantiated Command object whose active flag is set to c's active flag.
	 */
	public Command(Command c) {
		super();
		active_flag = c.active_flag;
	}
};
