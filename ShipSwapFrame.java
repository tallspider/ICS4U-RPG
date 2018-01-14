//swapping ships frame

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
   
   public ShipSwapFrame(Player p, Component c){
      player = p;
      lastPage = c;
      init();
   }
   
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
   
   private void initHangarShipsPanel(){
      hangarShipsPanel = new HangarShipsPanel(this);
   }
   
   private void initFleetShipsPanel(){
      fleetShipsPanel = new FleetShipsPanel(this);
   }
   
   private void initInfoContainerPanel(){
      infoContainerPanel = new InfoContainerPanel(this); 
   }
   
   public int getShip1ID(){
      return ship1ID;
   }
   
   public int getShip2ID(){
      return ship2ID;
   }
   
   public int getShip1Hold(){
      return ship1Hold;
   }
   
   public int getShip2Hold(){
      return ship2Hold;
   }
   
   public Ship getShip1(){
      return ship1;
   }
   
   public Ship getShip2(){
      return ship2;
   }
   
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
   
   public void emptyShip1(){
      setShip1All(-1, -1);
   }
   
   public void emptyShip2(){
      setShip2All(-1, -1);
   }
   
   public void showErrorMessage(String q, String t){
      JOptionPane.showMessageDialog(null, q, t, JOptionPane.ERROR_MESSAGE);
   }
   
   public Player getPlayer(){
      return player;
   }
   
   public Component getLastPage(){
      return lastPage;
   }
   
   public Hangar getHangar(){
      return hangar;
   }
   
   public Fleet getFleet(){
      return fleet;
   }
}

class HangarShipsPanel extends JPanel{
   private int length;
   private int height;
   
   private ShipSwapFrame frame;
   private Player player;
   
   private Hangar hangar;
   
   public HangarShipsPanel(ShipSwapFrame f){
      frame = f;
      init();
   }
   
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
               if(frame.getShip1ID() == -1){
                  frame.setShip1All(Player.HANGAR, AT);
               } else if(frame.getShip2ID() == -1){
                  frame.setShip2All(Player.HANGAR, AT);
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
      
      
   }
   
   public void displayEmpty(){
   
   }
   
   public void displayShip(){
      
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
      
      
   }
   
   public void displayEmpty(){
   
   }
   
   public void displayShip(){
      
   }
}