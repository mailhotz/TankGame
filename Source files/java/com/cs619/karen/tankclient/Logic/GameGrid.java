package com.cs619.karen.tankclient.Logic;

import com.cs619.karen.tankclient.rest.BulletZoneRestClient;
import com.cs619.karen.tankclient.ui.GridAdapter;
import com.cs619.karen.tankclient.util.BooleanWrapper;
import com.cs619.karen.tankclient.util.GridWrapper;
import com.cs619.karen.tankclient.util.LongWrapper;


/**
 * Created by Zachary on 10/30/2015.
 */
public class GameGrid {
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
                String valS = "" + val;
                valS.valueOf(val);
                System.out.println("Val as a string is: " + valS);
                if(val >= 1000 && val <= 2000)
                    System.out.print(" W ");
                else if(val >= 2000000 && val <= 3000000)
                    System.out.print(" B ");
                else if(val >= 10000000 && val <= 20000000)
                    System.out.print(" T ");
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

}
