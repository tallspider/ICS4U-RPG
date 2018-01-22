// Needed no more - 18/Jan   
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;


public class FleetFrame extends JFrame{
   private Fleet fleet;
   private int shipNum;
   private Button [] buttons;
   private Ship[] ships;
   private MainScene mainscene;

   public FleetFrame(Fleet fleet,  MainScene ms){
      super("Fleet");
      mainscene = ms;
      ms.setVisible(false);
      ms.addReopenListener(this);
      this.fleet = fleet;
   
      this.setVisible(true);
      this.setLayout(new FlowLayout());
   
      shipNum = fleet.getShipNum();
      this.setBounds(500,500,400,shipNum*65+300);	
      if(shipNum != 0){
         ships = new Ship [shipNum];
      
         buttons = new Button[shipNum];
         for(int i = 0;i<shipNum;i++){
            if(ships[i] != null){
               buttons[i] = new Button(ships[i].getName());
               buttons[i].addMouseListener(new ShipListener(ships[i], mainscene,this));
            }
         }
      
         for(int i = 0; i <= shipNum +1;i++){
            this.add(buttons[i]);
         }
      }
      else{
         JLabel noship = new JLabel("Currrently no ship in your fleet",SwingConstants.CENTER);
         noship.setFont(new Font("TimesRoman", Font.BOLD, 25));
         noship.setPreferredSize(new Dimension(400,150));
         add(noship);
      }
   	
   	
   	
      Button returnB = new Button("Return");
      returnB.addMouseListener(new returnListener (this, mainscene));
      returnB.setFont(new Font("TimesRoman", Font.BOLD, 15));
      returnB.setPreferredSize(new Dimension(100,50));
      add(returnB);
   	
      this.addWindowListener(
                new WindowAdapter(){
                   public void windowClosing(WindowEvent e){
                      dispose();
                   }
                });    
   }


   private class ShipListener extends MouseAdapter{
      private FleetFrame ff = null;
      private Ship ship = null;
      private MainScene mainscene = null;
      public ShipListener(Ship ship,MainScene mainscene,FleetFrame ff){
         this.ff = ff;
         this.mainscene = mainscene;
         this.ship = ship;
      }
   
      public void mouseClicked(MouseEvent e){
         new InfoFrame(ship,ff);
      }
   }

   private class returnListener extends MouseAdapter{
      private FleetFrame ff = null;
      private MainScene mf = null;
   
      public returnListener(FleetFrame ff, MainScene mf){
         this.ff = ff;
         this.mf = mf;
      }
      public void mouseClicked(MouseEvent e){
         Window window = KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow();
         WindowEvent windowClosing = new WindowEvent(window, WindowEvent.WINDOW_CLOSING);
         window.dispatchEvent(windowClosing);
      }
   }
}
