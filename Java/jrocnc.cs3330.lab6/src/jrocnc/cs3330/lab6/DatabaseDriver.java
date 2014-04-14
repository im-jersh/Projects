/**
 * Name: Joshua O'Steen
 * Date: 03/17/2014
 * Class: CS3330
 * Lab: 6
 * Section: D
 * Submission Code: Bioprocess
 */
package jrocnc.cs3330.lab6;

/**
 * @author jrocnc
 *
 */
public class DatabaseDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// file name, instantiate database object
		String fileName = "empData.csv";
		Database empDatabase = new Database(fileName);
		
		// menu, request input
		empDatabase.searchMenu();
		
		
		
	}

}
