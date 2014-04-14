/**
 * 
 */
package jrocnc.cs3330.hw1;

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

		// iterate through array and print
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
	
	public void updateTool(int tool_index, int additional_strength){
		// update tool in arraylist at tool_index by additional_strength 
		this.tools.get(tool_index).updateStrength(additional_strength);
	}
	
}
