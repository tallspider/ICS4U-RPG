import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ErrorFrame extends JFrame{
   public static JLabel error = new JLabel("Error");
   private int length;
   
   public ErrorFrame() {
	   super("Error");
	   length = error.getText().length();
      this.setLayout(new FlowLayout());
      this.setBounds(600,600,length*30,300);
      error.setFont(new Font("TimesRoman", Font.BOLD, 25));
      error.setPreferredSize(new Dimension(length*30,150));
      this.setVisible(true);
      //JLabel error = new JLabel("Error");
      //error.setFont(new Font("Serif",Font.BOLD,20));
      this.add(error);
      Button confirm = new Button("Confirm");
      confirm.setFont(new Font("TimesRoman", Font.BOLD, 20));
      confirm.setPreferredSize(new Dimension(150,50));
      this.add(confirm);
      confirm.addMouseListener(new ConfirmListener(this));
   }
   
   private class ConfirmListener extends MouseAdapter{
      ErrorFrame ef;
      
      public ConfirmListener(ErrorFrame eF){
         ef = eF;
      }
      public void mouseClicked(MouseEvent e){
         ef.setVisible(false); 		
         //errorFrame.repaint();
      }
   }
   
   public static void setText(String s) {
	   error = new JLabel(s,SwingConstants.CENTER);
   }
   
}
