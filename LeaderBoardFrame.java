import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
      
      rank = new JLabel[num];     
      for (int i = 0;i<=num;i++){
         rank[i] = new JLabel(i + "\n" +scoreSort[i].getScore()+ "\n" + scoreSort[i]. getUsername());
         this.add(rank[i]);
      }
      
      this.add(change1);
      change1.add(new SortScoreListener(this));
      this.addWindowListener(
         new WindowAdapter(){
            public void windowClosing(WindowEvent e){
               setVisible(false);
               System.exit(-1);
            }
         });
      this.setVisible(true);
   }
   
    class SortScoreListener extends MouseAdapter{
      LeaderBoardFrame lbf;
      public SortScoreListener(LeaderBoardFrame lbf){
         this.lbf = lbf;
         lbf.removeAll();
         wayOfSort.setText("rank/nPlayer\nScore");
         
         for(int i =0; i<=num;i++){
            rank[num] = new JLabel(i + "\n" +nameSort[i].getScore()+ "\n" + nameSort[i]. getUsername());
            lbf.add(rank[i]);
         }
         
         lbf.add(change2);
         change2.add(new SortNameListener(lbf));
         lbf.addWindowListener(
            new WindowAdapter(){
               public void windowClosing(WindowEvent e){
                  setVisible(false);
                  System.exit(-1);
               }
            });
         lbf.repaint();
      }
   
     
   }  
   
   class SortNameListener extends MouseAdapter{
      LeaderBoardFrame lbf;
      
      public SortNameListener(LeaderBoardFrame lbf){
         this.lbf = lbf;
         lbf.removeAll();
         wayOfSort.setText("rank/nScore\nPlayer");
         
         for (int i = 0;i<=num;i++){
            rank[i] = new JLabel(i + "\n" +scoreSort[i].getScore()+ "\n" + scoreSort[i]. getUsername());
            lbf.add(rank[i]);
         }
         
         lbf.add(change1);
         change2.add(new SortNameListener(lbf));
         lbf.addWindowListener(
            new WindowAdapter(){
               public void windowClosing(WindowEvent e){
                  setVisible(false);
                  System.exit(-1);
               }
            });
      }
   }     
}