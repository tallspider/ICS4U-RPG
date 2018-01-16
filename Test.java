import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Test {

   public static void main(String[]args){
   
      Scanner input = new Scanner(System.in);
   
      Map map = new Map();
   
   
      Fleet f = new Fleet();
      Player p = new Player("Fl47");
   
      for (int i = 0 ; i < 5; i++){ 
         f.addShip(i,new Ship("CARRIER", Player.AI_SHIP_IMAGE, 5, 4, 3, 0, 0 , true));
      }
   
   
      p.setFleet(f);
      p.setNumCoins(50);
      p.setScore(50);
      
      Gameboard g = new Gameboard();
      
      g.startCombat(p);
   
   
   
   }

}