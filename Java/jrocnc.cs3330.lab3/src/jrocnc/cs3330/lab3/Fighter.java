/**
 *@author jrocnc
 * Name: Joshua O'Steen
 * Version: 1.0
 * LAB: 3
 * Section: D
 * Date: 02/017/14
 * Description: ArrayList
 * s. code: 
 * submission receipt:
 */

package jrocnc.cs3330.lab3;

/**
 * 
 *
 */
public class Fighter {

	// attributes
	private String name;
	private int healthPoints;
	private boolean isAlive;
	
	// constructor
	public Fighter(String name, int health){
		setName(name);
		setHealth(health);
		setAlive(true);
	}
	
	// getters and setters
	private void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	private void setHealth(int health){
		
		this.healthPoints += health;
		
		// check health
		if(this.getHealth() <= 0){
			this.die();
		}
	}
	
	public int getHealth(){
		return this.healthPoints;
	}
	
	private void setAlive(boolean status){
		this.isAlive = status;
	}
	
	public boolean getAlive(){
		return this.isAlive;
	}
	
	public void beingHit(int distance){
		
		// determine hit points based off distance
		if(distance <= 5){
			System.out.println(this.getName() + ": I was hit for 30 points of damage.");
			this.setHealth(-30);
		}
		else if(distance > 5 && distance < 8){
			System.out.println(this.getName() + ": I was hit for 10 points of damage.");
			this.setHealth(-10);
		}
		else{
			System.out.println(this.getName() + ": I dodged!");
			this.setHealth(0);
		}
	}
	
	private void die(){
		// set dead
		this.setAlive(false);
		System.out.println(this.getName() +" has died.");
	}
	
	public String toString(){
		return "My name is " + this.getName() + "! I've " + this.getHealth() + " health points remaining\n";
	}

	
}
