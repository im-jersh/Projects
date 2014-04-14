/**
 * Joshua O'Steen
 * 02/10/2014
 * section D
 * lab 2
 * string object
 * Sunday Funday
 */
package jrocnc.cs3330.lab2;

/**
 * @author jrocnc
 *
 */
public class Person {

	// attributes
	private String name;
	
	// methods
	public Person(String name){
		setName(name);
	}
	
	public String getName(){
		return this.name;
	}
	
	private void setName(String name){
		this.name = name;
	}
}
