/*
Note to self:

   -class headers indicating:
o class name
o author
o date

o school
o purpose
   -method headers including:
o description of each parameters and return values
o purpose
   -sufficient and appropriate comments describing
o code that is difficult to understand
o constant, type and variable declarations
*/

/*
Class Name: Spot
Author: David Qian 
Date: January 7, 2018
School: A.Y.Jackson S.S
Purpose: Retain the information regarding every square / spot in the map
*/

    public class Spot{
   
   // the thing or entity that can be held withing the spot
      Entity thing;
   
   // empty constructor
       public Spot(){
      }
   
   //accessors and mutators
       public void setWall(){
         this.thing = new Wall();
      }
   
       public void setEmpty(){
         this.thing = null;
      }
   
       public void setShip(Ship ship){
         this.thing = ship;
      }
   
       public boolean isEmpty(){
         return thing == null;
      }
   
   // all walls are the same, so we only need to check if it's a wall or not
       public boolean isWall(){
         return thing.isWall();
      }
   
       public boolean isShip(){
         return thing.isShip();
      }
   
   // isShip will be used with getShip to ensure this.thing is a ship
       public Ship getShip(){
         return (Ship)this.thing;
      }   
   }