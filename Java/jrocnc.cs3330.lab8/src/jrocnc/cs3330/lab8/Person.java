/**
 * Name: Joshua O'Steen
 * Date: 04/09/2014
 * Class: CS3330
 * Lab: 8
 * Section: D
 * Submission Code: GUI
 */

package jrocnc.cs3330.lab8;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author jrocnc
 *
 */
public class Person {
	
	// attributes
	private String name;
	private int age;
	private double bankAccountBalance;
	
	// constructor
	public Person(int age, String name, double balance){
		this.setName(name);
		this.setAge(age);
		this.setBalance(balance);
	}
	
	// methods
	private void setName(String name){
		this.name = name;
	}
	
	private void setAge(int age){
		this.age = age;
	}
	
	private void setBalance(double balance){
		this.bankAccountBalance = balance;
	}
	
	protected String getName(){
		return this.name;
	}
	
	protected int getAge(){
		return this.age;
	}
	
	protected double getBalance(){
		return this.bankAccountBalance;
	}
	
	@Override
	
	/* 
	 * in order to format the double value to normal notation for money
	 * I created an explicitly determined locale specific instance of the 
	 * NumberFormat class and passed it's .format method the double which collectively 
	 * returns a string formatted with two decimal places along with commas
	 * to separated every third power of ten
	 * 
	 * NOTE: this specific implementation should not be used where rounding 
	 * the hundredth cent is critical such as financial use
	 */
	public String toString(){
		return "Name: " + this.getName().replaceAll("\\s+", " ").trim() + "\nBank Balance: " + NumberFormat.getCurrencyInstance(Locale.US).format(this.getBalance()) + "\nAge: " + this.getAge() + "\n";
	}
}
