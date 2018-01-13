//GUI for Hangar class

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HangarFrame extends JFrame{
   
   private Hangar hangar;
   private ShipSideBar shipSideBar;
   private HangarInfoPanel hangarInfoPanel;
   public static final int WINDOW_HEIGHT = MainScene.WINDOW_WIDTH;
   public static final int WINDOW_LENGTH = MainScene.WINDOW_LENGTH;
   public static final int SHIP_SIDEBAR_LENGTH = WINDOW_LENGTH / 5;
   public static final int PAGE_SPLIT_HEIGHT = WINDOW_HEIGHT / 2;
   
   public HangarFrame(){
      init();
   }
   
   public void init(){
      setSize(WINDOW_LENGTH, WINDOW_HEIGHT);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(new BorderLayout(0, 0));
      
      initShipSideBar();
      initHangarInfoPanel();
      
      add(BorderLayout.LINE_START, shipSideBar);
      add(BorderLayout.LINE_END, hangarInfoPanel);
      
      setVisible(true);
   }
   
   private void initShipSideBar(){
      shipSideBar = new ShipSideBar(Hangar.MAX_SHIPS, SHIP_SIDEBAR_LENGTH, WINDOW_HEIGHT);
      for(int at = 0; at < Hangar.MAX_SHIPS; at++){
         shipSideBar.addButton("Ship " + (at + 1) + "");
      }
   }
   
   private void initHangarInfoPanel(){
      hangarInfoPanel = new HangarInfoPanel();
   }
   
   public static void main(String[] args){
      HangarFrame hg = new HangarFrame();
   }
}

class ShipSideBar extends JPanel{
   
   public ShipSideBar(int buttons, int length, int height){
      init(buttons, length, height);
   }
   
   public void init(int buttons, int length, int height){
      setLayout(new GridLayout(buttons, 0));
      setPreferredSize(new Dimension(length, height));
   }
   
   public void addButton(String text){
      JButton button = new JButton(text);
      button.setAlignmentX(Component.CENTER_ALIGNMENT);
      add(button);
   }
   
   
}

class HangarInfoPanel extends JPanel{
   
   public HangarInfoPanel(){
      init();
   }
   
   public void init(){
      setPreferredSize(new Dimension(800, 700));
      setLayout(new BorderLayout(0, 0));
      setOpaque(true);
      setBackground(Color.red);
   }
}