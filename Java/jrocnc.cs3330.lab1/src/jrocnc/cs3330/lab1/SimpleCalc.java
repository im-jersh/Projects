/**
 * 
 */
package jrocnc.cs3330.lab1;

/**
 * @author jrocnc
 *
 */
public class SimpleCalc {
	// attributes
		private double num1, num2;
		
		// methods
		public SimpleCalc(double x, double y){ // constructor
			setNum1(x);
			setNum2(y);
		}
		
		// getters
		public double getNum1(){
			return this.num1;
		}
		
		public double getNum2(){
			return this.num2;
		}
		
		// setters
		private void setNum1(double n){
			this.num1 = n;
		}
		
		private void setNum2(double n){
			this.num2 = n;
		}
		
		// add method
		public double addNumbers(){
			return this.getNum1() + this.getNum2();
		}
		
		// subtract method
		public double subtractNumbers(){
			return this.getNum1() - this.getNum2();
		}
		
		// multiply method
		public double multiplyNumbers(){
			return this.getNum1() * this.getNum2();
		}
		
		// divide method
		public double divideNumbers(){
			// error check
			if(getNum2() == 0){
				System.out.println("Error: can not divide by zero");
				System.exit(1);
			}
			return this.getNum1()/this.getNum2();
		}
}



















