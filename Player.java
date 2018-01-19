//Class name: Player
//Author: Annie Gao
//Date: Jan. 8, 2018
//School: A.Y.Jackson S.S.
//Purpose: to represent the character and actions of the user in the game

import java.io.*;
import java.util.*;
public class Player{
   private String username;
   private String filename;
   private Hangar hangar;
   private Fleet fleet;
   private HangarFrame hangarFrame;
   private int score;
   private int numCoins;
   private int shipsBoughtThisTime;
   public static final int HANGAR = 0;
   public static final int FLEET = 1;
   public static final String IMAGE_FILE_FILE = "images.txt";
   public static final String NO_PIC = "nopic.jpg";
   public static final String AI_SHIP_IMAGE = "aishipimage.jpg";
   public static final String WALL_IMAGE = "wall.jpg";
	public static final int BASIC_COINS = 1000;
   
   
   //Constructor of the Player class
   //takes in the username of this user as a String   
   public Player(String un){
      username = un;
      filename = un + ".txt";
      hangar = new Hangar();
		fleet = new Fleet();
      load();
      hangarFrame = new HangarFrame(this);
   }
   
   //Accessor method for the username of this player
   public String getUsername(){
      return username;
   }
   
   //Mutator method for the username of this player
   //takes in the String to which the usernname of this player will be changed
   public void setUsername(String un){
      username = un;
   }
   
   //Accessor method for the name of the file in which the information of this player is stored
   public String getFilename(){
      return filename;
   }
   
   //Mutator method for the name of the file in which the information of this player is stored
   //takes in the String to which he player filename will be changed
   public void setFilename(String fn){
      filename = fn;
   }
   
   //Accessor method for the Hangar object associated with this player
   public Hangar getHangar(){
      return hangar;
   }
   
   //Mutator method for the Hangar object associated with this player
   //takes in a Hangar object to which the hangar of this player will be changed
   public void setHangar(Hangar h){
      hangar = h;
   }
   
   //Accessor method for the Fleet object associated with this player
   public Fleet getFleet(){
      return fleet;
   }
   
   //Mutator method for the Fleet object associated with this player
   //takes in a Fleet object to which the fleet of this player will be set
   public void setFleet(Fleet f){
      fleet = f;
   }
   
   //Accessor method for the number of coins owned by this player
   public int getNumCoins(){
      return numCoins;
   }
   
   //Mutator method for the number of coins earned by this player
   //takes in the new number of coins that the player owns as an int
   public void setNumCoins(int nc){
      numCoins = nc;
   }
   
   //Accessor method for the total score of this player
   public int getScore(){
      return score;
   }
   
   //Mutator method for the total score of this player
   //takes in the new score total of this player as an int
   public void setScore(int s){
      score = s;
   }
   
   //Calculates the total score of the player
   public int calcScore(){
      
      return hangar.getScoreTotal() + fleet.getScoreTotal();
      
   }
   
	//loads player information from file, initializes all the fields
   public void load(){
      try{
			//create a BufferedReader object to read in the player information
			BufferedReader f = new BufferedReader(new FileReader(filename));
			
			//skip username line
			f.readLine();
			//read in the score of the player, on the second line
			score = Integer.parseInt(f.readLine());
			//read in number of coins owned by the player
			numCoins = Integer.parseInt(f.readLine());
		
			//read in and discard blank line separating chunks of information
			f.readLine();
			
			String s;
         
         hangar.loadNext(f);
			
         fleet.loadNext(f);
         
         //close the BufferedReader
			f.close();
         
		} catch(IOException e){
			System.out.println("File error");
		}
   }
   
   public String getNewShipImageShort(){
      try{
         
         BufferedReader f = new BufferedReader(new FileReader(IMAGE_FILE_FILE));
         
         String s;
         while( (s = f.readLine()) != null){
            boolean taken = false;
            for(int i = 0; i < Hangar.MAX_SHIPS && !taken; i++){
               if(hangar.getShip(i) != null && hangar.getShip(i).getImageShort().equals(s)){
                  taken = true;
               }
            }
            for(int i = 0; i < Fleet.MAX_SHIPS && !taken; i++){
               if(fleet.getShip(i) != null && fleet.getShip(i).getImageShort().equals(s)){
                  taken = true;
               }
            }
            if(!taken) return s;
         }
         
         return null;
         
      } catch(IOException e){
         System.out.println("Image File Error");
      }
      return null;
   }
   
   //contains the code to be run when the player clicks the button to buy a Ship
   //takes in the id of the Ship-containing slot in hangar that the player wishes to purchase a Ship into
   public boolean buyShip(int id){
      
      //create a boolean to hold whether the player wishes to complete this transaction
      boolean buy;
      
      //checks if the player has enough money to buy this Ship
      if(numCoins >= Ship.BASIC_COST){
         //confirm with player
         buy = hangarFrame.askQuestion("Are you sure you would like to buy this ship?", "Sure?");
         //checks if the player wishes to continue with the transaction
         if(buy){
            //decrease the number of coins the player owns by the amount required to buy this Ship
            numCoins -= Ship.BASIC_COST;
            //add the newly-acquired Ship to hangar
            return hangar.addNewShip(id, getNewShipImageShort());
         } 
      }
      //return false to signify that the transaction did not go through
      return false;
   }
   
   //contains the code to be run when the player clicks the button to sell a ship
   //takes in the id of the Ship-containing slot that holds the Ship the player has chosen to sell
   public boolean sellShip(int id){
      
      //create a boolean to hold whether the player wishes to complete the transaction
      boolean sell;
      
      //confirm with player
      sell = hangarFrame.askQuestion("Are you sure you would like to sell this ship?", "Sure?");
      
      //checks if the player wishes to continue with the transaction
      if(sell){
         //increase the number of coins the player has by half the value of the ship they wish to sell
         numCoins += hangar.getShipSellPrice(id);
         //remove the newly-sold ship from hangar
         hangar.deleteShip(id);
         //return true to signify that the transaction is complete
         return true;
      }
      //return false to signify that the transaction did not go through
      return false;
   }
   
	public boolean upgradeShip(int id, int upgrade){
      
  		//check whether upgrades can still be done to this Ship
		if(!hangar.getShipUpgradable(id)){
			//output error message
			
			
			return false;
		}    
		
		
		//if the player does have enough money
		if(numCoins >= hangar.getShipUpgradeCost(id, upgrade)){
			//confirm with player that they wish to proceed with the transaction
		   boolean cont;
         cont = hangarFrame.askQuestion("Are you sure you would like to upgrade this ship?", "Sure?");
         
			//upgrade the ship through hangar
			if(cont){
            numCoins -= hangar.getShipUpgradeCost(id, upgrade);
            hangar.upgradeShip(id, upgrade);
            return true;
         }
			
		} else {
			//output error message
			
			
		}
		
		//return false to signify that the transaction did not go through
		return false;
   }
   
	//saves player information to the corresponding file
   public void save(){
   	try{
			//create a PrintWriter object to write to file
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			
			//first chunk of information: username, score, number of coins
			out.println(username);
			out.println(score);
			out.println(numCoins);
			
			//separate chunks of information with a blank line
			out.println("");
			
			out.println(hangar.getPrintString());
			
			//blank line to separate chunks of information
			out.println("");
			
			out.println(fleet.getPrintString());
			
			//close PrintWriter and flush stream
			out.close();
         
     } catch(IOException e){
			System.out.println("File error");
		}
   }
   
	public static void createFirstFile(String un){
		try{
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(un + ".txt")));
			
			out.println(un);
			out.println(0);
			out.println(BASIC_COINS);
			out.println("");
			out.println("");
			out.close();
			
		} catch(IOException e){
			System.out.println("File error in making new player file");
		}
	}
	
	//contains the code to be run when the player clicks the button to switch the contents of two Ship-containing slots
	//takes in the storage holding the first Ship (hangar or fleet) as an int
	//takes in the id of the first Ship in its storage as an int
	//takes in the storage holding the second Ship (hangar or fleet) as an int
	//takes in the id of the second Ship in its storage as an int
	public boolean swapShip(int hold1, int id1, int hold2, int id2){
      
		//if the first Ship is from hangar and the second Ship is from hangar
		if(hold1 == HANGAR && hold2 == HANGAR){
		
			//store the first Ship in a temporary Ship object
         Ship temp = hangar.getShip(id1);
			//set the first Ship to be equal to the second Ship
			//(delete the first Ship from the slot and add the second Ship into the slot)
			hangar.setShip(id1, hangar.getShip(id2));
			//set the second Ship to be equal to the first Ship, which is stored in the temporary Ship object
         //(set the Ship contained by the slot containing the second Ship to the Ship stored in the temporary Ship object)
			hangar.setShip(id2, temp);
   		//return true to signify that the swap was successful      
			return true;
      }
		
		//if the first Ship is from hangar and the second Ship is from fleet
      if(hold1 == HANGAR && hold2 == FLEET){
			
			//store the first Ship in a temporary Ship object
         Ship temp = hangar.getShip(id1);
			//set the first Ship to be equal to the second Ship
	      hangar.setShip(id1, fleet.getShip(id2));
			//set the second Ship to the first Ship, which is stored in the temporary Ship variable
         fleet.setShip(id2, temp);
			//return true to signify that the swap was successful
         return true;
      }
		
		//if the first Ship if from fleet and the second Ship is from hangar
      if(hold1 == FLEET && hold2 == HANGAR){
		
			//store the first Ship in a temporary Ship object
         Ship temp = fleet.getShip(id1);
			//set the first Ship to be equal to the second Ship
	      fleet.setShip(id1, hangar.getShip(id2));
			//set the second Ship to be equal to the first Ship, which is stored in the temporary variable
   		hangar.setShip(id2, temp);
			//return true to signify that the swap was successful
         return true;
      }
		
		//if the first Ship if from fleet and the second Ship is from fleet
      if(hold1 == FLEET && hold2 == FLEET){
         
			//store the first Ship in a temporary Ship object
			Ship temp = fleet.getShip(id1);
			//set the first Ship to be equal to the second Ship
   		fleet.setShip(id1, fleet.getShip(id2));
			//set the second Ship to be equal to the first Ship, which is stored in the temporary Ship variable
   		fleet.setShip(id2, temp);
			//return true to signify that the swap was successful
         return true;
      }
		//return false to signify that the swap did not go through
      return false;
   }
   
	//prints information of player to console in a clear fashion for checking and debugging purposes
   public String toString(){
      return "Username: " + username + "\nFilename: " + filename + "\nHangar: " + hangar.toString() + "\nFleet: " + fleet.toString() + "\nScore: " + score + "\nNumber of Coins: " + numCoins;
   }
}
