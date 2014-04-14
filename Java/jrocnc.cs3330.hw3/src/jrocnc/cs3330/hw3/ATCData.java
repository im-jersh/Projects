/*
 * 	Joshua O'Steen
 * 	CS 3330 - Guilliams, England
 * 	Homework 3
 * 	04/10/2014
 * 	UML, Machine State, Javadoc
 */

package jrocnc.cs3330.hw3;

import java.lang.Object;
import java.util.Random;


// TODO: Auto-generated Javadoc
/**
 * "Model" in ATCJ's model-view-controller structure.
 */
public class ATCData extends Object{
  
	// attributes
	//
	//
	//
	protected ATC atc_obj = null;
  
	// setup data
	protected int dx, dy;
	protected int new_plane_chance;
	protected int init_tick_ms;
	protected int tick_ms;
	protected int tick_dec;
	protected String config_name;
	protected String user_name;
	protected int max_static_obj = 10;
	protected int max_plane = 26;
	protected int min_tick_ms = 800;
	ATCConfig config = null;
	ATCRecord record = null;
  
	// dynamic game data
	protected Exit exits[];
	protected Beacon beacons[];
	protected Airfield airfields[];
	protected Line lines[];
	protected int beacon_count = 0;
	protected int exit_count = 0;
	protected int airfield_count = 0;
	protected int line_count = 0;
	protected Plane planes[];
	protected int next_plane = 0;
	protected int tick_count = 0;
	protected int safe_plane_count = 0;
	protected Random rand = new Random();
	protected long start_time_ms = 0;
	protected long stop_time_ms = 0;
	java.util.Timer timer = null;
	ATCTask task = null;

	
	// constructors
	//
	//
	//
	/**
	 * Instantiates a new ATC data.
	 * @post Instantiated ATCData object. 
	 */
	protected ATCData(){ 
		super(); 
	}
	
	/**
	 * Instantiates a new ATC data with an existing ATC object
	 *
	 * @param a the existing ATC object
	 * @pre a is an existing ATC object
	 * @post Instantiated ATCData object whose atc_obj field is set to a.
	 * 
	 */
	public ATCData( ATC a ){
		super();
		atc_obj = a;
	}

	
	// nested class
	//
	//
	//
	class ATCTask extends java.util.TimerTask{
		
		protected ATCData data;
    
		/**
		 * Instantiates a new ATC task.
		 *
		 * @param d the ATCData object
		 */
		public ATCTask( ATCData d ){
			data = d; 
		}
    
		/* (non-Javadoc)
		 * @see java.util.TimerTask#run()
		 */
		public void run(){ 
			try{ 
				data.tick(); 
			} 
			catch( ATCGameOverException e ) { 
				data.gameOver( e.getMessage() );
			}
		}
	};

	
	// methods
	//
	//
	//
	/**
	 * Sets this object's config field.
	 *
	 * @param conf the new config
	 * @pre conf is the ATCConfig object which will hold the values to set up the
	 * user selected game field in order to play.
	 * @post this objects config field is set to conf.
	 * 
	 */
	public void setConfig( ATCConfig conf ){
		config = conf;
	}

	/**
	 * Sets this object's record field.
	 *
	 * @param rec the new record
	 * @pre rec is the record object which will hold the end state information
	 * from game play once the game has ended.
	 * @post this objects record field is set to rec. 
	 *
	 */
	public void setRecord( ATCRecord rec ){
		record = rec;
	}

	/**
	 * Initializes attributes for the ATCData object and readies
	 * the application data for game play. Configures game play based
	 * on the values read from the designated document in the /config
	 * folder.
	 * 
	 * @post All data is initialized, the UI has been built and displayed, and
	 * the game is ready to play.
	 */
	public void initData(){
    
		beacons = new Beacon[max_static_obj];
		exits = new Exit[max_static_obj];
		airfields = new Airfield[max_static_obj];
		lines = new Line[max_static_obj];
		planes = new Plane[max_plane];
		int i;
		
		for( i=0; i < max_plane; i++ )
			planes[i] = null;
		
		for( i=0; i < max_static_obj; i++ ){
			beacons[i] = null;
			exits[i] = null;
			airfields[i] = null;
			lines[i] = null;
		}

		// read config
		if( config == null )
			return;
		
		config.config( beacons, max_static_obj,
                   		exits, max_static_obj,
                   		airfields, max_static_obj,
                   		lines, max_static_obj );
		dx = config.get_dx();
		dy = config.get_dy();
		new_plane_chance = config.get_new_plane_chance();
		init_tick_ms = tick_ms = config.get_init_tick_ms();
		tick_dec = config.get_tick_dec();
		config_name = config.get_name();
 
		// user name is taken from operating system
		try{
			user_name = System.getProperties().getProperty("user.name");
		}
		catch( java.security.AccessControlException e ){
			user_name = new String("unknown");
		}

		// ready ui objects based on config file
		atc_obj.getUI().initUI( dx, dy );

		for( i=0; i<max_static_obj; i++ )
			if( beacons[i] != null ){
				beacon_count = i+1;
				atc_obj.getUI().StaticObjNew( beacons[i] );
			}
    
		for( i=0; i<max_static_obj; i++ )
			if( exits[i] != null ){
				exit_count = i+1;
				atc_obj.getUI().StaticObjNew( exits[i] );
			}
		
		for( i=0; i<max_static_obj; i++ )
			if( airfields[i] != null ){
				airfield_count = i+1;
				atc_obj.getUI().StaticObjNew( airfields[i] );
			}
    
		for( i=0; i<max_static_obj; i++ )
			if( lines[i] != null ){
				line_count = i+1;
				atc_obj.getUI().StaticObjNew( lines[i] );
			}

		atc_obj.getUI().refresh();
		atc_obj.getUI().ready();
		
	}

	/**
	 * Starts game play. Records the starting time and reinitializes values from 
	 * possible previous game play.
	 * 
	 * @pre the UI is presented and the user is left to elect to start or exit the program
	 * @post the user elected to start; game play starts.
	 */
	public synchronized void start() {
		start_time_ms = System.currentTimeMillis();

		int i;
		for (i = 0; i < max_plane; i++)
			if (planes[i] != null && planes[i].takeoff_flag) {
				atc_obj.getUI().PlaneRemove(planes[i]);
				planes[i] = null;
			}

		next_plane = 0;
		tick_count = 0;
		safe_plane_count = 0;
		tick_ms = init_tick_ms;

		atc_obj.getUI().InfoUpdate(tick_count, safe_plane_count);

		atc_obj.getUI().start();

		task = null;
		task = new ATCTask(this);
		timer = new java.util.Timer();
		timer.scheduleAtFixedRate(task, 100, get_tick_ms());
	}

	/**
	 * Records the stop time when game play ends and saves the game play
	 * data to the record.
	 *
	 * @param gameOverMessage the game over message
	 * @pre the game over message is passed in and will be used to display to the
	 * user what caused the game to end.
	 * @post game play information is recorded and displayed to the user.
	 */
	public void gameOver(String gameOverMessage) {
		stop_time_ms = System.currentTimeMillis();
		
		if (timer != null) {
			timer.cancel();
			timer = null;
		}

		// save record
		if (start_time_ms != 0)
			if (record != null)
				record.save(user_name, config_name, tick_count, stop_time_ms
						- start_time_ms, safe_plane_count);
		start_time_ms = stop_time_ms = 0;

		if (gameOverMessage != null)
			atc_obj.getUI().gameOver(gameOverMessage);

	}

	/**
	 * Instantiates and places a new plane on the radar. Determines if a plane
	 * can be placed on the radar, and if so placement is determined
	 * by attempting to find a location that is not near another plane.
	 * 
	 * @post If it is decided that a new plane can be added to game play, it is added
	 * to the planes array and it the UI is updated to reflect this addition based on 
	 * the planes information.
	 */
	protected void newPlane() {
		int r = rand.nextInt(new_plane_chance);
		if (ATC.debug_flag)
			System.out.println("NP: r=" + r); // DEBUG
		if (tick_count == 1 || r == new_plane_chance - 1) {
			int plane_id;
			synchronized (this) {
				plane_id = next_plane;
				if (planes[plane_id] != null) {
					plane_id++;
					if (plane_id >= max_plane)
						plane_id %= max_plane;
					while (planes[plane_id] != null) {
						plane_id++;
						if (plane_id >= max_plane)
							plane_id %= max_plane;
						if (plane_id == next_plane)
							return;
					}
				}

				if (ATC.debug_flag)
					System.out.println("NP: id=" + plane_id); // DEBUG

				// pick start and destination
				int total = exit_count + airfield_count;
				if (total < 2)
					return;
				int i1, i2;
				StaticObj o1 = null, o2 = null;

			    //Try to find a place not next to other planes. 4 times.
				int i, j;
				boolean found_flag = false;
				for (i = 0; i < 4 && !found_flag; i++) {
					i1 = rand.nextInt(total);
					do {
						i2 = rand.nextInt(total);
					} while (i1 == i2);

					o1 = i1 < exit_count ? (StaticObj) (exits[i1])
							: (StaticObj) (airfields[i1 - exit_count]);
					o2 = i2 < exit_count ? (StaticObj) (exits[i2])
							: (StaticObj) (airfields[i2 - exit_count]);
					if (o1 == null || o2 == null)
						continue;

					found_flag = true;
					for (j = 0; j < max_plane; j++)
						if (planes[j] != null && planes[j].pos != null
								&& planes[j].takeoff_flag)
							if (Math.abs(planes[j].pos.x - o1.pos.x) <= 1
									&& Math.abs(planes[j].pos.y - o1.pos.y) <= 1
									&& Math.abs(planes[j].alt - o1.alt) <= 1)
								found_flag = false;
				}
				if (!found_flag)
					return;

				if (ATC.debug_flag)
					System.out.println("NP: found."); // DEBUG

				Plane new_plane = new Plane(o1.pos, o1.dir, o1.alt,
						rand.nextInt(2) + 1, o2);

				new_plane.id = plane_id;
				planes[plane_id] = new_plane;
				next_plane = plane_id + 1;
				if (next_plane >= max_plane)
					next_plane %= max_plane;
			}
			atc_obj.getUI().PlaneNew(planes[plane_id]);
		}
	}

	/**
	 * Tick count manages game play by updating existing plane data, attempting to add a new 
	 * plane, determining if planes are safe or dead, and updating the UI. 
	 *
	 * @throws ATCGameOverException Signals if a plane is dead. 
	 * @post The planes array objects are updated to reflect any commands that were entered 
	 * by the user. An attempt to make a new plane is made and if it is it is added to the 
	 * planes array. All planes in the array are checked to see if they are safe at their 
	 * destination or dead and, if no planes have crashed, the UI is updated to reflect 
	 * all the planes updated information.
	 */
	public void tick() throws ATCGameOverException {
		// tick count updated
		tick_count++;

		// existing plane data is updated
		int plane_id;
		for (plane_id = 0; plane_id < max_plane; plane_id++)
			if (planes[plane_id] != null) {
				planes[plane_id].tick();
			}

		// attempt to make a new plane
		newPlane();

		// update planes in UI and determine if safe or dead
		synchronized (this) {
			for (plane_id = 0; plane_id < max_plane; plane_id++) {
				if (planes[plane_id] == null)
					continue;

				if (planes[plane_id].changed_flag)
					atc_obj.getUI().PlaneUpdate(planes[plane_id]);

				if (isSafe(plane_id)) {
					atc_obj.getUI().PlaneRemove(planes[plane_id]);
					safe_plane_count++;
					planes[plane_id] = null;
					continue;
				}

				if (isDead(plane_id))
					throw new ATCGameOverException("Game Over!");
			}

			if (tick_ms > min_tick_ms)
				tick_ms -= tick_dec;
		}
		
		// update UI
		atc_obj.getUI().InfoUpdate(tick_count, safe_plane_count);
		atc_obj.getUI().refresh();
	}

	/**
	 * Determines if a plane has safely reached it's destination.
	 *
	 * @param id the index of plane in the planes array
	 * @return true if the plane's position, altitude, and direction match that of
	 * its target destination; false otherwise
	 * @pre 0 <= id <= planes.length
	 * @post i is used to check if the plane in the array at index i is safe. True is 
	 * passed back to the caller if so; otherwise false.
	 * 
	 */
	protected boolean isSafe(int id) {
		if (!planes[id].takeoff_flag)
			return false;
		StaticObj destination = planes[id].destination;
		if (destination.pos != null && destination.pos.equals(planes[id].pos))
			if (destination.exit_alt == planes[id].alt)
				if (destination.exit_dir != null) {
					if (planes[id].dir.equals(destination.exit_dir))
						return true;
				} else
					return true;
		return false;
	}

	/**
	 * Determines if a plane is dead and how.
	 *
	 * @param id the index of plane in planes array
	 * @return False if the plane's take_off attribute is false, it flew
	 * out of bounds, or it collided with another plane; this method always
	 * returns false. If a plane has died, the game is over and is handled
	 * by an ATCGameOverException.
	 * @throws ATCGameOverException Signals if a plane is dead. 
	 * @pre 0 <= id <= planes.length
	 * @post i is used to determine if plane at index i in the planes array is
	 * dead. If so, a game over exception is thrown, ending the game thusly. otherwise,
	 * false is returned indicating to the caller that the plane is still flying.
	 */
	protected boolean isDead(int id) throws ATCGameOverException {
		
		// if plane takeoff flag is false
		if (!planes[id].takeoff_flag)
			return false;

		// if plane altitude is less than or zero
		if (planes[id].alt <= 0)
			throw new ATCGameOverException("Plane " + planes[id].getIdChar()
					+ " crashed to the ground.");

		// if plane flew out of bounds
		if (planes[id].pos.x < 0 || planes[id].pos.y < 0
				|| planes[id].pos.x >= dx || planes[id].pos.y >= dy)
			throw new ATCGameOverException("Plane " + planes[id].getIdChar()
					+ " flew out of radar area.");

		// if two planes collided 
		int id2;
		for (id2 = 0; id2 < max_plane; id2++)
			if (planes[id2] != null && id2 != id && planes[id2].takeoff_flag) {
				if (Math.abs(planes[id].alt - planes[id2].alt) <= 1
						&& Math.abs(planes[id].pos.x - planes[id2].pos.x) <= 1
						&& Math.abs(planes[id].pos.y - planes[id2].pos.y) <= 1)
					throw new ATCGameOverException("Planes "
							+ planes[id].getIdChar() + " and "
							+ planes[id2].getIdChar() + " crashed.");
			}

		return false;
	}

	/**
	 * Gets this object's tick_ms field.
	 *
	 * @return tick_ms
	 */
	protected int get_tick_ms() {
		return tick_ms;
	}

	/**
	 * Sets the corresponding command based on cmd's instanceof return
	 * and updates the plane on the radar.
	 *
	 * @param id the index of plane in the planes array
	 * @param cmd the new command for plane
	 * @return false if plane doesn't exist. true if command and UI are updated. 
	 * @pre 0 <= id <= planes.length 
	 * @post the command for plane at index id is updated with cmd and the UI 
	 * is updated.
	 */
	public boolean setCommand(int id, Command cmd) {
		if (planes[id] == null)
			return false;
		planes[id].setCommand(cmd);
		atc_obj.getUI().PlaneUpdate(planes[id]);

		return true;
	}

	/**
	 * Sets the UI command string.
	 *
	 * @param s the new command string
	 * @pre the user input command to send to the plane.
	 * @post the UI is updated to show the command was valid and display 
	 * that the plane recieved the command.
	 */
	public void setCommandString(String s) {
		atc_obj.getUI().CommandUpdate(s);
	}

	/**
	 * Gets the exits field.
	 *
	 * @return the exits
	 */
	public StaticObj[] getExits(){ 
		return exits; 
	}
	
	/**
	 * Gets the beacons field.
	 *
	 * @return the beacons
	 */
	public StaticObj[] getBeacons(){ 
		return beacons; 
	}
	
	/**
	 * Gets the airfields field.
	 *
	 * @return the airfields
	 */
	public StaticObj[] getAirfields(){ 
		return airfields; 
	}

	/**
	 * Gets a plane in the planes array.
	 *
	 * @param i the index of the plane
	 * @return the plane object at index i of the planes array
	 * @pre 0 <= i <= planes.length
	 * @post the plane at index i in the planes array is returned to 
	 * the caller.
	 */
	public Plane getPlane( int i ){
		return planes[i]; 
	}

};




























