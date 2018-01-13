/*
Class Name: Hanger
Author: Hans Long
Date: January 11, 2018
Purpose: Used to keep track the ships that in the battle part.
*/

import java.io.*;
import java.util.*;
public class Hangar
{
  
   private Ship[] ships = new Ship[MAX_SHIPS];
   private int shipnum;
   public static final int MAX_SHIPS = 10;
   public static final int MIN_SHIPS = 0;
 
   public Hangar(Ship[] ships)
   {
      this.ships = ships;
      this.shipnum = ships.length;
   }
   public Hangar()
   {
      this.ships = new Ship[MAX_SHIPS];
      this.shipnum = 0;
   }
  
   public void loadNext(BufferedReader f)
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
   {
      this.ships = ships;
   }
   
   public Ship[] getShips()
   {
      return this.ships;
   }
   
   public void setShipnum(int num)
   {
      this.shipnum = num;
   }
   
   public int getShipnum()
   {
      return this.shipnum;
   }
   
   public boolean deleteShip(int index)
   {
      if (index>=MIN_SHIPS && index<=(ships.length-1))
      {
         ships[index]= null;
         for (int i = index ; i<shipnum-2;i++)
         {
            ships[i]= ships[i+1];
         }
         ships[shipnum-1]=null;
         return true;
      }
      else
      {
         return false;
      }
   }
   public boolean addShip(int index ,Ship ship)
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
         return true;
      }
      else if (index == shipnum-1)
      {
         temp = ships[index];
         ships[index] = ship;
         ships[shipnum+1] = temp;
         return true;
      }
      else
      {
         return false;
      }
   }

   public boolean addNewShip(int index){
   	return addShip(index, new Ship("", Ship.BASIC_STAT, Ship.BASIC_STAT, Ship.BASIC_STAT, Ship.INIT_UPGRADES, Ship.BASIC_VALUE, true));
   }
  
   public void upgrade(int id, int upgrade)
   {
       ships[id].upgrade(upgrade);
   }
  
  public String getPrintString()
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
   
   public void upgradeShip(int index, int index2)
   {
     if(ships[index] != null)
     {
         ships[index].upgrade(index2);
     }
   }

   public int getShipUpgradeCost(int id, int upgrade)
   {
      if(ships[id] == null) 
      {
        return -1;
      }
      return ships[id].calcUpgradeCost(upgrade);
   }
  
   public int getShipSellPrice(int id){
   	return ships[id].getValue()/2;
   }
	
   public boolean getShipUpgradable(int id){
   	return ships[id].isUpgradable();
   }
	
   public int getScoreTotal()
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

}
