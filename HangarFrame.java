//GUI for Hangar class

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HangarFrame extends JFrame{
   
   Hangar hangar;
   
   public HangarFrame(){
      setSize(1000, 700);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
   }
   
   public static void main(String[] args){
      HangarFrame hg = new HangarFrame();
   }
}