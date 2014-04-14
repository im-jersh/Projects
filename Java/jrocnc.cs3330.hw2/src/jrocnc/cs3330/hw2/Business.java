/**
 * Name: Joshua O'Steen
 * Class: CS 3330
 * Section: D
 * TA: Matthew England
 * Assignment: Homework 2
 * Due Date: 03/21/14
 * Objectives: 
 * 		Updating existing code base
 * 		Inheritance
 * 		Composition
 * 		Encapsulation
 * 		Polymorphism
 * 
 */
package jrocnc.cs3330.hw2;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Josh
 *
 */
public class Business {
	
	// attributes
	private String name;
	private String contacts_name;
	private ArrayList<Job> jobOffers;
	protected Grid grid;
	protected final static int JOB_OFFER_SIZE = 10;
	
	
	// constructor
	public Business(String name, String contact, Grid grid){
		// set name, contact, and grid attributes
		this.setBusinessName(name);
		this.setBusinessContact(contact);
		this.setGrid(grid);
		
		// randomly generate job offers from grid attribute
		// using Business.fillJobOffers()
		this.fillJobOffers();
	}
	
	// methods
	private void setBusinessName(String name){
		// set private name attribute
		this.name = name;
	}
	
	private void setGrid(Grid grid){
		// set protected grid attribute
		this.grid = grid;
	}
	
	private void setBusinessContact(String contact){
		// set private contact attribute
		this.contacts_name = contact;
	}
	
	protected String getName(){
		// return private name attribute
		return this.name;
	}
	
	protected String getContact(){
		// return private contact attribute
		return this.contacts_name;
	}
	
	protected void fillJobOffers(){
		// create random generator
		// instantiate jobOffers arrayList to size JOB_OFFER_SIZE
		Random randomJob = new Random();
		this.jobOffers = new ArrayList<Job>(JOB_OFFER_SIZE);
		
		// loop to add jobs to arrayList, randomly select server from grid, 
		// obtain name using Grid.getServerName(), 
		// randomly set worth using Business.determineWorth,
		// then add to arraylist
		for(int i = 0; i < JOB_OFFER_SIZE; i++){
			this.jobOffers.add(new Job(this.grid.getServerName(randomJob.nextInt(this.grid.numberOfServers())), determineWorth()));
		}
		
	}
	
	public void viewJobOffers(){
		// iterate through jobOffers arrayList
		// and print out information
		for(int i = 0; i < this.jobOffers.size(); i++){
			System.out.printf("%d) Job: %s worth %d\n", i + 1, this.jobOffers.get(i).getTargetAddress(), this.jobOffers.get(i).getReward());
		}
		
	}
	
	public Job getJobOffer(int job_offer){
		// copy job at index "job_offer" and remove from arrayList
		Job jobToReturn = new Job(this.jobOffers.get(job_offer).getTargetAddress(), this.jobOffers.get(job_offer).getReward());
		this.jobOffers.remove(job_offer);
		
		// return copied job
		return jobToReturn;
		
	}
	
	public int getJobOfferCount(){
		// return size of jobOffers arrayList
		return this.jobOffers.size();
	}
	
	protected int determineWorth(){
		// instantiate random number generator
		// return random number between 10000-25000
		Random randomWorth = new Random();
		return randomWorth.nextInt(15001) + 10000;
	}

}
