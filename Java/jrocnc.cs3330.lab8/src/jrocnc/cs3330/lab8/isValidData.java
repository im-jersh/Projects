/**
 * Name: Joshua O'Steen
 * Date: 04/09/2014
 * Class: CS3330
 * Lab: 8
 * Section: D
 * Submission Code: GUI
 */

package jrocnc.cs3330.lab8;



public interface isValidData {
	public void isValidAge(String age) throws InvalidAgeException;
	public void isBalance(String balance) throws InvalidBalanceException;
	public void isName(String name) throws InvalidNameException;
}
