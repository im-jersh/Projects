/*
 * 	Joshua O'Steen
 * 	CS 3330 - Guilliams, England
 * 	Homework 3
 * 	04/10/2014
 * 	UML, Machine State, Javadoc
 */


package jrocnc.cs3330.hw3;

import java.lang.Object;

public interface ATCInputhandler {
	public void processKey(char c);

	public boolean processCommand();

	public boolean processActionCommand(String c);

	public void reset();
};
