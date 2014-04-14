/**
 * Name: Josh O'steen
 * Lab: 4
 * Section: D
 * Date: 02/25/2014
 * Description: Shapes
 * Sub Code: Russian won the Olympics :(
 */
package jrocnc.cs3330.lab4;

/**
 * @author Josh
 *
 */
public class Ellipse implements Shape {
	
	// attributes
	private double majorAxis, minorAxis;
	
	// constructor
	public Ellipse(double majorAxis, double minorAxis){
		this.setMajorAxis(majorAxis);
		this.setMinorAxis(minorAxis);
	}
	
	// methods
	public double getMajorAxis(){
		return this.majorAxis;
	}
	
	protected void setMajorAxis(double majorAxis){
		this.majorAxis = majorAxis;
	}
	
	public double getMinorAxis(){
		return this.minorAxis;
	}
	
	protected void setMinorAxis(double minorAxis){
		this.minorAxis = minorAxis;
	}
	
	
	public String toString(){
		return "Ellipse\n\tAxis lengths = " + getMajorAxis() + " and " + getMinorAxis() + "\n\tArea = " + calcArea() + "\n\tPerimeter = " + calcPerimeter() + "\n";
	}

	
	public double calcArea() {
		return (Math.PI * this.getMajorAxis() * this.getMinorAxis());
	}

	
	public double calcPerimeter() {
		
		return (2 * Math.PI) * Math.sqrt((Math.pow(this.getMajorAxis(), 2) + Math.pow(this.getMinorAxis(), 2))/2);
	}
}



