/**
 * Joshua O'Steen
 * 02/10/2014
 * section D
 * lab 2
 * string object
 * Sunday Funday
 */
package jrocnc.cs3330.lab2;

import java.util.Scanner;


/**
 * @author jrocnc
 *
 */
public class LabTwoDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// create scanner class
		Scanner scanner = new Scanner(System.in);
		
		int numberOfSmiths = 0;
		
		// request input
		System.out.println("How many people?");
		int entries = Integer.parseInt(scanner.nextLine());
		
		//Create an array of objects of type Person. 
		Person[] people = new Person[entries];
		
		// loop through array
		for(int i = 1; i <= entries; i++){
			System.out.print("Name of Person " + i + ": ");
			String name = scanner.nextLine();
			
			// create Person object
			people[i-1] = new Person(name);
			System.out.println();
			
		}// end for loop
		
		// loop through array using String class methods
		for(int j = 1; j <= entries; j++){
			
			// check for prefix
			if(people[j-1].getName().startsWith("Dr.")){
				System.out.printf("\nHello %s, congratulation on the Ph.D!", people[j-1].getName());
			}
			else if(people[j-1].getName().startsWith("Mr.")){
				System.out.printf("\nHello, %s.\n%s begins with a Mr.", people[j-1].getName(),people[j-1].getName());
			}
			else if(people[j-1].getName().startsWith("Mrs.")){
				System.out.printf("\nHello, %s.\n%s begins with a Mrs.", people[j-1].getName(), people[j-1].getName());
			}
			else if(people[j-1].getName().startsWith("Ms.")){
				System.out.printf("\nHello, %s.\n%s begins with a Ms.", people[j-1].getName(), people[j-1].getName());
			}
			else{
				System.out.printf("\nHello, %s", people[j-1].getName());
			}
			
			// check name length
			if(people[j-1].getName().length() < 5){
				System.out.printf("\nYou have a short name %s!",  people[j-1].getName());
			}
			else if(people[j-1].getName().length() > 15){
				System.out.printf("\nYou have a long name %s!",  people[j-1].getName());
			}
			
			// check for Smith
			if(people[j-1].getName().contains("Smith")){
				numberOfSmiths++;
			}
			
			System.out.println();
			
		}// end for loop
		
		// number of smiths
		System.out.printf("\nThere were %d Smiths in this array.", numberOfSmiths);
		
		//close scanner
		scanner.close();
		
		// ends successfully
		System.exit(0);
	
	}// end main

}// end class
