public class Fleet{
	final	private int MAX_SHIP = 10;
	private Ship[] ships = new Ship [MAX_SHIP];
	
	public boolean addShip(int index,Ship newship){
		if(!ships[index].equals(null)){
			return false;
		}else{
			ships[index] = newship;
			return true;
		}
	}
	
	public boolean deleteShip(int index){
		if(ships[index].equals(null)){
			return false;
		}else{
			ships[index] = null;
			return true;
		}
	}
	
	public boolean checkEmpty(){
		boolean toReturn = true;
		for(int i = 0; i <MAX_SHIP;i++){
			if(!ships[i].equals(null)){
				toReturn = false;
			}
		}
		return toReturn;
	}
	public Ship getShip(int index){
		return ships[index];
	}
}