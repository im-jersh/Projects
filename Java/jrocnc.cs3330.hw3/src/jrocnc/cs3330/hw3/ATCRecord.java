/*
 * 	Joshua O'Steen
 * 	CS 3330 - Guilliams, England
 * 	Homework 3
 * 	04/10/2014
 * 	UML, Machine State, Javadoc
 */


package jrocnc.cs3330.hw3;

public interface ATCRecord {
	public void save(String user, String config_name, int time, long real_time,
			int safe_count);
};
