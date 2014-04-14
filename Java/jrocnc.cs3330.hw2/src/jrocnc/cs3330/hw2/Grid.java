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

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;



/**
 * @author Josh
 *
 */
public class Grid{

	// attributes
	private ArrayList<Server> servers;
	
	// attributes not in the UML diagram
	// added for ease of use
	String serverFileName = "servers.txt";

	
	// constructor
	public Grid(){
		// initialize grid
		this.loadServers();
	}
	
	// methods
	private void loadServers(){
		// file name and class
		File serverDataFile = new File(serverFileName);
		Scanner serverScanner = null;
		
		// catch file open error
		try{
			serverScanner = new Scanner(serverDataFile);
		}
		catch(IOException e){
			System.out.println("Error: server file not found.");
			System.exit(0);
		}
		
		// instantiate arrayList and string array to hold line
		servers = new ArrayList<Server>();
		String[] serverLine;
		
		// fill array line by line until end of file reached
		while(serverScanner.hasNextLine()){
			
			// get next line, store comma separated tokens in string array
			serverLine = serverScanner.nextLine().split(",");
			
			// send values to instantiate new server object, store in array list
			servers.add(new Server(serverLine[0], serverLine[1], Integer.parseInt(serverLine[2])));
		}
		
	}
	
	public int search(String name){
		// iterate through grid
		for(int index = 0; index < this.servers.size(); index++){
			// if server name matches parameter name
			if(this.servers.get(index).getName().equals(name))
				// return index of server within grid arrayList
				return index;
		}
		
		// otherwise return -1 for not found
		return -1;
	}
	
	public int numberOfServers(){
		// return number of servers in grid
		return this.servers.size();
	}
	
	public String getServerName(int server_idx){
		// error check server index
		if(server_idx < 0 || server_idx > this.numberOfServers() - 1)
			return "Invalid server.";
		
		// valid index, return server name
		return servers.get(server_idx).getName();
	}
	
	public int attackServer(Tool tool, int server){
		// print out server info
		System.out.println(this.servers.get(server));
		
		
		//----------------------------------------------------------------------------//

		
		// if server type ENCRYPTED
		if(servers.get(server).getType().equals("ENCRYPTED")){
			// if tool type BASHER
			if(tool.getType().equals("BASHER")){
				this.servers.get(server).updateStrength(3);
				return -30;
			}
			// if tool type DECRYPTOR
			else if(tool.getType().equals("DECRYPTOR")){
				// compare strengths
				if(servers.get(server).getStrength() >= tool.getStrength()){
					this.servers.get(server).updateStrength(3);
					return -20;
				}
				else if (servers.get(server).getStrength() < tool.getStrength()){
					this.servers.get(server).updateStrength(3);
					return 25000;
				}
			}
		}
		// if server type UNENCRYPTED
		else if (servers.get(server).getType().equals("UNENCRYPTED")){
			// if tool type BASHER
			if(tool.getType().equals("BASHER")){
				// compare strength
				if(servers.get(server).getStrength() >= tool.getStrength()){
					this.servers.get(server).updateStrength(3);
					return -10;
				}
				else if(servers.get(server).getStrength() < tool.getStrength()){
					this.servers.get(server).updateStrength(3);
					return 10000;
				}
			}
			// if tool type DECRYPTOR
			else if(tool.getType().equals("DECRYPTOR")){
				this.servers.get(server).updateStrength(3);
				return -30;
			}
		}
	
	
	
	// return 0 as default if control reaches this point
	return 0;
	}

}
