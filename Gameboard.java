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
   	
   // to determine result of game for checkEnd
      static final int NOT_END = Map.NOT_END;
      static final int USER_WINS = Map.USER_WINS;
      static final int AI_WINS = Map.I_WINS;
   
   // generates the map 
   
   
   // adds the player fleet into the map (ship by ship)
       private void addFleet(Fleet fleet){}
   
   // generates and adds Enemies into the map (ai) (ship by ship), the ai's ship's upgrades will be determined by the player's score
       private void addEnemies(int numEnemies, int playerScore){}	
       
   // starts and initializes the combat gameplay, initializes the map, and adds the player and ai fleets, loop thorught play and aiplay to allower the user and ai to play, checks endgame after every turn
       public void startCombat(Player player){
         map = new Map(0); // 0 IS TO BE PLAYER SCORE TEMP WORKAROUND**********************************
      }
   
   // ckecks if the game has ended (one side has no ships) (calls checkEnd in map and acts accordingly)
       private void checkEnd(){
       
         map.checkEnd();

      }
   
   // end the game and reward the player with money accordingly
       private boolean endGame(boolean playerWin){}
   
   // get the user to select his/her ship during his/her turn, returns location of selected ship
       private Location selectShip(){}
   
   // gets the usert select his/her action during his/her turn, returns int, each interger represents a different action
       private int selectAction(){}
   
   // allows the user to plat duing hes her turn, allows user to select and act on the ships
       private void play();
   
   // attempts to move a ship from one location to another
       private void moveShip(Location currentLocation, Location targetLocation, boolean[][] validMap){}
   
   // shows the user all the possible places the ship can move
       private void displayPossibleMovement(boolean[][] validMap){}
   
   // returns all the valid moves for a certain ship
       private boolean[][] allValidMoves(Location shipLocation){}
   
   // attempts to attack a ship using another ship
       private void attackShip(Location currentLocation, Location targetLocation, boolean[][] validMap){}
   
   // shows the user all possible palces the ship can attack
       private void displayPossibleAttack(boolean[][] validMap){}
   
   // returns all the valid attacks for a certain ship
       private boolean[][] allValidAttack(Location shipLocation){}
   
   // plays the ai turn (cointains all ai logic)
       private void aiPlay(){}
   
   // allows the ai to act on a ship (use the ship)
       private void aiActShip(location shipLocation){}
   
   // attacks any and all possible play ships in range of the ship given in location
       private void aiAttack(Location shipLocation, boolean[][] validMap){}
   
   // moves ai ship depending on player location and depending on if the ship has attacked or not
       private void aiMove(Location shipLocation, boolean[][] validMap){}
   
   }










