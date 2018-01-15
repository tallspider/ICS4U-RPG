/*
Class Name: Wall
Author: David Qian 
Date: January 7, 2018
School: A.Y.Jackson S.S
Purpose: A wall that is a entity that is used in movement calculation (obstructs movement)
*/

public class Wall extends Entity{

   public final static String imageFile = "wall.png";

//constructor
   public Wall(){
   }
   
   public String getImageFile(){
      return imageFile;
   }
}