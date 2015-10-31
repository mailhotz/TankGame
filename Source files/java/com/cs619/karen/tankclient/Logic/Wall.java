package com.cs619.karen.tankclient.Logic;

import com.cs619.karen.tankclient.Logic.Destroyable;

/**
 * Created by Eric on 10/24/2015.
 * Wall.java
 * This class will be used to extend the basic Destroyable.java class
 * and will be used to represent the tank in the tank game
 * 
 */
 
 public class Wall extends Destroyable {
	//--------------------- instance vars -----------------------//
	private boolean destroyable = false;
	//-----------------------------------------------------------//
	//----------------------- Constructors ----------------------//
    //Default
	public Wall( ){
		super();
	}
	//Takes an int and stores it in value
	public Wall( int i ){
		super(i);
	}
	//-----------------------------------------------------------//
	
	//-------------------------- isDestroyable ------------------//
	public boolean isDestroyable(){
		return destroyable;
	}
 }