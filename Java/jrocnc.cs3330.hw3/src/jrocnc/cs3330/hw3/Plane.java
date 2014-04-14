/*
 * 	Joshua O'Steen
 * 	CS 3330 - Guilliams, England
 * 	Homework 3
 * 	04/10/2014
 * 	UML, Machine State, Javadoc
 */

package jrocnc.cs3330.hw3;

import java.lang.Object;

// TODO: Auto-generated Javadoc
public class Plane extends MovingObj {
	
	// attributes
	//
	//
	//
	public boolean waiting_flag = false;
	public boolean takeoff_flag = true;
	public ALTCommand alt_cmd = null;
	public DIRCommand dir_cmd = null;
	public StaticObj destination = null;

	
	// constructors
	//
	//
	//
	/**
	 * Instantiates a new plane.
	 * 
	 * @post Instantiated Plane object.
	 */
	protected Plane() {
		super();
	}

	/**
	 * Instantiates a new plane with existing Plane object.
	 *
	 * @param ao the existing Plane object
	 * @pre ao is an existing plane object
	 * @post Instantiated Plane object copied from ao.
	 */
	public Plane(Plane ao) {
		super((MovingObj) ao);
		waiting_flag = ao.waiting_flag;
		takeoff_flag = ao.takeoff_flag;
		destination = ao.destination;
	}

	/**
	 * Instantiates a new plane.
	 *
	 * @param p the position
	 * @param d the direction
	 * @param altitude the altitude
	 * @param i_speed the interval speed
	 * @param des the destination
	 * @pre p and d are valid coordinates on the radar game field. altitude is between 0 and 9
	 * (in thousands of feet). i_speed is the rate that this object will be updated at and is either 1 or 2. 
	 * @post Instantiated Plane object whose fields are set to their respective parameters. 
	 */
	public Plane(Position p, Direction d, int altitude, int i_speed,
			StaticObj des) {
		super(p, d, altitude, i_speed);
		if (altitude == 0) {
			waiting_flag = true;
			takeoff_flag = false;
		}
		destination = des;
	}

	
	// methods
	//
	//
	//
	/**
	 * Sets the altitude or direction command.
	 *
	 * @param c the new command
	 * @pre c is a Command object, either ALTCommand or DIRCommand.
	 * @post this object's appropriate command is set to c.
	 */
	public void setCommand(Command c) {
		if (c instanceof ALTCommand) {
			alt_cmd = (ALTCommand) c;
			waiting_flag = false;
		} else if (c instanceof DIRCommand)
			dir_cmd = (DIRCommand) c;
	}

	/**
	 * Clears the altitude command.
	 */
	public void clearALTCommand() {
		alt_cmd = null;
	}

	/**
	 * Clears the direction command.
	 */
	public void clearDIRCommand() {
		dir_cmd = null;
	}

	/**
	 * Processes the altitude command.
	 * 
	 * @pre Plane is at current altitude.
	 * @post Plane is at altitude set by the altitude command.
	 */
	protected void processALTCommand() {
		// if no command or not active
		if (alt_cmd == null || !alt_cmd.active_flag)
			return;
		// if current altitude is same as alt command
		if (alt == alt_cmd.alt) {
			clearALTCommand();
			return;
		}
		
		// set altitude
		alt = alt_cmd.alt > alt ? alt + 1 : alt - 1;
		
		// if current altitude is same as alt command
		if (alt == alt_cmd.alt)
			clearALTCommand();
		// if current altitude is greater than zero and
		// takeoff flag is false
		if (alt > 0 && !takeoff_flag)
			takeoff_flag = true;
	}

	/**
	 * Processes the direction command.
	 * 
	 * @pre Plane is at current position traveling in current direction.
	 * @post Plane is at new position following execution of direction command.
	 */
	protected void processDIRCommand() {
		// if direction command is null
		if (dir_cmd == null)
			return;
		// if direction command is same as current position
		if (!dir_cmd.active_flag)
			if (pos.equals(dir_cmd.pos))
				dir_cmd.active_flag = true;
			else
				return;

		// if direction command is a turn command
		if (dir_cmd instanceof TurnCommand) {
			Direction new_dir = ((TurnCommand) dir_cmd).dir;
			// if new direction is null
			if (new_dir == null)
				return;
			// if new direction equals current direction
			if (dir.equals(new_dir)) {
				clearDIRCommand();
				return;
			}
			
			// execute turn command
			Turn turn = Turn.turnTowards(dir, new_dir);
			dir.tick(turn);
			if (dir.equals(new_dir))
				clearDIRCommand();
		}

		// if direction is circle command
		if (dir_cmd instanceof CircleCommand) {
			Turn turn = ((CircleCommand) dir_cmd).turn;
			if (turn == null)
				return;
			// excute circle command
			dir.tick(turn);
		}
	}

	/**
	 * Checks if plane has received any commands and if so processes
	 * those commands concurrently with ATCData tick updates.
	 * 
	 * @pre Plane exists traveling at current altitude and direction.
	 * @post Plane is traveling in new direction or altitude if new command 
	 * is received.
	 */
	public synchronized void tick() {
		
		if (waiting_flag) {
			changed_flag = false;
			return;
		}
		if (++speed_count < inv_speed) {
			changed_flag = false;
			return;
		} 
		else {
			speed_count = 0;
			tick_count++;
			changed_flag = true;
			processDIRCommand();
			pos.tick(dir);
			processALTCommand();
		}
	}

	/**
	 * Gets the id char determined by the type of plane.
	 *
	 * @return the id char
	 */
	public char getIdChar() {
		// if plane is a jet
		if (inv_speed == 1)
			return (char) ('a' + id);
		// if plane is a prop
		else
			return (char) ('A' + id);
	}

	/**
	 * Prints the debug.
	 */
	public void printDebug() {
		System.out.println("#" + tick_count + "[" + pos.x + ":" + pos.y + ":"
				+ alt + "] [" + dir.x + ":" + dir.y + "]");
	}
};















