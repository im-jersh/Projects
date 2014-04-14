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
public class MovingObj extends ATCObj {
	
	// attributes
	//
	//
	//
	public Direction dir = null;
	public int inv_speed = 1;
	public int speed_count = 0;
	public int tick_count = 0;

	
	// constructors
	//
	//
	//
	/**
	 * Instantiates a new moving obj.
	 * 
	 * @post Instantiated MovingObj object.
	 */
	public MovingObj() {
		super();
	}

	/**
	 * Instantiates a new moving obj from existing moving obj.
	 *
	 * @param ao the existing moving obj.
	 * @pre ao is an existing ao object
	 * @post Instantiated MovingObj object copied from ao. 
	 */
	public MovingObj(MovingObj ao) {
		super((ATCObj) ao);
		if (ao != null) {
			inv_speed = ao.inv_speed;
			speed_count = ao.speed_count;
			if (ao.dir != null)
				dir = new Direction(ao.dir);
		}
	}

	/**
	 * Instantiates a new moving obj given a position, direction, altitude, and interval speed.
	 *
	 * @param p the position
	 * @param d the direction
	 * @param altitude the altitude
	 * @param i_speed the interval speed
	 * @pre p and d are valid coordinates on the radar game field. altitude is between 0 and 9
	 * (in thousands of feet). i_speed is the rate that this object will be updated at and is either 1 or 2. 
	 * @post Instantiated MovingObj object with field whose values are set to their respective parameters.
	 */
	public MovingObj(Position p, Direction d, int altitude, int i_speed) {
		super();
		if (p != null)
			pos = new Position(p);
		if (d != null)
			dir = new Direction(d);
		alt = altitude;
		inv_speed = i_speed;
	}
};
