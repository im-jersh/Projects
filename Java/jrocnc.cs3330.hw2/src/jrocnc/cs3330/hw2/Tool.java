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
public class Tool{

	// attributes
	private String name;
	private String type;
	private int cost;
	private int strength;
	
	// constructor
	public Tool(String name, String type, int cost, int strength){
		// set name, type, cost and strength
		setName(name);
		setType(type);
		setCost(cost);
		setStrength(strength);
	}
	
	// methods
	private void setName(String name){
		// set private name attribute
		this.name = name;
	}
	
	private void setType(String type){
		// set private type attribute
		this.type = type;
	}
	
	private void setCost(int cost){
		// set private cost attribute
		this.cost += cost;
	}
	
	private void setStrength(int strength){
		// set private strength attribute
		this.strength += strength;
	}
	
	public void updateStrength(int additional_strength){
		// update private strength attribute
		setStrength(additional_strength);
	}
	
	public void updateCost(int additional_cost){
		// update private cost attribute
		this.setCost(additional_cost);
	}
	
	public String getName(){
		// return private name attribute
		return this.name;
	}
	
	public String getType(){
		// return private type attribute
		return this.type;
	}
	
	public int getCost(){
		// return private cost attribute
		return this.cost;
	}
	
	public int getStrength(){
		// return private strength attribute
		return this.strength;
	}
	
	@Override
	public String toString(){
		// override Object.toString() method and return String representation of tool object
		return getType() + " tool named " + getName() + " with " + getStrength() + " strength and a cost of " + getCost() + ".";
	}
	
	
	
}
