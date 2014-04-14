/**
 * Name: Josh O'steen
 * Lab: 4
 * Section: D
 * Date: 02/25/2014
 * Description: Shapes
 * Sub Code: Russian won the Olympics :(
 *
 * 
 */
package jrocnc.cs3330.lab4;

/**
 * @author Josh
 *
 */
public class Rectangle implements Shape {
	
	// attributes
	private double length, width;
	
	// constructor
	public Rectangle(double length, double width){
		setLength(length);
		setWidth(width);
	}
	
	public double getLength(){
		return this.length;
	}
	
	protected void setLength(double length){
		this.length = length;
	}
	
	public double getWidth(){
		return this.width;
	}
	
	protected void setWidth(double width){
		this.width = width;
	}
	
	
	public String toString(){
		return "Rectangle\n\tDimensions = " + getLength() + " x " + getWidth() + "\n\tArea = " + calcArea() + "\n\tPerimeter = " + calcPerimeter() + "\n";
	}

	
	public double calcArea() {
		
		return this.getLength()*this.getWidth();
	}

	
	public double calcPerimeter() {
		
		return (2*this.getWidth())+(2 * this.getLength());
	}
}