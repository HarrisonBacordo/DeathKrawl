package Collision;

import Entity.*;
import LevelGenerator.Level;
import LevelGenerator.Rooms.Room;

public class Collision {


    private Level level;

    //room that we want to look at for collisions
    private Room currentRoom;

    private Entity grid[][];


    /**
     * Constructor that sets the currentRoom and grid given a level
     */
    Collision(Level level){
        this.level = level;


        this.currentRoom = this.level.getCurrentRoom();
        this.grid = currentRoom.getGrid();
    }


    /**
     * Checks to see if there is more than one entity within a grid square, and thus is collision calculations are necessary
     */
    private void gridCheck(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){

                //if the grid contains more than one entity, then check collions
                //if(grid[i][j].hasMoreThanOne){
                    //test collisions within that grid square
                 //   checkCollisions(i,j);
                //}

            }
        }

    }


    /**
     * Look for collisions within the grid
     */
    private void checkCollisions(int row, int col){
        Entity toCheck = grid[row][col];

        //for all the entities in the grid tile

        //if their rectangles overlap
        //pass the logic onto the entity itself


    }


}
