//Class name: Ship 
//Author: Hans Long
//Date: Jan. 11, 2018
//School: A.Y.Jackson S.S.
//Purpose: a simplified version of the Ship class 

import java.io.*;
import java.util.*;
public class Ship extends Entity
{
   private String name;
   private int attackRange;
   private int travelRange;
   private int firingSpeed;
   private int upgradesLeft;
   private String imageFile;
   public static final int AR_Upgrade = 1;
   public static final int TR_Upgrade = 2;
   public static final int FS_Upgrade = 3;
   public static final int BASIC_STAT = 1;
   public static final int INIT_UPGRADES = 10;
   private boolean ownedByPlayer; 
   private int value;
   private boolean movedAlready; 
   private int timesAttacked;
   public static final int BASIC_COST = 500;
	public static final int UPGRADE_MULTIPLE = 50;
   public static final String BASIC_NAME = "A New Ship";
      
   public void loadNext(BufferedReader f)  
   // Loading the ship information from txt file, takes in the object of BufferedReader
   //Given the BufferedReader object
   {
      try
      {
         name = f.readLine();
	      imageFile = f.readLine();
         attackRange = Integer.parseInt(f.readLine());
         travelRange = Integer.parseInt(f.readLine());
         firingSpeed = Integer.parseInt(f.readLine());
         upgradesLeft = Integer.parseInt(f.readLine());
         ownedByPlayer = true;
			updateValue();
      } 
      catch (IOException e)
      {
         System.out.println("File error on Ship level");
      }
   }
   
   public Ship(String name, String imageFile, int attackRange, int travelRange , int firingSpeed ,int upgradesLeft , int value, boolean ownedByPlayer)
   // Constructor of ship class, take in the fields of Ship class
   {
      this.name = name;
      this.imageFile = imageFile;
      this.attackRange = attackRange;
      this.travelRange = travelRange;
      this.firingSpeed = firingSpeed;
      this.upgradesLeft = upgradesLeft;
      this.value = value;
      this.ownedByPlayer = ownedByPlayer;
		updateValue();
   }
   	
   public Ship(String name) 
   // Constructor of ship class, take in the name of Ship as a string
   // Given the name of the ship as a string
   {
      this.name = name; 
   }
   
   public Ship()   
   // Constructor of ship class
   {
      this.name = BASIC_NAME;
      this.imageFile = null;
      this.attackRange = 0;
      this.travelRange = 0;
      this.firingSpeed = 0;
      this.upgradesLeft = 10;
      this.ownedByPlayer = false;
   }
   
	public void updateValue(){
		value = (attackRange * (attackRange - 1) + travelRange * (travelRange - 1) + firingSpeed * (firingSpeed - 1) ) * UPGRADE_MULTIPLE / 2 + BASIC_COST;
	}
	
   public void upgrade(int num)  
   // Upgrade the ship by given the wanted upgrade type as a int
   // Given upgrade type as a int
   {
      if(num == AR_Upgrade)
      {
      //add the attackRange
         attackRange++;  
	 
      }
      else if(num == TR_Upgrade)
      {
      //add the travelRange
         travelRange++;
      }
      else
      {
      //add the firingSpeed
         firingSpeed++;
      }
      updateValue();
   }

   public int calcUpgradeCost(int upgrade)  
   //Return the cost after upgrade the choosen ship by given the ship index and the wanted upgrade type
   //Given upgrade type as a int
   {
      
      if(upgrade == AR_Upgrade)
      {
      //update the cost of attackRange
			return attackRange * UPGRADE_MULTIPLE;		
      } 
      else if (upgrade == TR_Upgrade)
      {
      //update the cost of travelRange
      	return travelRange * UPGRADE_MULTIPLE;		
      } 
      else if (upgrade == FS_Upgrade)
      //update the cost of firingSpeed
      {	
      	return firingSpeed * UPGRADE_MULTIPLE;
      }
      //return a negative intager if the update is not success
      return -1;
   }
   
 	public int getSellPrice()  
   //A method for the sell price of this ship
   {
      return value /2;
      //The selling price of the ship is the half of the ship
   }
   
   public String getPrintString()  
   //Return the string which contains the ship basic information
   {
      return  System.lineSeparator() + name + System.lineSeparator() + imageFile + System.lineSeparator() + attackRange + System.lineSeparator() + travelRange + System.lineSeparator() + firingSpeed + System.lineSeparator() + upgradesLeft;
   }

   public boolean isUpgradable()  
   //Indicate if the ship is upgradable
   {
      return upgradesLeft > 0;
   }

   public int getScoreTotal()  
   //Indicate the score of the ship
   {
      return attackRange + travelRange + firingSpeed;
   }
   
   public String toString()  
   //Return the string which contains the ship's all information
   {
     return "Name: "+name+"/nAttackRange: "+attackRange+ "/nTravelRange: "+travelRange+"/nFiringSpeed: "+
     firingSpeed+"/nUpgradesLeft: "+upgradesLeft+"/nOwnedByPlayer: "+ownedByPlayer+"/nMovedAlready: "+movedAlready+
     "/nTimesAttacked: "+timesAttacked+"/nValue: "+value;
   }
   
   public void setName(String name)  
   //Mutator method for the name of ship 
   //Given the wanted name of the ship as a string
   {
      this.name = name;
   }
   
   public String getName()  
   //Accessor method for the ship name of this ship
   {
      return this.name;
   }
   
   public void setAttackRange(int attackRange)  
   //Mutator method for the attackRange of ship
   //Given the wanted attackRange of the ship as an int
   {
      this.attackRange = attackRange;
   }
   
   public int getAttackRange()  
   //Accessor method for the ship attackRange of this ship
   {
      return this.attackRange;
   }  
   
   public void setTravelRange(int travelRange)  
   //Mutator method for the travelRange of ship
   //Given the wanted travelRange of the ship as an int
   {
      this.travelRange = travelRange;
   }
   
   public int getTravelRange()  
   //Accessor method for the ship travelRange of this ship
   {
      return this.travelRange;
   } 
   
   public void setFiringSpeed(int firingSpeed)  
   //Mutator method for the travelRange of ship
   //Given the wanted firingSpeed of the ship as an int
   {
      this.firingSpeed = firingSpeed;
   }
   
   public int getFiringSpeed()  
   //Accessor method for the ship firingSpeed of this ship
   {
      return this.firingSpeed;
   } 
   
   public void setUpgradesLeft(int upgradesLeft)  
   //Mutator method for the travelRange of ship
   //Given the wanted upgradesLeft of the ship as an int
   {
      this.upgradesLeft = upgradesLeft;
   }
   
   public int getUpgradesLeft()  
   //Accessor method for the upgradesLeft of this ship
   {
      return this.upgradesLeft;
   } 
   
   public String getImageFile()  
   //Accessor method for the ship imageFile of this ship
   {
   	return imageFile;
   }
   
   public void setImageFile(String s)  
   //Mutator method for the imageFile of ship
   //Given the wanted imageFile of the ship as a string
   {
   	imageFile = s;
   }
   
   public void setOwnedByPlayer(boolean ownedByPlayer)  
   //Mutator method for the ownedByPlayer of ship
   //Given the wanted ownedByPlayer of the ship as a boolean
   {
      this.ownedByPlayer = ownedByPlayer;
   }
   
   public boolean getOwnedByPlayer()  
   //Accessor method for the ship ownedByPlayer of this ship
   {
      return this.ownedByPlayer;
   } 
   
   public void setMovedAlready(boolean movedAlready)  
   //Mutator method for the movedAlready of ship
   //Given the wanted movedAlready of the ship as a boolean
   {
      this.movedAlready = movedAlready;
   }
   
   public boolean getMovedAlready()  
   //Accessor method for the ship movedAlready of this ship
   {
      return this.movedAlready;
   } 
   
   public void setTimesAttacked(int timesAttacked)  
   //Mutator method for the timesAttacked of ship
   //Given the wanted timesAttacked of the ship as an int
   {
      this.timesAttacked = timesAttacked;
   }
   
   public int getTimesAttacked()  
   //Accessor method for the ship timesAttacked of this ship
   {
      return this.timesAttacked;
   } 
   
   public void setValue(int value)  
   //Mutator method for the value of ship
   //Given the wanted value of the ship as an int
   {
      this.value = value;
   }
   
   public int getValue()  
   //Accessor method for the ship value of this ship
   {
      return this.value;
   }  
   
}
