   /*
Class Name: Gameboard
Author: David Qian 
Date: January 7, 2018
School: A.Y.Jackson S.S
Purpose: Cointains the map and combat
*/

   import java.util.Random;
   import java.io.*;

    class Gameboard{
    
   // the map cointains information regarding palyer and ai ships aswell as terrain (where walls are)
      private Map map;
      	
   // the player cointains the player information and is used to spawn in the ships from player's fleet, as well as player's score to determine the difficulty of the map
      private Player player;
      
      private GameBoardGUI gui; 
      
   // to determine the player's command when acting on a ship
      private static final int CANCEL = 0;
      private static final int ATTACK = 1;
      private static final int MOVE = 2;
   
   // ammount of coins rewarded after a player finishes a game (depending on if the player won or not)
      private static final int WIN_REWARD = 500;
      private static final int LOSE_REWARD = 50;
   
   // the maxinum and mininum of ai ships generated
      public static final int MAX_AI_SHIPS = 10;
      public static final int MIN_AI_SHIPS = 5;
   
   // ammount of delay between ai doing things (so user can see what the ai did)
      private static final int DELAY_BETWEEN_AI_ACTIONS = 250;
   
   // to get input from the gui
      public PipedInputStream input;
   
   // adds the player fleet into the map (ship by ship)
       private void addFleet(Fleet fleet){
       
      // go throught the entire fleet and add in the ships one by one
       
         for (int i = 0 ; i < Fleet.MAX_SHIPS ; i++){ 
            if(!fleet.isEmpty(i)){
               map.addShipBottomRight(fleet.getShip(i));
            }
         }
       
      }
   
   // generates and adds Enemies into the map (ai) (ship by ship), the ai's ship's upgrades will be determined by the player's score
       private void addEnemies(int numEnemies, int playerScore){
       
         int attackRange;
         int travelRange;
         int firingSpeed;
      
      // averageStat is the ballpark total average stats of the ai ship based on player score (should be a weaker than player's ships)
         int averageStat = (playerScore / (Fleet.MAX_SHIPS + Hangar.MAX_SHIPS)) / numEnemies / 4;
         Random rand = new Random();
      
         for (int i = 0 ; i < numEnemies ; i++){
            attackRange = 1  + averageStat + rand.nextInt(2);
            travelRange = 1  + averageStat + rand.nextInt(2);
            firingSpeed = 1  + averageStat + rand.nextInt(2);
            map.addRandomShip(new Ship("AI SHIP", Player.AI_SHIP_IMAGE, attackRange, travelRange, firingSpeed, 0, 0 , false));
         }
       
       
      }	
       
   // starts and initializes the combat gameplay, initializes the map, and adds the player and ai fleets, loop thorught play and aiplay to allower the user and ai to play, checks endgame after every turn
       public void startCombat(Player player){
      
      // generate map and enemies, and adds the player fleet
         map = new Map();
         addEnemies(new Random().nextInt(MAX_AI_SHIPS - MIN_AI_SHIPS + 1)+ MIN_AI_SHIPS,player.getScore());
         addFleet(player.getFleet());
         gui = new GameBoardGUI();
         try{
            input = new PipedInputStream(gui.output);
         }
             catch (IOException iox){
               System.out.println("piped input error");
            }
         boolean gameEnd = false;
      
         while (!gameEnd){
         
            play();
            gameEnd = !checkEnd();
         
         // ai only plays if the game has not ended
            if (!gameEnd){
               aiPlay();
               gameEnd = !checkEnd();
            }
         }
      
      }
   
   // ckecks if the game has ended (one side has no ships) and displays victory / defeat messages aswell as reward money when game ends
       public boolean checkEnd(){
       
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
            return true;
         }
         else if (!userHasShips && aiHasShips){
            endGame(false);
            return true;
         }
         else if (!userHasShips && !aiHasShips){
            System.out.println("Error: Both teams defeated");
            return true;
         }
         return false;
      }
   
   // end the game and reward the player with money accordingly
       private void endGame(boolean playerWin){
         if(playerWin){
            player.setNumCoins(player.getNumCoins() + WIN_REWARD);
            displayVictory();
         }
         else{
            player.setNumCoins(player.getNumCoins() + LOSE_REWARD);
            displayDeafeat();
         }
      }
   
   // displays that the player has won
       private void displayVictory(){}
   
   // displays that the player has lost
       private void displayDeafeat(){}
   
   // get the user to select his/her ship during his/her turn, returns location of selected ship
       private Location selectLocation(){
      
         int x = -1,y = -1;
         try{
            x = input.read();
            y = input.read();
         }
             catch(IOException iox){
            
            }
      // -1,-1 is to end turn
         if(x == -1 && y == -1){
            return null;
         }
      
         return new Location(x,y); 
      }
   
   // gets the usert select his/her action during his/her turn, returns int, each interger represents a different action
       private int selectAction(){
         return 0; //************************TEMP WORKAROUND*******************************************
      }
   
   // allows the user to play duing hes / her turn, allows user to select and act on the ships
       private void play(){
      
         Location shipLocation;
         int shipAction;
         boolean[][] validMap;
      
      // this loop if to let user select ships ends turn when the location is null
      // no ship is currently selected therefor selectLocation will to be to select ship
         shipLocation = selectLocation();
         gui.updateInfoArea(map,shipLocation);
         gui.updatePlayArea(map);
         while(shipLocation != null){
         
         // only lets user act on a ship, if theres a ship where the user selected, and it belongs to the player
            if (map.isShip(shipLocation)){
               if (map.getShip(shipLocation).getOwnedByPlayer()){
               
               // here we enter the ship action loop, where the player can move and attack the ship untill he/she deselects it (selects cancel)
               
                  shipAction = selectAction();
               
                  while (shipAction != CANCEL){
                  
                     if (shipAction == MOVE){
                     
                        validMap = allValidMoves(shipLocation);
                        displayPossibleMovement(validMap);
                        moveShip(shipLocation, selectLocation(), validMap);
                     }
                     else if(shipAction == ATTACK){
                     
                        validMap = allValidAttack(shipLocation);
                        displayPossibleAttack(validMap);
                        attackShip(shipLocation, selectLocation(), validMap);
                     }
                  
                  // visuals must be updated once player does an action, just like with ai
                     gui.updatePlayArea(map);
                  
                     shipAction = selectAction();
                  }
               
               }
            }
            shipLocation = selectLocation();
         // gives information regarding the location selected, regardless of what it is
            gui.updateInfoArea(map,shipLocation);
         }
      // end of player turn  
      }
   
   // attempts to move a ship from one location to another
       private void moveShip(Location currentLocation, Location targetLocation, boolean[][] validMap){
      
      // this ship's pointer will get moved, so we need to get that ship's pointer;
         Ship ship = map.getShip(currentLocation);
      
         if (validMap[targetLocation.getX()][targetLocation.getY()] && !ship.getMovedAlready()){
            ship.setMovedAlready(true);
            map.setShip(ship,targetLocation);
            map.setEmpty(currentLocation);
         }
      }
   
   // shows the user all the possible places the ship can move
       private void displayPossibleMovement(boolean[][] validMap){
         gui.displayPossibleMove(map, validMap);
      }
   
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
      
      //ship can only move once, so if the ship already moved, it cannot move again, so we return all false map if the ship already moved
         if (ship.getMovedAlready()){
            return validMap;
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
         if (validMap[targetLocation.getX()][targetLocation.getY()] && map.isShip(targetLocation) && ship.getTimesAttacked() < ship.getFiringSpeed()){
            map.setEmpty(targetLocation);
            ship.setTimesAttacked(ship.getTimesAttacked() + 1);
         }
      
      }
   
   // shows the user all possible palces the ship can attack
       private void displayPossibleAttack(boolean[][] validMap){
         gui.displayPossibleAttack(map, validMap);
      }
   
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
       private void aiPlay(){
      
      // testLocation and testShip are there to prevent the use of new Location(x,y) and map.getShip(new Location(x.y)) many times 
         Location testLocation;
         Ship testShip;
      
      // basically loops throught the entire map and acts on all ai ships
         for (int x = 0 ; x < Map.WIDTH_OF_MAP ; x++){
            for (int y = 0 ; y < Map.LENGTH_OF_MAP ; y++){
            
               testLocation = new Location(x,y);
               if (map.isShip(testLocation)){
               
                  testShip = map.getShip(testLocation);
               
                  if (!testShip.getOwnedByPlayer()){
                     aiActShip(testLocation);
                  }
               }
            
            }
         }
      
      }
   
   // allows the ai to act on a ship (use the ship) (no need to worry about ai using the ship twice, as nothing will happen due to gamelogic)
       private void aiActShip(Location shipLocation){
      
      // note how validMap is used for both attacking and moving, we can do this beause validmap has no purpose after being used in the attack or move method (just don't mix up the attack and move functions)
         boolean[][] validMap;
      
      // the delay is used so that the player can see the ai's moves, so that the ai's moves will not be instantanious 
      
         validMap = allValidAttack(shipLocation);
         aiAttack(shipLocation, validMap);
         gui.updatePlayArea(map);
         try{
            Thread.sleep(DELAY_BETWEEN_AI_ACTIONS);
         }
             catch (InterruptedException ie){}
      
         validMap = allValidMoves(shipLocation);
         aiMove (shipLocation, validMap);
         gui.updatePlayArea(map);
         try{
            Thread.sleep(DELAY_BETWEEN_AI_ACTIONS);
         }
             catch (InterruptedException ie){}
      
         validMap = allValidAttack(shipLocation);
         aiAttack(shipLocation, validMap);
         gui.updatePlayArea(map);
         try{
            Thread.sleep(DELAY_BETWEEN_AI_ACTIONS);
         }
             catch (InterruptedException ie){}
      }
   
   // attacks any and all possible play ships in range of the ship given in location (pew pew pew)
       private void aiAttack(Location shipLocation, boolean[][] validMap){
      
      // checking if the target ship is owned by player is in a seperate if statment because we first need to check if that location has a ship or not
      // this will attempt to attack regardless of whether or not the ship still can attack because attackShip() will check;
      
         for (int x = 0 ; x < validMap.length ; x++){
            for (int y = 0 ; y < validMap[x].length ; y++){
               if(validMap[x][y] && map.isShip(new Location(x,y))){
                  if (map.getShip(new Location(x,y)).getOwnedByPlayer()){
                     attackShip(shipLocation, new Location(x,y), validMap);
                  }
               }
            }
         }
      
      }
   
   // moves ai ship depending on player location and depending on if the ship has attacked or not (if ship did not attack yet, move in range of player ship, if ship did attack move away)
       private void aiMove(Location shipLocation, boolean[][] validMap){
      
      // ship used for times attacked and firing speed to determine if the ship can still attack
         Ship ship = map.getShip(shipLocation);
      
      // test location is used for finding closest player location
         Location closestPlayerLocation = new Location();
         Location testLocation;
      
      // only go throught all this logic if the ship has not moved yet
         if (!ship.getMovedAlready()){
         
            for (int x = 0 ; x < Map.WIDTH_OF_MAP ; x++){
               for (int y = 0 ; y < Map.LENGTH_OF_MAP ; y++){
                  testLocation = new Location(x,y);
               
                  if (map.isShip(testLocation)){
                     if (map.getShip(testLocation).getOwnedByPlayer()){
                        if (closestPlayerLocation == null){
                           closestPlayerLocation = testLocation;
                        }
                        else if(shipLocation.compare(closestPlayerLocation) > shipLocation.compare(testLocation)){
                           closestPlayerLocation = testLocation;
                        }
                     }
                  }
               
               }
            }
         
         
            if (ship.getTimesAttacked()< ship.getFiringSpeed()){
            // move towards closest player ship
            
               Location closestValidLocation = new Location();
            
               for (int x = 0 ; x < Map.WIDTH_OF_MAP ; x++){
                  for (int y = 0 ; y < Map.LENGTH_OF_MAP ; y++){
                  
                  // we are reusing the testlocation from finding the closest ship, because it is no longer being used                                                       (we are being very eco-friendly)
                     testLocation = new Location(x,y);
                  
                     if (validMap[x][y]){
                        if (closestValidLocation == null){
                           closestValidLocation = testLocation;
                        }
                        else if(shipLocation.compare(testLocation) < shipLocation.compare(closestValidLocation)){
                           closestValidLocation = testLocation;
                        }
                     }
                  
                  }
               }
            
            // only move if theres a valid location to move to
               if (!(closestValidLocation == null)){
                  moveShip(shipLocation, closestValidLocation,validMap);
               }
            
            }
            else {
            // move away from player ship
            
               Location farthestValidLocation = new Location();
            
               for (int x = 0 ; x < Map.WIDTH_OF_MAP ; x++){
                  for (int y = 0 ; y < Map.LENGTH_OF_MAP ; y++){
                  
                  // we are reusing the testlocation from finding the closest ship, because it is no longer being used                                                       (we are being very eco-friendly)
                     testLocation = new Location(x,y);
                  
                     if (validMap[x][y]){
                        if (farthestValidLocation == null){
                           farthestValidLocation = testLocation;
                        }
                        else if(shipLocation.compare(testLocation) > shipLocation.compare(farthestValidLocation)){
                           farthestValidLocation = testLocation;
                        }
                     }
                  
                  }
               }
            
            // only move if theres a valid location to move to
               if (!(farthestValidLocation == null)){
                  moveShip(shipLocation, farthestValidLocation,validMap);
               }
            }
         
         }
      
      }
   
   }










