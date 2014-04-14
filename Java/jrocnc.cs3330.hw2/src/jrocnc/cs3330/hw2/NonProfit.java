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

/**
 * @author Josh
 *
 */
public class NonProfit extends Business{

	// attributes (all inherited)
	
	
	// constructor
	public NonProfit(String name, String contact, Grid grid){
		// intialize using super constructor
		super(name, contact, grid);
	}
	
	// methods (all inherited)
	
	@Override
	public String toString(){
		// override Object.toString() method and return String representation of Non-profit object
		return "[Contact " + super.getContact() + "]  \tNon-profit: " + super.getName();
	}
	
}
