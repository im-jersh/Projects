/*
 * 	Joshua O'Steen
 * 	CS 3330 - Guilliams, England
 * 	Homework 3
 * 	04/10/2014
 * 	UML, Machine State, Javadoc
 */


package jrocnc.cs3330.hw3;

import java.lang.Object;
import java.lang.String;

// TODO: Auto-generated Javadoc
public class ATCUI_dummy_impl extends Object implements ATCUI {
	
	// attributes
	//
	//
	//
	protected ATC atc_obj = null;

	
	// constructors
	//
	//
	//
	/**
	 * Instantiates a new ATCUI_dummy_impl obj.
	 */
	protected ATCUI_dummy_impl() {
		super();
	}

	/**
	 * Instantiates a new ATCUI_dummy_impl obj given an ATC obj.
	 *
	 * @param a the existing ATC object
	 */
	public ATCUI_dummy_impl(ATC a) {
		super();
		atc_obj = a;
	}

	
	// methods
	//
	//
	//
	/**
	 * Prints out the dimensions of the playing field to the terminal.
	 * 
	 * @param dx the x dimension
	 * @param dy the y dimension
	 */
	public void initUI(int dx, int dy) {
		System.out.println("Dimension is " + dx + "x" + dy);
	}

	/**
	 * Prints out the a StaticObj information to the terminal.
	 * 
	 * @param so the StaticObj obj
	 */
	public void StaticObjNew(StaticObj so) {
		if (so instanceof Beacon) {
			System.out.println("New: Beacon #" + so.id + " pos=[" + so.pos.x
					+ ":" + so.pos.y + "]");
			return;
		}
		if (so instanceof Airfield) {
			System.out.println("New: Airfield #" + so.id + " pos=[" + so.pos.x
					+ ":" + so.pos.y + "]");
			return;
		}
		if (so instanceof Exit) {
			System.out.println("New: Exit #" + so.id + " pos=[" + so.pos.x
					+ ":" + so.pos.y + "]");
			return;
		}
		if (so instanceof Line) {
			System.out.println("New: Line #" + so.id);
			return;
		}
	}

	/**
	 * Prints out a command string to the terminal.
	 * 
	 * @param cmd_str the command string
	 */
	public void CommandUpdate(String cmd_str) {
		System.out.println("Set: Command String: " + cmd_str);
	}

	/**
	 * Prints out a new plane's information to the terminal.
	 * 
	 * @param p the plane
	 */
	public void PlaneNew(Plane p) {
		System.out.println("New: Plane #" + p.getIdChar() + " pos=[" + p.pos.x
				+ ":" + p.pos.y + "] alt=" + p.alt + " dir="
				+ p.dir.getDirName() + " ispd=" + p.inv_speed);
	}

	/**
	 * Prints a plane's updated information to the terminal.
	 * 
	 * @param p the plane
	 */
	public void PlaneUpdate(Plane p) {
		System.out.println("Update: Plane #" + p.getIdChar() + " pos=["
				+ p.pos.x + ":" + p.pos.y + "] alt=" + p.alt + " dir="
				+ p.dir.getDirName());
	}

	/**
	 * Prints a plane to the terminal when it is removed from the game field.
	 */
	public void PlaneRemove(Plane p) {
		System.out.println("Remove: Plane #" + p.getIdChar());
	}

	/**
	 * Prints the updated tick and safe count to the terminal.
	 * 
	 * @param tick_count the tick count
	 * @param safe_count the safe plane count
	 */
	public void InfoUpdate(int tick_count, int safe_count) {
	}

	/**
	 * 
	 */
	public void ready() {
	}

	/**
	 * 
	 */
	public void start() {
	}

	/**
	 * 
	 */
	public void gameOver(String s) {
	}

	/**
	 * 
	 */
	public void refresh() {
	}

	/**
	 * 
	 */
	public void close() {
	}

};
