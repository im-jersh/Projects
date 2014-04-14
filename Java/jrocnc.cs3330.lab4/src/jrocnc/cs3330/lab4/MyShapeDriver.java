/**
 * Name: Josh O'steen
 * Lab: 4
 * Section: D
 * Date: 02/25/2014
 * Description: Shapes
 * Sub Code: Russian won the Olympics :(
 */
package jrocnc.cs3330.lab4;

/**
 * @author Josh
 *
 */

import java.io.IOException;
import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

public class MyShapeDriver {

	public static void main(String[] args) {
		
		// set up file read
		String fileName = "shapes.csv";
		File shapeData = new File(fileName);
		Scanner fileScanner = null;
				
		// catch file open error
		try{
			fileScanner = new Scanner(shapeData);
			fileScanner.hasNextLine();
		}
		catch(IOException e){
			System.out.println("There is no record in your csv file!");
			System.exit(0);
		}
				
		// String variables for scanned in data
		String shape;
		double info = 0;
		double info2 = 0;
				
		// HashMap declaration
		int mapKey = 1;
		HashMap<Integer, Shape> shapeMap = new HashMap<Integer, Shape>();
		String[] parsedString;
		
		// scan until EOF or hashmap is full
		while(fileScanner.hasNextLine() && mapKey <= 10){
			
			// parse comma separated string into String array
			parsedString = fileScanner.nextLine().split(",");
			
			// assing array contents to variable to form shape objects
			shape = parsedString[0];
			info = Integer.parseInt(parsedString[1]);
			// we only assign info here if the file line provides a second
			// value, which will only be in the case of the ellipse and
			// the rectangle
			if(shape.equals("Ellipse") || shape.equals("Rectangle")){
				info2 = Integer.parseInt(parsedString[2]);
			}
			
			//Add objects to hashmap
			if(shape.equals("Circle")){
				System.out.println("Storing Circle in the Hashmap at position: " + mapKey);
				// here we pass info and a 0 since we're only concerned with the radius
				// yet 
				shapeMap.put(mapKey, new Circle(info));
			}
			else if(shape.equals("Ellipse")){
				System.out.println("Storing Ellipse in the Hashmap at position: " + mapKey);
				shapeMap.put(mapKey, new Ellipse (info, info2));
			}
			else if(shape.equals("Rectangle")){
				System.out.println("Storing Rectangle in the Hashmap at position: " + mapKey);
				shapeMap.put(mapKey, new Rectangle(info, info2));
			}
			else{
				System.out.println("Storing Square in the Hashmap at position: " + mapKey);
				info2 = info;
				shapeMap.put(mapKey, new Square(info));
			}
					
			// bump counter
			mapKey++;
		}
		
		// check if there are still lines in the file, prompt if there are
		if(fileScanner.hasNextLine())
			System.out.println("\nThere are more shapes in the file, but it skips the rest since the hashmap is full!\n");
			
		System.out.println("**************************************************\n\nYour shape hashmap contains:\n\n");
		
		// call method to print hashmap contents
		printHashmap(shapeMap);
	}
	
	
	
	// static print hashmap method
	public static void printHashmap(HashMap<Integer, Shape> mapToPrint){
		
		// for each to print off hashmap shape info
		for(Map.Entry<Integer, Shape> entry : mapToPrint.entrySet()){
			// print shape
			System.out.println("Shape " + entry.getKey() + " is: " + entry.getValue());
			
			
		}
		
	}
}

































