   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
    public class RPGDemo{
	 
	 	private static TextField loginField = new TextField(10);
		private static TextField passwordField = new TextField(10);  		  	
	 	private static Login user; 
       public static void main(String[]args){

			 MainFrame mainFrame =new MainFrame("RPG");
      	 
      
      
      }
       static class MainFrame extends JFrame{
          public MainFrame(String title){
            super(title);
            this.setBounds(500,500,200,300);
            this.setVisible(true);
            this.setLayout(new FlowLayout());
            JPanel unp = new JPanel();
            unp.setLayout(new FlowLayout());
            unp.setSize(100,50);
            unp.add(new JLabel("Username"));
				
            unp.add(loginField);
            this.add(unp);
            JPanel pwp = new JPanel();
            pwp.setSize(100,50);
            pwp.add(new JLabel("Password"));
            pwp.add(passwordField);
            this.add(pwp);
				Button LogInB = new Button("Login");
            this.add(LogInB);
            this.add(new Button("Password"));
				LogInB.addMouseListener(new TextListener ());
         }
      }
		
		   
       static class TextListener extends MouseAdapter{
          public void mouseClicked(MouseEvent e){
				String username = loginField.getText();
				String password = passwordField.getText();
				user = new Login(username,password);
         }    	
      }
   }