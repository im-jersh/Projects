/*
 * 	Joshua O'Steen
 * 	CS 3330 - Guilliams, England
 * 	Homework 3
 * 	04/10/2014
 * 	UML, Machine State, Javadoc
 */


package jrocnc.cs3330.hw3;

import java.awt.Point;
import java.lang.Math;

// TODO: Auto-generated Javadoc
public class Position extends Point {
	
	// constructors
	//
	//
	//
	/**
	 * Instantiates a new position.
	 * 
	 * @post Instantiated Position object
	 */
	public Position() {
		super();
	}

	/**
	 * Instantiates a new position from existing position.
	 *
	 * @param p the existing position
	 * @pre p is a valid coordinate on the radar game field. 
	 * @post Instantiated Position copied from p.
	 */
	public Position(Position p) {
		super((Point) p);
	}

	/**
	 * Instantiates a new position given x and y coordinates.
	 *
	 * @param a the x coordinate
	 * @param b the y coordinate
	 * @pre x and y represent a valid x/y coordinate on the radar game field.
	 * @post Instantiated Position object whose x/y values are set to a/b
	 */
	public Position(int a, int b) {
		super(a, b);
	}

	
	// methods
	//
	//
	//
	/**
	 * Updates the current x and y coordinates with those of the new direction
	 * supplied by a turn command.
	 *
	 * @param dir the direction to turn
	 * @pre dir contains x and y values that can be any combination of -1, 0, and 1.
	 * @post This object's x and y fields are incremented by the corresponding x and
	 * y fields of dir.
	 */
	public void tick(Direction dir) {
		if (dir == null)
			return;
		x += dir.x;
		y += dir.y;
	}

	/**
	 * Checks if this object is colliding with another object in the same position.
	 *
	 * @param pos the position to compare to
	 * @return true, if it is colliding with another object; otherwise false.
	 * @pre pos contains x and y fields that are within the field of play.
	 * @post a determination of adjacency to another object is established.
	 */
	public boolean isColliding(Position pos) {
		if (pos == null)
			return false;
		return Math.abs(pos.x - x) <= 1 && Math.abs(pos.y - y) <= 1;
	}
};
