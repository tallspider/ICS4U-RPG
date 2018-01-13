import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ErrorFrame extends JFrame{
   public ErrorFrame() {
      this.setLayout(new FlowLayout());
      this.setBounds(600,600,150,100);
      this.setVisible(false);
      JLabel error = new JLabel("Error");
   		//	error.setFont(new Font("Serif",Font.BOLD,20));
      this.add(error);
      Button confirm = new Button("Confirm");
      this.add(confirm);
      confirm.addMouseListener(new ConfirmListener());
   }
   static class ConfirmListener extends MouseAdapter{
      public void mouseClicked(MouseEvent e){
         MainFrame.errorFrame.setVisible(false);      		
         //errorFrame.repaint();
      }
   }
   
}
