/**
 * 
 */
package jrocnc.cs3330.hw1;

/**
 * @author Josh
 *
 */
public class Server{
	
	// attributes
	private String name, type;
	private int strength;
	
	// constructor
	public Server(String name, String type, int strength){
		// set name, strength and type
		this.setName(name);
		this.setStrength(strength);
		this.setType(type);
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
	
	private void setStrength(int strength){
		// set private strength attribute
		this.strength += strength;
	}
	
	public void updateStrength(int additional_strength){
		// update private strength attribute
		setStrength(additional_strength);
	}
	
	public String getName(){
		// return private name attribute
		return this.name;
	}
	
	public String getType(){
		// return private type attribute
		return this.type;
	}
	
	public int getStrength(){
		// return private strength attribute
		return this.strength;
	}
	
	@Override
	public String toString(){
		// override Object.toString method and return String representation of server object
		return "SERVER INFO: " + getType() + " server with address of " + getName() + " with " + getStrength() + " strength.";
	}
	

}
