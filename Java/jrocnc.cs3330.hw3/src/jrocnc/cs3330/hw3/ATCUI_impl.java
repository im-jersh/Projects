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
import java.lang.Math;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;
import javax.swing.*;

// TODO: Auto-generated Javadoc
public class ATCUI_impl extends JFrame implements ATCUI, ActionListener,
		KeyListener, WindowListener {

	// attributes
	//
	//
	//
	protected ATC atc_obj = null;
	protected boolean keyListenerAdded = false;
	protected int grid_size = 30, icon_size = 20;
	protected int text_height, text_gap = -3;
	protected int plane_width, plane_height;
	protected int radar_area_width, radar_area_height;
	protected int info_area_width = 140;
	protected int dx, dy;
	protected Color rim_color = Color.darkGray;
	protected Color back_color = Color.blue;
	protected Color text_color = Color.yellow;
	protected Color so_color = Color.orange;
	protected Color so_text_color = Color.magenta;
	protected Color line_color = Color.white;
	ImageIcon planeIcons[] = new ImageIcon[9];
	String planeIconPaths[] = { "images/NW.gif", "images/N.gif",
			"images/NE.gif", "images/W.gif", "images/back.gif", "images/E.gif",
			"images/SW.gif", "images/S.gif", "images/SE.gif" };
	RadarPane radarArea;
	JPanel infoArea;
	Label infoTopLine;
	JLabel inputArea;
	JPanel controlArea;
	JButton newButton;
	JButton exitButton;
	Map planes = new HashMap();
	
	
	// constructors
	//
	//
	//
	/**
	 * Instantiates a new ATCUI_impl obj.
	 * 
	 * @post Instantiated ATCUI_impl object.
	 */
	protected ATCUI_impl() {
		super();
	}

	/**
	 * Instantiates a new ATCUI_impl obj given an ATC obj.
	 *
	 * @param a the ATC object
	 * @pre a is the existing ATC object containing the necessary data for game play
	 * @post Instantiated ATCUI_impl object whose atc_obj is a. this object is then
	 * sent to the window listener.
	 */
	public ATCUI_impl(ATC a) {
		super();
		atc_obj = a;
		addWindowListener(this);
	}

	
	//nested classes
	//
	//
	//
	class RadarPane extends JLayeredPane {
		public JLabel back;
		public ImageIcon backIcon;
		public BufferedImage backImage;

		/**
		 * Instantiates a new radar pane.
		 */
		public RadarPane() {
			super();
		}
	};
	
	class UIPlane extends Object {
		public JLabel radar_label;
		public Label info_label;
		
	};
	
	
	// methods
	//
	//
	//
	/**
	 * Converts position to coordinates.
	 *
	 * @param px the px
	 * @return the coordinates
	 */
	protected int convPos(int px) {
		return px * grid_size + grid_size / 2;
	}

	/**
	 * Loads the UI icons.
	 */
	protected void loadIcons() {

		int i;
		java.net.URL imageURL = null;
		for (i = 0; i < planeIconPaths.length; i++) {
			try {
				imageURL = new java.net.URL(atc_obj.codeBase
						+ planeIconPaths[i]);
			} catch (Exception e) {
				imageURL = null;
			}

			if (imageURL == null) {
				System.err.println("Can't load icon " + i);
				atc_obj.stopATC();
			}

			planeIcons[i] = new ImageIcon(imageURL);
		}
	}

	/**
	 * Selects the appropriate plane image determined by the plane's direction.
	 *
	 * @param d the direction
	 * @return plane image
	 */
	protected ImageIcon dirToPlaneIcon(Direction d) {
		int i = (d.x + 1) + (d.y + 1) * 3;
		if (i < 0 || i >= 9)
			return null;
		return planeIcons[i];
	}

	/**
	 * Initializes all UI elements in the application window.
	 * 
	 * @param x the x dimension
	 * @param y the y dimension
	 * @pre the x and y values that determine the window size.
	 * @post the UI is displayed.
	 */
	public void initUI(int x, int y) {
		setVisible(true);

		loadIcons();

		dx = x;
		dy = y;
		text_height = getFontMetrics(getFont()).getHeight();
		plane_width = icon_size;
		plane_height = icon_size + text_height + text_gap;
		radar_area_width = grid_size * dx;
		radar_area_height = grid_size * dy;

		getContentPane().setLayout(new BorderLayout());

		radarArea = new RadarPane();
		radarArea.setMinimumSize(new Dimension(radar_area_width,
				radar_area_height));
		radarArea.setPreferredSize(new Dimension(radar_area_width,
				radar_area_height));
		getContentPane().add(radarArea, BorderLayout.CENTER);

		radarArea.backImage = new BufferedImage(radar_area_width,
				radar_area_height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = radarArea.backImage.createGraphics();
		g.setBackground(back_color);
		g.setColor(rim_color);
		g.fillRect(0, 0, radar_area_width, radar_area_height);
		g.setColor(back_color);
		g.fillRect(convPos(0), convPos(0), radar_area_width - grid_size,
				radar_area_height - grid_size);
		g.setColor(line_color);
		int i, j;
		for (i = 0; i < dx; i++)
			for (j = 0; j < dy; j++)
				g.draw(new Rectangle(convPos(i) - 1, convPos(j) - 1, 1, 1));

		radarArea.backIcon = new ImageIcon(radarArea.backImage);
		radarArea.back = new JLabel(radarArea.backIcon);
		radarArea.back.setBounds(0, 0, radar_area_width, radar_area_height);
		radarArea.add(radarArea.back, new Integer(0));

		infoArea = new JPanel();
		infoArea.setMinimumSize(new Dimension(info_area_width,
				radar_area_height));
		infoArea.setPreferredSize(new Dimension(info_area_width,
				radar_area_height));
		infoArea.setLayout(new GridLayout(27, 1));
		infoTopLine = new Label(" ");
		infoArea.add(infoTopLine);
		getContentPane().add(infoArea, BorderLayout.EAST);

		inputArea = new JLabel("          ");
		getContentPane().add(inputArea, BorderLayout.SOUTH);

		controlArea = new JPanel();
		newButton = new JButton("New");
		newButton.setActionCommand("New");
		newButton.addActionListener(this);
		newButton.setEnabled(false);
		newButton.setFocusable(false);
		exitButton = new JButton("Exit");
		exitButton.setActionCommand("Exit");
		exitButton.addActionListener(this);
		exitButton.setFocusable(false);
		controlArea.add(newButton);
		controlArea.add(exitButton);
		getContentPane().add(controlArea, BorderLayout.NORTH);

		pack();
	}

	/**
	 * Draws the a static object on the game field.
	 * 
	 * @param so the static object
	 * @pre the static object such as an airfield, beacon, exit, or line.
	 * @post the object is drawn on the radar game field.
	 */
	public void StaticObjNew(StaticObj so) {
		Graphics2D g = radarArea.backImage.createGraphics();
		g.setColor(so_color);

		if (so instanceof Beacon) {
			g.drawOval(convPos(so.pos.x) - grid_size / 6, convPos(so.pos.y)
					- grid_size / 6, grid_size / 3, grid_size / 3);
			JLabel sol = new JLabel(Integer.toString(so.id));
			sol.setForeground(so_text_color);
			sol.setBounds(convPos(so.pos.x) + grid_size / 6, convPos(so.pos.y),
					grid_size / 3, grid_size / 2);
			radarArea.add(sol, new Integer(1));
		}

		if (so instanceof Airfield) {
			int xa[] = new int[3], ya[] = new int[3];
			int l1 = grid_size / 2, l2 = grid_size / 6;
			xa[0] = convPos(so.pos.x);
			ya[0] = convPos(so.pos.y);
			xa[1] = convPos(so.pos.x) - l1 * so.dir.x - l2 * so.dir.y;
			ya[1] = convPos(so.pos.y) - l1 * so.dir.y + l2 * so.dir.x;
			xa[2] = convPos(so.pos.x) - l1 * so.dir.x + l2 * so.dir.y;
			ya[2] = convPos(so.pos.y) - l1 * so.dir.y - l2 * so.dir.x;
			g.drawPolygon(xa, ya, 3);
			JLabel sol = new JLabel(Integer.toString(so.id));
			sol.setForeground(so_text_color);
			sol.setBounds(convPos(so.pos.x) + grid_size / 6, convPos(so.pos.y),
					grid_size / 3, grid_size / 2);
			radarArea.add(sol, new Integer(1));
		}

		if (so instanceof Exit) {
			g.drawRect(convPos(so.pos.x) - grid_size / 6, convPos(so.pos.y)
					- grid_size / 6, grid_size / 3, grid_size / 3);
			JLabel sol = new JLabel(Integer.toString(so.id));
			sol.setForeground(so_text_color);
			sol.setBounds(convPos(so.pos.x) + grid_size / 6, convPos(so.pos.y),
					grid_size / 3, grid_size / 2);
			radarArea.add(sol, new Integer(1));
		}

		if (so instanceof Line) {
			g.setColor(line_color);
			g.drawLine(convPos(((Line) so).pos.x), convPos(((Line) so).pos.y),
					convPos(((Line) so).second_end.x),
					convPos(((Line) so).second_end.y));
		}

		radarArea.backIcon = new ImageIcon(radarArea.backImage);
		radarArea.back.setIcon(radarArea.backIcon);
	}

	/**
	 * Updates the command string UI element.
	 * 
	 * @param cmd_str the string to display.
	 * @pre cmd_str is the individual command (not the full parseable command
	 * sent to the plane)
	 * @pre the UI displays the portion of the full command (cmd_str)
	 */
	public void CommandUpdate(String cmd_str) {
		inputArea.setText(cmd_str);
	}

	/**
	 * Places a new plane UI element on the game field.
	 * 
	 * @param p the plane
	 * @pre a new plane
	 * @post new plane p is displayed on the radar game field with info
	 * based on its fields.
	 */
	public void PlaneNew(Plane p) {
		if (ATC.debug_flag)
			System.out.println("p.n.1");
		UIPlane uiplane = new UIPlane();
		char id = p.getIdChar();
		uiplane.radar_label = null;
		uiplane.info_label = new Label((new Character(id)).toString());
		if (ATC.debug_flag)
			System.out.println("p.n.2");
		infoArea.add(uiplane.info_label);
		if (ATC.debug_flag)
			System.out.println("p.n.2.1");
		infoArea.validate();
		if (ATC.debug_flag)
			System.out.println("p.n.3");
		synchronized (this) {
			planes.put((Object) (new Character(id)), (Object) uiplane);
		}

		if (ATC.debug_flag)
			System.out.println("p.n.4");
		PlaneUpdate(p);
		if (ATC.debug_flag)
			System.out.println("p.n.5");
	}

	/**
	 * Updates an existing plane UI element on the game field.
	 * 
	 * @param p the plane
	 * @pre an existing plane
	 * @post the existing plane p is updated on the UI based on its new info.
	 */
	public void PlaneUpdate(Plane p) {
		if (ATC.debug_flag)
			System.out.println("p.u.1");
		char id = p.getIdChar();
		UIPlane uiplane = null;
		synchronized (this) {
			uiplane = (UIPlane) (planes.get((Object) (new Character(id))));
		}
		if (ATC.debug_flag)
			System.out.println("p.u.1 il=" + uiplane.info_label);
		if (uiplane == null)
			return;
		if (ATC.debug_flag)
			System.out.println("p.u.2");

		if (p.takeoff_flag) {
			if (uiplane.radar_label == null) {
				if (ATC.debug_flag)
					System.out.println("p.u.3");
				uiplane.radar_label = new JLabel(getPlaneText(p),
						dirToPlaneIcon(p.dir), JLabel.CENTER);
				uiplane.radar_label.setVerticalTextPosition(JLabel.TOP);
				uiplane.radar_label.setHorizontalTextPosition(JLabel.CENTER);
				uiplane.radar_label.setIconTextGap(text_gap);
				uiplane.radar_label.setForeground(text_color);
				radarArea.add(uiplane.radar_label, new Integer(2));
				if (ATC.debug_flag)
					System.out.println("p.u.4");
			} else {
				if (ATC.debug_flag)
					System.out.println("p.u.5");
				uiplane.radar_label.setText(getPlaneText(p));
				uiplane.radar_label.setIcon(dirToPlaneIcon(p.dir));
				if (ATC.debug_flag)
					System.out.println("p.u.6");
			}

			uiplane.radar_label.setBounds(convPos(p.pos.x) - plane_width / 2,
					convPos(p.pos.y) - plane_height + icon_size / 2,
					plane_width, plane_height);
			if (ATC.debug_flag)
				System.out.println("p.u.7");
		}

		uiplane.info_label.setText(getPlaneInfoText(p));
		if (ATC.debug_flag)
			System.out.println("p.u.8");
		infoArea.validate();

		if (ATC.debug_flag)
			System.out.println("p.u.9");
	}

	/**
	 * Gets the plane text.
	 *
	 * @param p the plane
	 * @return the plane text
	 * @pre the plane to display
	 */
	protected String getPlaneText(Plane p) {
		return (new Character(p.getIdChar())).toString()
				+ (new Integer(p.alt)).toString();
	}

	/**
	 * Gets the plane info text.
	 *
	 * @param p the plane
	 * @return the plane info text
	 * @pre the plane to get info text from
	 * @post info about the plane is used to return a string that gives
	 * information about the plane and its current command.
	 */
	protected String getPlaneInfoText(Plane p) {
		String rs = new String(" ");
		rs += (new Character(p.getIdChar())).toString() + p.alt + " ";
		rs += p.destination.getName() + " ";
		if (p.dir_cmd != null) {
			if (p.dir_cmd instanceof CircleCommand) {
				rs += "C";
			} else {
				rs += ((TurnCommand) p.dir_cmd).dir.getDirName();
			}
			if (p.dir_cmd.pos_obj != null) {
				rs += "@";
				rs += p.dir_cmd.pos_obj.getName();
			}
		}
		return rs;
	}

	/**
	 * Removes a plane UI element from the game field.
	 * 
	 * @param p the plane
	 * @pre the plane to be removed that is displayed in the UI
	 * @post the plane is removed from the UI
	 */
	public void PlaneRemove(Plane p) {
		char id = p.getIdChar();
		UIPlane uiplane = null;
		synchronized (this) {
			uiplane = (UIPlane) (planes.get((Object) (new Character(id))));
		}
		if (uiplane == null)
			return;

		if (uiplane.radar_label != null)
			radarArea.remove(uiplane.radar_label);
		if (uiplane.info_label != null)
			infoArea.remove(uiplane.info_label);
		synchronized (this) {
			planes.remove((Object) (new Character(id)));
		}

		radarArea.repaint();
		infoArea.repaint();
	}

	/**
	 * Updates the game play info.
	 * 
	 * @param tick_count the tick count
	 * @param safe_count the safe plane count
	 * @pre the ticker and safe count from the newest tick update are passed in.
	 * @post the ticker and safe plane count are updated in the UI
	 */
	public void InfoUpdate(int tick_count, int safe_count) {
		infoTopLine.setText(" Time: " + tick_count + ", Safe: " + safe_count);
	}

	/**
	 * Processes the new game or exit buttons action commands.
	 * 
	 * @param e the ActionEvent 
	 * @pre a button was pressed in the UI
	 * @post the methods/actions associated with the pressed button are executed.
	 */
	public void actionPerformed(ActionEvent e) {
		atc_obj.getInputhandler().processActionCommand(e.getActionCommand());
	}

	/**
	 * Enables the "New" game UI button.
	 */
	public void ready() {
		newButton.setEnabled(true);
	}

	/**
	 * Disables the "New" game UI button and ensures there is an active
	 * key listener.
	 */
	public void start() {
		newButton.setEnabled(false);
		synchronized (this) {
			if (!keyListenerAdded) {
				addKeyListener(this);
				keyListenerAdded = true;
			}
		}
	}

	/**
	 * Displays a game over message and reenables the "New" game UI button. 
	 *
	 * @param gameOverMessage the string to display
	 * @pre string displaying the reason the game was over
	 * @post the messase was displayed and the "New" UI button is reenabled
	 * indicating that a new game can be played now that the previous game 
	 * has ended.
	 */
	public void gameOver(String gameOverMessage) {
		JOptionPane.showMessageDialog(null, gameOverMessage);

		newButton.setEnabled(true);
		synchronized (this) {
			if (keyListenerAdded) {
				removeKeyListener(this);
				keyListenerAdded = false;
			}
		}
	}

	/**
	 * Refreshes the radar canvas game field.
	 */
	public void refresh() {
	}

	/**
	 * Closes the application window.
	 */
	public synchronized void close() {
		setVisible(false);
		dispose();
	}

	/**
	 * Processes a KeyEvent
	 * 
	 * @param e the KeyEvent
	 * @pre keyboard button was pressed
	 * @post the command is processed if enter was pressed. otherwise the 
	 * the character associated with the KeyEvent is processed.
	 */
	public void keyTyped(KeyEvent e) {
		char key = e.getKeyChar();
		if (key == '\n')
			atc_obj.getInputhandler().processCommand();
		else
			atc_obj.getInputhandler().processKey(key);
	}

	/**
	 * 
	 */
	public void keyPressed(KeyEvent e) {
	}

	/**
	 * 
	 */
	public void keyReleased(KeyEvent e) {
	}

	/**
	 * 
	 */
	public void windowClosing(WindowEvent event) {
		atc_obj.stopATC();
	}

	/**
	 * 
	 */
	public void windowClosed(WindowEvent e) {
	}

	/**
	 * 
	 */
	public void windowOpened(WindowEvent e) {
	}

	/**
	 * 
	 */
	public void windowIconified(WindowEvent e) {
	}

	/**
	 * 
	 */
	public void windowDeiconified(WindowEvent e) {
	}

	/**
	 * 
	 */
	public void windowActivated(WindowEvent e) {
	}

	/**
	 * 
	 */
	public void windowDeactivated(WindowEvent e) {
	}
};
