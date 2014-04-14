/*
 * 	Joshua O'Steen
 * 	CS 3330 - Guilliams, England
 * 	Homework 3
 * 	04/10/2014
 * 	UML, Machine State, Javadoc
 */


package jrocnc.cs3330.hw3;

import java.lang.String;

public interface ATCConfig {
	public boolean config(Beacon beacons[], int max_beacon, Exit exits[],
			int max_exit, Airfield airfields[], int max_airfield, Line lines[],
			int max_line);

	public int get_dx();

	public int get_dy();

	public int get_new_plane_chance();

	public int get_init_tick_ms();

	public int get_tick_dec();

	public String get_name();
};
