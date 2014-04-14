/*
 * 	Joshua O'Steen
 * 	CS 3330 - Guilliams, England
 * 	Homework 3
 * 	04/10/2014
 * 	UML, Machine State, Javadoc
 */

package jrocnc.cs3330.hw3;

import java.lang.Object;
import java.applet.Applet;
import java.io.*;

public class ATC extends Applet{
	
	// attributes
	//
	//
	// 
	public static boolean debug_flag = false;
	protected ATCUI ui = null;
	protected ATCInputhandler input_handler = null;
	protected ATCData data = null;
	protected boolean applet_flag = false;
	public String codeBase = null;
 
	protected String game = null;
	protected Reader input_reader = null;

	
	// constructors
	//
	//
	// 
	/**
	 * Instantiates a new ATC obj.
	 * 
	 * @post Instantiated ATC object.
	 */
	public ATC(){ 
		super(); 
	}
	
	/**
	 * Instantiates a new ATC obj given a string.
	 * 
	 * @param s the string of the game name
	 * @pre s is the name of the type of game the user has selected to play
	 * @post Instantiated ATC object whose game field is set to s.
	 * 
	 */
	public ATC(String s){
		super(); 
		game = new String(s); 
	}
	
	/**
	 * Instantiates a new ATC obj given an existing ATC obj.
	 * 
	 * @param ao the existing ATC obj
	 * @pre ao is the existing ATC obj that the new ATC obj will copy itself from.
	 * @post Instantiated ATC object with fields ui, input_handler, data, and
	 * applet_flag set to the same values as those same fields within ao.
	 */
	public ATC(ATC ao){ 
		super(); 
		ui = ao.ui;
		input_handler = ao.input_handler;
		data = ao.data;
		applet_flag = ao.applet_flag;
	}

	
	// methods
	//
	//
	//
	/**
	 * Initializes this object's codeBase string and app flag.
	 */
	public void init(){
		applet_flag = true;
		codeBase = getCodeBase().toString();
	}
	
	/**
	 * Starts game play. 
	 */
	public void start(){
		startATC();
	}
  
	/**
	 * Stops game play. 
	 */
	public void stop(){
		stopATC();
	}

	/**
	 * Handles much of the necessary initialization for game play such as file
	 * and input readers, copyright information is displayed to user in the terminal,
	 * and application UI is built and displayed. 
	 * Data object which manages most of the game play is instantiated here. 
	 */
	public void startATC(){
		
		if( !applet_flag ){
			codeBase = ClassLoader.getSystemResource(".").toString();
				
			try{
				input_reader = new FileReader( "config/"+game );
			}
			catch( FileNotFoundException e ){
				System.err.println("Cannot open config file!");
				System.exit(0);
			}
		}
		else
		{
			game = getParameter("GAME");
			input_reader = new StringReader( getParameter("CONFIG") );
		}
		

		if( input_reader == null ) 
			return;

		printCopyright();

		ui = new ATCUI_impl( this );

		input_handler = new ATCInputhandler_impl( this );
		data = new ATCData( this );
		data.setConfig( new ATCConfig_from_stream( game, input_reader ) );
		data.setRecord( new ATCRecord_impl() );
		data.initData();
	}

	/**
	 * Closes the application by closing the ui and setting ui, data, and input_handler to
	 * null values.
	 */
	public synchronized void stopATC(){
    
		if( data != null )
			data.gameOver( null );
		
		if( ui != null )
			ui.close();
		
		ui = null;
		input_handler = null;
		data = null;

		if( !applet_flag )
			System.exit(0);
	}

	/**
	 * Gets this object's ui field.
	 */
	public ATCUI getUI(){ 
		return ui; 
	}
	
	/**
	 * Gets this objects input_handler field.
	 */
	public ATCInputhandler getInputhandler(){
		return input_handler; 
	}
	
	/**
	 * Gets this objects data field.
	 */
	public ATCData getData(){
		return data; 
  	}
	
	/**
	 * Prints out the copyright information associated with the ATC game 
	 * to the user.
	 */
	protected void printCopyright(){
		
		System.out.println("ATCJ: Air Traffic Controller Game");
		System.out.println("Copyright (C) 2003 Zheli Erwin Yu.");
		System.out.println("This is free software; see the source for copying conditions.  There is NO");
		System.out.println("warranty; not even for MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.\n");
	}
};























