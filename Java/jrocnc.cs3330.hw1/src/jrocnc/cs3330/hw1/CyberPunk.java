/**
 * 
 */
package jrocnc.cs3330.hw1;

import java.util.Scanner;

/**
 * @author Josh
 *
 */
public class CyberPunk {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		// create player and console instances
		Scanner mainScanner = new Scanner(System.in);
		System.out.printf("Enter your name: ");
		Player player1 = new Player(mainScanner.next());
		Console console = new Console(player1);
		
		// prompt
		System.out.println("The year is 2077.\n" + player1 + ".\nStarting Console.");
		
		// run attack on grid until player has no health
		boolean control = true;
		while(control){
			if(!console.use())
				control = false;
		}
		
		// game over
		System.out.println("Game Over!");
		
		
	}

}
