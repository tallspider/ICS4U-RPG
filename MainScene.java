   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;

    public class MainScene extends Frame
   {
      boolean menu = true;
      boolean Portal = false;
      boolean Fleet = false;
      boolean Save = false;
      boolean Hangar = false;
      boolean Leaderboard = false;
      boolean Quit = false;
      ImageIcon[] boardPictures = new ImageIcon[10];   
      static final int WINDOW_WIDTH=700;
      static final int WINDOW_LENGTH=1000;
      private HangarFrame hangarFrame;

   
   
       public static void main(String arg[]) 
      {  
         MainScene mainScene = new MainScene();
         ImageIcon img = new ImageIcon("image/img1.png"); 
         JLabel backimg = new JLabel(img); //background 
         mainScene.backimg.setBounds(0, 0, WINDOW_LENGTH, WINDOW_WIDTH);
         mainScene.getLayeredPane().add(backimg, new Integer(Integer.MIN_VALUE));  
         ((JPanel)mainScene.getContentPane()).setOpaque(false); //make invisible  
      
      
      
      
      
         JFrame frame = new JFrame("Space RPG");  
         JPanel panel = new JPanel();  
         panel.setLayout(new FlowLayout());  
         JButton b1 = new JButton("Portal");  
         JButton b2 = new JButton("Fleet"); 
         JButton b3 = new JButton("Save");
         JButton b4 = new JButton("Hangar");  
         JButton b5 = new JButton("Leaderboard"); 
         JButton b6 = new JButton("Quit");  
      
         panel.setLayout(null);
         b1.setBounds(250, 200, 150, 70);
         panel.add(b1);		 
      
         b2.setBounds(250, 300, 150, 70);
         panel.add(b2);		 
      
         b3.setBounds(250, 400, 150, 70);
         panel.add(b3);		 
      
         b4.setBounds(600, 200, 150, 70);
         panel.add(b4);		 
      
         b5.setBounds(600, 300, 150, 70);
         panel.add(b5);		 
      
         b6.setBounds(600, 400, 150, 70);
         panel.add(b6);		 
        
        
         
      
         frame.add(panel);  
         frame.setSize(WINDOW_LENGTH, WINDOW_WIDTH);  
         frame.setLocationRelativeTo(null);  
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
         frame.setVisible(true);  
         
    	
      	
      }  
      
      
      public void actionPerformed(ActionEvent e) 
      {
   
         if(e.getSource()==b1)
         {
              Portal=true;    
      
         }
         else if(e.getSource()==b2)
         {
            Fleet=true;         
         
         }
         
         if(e.getSource()==b3)
         {
            Save=true;      
      
         }
         else if(e.getSource()==b4)
         {
            Hangar=true;
            hangarFrame.setVisible (Component lastPage, boolean Hangar);
         
         }
                  
         if(e.getSource()==b5)
         {
            Leaderboard=true;      
      
         }
         else if(e.getSource()==b6)
         {
            Quit=true;         
         
         }
                  
         
      }
    
   
   
   
   }  






