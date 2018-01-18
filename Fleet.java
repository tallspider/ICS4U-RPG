//Class name: Player
//Author: 
//Date: Jan. 8, 2018
//School: A.Y.Jackson S.S.
//Purpose: To represent the player's actions of the combat fleet in the game
// Needed no more - 18/Jan

import java.io.*;
public class Fleet
{
	public static final int MAX_SHIPS = 5;
	public static final int MIN_SHIPS = 0;
	private Ship[] ships = new Ship [MAX_SHIPS];
	private int shipnum;
	
   public Fleet(Ship[] ships) 	
   //Constructor of Fleet class takes in the ship array as the field  of the Fleet
   //Given the ship array
   {
      this.ships = ships;
      //this.shipnum = ships.length;
   }
	
   public Fleet()  
   //Constructor of Hangar class
   {
      this.ships = new Ship[MAX_SHIPS];
   }
	
	public boolean addShip(int index,Ship newship)
	{
	/*add a ship to the array  with the given index
	If ships[index] already has a object, return false. Otherwise return true.*/
		if(!(ships[index]== null))
		{
			return false;
			//return false if the ship under the specic index is not null
		}
		else
		{
			ships[index] = newship;
			shipnum++;
			//add the newship in the specific index
			return true;
		}
	}
	
	public int getShipNum()  
	//Accessor method for the num of ship in the ship list of this Fleet
	{
	      int sum =0;
	      for(int i = 0; i <MAX_SHIPS;i++)
	      {
		 if(!ships[i] == null)
		 {
		    sum++;
		    // get the number of the ships in the array
		 }      
	      } 
		
	      return shipnum;   
	   }
	
	public boolean deleteShip(int index)  
	// Delete the chosen ship by given the index of the ship in the list 
        //Given the index of the ship which is an int
	//If the slot has no object, retutn false. Otherwise, return true
	{
		if(ships[index] == null)
		{
			return false;
			//return false if the ship under the specific index is null
		}
		else
		{
			ships[index] = null;
			shipnum--;
			return true;
                        //delete the specific ship and update nuw of the ship
		}
	}
	
	public void loadNext(BufferedReader f)  
	// Loading the ship information from txt file
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
	      shipnum++;
              }
           } 
           catch (IOException e)
	   {
             System.out.println("File error on Hangar/Fleet level");
           }
        }
	
	public boolean checkEmpty()  
	//Return true if the array is empty. Otherwise return false.
	{
		boolean toReturn = true;
		for(int i = 0; i <MAX_SHIPS;i++)
		{
			if(!(ships[i] == null))
			{
				toReturn = false;
			}
		}
		return toReturn;
	}
	public Ship getShip(int index)  
	//Return the ship object in the array of the given index.
	//Given the index of the ship which is an int
	{
		return ships[index];
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
	
	public Ship[] getShips()  
	//Return the array of the ships.
	{
		return ships;
	}	
	
	public boolean isEmpty(int index)   
	//Indicate if the ship by the given index is Empty
	//Given the index of the ship which is an int
	{
		return ships[index] == null;
	}
	
	public void emptyFleet()   
	//Delete all the object in the fleet array.
	{
		for(int i = 0; i <MAX_SHIPS;i++){
			ships[i] = null;
		}
	}
	
	public String getPrintString()  
	//Return the string which contains the Serial number of the ship information
        {
           String ret = "";
           for(int at = 0; at < MAX_SHIPS; at++)
           {
              ret += at + "\n" + ships[at].getPrintString();
           }
           return ret;
        }
	
        public int getScoreTotal()  
	//Indicate the total score earned by the ships
        {
          int ret = 0;
          for(int at = 0; at < MAX_SHIPS; at++)
          {
            if(ships[at] != null)
            {
               ret += ships[at].getScoreTotal();
            }
          }
          return ret;
        }
	
	public void setShip(int id, Ship ship)   
	//Mutator method for the num of ship in the ship list of this Fleet
	//Given the index of the ship which is an int and a ship object
	{
   		ships[id] = ship;
   	}


	
}
