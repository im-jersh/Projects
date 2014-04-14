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
public class Player{

	// attributes
	private String name;
	private int health;
	private int money;
	
	// constructor
	public Player(String name){
		// set player, money and health 
		this.setName(name);
		this.setMoney(20000);
		this.setHealth(100);
	}
		
	// methods
	private void setName(String name){
		// set private name attribute
		this.name = name;
	}
	
	private void setHealth(int health){
		// set private health attribute
		this.health += health;
	}
	
	private void setMoney(int money){
		// set private money attribute
		this.money += money;
	}
	
	public void updateMoney(int money){
		// update private money attribute
		setMoney(money);
	}
	
	public void updateHealth(int health){
		// update private health attribute
		setHealth(health);
	}
	
	public String getName(){
		// return private name attribute
		return this.name;
	}
	
	public int getHealth(){
		// return private health attribute
		return this.health;
	}
	
	public int getMoney(){
		// return private money attribute
		return this.money;
	}
	
	@Override
	public String toString(){
		// override Object.toString method and return String representation of player object
		return "Hacker " + getName() + " with health of " + getHealth() + " with " + getMoney() + " yen";
	}

}
