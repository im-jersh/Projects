/**
 * Name: Joshua O'Steen
 * Date: 03/31/2014
 * Class: CS3330
 * Lab: 7
 * Section: D
 * Submission Code: Opening Day
 */
package jrocnc.cs3330.lab7;

import java.util.ArrayList;
import java.util.Scanner;



/**
 * @author jrocnc
 *
 */
public class Driver{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		// variables
		String name = null;
		String age = null;
		String balance = null;
		ArrayList<Person> pplArrayList = new ArrayList<Person>();
		Scanner input = new Scanner(System.in);
		Validator verify = new Validator();
		boolean nameControl = true;
		boolean ageControl = true;
		boolean balanceControl = true;
		boolean loopControl = true;
		
		
		
		// run loop to instantiate person objects and fill array list
		while(loopControl){
			
			System.out.println("\n\nEnter person info or q to quit.");
			
			// name loop
			while(nameControl){
				
				// get input
				System.out.println("Please enter the name of this person: ");
				name = input.nextLine();
				
				if(name.equals("q")){
					loopControl = false;
					break;
				}
			
				// verify valid name was entered
				// if not, print error and loop until valid name entered
				try{
					verify.isName(name);
				}
				catch (InvalidNameException e){
					System.out.println(e);
					continue;
				}
				
				// if control reaches this point, valid name was entered.
				// exit loop
				nameControl = false;
			
			}
			
			
			// break out of control loop if q was entered
			if(loopControl == false)
				break;
			
			
			// age loop
			while(ageControl){
				
				// get age
				System.out.println("Please enter an age for this person: ");
				age = input.nextLine();
				
				// verify valid age was entered
				// if not, print error type and loop until valid age entered
				try{
					verify.isValidAge(age);
				}
				catch(InvalidAgeException e){
					System.out.println(e);
					continue;
				}
				
				// if control reaches this point, valid age was entered.
				// exit loop
				ageControl = false;
				
			}
			
			
			
			// balance loop
			while(balanceControl){
				
				// get balance
				System.out.println("Please enter a bank account balance for this person: ");
				balance = input.nextLine();
				
				// verify valid balance was entered
				// if not, print error type and loop until valid age entered
				try{
					verify.isBalance(balance);
				}
				catch(InvalidBalanceException e){
					System.out.println(e);
					continue;
				}
				
				// if control reaches this point, valid balance was entered.
				// exit loop
				balanceControl = false;
				
			}
			
			
			// control has determined by this point that all user input is valid.
			// input can be used to instantiate a person object and can be added
			// to the array list.
			pplArrayList.add(new Person(Integer.parseInt(age), name, Double.parseDouble(balance)));
			
			// reset variables
			name = null;
			age = null;
			balance = null;
			nameControl = true;
			ageControl = true;
			balanceControl = true;
			
		}
		
		input.close();
		
		// print array list
		for(Person p : pplArrayList){
			System.out.println(p);
		}
		
		
	}
	
	
}
