/**
 * Name: Joshua O'Steen
 * Date: 03/31/2014
 * Class: CS3330
 * Lab: 7
 * Section: D
 * Submission Code: Opening Day
 */
package jrocnc.cs3330.lab7;

/**
 * @author Josh
 *
 */
public class Validator implements isValidData{
	
	// methods
	public void isName(String name) throws InvalidNameException{
		
		// throw if null was sent
		if(name == null || name.isEmpty())
			throw new InvalidNameException("You did not enter a name.");
		
		// trim spaces out of string to verify all characters are letters
		// and to ensure that only spaces were not sent to this method
		name = name.replaceAll("\\s+","");
		
		
		// if name has no length after trim in the case only spaces were sent
		if(name.length() == 0)
			throw new InvalidNameException("You did not enter a name.");
		
		
		/*
		// iterate through remaining character to ensure all are letter
		for(char current : name.toCharArray()){
			if(!Character.isLetter(current))
				throw new InvalidNameException("You have entered an invalid name.");
		}
		*/
		
		// if anything except letters are found, throw error
		if(!name.matches("[A-Za-z'-]+"))
			throw new InvalidNameException("You have entered an invalid name.");
		
		
		
	}
	
	public void isValidAge(String age) throws InvalidAgeException{
		
		// throw if null or string containing spaces or only spaces was sent
		if(age == null || age.contains(" "))
			throw new InvalidAgeException("You did not enter an age.");
		
		/*
		// iterate through string to ensure all are digits
		for(char current : age.toCharArray()){
			if(!Character.isDigit(current))
				throw new InvalidAgeException("You did not enter an integer.");
		}
		*/
		
		// if anything other than numbers found, throw error
		if(!age.matches("[0-9-]+"))
			throw new InvalidAgeException("You did not enter an integer.");
		
		// convert string to integer and error check value
		int stringToAge = Integer.parseInt(age);
		
		if(stringToAge <= 0)
			throw new InvalidAgeException("Age can not be 0 or negative.");
		if(stringToAge >= 150)
			throw new InvalidAgeException("Age can not be equal to or more than 150.");
		
	}
	
	public void isBalance(String balance) throws InvalidBalanceException{
		
		// throw if null or string containing spaces or only spaces was sent
		if(balance == null || balance.contains(" "))
			throw new InvalidBalanceException("You did not enter a double.");
				
		/*
		// iterate through string to ensure all are digits
		for(char current : balance.toCharArray()){
			if(!Character.isDigit(current))
				throw new InvalidBalanceException("You did not enter an double.");
		}
		*/
		
		// if anything other than numbers found, throw error
		if(!balance.matches("[0-9.]+"))
			throw new InvalidBalanceException("You did not enter an double.");
		
		// quick fix for block below where the string consists of a single period
		if(balance.length() == 1 && balance.equals("."))
			throw new InvalidBalanceException("You did not enter an double.");

		// make sure only 1 decimal point is present to avoid possible 
		// NumberFormatException 
		int decimals = 0;
		for(char current : balance.toCharArray()){
			if(current == 0x2E)
				decimals++;
			if(decimals > 1)
				throw new InvalidBalanceException("You did not enter an double.");
		}
		
		
		
		
	}
	
	
}
