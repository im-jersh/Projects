/*
 * 	Joshua O'Steen
 * 	CS 3330 - Guilliams, England
 * 	Homework 3
 * 	04/10/2014
 * 	UML, Machine State, Javadoc
 */


package jrocnc.cs3330.hw3;

// TODO: Auto-generated Javadoc
public class Turn {
	
	// attributes
	//
	//
	//
	public int matrix[][] = new int[2][2];
	public static Turn SMALL_RIGHT = new Turn(1, -1, 1, 1);
	public static Turn SMALL_LEFT = new Turn(1, 1, -1, 1);
	public static Turn RIGHT = new Turn(0, -1, 1, 0);
	public static Turn LEFT = new Turn(0, 1, -1, 0);

	
	// constructors
	//
	//
	//
	/**
	 * Instantiates a new turn.
	 *
	 * @param a the a
	 * @param b the b
	 * @param c the c
	 * @param d the d
	 * @pre a, b, c, and d can be any integer value between -1 and 0 respectively.
	 * @post Instantiated Turn object with matrix values a,b,c,d
	 */
	public Turn(int a, int b, int c, int d) {
		matrix[0][0] = a;
		matrix[0][1] = b;
		matrix[1][0] = c;
		matrix[1][1] = d;
	}

	/**
	 * Instantiates a new turn from an existing turn object.
	 *
	 * @param t the existing turn object
	 * @pre t is an existing Turn object
	 * @post Instantiated Turn object copied from t
	 */
	public Turn(Turn t) {
		int x, y;
		for (x = 0; x < 2; x++)
			for (y = 0; y < 2; y++)
				matrix[x][y] = t.matrix[x][y];
	}

	
	// methods
	//
	//
	//
	/**
	 *  Determines if this object equals another turn object. 
	 *  
	 *  @param t the object to compare this object to.
	 *  @return true if this object is equal to t; false otherwise.
	 *  @pre t is any object within the scope of this package.
	 *  @post t is compared and determined to be equal or not to this object.
	 */
	public boolean equals(Object t) {
		if (!(t instanceof Turn))
			return false;
		return ((Turn) t).matrix[0][0] == matrix[0][0]
				&& ((Turn) t).matrix[0][1] == matrix[0][1]
				&& ((Turn) t).matrix[1][0] == matrix[1][0]
				&& ((Turn) t).matrix[1][1] == matrix[1][1];
	}

	/**
	 * Provides a hashed integer value based on the contents of the Turn object
	 * matrix.
	 * 
	 * @return result the hashed integer value
	 */
	public int hashCode() {
		int result = matrix[0][0];
		result = result * 7 + matrix[0][1];
		result = result * 7 + matrix[1][0];
		result = result * 7 + matrix[1][1];
		return result;
	}

	/**
	 * Determines which direction a plane should turn towards relative to it's
	 * current direction.
	 *
	 * @param from the current direction
	 * @param to the direction to turn towards
	 * @return turn object with the new direction to turn
	 * @pre from is the plane's current direction (x,y) and to is the direction the user
	 * has instructed the plane to turn towards (x,y).
	 * @post New turn object instructing the plane the degree to which is should turn left
	 * or right which is determined by the the (x,y) coordinates of from and to.
	 */
	public static Turn turnTowards(Direction from, Direction to) {
		int dx, dy;
		dx = from.x * to.x + from.y * to.y;
		dy = from.x * to.y - from.y * to.x;

		if (dx >= 0 && dy == 0)
			return null;
		if (dx > 0)
			return dy > 0 ? new Turn(SMALL_RIGHT) : new Turn(SMALL_LEFT);
		return dy > 0 ? new Turn(RIGHT) : new Turn(LEFT);
	}

	/**
	 * Determines the degree to which a plane should turn based off of user input.
	 *
	 * @param c the user input character
	 * @return the turn the plane should make
	 * @pre c can be any character in 'rlRL'
	 * @post Instantiated Turn object with a matrix representing either a 45 or 90 degree
	 * right or left turn based off of c is returned.
	 */
	public static Turn charToTurn(char c) {
		switch (c) {
		case 'r':
			return new Turn(SMALL_RIGHT);
		case 'l':
			return new Turn(SMALL_LEFT);
		case 'R':
			return new Turn(RIGHT);
		case 'L':
			return new Turn(LEFT);
		}
		return null;
	}
};
