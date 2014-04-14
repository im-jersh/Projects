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
public class Exit extends StaticObj {
	
	// constructors
	//
	//
	//
	/**
	 * Instantiates a new exit with altitude of 9.
	 * 
	 * @post instantiated Exit object with altitude of 9000ft.
	 */
	protected Exit() {
		super();
		alt = 9;
	}

	/**
	 * Instantiates a new exit given an existing exit object.
	 *
	 * @param ao the existing exit object.
	 * @pre ao is an existing Exit object
	 * @post New exit object copied from ao with altitude of 7 and exit altitude of 9.
	 */
	public Exit(Exit ao) {
		super((StaticObj) ao);
		alt = 7;
		exit_alt = 9;
	}

	/**
	 * Instantiates a new exit given position and direction.
	 *
	 * @param p the position
	 * @param d the direction
	 * @pre Position is (x,y) point on the coordinate game field and 
	 * direction is the direction the plane must be traveling.
	 * @post New exit object at position p, with direction d, altitude
	 * of 7, exit altitude of 9, and a null exit direction.
	 */
	public Exit(Position p, Direction d) {
		super(p, d);
		alt = 7;
		exit_alt = 9;
		exit_dir = null;
	}

	
	// methods
	//
	//
	//
	/**
	 * Gets this exit object's name.
	 */
	public String getName() {
		return new String("E") + id;
	}
};
