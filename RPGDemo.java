   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
    public class RPGDemo{
	 
	 	private static TextField loginField = new TextField(10);
		private static TextField passwordField = new TextField(10);  		  	
	 	private static Login user; 
		private static ErrorFrame errorFrame = new ErrorFrame();
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
		
		static class ErrorFrame extends JFrame{
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
			}
   	 static class ConfirmListener extends MouseAdapter{
		 	public void mouseClicked(MouseEvent e){
				errorFrame.setVisible(false);
				
				errorFrame.repaint();
			}
		 }
       static class TextListener extends MouseAdapter{
          public void mouseClicked(MouseEvent e){
				String username = loginField.getText();
				String password = passwordField.getText();
				user = new Login(username,password);
				errorFrame.setVisible(true);
         }    	
      }
   }
