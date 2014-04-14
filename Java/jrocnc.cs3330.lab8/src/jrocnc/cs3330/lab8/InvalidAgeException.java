/**
 * Name: Joshua O'Steen
 * Date: 04/09/2014
 * Class: CS3330
 * Lab: 8
 * Section: D
 * Submission Code: GUI
 */
package jrocnc.cs3330.lab8;

/**
 * @author jrocnc
 *
 */

@SuppressWarnings("serial")

public class InvalidAgeException extends Exception {

	// attributes
	String warning = null;
	
	// constructors
	public InvalidAgeException(){}
	public InvalidAgeException(String warning){
		this.setWarning(warning);
	}
	
	// methods
	private void setWarning(String warning){
		this.warning = warning;
	}
	
	protected String getWarning(){
		return this.warning;
	}
	
	// overriding the Throwable.toString method in order to only print
	// the formatted error to the user. Disregarding to print the system
	// exception although the documentation includes it since each type 
	// of user defined exception is handled within it's own respective
	// loop that continues to request valid input. It is understood however
	// that to get the documentation output one simply has to pass the warning
	// string in the constructor to the super class and then print the object 
	// using its default toString method
	@Override
	public String toString(){
		return this.getWarning();
	}
	
}
