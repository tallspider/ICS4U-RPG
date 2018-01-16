import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
//Display the ship information from fleet
public class InfoFrame extends JFrame{
   private Ship ship;
   private JLabel info;
   private FleetFrame ff;
   public InfoFrame(Ship userShip,FleetFrame ff){
      super(userShip.getName());
      this.ff = ff;
      ship = userShip;
      
   
      this.setBounds(600,600,800,300);
      this.setVisible(true);
      
      info = new JLabel(ship.toString());
      this.add(info);
      Button close = new Button("Close");
      this.addMouseListener(new  closeListener(this,ff));  
   }
   
   private class closeListener extends MouseAdapter{
      private InfoFrame iF;
      private FleetFrame ff;
      public closeListener(InfoFrame iF, FleetFrame ff){
         this.iF = iF;
         this.ff = ff;
      }
      public void mouseClicked(MouseEvent e){
         iF.setVisible(false);
         ff.setVisible(true);
      }
   }
}
