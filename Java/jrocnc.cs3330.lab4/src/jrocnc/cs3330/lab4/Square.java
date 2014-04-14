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
public class Square extends Rectangle {

	public Square(double side) {
		super(side, side);
		
	}
	
	public double getSideLength(){
		return this.getLength();
	}
	
	@Override
	public String toString(){
		return "Square\n\tDimensions = " + getLength() + " x " + getWidth() + "\n\tArea = " + super.calcArea() + "\n\tPerimeter = " + super.calcPerimeter() + "\n";
	}
	
	
}
