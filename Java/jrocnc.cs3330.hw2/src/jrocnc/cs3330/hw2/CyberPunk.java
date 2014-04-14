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
		
		// create player, console, and grid instances
		Scanner mainScanner = new Scanner(System.in);
		System.out.printf("Enter your name: ");
		Player player1 = new Player(mainScanner.nextLine());
		Console console = new Console(player1, new Grid());
		
		// prompt
		System.out.println("\nThe year is 2077.\n\nStarting Console.\n");
		
		// run the game by calling Console.use()
		boolean control = console.use();
		
		// game over
		if(control)
			// Console.use() returned "true" meaning the player logged off
			System.out.println("\nLogging Off!");
		else{
			// Console.use() returned "false" meaning they player has no health
			System.out.println("\n" + player1 + " has FLATLINED");
			System.out.println("Game Over!");
		}
		
		// close scanner
		mainScanner.close();
		
		
	}

}
