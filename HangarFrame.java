//GUI for Hangar class

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HangarFrame extends JFrame{
   
   private Player player;
   private int currentShipID;
   private String imageFile;
   
   private Hangar hangar;
   private ImageIcon image;
   
   private ShipSideBar shipSideBar;
   private HangarInfoPanel hangarInfoPanel;
   
   public static final int WINDOW_HEIGHT = MainScene.WINDOW_WIDTH;
   public static final int WINDOW_LENGTH = MainScene.WINDOW_LENGTH;
   public static final int SHIP_SIDEBAR_LENGTH = WINDOW_LENGTH / 5;
   public static final int PAGE_SPLIT_HEIGHT = WINDOW_HEIGHT / 2 + 50;
   
   public HangarFrame(Player p, String ims){
      player = p;
      imageFile = ims;
      init();
   }
   
   public void init(){
      setSize(WINDOW_LENGTH, WINDOW_HEIGHT);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(new BorderLayout(0, 0));
      
      hangar = player.getHangar();
      image = new ImageIcon(imageFile);
      
      initShipSideBar();
      initHangarInfoPanel();
      
      add(BorderLayout.LINE_START, shipSideBar);
      add(BorderLayout.LINE_END, hangarInfoPanel);
      
      setVisible(true);
   }
   
   private void initShipSideBar(){
      shipSideBar = new ShipSideBar(this, player, SHIP_SIDEBAR_LENGTH, WINDOW_HEIGHT);
      
   }
   
   private void initHangarInfoPanel(){
      hangarInfoPanel = new HangarInfoPanel(this, player, currentShipID, imageFile, WINDOW_LENGTH - SHIP_SIDEBAR_LENGTH, WINDOW_HEIGHT, PAGE_SPLIT_HEIGHT);
   }
   
   public static void main(String[] args){
      HangarFrame hg = new HangarFrame(new Player("Annie"), "img.jpg");
   }
   
   public void updateShipInfoPanel(int id){
      currentShipID = id;
      //remove(hangarInfoPanel);
      initHangarInfoPanel();
      //add(BorderLayout.LINE_END, hangarInfoPanel);
   }
}

class ShipSideBar extends JPanel{
   
   private HangarFrame hangarFrame;
   private Player player;
   private int length;
   private int height;
   private int numButtons;
   
   private Hangar hangar;
   
   public ShipSideBar(HangarFrame hf, Player p, int len, int hei){
      hangarFrame = hf;
      player = p;
      numButtons = Hangar.MAX_SHIPS;
      init();
   }
   
   public void init(){
      setLayout(new GridLayout(numButtons, 0));
      setPreferredSize(new Dimension(length, height));
      
      hangar = player.getHangar();
      
      for(int at = 0; at < Hangar.MAX_SHIPS; at++){
         
         JButton button;
         if(hangar.getShip(at) != null)
            button = new JButton(hangar.getShip(at).getName());
         else
            button = new JButton("Empty Ship " + (at + 1));
         
         button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               hangarFrame.updateShipInfoPanel(at);
            }
         });
         
         add(button);
      }
   }
   
}

class HangarInfoPanel extends JPanel{
   
   private HangarFrame hangarFrame;
   private HangarInfoTop hangarInfoTop;
   private HangarInfoBot hangarInfoBot;
   
   private Player player;
   private int shipID;
   private String imageFile;
   private int length;
   private int height;
   private int pageSplitHeight;
   
   public HangarInfoPanel(HangarFrame hf, Player p, int sID, String imgf, int len, int hei, int psh){
      hangarFrame = hf;
      shipID = sID;
      imageFile = imgf;
      player = p;
      length = len;
      height = hei;
      pageSplitHeight = psh;
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
      hangarInfoTop = new HangarInfoTop(player, shipID, imageFile, length, height - pageSplitHeight);
   }
   
   public void initHangarInfoBot(){
      hangarInfoBot = new HangarInfoBot(hangarFrame, player, shipID, length, pageSplitHeight);
   }
}

class HangarInfoTop extends JPanel{
   
   private Player player;
   private int shipID;
   private int length;
   private int height;
   private String imageFile;
   private HangarInfoTopLeft hangarInfoTopLeft;
   private HangarInfoTopMid hangarInfoTopMid;
   private HangarInfoTopRight hangarInfoTopRight;
   
   public HangarInfoTop(Player p, int sID, String imgf, int len, int hei){
      player = p;
      shipID = sID;
      imageFile = imgf;
      length = len;
      height = hei;
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
      hangarInfoTopLeft = new HangarInfoTopLeft(player, shipID, length / 3, height);
   }
   
   public void initHangarInfoTopMid(){
      hangarInfoTopMid = new HangarInfoTopMid(player, shipID, length / 3, height);
   }
   
   public void initHangarInfoTopRight(){
      hangarInfoTopRight = new HangarInfoTopRight(player, shipID, imageFile, length / 3, height);
   }
}

class HangarInfoBot extends JPanel{
   
   private HangarFrame hangarFrame;
   private Player player;
   private int shipID;
   private int length;
   private int height;
   private HangarInfoBotLeft hangarInfoBotLeft;
   private HangarInfoBotMid hangarInfoBotMid;
   private HangarInfoBotRight hangarInfoBotRight;
   
   public HangarInfoBot(HangarFrame hf, Player p, int sID, int len, int hei){
      hangarFrame = hf;
      player = p;
      shipID = sID;
      length = len;
      height = hei;
      init();
   }
   
   public void init(){
      setPreferredSize(new Dimension(length, height));
      setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
      setOpaque(true);
      setBackground(Color.blue);
   }
   
   private void initHangarInfoBotLeft(){
      hangarInfoBotLeft = new HangarInfoBotLeft(player, length / 3, height);
   }
   
   private void initHangarInfoBotMid(){
      hangarInfoBotMid = new HangarInfoBotMid(player, shipID, length / 3, height);
   }
   
   private void initHangarInfoBotRight(){
      hangarInfoBotRight = new HangarInfoBotRight(hangarFrame, player, shipID, length / 3, height);
   }
}

class HangarInfoTopLeft extends JPanel{
   
   private Player player;
   private int shipID;
   private int length;
   private int height;
   
   private Hangar hangar;
   private Ship ship;
   
   
   public HangarInfoTopLeft(Player p, int sID, int len, int hei){
      player = p;
      shipID = sID;
      length = len;
      height = hei;
      
      hangar = player.getHangar();
      ship = hangar.getShip(shipID);
      
      init();
   }
   
   public void init(){
      setPreferredSize(new Dimension(length, height));
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      setOpaque(true);
      setBackground(Color.green);
      
      addLine("Travel range: " + ship.getTravelRange());
      addLine("Attack range: " + ship.getAttackRange());
      addLine("Firing speed: " + ship.getFiringSpeed());
      addLine("Sell for: " + ship.getSellPrice());
   }
   
   public void addLine(String s){
      add(new JLabel(s));
   }
}

class HangarInfoTopMid extends JPanel{
   
   private Player player;
   private int shipID;
   private int length;
   private int height;
   
   private Hangar hangar;
   private Ship ship;
   
   public HangarInfoTopMid(Player p, int sID, int len, int hei){
      player = p;
      shipID = sID;
      length = len;
      height = hei;
      
      hangar = player.getHangar();
      ship = hangar.getShip(shipID);
      
      init();
   }
   
   public void init(){
      setPreferredSize(new Dimension(length, height));
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      setOpaque(true);
      setBackground(Color.gray);
      
      JTextField entry = new JTextField(10); 
      JButton button = new JButton("Change Ship Name");
      
      button.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            System.out.println("Change Ship Name button clicked");
            ship.setName(entry.getText());
         }
      });
      
      add(entry);
      add(button);
      
   }
}

class HangarInfoTopRight extends JPanel{
   
   private Player player;
   private int shipID;
   private String imageFile;
   private int length;
   private int height;
   
   private Hangar hangar;
   private Ship ship;
   private ImageIcon image;
   
   public HangarInfoTopRight(Player p, int sID, String imgf, int len, int hei){
      player = p;
      shipID = sID;
      imageFile = imgf;
      length = len;
      height = hei;
      
      hangar = player.getHangar();
      ship = hangar.getShip(shipID);
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
   
   private Player player;
   private int length;
   private int height;
   
   public HangarInfoBotLeft(Player p, int len, int hei){
      player = p;
      length = len;
      height = hei;
      init();
   }
   
   public void init(){
      setPreferredSize(new Dimension(length, height));
      setLayout(new FlowLayout());
      setOpaque(true);
      setBackground(Color.orange);
      
      addLine("\tUpgrade: ");
      addLine("Travel Range: ");
      addLine("Attack Range: ");
      addLine("Firing Speed: ");
      addLine("Player Money: " + player.getNumCoins());
   }
   
   private void addLine(String s){
      add(new JLabel(s));
   }
}

class HangarInfoBotMid extends JPanel{
   
   private Player player;
   private int shipID;
   private int length;
   private int height;
   
   private Hangar hangar;
   private Ship ship;
   
   public HangarInfoBotMid(Player p, int sID, int len, int hei){
      player = p;
      shipID = sID;
      length = len;
      height = hei;
      
      hangar = player.getHangar();
      ship = hangar.getShip(shipID);
      
      init();
   }
   
   public void init(){
      setPreferredSize(new Dimension(length, height));
      setLayout(new FlowLayout());
      setOpaque(true);
      setBackground(Color.white);
      
      add(new JLabel("Cost: "));
      
      JLabel trLabel = new JLabel(ship.calcUpgradeCost(Ship.TR_Upgrade)+ "");
      JButton trButton = new JButton("Upgrade");
      trButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            player.upgradeShip(shipID, Ship.TR_Upgrade);
         }
      });
      
      JLabel arLabel = new JLabel(ship.calcUpgradeCost(Ship.AR_Upgrade)+ "");
      JButton arButton = new JButton("Upgrade");
      arButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            player.upgradeShip(shipID, Ship.AR_Upgrade);
         }
      });
      
      JLabel fsLabel = new JLabel(ship.calcUpgradeCost(Ship.FS_Upgrade)+ "");
      JButton fsButton = new JButton("Upgrade");
      fsButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            player.upgradeShip(shipID, Ship.FS_Upgrade);
         }
      });
      
      addLineButton(trLabel, trButton);
      addLineButton(arLabel, arButton);
      addLineButton(fsLabel, fsButton);
      add(new JLabel("Money After Sell: " + (player.getNumCoins() - ship.getSellPrice())));
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
   
   private HangarFrame hangarFrame;
   private Player player;
   private int shipID;
   private int length;
   private int height;
   
   private Hangar hangar;
   private Ship ship;
   
   public HangarInfoBotRight(HangarFrame hf, Player p, int sID, int len, int hei){
      hangarFrame = hf;
      player = p;
      shipID = sID;
      length = len;
      height = hei;
      
      hangar = player.getHangar();
      ship = hangar.getShip(shipID);
      
      init();
   }
   
   public void init(){
      setPreferredSize(new Dimension(length, height));
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      setOpaque(true);
      setBackground(Color.yellow);
      
      JButton sellButton = new JButton("Sell");
      sellButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            player.sellShip(shipID);
         }
      });
      
      JButton backButton = new JButton("Back");
      backButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            //set mainScene to visible, this to not visible
         }
      });
      
      
      
      add(sellButton);
      add(backButton);
   }
}