//Class name: HangarFrame
//Author: Annie Gao
//Date: Jan. 12, 2018
//School: A.Y.Jackson S.S.
//Purpose: the graphical representation of the Hangar class

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HangarFrame extends JFrame{
   
   private Component lastPage;
   private Player player;
   private int currentShipID;
   
   private Hangar hangar;
   
   private ShipSideBar shipSideBar;
   private HangarInfoPanel hangarInfoPanel;
   
   public static final int WINDOW_HEIGHT = MainScene.WINDOW_WIDTH;
   public static final int WINDOW_LENGTH = MainScene.WINDOW_LENGTH;
   public static final int SHIP_SIDEBAR_LENGTH = WINDOW_LENGTH / 5;
   public static final int PAGE_SPLIT_HEIGHT = WINDOW_HEIGHT / 2 + 50;
   
   //constructor of the HangarFrame class
   public HangarFrame(Player p){
      player = p;
      currentShipID = 0;
      init();
   }
   
   //a methods that other classes use to open this page 
   //takes in the last page to potentially go back to
   //takes in boolean that dictates whether or not to make this page visible
   public void setVisible(Component c, boolean b){
      lastPage = c;
      init();
   }
   
   //initializes the Hangar Page
   public void init(){
      setSize(WINDOW_LENGTH, WINDOW_HEIGHT);
      setLocationRelativeTo(null);
      setLayout(new BorderLayout(0, 0));
      
      hangar = player.getHangar();
      
      initShipSideBar();
      initHangarInfoPanel();
      
      add(BorderLayout.LINE_START, shipSideBar);
      add(BorderLayout.LINE_END, hangarInfoPanel);
      
      setVisible(true);
   }
   
   //initializes the left side bar, which contains a list of ships for user to click on
   private void initShipSideBar(){
      shipSideBar = new ShipSideBar(this);
      
   }
   
   //initializes the right side of the page, which displays the information of the selected ship
   private void initHangarInfoPanel(){
      hangarInfoPanel = new HangarInfoPanel(this);
   }
   
   //updates the right (Ship information) side of the page when a new ship has been selected
   public void updateShipInfoPanel(int id){
      currentShipID = id;
      remove(hangarInfoPanel);
      hangarInfoPanel = new HangarInfoPanel(this);
      initHangarInfoPanel();
      add(BorderLayout.LINE_END, hangarInfoPanel);
   }
   
   //displays a question pane that asks the user to select yes or no
   //takes in the question as a String, and the title of the pane as a String
   public boolean askQuestion(String ques, String title){
      return JOptionPane.showConfirmDialog(null, ques, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
   }
   
   //accessor method for the page to go back to
   public Component getLastPage(){
      return lastPage;
   }
   
   //accessor method for the Player object
   public Player getPlayer(){
      return player;
   }
   
   //accessor method for the ID of teh current ship in hangar
   public int getCurrentShipID(){
      return currentShipID;
   }
}

//class representing the left sidebar holding all the ships in hangar for player to choose from
class ShipSideBar extends JPanel{
   
   private HangarFrame hangarFrame;
   private Player player;
   private int length;
   private int height;
   private int numButtons;
   
   private Hangar hangar;
   
   //constructs a new left sidebar of ships
   public ShipSideBar(HangarFrame hf){
      hangarFrame = hf;
      init();
   }
   
   //initializes the left sidebar of ships
   //creates a button for each ship
   //attaches an ActionListener to each button that updates the Ship info displayed
   //when the button is clicked
   public void init(){
      setLayout(new GridLayout(numButtons, 0));
      setPreferredSize(new Dimension(length, height));
      
      player = hangarFrame.getPlayer();
      hangar = player.getHangar();
      length = HangarFrame.SHIP_SIDEBAR_LENGTH;
      height = HangarFrame.WINDOW_HEIGHT;
      numButtons = Hangar.MAX_SHIPS;
      
      for(int at = 0; at < Hangar.MAX_SHIPS; at++){
         final int AT = at;
         JButton button;
         if(hangar.getShip(at) != null)
            button = new JButton(hangar.getShip(at).getName());
         else
            button = new JButton("Empty Ship " + (at + 1));
         
         button.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent e){
                  hangarFrame.updateShipInfoPanel(AT);
               }
            });
         
         add(button);
      }
   }
   
}

//class representing the left side of the page, which displays information of the
//current selected ship. Displays info of first ship when the page first loads
class HangarInfoPanel extends JPanel{
   
   private HangarFrame hangarFrame;
   private Component lastPage;
   private HangarInfoTop hangarInfoTop;
   private HangarInfoBot hangarInfoBot;
   
   private Player player;
   private Hangar hangar;
   private int shipID;
   private String imageFile;
   private int length;
   private int height;
   private int pageSplitHeight;
   
   //constructor of the HangarInfoPanel class
   //takes in the HangarFrame that it is a part of
   public HangarInfoPanel(HangarFrame hf){
      hangarFrame = hf;
      init();
   }
   
   //initializes the panel to display the information of the selected ship
   public void init(){
      lastPage = hangarFrame.getLastPage();
      player = hangarFrame.getPlayer();
      hangar = player.getHangar();
      shipID = hangarFrame.getCurrentShipID();
      imageFile = hangar.getShip(shipID).getImageFile();
      length = HangarFrame.WINDOW_LENGTH - HangarFrame.SHIP_SIDEBAR_LENGTH;
      height = HangarFrame.WINDOW_HEIGHT;
      pageSplitHeight = HangarFrame.PAGE_SPLIT_HEIGHT;
      
      setPreferredSize(new Dimension(length, height));
      setLayout(new BorderLayout(0, 0));
      setOpaque(true);
      setBackground(Color.red);
      
      initHangarInfoTop();
      initHangarInfoBot();
      
      add(hangarInfoTop, BorderLayout.PAGE_START);
      add(hangarInfoBot, BorderLayout.PAGE_END);
   }
   
   //initializes the top half of this panel
   public void initHangarInfoTop(){
      hangarInfoTop = new HangarInfoTop(this);
   }
   
   //initializes the bottom half of this panel
   public void initHangarInfoBot(){
      hangarInfoBot = new HangarInfoBot(this);
   }
   
   //accessor for the HangarFrame this objet belongs to
   public HangarFrame getHangarFrame(){
      return hangarFrame;
   }
   
   //accessor for the length of this information panel
   public int getLength(){
      return length;
   }
   
   //accessor for the height of this information panel
   public int getHeight(){
      return height;
   }
}

//class that represents the top half of the information panel
class HangarInfoTop extends JPanel{
   
   private Player player;
   private int shipID;
   private int length;
   private int height;
   private String imageFile;
   private HangarFrame hangarFrame;
   private HangarInfoPanel hangarInfoPanel;
   private HangarInfoTopLeft hangarInfoTopLeft;
   private HangarInfoTopMid hangarInfoTopMid;
   private HangarInfoTopRight hangarInfoTopRight;
   
   //constuctor for this class
   //takes in the HangarInfoPanel that it is a part of
   public HangarInfoTop(HangarInfoPanel hp){
      hangarInfoPanel = hp;
      init();
   }
   
   //initializes the object (the top half of the information panel)
   //initializes each of its 3 sections (left, middle, right) and adds them
   public void init(){
      hangarFrame = hangarInfoPanel.getHangarFrame();
      player = hangarFrame.getPlayer();
      shipID = hangarFrame.getCurrentShipID();
      imageFile = player.getHangar().getShip(shipID).getImageFile();
      length = hangarInfoPanel.getLength();
      height = hangarInfoPanel.getHeight() - HangarFrame.PAGE_SPLIT_HEIGHT;
      
      setPreferredSize(new Dimension(length, height));
      setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
      setOpaque(true);
      setBackground(Color.yellow);
      
      initHangarInfoTopLeft();
      initHangarInfoTopMid();
      initHangarInfoTopRight();
      
      add(hangarInfoTopLeft);
      add(hangarInfoTopMid);
      add(hangarInfoTopRight);
   }
   
   //creates the leftmost section
   public void initHangarInfoTopLeft(){
      hangarInfoTopLeft = new HangarInfoTopLeft(this);
   }
   
   //creates the middle section
   public void initHangarInfoTopMid(){
      hangarInfoTopMid = new HangarInfoTopMid(this);
   }
   
   //creates the rightmost section
   public void initHangarInfoTopRight(){
      hangarInfoTopRight = new HangarInfoTopRight(this);
   }
   
   //accessor method for the HangarFrame that it is part of
   public HangarFrame getHangarFrame(){
      return hangarFrame;
   }
   
   //accessor method for its length
   public int getLength(){
      return length;
   }
   
   //accessor method for its height
   public int getHeight(){
      return height;
   }
}

//class representing the bottom section of the information panel
class HangarInfoBot extends JPanel{
   
   private HangarFrame hangarFrame;
   private Component lastPage;
   private Player player;
   private int shipID;
   private int length;
   private int height;
   private HangarInfoPanel hangarInfoPanel;
   private HangarInfoBotLeft hangarInfoBotLeft;
   private HangarInfoBotMid hangarInfoBotMid;
   private HangarInfoBotRight hangarInfoBotRight;
   
   //constructor for the class
   public HangarInfoBot(HangarInfoPanel hp){
      hangarInfoPanel = hp;
      init();
   }
   
   //initializes this object (the bottom of the information pane)
   //initializes each of its 3 sections (left, middle, right) and adds them
   public void init(){
      hangarFrame = hangarInfoPanel.getHangarFrame();
      lastPage = hangarFrame.getLastPage();
      player = hangarFrame.getPlayer();
      shipID = hangarFrame.getCurrentShipID();
      length = HangarFrame.WINDOW_LENGTH - HangarFrame.SHIP_SIDEBAR_LENGTH;
      height = HangarFrame.WINDOW_HEIGHT / 2;
      
      setPreferredSize(new Dimension(length, height));
      setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
      setOpaque(true);
      setBackground(Color.blue);
      
      initHangarInfoBotLeft();
      initHangarInfoBotMid();
      initHangarInfoBotRight();
      
      add(hangarInfoBotLeft);
      add(hangarInfoBotMid);
      add(hangarInfoBotRight);
   }
   
   //creates the leftmost section
   private void initHangarInfoBotLeft(){
      hangarInfoBotLeft = new HangarInfoBotLeft(this);
   }
   
   //creates the middle section
   private void initHangarInfoBotMid(){
      hangarInfoBotMid = new HangarInfoBotMid(this);
   }
   
   //creates the rightmost section
   private void initHangarInfoBotRight(){
      hangarInfoBotRight = new HangarInfoBotRight(this);
   }
   
   //accessor for the HangarFrame this JPanel belongs to
   public HangarFrame getHangarFrame(){
      return hangarFrame;
   }
   
   //accessor for the length of this JPanel
   public int getLength(){
      return length;
   }
   
   //accessor for the height of this JPanel 
   public int getHeight(){
      return height;
   }
}

//class that represents the top left panel of the information panel
class HangarInfoTopLeft extends JPanel{
   
   private HangarInfoTop hangarInfoTop;
   private HangarFrame hangarFrame;
   private Player player;
   private int shipID;
   private int length;
   private int height;
   
   private Hangar hangar;
   private Ship ship;
   
   //constructor for this class
   public HangarInfoTopLeft(HangarInfoTop ht){
      hangarInfoTop = ht;
      
      if(ship == null) ship = new Ship("", Player.NO_PIC, Ship.BASIC_STAT, Ship.BASIC_STAT, Ship.BASIC_STAT, Ship.INIT_UPGRADES, Ship.BASIC_COST, false);
      
      init();
   }
   
   //initializes the object
   //adds the information of the Ship to this JPanel
   public void init(){
      hangarFrame = hangarInfoTop.getHangarFrame();
      player = hangarFrame.getPlayer();
      shipID = hangarFrame.getCurrentShipID();
      length = hangarInfoTop.getLength() / 3;
      height = hangarInfoTop.getHeight();
      
      hangar = player.getHangar();
      ship = hangar.getShip(shipID);
      
      setPreferredSize(new Dimension(length, height));
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      setOpaque(true);
      setBackground(Color.green);
      
      addLine("Travel range: " + ship.getTravelRange());
      addLine("Attack range: " + ship.getAttackRange());
      addLine("Firing speed: " + ship.getFiringSpeed());
      
      if(ship.getOwnedByPlayer())
         addLine("Sell for: " + ship.getSellPrice());
      else
         addLine("Cost: " + Ship.BASIC_COST);
   }
   
   //utility method for adding lines of text onto JPanel
   private void addLine(String s){
      add(new JLabel(s));
   }
}

//class that represents the middle section of the top half of the information panel
class HangarInfoTopMid extends JPanel{
   
   private HangarInfoTop hangarInfoTop;
   
   private HangarFrame hangarFrame;
   private Player player;
   private int shipID;
   private int length;
   private int height;
   
   private Hangar hangar;
   private Ship ship;
   
   //constructor of the class
   public HangarInfoTopMid(HangarInfoTop ht){
      hangarInfoTop = ht;
      init();
   }
   
   //initializes the object
   //creates the text field and button for player to change the name of the ship
   public void init(){
      hangarFrame = hangarInfoTop.getHangarFrame();
      player = hangarFrame.getPlayer();
      shipID = hangarFrame.getCurrentShipID();
      length = hangarInfoTop.getLength() / 3;
      height = hangarInfoTop.getHeight();
      
      hangar = player.getHangar();
      ship = hangar.getShip(shipID);
      
      if(ship == null) 
         ship = new Ship("", Player.NO_PIC, Ship.BASIC_STAT, Ship.BASIC_STAT, Ship.BASIC_STAT, Ship.INIT_UPGRADES, Ship.BASIC_COST, false);
      
      setPreferredSize(new Dimension(length, height));
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      setOpaque(true);
      setBackground(Color.gray);
      
      JTextField entry;
      JButton button;
      
      if(ship.getOwnedByPlayer()){
         entry = new JTextField(10); 
         button = new JButton("Change Ship Name");
      	
			final JTextField ENTRY = entry;
         button.addActionListener(
            new ActionListener(){
					public void actionPerformed(ActionEvent e){
                  System.out.println("Change Ship Name button clicked");
                  ship.setName(ENTRY.getText());
               }
            });
      
         add(entry);
         add(button);
      }
      
   }
}

//class that represents the rightmost section of the top half of the informaion panel
class HangarInfoTopRight extends JPanel{
   
   private HangarInfoTop hangarInfoTop;
   private HangarFrame hangarFrame;
   private Player player;
   private int shipID;
   private String imageFile;
   private int length;
   private int height;
   
   private Hangar hangar;
   private Ship ship;
   private ImageIcon image;
   
   //constructor of this class
   //takes in the HangarInfoTop object that contains it
   public HangarInfoTopRight(HangarInfoTop ht){
      hangarInfoTop = ht;
      init();
   }
   
   //initializes this object
   //adds the picture associated with this ship to the JPanel
   public void init(){
      hangarFrame = hangarInfoTop.getHangarFrame();
      player = hangarFrame.getPlayer();
      shipID = hangarFrame.getCurrentShipID();
      hangar = player.getHangar();
      ship = hangar.getShip(shipID);
      imageFile = ship.getImageFile();
      length = hangarInfoTop.getLength() / 3;
      height = hangarInfoTop.getHeight();
      
      image = new ImageIcon(imageFile);
      
      setPreferredSize(new Dimension(length, height));
      setLayout(new BorderLayout());
      setOpaque(true);
      setBackground(Color.pink);
      add(new JLabel(image), BorderLayout.CENTER);
   }
}

//class that represents the leftmost section of the bottom half of the information panel
class HangarInfoBotLeft extends JPanel{
   private HangarInfoBot hangarInfoBot;
   private HangarFrame hangarFrame;
   private Player player;
   private int shipID;
   private int length;
   private int height;
   
   private Hangar hangar;
   private Ship ship;
   
   //constructor of this class
   //takes in the HangarInfoBot object that contains it
   public HangarInfoBotLeft(HangarInfoBot hb){
      hangarInfoBot = hb;
      init();
   }
   
   //initializes this object (the leftmost section of the bottom half of the info panel)
   //adds the upgrade categories and the player money to the JPanel
   public void init(){
      hangarFrame = hangarInfoBot.getHangarFrame();
      player = hangarFrame.getPlayer();
      shipID = hangarFrame.getCurrentShipID();
      hangar = player.getHangar();
      ship = hangar.getShip(shipID);
      length = hangarInfoBot.getLength() / 3;
      height = hangarInfoBot.getHeight();
      
      setPreferredSize(new Dimension(length, height));
      setLayout(new FlowLayout());
      setOpaque(true);
      setBackground(Color.orange);
      
      hangar = player.getHangar();
      ship = hangar.getShip(shipID);
      
      if(ship != null){
         addLine("\tUpgrade: ");
         addLine("Travel Range: ");
         addLine("Attack Range: ");
         addLine("Firing Speed: ");
      }
      addLine("Player Money: " + player.getNumCoins());
   }
   
   //utility method for adding a line of text onto the JPanel
   private void addLine(String s){
      add(new JLabel(s));
   }
}

//class that represents the middle section of the bottom half of the info panel
class HangarInfoBotMid extends JPanel{
   
   private HangarInfoBot hangarInfoBot;
   private HangarFrame hangarFrame;
   private Player player;
   private int shipID;
   private int length;
   private int height;
   
   private Hangar hangar;
   private Ship ship;
   
   //constructor of this class
   public HangarInfoBotMid(HangarInfoBot hb){
      hangarInfoBot = hb;
      init();
   }
   
   //initializes this object
   //adds the buttons for upgrading each of the stats
   public void init(){
      hangarFrame = hangarInfoBot.getHangarFrame();
      player = hangarFrame.getPlayer();
      shipID = hangarFrame.getCurrentShipID();
      hangar = player.getHangar();
      ship = hangar.getShip(shipID);
      length = hangarInfoBot.getLength() / 3;
      height = hangarInfoBot.getHeight();
      
      setPreferredSize(new Dimension(length, height));
      setLayout(new FlowLayout());
      setOpaque(true);
      setBackground(Color.white);
      
      JLabel trLabel, arLabel, fsLabel;
      JButton trButton, arButton, fsButton;
      
      if(ship != null){
         add(new JLabel("Cost: "));
      
         trLabel = new JLabel(ship.calcUpgradeCost(Ship.TR_Upgrade)+ "");
         trButton = new JButton("Upgrade");
         trButton.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent e){
                  player.upgradeShip(shipID, Ship.TR_Upgrade);
               }
            });
      
         arLabel = new JLabel(ship.calcUpgradeCost(Ship.AR_Upgrade)+ "");
         arButton = new JButton("Upgrade");
         arButton.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent e){
                  player.upgradeShip(shipID, Ship.AR_Upgrade);
               }
            });
      
         fsLabel = new JLabel(ship.calcUpgradeCost(Ship.FS_Upgrade)+ "");
         fsButton = new JButton("Upgrade");
         fsButton.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent e){
                  player.upgradeShip(shipID, Ship.FS_Upgrade);
               }
            });
      
         addLineButton(trLabel, trButton);
         addLineButton(arLabel, arButton);
         addLineButton(fsLabel, fsButton);
         add(new JLabel("Money After Sell: " + (player.getNumCoins() - ship.getSellPrice())));
      } else {
         add(new JLabel("Money After Pay: " + (player.getNumCoins() - Ship.BASIC_COST)));
      }
   }
   
   //utility method that adds a line of text and a button in one line
   //takes in the line of text and the button to be added in one line 
   private void addLineButton(JLabel label, JButton button){
      JPanel temp = new JPanel();
      temp.setPreferredSize(new Dimension(length, height / 5));
      label.setPreferredSize(new Dimension(length / 2, height / 5));
      button.setPreferredSize(new Dimension(length / 2, height / 5));
      temp.add(label);
      temp.add(button);
   }
}

//this class represents the rightmost section of the bottom half of the info panel
class HangarInfoBotRight extends JPanel{
   
   private HangarInfoBot hangarInfoBot;
   private HangarFrame hangarFrame;
   private Component lastPage;
   private Player player;
   private int shipID;
   private int length;
   private int height;
   
   private Hangar hangar;
   private Ship ship;
   
   //constructor of this class
   //takes in the HangarInfoBot object that it is a part of
   public HangarInfoBotRight(HangarInfoBot hb){
      hangarInfoBot = hb;
      init();
   }
   
   //initializes the object (the rightmost section of the bottom half of the info panel)
   //adds the buy / sell, upgrade and swap ship buttons
   public void init(){
      hangarFrame = hangarInfoBot.getHangarFrame();
      player = hangarFrame.getPlayer();
      shipID = hangarFrame.getCurrentShipID();
      hangar = player.getHangar();
      ship = hangar.getShip(shipID);
      length = hangarInfoBot.getLength() / 3;
      height = hangarInfoBot.getHeight();
      lastPage = hangarFrame.getLastPage();
      
      setPreferredSize(new Dimension(length, height));
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      setOpaque(true);
      setBackground(Color.yellow);
      
      JButton sellButton, buyButton, backButton, swapButton;
      
      if(ship != null){
         sellButton = new JButton("Sell");
         sellButton.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent e){
                  player.sellShip(shipID);
               }
            });
         add(sellButton);
      } else {
         buyButton = new JButton("Buy");
         buyButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               player.buyShip(shipID);
            }
         });
         add(buyButton);
      }
      
      backButton = new JButton("Back");
      backButton.addActionListener(
         new ActionListener(){
            public void actionPerformed(ActionEvent e){
               hangarFrame.setVisible(false);
               lastPage.setVisible(true);
            }
         });
      
      
      add(backButton);
      
      swapButton = new JButton("Swap");
      swapButton.addActionListener(
         new ActionListener(){
            public void actionPerformed(ActionEvent e){
               hangarFrame.setVisible(false);
               ShipSwapFrame ssf = new ShipSwapFrame(player, hangarFrame);
            }
         });
      
      
      add(swapButton);
   }
   
}
