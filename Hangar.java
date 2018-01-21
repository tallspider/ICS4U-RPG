//Class Name: Hanger
//Author: Hans Long
//Date: January 11, 2018
//School: A.Y.Jackson S.S.
//Purpose: Used to keep track the ships that in the battle part.

import java.io.*;
import java.util.*;
public class Hangar
{
  
   private Ship[] ships = new Ship[MAX_SHIPS];
   private int shipnum;
   public static final int MAX_SHIPS = 10;
   public static final int MIN_SHIPS = 0;
 
   public Hangar(Ship[] ships)  
   //Constructor of Hangar classÃ¯Â¼Å’takes in the ship array as the field  of the Hangar
   //Given the ship array
   {
      this.ships = ships;
      this.shipnum = ships.length;
   }
   public Hangar()  
   //Constructor of Hangar class
   {
      this.ships = new Ship[MAX_SHIPS];
      this.shipnum = 0;
   }
   
   public void loadNext(BufferedReader f)  
   // Loading the ship information from txt file, takes in the object of BufferedReader
   //Given the BufferedReader object
   {
     try
     {
        String s;
        while( (s = f.readLine())!= null && s.length() != 0)
	{
            int ID = Integer.parseInt(s);
            ships[ID] = new Ship();
            ships[ID].loadNext(f);
        }
     } 
     catch (IOException e)
     {
        System.out.println("File error on Hangar/Fleet level");
     }
   }
   
   public void setShips(Ship[] ships)  
   //Mutator method for the ship list of this Hangar
   //Given the ship array
   {
      this.ships = ships;
   }
   
   public Ship[] getShips()  
   //Accessor method for the ship list of this Hangar
   {
      return this.ships;
   }
   
   public void setShipnum(int num)   
   //Mutator method for the num of ship in the ship list of this Hangar
   //Given the num of the ship in the array which is an int
   {
      this.shipnum = num;
   }
   
   public int getShipnum()  
   //Accessor method for the num of ship in the ship list of this Hangar
   {
      return this.shipnum;
   }
   
   public boolean deleteShip(int index)  
   //Delete the chosen ship by given the index of the ship in the list 
   //Given the index of the ship which is an int
   {
      if (index>=MIN_SHIPS && index<=(ships.length-1))
      {
         ships[index]= null;
	 // set the specific ship null and adjust the num of ships in the array
	 shipnum--;
         return true;
      }
      else
      {
         return false;
      }
   }
	
   public boolean addShip(int index ,Ship ship) 
   // Add the another ship by given the wanted index of the ship in the list 
   //Given the index of the ship which is an int, and a ship object
   {
      if(index>=MIN_SHIPS && index<MAX_SHIPS && ships[index] == null)
      {
         ships[index] = ship;
	      shipnum++;
	 // add the specific ship and adjust the num of ships in the array
         return true;
      }
      else
      {
         return false;
      }
   }

   public boolean addNewShip(int index, String imgf)  
   //Add one new ship by given the wanted index and imageFile of the ship in the list  
   //Given the index of the ship which is an int and ship's image which is string
   {
   	return addShip(index, new Ship(Ship.BASIC_NAME, imgf, Ship.BASIC_STAT, Ship.BASIC_STAT, Ship.BASIC_STAT, 
				       Ship.INIT_UPGRADES, Ship.BASIC_COST, true));
   }
  
  
  public String getPrintString()  
  //Return the string which contains the Serial number of the ship information
  {
      String ret = "";
      for(int at = 0; at < ships.length; at++)
      {
         if(ships[at] != null)
         {
            ret += at + "\n" + ships[at].getPrintString();
         }
	 //print the ship indormation with the serial number
      }
      return ret;
  }
   
   public void upgradeShip(int index, int upgrade) 
   // Upgrade the choosen ship by given the ship index and the wanted upgrade type
   // Given the index of ship and the upgrade type of the ship by int 
   {
     if(ships[index] != null)
     {
         ships[index].upgrade(upgrade);
     }
   }

   public int getShipUpgradeCost(int id, int upgrade)  
   //Return the cost after upgrade the choosen ship by given the ship index and the wanted upgrade type
   //Given the index of ship and the upgrade type of the ship by int 
   {
      if(ships[id] == null) 
      {
        return -1;
	//return -1 if the given index is the correct
      }
      return ships[id].calcUpgradeCost(upgrade);
   }
  
   public int getShipSellPrice(int id)   
   //Return the sell price of the specific ship
   // Given the index of the ship which is an int
   {  
   	return ships[id].getSellPrice();
   }
	
   public boolean getShipUpgradable(int id) 
   //Indicate if the ship can be upgrade or not
   // Given the index of the ship which is an int
   {
   	return ships[id].isUpgradable();
   }
	
   public int getScoreTotal()  
   //Indicate the total score earned by the ships
   {
      int ret = 0;
      for(int at = 0; at < ships.length; at++)
      {
         if(ships[at] != null)
         {
            ret += ships[at].getScoreTotal();
            //add the scores of ecah specific ships
         }
      }
      return ret;
   }
	
   public Ship getShip(int id)   
   // Accessor method for the ship list of this Hangar
   // Given the index of the ship which is an int
   {
   	return ships[id];
   }

   public void setShip(int id, Ship ship)    
   // Mutator method for the ship list of this Hangar
   // Given the index of the ship which is an int and a ship object
   {
   	ships[id] = ship;
   }
	

}
