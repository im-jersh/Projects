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
public class ALTCommand extends Command {
	
	// attributes
	//
	//
	//
	public int alt;

	
	// constructors
	//
	//
	//
	/**
	 * Instantiates a new ALT command.
	 * @post Instantiated active ALTCommand with altitude of 0.
	 */
	public ALTCommand() {
		super();
		alt = 0;
		active_flag = true;
	}

	/**
	 * Instantiates a new ALT command given an altitude. 
	 *
	 * @param a the altitude
	 * @post Instantiated active ALTCommand with altitude of 0.
	 */
	public ALTCommand(int a) {
		super();
		alt = a;
		active_flag = true;
	}

	/**
	 * Instantiates a new ALT command from existing ALT command.
	 *
	 * @param ac the existing ALT command
	 * @pre ao has an altitude field that will be copied to new ALTCommand.
	 * @post Instantiated ALTCommand with same altitude as ao.
	 */
	public ALTCommand(ALTCommand ac) {
		super((Command) ac);
		alt = ac.alt;
	}
};
