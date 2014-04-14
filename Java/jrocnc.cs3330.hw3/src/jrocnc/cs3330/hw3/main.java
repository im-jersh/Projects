/*
 * 	Joshua O'Steen
 * 	CS 3330 - Guilliams, England
 * 	Homework 3
 * 	04/10/2014
 * 	UML, Machine State, Javadoc
 */


package jrocnc.cs3330.hw3;

import javax.swing.JFrame;
import java.lang.*;
import java.util.*;
//import eyu.atc.*;

public class main{
	
	public static void main( String args[] ){
	
		// attributes
		String game = args.length >= 1 ? args[0] : "default";
		ATC atc = new ATC( game );
		
		
		
		WindowUtilities.setNativeLookAndFeel();


		// call .startATC method
		atc.startATC();
	}

};
