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
   private boolean movedAlready; 
   private int timesAttacked;
   public static final int BASIC_COST = 500;
   private int value;
      
   public void loadNext(BufferedReader f)  // Loading the ship information from txt file
   {
      try
      {
         name = f.readLine();
	 imageFile = f.readLine();
         attackRange = Integer.parseInt(f.readLine());
         travelRange = Integer.parseInt(f.readLine());
         firingSpeed = Integer.parseInt(f.readLine());
         upgradesLeft = Integer.parseInt(f.readLine());
         
      } 
      catch (IOException e)
      {
         System.out.println("File error on Ship level");
      }
   }
   
   //Constructor of Ship class
   public Ship(String name, String imageFile, int attackRange, int travelRange , int firingSpeed ,int upgradesLeft , int value, boolean ownedByPlayer)
   {
      this.name = name;
      this.imageFile = imageFile;
      this.attackRange = attackRange;
      this.travelRange = travelRange;
      this.firingSpeed = firingSpeed;
      this.upgradesLeft = upgradesLeft;
      this.value = value;
      this.ownedByPlayer = ownedByPlayer;
   }
   	
   public Ship(String name)  //Constructor of Ship class
   {
      this.name = name; 
   }
   
   public Ship()  //Constructor of Ship class
   {
      this.name = "";
      this.imageFile = null;
      this.attackRange = 0;
      this.travelRange = 0;
      this.firingSpeed = 0;
      this.upgradesLeft = 10;
      this.ownedByPlayer = false;
   }
      
   public void upgrade(int num)  //Upgrade the ship by the given indicate number
   {
      if(num == AR_Upgrade)
      {
         attackRange++;  
      }
      else if(num == TR_Upgrade)
      {
         travelRange++;
      }
      else
      {
         firingSpeed++;
      }
   }

   public int calcUpgradeCost(int upgrade)  //Return the cost after upgrade the choosen ship by given the ship index and the wanted upgrade type
   {
      
      if(upgrade == AR_Upgrade)
      {
	return (attackRange + 1) * 100;		
      } 
      else if (upgrade == TR_Upgrade)
      {
      	return (travelRange + 1) * 100;		
      } 
      else if (upgrade == FS_Upgrade)
      {	
      	return (firingSpeed + 1) * 100;
      }
      return -1;
   }
   
   public int getSellPrice()  //Return the sell price of the specific ship
   {
      return value /2;
   }
   
   public String getPrintString()  //Return the string which contains the Serial number of the ship information
   {
      return "\n" + name + "\n" + attackRange + "\n" + travelRange + "\n" + firingSpeed + "\n" + upgradesLeft;
   }

   public boolean isUpgradable()   //Indicate if the ship can be upgrade or not
   {
      return upgradesLeft > 0;
   }

   public int getScoreTotal()  //Indicate the score earned by the ship
   {
      return attackRange + travelRange + firingSpeed;
   }
   
   public String toString()  //Return the string contains the ship information
   {
     return "Name: "+name+"/nAttackRange: "+attackRange+ "/nTravelRange: "+travelRange+"/nFiringSpeed: "+firingSpeed+"/nUpgradesLeft: "+upgradesLeft+"/nOwnedByPlayer: "+ownedByPlayer+"/nMovedAlready: "+movedAlready+"/nTimesAttacked: "+timesAttacked+"/nValue: "+value;
   }
   
   public void setName(String name)  //Mutator method for the name of this Ship
   {
      this.name = name;
   }
   
   public String getName()  //Accessor method for the name of this Ship
   {
      return this.name;
   }
   
   public void setAttackRange(int attackRange)  //Mutator method for the attackRange of this Ship
   {
      this.attackRange = attackRange;
   }
   
   public int getAttackRange()  //Accessor method for the attackRange of this Ship
   {
      return this.attackRange;
   }  
   
   public void setTravelRange(int travelRange)  //Mutator method for the travelRange of this Ship
   {
      this.travelRange = travelRange;
   }
   
   public int getTravelRange()  //Accessor method for the travelRange of this Ship
   {
      return this.travelRange;
   } 
   
   public void setFiringSpeed(int firingSpeed)  //Mutator method for the firingSpeed of this Ship
   {
      this.firingSpeed = firingSpeed;
   }
   
   public int getFiringSpeed()  //Accessor method for the firingSpeed of this Ship
   {
      return this.firingSpeed;
   } 
   
   public void setUpgradesLeft(int upgradesLeft)  //Mutator method for the upgradesLeft of this Ship
   {
      this.upgradesLeft = upgradesLeft;
   }
   
   public int getUpgradesLeft()  //Accessor method for the upgradesLeft of this Ship
   {
      return this.upgradesLeft;
   } 
   
   public void setImageFile(String s)  //Mutator method for the imageFile of this Ship
   {
   	imageFile = s;
   }   
   
   public String getImageFile()  //Accessor method for the imageFile of this Ship
   {  
   	return imageFile;
   }
   
   public void setOwnedByPlayer(boolean ownedByPlayer)  //Mutator method for the ownedByPlayer of this Ship
   {
      this.ownedByPlayer = ownedByPlayer;
   }
   
   public boolean getOwnedByPlayer()  //Accessor method for the ownedByPlayer of this Ship
   {
      return this.ownedByPlayer;
   } 
   
   public void setMovedAlready(boolean movedAlready)  //Mutator method for the movedAlready of this Ship
   {
      this.movedAlready = movedAlready;
   }
   
   public boolean getMovedAlready()  //Accessor method for the movedAlready of this Ship
   {
      return this.movedAlready;
   } 
   
   public void setTimesAttacked(int timesAttacked)  //Mutator method for the timesAttacked of this Ship
   {
      this.timesAttacked = timesAttacked;
   }
   
   public int getTimesAttacked()  //Accessor method for the timesAttacked of this Ship
   {
      return this.timesAttacked;
   } 
   
   public void setValue(int value)  //Mutator method for the value of this Ship
   {
      this.value = value;
   }
   
   public int getValue()  //Accessor method for the value of this Ship
   {
      return this.value;
   }  
   
}
