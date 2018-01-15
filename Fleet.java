//Class name: Player
//Author: 
//Date: Jan. 8, 2018
//School: A.Y.Jackson S.S.
//Purpose: To represent the player's actions of the combat fleet in the game


import java.io.*;
public class Fleet
{
	final static public int MAX_SHIPS = 5;
	private Ship[] ships = new Ship [MAX_SHIPS];
	
	public Fleet(Ship[] ships)  //Constructor of Fleet class锛宼akes in the ship array as the field  of the Fleet
   //Given the ship array
   {
      this.ships = ships;
      //this.shipnum = ships.length;
   }
	
   public Fleet()  //Constructor of Hangar class
   {
      this.ships = new Ship[MAX_SHIPS];
   }
	
	public boolean addShip(int index,Ship newship)
	{
	/*add a ship to the array  with the given index
	If ships[index] already has a object, return false. Otherwise return true.*/
		if(!ships[index].equals(null))
		{
			return false;
		}
		else
		{
			ships[index] = newship;
			return true;
		}
	}
	
	public int getShipNum()  //Accessor method for the num of ship in the ship list of this Fleet
	{
	      int sum =0;
	      for(int i = 0; i <MAX_SHIPS;i++)
	      {
		 if(!ships[i].equals(null))
		 {
		    sum++;
		 }      
	      }
	      return sum;   
	   }
	
	public boolean deleteShip(int index)  // Delete the chosen ship by given the index of the ship in the list 
        //Given the index of the ship which is an int
	//If the slot has no object, retutn false. Otherwise, return true
	{
		if(ships[index] == null)
		{
			return false;
		}
		else
		{
			ships[index] = null;
			return true;
		}
	}
	
	public void loadNext(BufferedReader f)  // Loading the ship information from txt file
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
	
	public boolean checkEmpty()  //Return true if the array is empty. Otherwise return false.
	{
		boolean toReturn = true;
		for(int i = 0; i <MAX_SHIPS;i++)
		{
			if(!ships[i].equals(null))
			{
				toReturn = false;
			}
		}
		return toReturn;
	}
	public Ship getShip(int index)  //Return the ship object in the array of the given index.
	//Given the index of the ship which is an int
	{
		return ships[index];
	}
	
	public Ship[] getShips()  //Return the array of the ships.
	{
		return ships;
	}	
	
	public boolean isEmpty(int index)   //Indicate if the ship by the given index is Empty
	//Given the index of the ship which is an int
	{
		return ships[index] == null;
	}
	
	public void emptyFleet()   //Delete all the object in the fleet array.
	{
		for(int i = 0; i <MAX_SHIPS;i++){
			ships[i] = null;
		}
	}
	
	public String getPrintString()  //Return the string which contains the Serial number of the ship information
        {
           String ret = "";
           for(int at = 0; at < ships.length; at++)
           {
              ret += at + "\n" + ships[at].getPrintString();
           }
           return ret;
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
	
	public void setShip(int id, Ship ship)   //Mutator method for the num of ship in the ship list of this Fleet
	//Given the index of the ship which is an int and a ship object
	{
   		ships[id] = ship;
   	}
}
