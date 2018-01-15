//GUI for Hangar class

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
   
   public HangarFrame(Player p){
      player = p;
      currentShipID = 0;
      init();
   }
   
   public void setVisible(Component c, boolean b){
      lastPage = c;
      init();
   }
   
   public void init(){
      setSize(WINDOW_LENGTH, WINDOW_HEIGHT);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(new BorderLayout(0, 0));
      
      hangar = player.getHangar();
      
      initShipSideBar();
      initHangarInfoPanel();
      
      add(BorderLayout.LINE_START, shipSideBar);
      add(BorderLayout.LINE_END, hangarInfoPanel);
      
      setVisible(true);
   }
   
   private void initShipSideBar(){
      shipSideBar = new ShipSideBar(this);
      
   }
   
   private void initHangarInfoPanel(){
      hangarInfoPanel = new HangarInfoPanel(this);
   }
   
   public void updateShipInfoPanel(int id){
      currentShipID = id;
      //remove(hangarInfoPanel);
      initHangarInfoPanel();
      //add(BorderLayout.LINE_END, hangarInfoPanel);
   }
   
   public boolean askQuestion(String ques, String title){
      return JOptionPane.showConfirmDialog(null, ques, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
   }
   
   public Component getLastPage(){
      return lastPage;
   }
   
   public Player getPlayer(){
      return player;
   }
   
   public int getCurrentShipID(){
      return currentShipID;
   }
}

class ShipSideBar extends JPanel{
   
   private HangarFrame hangarFrame;
   private Player player;
   private int length;
   private int height;
   private int numButtons;
   
   private Hangar hangar;
   
   public ShipSideBar(HangarFrame hf){
      hangarFrame = hf;
      init();
   }
   
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
   
   public HangarInfoPanel(HangarFrame hf){
      hangarFrame = hf;
      lastPage = hf.getLastPage();
      player = hf.getPlayer();
      hangar = player.getHangar();
      shipID = hf.getCurrentShipID();
      imageFile = hangar.getShip(shipID).getImageFile();
      length = HangarFrame.WINDOW_LENGTH - HangarFrame.SHIP_SIDEBAR_LENGTH;
      height = HangarFrame.WINDOW_HEIGHT;
      pageSplitHeight = HangarFrame.PAGE_SPLIT_HEIGHT;
      init();
   }
   
   public void init(){
      setPreferredSize(new Dimension(length, height));
      setLayout(new BorderLayout(0, 0));
      setOpaque(true);
      setBackground(Color.red);
      
      initHangarInfoTop();
      initHangarInfoBot();
      
      add(hangarInfoTop, BorderLayout.PAGE_START);
      add(hangarInfoBot, BorderLayout.PAGE_END);
   }
   
   public void initHangarInfoTop(){
      hangarInfoTop = new HangarInfoTop(this);
   }
   
   public void initHangarInfoBot(){
      hangarInfoBot = new HangarInfoBot(this);
   }
   
   public HangarFrame getHangarFrame(){
      return hangarFrame;
   }
   
   public int getLength(){
      return length;
   }
   
   public int getHeight(){
      return height;
   }
}

class HangarInfoTop extends JPanel{
   
   private Player player;
   private int shipID;
   private int length;
   private int height;
   private String imageFile;
   private HangarFrame hangarFrame;
   private HangarInfoTopLeft hangarInfoTopLeft;
   private HangarInfoTopMid hangarInfoTopMid;
   private HangarInfoTopRight hangarInfoTopRight;
   
   public HangarInfoTop(HangarInfoPanel hp){
      hangarFrame = hp.getHangarFrame();
      player = hangarFrame.getPlayer();
      shipID = hangarFrame.getCurrentShipID();
      imageFile = player.getHangar().getShip(shipID).getImageFile();
      length = hp.getLength();
      height = hp.getHeight() - HangarFrame.PAGE_SPLIT_HEIGHT;
      init();
   }
   
   public void init(){
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
   
   public void initHangarInfoTopLeft(){
      hangarInfoTopLeft = new HangarInfoTopLeft(this);
   }
   
   public void initHangarInfoTopMid(){
      hangarInfoTopMid = new HangarInfoTopMid(this);
   }
   
   public void initHangarInfoTopRight(){
      hangarInfoTopRight = new HangarInfoTopRight(this);
   }
   
   public HangarFrame getHangarFrame(){
      return hangarFrame;
   }
   
   public int getLength(){
      return length;
   }
   
   public int getHeight(){
      return height;
   }
}

class HangarInfoBot extends JPanel{
   
   private HangarFrame hangarFrame;
   private Component lastPage;
   private Player player;
   private int shipID;
   private int length;
   private int height;
   private HangarInfoBotLeft hangarInfoBotLeft;
   private HangarInfoBotMid hangarInfoBotMid;
   private HangarInfoBotRight hangarInfoBotRight;
   
   public HangarInfoBot(HangarInfoPanel hp){
      hangarFrame = hp.getHangarFrame();
      lastPage = hangarFrame.getLastPage();
      player = hangarFrame.getPlayer();
      shipID = hangarFrame.getCurrentShipID();
      length = HangarFrame.WINDOW_LENGTH - HangarFrame.SHIP_SIDEBAR_LENGTH;
      height = HangarFrame.WINDOW_HEIGHT;
      init();
   }
   
   public void init(){
      setPreferredSize(new Dimension(length, height));
      setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
      setOpaque(true);
      setBackground(Color.blue);
   }
   
   private void initHangarInfoBotLeft(){
      hangarInfoBotLeft = new HangarInfoBotLeft(this);
   }
   
   private void initHangarInfoBotMid(){
      hangarInfoBotMid = new HangarInfoBotMid(this);
   }
   
   private void initHangarInfoBotRight(){
      hangarInfoBotRight = new HangarInfoBotRight(this);
   }
   
   public HangarFrame getHangarFrame(){
      return hangarFrame;
   }
   
   public int getLength(){
      return length;
   }
   
   public int getHeight(){
      return height;
   }
}

class HangarInfoTopLeft extends JPanel{
   
   private HangarInfoTop hangarInfoTop;
   private HangarFrame hangarFrame;
   private Player player;
   private int shipID;
   private int length;
   private int height;
   
   private Hangar hangar;
   private Ship ship;
   
   
   public HangarInfoTopLeft(HangarInfoTop ht){
      hangarInfoTop = ht;
      
      if(ship == null) ship = new Ship("", Player.NO_PIC, Ship.BASIC_STAT, Ship.BASIC_STAT, Ship.BASIC_STAT, Ship.INIT_UPGRADES, Ship.BASIC_COST, false);
      
      init();
   }
   
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
   
   public void addLine(String s){
      add(new JLabel(s));
   }
}

class HangarInfoTopMid extends JPanel{
   
   private HangarInfoTop hangarInfoTop;
   
   private HangarFrame hangarFrame;
   private Player player;
   private int shipID;
   private int length;
   private int height;
   
   private Hangar hangar;
   private Ship ship;
   
   public HangarInfoTopMid(HangarInfoTop ht){
      hangarInfoTop = ht;
      hangarFrame = ht.getHangarFrame();
      player = hangarFrame.getPlayer();
      shipID = hangarFrame.getCurrentShipID();
      length = ht.getLength() / 3;
      height = ht.getHeight();
      
      hangar = player.getHangar();
      ship = hangar.getShip(shipID);
      
      if(ship == null) 
         ship = new Ship("", Player.NO_PIC, Ship.BASIC_STAT, Ship.BASIC_STAT, Ship.BASIC_STAT, Ship.INIT_UPGRADES, Ship.BASIC_COST, false);
      
      init();
   }
   
   public void init(){
      setPreferredSize(new Dimension(length, height));
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      setOpaque(true);
      setBackground(Color.gray);
      
      JTextField entry;
      JButton button;
      
      if(ship.getOwnedByPlayer()){
         entry = new JTextField(10); 
         button = new JButton("Change Ship Name");
      
         button.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent e){
                  System.out.println("Change Ship Name button clicked");
                  ship.setName(entry.getText());
               }
            });
      
         add(entry);
         add(button);
      }
      
   }
}

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
   
   public HangarInfoTopRight(HangarInfoTop ht){
      hangarInfoTop = ht;
      hangarFrame = ht.getHangarFrame();
      player = hangarFrame.getPlayer();
      shipID = hangarFrame.getCurrentShipID();
      hangar = player.getHangar();
      ship = hangar.getShip(shipID);
      imageFile = ship.getImageFile();
      length = ht.getLength() / 3;
      height = ht.getHeight();
      
      image = new ImageIcon(imageFile);
      
      init();
   }
   
   public void init(){
      setPreferredSize(new Dimension(length, height));
      setLayout(new BorderLayout());
      setOpaque(true);
      setBackground(Color.pink);
      add(new JLabel(image), BorderLayout.CENTER);
   }
}

class HangarInfoBotLeft extends JPanel{
   private HangarInfoBot hangarInfoBot;
   private HangarFrame hangarFrame;
   private Player player;
   private int shipID;
   private int length;
   private int height;
   
   private Hangar hangar;
   private Ship ship;
   
   public HangarInfoBotLeft(HangarInfoBot hb){
      hangarInfoBot = hb;
      hangarFrame = hb.getHangarFrame();
      player = hangarFrame.getPlayer();
      shipID = hangarFrame.getCurrentShipID();
      hangar = player.getHangar();
      ship = hangar.getShip(shipID);
      length = hb.getLength() / 3;
      height = hb.getHeight();
      
      init();
   }
   
   public void init(){
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
   
   private void addLine(String s){
      add(new JLabel(s));
   }
}

class HangarInfoBotMid extends JPanel{
   
   private HangarInfoBot hangarInfoBot;
   private HangarFrame hangarFrame;
   private Player player;
   private int shipID;
   private int length;
   private int height;
   
   private Hangar hangar;
   private Ship ship;
   
   public HangarInfoBotMid(HangarInfoBot hb){
      hangarInfoBot = hb;
      hangarFrame = hb.getHangarFrame();
      player = hangarFrame.getPlayer();
      shipID = hangarFrame.getCurrentShipID();
      hangar = player.getHangar();
      ship = hangar.getShip(shipID);
      length = hb.getLength() / 3;
      height = hb.getHeight();
      
      init();
   }
   
   public void init(){
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
   
   private void addLineButton(JLabel label, JButton button){
      JPanel temp = new JPanel();
      temp.setPreferredSize(new Dimension(length, height / 5));
      label.setPreferredSize(new Dimension(length / 2, height / 5));
      button.setPreferredSize(new Dimension(length / 2, height / 5));
      temp.add(label);
      temp.add(button);
   }
}

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
   
   public HangarInfoBotRight(HangarInfoBot hb){
      hangarInfoBot = hb;
      hangarFrame = hb.getHangarFrame();
      player = hangarFrame.getPlayer();
      shipID = hangarFrame.getCurrentShipID();
      hangar = player.getHangar();
      ship = hangar.getShip(shipID);
      length = hb.getLength() / 3;
      height = hb.getHeight();
      lastPage = hangarFrame.getLastPage();
      init();
   }
   
   public void init(){
      setPreferredSize(new Dimension(length, height));
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      setOpaque(true);
      setBackground(Color.yellow);
      
      JButton sellButton, buyButton, backButton;
      
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
               lastPage.setVisible(true);
               hangarFrame.setVisible(false);
            }
         });
      
      
      add(backButton);
   }
   
}
