//Class name: Player
//Author: Annie Gao
//Date: Jan. 8, 2018
//School: A.Y.Jackson S.S.
//Purpose: to represent the character and actions of the user in the game

import java.io.*;
public class Player{
   private String username;
   private String filename;
   private Hangar hangar;
   private Fleet fleet;
   private int score;
   private int numCoins;
   static final int HANGAR = 0;
   static final int FLEET = 1;
   
   //Constructor of the Player class
   //takes in the username of this user as a String   
   public Player(String un){
      username = un;
      filename = un + ".txt";
		hangar = new Hangar();
		fleet = new Fleet();
		
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
   public int setScore(int s){
      score = s;
   }
   
   //Calculates the total score of the player
   //returns the total score of the player calculated as the attackRange + travelRange + firingSpeed of every ship owned by the player
   public int calcScore(){
      
      //set the initial score to 0
      int totalScore = 0;
      
      //go through each of the slots potentially containing a ship in hangar
      //if thre is a Ship, or if the Ship object is not null, then add the attackRange, the travelRange and the firingSpeed of this Ship to the total score
      for(int eachShip = 0; eachShip < Hangar.MAX_SHIPS; eachShip++){
         Ship tempShip = hangar.getShips()[eachShip];
         if(tempShip != null){
            totalScore += tempShip.getAttackRange() + tempShip.getTravelRange() + tempShip.getFiringSpeed();
         }
      }
      
      //go through each of the slots potentially containing a ship in fleet
      //if thre is a Ship, or if the Ship object is not null, then add the attackRange, the travelRange and the firingSpeed of this Ship to the total score
      for(int eachShip = 0; eachShip < Fleet.MAX_SHIPS; eachShip++){
         Ship tempShip = hangar.getShips()[eachShip];
         if(tempShip != null){
            totalScore += tempShip.getAttackRange() + tempShip.getTravelRange() + tempShip.getFiringSpeed();
         }
      }
      
      //return the total score calculated
      return totalScore;
   }
   
   public void load(){
      try{
			BufferedReader f = new BufferedReader(new FileReader(filename));
			f.readLine();
			score = Integer.parseInt(f.readLine());
			numCoins = Integer.parseInt(f.readLine());
			f.readLine();
			
			String s;
			while( (s = f.readLine()) != "" && s != null){
				int tID = Integer.parseInt(s);
				String tname = f.readLine();
				int tattackRange = Integer.parseInt(f.readLine());
				int ttravelRange = Integer.parseInt(f.readLine());
				int tfiringSpeed = Integer.parseInt(f.readLine());
				int tupgradesLeft = Integer.parseInt(f.readLine());
				int tAR_Upgrades = Integer.parseInt(f.readLine());
				int tTR_Upgrade = Integer.parseInt(f.readLine());
				int tFS_Upgrade = Integer.parseInt(f.readLine());
				hangar.getShips()[tID] = new Ship(tname, tattackRange, ttravelRange, tfiringSpeed, tupgradesLeft, tAR_Upgrades, tTR_Upgrade, tFS_Upgrade, true);
			}
			
			while( (s = f.readLine()) != "" && s != null){
				int tID = Integer.parseInt(s);
				String tname = f.readLine();
				int tattackRange = Integer.parseInt(f.readLine());
				int ttravelRange = Integer.parseInt(f.readLine());
				int tfiringSpeed = Integer.parseInt(f.readLine());
				int tupgradesLeft = Integer.parseInt(f.readLine());
				int tAR_Upgrades = Integer.parseInt(f.readLine());
				int tTR_Upgrade = Integer.parseInt(f.readLine());
				int tFS_Upgrade = Integer.parseInt(f.readLine());
				fleet.getShips()[tID] = new Ship(tname, tattackRange, ttravelRange, tfiringSpeed, tupgradesLeft, tAR_Upgrades, tTR_Upgrade, tFS_Upgrade, true);
			}
		} catch(IOException e){
			System.out.println("File error");
		}
   }
   
   //contains the code to be run when the player clicks the button to buy a Ship
   //takes in the id of the Ship-containing slot in hangar that the player wishes to purchase a Ship into
   public boolean buyShip(int id){
      
      //create a boolean to hold whether the player wishes to complete this transaction
      boolean buy;
      
      //checks if the player has enough money to buy this Ship
      if(numCoins > Ship.BASIC_COST){
         //confirm with player
         
         //checks if the player wishes to continue with the transaction
         if(buy){
            //decrease the number of coins the player owns by the amount required to buy this Ship
            numCoins -= Ships.BASIC_COST;
            //add the newly-acquired Ship to hangar
            hangar.addShip(id);
            //return true to signify that the transaction is complete
            return true;
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
      
      
      //checks if the player wishes to continue with the transaction
      if(sell){
         //increase the number of coins the player has by half the value of the ship they wish to sell
         numCoins += hangar.getShip(id).value() / 2;
         //remove the mewly-sold ship from hangar
         hangar.deleteShip(id);
         //return true to signify that the transaction is complete
         return true;
      }
      //return false to signify that the transaction did not go through
      return false;
   }
   
	//calculates how much money
   public boolean upgradeShip(int id, int upgrade){
      
      //create a boolean to hold whether the player wishes to continue with this transaction
      boolean cont;
		//create an int variable to hold the number of coins required for the transaction
		int coinsRequired;
		
		//calculate the number of coins required to upgrade the Ship based on the type of upgrade chosen
      if(upgrade == AR_Upgrades){
		
			//if user chooses to upgrade attack range of ship, the cost is 100 times (the current attack range of the ship plus one)
      	coinsRequired = (hangar.getShips()[id].getAttackRange() + 1) * 100;
			
      } else if (upgrade == TR_Upgrades){
		
      	//if user chooses to upgrade travel range of ship, the cost is 100 times (the current travel range of the ship plus one)
      	coinsRequired = (hangar.getShips()[id].getTravelRange() + 1) * 100;
			
      } else if (upgrade == FS_Upgrades){
		
      	//if user chooses to upgrade firing speed of ship, the cost is 100 times (the current firing speed of the ship plus one)
      	coinsRequired = (hangar.getShips()[id].getFiringSpeed() + 1) * 100;
		}
		
		//if the player does have enough money
		if(numCoins >= coinsRequired){
			//confirm with player that they wish to proceed with the transaction
			
			
			//upgrade the ship through hangar
			hangar.upgradeShip(id, upgrade);
			
			//return true to signify that the transaction is complete
			return true;
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
			
			//second chunk of information: ships in hangar
			//for each slot in hangar:
			for(int eachShip = 0; eachShip < Hangar.MAX_SHIPS; eachShip++){
				
				//create temporary Ship variable to hold the Ship whose information is being saved
				Ship tempShip = hangar.getShips()[eachShip];
				
				//will not save anything if there is not a ship in this slot
				if(tempShip != null){
					//second chunk of information: Ship index, Ship name, attack 
					//range, travel range, firing speed, number of upgrades left,
					//number of each type of upgrade already applied for each Ship 
					//stored in hangar
					out.println(eachShip);
					out.println(tempShip.getName());
					out.println(tempShip.getAttackRange());
					out.println(tempShip.getTravelRange());
					out.println(tempShip.getFiringSpeed());
					out.println(tempShip.getUpgradesLeft());
					out.println(tempShip.getAR_Upgrades());
					out.println(tempShip.getTR_Upgrade());
					out.println(tempShip.getFS_Upgrade());
				}
			}
			
			//blank line to separate chunks of information
			out.println("");
			
			//third chunk of information: ships in fleet
			//for each slot in fleet:
			for(int eachShip = 0; eachShip < Fleet.MAX_SHIPS; eachShip++){
				
				//create temporary Ship variable to hold the Ship whose information is being saved
				Ship tempShip = fleet.getShips()[eachShip];
				
				//will not save anything if there is not a ship in this slot
				if(tempShip != null){
					//third chunk of information: Ship index, Ship name, attack 
					//range, travel range, firing speed, number of upgrades left,
					//number of each type of upgrade already applied for each Ship 
					//stored in fleet
					out.println(eachShip);
					out.println(tempShip.getName());
					out.println(tempShip.getAttackRange());
					out.println(tempShip.getTravelRange());
					out.println(tempShip.getFiringSpeed());
					out.println(tempShip.getUpgradesLeft());
					out.println(tempShip.getAR_Upgrades());
					out.println(tempShip.getTR_Upgrade());
					out.println(tempShip.getFS_Upgrade());
				}
			}
			
			//close PrintWriter and flush stream
			out.close();
			
		} catch(IOException e){
			System.out.println("File error");
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
			hangar.deleteShip(id1);
			hangar.addShip(id1, hangar.getShips[id2]);
			//set the second Ship to be equal to the first Ship, which is stored in the temporary Ship object
         //(set the Ship contained by the slot containing the second Ship to the Ship stored in the temporary Ship object)
			hangar.deleteShip(id2);
			hangar.addShip(id2, temp);
   		//return true to signify that the swap was successful      
			return true;
      }
		
		//if the first Ship is from hangar and the second Ship is from fleet
      if(hold1 == HANGAR && hold2 == FLEET){
			
			//store the first Ship in a temporary Ship object
         Ship temp = hangar.getShip(id1);
			//set the first Ship to be equal to the second Ship
			hangar.deleteShip(id1);
         hangar.addShip(id1, fleet.getShips[id2]);
			//set the second Ship to the first Ship, which is stored in the temporary Ship variable
         fleet.deleteShip(id2);
			fleet.addShip(id2, temp);
			//return true to signify that the swap was successful
         return true;
      }
		
		//if the first Ship if from fleet and the second Ship is from hangar
      if(hold1 == FLEET && hold2 == HANGAR){
		
			//store the first Ship in a temporary Ship object
         Ship temp = fleet.getShip(id1);
			//set the first Ship to be equal to the second Ship
			fleet.deleteShip(id1);
         fleet.addShip(id1, hangar.getShips[id2]);
			//set the second Ship to be equal to the first Ship, which is stored in the temporary variable
   		hangar.deleteShip(id2);      
			hangar.addShip(id2, temp);
			//return true to signify that the swap was successful
         return true;
      }
		
		//if the first Ship if from fleet and the second Ship is from fleet
      if(hold1 == FLEET && hold2 == FLEET){
         
			//store the first Ship in a temporary Ship object
			Ship temp = fleet.getShip(id1);
			//set the first Ship to be equal to the second Ship
   		fleet.deleteShip(id1);      
			fleet.addShip(id1, fleet.getShips[id2]);
			//set the second Ship to be equal to the first Ship, which is stored in the temporary Ship variable
   		fleet.deleteShip(id2);      
			fleet.addShip(id2, temp);
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