import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;


public class FleetFrame extends JFrame{
   private Fleet fleet;
   private int shipNum;
   private Button buttons;
   private Ship[] ships;
   private MainScene mainscene;
   
   public FleetFrame(Fleet fleet,   MainScene ms){
      super("Fleet");
      mainscene = ms;
      ms.setVisible(false);
      this.fleet = fleet;
      this.setBounds(500,500,400,600);
      this.setVisble(true);
      this.setLayout(new FlowLayout());
      
      shipNum = fleet.getShipNum();
      ships = new Ship [shipNum];
      
      buttons = new Button[shipNum+1];
      for(int i = 0;i<=shipNum;i++){
         buttons[i] = new Button(ships[i].getName());
         buttons[i].addMouseListener(new ShipListener(button[i]));
      }
      
      
      buttons[shipNum+1] = new Button("Return to main scene");
      buttons[shipNum+1].addMouseListener(new returnListener (this, mainscene));
      for(int i = 0; i <= shipNum +1;i++){
         this.add(buttons[i]);
      }
   }
   
   
   private class ShipListener extends MouseAdapter{
      private Ship ship = null;
      private MainScene mainscene = null;
      public void ShipListener(Ship ship,MainScene mainscene){
         this.mainscene = mainscene;
         this.ship = ship;
      }
      
      public void mouseClicked(MouseEvent e){
         new infoFrame(ship);
      }
   }
   
   private class returnListener extends MouseAdapter{
      private FleetFrame ff = null;
      private MainFrame mf = null;
      
      public returnListener(FleetFrame ff, MainFrame mf){
         this.ff = ff;
         this.mf = mf;
      }
      public void mouseClicked(MouseEvent e){
         ff.setVisble(false);
         mf.setVisble(true);
      }
   }
}