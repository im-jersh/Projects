/**
 * Name: Joshua O'Steen
 * Date: 03/17/2014
 * Class: CS3330
 * Lab: 6
 * Section: D
 * Submission Code: Bioprocess
 */
package jrocnc.cs3330.lab6;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;


/**
 * @author jrocnc
 *
 */
public class Database {
	
	// attributes
	private HashMap<String, Employee> empDB;
	private int counter;
	private static Scanner userInput;
	
	// constructor
	public Database(String fileName){
		userInput = new Scanner(System.in);
		this.importFile(fileName);
	}
	
	protected void importFile(String fileName){
		File empData = new File(fileName);
		Scanner fileScanner = null;
		empDB = new HashMap<String, Employee>();
		
		try{
			fileScanner = new Scanner(empData);
		}
		catch(IOException e){
			System.out.println("There is no record in your text file!");
			System.exit(0);
		}
		
		//
		
		String title, department = null, name, langProf = null ;
		int social, netWorth = 0;
		//Read until EOF reached
				while(fileScanner.hasNextLine() && empDB.size() < 10){
							
				//Parse comma separated string into a String array
					String[] parsedString = fileScanner.nextLine().split(",");
										
							//Passing array contents to variable to form shape objects
							title = parsedString[0];
							name = parsedString[1];
							social = Integer.parseInt(parsedString[2]);
							
							//Parse out the Don's information
							if(title.equals("CEO")){
								
								netWorth = Integer.parseInt(parsedString[3]);
							}
							else if(title.equals("Manager")){
								department = parsedString[3];
								
							}
							else if(title.equals("Developer")){
								langProf = parsedString[3];
							}
							
							//Add objects to the tree
							if(title.equals("CEO")){
								// add CEO
								empDB.put(title + name, new CEO(name, social, netWorth));
							}
							else if(title.equals("Manager")){
								// add Manager
								empDB.put(title + name, new Manager(name, social, department));
							}
							else if(title.equals("Developer")){
								// add Developer
								empDB.put(title + name, new Developer(name, social, langProf));
							}
				}
				System.out.println("There are possibly more data in the file, but we're skipping the rest...");
				System.out.println("Import Complete.\n");
		
	}
	
	public void searchMenu(){
		
		
		// loop while not "q"
		String queryString = null;
		
		while(true){
			System.out.println("Please enter your query (q to exit): ");
			this.userInput.next();
			
			if(queryString.equals("q"))
				break;
			
			// search database
			//this.searchDatabase(queryString);
		}
		
		
		
		
		
		
	}
	
	
	/*
	protected ArrayList<Employee> searchDatabase(String queryString){
		
	}
	*/

}
