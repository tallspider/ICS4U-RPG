import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JComponent;

public class LeaderBoardFrame2 extends JFrame
{
   private static LeaderBoardFrame lf;
   private String un2;
   
   private JLabel wayOfSort2;
   private LeaderBoard ranking2;
   private static int num2;
   private static JLabel[] rank2;
   private static JLabel[] names2;
   private static JLabel[] score2;
   private static JPanel[] sortByScore2;
   private static JPanel[] sortByName2;
   //two different display method
   private static ComparisonPlayer[] nameSort2;
   private static ComparisonPlayer[] scoreSort2;
   //set the panel that contains all player's score informational panels
   private JPanel p2 = new JPanel();
   
   //constructor of the LeaderBoardFrame2 class
   public LeaderBoardFrame2(LeaderBoardFrame lf,String un2)
   {
      this.lf = lf;   
      this.setTitle("LeaderBoard");
      //set the handout of the center part
      wayOfSort2 = new JLabel("Rank   Score   Player", SwingConstants.CENTER);
      //set the font's kind and size
      wayOfSort2.setFont(new Font("TimesRoman", Font.BOLD, 15));
      ranking2 = new LeaderBoard(un2);
      scoreSort2 = ranking2.getPlayersByScore();
      nameSort2 = ranking2.getPlayersByName();
      num2 = nameSort2.length;
      //set the layout of the frame 
      //there are five pixels between the vertical components of the frame
      setLayout(new BorderLayout(0,5));
      //set the prefer size of the head
      wayOfSort2.setPreferredSize(new Dimension(400, 15));
      //set the head's place
      add(wayOfSort2,BorderLayout.NORTH);
      setResizable(false);
      setBounds(500,200,400,num2*30+150);
      setVisible(true);
      
      p2.setLayout(null);
      
      rank2 = new JLabel[num2];
      names2 = new JLabel[num2];
      score2 = new JLabel[num2];
      
      //assign values to the arrays
      for(int i = 0;i<num2;i++)
      {
         rank2[i] = new JLabel(" "+Integer.toString(i+1)+"       ");
         score2[i] = new JLabel(Integer.toString(nameSort2[i].getScore())+"        "); 
         names2[i] = new JLabel(nameSort2[i].getUsername()+"       ");
      }
      
      //set the length of the panel array
      sortByName2 = new JPanel[num2];
      
      //add each player's information into the sortByName2 panel
      //set the layout of each panel
      for(int i = 0; i<num2; i++)
      {      
         sortByName2[i] = new JPanel();
         sortByName2[i].add(rank2[i]);             
         sortByName2[i].add(score2[i]); 
         sortByName2[i].add(names2[i]);
         sortByName2[i].setLayout(new FlowLayout());
      }
      
      //add all players' information to p2
      for(int i = 0; i<num2; i++)
      {
         System.out.println(i+1);
         sortByName2[i].setBounds(10,10+i*35,400,30);
         
         //set the background color of the personal information 
         if (i%2==0)
         {
            sortByName2[i].setBackground(Color.RED);
         }
         else 
         {
            sortByName2[i].setBackground(Color.BLUE);
         }
         
         p2.add(sortByName2[i]);       
      }
      
      getContentPane().add("Center",p2); 
      p2.setVisible(true);
   
      //create the button to let player the change the sort way 
      Button change2 = new Button("Switch to sort by Score");
      change2.setFont(new Font("TimesRoman", Font.BOLD, 15));
      add(change2,BorderLayout.SOUTH);
      
      //check if the window was click by the player
      addWindowListener(new MyWindowMonitor2());
      
      //add a minotor to get the access information of the button 
      //when palyer click the button it will change the way of sort
      Monitor2 mo = new Monitor2(this);
      change2.addActionListener(mo);
   }
   
   //a class to close the window if player click the close button
   class MyWindowMonitor2 extends WindowAdapter
   {
      public void windowclosing(WindowEvent e)
      {
         setVisible(false);
         System.exit(0);
      }
   }
   
   //a monitor class to check the palyer's action of this button
   class Monitor2 implements ActionListener
   {
      private LeaderBoardFrame2 lf2;
      
      //constructor of the class
      //pass the LeaderBoardFrame2
      public Monitor2(LeaderBoardFrame2 lf)
      {
         this.lf2 = lf;
      }
      
      //set the way of sort by score unvisible
      //set the way of sort by name visible
      public void actionPerformed(ActionEvent e)
      {
         lf2.setVisible(false);
         lf.setVisible(true);
      }
      
   }
   
   
}
