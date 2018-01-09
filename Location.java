/*
Class Name: Fleet
Author: Chongpu Zhao
Date: January 8, 2018
Purpose: Composition class with the Enity class. Used to keep track the location of a unity in the gameboard. 
*/
public class Location{
   private int x;
   private int y;
   
   public Location(int x, int y){
   //Constructor
      this.x = x;
      this.y = y;
   }
   
   public Location(){
   //Another construcotr
   }
   
   public static int compareLocations(Location a, Location b){
   //Return the distence bewtween two given location objects.
      return (Math.abs(a.getX() - b.getX()) + Math.abs(a.getY()-b.getY()));
   }
   public int getX(){
   //Return the x-coordinate.
      return x;
   }
   public int getY(){
   //Return the y-coordinate.
      return y;
   }
   public void setX(int x){
   //Set the x-coordinate to the given integer.
      this.x = x;
   }
   public void setY(int y){
   //Set the y-coordinate to the given integer.
      this.y = y;
   }
   
   public void SetXY(int x,int y){
   //Set the x and y to the given integers.   
      this.x = x;
      this.y = y;
   }
}
