/*
Class Name: LoginFrame
Author: Chongpu Zhao
Date: January 10, 2018
Purpose:A graphic class use to present the login window.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class LoginFrame extends JFrame{
      private Player player;
	 	private static TextField loginField = new TextField(10);
		private static TextField passwordField = new TextField(10);  		  	
	 	private static Login user; 
	//	private static ErrorFrame errorFrame = new ErrorFrame();//make it private and do a accessor later

   public LoginFrame(){
      super("RPG");
      this.setResizable(false);
      this.setBounds(500,500,400,400);
      this.setVisible(true);
      this.setLayout(new FlowLayout());
      JPanel unp = new JPanel();// username Panel
      unp.setLayout(new FlowLayout());
      JLabel un = new JLabel("Username");
      unp.add(un);     
      unp.add(loginField);
      unp.setPreferredSize(new Dimension(400,75));
      unp.setFont(new Font("TimesRoman", Font.BOLD, 30));
      this.add(unp);
      
      JPanel pwp = new JPanel();//password Panel
      JLabel ps = new JLabel("Password");
      pwp.add(ps);
      pwp.add(passwordField);
      pwp.setPreferredSize(new Dimension(400,75));
      pwp.setFont(new Font("TimesRoman", Font.BOLD, 30));
      this.add(pwp);
      
      Button LogInB = new Button("Login"); 
      LogInB.setFont(new Font("TimesRoman", Font.BOLD, 15));
      LogInB.setPreferredSize(new Dimension(100,50));
      this.add(LogInB);
      Button PSB = new Button("Registe");
      PSB.setFont(new Font("TimesRoman", Font.BOLD, 15));
      PSB.setPreferredSize(new Dimension(100,50));
      this.add(PSB);
      LogInB.addMouseListener(new LoginListener (this));
      PSB.addMouseListener(new RegisterListenner (this));
      
      this.addWindowListener(new WindowAdapter(){
         public void windowClosing(WindowEvent e){
            setVisible(false);
            System.exit(-1);
         }
      });
   }
   
   private class LoginListener extends MouseAdapter{
      LoginFrame mf = null;
      public LoginListener(LoginFrame mf){
         this.mf = mf;
      }
      
      public void mouseClicked(MouseEvent e){
         String username = loginField.getText();
         String password = passwordField.getText();
         user = new Login(username,password);
         //errorFrame.setVisible(true);
         if(user.checkSuccessful()){
				dispose();
            new MainScene(new Player(username));            
         }else{
				ErrorFrame.setText("Wrong username / password");
            new ErrorFrame();
         }
      }    	
   }
   
  	private class RegisterListenner extends MouseAdapter{
      LoginFrame mf = null;
   
      public RegisterListenner(LoginFrame mF){
         this.mf = mF;
      }
      public void mouseClicked(MouseEvent e){
			mf.setVisible(false);
			new RegisterFrame(mf);
      }
   }
}
