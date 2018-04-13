/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bindingofisaac;

/**
 *
 * @author Austin
 */
public class DirectionNotFoundException  extends Exception{
	
	public DirectionNotFoundException(String message){
		System.err.println(message);
	}
	
	public DirectionNotFoundException() throws Exception{
		throw new DirectionNotFoundException(this.getMessage());
	}
}
