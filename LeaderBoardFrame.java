import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JComponent;
public class LeaderBoardFrame extends JFrame{

   private JLabel wayOfSort = new JLabel("rank/nScore\nPlayer");
   private LeaderBoard ranking;
   private int num;
   private ComparisonPlayer [] nameSort;
   private ComparisonPlayer [] scoreSort;
   private static JLabel[] rank;
   private static Button change1 = new Button("Switch to sort by name");
   private static Button change2 = new Button("Switch to sort by player"); 
   public LeaderBoardFrame(String name){
      super("LeaderBoard");
   
      ranking = new LeaderBoard(name);
      scoreSort = ranking.getPlayersByScore();
      nameSort = ranking.getPlayersByName();
      num = nameSort.length;
      
      this.setLayout(new FlowLayout());
      this.add(wayOfSort);
      this.setResizable(false);
      this.setBounds(500,500,400,num*50+100);
      this.setVisible(true);
      
      rank = new JLabel[num];     
      for (int i = 0;i<=num;i++){
         rank[i] = new JLabel(i + "\n" +scoreSort[i].getScore()+ "\n" + scoreSort[i]. getUsername());
         this.add(rank[i]);
      }
      
      Button change = new Button("Switch to sort by name");
      this.add(change);
      change.addMouseListener(new SortScoreListener(this));
      
      this.addWindowListener(
         new WindowAdapter(){
            public void windowClosing(WindowEvent e){
               setVisible(false);
               System.exit(-1);
            }
         });
   }
   
   class SortScoreListener extends MouseAdapter{  
      LeaderBoardFrame lbf = null;
      
      public SortScoreListener(LeaderBoardFrame lbf){
         this.lbf = lbf;
      }
      
      public void mouseClicked(MouseEvent e){
       lbf.removeAll();
         wayOfSort.setText("rank/nPlayer\nScore");
         
         for(int i =0; i<=num;i++){
            rank[num] = new JLabel(i + "\n" +nameSort[i].getScore()+ "\n" + nameSort[i]. getUsername());
            lbf.add(rank[i]);
         }
         
         Button change = new Button("Switch to sort by name");
         lbf.add(change);
         change.addMouseListener(new SortScoreListener(lbf));
         lbf.addWindowListener(
         
            new WindowAdapter(){
               public void windowClosing(WindowEvent e){
                  setVisible(false);
               }
            });
         lbf.repaint();
      
      }
     
   }  
   
   class SortNameListener extends MouseAdapter{
      LeaderBoardFrame lbf = null;
      
      public SortNameListener(LeaderBoardFrame lbf){
         this.lbf = lbf;
      }
      public void mouseClicked(MouseEvent e){
         lbf.removeAll();
         wayOfSort.setText("rank/nScore\nPlayer");
         
         for (int i = 0;i<=num;i++){
            rank[i] = new JLabel(i + "\n" +scoreSort[i].getScore()+ "\n" + scoreSort[i]. getUsername());
            lbf.add(rank[i]);
         }
         
         Button change = new Button("Switch to sort by name"); 
         lbf.add(change);
         change.addMouseListener(new SortNameListener(lbf));
         
         lbf.addWindowListener(
            new WindowAdapter(){
               public void windowClosing(WindowEvent e){
                  setVisible(false);
               }
            });
      }
   }     
}
