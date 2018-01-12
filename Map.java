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
   import java.util.Random;

    public class Map{
   
   //********************************************************************TEMPORARY WORKAROUND **********************************************************************//
   // the minimun ammount of empty spots a map is allowed to have (maxinum number of enemies + maxinum number of fleet ships)
      public static int MINIMUM_EMPTY_SPOTS = 15;
   //********************************************************************TEMPORARY WORKAROUND **********************************************************************//
      public static int WIDTH_OF_MAP = 16;
      public static int LENGTH_OF_MAP = 16;
   
   // the board is a 2d spot array
      Spot[][] board;
   
       public Map(){
         board = new Spot[WIDTH_OF_MAP][LENGTH_OF_MAP];
         
         for (int x = 0 ; x < board.length ; x ++){
            for (int y = 0 ; y < board[x].length ; y++){
               board[x][y] = new Spot();
            }
         }
      }
   
   // generates a map based on player score
       public Map(int score){
       
         board = new Spot[WIDTH_OF_MAP][LENGTH_OF_MAP];
         
         for (int x = 0 ; x < board.length ; x ++){
            for (int y = 0 ; y < board[x].length ; y++){
               board[x][y] = new Spot();
            }
         }	
      	
         Map mapCopy = copy();
       
         do{
            board = mapCopy.getBoard();  
            mapCopy = copy();
            mapCopy.addRandomWall();
         }while(mapCopy.isValid());
         
      }
   
   // constructor 
       public Map(Spot[][] board){
         this.board = board;
      }
   
   // checks if the given map is valid, a map is considered valid if every non-wall spot is connected to each other either directly or indirectly using other non-wall spots, also a valid map must have atleast (max number of enemies) + (max number of fleet ships) spots
       private boolean isValid(){
      
         int numEmptySpots = numEmptySpots();
         if (numEmptySpots < MINIMUM_EMPTY_SPOTS){
            return false;
         }
         
       //makes a copy of the map to wall flood, then checks if there are any empty spaces left, if so, it would mean there are 2 or more spots that are not directly or indirectly connected to each other
         Map mapCopy = copy(); 
         mapCopy.floodFirstEmpty();
      
      // if board is filled completely with walls, this map is fully connected and thus valid, otherwise it is invalid
         return mapCopy.isFilledWall();
      }
   
   // loops through the entire board and counts the number of empty spots
       private int numEmptySpots(){
      
         int numEmptySpots = 0;
      
         for (int x = 0 ; x < board.length ; x ++){
            for (int y = 0 ; y < board[x].length ; y++){
               if (board[x][y].isEmpty()){
                  numEmptySpots ++;
               }
            }
         }
         return numEmptySpots;
      }
   
   // finds the first empty spot and floods only that spot, alreadyFlooded is used to prevent wallflood to be called mutiple times
       private void floodFirstEmpty(){
         boolean alreadyFlooded = false;
         for (int x = 0 ; x < board.length && alreadyFlooded == false ; x ++){
            for (int y = 0 ; y < board[x].length && alreadyFlooded == false ; y++){
               if (board[x][y].isEmpty()){
                  wallFlood(new Location(x,y));
                  alreadyFlooded = true;
               }
            }
         }
      }
   
   // loops throught the entire copy of board to make sure every spot is filled in with wall, this means that all empty spots are connected
       private boolean isFilledWall(){
         for (int x = 0 ; x < board.length; x ++){
            for (int y = 0 ; y < board[x].length ; y++){
               if (board[x][y].isEmpty()){
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
       private boolean isInMap(Location location){
         if (location.getX() >= 0 && location.getX() < board.length ){
            if (location.getY() >= 0 && location.getY() < board[location.getX()].length){
               return true;
            }
         }
         return false;
      }
   
   // makes a copy of this map (this only does walls and empty spots)
       private Map copy(){
           
         Spot[][] boardCopy;
         boardCopy = new Spot[board.length][]; 
         for (int i = 0 ; i < board.length ; i++){
            boardCopy[i] = new Spot[board[i].length];
         }
         
         for (int x = 0 ; x < boardCopy.length ; x ++){
            for (int y = 0 ; y < boardCopy[x].length ; y++){
               boardCopy[x][y] = new Spot();
            }
         }
        
      // since at this point the only possiblities for any spot is either a wall or empty it is safe to assume that anythign that is not a wall is empty and vice versa
      // loop through the entire map and counts the number of empty spots
         for (int x = 0 ; x < board.length ; x ++){
            for (int y = 0 ; y < board[x].length ; y++){
               if (!board[x][y].isEmpty()){
                  boardCopy[x][y].setWall();
               }
            }
         }
         
         return new Map(boardCopy);  	
      }
   
   // adds one entity to a random spot in the map
       public void addRandomEntity(Entity entity){
      
      // gets the number of empty spots and generates a number from 0 to number of empty spots -1
         int numEmptySpots = numEmptySpots();
         int locationAdd = new Random().nextInt(numEmptySpots);
      	
         int currentLocation = 0;
      	
      	// adds a wall in the location specified (locationAdd), location add only counts the number of empty spots (this is to prevent adding a wall on a currently existing wall)	
         for (int x = 0 ; x < board.length && currentLocation <= locationAdd ; x++){
            for (int y = 0 ; y < board[x].length && currentLocation <= locationAdd ; y++){
               if(board[x][y].isEmpty()){
                  if(locationAdd == currentLocation){
                     setEntity(new Location(x,y), entity);
                  }
                  currentLocation++;
               }  
            }
         }
      }
   
       public void addRandomWall(){
         addRandomEntity(new Wall());
      }
      
       public void addRandomShip(Ship ship){
         addRandomEntity(ship);
      }
   
   // loops through the entire board and finds the empty space nearest to the target (bottom Right)
       public void addShipBottomRight(Ship ship){
      
         Location closest;
         Location bottomRight = new Location(WIDTH_OF_MAP,0);  

         for (int x = 0 ; x < board.length ; x ++){
            for (int y = 0 ; y < board[x].length ; y++){
            
               if (board[x][y].isEmpty()){
                  if (closest == null){
                     closest = new Location(x,y);
                  }
                  else if (bottomRight.compare(new Location(x,y)) < bottomRight.compare(closest)){
                     closest = new Location(x,y);
                  }
               }
               
            }
         }
         
         setShip(ship,closest);
      }
   // accessors and mutators
   
   // setEntity also known as spawn, but setWall and setShip is differentiated and easier to understand 
   
       public void setEntity(Location location, Entity entity){
         if (entity == null){
            setEmpty(location);
         }
         else if (entity.isWall()){
            setWall(location);
         }
         else if (entity.isShip()){
            setShip((Ship)entity,location);
         }
      }
      
       public void setWall(Location location){
         this.board[location.getX()][location.getY()].setWall();
      }
   
       public void setShip(Ship ship,Location location){
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
   
       public Spot[][] getBoard(){
         return this.board;
      }
   
   //********************SUBJECT TO CHANGE*************************** USED TO TEST/DEBUG****************
       public void display(){
      
         for (int y = LENGTH_OF_MAP ; y >= 0 ; y--){
            for (int x = 0 ; x < WIDTH_OF_MAP ; x++){
               if(board[x][y].isEmpty()){
                  System.out.print(".");
               }
               else{
                  System.out.print("#");
               }
            }
            System.out.println();
         }
      }
   
   }
