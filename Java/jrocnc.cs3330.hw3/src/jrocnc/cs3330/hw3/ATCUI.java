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

public interface ATCUI {
	public void initUI(int dx, int dy);

	public void StaticObjNew(StaticObj so);

	public void CommandUpdate(String cmd_str);

	public void PlaneNew(Plane p);

	public void PlaneUpdate(Plane p);

	public void PlaneRemove(Plane p);

	public void InfoUpdate(int tick_count, int safe_count);

	public void ready();

	public void start();

	public void gameOver(String s);

	public void refresh();

	public void close();
};
