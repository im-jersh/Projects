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
public class Airfield extends StaticObj {
	
	// constructors
	//
	//
	//
	/**
	 * Instantiates a new airfield.
	 * @post Instantiated airfield.
	 */
	protected Airfield() {
		super();
	}

	/**
	 * Instantiates a new airfield from existing airfield.
	 *
	 * @param ao the existing airfield
	 * @post Newly instantiated Airfield object with altitude of zero
	 * and exit altitude of 0.
	 */
	public Airfield(Airfield ao) {
		super((StaticObj) ao);
		alt = 0;
		exit_alt = 0;
	}

	/**
	 * Instantiates a new airfield given a position and direction.
	 *
	 * @param p the position
	 * @param d the direction
	 * @pre p is the position (x,y) on the radar that the airfield is located
	 * and d is the direction
	 * @post New Airfield object has position p, direction d, zero altitude,
	 * and zero exit altitude.
	 */
	public Airfield(Position p, Direction d) {
		super(p, d);
		alt = 0;
		exit_alt = 0;
	}

	
	// methods
	//
	//
	//
	/**
	 * Gets the airfield name.
	 */
	public String getName() {
		return new String("A") + id;
	}
};
