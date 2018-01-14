//GUI for Hangar class

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HangarFrame extends JFrame{
   
   private Hangar hangar;
   private Player player;
   private ShipSideBar shipSideBar;
   private HangarInfoPanel hangarInfoPanel;
   private int currentShipID;
   private ImageIcon image;
   private String imageFile;
   public static final int WINDOW_HEIGHT = MainScene.WINDOW_WIDTH;
   public static final int WINDOW_LENGTH = MainScene.WINDOW_LENGTH;
   public static final int SHIP_SIDEBAR_LENGTH = WINDOW_LENGTH / 5;
   public static final int PAGE_SPLIT_HEIGHT = WINDOW_HEIGHT / 2 + 50;
   
   public HangarFrame(Hangar h, Player p, String ims){
      hangar = h;
      player = p;
      currentShipID = 0;
      imageFile = ims;
      init();
   }
   
   public void init(){
      setSize(WINDOW_LENGTH, WINDOW_HEIGHT);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(new BorderLayout(0, 0));
      
      image = new ImageIcon(imageFile);
      
      initShipSideBar();
      initHangarInfoPanel();
      
      add(BorderLayout.LINE_START, shipSideBar);
      add(BorderLayout.LINE_END, hangarInfoPanel);
      
      setVisible(true);
   }
   
   private void initShipSideBar(){
      shipSideBar = new ShipSideBar(Hangar.MAX_SHIPS, SHIP_SIDEBAR_LENGTH, WINDOW_HEIGHT);
      for(int at = 0; at < Hangar.MAX_SHIPS; at++){
         if(hangar.getShip(at) != null)
            shipSideBar.addButton(hangar.getShip(at).getName());
         else
            shipSideBar.addButton("Ship " + (at + 1));
      }
   }
   
   private void initHangarInfoPanel(){
      hangarInfoPanel = new HangarInfoPanel(hangar.getShip(currentShipID), image, player, WINDOW_LENGTH - SHIP_SIDEBAR_LENGTH, WINDOW_HEIGHT, PAGE_SPLIT_HEIGHT);
   }
   
   public static void main(String[] args){
      HangarFrame hg = new HangarFrame(new Hangar(), new Player("Annie"), "img.jpg");
   }
   
   public void updateShipInfoPanel(int id){
      currentShipID = id;
      //remove(hangarInfoPanel);
      initHangarInfoPanel();
      //add(BorderLayout.LINE_END, hangarInfoPanel);
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
   
   private HangarInfoTop hangarInfoTop;
   private HangarInfoBot hangarInfoBot;
   private Ship ship;
   private ImageIcon image;
   private Player player;
   private int length;
   private int height;
   private int pageSplitHeight;
   
   public HangarInfoPanel(Ship s, ImageIcon img, Player p, int len, int hei, int psh){
      ship = s;
      image = img;
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
      hangarInfoTop = new HangarInfoTop(ship, image, length, height - pageSplitHeight);
   }
   
   public void initHangarInfoBot(){
      hangarInfoBot = new HangarInfoBot(ship, player, length, pageSplitHeight);
   }
}

class HangarInfoTop extends JPanel{
   
   private Ship ship;
   private int length;
   private int height;
   private ImageIcon image;
   private HangarInfoTopLeft hangarInfoTopLeft;
   private HangarInfoTopMid hangarInfoTopMid;
   private HangarInfoTopRight hangarInfoTopRight;
   
   public HangarInfoTop(Ship s, ImageIcon im, int len, int hei){
      ship = s;
      length = len;
      height = hei;
      image = im;
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
      hangarInfoTopLeft = new HangarInfoTopLeft(ship, length / 3, height);
   }
   
   public void initHangarInfoTopMid(){
      hangarInfoTopMid = new HangarInfoTopMid(ship, length / 3, height);
   }
   
   public void initHangarInfoTopRight(){
      hangarInfoTopRight = new HangarInfoTopRight(ship, length / 3, height, image);
   }
}

class HangarInfoBot extends JPanel{
   
   private Ship ship;
   private Player player;
   private int length;
   private int height;
   private HangarInfoBotLeft hangarInfoBotLeft;
   private HangarInfoBotMid hangarInfoBotMid;
   private HangarInfoBotRight hangarInfoBotRight;
   
   public HangarInfoBot(Ship s, Player p, int len, int hei){
      ship = s;
      player = p;
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
      hangarInfoBotMid = new HangarInfoBotMid(player, length / 3, height);
   }
   
   private void initHangarInfoBotRight(){
      hangarInfoBotRight = new HangarInfoBotRight(length / 3, height);
   }
}

class HangarInfoTopLeft extends JPanel{
   
   private Ship ship;
   private int length;
   private int height;
   
   public HangarInfoTopLeft(Ship s, int len, int hei){
      ship = s;
      length = len;
      height = hei;
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
   
   private Ship ship;
   private int length;
   private int height;
   
   public HangarInfoTopMid(Ship s, int len, int hei){
      ship = s;
      length = len;
      height = hei;
      init();
   }
   
   public void init(){
      setPreferredSize(new Dimension(length, height));
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      setOpaque(true);
      setBackground(Color.gray);
      
      add(new TextField(10));
      add(new JButton("Change Ship Name"));
   }
}

class HangarInfoTopRight extends JPanel{
   
   private Ship ship;
   private int length;
   private int height;
   private ImageIcon image;
   
   public HangarInfoTopRight(Ship s, int len, int hei, ImageIcon im){
      ship = s;
      length = len;
      height = hei;
      image = im;
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
   }
}

class HangarInfoBotMid extends JPanel{
   
   private Player player;
   private int length;
   private int height;
   
   public HangarInfoBotMid(Player p, int len, int hei){
      player = p;
      length = len;
      height = hei;
      init();
   }
   
   public void init(){
      setPreferredSize(new Dimension(length, height));
      setLayout(new FlowLayout());
      setOpaque(true);
      setBackground(Color.white);
   }
}

class HangarInfoBotRight extends JPanel{
   
   private int length;
   private int height;
   
   public HangarInfoBotRight(int len, int hei){
      length = len;
      height = hei;
      init();
   }
   
   public void init(){
      setPreferredSize(new Dimension(length, height));
      setLayout(new FlowLayout());
      setOpaque(true);
      setBackground(Color.yellow);
   }
}