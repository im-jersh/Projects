/**
 * Name: Joshua O'Steen
 * Class: CS 3330
 * Section: D
 * TA: Matthew England
 * Assignment: Homework 2
 * Due Date: 03/21/14
 * Objectives: 
 * 		Updating existing code base
 * 		Inheritance
 * 		Composition
 * 		Encapsulation
 * 		Polymorphism
 * 
 */
package jrocnc.cs3330.hw2;

import java.util.Random;

/**
 * @author Josh
 *
 */
public class Corporation extends Business{
	
	// attributes (all inherited)
	
	
	// constructor
	public Corporation(String name, String contact, Grid grid){
		// initialize using super constructor
		super(name, contact, grid);
	}
	
	// methods (all inherited)
	
	@Override
	protected int determineWorth(){
		// override Business.determineWorth method
		// initialize random number generator
		// return random number 20000-35000
		Random randomWorth = new Random();
		return randomWorth.nextInt(15001) + 20000;
	}
	
	@Override
	public String toString(){
		// override Object.toString() method and return String representation of Corporation object
		return "[Contact " + super.getContact() + "]  \tCorporation: " + super.getName();
 
	}

}
