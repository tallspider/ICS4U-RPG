//Class Name: Hanger
//Author: Hans Long
//Date: January 11, 2018
//Purpose: Used to keep track the ships that in the battle part.

import java.io.*;
import java.util.*;
public class Hangar
{
  
   private Ship[] ships = new Ship[MAX_SHIPS];
   private int shipnum;
   public static final int MAX_SHIPS = 10;
   public static final int MIN_SHIPS = 0;
 
   public Hangar(Ship[] ships)  //Constructor of Hangar class
   {
      this.ships = ships;
      this.shipnum = ships.length;
   }
   public Hangar()  //Constructor of Hangar class
   {
      this.ships = new Ship[MAX_SHIPS];
      this.shipnum = 0;
   }
   
   public void loadNext(BufferedReader f)  // Loading the ship information from txt file
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
   
   public void setShips(Ship[] ships)  //Mutator method for the ship list of this Hangar
      this.ships = ships;
   }
   
   public Ship[] getShips()  //Accessor method for the ship list of this Hangar
   {
      return this.ships;
   }
   
   public void setShipnum(int num)   //Mutator method for the num of ship in the ship list of this Hangar
   {
      this.shipnum = num;
   }
   
   public int getShipnum()  //Accessor method for the num of ship in the ship list of this Hangar
   {
      return this.shipnum;
   }
   
   public boolean deleteShip(int index)  // Delete the chosen ship by given the index of the ship in the list 
   {
      if (index>=MIN_SHIPS && index<=(ships.length-1))
      {
         ships[index]= null;
         for (int i = index ; i<shipnum-2;i++)
         {
            ships[i]= ships[i+1];
         }
         ships[shipnum-1]=null;
	 shipnum--;
         return true;
      }
      else
      {
         return false;
      }
   }
   public boolean addShip(int index ,Ship ship) // Add the another ship by given the wanted index of the ship in the list 
   {
      Ship temp;
      if(index>=MIN_SHIPS && index<(shipnum-1))
      {
         temp = ships[index];
         ships[index] = ship;
         for (int i = shipnum; i>index+1; i--)
         {
            ships[i] = ships[i-1];
         }
         ships[index+1] = temp;
	 shipnum++;
         return true;
      }
      else if (index == shipnum-1)
      {
         temp = ships[index];
         ships[index] = ship;
         ships[shipnum+1] = temp;
	 shipnum++;
         return true;
      }
      else
      {
         return false;
      }
   }

   public boolean addNewShip(int index, String imgf)  // Add one new ship by given the wanted index and imageFile of the ship in the list  
   {
   	return addShip(index, new Ship("", imgf, Ship.BASIC_STAT, Ship.BASIC_STAT, Ship.BASIC_STAT, Ship.INIT_UPGRADES, Ship.BASIC_COST, true));
   }
  
   public void upgrade(int id, int upgrade)  //Upgrade the ship in the Hangar
   {
       ships[id].upgrade(upgrade);
   }
  
  public String getPrintString()  //Return the string which contains the Serial number of the ship
  {
      String ret = "";
      for(int at = 0; at < ships.length; at++)
      {
         if(ships[at] != null)
         {
            ret += at + "\n" + ships[at].getPrintString();
         }
      }
      return ret;
  }
   
   public void upgradeShip(int index, int index2)  // Upgrade the choosen ship by given the ship index and the wanted upgrade type
   {
     if(ships[index] != null)
     {
         ships[index].upgrade(index2);
     }
   }

   public int getShipUpgradeCost(int id, int upgrade)  //Return the cost after upgrade the choosen ship by given the ship index and the wanted upgrade type
   {
      if(ships[id] == null) 
      {
        return -1;
      }
      return ships[id].calcUpgradeCost(upgrade);
   }
  
   public int getShipSellPrice(int id)   //Return the sell price of the specific ship
   {  
   	return ships[id].getSellPrice();
   }
	
   public boolean getShipUpgradable(int id)  //Indicate if the ship can be upgrade or not
   {
   	return ships[id].isUpgradable();
   }
	
   public int getScoreTotal()  //Indicate the total score earned by the ships
   {
      int ret = 0;
      for(int at = 0; at < ships.length; at++)
      {
         if(ships[at] != null)
         {
            ret += ships[at].getScoreTotal();
         }
      }
      return ret;
   }
	
   public Ship getShip(int id)   //Accessor method for the ship list of this Hangar
   {
   	return ships[id];
   }

   public void setShip(int id, Ship ship)    //Mutator method for the ship list of this Hangar
   {
   	ships[id] = ship;
   }

}
