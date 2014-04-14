/**
 * @author jrocnc
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


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.util.Random;

public class MKDriver {

	public static void main(String[] args) {
		// instantiate a Scanner and File class 
				String dataFileName = "Fighters.data";
				File fighterDataFile = new File(dataFileName);
				Scanner dataScanner = null;
				
				// catch file open error
				try{
					dataScanner = new Scanner(fighterDataFile);
				}
				catch(IOException e){
					System.out.println("Error: Fighter stats file not found.");
					System.exit(0);
				}
				
				
				// ask user for input
				System.out.print("!!!Welcome to Mortal Kombat!!!\n\nLoading Data...");
				
				// parsed values
				String name;
				int health;
				
				// declare ArrayLists
				ArrayList<Fighter> fighterList = new ArrayList<Fighter>();
				
				
				// read line by line
				while(dataScanner.hasNextLine()){
					
					// parse comma separated string into String array
					String[] parsedString = dataScanner.nextLine().split(",");
					
					name = parsedString[0];
					health = Integer.parseInt(parsedString[1]);
					
					// add to ArrayList
					fighterList.add(new Fighter(name, health));
					
				}
				
				System.out.println("\nLoading Completed!\n\n");
				
				// print ArrayLists
				System.out.println("Here's the list of Fighters:");
				Iterator<Fighter> CTIter = fighterList.iterator();
				while(CTIter.hasNext()){
					String CTInfo = CTIter.next().toString();
					System.out.printf("%s", CTInfo);
				}
				
				System.out.println("\n\n3...2...1...Fight!\n\n");
				
				// instantiate random class
				Random randomGenerator = new Random();
				Random disGenerator = new Random();
				
				// fight while both are alive
				while(fighterList.get(0).getAlive() && fighterList.get(1).getAlive()){
					
					// determine which fighter is being hit
					int randomFighter = randomGenerator.nextInt(2);
					int distance = disGenerator.nextInt(10)+1;
					
					// decide which fighter to hit
					if(randomFighter == 0){
						fighterList.get(randomFighter).beingHit(distance);
						System.out.println(fighterList.get(randomFighter).toString()); 
					}
					else{
						fighterList.get(randomFighter).beingHit(distance);
						System.out.println(fighterList.get(randomFighter).toString()); 

					}
					
				}
				
				// print winner
				System.out.println("\n\nRound Over...");
				if(fighterList.get(0).getAlive()){
					System.out.println(fighterList.get(0).getName() + " won!");
				}
				else if(fighterList.get(1).getAlive()){
					System.out.println(fighterList.get(1).getName() + " won!");
				}
				
				

				// closing the Scanner class
				dataScanner.close();
				
				// return 0 for success
				System.exit(0);

	}

}
