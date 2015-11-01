package com.cs619.karen.tankclient.Logic;

import com.cs619.karen.tankclient.Logic.Destroyable;

/**
 * Created by Eric on 10/24/2015.
 * Tank.java
 * This class will be used to extend the basic Destroyable.java class
 * and will be used to represent the tank in the tank game
 * 
 */
 
 public class Tank extends Destroyable {
	//--------------------- instance vars -----------------------//
	
	//-----------------------------------------------------------//
	//----------------------- Constructors ----------------------//
    //Default
	public Tank( ){
		super();
	}
	//Takes an int and stores it in value
	public Tank( int i ){
		super(i);
	}
	//-----------------------------------------------------------//
	
	//------------------------- move ----------------------------//
	public boolean move( int steps ){
		
		return true;
	}
	
	//------------------------ rotate ---------------------------//
	public boolean rotate( int direction) {
	
		return true;
	}
	
	//------------------------ shoot ----------------------------//
	public boolean shoot( ){
		
		return true;
	}
 }
 
 