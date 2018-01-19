import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JComponent;
public class LeaderBoardFrame extends JFrame
{

   private JLabel wayOfSort = new JLabel("rank   Score   Player", SwingConstants.CENTER);
   private LeaderBoard ranking;
   private int num;
   private JLabel [] rank;
   private JLabel [] name;
   private JLabel [] score;
   private ComparisonPlayer [] nameSort;
   private ComparisonPlayer [] scoreSort;
   private static Button change1 = new Button("Switch to sort by name");
   private static Button change2 = new Button("Switch to sort by player"); 
   
   
   
   public LeaderBoardFrame(String name)
   {
      super("LeaderBoard");
   
      ranking = new LeaderBoard(name);
      scoreSort = ranking.getPlayersByScore();
      nameSort = ranking.getPlayersByName();
      num = nameSort.length;
      
      
      this.setLayout(new BorderLayout(200,50));
      this.add(wayOfSort,BorderLayout.NORTH);
      this.setResizable(false);
      this.setBounds(500,500,400,num*100+100);
      this.setVisible(true);
      
      for(int i = 0;i<num;i++)
      {
         rank[i] = new JLabel(Integer.toString(i),SwingConstants.CENTER);
         nameSort[i] = new JLabel(scoreSort[i].getUsername(),SwingConstants.CENTER);
      }
      
      Button change = new Button("Switch to sort by name");
      this.add(change,BorderLayout.SOUTH);
      change.addMouseListener(new SortScoreListener(this));
      
      this.addWindowListener
      (
         new WindowAdapter()
         {
            public void windowClosing(WindowEvent e)
            {
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
          //  rank[num] = new JLabel(i + "\n" +nameSort[i].getScore()+ "\n" + nameSort[i]. getUsername());
          //  lbf.add(rank[i]);
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
          //  rank[i] = new JLabel(i + "\n" +scoreSort[i].getScore()+ "\n" + scoreSort[i]. getUsername());
          // lbf.add(rank[i]);
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
