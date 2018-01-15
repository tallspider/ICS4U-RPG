//Class name: ShipSwapFrame
//Author: Annie Gao
//Date: Jan. 12, 2018
//School: A.Y.Jackson S.S.
//Purpose: the graphical interface that allows user to switch the locations of two 
//ships in either hangar or fleet

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ShipSwapFrame extends JFrame{
   
   private Component lastPage;
   private Player player;
   
   private Hangar hangar;
   private Fleet fleet;
   
   private Ship ship1;
   private Ship ship2;
   
   private int ship1Hold;
   private int ship2Hold;
   
   private int ship1ID;
   private int ship2ID;
   
   private HangarShipsPanel hangarShipsPanel;
   private FleetShipsPanel fleetShipsPanel;
   private InfoContainerPanel infoContainerPanel;
   
   public static final int WINDOW_HEIGHT = MainScene.WINDOW_WIDTH;
   public static final int WINDOW_LENGTH = MainScene.WINDOW_LENGTH;
   
   //constructor of this class
   //takes in the object representing the current player, and the page to which they may go back
   public ShipSwapFrame(Player p, Component c){
      player = p;
      lastPage = c;
      init();
   }
   
   //initializes the object
   //initializes each of the 3 parts: hangar ship list, fleet ship list and info panel
   public void init(){
      setPreferredSize(new Dimension(WINDOW_LENGTH, WINDOW_HEIGHT));
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(new BorderLayout(0, 0));
      
      hangar = player.getHangar();
      fleet = player.getFleet();
      ship1 = null;
      ship2 = null;
      ship1Hold = -1;
      ship2Hold = -1;
      ship1ID = -1;
      ship2ID = -1;
      
      initHangarShipsPanel();
      initFleetShipsPanel();
      initInfoContainerPanel();
      
      add(hangarShipsPanel, BorderLayout.LINE_START);
      add(fleetShipsPanel, BorderLayout.CENTER);
      add(infoContainerPanel, BorderLayout.LINE_END);
   }
   
   //creates the hangar ship list section
   private void initHangarShipsPanel(){
      hangarShipsPanel = new HangarShipsPanel(this);
   }
   
   //creates the fleet ship list section
   private void initFleetShipsPanel(){
      fleetShipsPanel = new FleetShipsPanel(this);
   }
   
   //creates the info panel section
   private void initInfoContainerPanel(){
      infoContainerPanel = new InfoContainerPanel(this); 
   }
   
   //accessor for the ID of the first ship selected
   public int getShip1ID(){
      return ship1ID;
   }
   
   //accessor for the id of the second ship selected
   public int getShip2ID(){
      return ship2ID;
   }
   
   //accessor for the int denoting the storage of the first ship selected
   public int getShip1Hold(){
      return ship1Hold;
   }
   
   //accessor for the int denoting the storage of the second ship selected
   public int getShip2Hold(){
      return ship2Hold;
   }
   
   //accessor for the first ship
   public Ship getShip1(){
      return ship1;
   }
   
   //accessor for the second ship
   public Ship getShip2(){
      return ship2;
   }
   
   //mutator for setting all the details of the first ship
   public void setShip1All(int hold, int id){
      ship1Hold = hold;
      ship1ID = id;
      
      if(hold == Player.HANGAR) ship1 = hangar.getShip(id);
      else if(hold == Player.FLEET) ship1 = fleet.getShip(id);
      else ship1 = null;
      
      remove(infoContainerPanel);
      initInfoContainerPanel();
      add(infoContainerPanel, BorderLayout.LINE_END);
   }
   
   //mutator for setting all the details of the second ship
   public void setShip2All(int hold, int id){
      ship2Hold = hold;
      ship2ID = id;
      
      if(hold == Player.HANGAR) ship2 = hangar.getShip(id);
      else if(hold == Player.FLEET) ship2 = fleet.getShip(id);
      else ship2 = null;
      
      remove(infoContainerPanel);
      initInfoContainerPanel();
      add(infoContainerPanel, BorderLayout.LINE_END);
   }
   
   //method that marks the first ship slot as empty
   public void emptyShip1(){
      setShip1All(-1, -1);
   }
   
   //method that marks the second ship slot as empty
   public void emptyShip2(){
      setShip2All(-1, -1);
   }
   
   //displays a message to inform user of error
   public void showErrorMessage(String q, String t){
      JOptionPane.showMessageDialog(null, q, t, JOptionPane.ERROR_MESSAGE);
   }
   
   //accessor for the object representing the current player
   public Player getPlayer(){
      return player;
   }
   
   //accessor for the page to go Back to
   public Component getLastPage(){
      return lastPage;
   }
   
   //accessor for the Hangar object
   public Hangar getHangar(){
      return hangar;
   }
   
   //accessor for the Fleet object
   public Fleet getFleet(){
      return fleet;
   }
}

//class that represents the panel containing all the ships from hangar
class HangarShipsPanel extends JPanel{
   private int length;
   private int height;
   
   private ShipSwapFrame frame;
   private Player player;
   
   private Hangar hangar;
   
   //constructor for the class
   public HangarShipsPanel(ShipSwapFrame f){
      frame = f;
      init();
   }
   
   //initializes the object (the panel containing all the ships from hangar)
   //makes a button for each ship
   //when a button is clicked, try to set either Ship 1 or Ship 2 to the selected ship
   //error message if both spots are full
   public void init(){
      
      player = frame.getPlayer();
      hangar = player.getHangar();
      length = frame.WINDOW_LENGTH / 3;
      height = frame.WINDOW_HEIGHT;
      
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      setPreferredSize(new Dimension(length, height));
      
      for(int i = 0; i < Hangar.MAX_SHIPS; i++){
         final int AT = i;
         JButton button;
         
         if(hangar.getShip(i) != null){
            button = new JButton(hangar.getShip(i).getName());
         } else {
            button = new JButton("Empty");
         }
         
         button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               if(frame.getShip1ID() == -1 && (frame.getShip2ID() != AT || frame.getShipHold2() == Player.FLEET)){
                  frame.setShip1All(Player.HANGAR, AT);
               } else if(frame.getShip2ID() == -1 && (frame.getShip1ID() != AT || frame.getShipHold1() == Player.FLEET)){
                  frame.setShip2All(Player.HANGAR, AT);
               } else if(frame.getShip1ID() == AT){
                  
               } else if(frame.getShip2ID() == AT){
               
               } else {
                  frame.showErrorMessage("You may only choose 2 ships to swap. Click on a ship again to deselect", "Too many!");
               }
            }
         });
      }
   }
}

class FleetShipsPanel extends JPanel{
   private int length;
   private int height;
   
   private ShipSwapFrame frame;
   private Player player;
   
   private Fleet fleet;
   
   public FleetShipsPanel(ShipSwapFrame f){
      frame = f;
      init();
   }
   
   public void init(){
      
      player = frame.getPlayer();
      fleet = player.getFleet();
      length = frame.WINDOW_LENGTH / 3;
      height = frame.WINDOW_HEIGHT;
      
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      setPreferredSize(new Dimension(length, height));
      
      for(int i = 0; i < Fleet.MAX_SHIPS; i++){
         final int AT = i;
         JButton button;
         
         if(fleet.getShip(i) != null){
            button = new JButton(fleet.getShip(i).getName());
         } else {
            button = new JButton("Empty");
         }
         
         button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               if(frame.getShip1ID() == -1){
                  frame.setShip1All(Player.FLEET, AT);
               } else if(frame.getShip2ID() == -1){
                  frame.setShip2All(Player.FLEET, AT);
               } else {
                  frame.showErrorMessage("You may only choose 2 ships to swap. Click on a ship again to deselect", "Too many!");
               }
            }
         });
      }
   }
}

class InfoContainerPanel extends JPanel{
   
   private ShipSwapFrame frame;
   private int length;
   private int height;
   
   private TopInfoPanel topInfoPanel;
   private BotInfoPanel botInfoPanel;
   
   public InfoContainerPanel(ShipSwapFrame f){
      frame = f;
      length = f.WINDOW_LENGTH / 2;
      height = f.WINDOW_HEIGHT;
      init();
   }
   
   public void init(){
      setPreferredSize(new Dimension(length, height));
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      
      initTopInfoPanel();
      initBotInfoPanel();
      
      add(topInfoPanel);
      add(botInfoPanel);
   }
   
   public void initTopInfoPanel(){
      topInfoPanel = new TopInfoPanel(this);
   }
   
   public void initBotInfoPanel(){
      botInfoPanel = new BotInfoPanel(this);
   }
   
   public int getLength(){
      return length;
   }
   
   public int getHeight(){
      return height;
   }
   
   public ShipSwapFrame getFrame(){
      return frame;
   }
}

class TopInfoPanel extends JPanel{
   
   private InfoContainerPanel upPanel;
   private int length;
   private int height;
   
   private ShipSwapFrame frame;
   private int shipHold;
   private int shipID;
   private Ship ship;
   
   public TopInfoPanel(InfoContainerPanel p){
      upPanel = p;
      init();
   }
   
   public void init(){
      frame = upPanel.getFrame();
      length = upPanel.getLength();
      height = upPanel.getHeight() / 2;
      shipHold = frame.getShip1Hold();
      shipID = frame.getShip1ID();
      ship = frame.getShip1();
      
      setPreferredSize(new Dimension(length, height));
      setLayout(new BorderLayout(0, 0));
      
      if(shipID == -1) displayEmpty();
      else displayShip();
   }
   
   public void displayEmpty(){
      addLine("Choose a Ship");
   }
   
   public void displayShip(){
      if(ship == null){
         addLine("Empty Spot");
      } else {
         JPanel textPanel = new JPanel();
         textPanel.setPreferredSize(new Dimension(length / 2, height));
         textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
         textPanel.add(new JLabel(ship.getName()));
         
         if(shipHold == Player.HANGAR)
            textPanel.add(new JLabel("In Hangar"));
         else
            textPanel.add(new JLabel("In Fleet"));
         
         textPanel.add(new JLabel("At Position: " + (shipID + 1)));
         textPanel.add(new JLabel("Attack Range: " + ship.getAttackRange()));
         textPanel.add(new JLabel("Travel Range: " + ship.getTravelRange()));
         textPanel.add(new JLabel("Firing Speed: " + ship.getFiringSpeed()));
         textPanel.add(new JLabel("Value: " + ship.getValue()));
         
         JPanel picPanel = new JPanel();
         picPanel.setPreferredSize(new Dimension(length / 2, height));
         picPanel.setLayout(new BorderLayout(0, 0));
         JLabel picture = new JLabel(new ImageIcon(ship.getImageFile()));
         picture.setPreferredSize(new Dimension(length / 2, height / 2));
         picPanel.add(picture, BorderLayout.PAGE_START);
         
         add(textPanel, BorderLayout.LINE_START);
         
      }
   }
   
   public void addLine(String s){
      add(new JLabel(s));
   }
}

class BotInfoPanel extends JPanel{
   private InfoContainerPanel upPanel;
   private int length;
   private int height;
   
   private ShipSwapFrame frame;
   private int shipHold;
   private int shipID;
   private Ship ship;
   
   public BotInfoPanel(InfoContainerPanel p){
      upPanel = p;
      init();
   }
   
   public void init(){
      frame = upPanel.getFrame();
      length = upPanel.getLength();
      height = upPanel.getHeight() / 2;
      shipHold = frame.getShip2Hold();
      shipID = frame.getShip2ID();
      ship = frame.getShip2();
      
      setPreferredSize(new Dimension(length, height));
      setLayout(new BorderLayout(0, 0));
      
      if(shipID == -1) displayEmpty();
      else displayShip();
   }
   
   public void displayEmpty(){
      addLine("Choose a Ship");
   }
   
   public void displayShip(){
      if(ship == null){
         addLine("Empty Spot");
      } else {
         JPanel textPanel = new JPanel();
         textPanel.setPreferredSize(new Dimension(length / 2, height));
         textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
         textPanel.add(new JLabel(ship.getName()));
         
         if(shipHold == Player.HANGAR)
            textPanel.add(new JLabel("In Hangar"));
         else
            textPanel.add(new JLabel("In Fleet"));
         
         textPanel.add(new JLabel("At Position: " + (shipID + 1)));
         textPanel.add(new JLabel("Attack Range: " + ship.getAttackRange()));
         textPanel.add(new JLabel("Travel Range: " + ship.getTravelRange()));
         textPanel.add(new JLabel("Firing Speed: " + ship.getFiringSpeed()));
         textPanel.add(new JLabel("Value: " + ship.getValue()));
         
         JPanel picPanel = new JPanel();
         picPanel.setPreferredSize(new Dimension(length / 2, height));
         picPanel.setLayout(new BorderLayout(0, 0));
         JLabel picture = new JLabel(new ImageIcon(ship.getImageFile()));
         picture.setPreferredSize(new Dimension(length / 2, height / 2));
         picPanel.add(picture, BorderLayout.PAGE_START);
         
         JButton swapButton = new JButton("Swap");
         swapButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               if(frame.getShip1ID() != -1 && frame.getShip2ID() != -1){
                  frame.getPlayer().swapShip(frame.getShip1Hold(), frame.getShip1ID(), frame.getShip2Hold(), frame.getShip2ID());
                  frame.init();
               }
            }
         });
         
         add(textPanel, BorderLayout.LINE_START);
         
      }
   }
   
   public void addLine(String s){
      add(new JLabel(s));
   }

}