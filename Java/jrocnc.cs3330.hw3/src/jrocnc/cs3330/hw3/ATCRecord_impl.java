/*
 * 	Joshua O'Steen
 * 	CS 3330 - Guilliams, England
 * 	Homework 3
 * 	04/10/2014
 * 	UML, Machine State, Javadoc
 */


package jrocnc.cs3330.hw3;

public class ATCRecord_impl implements ATCRecord {
	
	// methods
	//
	//
	//
	/**
	 * Prints the game play information at the end of the game.
	 * 
	 * @param user the player name
	 * @param config_name the game type
	 * @param time the number of ticks
	 * @param real_time actually game play time
	 * @param safe_count the number of safe planes
	 * @pre the parameters contain the game play information at the end of play
	 * @post the game play information is displayed to the user in the system prompt.
	 */
	public void save(String user, String config_name, int time, long real_time,
			int safe_count) {
		System.out.println("User: " + user + ", Game: " + config_name
				+ ", Time: " + time + ", Real time: " + (double) real_time
				/ 1000 + "s" + ", Safe: " + safe_count);
	}
};
