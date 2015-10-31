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
	long id;
	int direction;
	//-----------------------------------------------------------//
	//----------------------- Constructors ----------------------//
    //Default
	public Tank( ){
		super();
	}
	//Takes an int and stores it in value
	public Tank( int i ){
		super(i);
		String temp = "" + i;
		id = Integer.parseInt(temp.substring(1,4));
		direction = Integer.parseInt(temp.substring(7,8));
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

	//------------------------ getId ----------------------------//
	public long getId() {
		return id;
	}

	//------------------------ setId ----------------------------//
	public void setId(long id) {
		this.id = id;
	}

	//------------------------- getDirection --------------------//
	public int getDirection() {
		return direction;
	}

	//------------------------- setDirection --------------------//
	public void setDirection(int direction) {
		this.direction = direction;
	}
}
 
 