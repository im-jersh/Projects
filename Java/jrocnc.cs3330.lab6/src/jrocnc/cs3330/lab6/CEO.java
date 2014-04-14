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
public class CEO extends Employee{
	
	// attributes
	int netWorth;
	
	// constructor
	public CEO(String name, int SSN, int netWorth){
		super(name, SSN);
		this.setNetWorth(netWorth);
	}
	
	// methods
	protected void setNetWorth(int netWorth){
		this.netWorth = netWorth;
	}
	
	public int getNetWorth(){
		return this.netWorth;
	}
}
