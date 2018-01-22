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
      private int INFO_AREA_LENGTH = 120;
      private int INFO_AREA_WIDTH = PLAY_AREA_WIDTH;
   
   //total area for the end turn button
      private int END_BUTTON_AREA_LENGTH = 35;
      private int END_BUTTON_AREA_WIDTH = PLAY_AREA_WIDTH;
   
   // total area for the mainframe
      private int FRAME_LENGTH = PLAY_AREA_LENGTH + INFO_AREA_LENGTH + END_BUTTON_AREA_LENGTH + 38;
      private int FRAME_WIDTH = PLAY_AREA_WIDTH + 16;
   
   // + 38 because appearently the top frame bar is 38 pixels high also +16 because the sides are like 8 pixels each
   
   
   // to output to gameboard
      public PipedOutputStream output;
   
      public JFrame mainFrame;
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
      
         FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
         layout.setHgap(0);
         layout.setVgap(0);
      
         frame.setSize(new Dimension(FRAME_WIDTH, FRAME_LENGTH));
         frame.setLayout(layout); 
         initSlots();
         createPlayArea();
         createInfoArea();
         createEndButtonArea();
         frame.add(playArea);
         frame.add(infoArea);
         frame.add(endButtonArea);
         frame.setVisible(true);
      
         mainFrame = frame;
      }
   
   // generates the playarea
       private void createPlayArea(){
      
         JPanel panel = new JPanel(); 
         panel.setPreferredSize(new Dimension(PLAY_AREA_WIDTH, PLAY_AREA_LENGTH));
         panel.setBackground(new Color(0,0,0));
         panel.setLayout(new GridLayout(Map.LENGTH_OF_MAP, Map.WIDTH_OF_MAP));
      
         for (int y = Map.LENGTH_OF_MAP - 1; y >= 0 ; y--) {
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
         panel.setAlignmentX(Component.LEFT_ALIGNMENT);
         infoArea = panel;
      }
   
   // generates the endbutton
       private void createEndButtonArea(){
         JPanel panel = new JPanel();
         panel.setPreferredSize(new Dimension(END_BUTTON_AREA_WIDTH , END_BUTTON_AREA_LENGTH));
         panel.setBackground(Color.lightGray);
         endButton = new Button("End Turn");
         endButton.setFont(new Font("SansSerif", Font.BOLD, 18));
         endButton.addActionListener(this);
         panel.add(endButton);
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
   
   // updates the play area, displays the location from map
       public void updatePlayArea(Map map){
      
      //test location used to check if wall or ship & act accordingly
         Location testLocation;
      
      // go through the entire given map and updates the play area to reflect the map
         for (int x = 0 ; x < Map.WIDTH_OF_MAP ; x++){
            for (int y = 0 ; y < Map.LENGTH_OF_MAP ; y++){
            
            // resets the background so that the background from displayPossibleMove and displayPossibleAttack don't stay
               slots[x][y].setOpaque(false);
               slots[x][y].setBackground(Color.black);
            
               testLocation = new Location(x,y);
            
               if (map.isEmpty(testLocation)){
                  slots[x][y].setIcon(null);
               }
               else if (map.isWall(testLocation)){
                  slots[x][y].setIcon(new ImageIcon(Wall.getImageFile()));
               }
               else if (map.isShip(testLocation)){
                  slots[x][y].setIcon(new ImageIcon(map.getShip(testLocation).getImageFile()));
               }
            }
         }
         infoArea.validate();
         infoArea.repaint();
      }
   
   // shows the valid moves on map given a 2d validmap array 
       public void displayPossibleMove(Map map, boolean[][] validMap){
      
         Location testLocation;
      
         for (int x = 0 ; x < Map.WIDTH_OF_MAP ; x++){
            for (int y = 0 ; y < Map.LENGTH_OF_MAP ; y++){
            
            // makes a background for the spots that are valid
               if (validMap[x][y]){
                  slots[x][y].setOpaque(true);
                  slots[x][y].setBackground(Color.blue);
               }
            
            }
         }
      
      }
   
   // shows the valid attacks on map given a 2d validmap array
       public void displayPossibleAttack(Map map, boolean[][] validMap){
      
         Location testLocation;
      
         for (int x = 0 ; x < Map.WIDTH_OF_MAP ; x++){
            for (int y = 0 ; y < Map.LENGTH_OF_MAP ; y++){
            
            // makes a background for the spots that are valid
               if (validMap[x][y]){
                  slots[x][y].setOpaque(true);
                  slots[x][y].setBackground(Color.red);
               }
            
            }
         }
      }
   
   // updates the info area, gives revelent info on what user clicked
       public void updateInfoArea(Map map, Location location){
      
      // clear everything in the info area
         infoArea.removeAll();
      
         JLabel label = new JLabel();
         Font font = new Font("SansSerif", Font.BOLD, 18);
      
      // adds the revelent information depending on what the spot is
         if (map.isEmpty(location)){
         
            label = new JLabel("This is a Empty Spot");
            label.setFont(font);
            infoArea.add(label);   
         }
         else if (map.isWall(location)){
         
            label = new JLabel("This is a Wall, can't move through it");
            label.setFont(font);
            infoArea.add(label);
         }
         else if (map.isShip(location)){
            Ship ship = map.getShip(location);
         
            label = new JLabel("Ship Name: " + ship.getName());
            label.setFont(font);
            infoArea.add(label); 
            label = new JLabel("Attack Range: " + ship.getAttackRange() + "     Attacks Left: " + (ship.getFiringSpeed() - ship.getTimesAttacked()));
            label.setFont(font);
            infoArea.add(label); 
            label = new JLabel("Travel Range: " + ship.getTravelRange() + "     Can Move: " + !ship.getMovedAlready());
            label.setFont(font);
            infoArea.add(label); 
            label = new JLabel("Firing Speed: " + ship.getFiringSpeed());
            label.setFont(font);
            infoArea.add(label); 
            label = new JLabel("Owned by Player: " + ship.getOwnedByPlayer());
            label.setFont(font);
            infoArea.add(label); 
         }
      
         infoArea.validate();
         infoArea.repaint();
      }
   
   // gets where the player clicked given the label clicked
       public Location getLocation(JLabel label){
      
      // loop through the entire map and check if the source of the click is the same as that slot, if so return that slot's location
         for (int x = 0 ; x < Map.WIDTH_OF_MAP ; x++){
            for (int y = 0 ; y < Map.LENGTH_OF_MAP ; y++){
               if (slots[x][y] == label){
                  //System.out.println(x + " " + y);
                  return new Location(x,y);
               }
            
            }
         }
         return null;
      }
   
   // asks user for what action he/she wants to do with the ship
       public int getAction(){
      
         String[] options = new String[] {"Cancel", "Attack", "Move"};
      
         return JOptionPane.showOptionDialog(null, "Please select action for the ship", "Ship Action", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[2]);
      }
   
   // displays player victory
       public void displayVictory(int reward){
         JOptionPane.showMessageDialog(null, "You have won! Victory!", "Victory", JOptionPane.PLAIN_MESSAGE);
         JOptionPane.showMessageDialog(null, "You are rewarded " + reward + " coins", "Victory", JOptionPane.PLAIN_MESSAGE);
      }
   
   // displays player defeat
       public void displayDefeat(int reward){
         JOptionPane.showMessageDialog(null, "You have lost! Defeat!", "Defeat", JOptionPane.PLAIN_MESSAGE);
         JOptionPane.showMessageDialog(null, "You are rewarded " + reward + " coins", "Defeat", JOptionPane.PLAIN_MESSAGE);
      }
   
   // displays tie
       public void displayTie(int reward){
         JOptionPane.showMessageDialog(null, "You have defeated the enemy, but were defeated aswell (did you shoot your own ship??)", "Tie", JOptionPane.PLAIN_MESSAGE);
         JOptionPane.showMessageDialog(null, "You are rewarded " + reward + " coins", "Tie", JOptionPane.PLAIN_MESSAGE);
      }
   
       public void displayTurnStart(){
         JOptionPane.showMessageDialog(null, "Turn Start", "Turn Start", JOptionPane.PLAIN_MESSAGE);
      }
      
       public void displayInvalidMove(){
         JOptionPane.showMessageDialog(null, "Move is invalid", "Invalid Move", JOptionPane.PLAIN_MESSAGE);
      }
   	
       public void displayInvalidAttack(){
         JOptionPane.showMessageDialog(null, "Cannot attack there, can only attack ships in range", "Invalid Attack", JOptionPane.PLAIN_MESSAGE);
      }
   	
       public void displayInvalidAttackEmpty(){
         JOptionPane.showMessageDialog(null, "Cannot attack empty space", "Invalid Attack", JOptionPane.PLAIN_MESSAGE);
      }
   	
       public void displayNoMovesLeft(){
         JOptionPane.showMessageDialog(null, "That ship has already moved, it cannot move again", "No More Moves", JOptionPane.PLAIN_MESSAGE);
      }
   	
       public void displayNoAttacksLeft(){
         JOptionPane.showMessageDialog(null, "That ship has no more attacks left", "no More Attacks", JOptionPane.PLAIN_MESSAGE);
      }
      
   // closes the gui
       public void close(){
      // closes the window as if a user clicked the X (this is to trigger addReopenListener so that main scene is visible again)
         Window window = KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow();
         WindowEvent windowClosing = new WindowEvent(window, WindowEvent.WINDOW_CLOSING);
         window.dispatchEvent(windowClosing);
      }
   
   //gets where the mouse clicked and outputs it to stream
       public void mouseClicked(MouseEvent e) {  
      
      // gets the source or where the user clicked
         JLabel label = (JLabel) e.getComponent();
      
      // calls the get location, which gives the location belong to the label
         Location location = getLocation(label);
      
         try{
            output.write(location.getX());
            output.write(location.getY());
         }
             catch (IOException iox){
            }
      }
   
   // @overide
       public void mousePressed(MouseEvent e){
      }
       public void mouseEntered(MouseEvent e){
      }
       public void mouseExited(MouseEvent e){
      }
       public void mouseReleased(MouseEvent e){
      }
   
   // action event for end turn button
       public void actionPerformed(ActionEvent e) {
      
         Button button = (Button)e.getSource();
      
      // if the end button is pressed write the end location
         if (button == endButton){
            try{
               output.write(Gameboard.END_TURN_lOCATION);
               output.write(Gameboard.END_TURN_lOCATION);
            }
                catch(IOException iox){
               }
         }
      }
   
   }