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
      PortalListener pl = new PortalListener(this, player);
      b1.addMouseListener(pl);
   
      b2.setBounds(250, 300, 150, 70);
      panel.add(b2);		 
      b2.addMouseListener(new FleetListener(player.getFleet(),this));
      
      b3.setBounds(250, 400, 150, 70);
      panel.add(b3);		 
      b3.addMouseListener(new SaveListener(player));
   
      b4.setBounds(600, 200, 150, 70);
      panel.add(b4);
      b4.addMouseListener(new HangarListener(this, player));		 
   
      b5.setBounds(600, 300, 150, 70);
      panel.add(b5);		 
      b5.addMouseListener(new LeaderBoardListener(player.getUsername()));
      
      b6.setBounds(600, 400, 150, 70);
      panel.add(b6);		 
      b6.addMouseListener(new QuitListener(this));
     
      
   
      frame.add(panel);  
      frame.setSize(WINDOW_LENGTH, WINDOW_WIDTH);  
      frame.setLocationRelativeTo(null);  
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
      frame.setVisible(true); 
   }
 
 
 
   private class QuitListener  extends MouseAdapter
   {
      MainScene ms = null;
      public QuitListener(MainScene ms)
      {
         this.ms = ms;
      }
   	
      public void  mouseClicked(MouseEvent e)
      {
         ms.setVisible(false);
         System.exit(-1);
      }
   }
 
   private class SaveListener extends MouseAdapter{
      Player player;
      public SaveListener(Player player){
         this.player = player;
      }
   
      public void  mouseClicked(MouseEvent e){
         ErrorFrame.setText("Save success");
         player.save();
         new ErrorFrame();
      }
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
         HangarFrame hf = new HangarFrame(player);
         hf.setVisible(ms, true);
      }
   }
   
   private class PortalListener  extends MouseAdapter
   {

      
      public PortalListener(MainScene ms,Player player)
      {

      }
   	
      public void  mouseClicked(MouseEvent e)
      {
      // creates a new swing worker which cointains the code needed to start the game, this code is inside the doInBackground class of the swing worker so that the code is run in a background thread (when the swingworker is executed)
      // if the code is not run in a bakcground thread, the events from the GameboardGUI will conflict with this (MainScene)'s events causing deadlock and a blank gui, this is because both event handlers are run on the same thread the "Event Dispatch Thread" 
      // this problem is circumvented by running the Gameboard (and thus the GameboardGUI) in a background thread, keeping the GameboardGUI's event handler out of the same thread as MainScene's event handler
         SwingWorker startGame = 
            new SwingWorker() {
            
            //overrides the do in background in swing worker (normally when you call it without overriding it it does "nothing" in the background)
            //it can't be a void method or else it won't override doInBackground
               protected Object doInBackground(){
               // the code to actually start the game
                 // make the hanger frame invisible, not sure how to do it   -David~
                  Gameboard gameBoard = new Gameboard(); 
                  gameBoard.startCombat(player);
                  return null;  
               }
            };
            
      // runs the code in startGame (most importantly the code in doInBackground)
         startGame.execute();
         
         // hotfix courtesy of david (and google, and stack overflow, and the internet)
      }
   }
   
   public void go(){
      Test test = new Test();
      test.main(null);
   }      
      
      
      
 
      
   private class LeaderBoardListener  extends MouseAdapter
   {
   	//MainScene ms = null;
      String un = null;
      public LeaderBoardListener(String un)
      {
      	//this.ms = ms;
         this.un = un;
      }
   	
      public void  mouseClicked(MouseEvent e)
      {
      	//ms.setVisible(false);
         new LeaderBoardFrame(un);
      }
   }
            
	    
	    
	    
	        
}  


