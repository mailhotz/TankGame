package com.cs619.karen.tankclient.Logic;

/**
 * Created by Eric on 10/24/2015.
 * Destroyable.java
 * This class will be used to extend the basic Tile.java class
 * 
 * 
 */
 
 public class Destroyable extends Tile{
	//--------------------- instance vars -----------------------//
	private int hp = 100;
	private boolean isDes = false;
	//-----------------------------------------------------------//
	//----------------------- Constructors ----------------------//
    //Default
	public Destroyable( ){
		super();
	}
	//Takes an integer
	public Destroyable( int i ){
		super(i);
	}
	//-----------------------------------------------------------//
	
	//-------------------------- getHP --------------------------//
	// Returns the value of hp
	public int getHP(){
		return hp;
	}
	
	//------------------------ isDestroyed ----------------------//
	// Returns 1 if the object is destroyed and 0 otherwise 
	public boolean isDestroyed(){
		return isDes;
	}
	
	//------------------------ takeDamage -----------------------//
	// decrements the hp of the object by a set amount
	// Returns int representing remaining hp
	public int takeDamage(){
		hp = hp - 10;
		return hp;
	}
 }