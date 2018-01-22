import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterFrame extends JFrame{
   private static LoginFrame lF;
   private static TextField loginField = new TextField(10);
   private static TextField passwordField = new TextField(10);  
   private static TextField rpasswordField = new TextField(10);		
   public RegisterFrame(LoginFrame lf){
      super("Register");
      
      lF = lf;
      this.setResizable(false);
      this.setVisible(true);
      this.setBounds(500,500,400,300);
      this.setLayout(new FlowLayout());
      JPanel unp = new JPanel();// username Panel
      unp.setLayout(new FlowLayout());
      unp.setPreferredSize(new Dimension(400,65));
      unp.setFont(new Font("TimesRoman", Font.BOLD, 15));
      unp.add(new JLabel("Username         "));
      		
      unp.add(loginField);
      this.add(unp);
      JPanel pwp = new JPanel();//password Panel
      pwp.setPreferredSize(new Dimension(400,65));
      pwp.setFont(new Font("TimesRoman", Font.BOLD, 15));
      pwp.add(new JLabel("Password         "));
      pwp.add(passwordField);
      this.add(pwp);
      
      JPanel rpn = new JPanel();//Re-enter password
      rpn.setPreferredSize(new Dimension(400,65));
      rpn.setFont(new Font("TimesRoman", Font.BOLD, 15));
      rpn.add(new JLabel("Re-enter password"));
      rpn.add(rpasswordField);
      this.add(rpn);
      Button rB = new Button("Register"); 
      rB.setFont(new Font("TimesRoman", Font.BOLD, 15)); 
      this.add(rB);
      Button reB = new Button("Return");
      reB.setFont(new Font("TimesRoman", Font.BOLD, 15)); 
      this.add(reB);
      rB.addMouseListener(new RegisterListener (this));
      reB.addMouseListener(new ReturnListener (this));
      
      this.addWindowListener(
                new WindowAdapter(){
                   public void windowClosing(WindowEvent e){
                      setVisible(false);
                      System.exit(-1);
                   }
                }); 
      	
      
   }
   
   private  class RegisterListener extends MouseAdapter{
      private Login user;
      private RegisterFrame rf = null;
      public RegisterListener(RegisterFrame rf){
         this.rf = rf;
         
      }
      
      public void mouseClicked(MouseEvent e){
         if(passwordField.getText().equals("") ||loginField.getText().equals("")){
            ErrorFrame.setText("Username / password can't be null");
            new ErrorFrame(); 
         }else{
            if( !passwordField.getText().equals(rpasswordField.getText())){
               ErrorFrame.setText("password does not match re-enter password");
               new ErrorFrame();
            }
            else{
               if(loginField.getText().length() >=8){
                  ErrorFrame.setText("Username can't be more then 8 characters");
                  new ErrorFrame();
               }else{
                  user = new Login( loginField.getText(), passwordField.getText());
                  if(user.checkRegisterDetails()){
                     user.createNewAccount();
                     ErrorFrame.setText("Register sucess");
                     new ErrorFrame();
                     rf.setVisible(false);
                     lF.setVisible(true);
                  }
                  else{
                     ErrorFrame.setText("Username already exists");
                     new ErrorFrame();
                  }
               }
            }
         }
      }
   }
   
   private class ReturnListener extends MouseAdapter{
      RegisterFrame rf = null;
      public ReturnListener(RegisterFrame rf){
         this.rf = rf;
         
      }
      public void mouseClicked(MouseEvent e){
         rf.setVisible(false);
         lF.setVisible(true);
      }
   }
   
}
