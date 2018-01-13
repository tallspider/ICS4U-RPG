import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MainFrame extends JFrame{

	 	private static TextField loginField = new TextField(10);
		private static TextField passwordField = new TextField(10);  		  	
	 //	private static Login user; 
		public static ErrorFrame errorFrame = new ErrorFrame();//make it private and do a accessor later

   public MainFrame(){
      super("RPG");
      this.setResizable(false);
      this.setBounds(500,500,400,300);
      this.setVisible(true);
      this.setLayout(new FlowLayout());
      JPanel unp = new JPanel();// username Panel
      unp.setLayout(new FlowLayout());
      unp.setSize(100,50);
      unp.add(new JLabel("Username"));
   			
      unp.add(loginField);
      this.add(unp);
      JPanel pwp = new JPanel();//password Panel
      pwp.setSize(100,50);
      pwp.add(new JLabel("Password"));
      pwp.add(passwordField);
      this.add(pwp);
      Button LogInB = new Button("Login"); 
      this.add(LogInB);
      Button PSB = new Button("Registe");
      this.add(PSB);
      LogInB.addMouseListener(new LoginListener ());
      PSB.addMouseListener(new RegisterListenner ());
   
   }
   
   static class LoginListener extends MouseAdapter{
      public void mouseClicked(MouseEvent e){
         String username = loginField.getText();
         String password = passwordField.getText();
      //   user = new Login(username,password);
         errorFrame.setVisible(true);
      }    	
   }
   
   class RegisterListenner extends MouseAdapter{
      public void mouseClicked(MouseEvent e){
         String username = loginField.getText();
         String password = passwordField.getText();
      }
   }
}