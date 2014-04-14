/**
 * name: Joshua O'Steen
 * pawprint: jrocnc
 * class: CS3330
 * lab: 1
 * section: D
 * date: 02/03/14
 * code: Pratfall
 * receipt:
 * 
 */
package jrocnc.cs3330.lab1;

// import the Scanner class
import java.util.Scanner;

/**
 * @author jrocnc
 *
 */
public class LabOneDriver {

	public static void main(String[] args){
		// instantiate a Scanner class for reading input
		// from the keyboard
		Scanner input = new Scanner(System.in);
		
		// ask user for input
		System.out.print("Enter your first number: ");
		
		// scan in an integer from the stdin buffer
		// using the scanner class
		double num_1 = input.nextDouble();
		
		System.out.print("Enter your second number: ");
		double num_2 = input.nextDouble();
		
		// instatiate the adder class for addition. You are creating an object!!!
		// calling the objects constructors
		SimpleCalc calculate = new SimpleCalc(num_1, num_2);
		
		// display the results to user
		System.out.println("The sum of these two numbers is " + calculate.addNumbers() + ".");
		System.out.println("The difference of these two numbers is " + calculate.subtractNumbers() + ".");
		System.out.println("The product of these two numbers is " + calculate.multiplyNumbers() + "."); 
		System.out.println("The sum of these two numbers is " + calculate.divideNumbers() + ".");
		
		// closing the Scanner class
		input.close();
		
		System.out.println();
		System.out.println("Program Finished");
		
		// return 0 for success
		System.exit(0);
		
	}
}
