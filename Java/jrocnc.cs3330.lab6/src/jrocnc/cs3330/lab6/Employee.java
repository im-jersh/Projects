/**
 * Name: Joshua O'Steen
 * Date: 03/17/2014
 * Class: CS3330
 * Lab: 6
 * Section: D
 * Submission Code: Bioprocess
 */
package jrocnc.cs3330.lab6;

/**
 * @author jrocnc
 *
 */
public class Employee {
	
	// attributes
	private String name;
	private int SSN;
	
	// constructor
	public Employee(String name, int SSN){
		this.setName(name);
		this.setSSN(SSN);
	}

	// methods
	protected void setName(String name){
		this.name = name;
	}
	
	protected void setSSN(int SSN){
		this.SSN = SSN;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getSSN(){
		return this.SSN;
	}
}
