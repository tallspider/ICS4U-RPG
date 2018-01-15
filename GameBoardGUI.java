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
   import java.awt.event.*;
   import java.io.*;
   
    public class GameBoardGUI implements MouseListener, ActionListener{
   
   // the length and width for each spot or icon (ship)
      private int ICON_LENGTH = 32;
      private int ICON_WIDTH = 32;
   
   // total area for the playarea
      private int PLAY_AREA_LENGTH = ICON_LENGTH * Map.LENGTH_OF_MAP;
      private int PLAY_AREA_WIDTH = ICON_LENGTH * Map.WIDTH_OF_MAP;
   
   //total area for the info area
      private int INFO_AREA_LENGTH = 100;
      private int INFO_AREA_WIDTH = PLAY_AREA_WIDTH;
   
   //total area for the end turn button
      private int END_BUTTON_AREA_LENGTH = 30;
      private int END_BUTTON_AREA_WIDTH = PLAY_AREA_WIDTH;
   
   // total area for the mainframe
      private int FRAME_LENGTH = PLAY_AREA_LENGTH + INFO_AREA_LENGTH + END_BUTTON_AREA_LENGTH;
      private int FRAME_WIDTH = PLAY_AREA_WIDTH;
   
   
   
   // to output to gameboard
      public PipedOutputStream output;
   
      private JFrame mainFrame;
      private JPanel playArea;
      private JLabel[][] slots;
      private JPanel infoArea;
      private JPanel endButtonArea;
      private Button endButton;
   
   
   
       public GameBoardGUI(){
      
         createMainFrame();
         output = new PipedOutputStream();
      }
   
   //creates the main frame that cointains both the play area and info area
       private void createMainFrame(){
      
         JFrame frame = new JFrame();
      
         frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_LENGTH));
         frame.setLayout(new BoxLayout (frame, BoxLayout.Y_AXIS)); 
         initSlots();
         createPlayArea();
         createInfoArea();
         frame.add(playArea);
         frame.add(infoArea);
         frame.add(endButtonArea);
      
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
   
       private void createEndButtonArea(){
         JPanel panel = new JPanel();
         panel.setPreferredSize(new Dimension(END_BUTTON_AREA_WIDTH , END_BUTTON_AREA_LENGTH));
         panel.setBackground(Color.lightGray);
         panel.setLayout (new BoxLayout (panel, BoxLayout.Y_AXIS));
         endButton = new Button("End Turn");
         endButton.addActionListener(this);
         endButtonArea = panel;
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
               label.addMouseListener(this);
               slots[x][y] = label; 
            }
         }
      
      }
   
       public void updatePlayArea(Map map){
      
      //test location used to check if wall or ship & act accordingly
         Location testLocation;
      
      // go through the entire given map and updates the play area to reflect the map
         for (int x = 0 ; x < Map.WIDTH_OF_MAP ; x++){
            for (int y = 0 ; y < Map.LENGTH_OF_MAP ; y++){
            
               slots[x][y].setOpaque(false);
            
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
   
       public void displayPossibleMove(Map map, boolean[][] validMap){
      
         Location testLocation;
      
         for (int x = 0 ; x < Map.WIDTH_OF_MAP ; x++){
            for (int y = 0 ; y < Map.LENGTH_OF_MAP ; y++){
            
            // makes a background for the spots that are valid
               if (validMap[x][y]){
                  slots[x][y].setOpaque(true);
                  slots[x][y].setBackground(Color.blue);
               }
            
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
   
       public void displayPossibleAttack(Map map, boolean[][] validMap){
         
         Location testLocation;
      
         for (int x = 0 ; x < Map.WIDTH_OF_MAP ; x++){
            for (int y = 0 ; y < Map.LENGTH_OF_MAP ; y++){
            
            // makes a background for the spots that are valid
               if (validMap[x][y]){
                  slots[x][y].setOpaque(true);
                  slots[x][y].setBackground(Color.red);
               }
            
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
   
   // updates the info area, gives revelent info on what user clicked
       public void updateInfoArea(Map map, Location location){
      
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
   
   // gets where the player clicked
       public Location getLocation(JLabel label){
      
         for (int x = 0 ; x < Map.WIDTH_OF_MAP ; x++){
            for (int y = 0 ; y < Map.LENGTH_OF_MAP ; y++){
               if (slots[x][y] == label){
                  return new Location(x,y);
               }
            
            }
         }
         return null;
      }
   
   // asks user for what action he/she wants to do for the ship
       public int getAction(){
       
         String[] options = new String[] {"Cancel", "Attack", "Move"};
       
         return JOptionPane.showOptionDialog(null, "Please select action for the ship", "Ship Action", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[2]);
         
      }
   
       public void displayVictory(int reward){
         JOptionPane.showMessageDialog(null, "You have won! Victory!", "Victory", JOptionPane.PLAIN_MESSAGE);
         JOptionPane.showMessageDialog(null, "You are rewarded " + reward + " coins", "Victory", JOptionPane.PLAIN_MESSAGE);
      }
   
       public void displayDefeat(int reward){
         JOptionPane.showMessageDialog(null, "You have lost! Defeat!", "Defeat", JOptionPane.PLAIN_MESSAGE);
         JOptionPane.showMessageDialog(null, "You are rewarded " + reward + " coins", "Defeat", JOptionPane.PLAIN_MESSAGE);
      
      }
   
    //gets where the mouse clicked and outputs it to stream
       public void mouseClicked(MouseEvent e) {  
      
         JLabel label = (JLabel) e.getComponent ();
      
         Location location = getLocation(label);
         try{
            output.write(location.getX());
            output.write(location.getY());
         }
             catch (IOException iox){
            
            }
      }
   
       public void mousePressed(MouseEvent e){
      }
       public void mouseEntered(MouseEvent e){
      }
       public void mouseExited(MouseEvent e){
      }
       public void mouseReleased(MouseEvent e){
      }
   
   // action event for button(s)
       public void actionPerformed(ActionEvent e) {
      
         Button button = (Button)e.getSource();
      
         if (button == endButton){
            try{
               output.write(-1);
               output.write(-1);
            }
                catch(IOException iox){
               }
         }
      }
   
   }