/*
 * 	Joshua O'Steen
 * 	CS 3330 - Guilliams, England
 * 	Homework 3
 * 	04/10/2014
 * 	UML, Machine State, Javadoc
 */

package jrocnc.cs3330.hw3;

import java.lang.String;
import java.io.*;

// TODO: Auto-generated Javadoc
public class ATCConfig_from_stream implements ATCConfig{
	
	// attributes
	//
	//
	//
	protected int dx=9, dy=9, new_plane_chance=20, init_tick_ms=3000, tick_dec=2;
	protected int next_exit = 0;
	protected int next_beacon = 0;
	protected int next_airfield = 0;
	protected int next_line = 0;
	protected String name = "";
	protected Reader input_reader = null;
	private StreamTokenizer tokenizer = null;
	

	// constructors
	//
	//
	//
	/**
	 * Instantiates a new ATCConfig_from_stream obj.
	 *
	 * @param n the name
	 * @param reader the input reader
	 * @pre n is the name of the configuration file to configure the
	 * game play; reader is the input reader to open and read that file.
	 * @post Instantiated ATCConfig_from_stream object that will read from the
	 * configuration file.
	 */
	public ATCConfig_from_stream(String n, Reader reader ){
		name = new String(n);
		input_reader = reader;
	}
	
	
	// nested classes
	//
	//
	//
	class ParseException extends Exception{
		
		/**
		 * Instantiates a new parses the exception.
		 *
		 * @param str the str
		 */
		public ParseException( String str ){
			super(str);
		}
	};

	
	// methods
	//
	//
	//
	
	/**
	 * Parses the game configuration file to get the values needed to set
	 * up the particular game difficulty.
	 * 
	 * @pre beacon, exits, airfields, lines are the arrays that will hold these
	 * objects in order to set up the game field and associated UI elements. these
	 * arguments are passed in along with their maximum array size.
	 * @post the configuration file has been read (or a parse error occurred) and 
	 * the values necessary for the game play selected by the user have been 
	 * updated based on that file.
	 */
	public boolean config(Beacon   beacons[],   int max_beacon,
						  Exit     exits[],     int max_exit,
						  Airfield airfields[], int max_airfield,
						  Line     lines[],     int max_line){
		
		try{
			Reader reader = new BufferedReader( input_reader );

			tokenizer = new StreamTokenizer (reader);
			tokenizer.eolIsSignificant (false);
			tokenizer.wordChars(':',':');
			tokenizer.lowerCaseMode( true );

			int token;
			
			while( true ){
				token = tokenizer.nextToken();
				
				if( StreamTokenizer.TT_EOF == token ) 
					break;
				
				if( StreamTokenizer.TT_WORD == token ){
					if( "update".equals( tokenizer.sval ) )
						parse_update();
					else if( "newplane".equals( tokenizer.sval ) )
						parse_newplane();
					else if( "width".equals( tokenizer.sval ) )
						parse_width();
					else if( "height".equals( tokenizer.sval ) )
						parse_height();
					else if( "exit:".equals( tokenizer.sval ) )
						parse_exit( exits, max_exit );
					else if( "beacon:".equals( tokenizer.sval ) )
						parse_beacon( beacons, max_beacon );
					else if( "airport:".equals( tokenizer.sval ) )
						parse_airport( airfields, max_airfield );
					else if( "line:".equals( tokenizer.sval ) )
						parse_line( lines, max_line );
					else 
						throw ( new ParseException(tokenizer.toString()) );
				}
				else
					throw ( new ParseException(tokenizer.toString()) );
			}
		}
		catch (IOException e){
			System.out.println (e.getMessage ());
		}
		catch (ParseException e2){
			System.out.println (e2.getMessage ());
		}
		
		return true;
		
	}

	/**
	 * Parses the update portion of the game configuration file
	 *
	 * @throws ParseException Signals a parsing exception has occurred.
	 * @throws IOException Signals that an I/O exception has occurred.
	 * 
	 */
	private void parse_update() throws ParseException, IOException{
		
		int update_val;
		expect_token( '=' );
		update_val=expect_number();
		expect_token( ';' );
		init_tick_ms = update_val * 1000;
	}
	
	/**
	 * Parses the newplane portion of the game configuration file.
	 *
	 * @throws ParseException Signals a parsing exception has occurred.
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void parse_newplane() throws ParseException, IOException{
    
		int r;
		expect_token( '=' );
		r=expect_number();
		expect_token( ';' );
		new_plane_chance = r;
	}
	
	/**
	 * Parses the width portion of the game configuration file.
	 *
	 * @throws ParseException Signals a parsing exception has occurred.
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void parse_width() throws ParseException, IOException{
    
		int r;
		expect_token( '=' );
		r=expect_number();
		expect_token( ';' );
		dx = r;
	}
	
	/**
	 * Parses the height portion of the game configuration file.
	 *
	 * @throws ParseException Signals a parsing exception has occurred.
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void parse_height() throws ParseException, IOException{
    
		int r;
		expect_token( '=' );
		r=expect_number();
		expect_token( ';' );
		dy = r;
	}

	/**
	 * Parses the exit portion of the game configuration file.
	 *
	 * @param exits the exits array
	 * @param max_exit the maximum number of exits in play
	 * @throws ParseException Signals a parsing exception has occurred.
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @pre the exits array and its maximum size are passed in. 
	 * @post the exits array has been filled with the exits for game play
	 * and are configured based on the config file.
	 */
	private void parse_exit( Exit exits[], int max_exit ) throws ParseException, IOException{
    
		int x, y;
		char dir;
		int token;
		
		while( true ){
			token = tokenizer.nextToken();
			
			if( token == ';' ) 
				return;
			if( token != '(' ) 
				throw ( new ParseException(tokenizer.toString()) );
     
			x = expect_number();
			y = expect_number();
			dir = expect_letter();
			expect_token( ')' );
			
			if( next_exit < max_exit ){
				exits[next_exit] = new Exit( new Position(x,y), Direction.charToDir(dir) );
				exits[next_exit].id = next_exit;
				next_exit++;
			}
		}
	}

	/**
	 * Parses the beacon portion of the game configuration file.
	 *
	 * @param beacons the beacons array
	 * @param max_beacon the maximum number of beacons in play
	 * @throws ParseException Signals a parsing exception has occurred.
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @pre the beacons array and its maximum size are passed in.
	 * @post the beacons array has been filled with the beacons for game play
	 * and are configured based on the config file.
	 */
	private void parse_beacon( Beacon beacons[], int max_beacon ) throws ParseException, IOException{
    
		int x, y;
		int token;
		
		while( true ){
			token = tokenizer.nextToken();
			
			if( token == ';' )
				return;
			
			if( token != '(' ) 
				throw ( new ParseException(tokenizer.toString()) );
      
			x = expect_number();
			y = expect_number();
			expect_token( ')' );
      
			if( next_beacon < max_beacon ){
				beacons[next_beacon] = new Beacon( new Position(x,y) );
				beacons[next_beacon].id = next_beacon;
				next_beacon++;
			}
		}
	}

	/**
	 * Parses the airport portion of the game configuration file.
	 *
	 * @param airfields the airfields array
	 * @param max_airfield the maximum number of airfields in play
	 * @throws ParseException Signals a parsing exception has occurred.
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @pre the airfields array and its maximum size are passed in.
	 * @post the airfields array has been filled with the airfields for game play
	 * and are configured based on the config file.
	 */
	private void parse_airport( Airfield airfields[], int max_airfield ) throws ParseException, IOException{
    
		int x, y;
		char dir;
		int token;
    
		while( true ){
			token = tokenizer.nextToken();
			
			if( token == ';' ) 
				return;
      
			if( token != '(' ) 
				throw ( new ParseException(tokenizer.toString()) );
      
			x = expect_number();
			y = expect_number();
			dir = expect_letter();
			expect_token( ')' );
      
			if( next_airfield < max_airfield ){
				airfields[next_airfield] = new Airfield( new Position(x,y), Direction.charToDir(dir) );
				airfields[next_airfield].id = next_airfield;
				next_airfield++;
			}
		}
	}
  
	/**
	 * Parses the line portion of the game configuration file.
	 *
	 * @param lines the lines array
	 * @param max_line the maximum number of lines in play
	 * @throws ParseException Signals a parsing exception has occurred.
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @pre the lines array and its maximum value are passed in.
	 * @post the lines array has been filled with the lines for game play and are
	 * configured bases on the config file.
	 */
	private void parse_line( Line lines[], int max_line ) throws ParseException, IOException{
    
		int x1, x2, y1, y2;
		int token;
   
		while( true ){
			token = tokenizer.nextToken();
      
			if( token == ';' ) 
				return;
      
			if( token != '[' ) 
				throw ( new ParseException(tokenizer.toString()) );
      
			expect_token( '(' );
			x1 = expect_number();
			y1 = expect_number();
			expect_token( ')' );
			expect_token( '(' );
			x2 = expect_number();
			y2 = expect_number();
			expect_token( ')' );
			expect_token( ']' );
      
			if( next_line < max_line ){
				lines[next_line] = new Line( new Position(x1,y1), new Position(x2,y2) );
				lines[next_line].id = next_line;
				next_line++;
			}
		}
	}

	/**
	 * Reads the next token in the file and determines if it is a letter or word token.
	 *
	 * @return the first character the token
	 * @throws ParseException Signals a parsing exception has occurred.
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private char expect_letter() throws ParseException, IOException{
    
		tokenizer.nextToken();
    
		if( StreamTokenizer.TT_WORD != tokenizer.ttype || tokenizer.sval.length() != 1 )
			throw ( new ParseException(tokenizer.toString()) );
		else
			return tokenizer.sval.charAt(0);
	}
  
	/**
	 * Reads the next token in the file and determines if it is a number.
	 *
	 * @return the integer that was read from the file.
	 * @throws ParseException Signals a parsing exception has occurred.
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private int expect_number() throws ParseException, IOException{
		
		tokenizer.nextToken();
    
		if( StreamTokenizer.TT_NUMBER != tokenizer.ttype )
			throw ( new ParseException(tokenizer.toString()) );
		else
			return (int)tokenizer.nval;
	}
	
	/**
	 * Reads the next token in the file and determines if it equals the integer argument.
	 *
	 * @param et the integer value to compare the token to
	 * @return the read token
	 * @throws ParseException Signals a parsing exception has occurred.
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @pre et is the int value of the token, in this case an ASCII character.
	 * @post if the next token in the file is not equal to the parameter a ParseException
	 * is thrown, otherwise the token is returned.
	 */
	private int expect_token( int et ) throws ParseException, IOException{
    
		int t = tokenizer.nextToken();
    
		if( t != et )
			throw ( new ParseException(tokenizer.toString()) );
		else
			return t;
  }

	/**
	 * Gets this object's dx field.
	 */
	public int get_dx() {
		return dx; 
	}
	
	/**
	 * Gets this object's dy field. 
	 */
	public int get_dy() { 
		return dy; 
	}
	
	/**
	 * Gets this object's new_plane_chance field. 
	 */
	public int get_new_plane_chance() { 
		return new_plane_chance; 
	}
	
	/**
	 * Gets this object's inti_tick_ms field. 
	 */
	public int get_init_tick_ms() { 
		return init_tick_ms; 
	}
	
	/**
	 * Gets this object's tick_dec field.
	 */
	public int get_tick_dec() { 
		return tick_dec; 
	}
	
	/**
	 * Gets this object's name field.
	 */
	public String get_name() { 
		return name; 
	}
};
