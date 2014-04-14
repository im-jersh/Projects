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
public class ATCObj extends Object {

	// attributes
	//
	//
	//
	public boolean changed_flag = false;
	public int id;
	public int alt = 0;
	public Position pos = null;

	
	// constructors
	//
	//
	//
	/**
	 * Instantiates a new ATC obj.
	 * 
	 * @post instantiated ATCObj obj.
	 */
	public ATCObj() {
		super();
	}

	/**
	 * Instantiates a new ATC obj.
	 *
	 * @param ao the existing ATCObj object
	 * @post Instantiated ATCObj with the same field values as ao.
	 */
	public ATCObj(ATCObj ao) {
		super();
		changed_flag = ao.changed_flag;
		id = ao.id;
		alt = ao.alt;
		if (ao.pos != null)
			pos = new Position(ao.pos);
	}
};
