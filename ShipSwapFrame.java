//Class name: ShipSwapFrame
//Author: Annie Gao
//Date: Jan. 12, 2018
//School: A.Y.Jackson S.S.
//Purpose: the graphical interface that allows user to switch the locations of two 
//ships in either hangar or fleet

import javax.swing.*;
import javax.swing.border.EtchedBorder;
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
   public static final int TITLE_HEIGHT = 40;

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
      setSize(WINDOW_LENGTH, WINDOW_HEIGHT);
      setLocationRelativeTo(null);
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

   public void update(){
      remove(hangarShipsPanel);
      remove(fleetShipsPanel);
      remove(infoContainerPanel);
      
      initHangarShipsPanel();
      initFleetShipsPanel();
      initInfoContainerPanel();
   
      add(hangarShipsPanel, BorderLayout.LINE_START);
      add(fleetShipsPanel, BorderLayout.CENTER);
      add(infoContainerPanel, BorderLayout.LINE_END);
      
      revalidate();
      repaint();
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
      revalidate();
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
      revalidate();
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

//accessor for the HangarShipsPanel object
   public HangarShipsPanel getHangarShipsPanel(){
      return hangarShipsPanel;
   }
}

//class that represents the panel containing all the ships from hangar
class HangarShipsPanel extends JPanel{
   private int length;
   private int height;

   private ShipSwapFrame frame;
   private Player player;
   private int numButtons;

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
      length = frame.WINDOW_LENGTH / 4;
      height = frame.WINDOW_HEIGHT;
      numButtons = Hangar.MAX_SHIPS;
   
      setLayout(new GridLayout(numButtons + 1, 0));
      setPreferredSize(new Dimension(length, height - ShipSwapFrame.TITLE_HEIGHT));
   //setOpaque(true);
   //setBackground(Color.GREEN);
   
      JLabel title = new JLabel("           Ships in Hangar");
      title.setPreferredSize(new Dimension(length, ShipSwapFrame.TITLE_HEIGHT));
      title.setFont(new Font("TimesRoman", Font.BOLD, 20));
      title.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
      add(title);
      
      for(int i = 0; i < Hangar.MAX_SHIPS; i++){
         final int AT = i;
         JButton button;
      
         if(hangar.getShip(i) != null){
            button = new JButton(hangar.getShip(i).getName());
         } 
         else {
            button = new JButton("Empty");
         }
      
         button.addActionListener(
                   new ActionListener(){
                      public void actionPerformed(ActionEvent e){
                         if(frame.getShip1ID() == -1 && (frame.getShip2ID() != AT || frame.getShip2Hold() == Player.FLEET)){
                            frame.setShip1All(Player.HANGAR, AT);
                         } 
                         else if(frame.getShip2ID() == -1 && (frame.getShip1ID() != AT || frame.getShip1Hold() == Player.FLEET)){
                            frame.setShip2All(Player.HANGAR, AT);
                         } 
                         else if(frame.getShip1ID() == AT){
                            frame.emptyShip1();
                         } 
                         else if(frame.getShip2ID() == AT){
                            frame.emptyShip2();
                         } 
                         else {
                            frame.showErrorMessage("You may only choose 2 ships to swap. Click on a ship again to deselect", "Too many!");
                         }
                      }
                   });
      
         JPanel buttonPanel = new JPanel();
         buttonPanel.setLayout(new BorderLayout(0, 0));
      //buttonPanel.setPreferredSize(new Dimension(length, (height-ShipSwapFrame.TITLE_HEIGHT) / numButtons));
         buttonPanel.add(button);
         add(buttonPanel);
         setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
      }
   }

   public int getNumButtons(){
      return numButtons;
   }
}

//class that represents the panel containing all the ships in Fleet
class FleetShipsPanel extends JPanel{
   private int length;
   private int height;

   private ShipSwapFrame frame;
   private Player player;
   private int numButtons;
   private int buttonHeight;

   private Fleet fleet;

//constructor of the class
   public FleetShipsPanel(ShipSwapFrame f){
      frame = f;
      init();
   }

//initializes the object(the panel containing all the ships in Fleet)
   public void init(){
   
      player = frame.getPlayer();
      fleet = player.getFleet();
      length = frame.WINDOW_LENGTH / 4;
      height = frame.WINDOW_HEIGHT;
      numButtons = Fleet.MAX_SHIPS;
      buttonHeight = (height - ShipSwapFrame.TITLE_HEIGHT) / frame.getHangarShipsPanel().getNumButtons();
   
      setLayout(new GridLayout(frame.getHangarShipsPanel().getNumButtons() + 1, 0));
      setPreferredSize(new Dimension(length, height - ShipSwapFrame.TITLE_HEIGHT));
   
      JLabel title = new JLabel("           Ships in Fleet");
      title.setPreferredSize(new Dimension(length, ShipSwapFrame.TITLE_HEIGHT));
      title.setFont(new Font("TimesRoman", Font.BOLD, 20));
      title.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
      add(title);
   
      for(int i = 0; i < Fleet.MAX_SHIPS; i++){
         final int AT = i;
         JButton button;
      
         if(fleet.getShip(i) != null){
            button = new JButton(fleet.getShip(i).getName());
         } 
         else {
            button = new JButton("Empty");
         }
      
         button.addActionListener(
                   new ActionListener(){
                      public void actionPerformed(ActionEvent e){
                         if(frame.getShip1ID() == -1 && (frame.getShip2ID() != AT || frame.getShip2Hold() == Player.HANGAR)){
                            frame.setShip1All(Player.FLEET, AT);
                         } 
                         else if(frame.getShip2ID() == -1 && (frame.getShip1ID() != AT || frame.getShip1Hold() == Player.HANGAR)){
                            frame.setShip2All(Player.FLEET, AT);
                         } 
                         else if(frame.getShip1ID() == AT){
                            frame.emptyShip1();
                         } 
                         else if(frame.getShip2ID() == AT){
                            frame.emptyShip2();
                         } 
                         else {
                            frame.showErrorMessage("You may only choose 2 ships to swap. Click on a ship again to deselect", "Too many!");
                         }
                      }
                   });
      
         JPanel buttonPanel = new JPanel();
      //buttonPanel.setPreferredSize(new Dimension(length - 10, buttonHeight));
         buttonPanel.setLayout(new BorderLayout(0, 0));
         buttonPanel.add(button);
         add(buttonPanel);
      }
      setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
   }
}

//class that represents the whole panel for displaying information of the ship
class InfoContainerPanel extends JPanel{

   private ShipSwapFrame frame;
   private int length;
   private int height;

   private TopInfoPanel topInfoPanel;
   private BotInfoPanel botInfoPanel;

//constructor for the class
   public InfoContainerPanel(ShipSwapFrame f){
      frame = f;
      length = f.WINDOW_LENGTH / 2;
      height = f.WINDOW_HEIGHT;
      init();
   }

//initializes the class (the whole panel for displaying information of the ship)
   public void init(){
      setPreferredSize(new Dimension(length, height));
      setLayout(new GridLayout(2, 0));
   
      initTopInfoPanel();
      initBotInfoPanel();
   
      add(topInfoPanel);
      add(botInfoPanel);
   }

//intializes the top section of the info panel (ship 1)
   public void initTopInfoPanel(){
      topInfoPanel = new TopInfoPanel(this);
   }

//initializes the bottom section of the info panel (ship 2)
   public void initBotInfoPanel(){
      botInfoPanel = new BotInfoPanel(this);
   }

//accessor for the length of this component
   public int getLength(){
      return length;
   }

//accessor for the height of this component
   public int getHeight(){
      return height;
   }

//accessor for the ShipSwapFrame this panel belongs to
   public ShipSwapFrame getFrame(){
      return frame;
   }
}

//class that represents the top half of the info panel (ship 1)
class TopInfoPanel extends JPanel{

   private InfoContainerPanel upPanel;
   private int length;
   private int height;

   private ShipSwapFrame frame;
   private int shipHold;
   private int shipID;
   private Ship ship;

//constructor for the class
   public TopInfoPanel(InfoContainerPanel p){
      upPanel = p;
      init();
   }

//initializes the object (the top half of the info panel)
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
      
      setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
   }

//what happens if no ship is selected as ship 1
   public void displayEmpty(){
      JPanel leftPanel = new JPanel();
      leftPanel.setPreferredSize(new Dimension(length / 3, height));
      //leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
      
      JPanel rightPanel = new JPanel();
      rightPanel.setPreferredSize(new Dimension(length / 5 * 3, height));
      //rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
      
      JLabel label = new JLabel("Choose a Ship");
      label.setFont(new Font("TimesRoman", Font.BOLD, 20));
      
      JLabel remind = new JLabel("To Deselect:");
      remind.setFont(new Font("TimesRoman", Font.BOLD, 20));
      
      JLabel remindS = new JLabel("             ");
      remindS.setFont(new Font("TimesRoman", Font.BOLD, 20));
      
      JLabel remind2 = new JLabel("Click Again");
      remind2.setFont(new Font("TimesRoman", Font.BOLD, 20));
      
      leftPanel.add(label);
      leftPanel.add(remindS);
      leftPanel.add(remind);
      leftPanel.add(remind2);
      add(BorderLayout.WEST, leftPanel);
   }

//what happens is a slot is selected for ship 1
   public void displayShip(){
      JPanel infoSide = new JPanel();
      infoSide.setPreferredSize(new Dimension(length / 3, height));
      infoSide.setLayout(new BoxLayout(infoSide, BoxLayout.Y_AXIS));
      
   /*infoSide.setOpaque(true);
   infoSide.setBackground(Color.red);
   add(infoSide,BorderLayout.WEST);*/
   
      JPanel picSide = new JPanel();
      picSide.setPreferredSize(new Dimension(length / 5 * 3, height));
   
      if(ship == null){
         JLabel emptyLabel, holdLabel, posLabel;
      
         emptyLabel = new JLabel("   Empty Slot");
      
         if(shipHold == Player.HANGAR)
            holdLabel = new JLabel("   In Hangar");
         else
            holdLabel = new JLabel("   In Fleet");
      
         posLabel = new JLabel("   At Position: ");
         JLabel posNumLabel = new JLabel("" + (shipID+1));
      
         emptyLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
         holdLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
         posLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
         posNumLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
      
         JPanel emptyPanel = new JPanel();
         emptyPanel.setLayout(new BorderLayout(0, 0));
         emptyPanel.add(emptyLabel, BorderLayout.WEST);
      
         JPanel holdPanel = new JPanel();
         holdPanel.setLayout(new BorderLayout(0, 0));
         holdPanel.add(holdLabel, BorderLayout.WEST);
      
         JPanel posPanel = new JPanel();
         posPanel.setLayout(new BorderLayout(0, 0));
         posPanel.add(posLabel, BorderLayout.CENTER);
         posPanel.add(posNumLabel, BorderLayout.EAST);
      
         infoSide.add(emptyPanel);
         infoSide.add(holdPanel);
         infoSide.add(posPanel);
      
         picSide = new PicSidePanel(Player.NO_PIC, length / 5 * 3, height);
         picSide.setPreferredSize(new Dimension(length / 5 * 3, height));
     } 
      else {
      // JPanel textPanel = new JPanel();
      //          //textPanel.setPreferredSize(new Dimension(length / 2, height));
      //          textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
      // 			
         JLabel nameLabel, holdLabel, posLabel, arLabel, trLabel, fsLabel, ugLabel, valueLabel;
      
         nameLabel = new JLabel("   " + ship.getName());
      
         if(shipHold == Player.HANGAR)
            holdLabel = new JLabel("   In Hangar");
         else
            holdLabel = new JLabel("   In Fleet");
      
         posLabel = new JLabel("   At Position: ");
         JLabel posNumLabel = new JLabel("" + (shipID + 1));
      
         arLabel = new JLabel("   Attack Range: ");
         JLabel arNumLabel = new JLabel("" + ship.getAttackRange());
      
         trLabel = new JLabel("   Travel Range: ");
         JLabel trNumLabel = new JLabel("" + ship.getTravelRange());
      
         fsLabel = new JLabel("   Firing Speed: ");
         JLabel fsNumLabel = new JLabel("" + ship.getFiringSpeed());
      
         ugLabel = new JLabel("   Upgrades left: ");
         JLabel ugNumLabel = new JLabel("" + ship.getUpgradesLeft());
            
         valueLabel = new JLabel("   Value: ");
         JLabel valueNumLabel = new JLabel("" + ship.getValue());
      
         nameLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
         holdLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
         posLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
         arLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
         trLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
         fsLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
         ugLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
         valueLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
      
         posNumLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
         arNumLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
         trNumLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
         fsNumLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
         ugNumLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
         valueNumLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
      
         JPanel namePanel = new JPanel();
         namePanel.setLayout(new BorderLayout(0, 0));
         namePanel.add(nameLabel, BorderLayout.WEST);
      
         JPanel holdPanel = new JPanel();
         holdPanel.setLayout(new BorderLayout(0, 0));
         holdPanel.add(holdLabel, BorderLayout.WEST);
      
         JPanel posPanel = new JPanel();
         posPanel.setLayout(new BorderLayout(0, 0));
         posPanel.add(posLabel, BorderLayout.WEST);
         posPanel.add(posNumLabel, BorderLayout.EAST);
      
         JPanel arPanel = new JPanel();
         arPanel.setLayout(new BorderLayout(0, 0));
         arPanel.add(arLabel, BorderLayout.WEST);
         arPanel.add(arNumLabel, BorderLayout.EAST);
      
         JPanel trPanel = new JPanel();
         trPanel.setLayout(new BorderLayout(0, 0));
         trPanel.add(trLabel, BorderLayout.WEST);
         trPanel.add(trNumLabel, BorderLayout.EAST);
      
         JPanel fsPanel = new JPanel();
         fsPanel.setLayout(new BorderLayout(0, 0));
         fsPanel.add(fsLabel, BorderLayout.WEST);
         fsPanel.add(fsNumLabel, BorderLayout.EAST);
      
         JPanel ugPanel = new JPanel();
         ugPanel.setLayout(new BorderLayout(0, 0));
         ugPanel.add(ugLabel, BorderLayout.WEST);
         ugPanel.add(ugNumLabel, BorderLayout.EAST);
      
         JPanel valuePanel = new JPanel();
         valuePanel.setLayout(new BorderLayout(0, 0));
         valuePanel.add(valueLabel, BorderLayout.WEST);
         valuePanel.add(valueNumLabel, BorderLayout.EAST);
      
         infoSide.add(namePanel);
         infoSide.add(holdPanel);
         infoSide.add(posPanel);
         infoSide.add(arPanel);
         infoSide.add(trPanel);
         infoSide.add(fsPanel);
         infoSide.add(ugPanel);
         infoSide.add(valuePanel);
      
         picSide = new PicSidePanel(ship.getImageFile(), length / 5 * 3, height);
         picSide.setPreferredSize(new Dimension(length / 5 * 3, height));
               
      }
      add(infoSide, BorderLayout.WEST);
      add(picSide, BorderLayout.EAST);
   }

//utility method for adding a line of text
   public void addLine(String s){
      add(new JLabel(s));
   }
}

//class that represents the bottom half of the info panel (ship 2)
class BotInfoPanel extends JPanel{
   private InfoContainerPanel upPanel;
   private int length;
   private int height;

   private ShipSwapFrame frame;
   private int shipHold;
   private int shipID;
   private Ship ship;
   private Component lastPage;

   public BotInfoPanel(InfoContainerPanel p){
      upPanel = p;
      init();
   }

//initializes the object (the bottom half of the info panel)
   public void init(){
      frame = upPanel.getFrame();
      length = upPanel.getLength();
      height = upPanel.getHeight() / 2;
      shipHold = frame.getShip2Hold();
      shipID = frame.getShip2ID();
      ship = frame.getShip2();
      lastPage = frame.getLastPage();
   
      setPreferredSize(new Dimension(length, height));
      setLayout(new BorderLayout(0, 0));
   
      if(shipID == -1) displayEmpty();
      else displayShip();
   
      setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
   }

//what happens if no ship is selected as ship 2
   public void displayEmpty(){
      JPanel leftPanel = new JPanel();
      leftPanel.setPreferredSize(new Dimension(length / 3, height));
      //leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
      
      JPanel rightPanel = new JPanel();
      rightPanel.setPreferredSize(new Dimension(length / 5 * 3, height));
      //rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
      
      JLabel label = new JLabel("Choose a Ship");
      label.setFont(new Font("TimesRoman", Font.BOLD, 20));
      
      JLabel remind = new JLabel("To Deselect:");
      remind.setFont(new Font("TimesRoman", Font.BOLD, 20));
      
      JLabel remindS = new JLabel("             ");
      remindS.setFont(new Font("TimesRoman", Font.BOLD, 20));
      
      JLabel remind2 = new JLabel("Click Again");
      remind2.setFont(new Font("TimesRoman", Font.BOLD, 20));
      
      leftPanel.add(label);
      leftPanel.add(remindS);
      leftPanel.add(remind);
      leftPanel.add(remind2);
      add(BorderLayout.WEST, leftPanel);
            
      JButton swapButton = new JButton("Swap");
      swapButton.addActionListener(
                new ActionListener(){
                   public void actionPerformed(ActionEvent e){
                      if(frame.getShip1ID() != -1 && frame.getShip2ID() != -1){
                         frame.getPlayer().swapShip(frame.getShip1Hold(), frame.getShip1ID(), frame.getShip2Hold(), frame.getShip2ID());
                         frame.emptyShip1();
                         frame.emptyShip2();
                         frame.update();
                      } else {
                        frame.showErrorMessage("You did not select 2 ships to swap.", "Not enough!");
                      }
                   }
                });
      if(frame.getShip1ID() == -1 || frame.getShip2ID() == -1) swapButton.setEnabled(false);
         
      
      JButton backButton;
      backButton = new JButton("Back");
      backButton.addActionListener(
                new ActionListener(){
                   public void actionPerformed(ActionEvent e){
                      ((HangarFrame)lastPage).setVisible(true, true);
                      frame.setVisible(false);
                   }
                });
      
      
      rightPanel.add(swapButton);
      rightPanel.add(backButton);
      add(BorderLayout.EAST, rightPanel);
   }

//what happens is a slot is selected for ship 2
   public void displayShip(){
      JPanel infoSide = new JPanel();
      infoSide.setPreferredSize(new Dimension(length / 3, height));
      infoSide.setLayout(new BoxLayout(infoSide, BoxLayout.Y_AXIS));
      
   /*infoSide.setOpaque(true);
   infoSide.setBackground(Color.red);
   add(infoSide,BorderLayout.WEST);*/
      PicSidePanel picSide;
      
      if(ship == null){
         picSide = new PicSidePanel(Player.NO_PIC, length / 5 * 3, height);
         picSide.setPreferredSize(new Dimension(length / 5 * 3, height));
   
         JLabel emptyLabel, holdLabel, posLabel;
      
         emptyLabel = new JLabel("   Empty Slot");
      
         if(shipHold == Player.HANGAR)
            holdLabel = new JLabel("   In Hangar");
         else
            holdLabel = new JLabel("   In Fleet");
      
         posLabel = new JLabel("   At Position: ");
         JLabel posNumLabel = new JLabel("" + (shipID+1));
      
         emptyLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
         holdLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
         posLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
         posNumLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
      
         JPanel emptyPanel = new JPanel();
         emptyPanel.setLayout(new BorderLayout(0, 0));
         emptyPanel.add(emptyLabel, BorderLayout.WEST);
      
         JPanel holdPanel = new JPanel();
         holdPanel.setLayout(new BorderLayout(0, 0));
         holdPanel.add(holdLabel, BorderLayout.WEST);
      
         JPanel posPanel = new JPanel();
         posPanel.setLayout(new BorderLayout(0, 0));
         posPanel.add(posLabel, BorderLayout.CENTER);
         posPanel.add(posNumLabel, BorderLayout.EAST);
      
         infoSide.add(emptyPanel);
         infoSide.add(holdPanel);
         infoSide.add(posPanel);
      
         JButton swapButton = new JButton("Swap");
         swapButton.addActionListener(
                new ActionListener(){
                   public void actionPerformed(ActionEvent e){
                      if(frame.getShip1ID() != -1 && frame.getShip2ID() != -1){
                         frame.getPlayer().swapShip(frame.getShip1Hold(), frame.getShip1ID(), frame.getShip2Hold(), frame.getShip2ID());
                         frame.emptyShip1();
                         frame.emptyShip2();
                         frame.update();
                      } else {
                        frame.showErrorMessage("You did not select 2 ships to swap.", "Not enough!");
                      }
                   }
                });
         if(frame.getShip1ID() == -1 || frame.getShip2ID() == -1) swapButton.setEnabled(false);
         
         picSide.add(swapButton);
      
         JButton backButton;
         backButton = new JButton("Back");
         backButton.addActionListener(
                new ActionListener(){
                   public void actionPerformed(ActionEvent e){
                      ((HangarFrame)lastPage).setVisible(true, true); System.out.println("check 1");
                      frame.setVisible(false);
                   }
                });
      
         picSide.add(backButton);
      
      } 
      else {
      // JPanel textPanel = new JPanel();
      //          //textPanel.setPreferredSize(new Dimension(length / 2, height));
      //          textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
      // 			
         JLabel nameLabel, holdLabel, posLabel, arLabel, trLabel, fsLabel, ugLabel, valueLabel;
      
         nameLabel = new JLabel("   " + ship.getName());
      
         if(shipHold == Player.HANGAR)
            holdLabel = new JLabel("   In Hangar");
         else
            holdLabel = new JLabel("   In Fleet");
      
         posLabel = new JLabel("   At Position: ");
         JLabel posNumLabel = new JLabel("" + (shipID + 1));
      
         arLabel = new JLabel("   Attack Range: ");
         JLabel arNumLabel = new JLabel("" + ship.getAttackRange());
      
         trLabel = new JLabel("   Travel Range: ");
         JLabel trNumLabel = new JLabel("" + ship.getTravelRange());
      
         fsLabel = new JLabel("   Firing Speed: ");
         JLabel fsNumLabel = new JLabel("" + ship.getFiringSpeed());
      
         ugLabel = new JLabel("   Upgrades left: ");
         JLabel ugNumLabel = new JLabel("" + ship.getUpgradesLeft());
            
         valueLabel = new JLabel("   Value: ");
         JLabel valueNumLabel = new JLabel("" + ship.getValue());
      
         nameLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
         holdLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
         posLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
         arLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
         trLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
         fsLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
         ugLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
         valueLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
      
         posNumLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
         arNumLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
         trNumLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
         fsNumLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
         ugNumLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
         valueNumLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
      
         JPanel namePanel = new JPanel();
         namePanel.setLayout(new BorderLayout(0, 0));
         namePanel.add(nameLabel, BorderLayout.WEST);
      
         JPanel holdPanel = new JPanel();
         holdPanel.setLayout(new BorderLayout(0, 0));
         holdPanel.add(holdLabel, BorderLayout.WEST);
      
         JPanel posPanel = new JPanel();
         posPanel.setLayout(new BorderLayout(0, 0));
         posPanel.add(posLabel, BorderLayout.WEST);
         posPanel.add(posNumLabel, BorderLayout.EAST);
      
         JPanel arPanel = new JPanel();
         arPanel.setLayout(new BorderLayout(0, 0));
         arPanel.add(arLabel, BorderLayout.WEST);
         arPanel.add(arNumLabel, BorderLayout.EAST);
      
         JPanel trPanel = new JPanel();
         trPanel.setLayout(new BorderLayout(0, 0));
         trPanel.add(trLabel, BorderLayout.WEST);
         trPanel.add(trNumLabel, BorderLayout.EAST);
      
         JPanel fsPanel = new JPanel();
         fsPanel.setLayout(new BorderLayout(0, 0));
         fsPanel.add(fsLabel, BorderLayout.WEST);
         fsPanel.add(fsNumLabel, BorderLayout.EAST);
      
         JPanel ugPanel = new JPanel();
         ugPanel.setLayout(new BorderLayout(0, 0));
         ugPanel.add(ugLabel, BorderLayout.WEST);
         ugPanel.add(ugNumLabel, BorderLayout.EAST);
      
         JPanel valuePanel = new JPanel();
         valuePanel.setLayout(new BorderLayout(0, 0));
         valuePanel.add(valueLabel, BorderLayout.WEST);
         valuePanel.add(valueNumLabel, BorderLayout.EAST);
      
         infoSide.add(namePanel);
         infoSide.add(holdPanel);
         infoSide.add(posPanel);
         infoSide.add(arPanel);
         infoSide.add(trPanel);
         infoSide.add(fsPanel);
         infoSide.add(ugPanel);
         infoSide.add(valuePanel);
      
         picSide = new PicSidePanel(ship.getImageFile(), length / 5 * 3, height);
         picSide.setPreferredSize(new Dimension(length / 5 * 3, height));
         
         JButton swapButton = new JButton("Swap");
         swapButton.addActionListener(
                new ActionListener(){
                   public void actionPerformed(ActionEvent e){
                      if(frame.getShip1ID() != -1 && frame.getShip2ID() != -1){
                         frame.getPlayer().swapShip(frame.getShip1Hold(), frame.getShip1ID(), frame.getShip2Hold(), frame.getShip2ID());
                         frame.emptyShip1();
                         frame.emptyShip2();
                         frame.update();
                      } else {
                        frame.showErrorMessage("You did not select 2 ships to swap.", "Not enough!");
                      }
                   }
                });
         
         if(frame.getShip1ID() == -1 || frame.getShip2ID() == -1) swapButton.setEnabled(false);
         picSide.add(swapButton);
         
         JButton backButton;
         backButton = new JButton("Back");
         backButton.addActionListener(
                new ActionListener(){
                   public void actionPerformed(ActionEvent e){
                      ((HangarFrame)lastPage).setVisible(true, true);
                      frame.setVisible(false);
                   }
                });
      
         picSide.add(backButton);
      
      
      
      }
      add(infoSide, BorderLayout.WEST);
      picSide.setOpaque(true);
      add(picSide, BorderLayout.EAST);
      
      
   }
   
}

class PicSidePanel extends JPanel{

   Image bgImage;
   int length;
   int height;
   
   public PicSidePanel(String imageFile, int len, int hei){
      bgImage = new ImageIcon(imageFile).getImage();
      height = hei;
      length = len;
   }

   @Override
   protected void paintComponent(Graphics g) {
   
      super.paintComponent(g);
      g.drawImage(bgImage, 0, 0, length, height, this);
   }
}

