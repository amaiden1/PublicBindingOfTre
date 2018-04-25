/*
 * Copyright © 2018 Austin Maiden and Nolan Ierardi.
 * All rights reserved.
 *
 * This code is licensed for private use. Any unauthorized distribution is prohibited.
 */

package bindingoftre;

/**
 * Exception that is thrown when a direction is specified that is not 0-3.
 * @author Austin Maiden and Nolan Ierardi
 */
public class DirectionNotFoundException  extends Exception{

	/**
	 * Constructor for DirectionNotFoundException.
	 * @param message the string to be printed
	 */
	public DirectionNotFoundException(String message){
		System.err.println(message);
	}

	/**
	 * Alternate constructor that does not take a message.
	 * @throws Exception
	 */
	public DirectionNotFoundException() throws Exception{
		throw new DirectionNotFoundException(this.getMessage());
	}
}
