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
public class Beacon extends StaticObj {
	
	// constructors
	//
	//
	//
	/**
	 * Instantiates a new beacon.
	 * 
	 * @post Instantiated Beacon object.
	 */
	public Beacon() {
		super();
	}

	/**
	 * Instantiates a new beacon from an existing beacon. 
	 *
	 * @param ao the existing beacon object.
	 * @pre ao is an existing beacon object.
	 * @pre Instantiated beacon object based off of ao.
	 */
	public Beacon(Beacon ao) {
		super((StaticObj) ao);
	}

	/**
	 * Instantiates a new beacon given a position.
	 *
	 * @param p the position (x,y) on the game field. 
	 * @pre coordinates for the beacon are supplied.
	 * @post Instantiated beacon with position p.
	 */
	public Beacon(Position p) {
		super(p);
	}

	
	// methods
	//
	//
	//
	/**
	 * Gets this beacon object's name. 
	 */
	public String getName() {
		return new String("B") + id;
	}
};
