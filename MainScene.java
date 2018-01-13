   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;

    public class MainScene extends Frame
   {
      boolean menu = true;
      boolean fleet = false;
      boolean battle = false;
      boolean leaderBoard = false;
      boolean login = false;
      boolean save = false;
      ImageIcon[] boardPictures = new ImageIcon[10];   
   
   
   
       public static void main(String arg[]) 
      {  
      
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
         frame.setSize(1000, 700);  
         frame.setLocationRelativeTo(null);  
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
         frame.setVisible(true);  
      	
      	
      
      
      	
      	
      	
      	
      	
      }  
      
    
   
   
   
   }  






