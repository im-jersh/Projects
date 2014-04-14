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
public class StaticObj extends ATCObj {
	
	// attributes
	//
	//
	//
	public Direction dir = null;
	public Direction exit_dir = null;
	public int exit_alt = 9;

	
	// constructors
	//
	//
	//
	/**
	 * Instantiates a new static obj.
	 * 
	 * @post Instantiated StaticObj object.
	 */
	protected StaticObj() {
		super();
	}

	/**
	 * Instantiates a new static obj from an existing static object.
	 *
	 * @param ao the existing static object
	 * @pre ao is an existing StaticObj object
	 * @post Instantiated StaticObj object copied from ao.
	 */
	public StaticObj(StaticObj ao) {
		super((ATCObj) ao);
		if (ao.dir != null) {
			dir = new Direction(ao.dir);
			exit_dir = new Direction(ao.dir);
		}
	}

	/**
	 * Instantiates a new static obj given a position on the game field. 
	 *
	 * @param p the position the static object should be located on the game field
	 * @pre p is a valid coordinate on the radar game field.
	 * @post Instantiated StaticObj object with altitude 0 and position p.
	 */
	protected StaticObj(Position p) {
		super();
		alt = 0;
		if (p != null)
			pos = new Position(p);
	}

	/**
	 * Instantiates a new static obj given a position and direction.
	 *
	 * @param p the position the object should be located on the game field
	 * @param d the direction the object is facing
	 * @pre p and d are valid coordinates on the radar game field.
	 * @post Instantiated StaticObj object with position p facing direction d.
	 */
	public StaticObj(Position p, Direction d) {
		super();
		alt = 0;
		if (p != null)
			pos = new Position(p);
		if (d != null) {
			dir = new Direction(d);
			exit_dir = new Direction(d);
		}
	}

	
	// methods
	//
	//
	//
	/**
	 * Gets the name of this object
	 *
	 * @return the name
	 */
	public String getName() {
		return new String("O") + id;
	}
};
