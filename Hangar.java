/*
Class Name: Hanger
Author: Hans Long
Date: January 11, 2018
Purpose: Used to keep track the ships that in the battle part.
*/

import java.util.*;
public class Hangar
{
  
   private Ship[] ships = new Ship[MAX_SHIPS];
   private int shipnum;
   private static final int MAX_SHIPS = 10;
   private static final int MIN_SHIPS = 0;
 
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
   public boolean upgradeShip(int index, int index2)
   {
     
   }

}
