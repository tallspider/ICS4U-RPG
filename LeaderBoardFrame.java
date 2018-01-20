import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JComponent;
public class LeaderBoardFrame extends JFrame{

   private JLabel wayOfSort;
   private LeaderBoard ranking;
   private static int num;
   private static JLabel[] rank;
   private static JLabel[] name;
   private static JLabel[] score;
   private static JPanel[] sortByName;
   // private static JPanel displaypart;
   private static ComparisonPlayer[] nameSort;
   private static ComparisonPlayer[] scoreSort;
   private static Button change1 = new Button("Switch to sort by name");
   private static Button change2 = new Button("Switch to sort by player"); 
   private JPanel p = new JPanel();
   
   public LeaderBoardFrame(String un)
   {
      this.setTitle("LeaderBoard");
      
      wayOfSort = new JLabel("Rank   Player   Score", SwingConstants.CENTER);
      wayOfSort.setFont(new Font("TimesRoman", Font.BOLD, 15));
      ranking = new LeaderBoard(un);
      scoreSort = ranking.getPlayersByScore();
      nameSort = ranking.getPlayersByName();
      num = nameSort.length;
      
      this.setLayout(new BorderLayout(0,5));
      wayOfSort.setPreferredSize(new Dimension(400, 15));
      // wayOfSort.setBackground(Color.YELLOW);
      
      this.add(wayOfSort,BorderLayout.NORTH);
      this.setResizable(false);
      this.setBounds(500,200,400,num*30+100);
      this.setVisible(true);
      
      p.setLayout(null);
      
      rank = new JLabel[num];
      name = new JLabel[num];
      score = new JLabel[num];
      
      
      for(int i = 0;i<num;i++)
      {
         rank[i] = new JLabel(" "+Integer.toString(i+1)+"       ");
         name[i] = new JLabel(scoreSort[i].getUsername()+"       ");
         score[i] = new JLabel(Integer.toString(scoreSort[i].getScore())+"        "); 
      }
      
      sortByName = new JPanel[num];
      
      for(int i = 0; i<num; i++)
      {      
         sortByName[i] = new JPanel();
         sortByName[i].add(rank[i]);    
         sortByName[i].add(name[i]);            
         sortByName[i].add(score[i]); 
         sortByName[i].setLayout(new FlowLayout());
      }
      
      
      for(int i = 0; i<num; i++)
      {
         System.out.println(i+1);
         sortByName[i].setBounds(10,10+i*35,400,30);
         if (i%2==0)
         {
            sortByName[i].setBackground(Color.RED);
         }
         else 
         {
            sortByName[i].setBackground(Color.BLUE);
         }
         
         p.add(sortByName[i]); 
            
      }
      
      getContentPane().add("Center",p); 
      p.setVisible(true);
   
       
      Button change = new Button("Switch to sort by name");
      change.setFont(new Font("TimesRoman", Font.BOLD, 15));
      this.add(change,BorderLayout.SOUTH);
      change.addMouseListener(new SortScoreListener(this));
      
      this.addWindowListener
         (
         new WindowAdapter()
         {
            public void windowClosing(WindowEvent e)
            {
               
               //setVisible(false);
               dispose();
            }
         }
         );
   }
   
   class SortScoreListener extends MouseAdapter
   {  
      LeaderBoardFrame lbf = null;
      
      public SortScoreListener(LeaderBoardFrame lbf)
      {
         this.lbf = lbf;
      }
      
      public void mouseClicked(MouseEvent e)
      {
         lbf.removeAll();
         wayOfSort.setText("rank/nPlayer\nScore");
         
         for(int i =0; i<nameSort.length;i++)
         {
            name[i] = new JLabel(nameSort[i].getUsername());
            score[i] = new JLabel(Integer.toString(nameSort[i].getScore())); 
         }
         
         Button change = new Button("Switch to sort by name");
         lbf.add(change);
         change.addMouseListener(new SortScoreListener(lbf));
         lbf.addWindowListener
            (
            
            new WindowAdapter()
            {
               public void windowClosing(WindowEvent e)
               {
                  setVisible(false);
               }
            });
         lbf.repaint();
      
      }
     
   }  
   
   class SortNameListener extends MouseAdapter
   {
      LeaderBoardFrame lbf = null;
      
      public SortNameListener(LeaderBoardFrame lbf)
      {
         this.lbf = lbf;
      }
      public void mouseClicked(MouseEvent e)
      {
         lbf.removeAll();
         wayOfSort.setText("rank/tScore\tPlayer");
         
         for (int i = 0;i<num;i++)
         {
            name[i] = new JLabel(scoreSort[i].getUsername());
            score[i] = new JLabel(Integer.toString(scoreSort[i].getScore())); 
         }
         
         Button change = new Button("Switch to sort by name"); 
         lbf.add(change);
         change.addMouseListener(new SortNameListener(lbf));
         
         lbf.addWindowListener
            (
            new WindowAdapter()
            {
               public void windowClosing(WindowEvent e)
               {
                  dispose();
               }
            });
      }
   }    
   
   
}
