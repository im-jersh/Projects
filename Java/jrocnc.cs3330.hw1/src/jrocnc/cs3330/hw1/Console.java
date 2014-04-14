/**
 * 
 */
package jrocnc.cs3330.hw1;

import java.util.Scanner;
import java.util.Random;

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
	final int UPGRADE_COST = 10000;
	
	// constructor
	public Console(Player player){
		// set player and call init
		setPlayer(player);
		init();
	}
	
	// methods
	private void setPlayer(Player player){
		// set private player attribute
		this.player = player;
	}
	
	private void init(){
		// initalize grid and toolkit array lists
		this.grid = new Grid();
		this.toolkit =  new ToolKit();
	}
	
	public boolean use(){
		
		while(this.player.getHealth() > 0){
			// ask for specific server name
			this.displayRunOptions();
			String response = input.next();
			
			
			//----------------------------------------------------------------------------//
			
			
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
			
			
			//----------------------------------------------------------------------------//
			
			
			// update tool prompt
			if(this.player.getHealth() > 0 && this.player.getMoney() > UPGRADE_COST){
				this.displayUpdateOptions();
				response = input.next();
				
				// upgrade confirmed
				if(response.equals("yes") || response.equals("Yes")){
					this.updateToolKit();
				}
			}
			
		}
		
		
		//----------------------------------------------------------------------------//
		
		
		// close scanner, zero player health and print result
		input.close();
		int zeroPlayerHealth = this.player.getHealth() + -(this.player.getHealth());
		this.player.updateHealth(zeroPlayerHealth - zeroPlayerHealth);
		System.out.println("\n" + this.player + " has FLATLINED");
		
		
		//----------------------------------------------------------------------------//

		
		
		// use method ended. player either died or has no money
		return false;
	
	}
	
	private void updateToolKit(){
		// display toolkit
		this.toolkit.displayTools();
		
		
		//----------------------------------------------------------------------------//

		
		// request tool and error check input
		System.out.println("Pick your ice breaker: ");
		int toolIndex = input.nextInt() - 1;
				
		while(toolIndex < 0 || toolIndex >= this.toolkit.getNumberOfTools()){
			System.out.println("Not a valid tool. Enter your choice again: ");
			toolIndex = input.nextInt() - 1;
		}
		
		
		//----------------------------------------------------------------------------//

		
		// request upgrade amount and error check that player has enough money
		System.out.println("How much strength to add (each 1 addition cost 10,000 ysn)? ");
		int upgrade = input.nextInt();
		
		if((upgrade * UPGRADE_COST) > this.player.getMoney())
			System.out.println("Not enough funds.\n\n");
		else if((upgrade * UPGRADE_COST) <= this.player.getMoney()){
			this.player.updateMoney(-(upgrade * UPGRADE_COST));
			this.toolkit.getTool(toolIndex).updateStrength(upgrade);
		}
		
	}
	
	private void runOnTheGrid(int target_server){
		// display toolkit
		this.toolkit.displayTools();
		
		
		//----------------------------------------------------------------------------//

		
		// request tool and error check input
		System.out.println("Pick your ice breaker: ");
		int toolIndex = input.nextInt() - 1;
		
		while(toolIndex < 0 || toolIndex >= this.toolkit.getNumberOfTools()){
			System.out.println("Not a valid tool. Enter your choice again: ");
			toolIndex = input.nextInt() - 1;
		}
		
		
		//----------------------------------------------------------------------------//

		
		// make sure player can afford tool then decrement player's money by tool cost
		while(this.toolkit.getTool(toolIndex).getCost() > this.player.getMoney()){
			System.out.println("You cannot afford the ice breaker " + this.toolkit.getTool(toolIndex).getName() + ".\nPlease pick another ice breaker that costs " + this.player.getMoney() + " or less.");
			
			toolIndex = input.nextInt();
			
			while(toolIndex < 0 || toolIndex >= this.toolkit.getNumberOfTools()){
				System.out.println("Not a valid tool. Enter your choice again: ");
				toolIndex = input.nextInt();
			}
		}
		
		
		//----------------------------------------------------------------------------//

		
		// decrement player money for cost of tool and attack server
		this.player.updateMoney(-(this.toolkit.getTool(toolIndex).getCost()));
		System.out.println("\nICE ENCOUNTERED. ATTEMPTING BREAK IN.....");
		int attackResult = this.grid.attack(this.toolkit.getTool(toolIndex), target_server);
		
		
		//----------------------------------------------------------------------------//

		
		// determine attack result method
		if(attackResult > 0){
			this.player.updateMoney(attackResult);
			System.out.println("RUN SUCCESSFUL");
		}
		else if(attackResult < 0){
			this.player.updateHealth(attackResult);
			System.out.println("RUN FAILED");

		}
		else if(attackResult == 0)
			System.out.println("You lost connection during your attack.");
		
		// print out player stats
		if(this.player.getHealth() > 0 && this.player.getMoney() > 0)
			System.out.println("\n" + this.player + ".");
		
		
	}
	
	private void displayRunOptions(){
		// print out run option to prompt
		System.out.println("Do you have a particular target server IP address to probe the grid? (yes/no): ");
	}
	
	private void displayUpdateOptions(){
		// print out update options to prompt
		System.out.println("Would you like to update a tool in the toolkit? (yes/no): ");
	}
	
}





















