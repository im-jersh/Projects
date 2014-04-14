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
import java.lang.String;
import java.lang.Character;

// TODO: Auto-generated Javadoc
public class Direction extends Point {
	
	// constructors
	//
	//
	//
	/**
	 * Instantiates a new direction given x and y integer values.
	 *
	 * @param a the x coordinate
	 * @param b the y coordinate
	 * @pre valid coordinates on the radar game field 
	 * @post Instantiated Direction object with with x/y values of a/b
	 */
	public Direction(int a, int b) {
		super(a, b);
		normalize();
	}

	/**
	 * Instantiates a new direction given an existing direction object.
	 *
	 * @param d the existing direction object.
	 * @pre d is an existing direction whose x/y values are valid coordinates
	 * on the radar game field.
	 * @post Instantiated Direction object with the same x/y values as d
	 */
	public Direction(Direction d) {
		super((Point) d);
		normalize();
	}

	/**
	 * Instantiates a new direction object given a starting position
	 * and a destination position on the game field.
	 *
	 * @param from the starting position
	 * @param to the destination position
	 * @pre from and to are valid x/y coordinates on the radar game field.
	 * @post Instantiated Direction object whose x/y values are based off of
	 * those of from and to. 
	 */
	public Direction(Position from, Position to) {
		super();
		int dx, dy;
		dx = to.x - from.x;
		dy = to.y - from.y;
		if (0.4 * Math.abs(dx) > Math.abs(dy))
			dy = 0;
		if (0.4 * Math.abs(dy) > Math.abs(dx))
			dx = 0;

		x = dx;
		y = dy;
		normalize();
	}

	
	// methods
	//
	//
	//
	/**
	 * Gets the reverse direction of this object's direction coordinates.
	 *
	 * @return the reverse (x,y) coordinates of this object, namely (-x,-y).
	 * @post Instantiated Direction object who's x/y values are the negated values
	 * of this object's x/y values.
	 */
	public Direction getReverse() {
		return new Direction(-x, -y);
	}

	/**
	 * Updates this object's (x,y) coordinates given a turn object. This method
	 * is run nearly concurrently with ATCData.tick method and is only called
	 * when a new direction command is given to a plane.
	 *
	 * @param t the turn object containing values to update this object's (x,y)
	 * coordinates.
	 * @pre The turn object contains a matrix containing 4 integer values between
	 * the -1 and 1.
	 * @post This object's x and y values are updated to reflect the turn command.
	 */
	public void tick(Turn t) {
		if (t == null)
			return;
		int new_x, new_y;
		new_x = t.matrix[0][0] * x + t.matrix[0][1] * y;
		new_y = t.matrix[1][0] * x + t.matrix[1][1] * y;
		x = new_x;
		y = new_y;
		normalize();
	}

	/**
	 * Gets the direction's name.
	 *
	 * @return the direction name given in cardinal or ordinal directions
	 */
	public String getDirName() {
		String ret_name = new String("");
		if (y != 0)
			ret_name += y > 0 ? "S" : "N";
		if (x != 0)
			ret_name += x > 0 ? "E" : "W";
		return ret_name;
	}

	/**
	 * Creates a new direction object based off of user input 'wedcxzaq', 
	 * representing the cardinal and ordinal directions relative to the 's' button
	 * on the keyboard.
	 *
	 * @param c the user input character
	 * @return the direction to turn
	 * @pre c can be any character within 'wedcxzaq'
	 * @post Instantiated Direction object representing a cardinal or ordinal direction
	 */
	public static Direction charToDir(char c) {
		switch (Character.toLowerCase(c)) {
		case 'a':
			return new Direction(-1, 0);
		case 'w':
			return new Direction(0, -1);
		case 'd':
			return new Direction(1, 0);
		case 'x':
			return new Direction(0, 1);
		case 'q':
			return new Direction(-1, -1);
		case 'e':
			return new Direction(1, -1);
		case 'c':
			return new Direction(1, 1);
		case 'z':
			return new Direction(-1, 1);
		}
		return null;
	}

	/**
	 * Normalizes this object's x and y values either to 1 or -1 respectively.
	 */
	protected void normalize() {
		if (x != 0)
			x = x > 0 ? 1 : -1;
		if (y != 0)
			y = y > 0 ? 1 : -1;
	}
};
