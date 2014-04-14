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
public class ToolKit {

	// attributes
	private ArrayList<Tool> tools;
	
	// constructor
	public ToolKit(){
		// initialize toolkit
		this.loadTools();
	}
	
	// methods
	private void loadTools(){
		// file name and class
			String toolsFileName = "tools.txt";
			File toolsDataFile = new File(toolsFileName);
			Scanner toolsScanner = null;
				
			// catch file open error
			try{
				toolsScanner = new Scanner(toolsDataFile);
			}
			catch(IOException e){
				System.out.println("Error: tools file not found.");
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
	
	public void displayTools(){
		// iterate through array and print information
		for(int index = 0; index < tools.size(); index++){
			System.out.println((index+1) + ") " + tools.get(index));
		}
		
	}
	
	public Tool getTool(int tool_index){
		// return tool object in arraylist at tool_index
		return this.tools.get(tool_index);
	}
	
	public int getNumberOfTools(){
		// return number of tool objects in arraylist
		return this.tools.size();
	}
	
	public void addOrUpdateTool(Tool incoming_tool){
		// loop through tools arrayList to see if incoming_tool already exists
		boolean exists = false;
		for(int i = 0; i < this.tools.size(); i++){
			// tool was found, update it
			if(this.tools.get(i).getName().equals(incoming_tool.getName())){
				exists = true;
				this.tools.get(i).updateStrength(incoming_tool.getStrength());
				this.tools.get(i).updateCost(incoming_tool.getCost());
				break;
			}
		}
		
		// tool wasn't found, add it to existing tools arrayList
		if(!exists)
			this.tools.add(incoming_tool); 
	}
	
}
