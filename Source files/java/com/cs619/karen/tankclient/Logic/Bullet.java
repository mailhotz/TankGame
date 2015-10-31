package com.cs619.karen.tankclient.Logic;

/**
 * Created by Eric on 10/24/2015.
 * Wall.java
 * This class will be used to extend the basic Destroyable.java class
 * and will be used to represent the tank in the tank game
 * 
 */
 
 public class Bullet extends Tile{
	//--------------------- instance vars -----------------------//

	//-----------------------------------------------------------//
	//----------------------- Constructors ----------------------//
    //Default
	public Bullet( ){
		super();
	}
	//Takes an int and stores it in value
	public Bullet( int i ){
		super(i);
	}
	//-----------------------------------------------------------//
	
	//-------------------------- move ---------------------------//
	public boolean move( ){
		
		return true;
	}
 }