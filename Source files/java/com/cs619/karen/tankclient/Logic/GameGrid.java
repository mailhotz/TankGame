package com.cs619.karen.tankclient.Logic;

import android.util.Log;

import com.cs619.karen.tankclient.rest.BulletZoneRestClient;
import com.cs619.karen.tankclient.ui.GridAdapter;
import com.cs619.karen.tankclient.util.BooleanWrapper;
import com.cs619.karen.tankclient.util.GridWrapper;
import com.cs619.karen.tankclient.util.LongWrapper;


/**
 * Created by Zachary on 10/30/2015.
 */
public class GameGrid {
    private static final String TAG = "GameGrid";

    //Grid that holds values from server
    private int grid [][] = new int[16][16];

    //Grid that uses Tile structure
    private Tile tGrid [][] = new Tile[16][16];

    //private BulletZoneRestClient restClient;
    private GridWrapper gWrap;

    //ID used to recognize my tank
    private long myID;

    //Default constructor
    public GameGrid(BulletZoneRestClient restClient){
        gWrap = restClient.grid();
        grid = gWrap.getGrid();

    }

    //Intialize Tile grid
    public void makeTGrid(){
        for(int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                //Is a wall
                if(grid[i][j] >= 1000 && grid[i][j] <=2000)
                    tGrid[i][j] = new Wall(grid[i][j]);
                //Is a tank
                else if(grid[i][j] >= 10000000 && grid[i][j] <= 20000000)
                    tGrid[i][j] = new Tank(grid[i][j]);
                //Is a bullet
                else if(grid[i][j] >= 2000000 && grid[i][j] <= 3000000)
                    tGrid[i][j] = new Bullet(grid[i][j]);
                //Empty tile
                else
                    tGrid[i][j] = new Tile(grid[i][j]);
                //Tiles position in array
                tGrid[i][j].setCol(i);
                tGrid[i][j].setRow(j);
            }
        }
    }

    //Print grid
    public void print(){
        for(int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                long val = grid[i][j];
                if(val >= 1000 && val <= 2000)
                    System.out.print(" W ");
                else if(val >= 2000000 && val <= 3000000)
                    System.out.print(" B ");
                else if(val >= 10000000 && val <= 20000000) {
                    String v = val + "";
                    if(myID == Long.parseLong(v.substring(1,4)))
                        System.out.print(" T ");
                    else
                        System.out.print(" t ");
                }
                else
                    System.out.print(" - ");
            }
            System.out.print("\n");
        }
    }

    //Return 16x16 array
    public int [][] getGrid(){
        return grid;
    }

    //Get tank ID from
    public long getID(long id){
        myID = id;
        return myID;
    }

    //findTank looks for the tank of the given ID and returns a Tank if it has the same ID
    //else it returns null
    private Tank findTank(long id){
        for(int i=0; i<16; i++)
            for(int j=0; j<16; j++)
                if( tGrid[i][j] instanceof Tank ) {
                    Tank t = (Tank)tGrid[i][j];
                    if ( id == t.getId() )
                        return (Tank) tGrid[i][j];
                }
        return null;
    }

    //canMove takes a long and an int. The long the tankId that the player wants
    //to move and the int is the direction that the player wants to move the tank
    //Return: 1 - if the tank can make that move
    //        0 - if the tank can make that rotation
    //       -1 - if the tank can't do anything
    public int canMove( long tId, int direction ){
        Tank t = findTank(tId);
        System.out.println( "You are facing " + t.getDirection() + " you want to go " + direction);
        if( eqOrOp( t.getDirection(), direction ) ) {
            System.out.println( "Moving" );
            switch (direction) {
                case 0: // move up case
                    if (tGrid[t.getCol() - 1][t.getRow()].getVal() != 0)
                        return -1;
                    break;
                case 2: // move right case
                    if (tGrid[t.getCol()][t.getRow()+1].getVal() != 0)
                        return -1;
                    break;
                case 4: // move down case
                    if (tGrid[t.getCol()+1][t.getRow()].getVal() != 0)
                        return -1;
                    break;
                case 6: // more left case
                    if (tGrid[t.getCol()][t.getRow()-1].getVal() != 0)
                        return -1;
                    break;
            }
            return 1;
        }
        else{
            System.out.println("Turning");
            switch(direction){
                case 0: // rotate to face up
                    if( t.getDirection() == 4 )
                        return -1;
                    break;
                case 2: // rotate to face right
                    if( t.getDirection() == 6 )
                        return -1;
                    break;
                case 4: // rotate to face down
                    if( t.getDirection() == 0 )
                        return -1;
                    break;
                case 6: // rotate to face left
                    if( t.getDirection() == 2 )
                        return -1;
                    break;
            }
            return 0;
        }
    }

    //canRotate takes a long and an int
    //returns a boolean:
    //                  true if the move was made
    //                  false if the move was not made
    public boolean canRotate( long tId, int direction ){
        return true;
    }

    //eqOrOp( directions are equal or opposite )
    //checks to see if the two directions that are fed into it are equal
    //or opposite
    //Returns a boolean
    private boolean eqOrOp( int f, int s){
        if( f == 0 && s == 4 || f == 4 && s == 0 ){
            return true;
        }
        else if( f == 2 && s == 6 || f == 6 && s == 2 ){
            return true;
        }
        else if( f == s )
            return true;
        else
            return false;
    }
}
