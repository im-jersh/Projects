/*
 * 	Joshua O'Steen
 * 	CS 3330 - Guilliams, England
 * 	Homework 3
 * 	04/10/2014
 * 	UML, Machine State, Javadoc
 */


package jrocnc.cs3330.hw3;

import java.lang.Object;
import java.util.*;

// TODO: Auto-generated Javadoc
public class ATCInputhandler_impl extends Object implements ATCInputhandler {
	
	// attributes
	//
	//
	//
	protected ATC atc_obj = null;
	protected LinkedList cmd_str = new LinkedList();
	protected String full_cmd_str = new String("");
	protected int plane_id;
	protected Command cmd = null;
	
	
	// constructors
	//
	//
	//
	/**
	 * Instantiates a new ATC inputhandler_impl.
	 * 
	 * @post Instantiated ATCInput_handler_impl object.
	 */
	protected ATCInputhandler_impl() {
		super();
	}

	/**
	 * Instantiates a new ATC inputhandler_impl.
	 *
	 * @param a the existing ATC obj
	 * @pre a is the ATC object that this object's field will be set to.
	 * @post Instantiated ATCInputhandler_impl object whose atc_obj is set to a. 
	 */
	public ATCInputhandler_impl(ATC a) {
		super();
		atc_obj = a;
	}

	
	// methods
	//
	//
	//
	
	/**
	 * Processes a key press.
	 * @param c the character to process
	 * @pre any keyboard character
	 */
	public void processKey(char c) {
		if (c == '\n')
			return;
		if (c == '\b') {
			if (!cmd_str.isEmpty())
				cmd_str.removeLast();
		}
		else
			cmd_str.add(new Character(c));
		
		if (parse(false))
			atc_obj.getData().setCommandString(full_cmd_str);
		else {
			cmd_str.removeLast();
			atc_obj.getData().setCommandString(full_cmd_str);
		}
	}

	/**
	 * Processes the command.
	 */
	public boolean processCommand() {
		if (parse(true)) {
			if (cmd != null)
				atc_obj.getData().setCommand(plane_id, cmd);

			atc_obj.getData().setCommandString("");

			reset();
			return true;
		} else {
			atc_obj.getData().setCommandString("");
			reset();
			return false;
		}
	}

	/**
	 * Parses the user input command to verify it is a valid command to 
	 * send to a plane.
	 *
	 * @param full_flag
	 * @return true if the command is valid; false otherwise.
	 * @pre full_flag indicates that the command entered is an entire command that is
	 * valid if set to true.
	 * @post full command is parsed and the appropriate commands are sent to the 
	 * appropriate plane.
	 */
	protected boolean parse(boolean full_flag) {
		if (ATC.debug_flag) {
			System.out.println("Parsing: " + cmd_str.toString() + full_flag);
		}

		int parse_state = 1;
		ListIterator it = cmd_str.listIterator();
		char c;
		Plane p = null;
		StaticObj objs[] = null, obj_to = null, obj_at = null;

		full_cmd_str = new String("");

		while (it.hasNext() && parse_state != 99) {
			c = ((Character) it.next()).charValue();
			switch (parse_state) {
			case 1:
				if (!Character.isLetter(c))
					return false;
				full_cmd_str += c;
				full_cmd_str += ": ";
				plane_id = Character.toLowerCase(c) - 'a';
				parse_state = 2;
				break;
			case 2:
				if (c != 'a' && c != 't' && c != 'c')
					return false;
				switch (c) {
				case 'a':
					full_cmd_str += "altitude: ";
					if (full_flag)
						cmd = new ALTCommand();
					parse_state = 3;
					break;
				case 't':
					full_cmd_str += "turn ";
					if (full_flag)
						cmd = new TurnCommand();
					parse_state = 6;
					break;
				case 'c':
					full_cmd_str += "circle ";
					if (full_flag)
						cmd = new CircleCommand();
					parse_state = 4;
					break;
				}
				break;
			case 3:
				int new_alt;
				try {
					new_alt = Integer.parseInt(Character.toString(c));
				} catch (Exception e) {
					return false;
				}
				if (new_alt < 0 || new_alt > 9)
					return false;
				full_cmd_str += Integer.toString(new_alt) + "000 feet ";
				if (full_flag)
					((ALTCommand) cmd).alt = new_alt;
				parse_state = 99;
				break;
			case 4:
				if (c == 'a') {
					it.previous();
					parse_state = 5;
					break;
				}
				if (c != 'l' && c != 'r')
					return false;
				full_cmd_str += c == 'l' ? "left " : "right ";
				if (full_flag)
					((CircleCommand) cmd).turn = c == 'r' ? Turn.RIGHT
							: Turn.LEFT;
				parse_state = 5;
				break;
			case 5:
				if (c != 'a')
					return false;
				full_cmd_str += "at ";
				parse_state = 9;
				break;
			case 6:
				if (c == 't') {
					full_cmd_str += "towards ";
					parse_state = 7;
					break;
				}
				p = atc_obj.getData().getPlane(plane_id);
				if (p == null)
					return false;
				Direction new_dir = new Direction(p.dir);
				switch (c) {
				case 'r':
					new_dir.tick(Turn.SMALL_RIGHT);
					full_cmd_str += "right ";
					break;
				case 'l':
					new_dir.tick(Turn.SMALL_LEFT);
					full_cmd_str += "left ";
					break;
				case 'R':
					new_dir.tick(Turn.RIGHT);
					full_cmd_str += "RIGHT ";
					break;
				case 'L':
					new_dir.tick(Turn.LEFT);
					full_cmd_str += "LEFT ";
					break;
				case 'a':
				case 'q':
				case 'w':
				case 'e':
				case 'd':
				case 'c':
				case 'x':
				case 'z':
					new_dir = Direction.charToDir(c);
					full_cmd_str += new_dir.getDirName();
					full_cmd_str += " ";
					break;
				default:
					return false;
				}
				if (full_flag)
					((TurnCommand) cmd).dir = new_dir;
				parse_state = 5;
				break;
			case 7:
				switch (c) {
				case 'b':
					objs = atc_obj.getData().getBeacons();
					full_cmd_str += "beacon ";
					break;
				case 'e':
					objs = atc_obj.getData().getExits();
					full_cmd_str += "exit ";
					break;
				case 'a':
					objs = atc_obj.getData().getAirfields();
					full_cmd_str += "airfield ";
					break;
				default:
					return false;
				}
				parse_state = 8;
				break;
			case 8:
				int obj_num;
				try {
					obj_num = Integer.parseInt(Character.toString(c));
				} catch (Exception e) {
					return false;
				}
				if (obj_num < 0)
					return false;
				full_cmd_str += "#" + c + " ";
				obj_to = objs[obj_num];
				if (obj_to == null)
					return false;

				if (full_flag) {
					p = atc_obj.getData().getPlane(plane_id);
					if (p == null)
						return false;
					((TurnCommand) cmd).dir = new Direction(p.pos, obj_to.pos);
				}
				parse_state = 5;
				break;
			case 9:
				if (c != 'b')
					return false;
				full_cmd_str += "beacon ";
				parse_state = 10;
				break;
			case 10:
				int beacon_num;
				try {
					beacon_num = Integer.parseInt(Character.toString(c));
				} catch (Exception e) {
					return false;
				}
				if (beacon_num < 0)
					return false;
				obj_at = atc_obj.getData().getBeacons()[beacon_num];
				if (obj_at == null)
					return false;

				full_cmd_str += "#" + c;
				if (full_flag) {
					((DIRCommand) cmd).pos = obj_at.pos;
					((DIRCommand) cmd).pos_obj = obj_at;
					cmd.active_flag = false;
					if (cmd instanceof TurnCommand && obj_to != null)
						((TurnCommand) cmd).dir = new Direction(obj_at.pos,
								obj_to.pos);
				}
				parse_state = 99;
				break;
			}
		}

		if (full_flag) {
			if (parse_state != 99 && parse_state != 5 && parse_state != 4)
				return false;
			else
				return true;
		}
		return true;
	}

	/**
	 * Processes the action command to either start a new game or exit
	 * the application.
	 * 
	 * @param command the command issued by a UI button
	 * @pre the "New" and "Exit" buttons shown in the UI are selectable.
	 * @post method is invoked based on selected button press. Either the game
	 * is started or the program is exited.
	 */
	public boolean processActionCommand(String command) {
		if ("New".equals(command)) {
			reset();
			atc_obj.getData().start();
		} else if ("Exit".equals(command)) {
			atc_obj.stopATC();
		}
		return true;
	}

	/**
	 * Resets the cmd and cmd_str fields.
	 */
	public void reset() {
		cmd = null;
		cmd_str = new LinkedList();
	}

};
