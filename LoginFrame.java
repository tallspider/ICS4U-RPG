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
            System.out.println("1");
            new MainScene(new Player(username));            
         }else{
            System.out.println("2");
				ErrorFrame.error.setText("Wrong username / password");
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
