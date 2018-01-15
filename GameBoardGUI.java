/*
Class Name: GameBoardGUI
Author: David Qian 
Date: January 13, 2018
School: A.Y.Jackson S.S
Purpose: input / output GUI for gameboard (combat)
*/

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class GameBoardGUI{

// the length and width for each spot or icon (ship)
   private int ICON_LENGTH = 32;
   private int ICON_WIDTH = 32;
   
// total area for the playarea
   private int PLAY_AREA_LENGTH = ICON_LENGTH + Map.LENGTH_OF_MAP;
   private int PLAY_AREA_WIDTH = ICON_LENGTH + Map.WIDTH_OF_MAP;
   
//total area for the info area
   private int INFO_AREA_LENGTH = 100;
   private int INFO_AREA_WIDTH = PLAY_AREA_WIDTH;
   
// total area for the mainframe
   private int FRAME_LENGTH = PLAY_AREA_LENGTH + INFO_AREA_LENGTH;
   private int FRAME_WIDTH = PLAY_AREA_WIDTH;
   
   
   private JFrame mainFrame;
   private JPanel playArea;
   private JLabel[][] slots;
   private JPanel infoArea;
   
   
   
   public GameBoardGUI(){
   
      createMainFrame();
   }
   
   //creates the main frame that cointains both the play area and info area
   public void createMainFrame(){
   
      JFrame frame = new JFrame();
   
      frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_LENGTH));
      frame.setLayout(new BoxLayout (frame, BoxLayout.Y_AXIS)); 
      createPlayArea();
      createInfoArea();
      frame.add(playArea);
      frame.add(infoArea);
   
      mainFrame = frame;
   }
   
   // generates the playarea
   private void createPlayArea(){
   
      JPanel panel = new JPanel(); 
      panel.setPreferredSize(new Dimension(PLAY_AREA_WIDTH, PLAY_AREA_LENGTH));
      panel.setBackground(new Color(0,0,0));
      panel.setLayout(new GridLayout(Map.LENGTH_OF_MAP, Map.WIDTH_OF_MAP));
   
      slots = new JLabel[Map.WIDTH_OF_MAP][Map.LENGTH_OF_MAP];
   
      for (int y = Map.LENGTH_OF_MAP - 1; y >= 0 ; y++) {
         for (int x = 0; x < Map.WIDTH_OF_MAP; x++) {
            panel.add(slots[x][y]);
         }
      }
   
      playArea = panel;
   }
   
   // generates the infoarea
   private void createInfoArea(){
      JPanel panel = new JPanel();
      panel.setPreferredSize(new Dimension(INFO_AREA_WIDTH, INFO_AREA_LENGTH));
      panel.setBackground(Color.lightGray);
      panel.setLayout (new BoxLayout (panel, BoxLayout.Y_AXIS));  
      infoArea = panel;
   }
   
   // initalizes the slots
   private void initSlots(){
   
      slots = new JLabel[Map.WIDTH_OF_MAP][Map.LENGTH_OF_MAP];
   
   // loops throught each slot and makes a new empty jpanel
   
      JLabel label;
      
      for (int x = 0 ; x < Map.WIDTH_OF_MAP ; x++){
         for (int y = 0 ; y < Map.LENGTH_OF_MAP ; y++){
            label = new JLabel();
            label.setPreferredSize(new Dimension(ICON_WIDTH, ICON_LENGTH));
            label.setHorizontalAlignment (SwingConstants.CENTER);
            label.setBorder (new LineBorder (Color.white));
            slots[x][y] = label; 
         }
      }
   
   }
   
   private void updatePlayArea(Map map){
   
   //test location used to check if wall or ship & act accordingly
      Location testLocation;
   
   // go through the entire given map and updates the play area to reflect the map
      for (int x = 0 ; x < Map.WIDTH_OF_MAP ; x++){
         for (int y = 0 ; y < Map.LENGTH_OF_MAP ; y++){
         
            testLocation = new Location(x,y);
            
            if (map.isWall(testLocation)){
               slots[x][y].setIcon(new ImageIcon(Wall.getImageFile()));
            }
            else if (map.isEmpty(testLocation)){
               slots[x][y].setIcon(null);
            }
            else if (map.isShip(testLocation)){
               slots[x][y].setIcon(new ImageIcon(map.getShip(testLocation).getImageFile()));
            }
         }
      }
   
   }
   
   private void updateInfoArea(Map map, Location location){
   
      infoArea.removeAll();
      
      if (map.isEmpty(location)){
         infoArea.add(new JLabel("This is a Empty Spot"));
      }
      else if (map.isWall(location)){
         infoArea.add(new JLabel("This is a Wall, can't move through it"));
      
      }
      else if (map.isShip(location)){
         Ship ship = map.getShip(location);
         infoArea.add(new JLabel("Ship Name" + ship.getName()));
         infoArea.add(new JLabel("Attack Range" + ship.getAttackRange()));
         infoArea.add(new JLabel("Travel Range" + ship.getTravelRange()));
         infoArea.add(new JLabel("Firing Speed" + ship.getFiringSpeed()));
         infoArea.add(new JLabel("Owned by Player" + ship.getOwnedByPlayer()));
      }
   }
}