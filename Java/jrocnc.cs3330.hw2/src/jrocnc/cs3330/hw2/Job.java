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
public class Job {
	
	// attributes
	private String target_address;
	private int reward;
	
	
	// constructor
	public Job(String target_address, int reward){
		// set reward and target address
		setReward(reward);
		setTargetAddress(target_address);
	}
	
	// methods
	private void setReward(int reward){
		// set private reward attribute
		this.reward = reward;
	}
	
	private void setTargetAddress(String target_address){
		// set private target_address attribute
		this.target_address = target_address;
	}
	
	public int getReward(){
		// return private address attribute
		return this.reward;
	}
	
	public String getTargetAddress(){
		// return private target_address attribute
		return this.target_address;
	}
	
	@Override
	public String toString(){
		// override Object.toString() method and return String representation of Job object
		return this.getTargetAddress() + " worth " + this.getReward();
	}

}
