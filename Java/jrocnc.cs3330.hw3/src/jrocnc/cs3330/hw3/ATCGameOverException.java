/*
 * 	Joshua O'Steen
 * 	CS 3330 - Guilliams, England
 * 	Homework 3
 * 	04/10/2014
 * 	UML, Machine State, Javadoc
 */

package jrocnc.cs3330.hw3;

import java.lang.Exception;
import java.lang.String;

/**
 * Exception thown when game over is detected.
 */
public class ATCGameOverException extends Exception {
	
	// constructors
	//
	//
	//
	/**
	 * Instantiates a new ATCGameOverException.
	 * @param str the string
	 */
	public ATCGameOverException(String str) {
		super(str);
	}
};
