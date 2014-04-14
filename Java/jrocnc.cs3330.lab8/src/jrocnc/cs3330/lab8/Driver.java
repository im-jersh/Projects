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
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.JOptionPane;



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
		Person person = null;
		//boolean nameControl = true;
		//boolean ageControl = true;
		//boolean balanceControl = true;
		boolean loopControl = true;
		
		
		
		// run loop to instantiate person objects and fill array list
		while(loopControl){
			
			// reset variables
			name = null;
			age = null;
			balance = null;
			
			
			//System.out.println("\n\nEnter person info or q to quit.");
			JOptionPane.showMessageDialog(null, "Enter person info or q to quit.");
			
			// name loop
			//while(nameControl){
				
				// get input
				//System.out.println("Please enter the name of this person: ");
				//name = input.nextLine();
				
				
			
				// verify valid name was entered
				// if not, print error and loop until valid name entered
				try{
					name = JOptionPane.showInputDialog("Please enter the name of this person: ");
					verify.isName(name);
				}
				catch (InvalidNameException e){
					//System.out.println(e);
					JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					continue;
				}
				
				
				if(name.equals("q")){
					loopControl = false;
					break;
				}
				
				// if control reaches this point, valid name was entered.
				// exit loop
				//nameControl = false;
			
			//}
			
			
			// break out of control loop if q was entered
			//if(name.equals("q"))
			//	break;
			
			
			// age loop
			//while(ageControl){
				
				// get age
				//System.out.println("Please enter an age for this person: ");
				//age = input.nextLine();
				
				// verify valid age was entered
				// if not, print error type and loop until valid age entered
				try{
					age = JOptionPane.showInputDialog("Please enter an age for this person: ");
					verify.isValidAge(age);
				}
				catch(InvalidAgeException e){
					//System.out.println(e);
					JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					continue;
				}
				
				// if control reaches this point, valid age was entered.
				// exit loop
				//ageControl = false;
				
			//}
			
			
			
			// balance loop
			//while(balanceControl){
				
				// get balance
				//System.out.println("Please enter a bank account balance for this person: ");
				//balance = input.nextLine();
				
				// verify valid balance was entered
				// if not, print error type and loop until valid age entered
				try{
					balance = JOptionPane.showInputDialog("Please enter a bank account balance for this person: ");
					verify.isBalance(balance);
				}
				catch(InvalidBalanceException e){
					//System.out.println(e);
					JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					continue;
				}
				
				// if control reaches this point, valid balance was entered.
				// exit loop
				//balanceControl = false;
				
			//}
			
			
			// control has determined by this point that all user input is valid.
			// input can be used to instantiate a person object and can be added
			// to the array list.
			person = new Person(Integer.parseInt(age), name, Double.parseDouble(balance));
			pplArrayList.add(person);
			JOptionPane.showMessageDialog(null, person.getName() + " aged " + person.getAge() + " with balance " + NumberFormat.getCurrencyInstance(Locale.US).format(person.getBalance()) + " has been added to your list.");
			
			
			//nameControl = true;
			//ageControl = true;
			//balanceControl = true;
			
		}
		
		input.close();
		
		if(pplArrayList.size() > 0){
			// print array list
			StringBuilder output = new StringBuilder();

			for(Person p : pplArrayList){
				output.append("\n\n" + p);
			}
			JOptionPane.showMessageDialog(null, "Person List:" + output);
		}
		else
			JOptionPane.showMessageDialog(null, "Your person list is empty.");

		
		
		
		
	}
	
	
}
