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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Josh
 *
 */
public class BlackMarket extends Business{

	// attributes 
	private ArrayList<Tool> tools;
	private static Scanner input = new Scanner(System.in);
	private Player buyer;
	
	// attributes not in the UML diagram
	// added for ease of use
	String toolsToBuyFile = "tools_to_buy.txt";
	
	
	// constructor
	public BlackMarket(String name, String contact, Grid grid, Player buyer){
		// initialize using super constructor
		// set buyer
		// initialize tools arrayList using BlackMarket.loadTools()
		super(name, contact, grid);
		this.setBuyer(buyer);
		this.loadTools();
		
	}
	
	// methods
	private void setBuyer(Player buyer){
		// set private buyer attribute
		this.buyer = buyer;
	}
	
	private void loadTools(){
		// file name and class
		File toolsToBuyDataFile = new File(toolsToBuyFile);
		Scanner toolsScanner = null;
				
		// catch file open error
		try{
			toolsScanner = new Scanner(toolsToBuyDataFile);
		}
		catch(IOException e){
			System.out.println("Error: server file not found.");
			System.exit(0);
		}
				
		// instantiate arraylist and string array to hold line
		tools = new ArrayList<Tool>();
		String[] toolsLine;
				
		// fill array line by line until end of file reached
		while(toolsScanner.hasNextLine()){	
			// get next line, store comma separated tokens in string array
			toolsLine = toolsScanner.nextLine().split(",");
					
			// send values to instantiate new server object, store in array list
			tools.add(new Tool(toolsLine[0], toolsLine[1], Integer.parseInt(toolsLine[2]), Integer.parseInt(toolsLine[3]) ));
					
		}
		
	}
	
	public void viewToolsDatabase(){
		// iterate through tools arrayList and print available tools to buy
		for(int index = 0; index < tools.size(); index++){
			System.out.println("PN:"+ (index+1) + "  [" + tools.get(index) + "]");
		}
	}
	
	public Tool buyATool(){
		// view tools to buy using BlackMarket.viewToolsDatabase()
		// get choice from player
		System.out.println(buyer);
		System.out.println("Welcome to the Black Market");
		this.viewToolsDatabase();
		System.out.println("To cancel purchase enter -1\nSelection: ");
		int choice = input.nextInt();
		
		
		//----------------------------------------------------------------------------//
		
		
		// buyer cancelled purchase, return null
		if(choice == -1){
			System.out.println("No tool bought");
			return null;
		}
		
		
		//----------------------------------------------------------------------------//
		
		
		// error check choice to make sure within valid range of arrayList bounds
		while(choice != -1 && (choice < 1 || choice > this.tools.size())){
			System.out.println("Option invalid, pick again: ");
			choice = input.nextInt();
		}
		
		
		//----------------------------------------------------------------------------//

		
		// error check buyer's balance to make sure chosen tool can be afforded
		while(choice != -1 && (this.buyer.getMoney() < this.tools.get(choice-1).getCost())){
			// insufficient funds to purchase tool
			// repeat selection process until affordable tool is chosen
			// or until purchase is cancelled
			System.out.println("Insufficient funds\nPick again: ");
			choice = input.nextInt();
			
			// error check choice to make sure within valid range of arrayList bounds
			while(choice != -1 && (choice < 1 || choice > this.tools.size())){
				System.out.println("Option invalid, pick again: ");
				choice = input.nextInt();
			}
			
			// buyer cancelled purchase, return null
			if(choice == -1){
				System.out.println("No tool bought");
				return null;
			}
		}
		
		
		//----------------------------------------------------------------------------//

		
		// buyer cancelled purchase, return null
		if(choice == -1){
			System.out.println("No tool bought");
			return null;
		}
		
		
		//----------------------------------------------------------------------------//
		
		
		// subtract tool cost from buyer's balance using Player.updateMoney()
		this.buyer.updateMoney(this.tools.get(choice-1).getCost() * -1);
					
		// return a copy of selected to be added or updated in ToolKit class
		return new Tool(this.tools.get(choice-1).getName(), this.tools.get(choice-1).getType(), this.tools.get(choice-1).getCost(), this.tools.get(choice-1).getStrength());
		
	}
	
	@Override
	public String toString(){
		// override Object.toString() method and return String representation of BlackMarket object
		return "[Contact " + super.getContact() + "]\tBlack Market: " + super.getName();
	}
}
