import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainScene extends JFrame
{
   private Player player;
   ImageIcon[] boardPictures = new ImageIcon[10];   
   static final int WINDOW_WIDTH=700;
   static final int WINDOW_LENGTH=1000;
   private HangarFrame hangarFrame;
	//Game game;


   public MainScene (Player player) 
   {  
      this.player = player;
      ImageIcon img = new ImageIcon("img1.jpg"); 
      JLabel backimg = new JLabel(img); //background 
      backimg.setBounds(0, 0, WINDOW_LENGTH, WINDOW_WIDTH);
      getLayeredPane().add(backimg, new Integer(Integer.MIN_VALUE));  
      ((JPanel)this.getContentPane()).setOpaque(false); //make invisible  
     
   
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
      b2.addMouseListener(new FleetListener(player.getFleet(),this));
      
      b3.setBounds(250, 400, 150, 70);
      panel.add(b3);		 
   
      b4.setBounds(600, 200, 150, 70);
      panel.add(b4);
		b4.addMouseListener(new HangarListener(this, player));		 
   
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
 
 
 
 
 
 
 
 
   private class FleetListener extends MouseAdapter
   {
      private MainScene ms = null;
      private Fleet fleet = null;
      
      public FleetListener(Fleet f, MainScene m)
      {
         ms = m;
         fleet = f;
      } 
      

      public void mouseClicked(MouseEvent e)
      {
        // ms.setVisible(false);
         new FleetFrame(fleet,ms);
      }
   }






	private class HangarListener  extends MouseAdapter
	{
		MainScene ms = null;
		Player player = null;
		public HangarListener(MainScene ms,Player player)
		{
			this.ms = ms;
			this.player = player;
		}
		
		public void  mouseClicked(MouseEvent e)
		{
			ms.setVisible(false);
			new HangarFrame(player);
		}
	}
      
      
      
      
      
      
	private class LeaderboardListener  extends MouseAdapter
	{
		MainScene ms = null;
		Leaderboard l = null;
		public HangarListener(MainScene ms,Leaderboard l)
		{
			this.ms = ms;
			this.l = l;
		}
		
		public void  mouseClicked(MouseEvent e)
		{
			ms.setVisible(false);
			new LeaderboardFrame(l);
		}
	}
            
	    
	    
	    
	    
	    
	private class HangarListener  extends MouseAdapter
	{
		MainScene ms = null;
		Player player = null;
		public HangarListener(MainScene ms,Player player)
		{
			this.ms = ms;
			this.player = player;
		}
		
		public void  mouseClicked(MouseEvent e)
		{
			ms.setVisible(false);
			new HangarFrame(player);
		}
	}
            
	    
	    
	    
	    
	    
	private class HangarListener  extends MouseAdapter
	{
		MainScene ms = null;
		Player player = null;
		public HangarListener(MainScene ms,Player player)
		{
			this.ms = ms;
			this.player = player;
		}
		
		public void  mouseClicked(MouseEvent e)
		{
			ms.setVisible(false);
			new HangarFrame(player);
		}
	}
      
      
      
      
      
      
	private class HangarListener  extends MouseAdapter
	{
		MainScene ms = null;
		Player player = null;
		public HangarListener(MainScene ms,Player player)
		{
			this.ms = ms;
			this.player = player;
		}
		
		public void  mouseClicked(MouseEvent e)
		{
			ms.setVisible(false);
			new HangarFrame(player);
		}
	}
      
      
      
      
      
      
}  


