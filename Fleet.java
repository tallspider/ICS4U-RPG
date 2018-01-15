//Class name: Player
//Author: 
//Date: Jan. 8, 2018
//School: A.Y.Jackson S.S.
//Purpose: To represent the player's actions of the combat fleet in the game


import java.io.*;
public class Fleet{
	final static public int MAX_SHIPS = 10;
	private Ship[] ships = new Ship [MAX_SHIPS];
	
	public boolean addShip(int index,Ship newship){
	/*add a ship to the array  with the given index
	If ships[index] already has a object, return false. Otherwise return true.*/
		if(!ships[index].equals(null)){
			return false;
		}else{
			ships[index] = newship;
			return true;
		}
	}
	
	public int getShipNum(){
	      int sum =0;
	      for(int i = 0; i <MAX_SHIPS;i++){
		 if(!ships[i].equals(null)){
		    sum++;
		 }      
	      }
	      return sum;   
	   }
	
	public boolean deleteShip(int index)
	{
	/*Delete a ship in the given index
	If the slot has no object, retutn false. Otherwise, return true*/
		if(ships[index].equals(null))
		{
			return false;
		}
		else
		{
			ships[index] = null;
			return true;
		}
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
	
	public boolean checkEmpty()
	{
	//Return true if the array is empty. Otherwise return false.
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
	public Ship getShip(int index)
	{
	//Return the ship object in the array of the given index.
		return ships[index];
	}
	
	public Ship[] getShips()
	{
	//Return the array of the ships.
		return ships;
	}	
	
	public boolean isEmpty(int index){
		return ships[index].equals(null);
	}
	
	public void emptyFleet(){
	//Delete all the object in the fleet array.
		for(int i = 0; i <MAX_SHIPS;i++){
			ships[i] = null;
		}
	}
	
	public String getPrintString()
        {
           String ret = "";
           for(int at = 0; at < ships.length; at++)
           {
              ret += at + "\n" + ships[at].getPrintString();
           }
           return ret;
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
	
	public void setShip(int id, Ship ship){
   		ships[id] = ship;
   	}
}
