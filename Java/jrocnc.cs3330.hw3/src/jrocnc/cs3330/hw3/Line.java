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
public class Line extends StaticObj {
	
	// attributes
	//
	//
	//
	public Position second_end = null;

	
	// constructors
	//
	//
	//
	/**
	 * Instantiates a new line.
	 * 
	 * @post Instantiated Line object
	 */
	public Line() {
		super();
	}

	/**
	 * Instantiates a new line from an existing line object.
	 *
	 * @param ao the existing line object
	 * @pre ao is an existing Line object
	 * @post Instantiated Line object copied from ao.
	 */
	public Line(Line ao) {
		super((StaticObj) ao);
		if (ao.second_end != null)
			second_end = new Position(ao.second_end);
	}

	/**
	 * Instantiates a new line given to points on the game field.
	 *
	 * @param p1 the first position
	 * @param p2 the second position
	 * @pre p1 and p2 are the end points (x,y) of the line to be made on the game field.
	 * @post Instantiated Line object with starting point at p1 and end point at p2
	 */
	public Line(Position p1, Position p2) {
		super(p1);
		if (p2 != null)
			second_end = new Position(p2);
	}
};
