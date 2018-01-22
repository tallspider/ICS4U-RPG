import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JComponent;
public class LeaderBoardFrame extends JFrame{

   private JLabel wayOfSort;
   private LeaderBoard ranking;
   private static int num;
   private static JLabel[] rank;
   private static JLabel[] names;
   private static JLabel[] score;
   private static JPanel[] sortByScore;
   private static JPanel[] sortByName;
   // private static JPanel displaypart;
   private static ComparisonPlayer[] nameSort;
   private static ComparisonPlayer[] scoreSort;
   private static Button change1 = new Button("Switch to sort by name");
   private static Button change2 = new Button("Switch to sort by player"); 
   private JPanel p = new JPanel();
   private String leaderBoardname;
	

   
   public LeaderBoardFrame(String un, MainScene ms)
   {
      ms.addReopenListener(this);
      this.setTitle("LeaderBoard");
      this.leaderBoardname = un;
      wayOfSort = new JLabel("Rank   Player   Score", SwingConstants.CENTER);
      wayOfSort.setFont(new Font("TimesRoman", Font.BOLD, 15));
      ranking = new LeaderBoard(leaderBoardname);
      scoreSort = ranking.getPlayersByScore();
      nameSort = ranking.getPlayersByName();
      num = nameSort.length;
      
      this.setLayout(new BorderLayout(0,5));
      wayOfSort.setPreferredSize(new Dimension(400, 15));
      // wayOfSort.setBackground(Color.YELLOW);
      
      this.add(wayOfSort,BorderLayout.NORTH);
      this.setResizable(false);
      this.setBounds(500,200,400,num*30+150);
      this.setVisible(true);
      
      p.setLayout(null);
      
      rank = new JLabel[num];
      names = new JLabel[num];
      score = new JLabel[num];
      
      
      for(int i = 0;i<num;i++)
      {
         rank[i] = new JLabel(" "+Integer.toString(i+1)+"       ");
         names[i] = new JLabel(scoreSort[i].getUsername()+"       ");
         score[i] = new JLabel(Integer.toString(scoreSort[i].getScore())+"        "); 
      }
      
      sortByScore = new JPanel[num];
      
      for(int i = 0; i<num; i++)
      {      
         sortByScore[i] = new JPanel();
         sortByScore[i].add(rank[i]);    
         sortByScore[i].add(names[i]);            
         sortByScore[i].add(score[i]); 
         sortByScore[i].setLayout(new FlowLayout());
      }
      
      
      for(int i = 0; i<num; i++)
      {
         System.out.println(i+1);
         sortByScore[i].setBounds(10,10+i*35,400,30);
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
   
       
      Button change = new Button("Switch to sort by name");
      change.setFont(new Font("TimesRoman", Font.BOLD, 15));
      add(change,BorderLayout.SOUTH);
      addWindowListener(new MyWindowMonitor());
      
      Monitor mo = new Monitor(this,leaderBoardname);
      change.addActionListener(mo);
      //change.addMouseListener(new SortScoreListener(this));
      
          
   }
   
   class MyWindowMonitor extends WindowAdapter
   {
      public void windowclosing(WindowEvent e)
      {
         setVisible(false);
      }
   }
   
   class Monitor implements ActionListener
   {
      private LeaderBoardFrame lf = null;
      private String un2 = leaderBoardname;
      
      public Monitor(LeaderBoardFrame fr,String leaderBoardname)
      {
         this.lf = fr;
         this.un2 = leaderBoardname;
      }
      
      public void actionPerformed(ActionEvent e)
      {
         lf.setVisible(false);
         new LeaderBoardFrame2(lf,un2);
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
   
   
   
   // class SortScoreListener extends MouseAdapter
   // {  
      // LeaderBoardFrame lbf = null;
   //    
      // public SortScoreListener(LeaderBoardFrame lbf)
      // {
         // this.lbf = lbf;
      // }
   //    
      // public void mouseClicked(MouseEvent e)
      // {
         // lbf.removeAll();
         // wayOfSort.setText("rank/nPlayer\nScore");
      //    
         // for(int i =0; i<nameSort.length;i++)
         // {
            // name[i] = new JLabel(nameSort[i].getUsername());
            // score[i] = new JLabel(Integer.toString(nameSort[i].getScore())); 
         // }
      //    
         // Button change = new Button("Switch to sort by name");
         // lbf.add(change);
         // change.addMouseListener(new SortScoreListener(lbf));
         // lbf.addWindowListener
            // (
            // 
            // new WindowAdapter()
            // {
               // public void windowClosing(WindowEvent e)
               // {
                  // setVisible(false);
               // }
            // });
         // lbf.repaint();
      // 
      // }
   //   
   // }  
//    
   // class SortNameListener extends MouseAdapter
   // {
      // LeaderBoardFrame lbf = null;
   //    
      // public SortNameListener(LeaderBoardFrame lbf)
      // {
         // this.lbf = lbf;
      // }
      // public void mouseClicked(MouseEvent e)
      // {
         // lbf.removeAll();
         // wayOfSort.setText("rank/tScore\tPlayer");
      //    
         // for (int i = 0;i<num;i++)
         // {
            // name[i] = new JLabel(scoreSort[i].getUsername());
            // score[i] = new JLabel(Integer.toString(scoreSort[i].getScore())); 
         // }
      //    
         // Button change = new Button("Switch to sort by name"); 
         // lbf.add(change);
         // change.addMouseListener(new SortNameListener(lbf));
      //    
         // lbf.addWindowListener
            // (
            // new WindowAdapter()
            // {
               // public void windowClosing(WindowEvent e)
               // {
                  // dispose();
               // }
            // });
      // }
   // }    
//    
//    
}
