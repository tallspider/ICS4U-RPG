/*
Note to self:

   -class headers indicating:
o class name
o author
o date

o school
o purpose
   -method headers including:
o description of each parameters and return values
o purpose
   -sufficient and appropriate comments describing
o code that is difficult to understand
o constant, type and variable declarations
*/

/*
Class Name: Map
Author: David Qian 
Date: January 7, 2018
School: A.Y.Jackson S.S
Purpose: Used to keep track of entities (ships and walls)
*/

public class Map{

//********************************************************************TEMPORARY WORKAROUND **********************************************************************//
// the minimun ammount of empty spots a map is allowed to have (maxinum number of enemies + maxinum number of fleet ships)
   public static int MINIMUM_EMPTY_SPOTS = 15;
//********************************************************************TEMPORARY WORKAROUND **********************************************************************//

// the board is a 2d spot array
   Spot[][] board;

// generates a map based on player score
   public Map(int score){}
   
// constructor 
   public Map(Spot[][] board){
      this.board = board;
   }

// checks if the given map is valid, a map is considered valid if every non-wall spot is connected to each other either directly or indirectly using other non-wall spots, also a valid map must have atleast (max number of enemies) + (max number of fleet ships) spots
   private boolean isValid(){
      
      int numEmptySpots = 0;
      
      //makes a copy of the map to wall flood, then checks if there are any empty spaces left, if so, it would mean there are 2 or more spots that are not directly or indirectly connected to each other
      Spot[][] boardCopy;
      boardCopy = new Spot[board.length][]; 
      for (int i = 0 ; i < board.length ; i++){
         boardCopy[i] = new Spot[board[i].length];
      }
        
      // since at this point the only possiblities for any spot is either a wall or empty it is safe to assume that anythign that is not a wall is empty and vice versa
      // also both copying of the board and checking for empty spots require looping throught the entire board, so the two are combined, thus the board only needs to be looped through once
      
      
      // loop through the entire map and counts the number of empty spots
      for (int x = 0 ; x < board.length ; x ++){
         for (int y = 0 ; y < board[x].length ; y++){
            if (board[x][y].isEmpty()){
               numEmptySpots ++;
            }
            else{
               boardCopy[x][y].setWall();
            }
         }
      }
      if (numEmptySpots < MINIMUM_EMPTY_SPOTS){
         return false;
      }
      
      Map mapCopy = new Map(boardCopy);  
       
       // finds the first empty spot and floods only that spot, alreadyFlooded is used to prevent wallflood to be called mutiple times
      boolean alreadyFlooded = false;
      for (int x = 0 ; x < boardCopy.length && alreadyFlooded == false ; x ++){
         for (int y = 0 ; y < boardCopy[x].length && alreadyFlooded == false ; y++){
            if (boardCopy[x][y].isEmpty()){
               wallFlood(new Location(x,y));
               alreadyFlooded = true;
            }
         }
      }
      
      // loops throught the entire copy of board to make sure every spot is filled in, this means that all empty spots are connected
      for (int x = 0 ; x < boardCopy.length && alreadyFlooded == false ; x ++){
         for (int y = 0 ; y < boardCopy[x].length && alreadyFlooded == false ; y++){
            if (boardCopy[x][y].isEmpty()){
               return false;
            }   
         }
      }
      
      
      return true;
   }

// recurisve statment that turns a non-wall spot into a wall, and calls this method on all adjacent non-wall spots, this is used in isValid to check if the map is valid
   private void wallFlood(Location location){
   
      if(this.isInMap(location) && this.isEmpty(location)){
         this.setWall(location);
      
      // cals wallFlood on all adjcent sides (no need to check if it's in the map because that will be done immediately after wallFlood is called)
         wallFlood(new Location(location.getX()+1, location.getY()));
         wallFlood(new Location(location.getX()-1, location.getY()));
         wallFlood(new Location(location.getX(), location.getY()+1));
         wallFlood(new Location(location.getX(), location.getY()-1));
      }
      return;
   }
   
      // checks if the given location exists inside the map
   public boolean isInMap(Location location){
      if (location.getX() >= 0 && location.getX() < board.length ){
         if (location.getY() >= 0 && location.getY() < board[location.getX()].length){
            return true;
         }
      }
      return false;
   }
   
// accessors and mutators

// setEntity also known as spawn, but setWall and setShip is differentiated and easier to understand 
   public void setWall(Location location){
      this.board[location.getX()][location.getY()].setWall();
   }
   
   public void setEntity(Ship ship,Location location){
      this.board[location.getX()][location.getY()].setShip(ship);
   }

// setEmpty also known as despawn, but setEmpty is easier to understand 
   public void setEmpty(Location location){
      this.board[location.getX()][location.getY()].setEmpty();
   }

   public boolean isEmpty(Location location){
      return this.board[location.getX()][location.getY()].isEmpty();
   }
   
   public boolean isWall(Location location){
      return this.board[location.getX()][location.getY()].isWall();
   }

   public boolean isShip(Location location){
      return this.board[location.getX()][location.getY()].isShip();
   }

   public Ship getShip(Location location){
      return this.board[location.getX()][location.getY()].getShip();
   }







}