/**
 * Name: Joshua O'Steen
 * Date: 03/31/2014
 * Class: CS3330
 * Lab: 7
 * Section: D
 * Submission Code: Opening Day
 */

package jrocnc.cs3330.lab7;



public interface isValidData {
	public void isValidAge(String age) throws InvalidAgeException;
	public void isBalance(String balance) throws InvalidBalanceException;
	public void isName(String name) throws InvalidNameException;
}
