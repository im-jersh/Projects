/*
 * 	Joshua O'Steen
 * 	CS 3330 - Guilliams, England
 * 	Homework 3
 * 	04/10/2014
 * 	UML, Machine State, Javadoc
 */

package jrocnc.cs3330.hw3;

import javax.swing.*;
import java.awt.*;

// TODO: Auto-generated Javadoc
/**
 * A few utilities that simplify using windows in Swing. 1998-99 Marty Hall,
 * http://www.apl.jhu.edu/~hall/java/
 */

public class WindowUtilities {

	// methods
	//
	//
	//
	/**
	 * Tell system to use native look and feel, as in previous releases. Metal
	 * (Java) LAF is the default otherwise.
	 */
	public static void setNativeLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}
	}

	/**
	 * Sets the java look and feel.
	 */
	public static void setJavaLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting Java LAF: " + e);
		}
	}

	/**
	 * Sets the motif look and feel.
	 */
	public static void setMotifLookAndFeel() {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		} catch (Exception e) {
			System.out.println("Error setting Motif LAF: " + e);
		}
	}

	/**
	 * A simplified way to see a JPanel or other Container. Pops up a JFrame
	 * with specified Container as the content pane.
	 *
	 * @param content the content
	 * @param width the width
	 * @param height the height
	 * @param title the title
	 * @param bgColor the bg color
	 * @return the j frame
	 */
	public static JFrame openInJFrame(Container content, int width, int height,
			String title, Color bgColor) {
		JFrame frame = new JFrame(title);
		frame.setBackground(bgColor);
		content.setBackground(bgColor);
		frame.setSize(width, height);
		frame.setContentPane(content);
		// frame.addWindowListener(new ExitListener());
		frame.setVisible(true);
		return (frame);
	}

	/**
	 *  Uses Color.white as the background color.
	 *
	 * @param content the content
	 * @param width the width
	 * @param height the height
	 * @param title the title
	 * @return the j frame
	 */
	public static JFrame openInJFrame(Container content, int width, int height,
			String title) {
		return (openInJFrame(content, width, height, title, Color.white));
	}

	/**
	 * Uses Color.white as the background color, and the name of the Container's
	 * class as the JFrame title.
	 *
	 * @param content the content
	 * @param width the width
	 * @param height the height
	 * @return the j frame
	 */
	public static JFrame openInJFrame(Container content, int width, int height) {
		return (openInJFrame(content, width, height, content.getClass()
				.getName(), Color.white));
	}
}
