/*
Class Name: Fleet
Author: Chongpu Zhao
Date: January 8, 2018
Purpose: Used to keep track the ships that in the battle part.
*/
public class Fleet{
	final static public int MAX_SHIP = 10;
	private Ship[] ships = new Ship [MAX_SHIP];
	
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
	
	public int getShipNum;
	public boolean deleteShip(int index){
	/*Delete a ship in the given index
	If the slot has no object, retutn false. Otherwise, return true*/
		if(ships[index].equals(null)){
			return false;
		}else{
			ships[index] = null;
			return true;
		}
	}
	
	public boolean checkEmpty(){
	//Return true if the array is empty. Otherwise return false.
		boolean toReturn = true;
		for(int i = 0; i <MAX_SHIP;i++){
			if(!ships[i].equals(null)){
				toReturn = false;
			}
		}
		return toReturn;
	}
	public Ship getShip(int index){
	//Return the ship object in the array of the given index.
		return ships[index];
	}
	
	public Ship[] getShips(){
	//Return the array of the ships.
		return ships;
	}	
	
	public boolean isEmpty(int index){
		return ships[index].equals(null);
	}
	
	public void emptyFleet(){
	//Delete all the object in the fleet array.
		for(int i = 0; i <MAX_SHIP;i++){
			ships[i] = null;
		}
	}
}
