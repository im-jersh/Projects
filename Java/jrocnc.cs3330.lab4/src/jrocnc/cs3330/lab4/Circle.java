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
public class Circle extends Ellipse {

	public Circle(double radius) {
		super(radius, radius);
		//Ellipse ellipse = new Ellipse(majorAxis, minorAxis);
		
	}
	
	public double getRadius(){
		return this.getMajorAxis();
	}
	
	@Override
	public String toString(){
		return "Circle\n\tRadius = " + getRadius( )+ "\n\tArea = " + super.calcArea() + "\n\tPerimeter = " + super.calcPerimeter() + "\n";
	}
	
	
}
