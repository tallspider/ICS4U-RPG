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
         System.out.println("1");
      
         lF = lf;
         this.setResizable(false);
         this.setVisible(true);
         this.setBounds(500,500,400,300);
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
      
         JPanel rpn = new JPanel();//Re-enter password
         rpn.setSize(100,50);
         rpn.add(new JLabel("Re-enter password"));
         rpn.add(rpasswordField);
         this.add(rpn);
         Button rB = new Button("Register"); 
         this.add(rB);
         Button reB = new Button("Return");
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
            if( !passwordField.getText().equals(rpasswordField.getText())){
               ErrorFrame.error.setText("password does not match re-enter password");
               new ErrorFrame();
            }
            else{
               user = new Login( loginField.getText(), passwordField.getText());
               if(user.checkRegisterDetails()){
                  user.createNewAccount();
                  ErrorFrame.error.setText("Register sucess");
						new ErrorFrame();
                  rf.setVisible(false);
                  lF.setVisible(true);
               }
               else{
                  ErrorFrame.error.setText("Username alrady exist");
                  new ErrorFrame();
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
