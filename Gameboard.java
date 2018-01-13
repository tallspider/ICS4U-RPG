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
Class Name: Gameboard
Author: David Qian 
Date: January 7, 2018
School: A.Y.Jackson S.S
Purpose: Cointains the map and combat
*/

class Gameboard{
    
   // the map cointains information regarding palyer and ai ships aswell as terrain (where walls are)
   private Map map;
      	
   // the player cointains the player information and is used to spawn in the ships from player's fleet, as well as player's score to determine the difficulty of the map
   private Player player;
      
   // to determine the player's command when acting on a ship
   private static final int CANCEL = 0;
   private static final int ATTACK = 1;
   private static final int TRAVEL = 2;
   
   // ammount of coins rewarded after a player finishes a game (depending on if the player won or not)
   private static final int WIN_REWARD= 500;
   private static final int LOSE_REWARD = 50;

   
   // adds the player fleet into the map (ship by ship)
   private void addFleet(Fleet fleet){
       
      for (int i = 0 ; i < Fleet.MAX_SHIPS ; i++){
         
         if(!fleet.isEmpty(i)){
            map.addShipBottomRight(fleet.getShip(i));
         }
      }
       
   }
   
   // generates and adds Enemies into the map (ai) (ship by ship), the ai's ship's upgrades will be determined by the player's score
   private void addEnemies(int numEnemies, int playerScore){
       
      for (int i = 0 ; i < numEnemies ; i++){
         map.addRandomShip(new Ship(""));
      }
       
       
   }	
       
   // starts and initializes the combat gameplay, initializes the map, and adds the player and ai fleets, loop thorught play and aiplay to allower the user and ai to play, checks endgame after every turn
   public void startCombat(Player player){
      map = new Map(player.getScore());
   }
   
   // ckecks if the game has ended (one side has no ships)
   public void checkEnd(){
       
      boolean userHasShips = false;
      boolean aiHasShips = false;
       
      //loops through entire board to see if there are any ships owned by ai / player and get the result accordingly
      for (int x = 0 ; x < map.WIDTH_OF_MAP ; x++){
         for (int y = 0 ; y < map.LENGTH_OF_MAP ; y++){
            if (map.isShip(new Location(x,y))){
               if(map.getShip(new Location(x,y)).getOwnedByPlayer()){
                  userHasShips = true;
               }
               else{
                  aiHasShips = true;
               }
            }
         }
      }
      
   // ends game once a team is defeated (no more ships left)
      if (userHasShips && !aiHasShips){
         endGame(true);
      }
      else if (!userHasShips && aiHasShips){
         endGame(false);
      }
      System.out.println("Error: Both teams defeated");
   }
   
   // end the game and reward the player with money accordingly
   private void endGame(boolean playerWin){
      if(playerWin){
         player.setNumCoins(player.getNumCoins() + WIN_REWARD);
      }
      else{
         player.setNumCoins(player.getNumCoins() + LOSE_REWARD);
      }
   
   }
   
   // get the user to select his/her ship during his/her turn, returns location of selected ship
   private Location selectShip(){
      return new Location(0,0); //************************TEMP WORKAROUND*******************************************
   }
   
   // gets the usert select his/her action during his/her turn, returns int, each interger represents a different action
   private int selectAction(){
      return 0; //************************TEMP WORKAROUND*******************************************
   }
   
   // allows the user to plat duing hes her turn, allows user to select and act on the ships
   private void play(){}
   
   // attempts to move a ship from one location to another
   private void moveShip(Location currentLocation, Location targetLocation, boolean[][] validMap){
   
   // this ship's pointer will get moved, so we need to get that ship's pointer;
      Ship ship = map.getShip(currentLocation);
      
      if (validMap[targetLocation.getX()][targetLocation.getY()]){
         map.setShip(ship,targetLocation);
         map.setEmpty(currentLocation);
      }
   }
   
   // shows the user all the possible places the ship can move
   private void displayPossibleMovement(boolean[][] validMap){}
   
   // returns all the valid moves for a certain ship
   private boolean[][] allValidMoves(Location shipLocation){
   
   // gets the ship from the location in order to access the ship's travel range for calculation
      Ship ship = map.getShip(shipLocation);
      
   //valid is a boolean array that is parallel to the map, true represents the spaces the ship can move to and false represents the places that the ship cannot move to
      boolean[][]validMap = new boolean[Map.WIDTH_OF_MAP][Map.LENGTH_OF_MAP];
   
      for (int x = 0 ; x < validMap.length ; x ++){
         for (int y = 0 ; y < validMap[x].length ; y++){
            validMap[x][y] = false;
         }
      }   
      
      // starts the recursive statment, first iteration will check ann squares around the ship, second will check all squares around the first iteration, and so on.
      validMap = allValidMoves(shipLocation, ship.getTravelRange(), validMap);
      
      return validMap;
   }
   
   // recursive statment for finding which moves are valid and which are not, a move is valid if the destination can be reached withing a certain ammount of moves and moving through only other valid moves
   private boolean[][] allValidMoves(Location currentLocation, int movesLeft, boolean[][] validMap){
   
   // target location is a location that is adjcent to the current location, target location is the location currently being tested to see if it is valid(and all of targetLocations adjcent locations will also be tested)
      Location targetLocation;
   
   // a seperate if statment is used for isInMap because that must be checked before checking if the target location is valid or not, as to not get a array out of bounds exception
   
      if (movesLeft > 0){
      
         targetLocation = new Location(currentLocation.getX() + 1, currentLocation.getY());
         if (map.isInMap(targetLocation)){
            if (map.isEmpty(targetLocation)){
               validMap[targetLocation.getX()][targetLocation.getY()] = true;
               validMap = allValidMoves(targetLocation, movesLeft - 1, validMap);
            }
         }
         
         targetLocation = new Location(currentLocation.getX() - 1, currentLocation.getY());
         if (map.isInMap(targetLocation)){
            if (map.isEmpty(targetLocation)){
               validMap[targetLocation.getX()][targetLocation.getY()] = true;
               validMap = allValidMoves(targetLocation, movesLeft - 1, validMap);
            }
         }
         
         targetLocation = new Location(currentLocation.getX(), currentLocation.getY() + 1);
         if (map.isInMap(targetLocation)){
            if (map.isEmpty(targetLocation)){
               validMap[targetLocation.getX()][targetLocation.getY()] = true;
               validMap = allValidMoves(targetLocation, movesLeft - 1, validMap);
            }
         }
         
         targetLocation = new Location(currentLocation.getX(), currentLocation.getY() - 1);
         if (map.isInMap(targetLocation)){
            if (map.isEmpty(targetLocation)){
               validMap[targetLocation.getX()][targetLocation.getY()] = true;
               validMap = allValidMoves(targetLocation, movesLeft - 1, validMap);
            }
         } 
      }
   
      return validMap;
   }
   
   // attempts to attack a ship using another ship
   private void attackShip(Location currentLocation, Location targetLocation, boolean[][] validMap){
   
   // may be used for updating the ship's times attacked status
      Ship ship = map.getShip(currentLocation);
      
   // basically removes the target ship and updates times attacked (can only attack ships although empty spaces shows that they can be attacked)
      if (validMap[targetLocation.getX()][targetLocation.getY()] && map.isShip(targetLocation)){
         map.setEmpty(targetLocation);
         ship.setTimesAttacked(ship.getTimesAttacked() + 1);
      }
   
   }
   
   // shows the user all possible palces the ship can attack
   private void displayPossibleAttack(boolean[][] validMap){}
   
   // returns all the valid attacks for a certain ship (valid if spot is empty, ship has free attacks (firing speed > times attacked), and if the ship is in range)
   private boolean[][] allValidAttack(Location shipLocation){
   
   // gets the ship from the location in order to access the ship's attack range and attack speed for calculation
      Ship ship = map.getShip(shipLocation);
      
   //valid is a boolean array that is parallel to the map, true represents the spaces the ship can attack and false represents the places that the ship cannot attack, note that empty spaces are also "attackable", while only ships can be attacked, the showing of empty spaces being "attackable" shows to the user that if a ship were in that range, they can attack it
      boolean[][]validMap = new boolean[Map.WIDTH_OF_MAP][Map.LENGTH_OF_MAP];
      
      for (int x = 0 ; x < validMap.length ; x++){
         for (int y = 0 ; y < validMap[x].length ; y++){
            if (!map.isWall(new Location(x,y)) && shipLocation.compare(new Location(x,y)) <= ship.getAttackRange() && ship.getTimesAttacked() < ship.getFiringSpeed()){
               validMap[x][y] = false;
            }
            else{
               validMap[x][y] = true;
            }
         }
      }
   
      return validMap;
   }
   
   // plays the ai turn (cointains all ai logic)
   private void aiPlay(){}
   
   // allows the ai to act on a ship (use the ship)
   private void aiActShip(Location shipLocation){}
   
   // attacks any and all possible play ships in range of the ship given in location
   private void aiAttack(Location shipLocation, boolean[][] validMap){}
   
   // moves ai ship depending on player location and depending on if the ship has attacked or not
   private void aiMove(Location shipLocation, boolean[][] validMap){}
   
}










