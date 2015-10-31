package com.cs619.karen.tankclient.Logic;

/**
 * Created by Eric on 10/24/2015.
 * Tile.java
 * This class will be the base class of the tile structure that will
 * be used to build out.
 * 
 */
public class Tile {
	//--------------------- instance vars -----------------------//
	private long value; // holds the in value from the Rest
	private int col;
	private int row;
	
	//-----------------------------------------------------------//
	//----------------------- Constructors ----------------------//
    //Default
	public Tile(){
		value = 0;
	}
	//Takes a long and stores it in value
	public Tile( long i ){
		value = i;
	}
	//-----------------------------------------------------------//
	
	//--------------------------- update ------------------------//
	// updates the graphic representation on the tile
	public void update(){
		
	}
	
	//--------------------------- setVal ------------------------//
	// sets the value to the int passed in 
	public void setVal( int i ){
		value = i;
	}

	//Set column to i
	public void setCol(int i){
		col = i;
	}

	//set Row to i
	public void setRow(int i){
		row = i;
	}
	//-------------------------- getVal -------------------------//
	// void function that returns value
	// Return: int
	public long getVal(){
		return value;
	}
}
