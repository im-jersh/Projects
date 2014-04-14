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
public class Developer extends Employee{
	
	// attributes
	private String langProf;
	
	// constructor
	public Developer(String name, int SSN, String langProf){
		super(name, SSN);
		this.setLangProf(langProf);
	}
	
	// methods
	protected void setLangProf(String langProf){
		this.langProf = langProf;
	}
	
	public String getLangProf(){
		return this.langProf;
	}

}
