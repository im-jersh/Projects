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
//import java.util.Random;

/**
 * @author Josh
 *
 */
public class Console{

	// attributes
	private static final Scanner input = new Scanner(System.in);
	private Player player;
	private Grid grid;
	private ToolKit toolkit;
	private ArrayList<Job> jobs;
	private ArrayList<Business> contacts;
	private BlackMarket blackMarket;
	
	// attributes not in the UML diagram
	// added for ease of use
	final int UPGRADE_COST = 10000;
	final String blackMarketName = "New Tokyo Subterranean Sewer";
	final String contactName = "Katsu Tanaka";
	final String contactsFileName = "contacts.txt";

	
	
	// constructor
	public Console(Player player, Grid grid){
		// set player & grid and initialize with Console.init()
		this.setPlayer(player);
		this.setGrid(grid);
		init();
	}
	
	// methods
	private void setPlayer(Player player){
		// set private player attribute
		this.player = player;
	}
	
	private void setGrid(Grid grid){
		// set private grid attribute
		this.grid = grid;
	}
	
	private void init(){
		// instantiate the blackMarket attribute
		// instantiate the jobs arrayList
		// instantiate ToolKit
		// load contacts with Contacts.loadContacts()
		blackMarket = new BlackMarket(blackMarketName, contactName, grid, player);
		jobs = new ArrayList<Job>();
		this.toolkit =  new ToolKit();
		this.loadContacts();
	}
	
	public boolean use(){
		while(this.player.getHealth() > 0 ){
			// print main menu with Console.displayOptionMenu() 
			// get choice from player
			System.out.println(this.player + "\n");
			int choice = this.displayOptionMenu();
			
			
			//----------------------------------------------------------------------------//
			
			
			// select-case depending on menu choice
			switch(choice){
			// RUN ON GRID
			case 1: 
				// local variables
				Job pulledJob = null;
				int serverToAttack;
				boolean attackReturn;
				
				// check to see if jobs arrayList is populated
				// if not, prompt player that there are no jobs 
				// continue while loop
				if(this.jobs.isEmpty()){
					System.out.println("\nYou need to get a job first!\n");
					continue;
				}
				else{
					// otherwise, get a job using Console.pullAJob()
					// get job's name using Job.getTargetAddress()
					// search grid for job using Grid.search() 
					// return grid index of server to attack
					pulledJob = this.pullAJob();
					serverToAttack = this.grid.search(pulledJob.getTargetAddress());
	
					// if server doesn't exist
					// continue while loop
					if(serverToAttack == -1){
						System.out.println("Server not found!");
						continue;
					}
					
					// attack selected server using Console.runOnTheGrid()
					attackReturn = this.runOnTheGrid(serverToAttack, pulledJob.getReward());
				}
				
				// remove job from available jobs arrayList if attack successful
				if(attackReturn)
					this.jobs.remove(pulledJob);
				
				// do nothing if attack failed
				// continue while loop
				continue;
			// VISIT THE BLACK MARKET
			case 2:
				// if player has sufficient funds, go to black market
				if(this.player.getMoney() > 0){
					// go to black market using Console.updateToolKit()
					// continue while loop
					this.updateToolKit();
					continue;
				}
				// otherwise, prompt player of insufficient funds
				// continue while loop
				else{
					System.out.println("No funds to visit black market");
					continue;
				}
			// ADD JOB TO THE LIST
			case 3:
				// add job unless 10 jobs exist already
				if(this.jobs.size() < 10){
					// add job to available jobs arrayList using Console.findJobOffer()
					// continue while loop
					this.findJobOffer();
					continue;
				}	
				// otherwise, prompt player that 10 jobs already exist
				// continue while loop
				else{
					System.out.println("Too many jobs left undone.");
					continue;
				}
			// LOG OFF CONSOLE
			case 4:
				// close Scanner
				// return true back to main()
				input.close();
				return true;
	
			}// end switch-case
			
			
			
			//----------------------------------------------------------------------------//

			
			/*
			 * 
			 * this section of code has been commented out from Homework 1 because
			 * jobs must be manually added to arrayList and manually selected from
			 * populated arrayList and updating the tool can only be selected from
			 * the main menu.
			 * 
			 * keeping the code here for version control sake and re-usability
			 * 
			 */
			
			/*
			// search for server if yes
			// get random server if no
			if(response.equals("yes") || response.equals("Yes")){
				// get server address input
				System.out.println("Enter the address to server: ");
				String serverAddress = input.next();
				
				// make sure returned server index is within valid range
				if(this.grid.search(serverAddress) > -1 && this.grid.search(serverAddress) < this.grid.numberOfServers()){
					// run using server address
					this.runOnTheGrid(this.grid.search(serverAddress));
				}
				// if server address wasn't found in grid
				else if(this.grid.search(serverAddress) == -1){
					// skip rest of while loop and ask for server again
					System.out.println("\nServer not found.");
					continue;
				}
				
			}
			else{
				//random generator
				Random randomServer = new Random();
				System.out.println("Pulling a random job.");
				this.runOnTheGrid(randomServer.nextInt(grid.numberOfServers()));
			}
						
			// update tool prompt
			if(this.player.getHealth() > 0 && this.player.getMoney() > UPGRADE_COST){
				this.displayUpdateOptions();
				response = input.next();
				
				// upgrade confirmed
				if(response.equals("yes") || response.equals("Yes")){
					this.updateToolKit();
				}
			}
			*/
			
			
			//----------------------------------------------------------------------------//

			
			
		}// end while loop
		
		
		// close scanner
		// zero out player health for printing
		// return false back to main()
		input.close();
		this.player.updateHealth(this.player.getHealth() * -1);
		return false;
			
	}
	
	private Job pullAJob(){
		// return null if jobs arrayList is empty
		// (this method should never be called if
		// the jobs arrayList is empty)
		if(this.jobs.isEmpty())
			return null;
		
		// iterate through jobs arrayList and print available jobs
		System.out.println("Jobs to do");
		for(int i = 0; i < this.jobs.size(); i++){
			System.out.println((i+1) + ") " + this.jobs.get(i));
		}
		
		// get choice from player
		System.out.println("Selection: ");
		int choice = input.nextInt();
		
		// error check choice
		while(choice < 1 || choice > this.jobs.size()){
			System.out.println("Option invalid, pick again ");
			choice = input.nextInt();
		}
		
		// return valid available job selection
		return this.jobs.get(choice-1);
	}
	
	private int displayOptionMenu(){
		// print main menu
		// get selection from player
		System.out.println("1) Run on the Grid\n2) Visit the Black Market\n3) Add a job to the list\n4) Log off Console\nSelection: ");
		int selection = input.nextInt();
		
		// error check selection
		while(selection < 1 || selection > 4){
			System.out.println("\nOption invalid, pick again: ");
			selection = input.nextInt();
		}
		
		// return valid selection
		return selection;
		
	}
	
	private void loadContacts(){
		// file name and class
		File contactsDataFile = new File(this.contactsFileName);
		Scanner contactsScanner = null;
				
		// catch file open error
		try{
			contactsScanner = new Scanner(contactsDataFile);
		}
		catch(IOException e){
			System.out.println("Error: server file not found.");
			System.exit(0);
		}
				
		// instantiate contacts arrayList and string array to hold next line
		contacts = new ArrayList<Business>();
		String[] contactsLine;
				
		// fill arrayList line by line until end of file reached
		while(contactsScanner.hasNextLine()){
					
			// get next line
			// store comma separated tokens in string array
			contactsLine = contactsScanner.nextLine().split(",");
					
			// send values to instantiate new business object, store in array list
			if(contactsLine[0].equals("Corporation"))
				contacts.add(new Corporation(contactsLine[1], contactsLine[2], grid));
			else if(contactsLine[0].equals("NonProfit"))
				contacts.add(new NonProfit(contactsLine[1], contactsLine[2], grid));
				
		}
	
	}
	
	private void updateToolKit(){
		// show black market using BlackMarket.buyATool()
		Tool returnedTool = this.blackMarket.buyATool();
		
		// if a tool was returned, update the toolkit using ToolKit.addOrUpdateTool()
		// otherwise, if null was returned, do nothing
		// and return control back to the use method
		if(returnedTool != null)
			this.toolkit.addOrUpdateTool(returnedTool);
		
	}
	
	private boolean runOnTheGrid(int target_server, int possible_income){
		// display toolkit using ToolKit.displayTools()
		this.toolkit.displayTools();
		
		
		//----------------------------------------------------------------------------//

		
		// request tool 
		System.out.println("Pick your ice breaker: ");
		int toolIndex = input.nextInt() - 1;
		
		// error check tool selection index
		while(toolIndex < 0 || toolIndex >= this.toolkit.getNumberOfTools()){
			System.out.println("Not a valid tool. Enter your choice again: ");
			toolIndex = input.nextInt() - 1;
		}
		
		
		//----------------------------------------------------------------------------//

		/*
		 * this section of code has been commented out from the Homework 1 because 
		 * tools that have been added to the toolkit no longer cost anything to use.
		 * keeping the code here for version control sake and re-usability
		 */
		
		/*
		// make sure player can afford tool then decrement player's money by tool cost
		while(this.toolkit.getTool(toolIndex).getCost() > this.player.getMoney()){
			System.out.println("You cannot afford the ice breaker " + this.toolkit.getTool(toolIndex).getName() + ".\nPlease pick another ice breaker that costs " + this.player.getMoney() + " or less.");
			
			toolIndex = input.nextInt();
			
			while(toolIndex < 0 || toolIndex >= this.toolkit.getNumberOfTools()){
				System.out.println("Not a valid tool. Enter your choice again: ");
				toolIndex = input.nextInt();
			}
		}
		
		// decrement player balance by cost of tool
		this.player.updateMoney(-(this.toolkit.getTool(toolIndex).getCost()));
		
		*/
		
		
		//----------------------------------------------------------------------------//

		
		// attack server using Grid.attackServer()
		System.out.println("\nICE ENCOUNTERED. ATTEMPTING BREAK IN.....");
		int attackResult = this.grid.attackServer(this.toolkit.getTool(toolIndex), target_server);
		
		
		//----------------------------------------------------------------------------//

		
		// determine attack result method
		if(attackResult > 0){
			// run was successful
			// add possibe_income from job to player balance
			// return true back to use() so job can be removed
			this.player.updateMoney(possible_income);
			System.out.println("RUN SUCCESSFUL");
			return true;
		}
		else if(attackResult < 0){
			// run failed
			// decrement player health by attackResult
			// return false back to use()
			this.player.updateHealth(attackResult);
			System.out.println("RUN FAILED");
			return false;	
		}
		else if(attackResult == 0){
			// error occurred during attack
			// return false back to use()
			System.out.println("You lost connection during your attack.");
			return false;
		}
		
		
		//----------------------------------------------------------------------------//

	
		// if control reaches this point, an error has occurred
		// return false 
		return false;
		
	}
	
	private void findJobOffer(){
		
		// iterate through contacts arrayList and
		// print out contact info
		System.out.println("Pick a contact to view offers");
		for(int i = 0; i < this.contacts.size(); i++){
			System.out.println((i+1) + ") " + this.contacts.get(i));
		}
		
		// get contact choice
		System.out.println("Selection: ");
		int contactChoice = input.nextInt();
		
		// error check contact choice
		while(contactChoice < 1 || contactChoice > this.contacts.size()){
			System.out.println("Invalid choice, try again: ");
			contactChoice = input.nextInt();
		}
		
		// display contacts job offers using Business.viewJobOffers()
		System.out.println(this.contacts.get(contactChoice - 1).toString());
		this.contacts.get(contactChoice - 1).viewJobOffers();
		
		// get job choice
		System.out.println("Selection: ");
		int jobChoice = input.nextInt();
		
		// error check job choice
		while(jobChoice < 1 || jobChoice > this.contacts.get(contactChoice - 1).getJobOfferCount()){
			System.out.println("Invalid choice, try again: ");
			jobChoice = input.nextInt();
		}
		
		// add job offer to jobs arrayList given valid contact index and job index
		this.jobs.add(this.contacts.get(contactChoice - 1).getJobOffer(jobChoice - 1));
		System.out.println("Job added successfully!");
		
	}
	
}
