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
public class Manager extends Employee{

	// attributes
	private String department;
	
	// constructor
	public Manager(String name, int SSN, String department){
		super(name, SSN);
		this.setDepartment(department);
		
	}
	
	// methods
	protected void setDepartment(String department){
		this.department = department;
	}
	
	public String getDepartment(){
		return this.department;
	}
	
	@Override
	public String toString(){
		return "Manager " + super.getName() + "\n\tSSN: " + super.getSSN() + "\n\tDepartment: " + this.getDepartment();
	} 
}
