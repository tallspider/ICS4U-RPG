//Class name: LeaderBoardFrame
//Author: Hans Long
//Date: Jan. 12, 2018
//School: A.Y.Jackson S.S.
//Purpose: the graphical representation of the LeaderBoardFrame class


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JComponent;

public class LeaderBoardFrame extends JFrame
{
   private JLabel wayOfSort;
   private LeaderBoard ranking;
   private static int num;
   private static JLabel[] rank;
   private static JLabel[] names;
   private static JLabel[] score;
   private static JPanel[] sortByScore;
   private static JPanel[] sortByName;
   //two different display method
   private static ComparisonPlayer[] nameSort;
   private static ComparisonPlayer[] scoreSort;
   private MainScene ms;
	
   //set the panel that contains all player's score informational panels
   private JPanel p = new JPanel();
   private String leaderBoardname;
	

    //constructor of the LeaderBoardFrame class
   public LeaderBoardFrame(String un, MainScene ms)
   {
      ms.addReopenListener(this);
      this.ms = ms;
      this.setTitle("LeaderBoard");
      //set the handout of the center part
      this.leaderBoardname = un;
      wayOfSort = new JLabel("Rank   Player   Score", SwingConstants.CENTER);
      //set the font's kind and size
      wayOfSort.setFont(new Font("TimesRoman", Font.BOLD, 15));
      ranking = new LeaderBoard(leaderBoardname);
      scoreSort = ranking.getPlayersByScore();
      nameSort = ranking.getPlayersByName();
      num = nameSort.length;
      
      //set the layout of the frame 
      //there are five pixels between the vertical components of the frame
      this.setLayout(new BorderLayout(0,5));
      //set the prefer size of the head
      wayOfSort.setPreferredSize(new Dimension(400, 15));
      
      //set the head's place
      this.add(wayOfSort,BorderLayout.NORTH);
      this.setResizable(false);
      this.setBounds(500,200,400,num*30+150);
      this.setVisible(true);
      
      //set the layout of the panel to null
      p.setLayout(null);
      
      //set the length of the arrays
      rank = new JLabel[num];
      names = new JLabel[num];
      score = new JLabel[num];
      
      //assign values to the arrays
      for(int i = 0;i<num;i++)
      {
         rank[i] = new JLabel(" "+Integer.toString(i+1)+"       ");
         names[i] = new JLabel(scoreSort[i].getUsername()+"       ");
         score[i] = new JLabel(Integer.toString(scoreSort[i].getScore())+"        "); 
      }
      
      //set the length of the panel array
      sortByScore = new JPanel[num];
      
      
      //add each player's information into the sortByName2 panel
      //set the layout of each panel
      for(int i = 0; i<num; i++)
      {      
         sortByScore[i] = new JPanel();
         sortByScore[i].add(rank[i]);    
         sortByScore[i].add(names[i]);            
         sortByScore[i].add(score[i]); 
         sortByScore[i].setLayout(new FlowLayout());
      }
       
      //add all players' information to p2
      for(int i = 0; i<num; i++)
      {
         
         sortByScore[i].setBounds(10,10+i*35,400,30);
	 
	 //set the background color of the personal information 
         if (i%2==0)
         {
            sortByScore[i].setBackground(Color.RED);
         }
         else 
         {
            sortByScore[i].setBackground(Color.BLUE);
         }
         
         p.add(sortByScore[i]); 
            
      }
      
      getContentPane().add("Center",p); 
      p.setVisible(true);
   
      
      //create the button to let player the change the sort way 
      Button change = new Button("Switch to sort by name");
      change.setFont(new Font("TimesRoman", Font.BOLD, 15));
      add(change,BorderLayout.SOUTH);
      //check if the window was click by the player
      addWindowListener(new MyWindowMonitor());
      
      
      //add a minotor to get the access information of the button 
      //when palyer click the button it will change the way of sort
      Monitor mo = new Monitor(this,leaderBoardname);
      change.addActionListener(mo);
      
      
   }
   
   
   //a class to close the window if player click the close button
   class MyWindowMonitor extends WindowAdapter
   {
      public void windowclosing(WindowEvent e)
      {
         setVisible(false);
         
      }
   }
   
   
   //a monitor class to check the palyer's action of this button
   class Monitor implements ActionListener
   {
      private LeaderBoardFrame lf = null;
      private String un2 = leaderBoardname;
      
      //constructor of the class
      //pass the LeaderBoardFrame2
      public Monitor(LeaderBoardFrame fr,String leaderBoardname)
      {
         this.lf = fr;
         this.un2 = leaderBoardname;
      }
      
      //set the way of sort by score unvisible
      //set the way of sort by name visible
      public void actionPerformed(ActionEvent e)
      {
         lf.setVisible(false);
         new LeaderBoardFrame2(lf,un2, ms);
      }
   }
   
   //accessor method for the name ranking array
   public JLabel getWayOfSort()
   {
      return wayOfSort;
   }
   
   public LeaderBoard getRanking()
   {
      return ranking;
   }
   
   public int getNum()
   {
      return num;
   }
   
   public JLabel[] getRank()
   {
      return rank;
   }

   public JLabel[] getNames()
   {
      return names;
   }
   
   public JLabel[] getScore()
   {
      return score;
   }
   
   public JPanel[] getSortByScore()
   {
      return sortByScore;
   }
   
   public JPanel[] getSortByName()
   {
      return sortByName;
   }
   
   public JPanel getP()
   {
      return p;
   }
   
   public ComparisonPlayer[] getNameSort()
   {
      return nameSort;
   }
   
   public ComparisonPlayer[] getScoreSort()
   {
      return scoreSort;
   }      
   
   
}
