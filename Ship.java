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
      
   public void loadNext(BufferedReader f)
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
   	
   public Ship(String name)
   {
      this.name = name; 
   }
   
   public Ship()
   {
      this.name = "";
      this.imageFile == null;
      this.attackRange = 0;
      this.travelRange = 0;
      this.firingSpeed = 0;
      this.upgradesLeft = 10;
      this.ownedByPlayer = false;
   }
      
   public void upgrade(int num)
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

   public int calcUpgradeCost(int upgrade)
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
   
   public int getSellPrice(){
      return value /2;
   }
   
   public String getPrintString()
   {
      return "\n" + name + "\n" + attackRange + "\n" + travelRange + "\n" + firingSpeed + "\n" + upgradesLeft;
   }

   public boolean isUpgradable()
   {
      return upgradesLeft > 0;
   }

   public int getScoreTotal()
   {
      return attackRange + travelRange + firingSpeed;
   }
   
   public String toString()
   {
     return "Name: "+name+"/nAttackRange: "+attackRange+ "/nTravelRange: "+travelRange+"/nFiringSpeed: "+firingSpeed+"/nUpgradesLeft: "+upgradesLeft+"/nOwnedByPlayer: "+ownedByPlayer+"/nMovedAlready: "+movedAlready+"/nTimesAttacked: "+timesAttacked+"/nValue: "+value;
   }
   
   public void setName(String name)
   {
      this.name = name;
   }
   
   public String getName()
   {
      return this.name;
   }
   
   public void setAttackRange(int attackRange)
   {
      this.attackRange = attackRange;
   }
   
   public int getAttackRange()
   {
      return this.attackRange;
   }  
   
   public void setTravelRange(int travelRange)
   {
      this.travelRange = travelRange;
   }
   
   public int getTravelRange()
   {
      return this.travelRange;
   } 
   
   public void setFiringSpeed(int firingSpeed)
   {
      this.firingSpeed = firingSpeed;
   }
   
   public int getFiringSpeed()
   {
      return this.firingSpeed;
   } 
   
   public void setUpgradesLeft(int upgradesLeft)
   {
      this.upgradesLeft = upgradesLeft;
   }
   
   public int getUpgradesLeft()
   {
      return this.upgradesLeft;
   } 
   
   public String getImageFile(){
   	return imageFile;
   }
   
   public void setImageFile(String s){
   	imageFile = s;
   }
   
   public void setOwnedByPlayer(boolean ownedByPlayer)
   {
      this.ownedByPlayer = ownedByPlayer;
   }
   
   public boolean getOwnedByPlayer()
   {
      return this.ownedByPlayer;
   } 
   
   public void setMovedAlready(boolean movedAlready)
   {
      this.movedAlready = movedAlready;
   }
   
   public boolean getMovedAlready()
   {
      return this.movedAlready;
   } 
   
   public void setTimesAttacked(int timesAttacked)
   {
      this.timesAttacked = timesAttacked;
   }
   
   public int getTimesAttacked()
   {
      return this.timesAttacked;
   } 
   
   public void setValue(int value)
   {
      this.value = value;
   }
   
   public int getValue()
   {
      return this.value;
   }  
   
}
