import java.util.*;
public class Ship extends Entity
{
   private String name;
   private int attackRange;
   private int travelRange;
   private int firingSpeed;
   private int upgradesLeft;
   static final int AR_Upgrade;
   static final int TR_Upgrade;
   static final int FS_Upgrade;
   private boolean ownedByPlayer; 
   private boolean movedAlready; 
   private int timesAttacked;
   private static final int BASIC_COST = 500;
   private int value;
      
   public Ship(String name, int attackRange, int travelRange , int firingSpeed ,int upgradesLeft , boolean ownedByPlayer)
   {
      this.name = name;
      this.attackRange = attackRange;
      this.travelRange = travelRange;
      this.firingSpeed = firingSpeed;
      this.upgradesLeft = upgradesLeft;
      this.ownedByPlayer = ownedByPlayer;
   }
   	
   public Ship(String name)
   {
      this.name = name; 
   }
      
   public void upgrade(int num)
   {
      if(num == AR_Upgrade)
      {
         Player.numCoins =Player.numCoins - ((AR_Upgrade+1)*100);
         attackRange = AR_Upgrade +1;  
      }
      else if(num == TR_Upgrade)
      {
         Player.numCoins =Player.numCoins - ((TR_Upgrade+1)*100);
         travelRange = TR_Upgrade +1;
      }
      else
      {
         Player.numCoins =Player.numCoins - ((FS_Upgrade+1)*100);
         firingSpeed = FS_Upgrade +1;
      }
   }
   
   public String toString()
   {
     return "Name: "+name+"/nAttackRange: "+attackRange+ "/nTravelRange: "+travelRange+"/nFiringSpeed: "+firingSpeed+"/nUpgradesLeft: "+upgradesLeft+"/nOwnedByPlayer: "+ownedByPlayer+"/nMovedAlready: "+movedAlready+"/nTimesAttacked: "+timesAttacked+"/nValue: "+value;
   }
   
   public void setName(String name)
   {
      this.name = name;
   }
   
   public String getName()
   {
      return this.name;
   }
   
   public void setAttackRange(int attackRange)
   {
      this.attackRange = attackRange;
   }
   
   public int getAttackRange()
   {
      return this.attackRange;
   }  
   
   public void setTravelRange(int travelRange)
   {
      this.travelRange = travelRange;
   }
   
   public int getTravelRange()
   {
      return this.travelRange;
   } 
   
   public void setFiringSpeed(int firingSpeed)
   {
      this.firingSpeed = firingSpeed;
   }
   
   public int getFiringSpeed()
   {
      return this.firingSpeed;
   } 
   
   public void setUpgradesLeft(int upgradesLeft)
   {
      this.upgradesLeft = upgradesLeft;
   }
   
   public int getUpgradesLeft()
   {
      return this.upgradesLeft;
   } 
   
   public void setOwnedByPlayer(boolean ownedByPlayer)
   {
      this.ownedByPlayer = ownedByPlayer;
   }
   
   public boolean getOwnedByPlayer()
   {
      return this.ownedByPlayer;
   } 
   
   public void setMovedAlready(boolean movedAlready)
   {
      this.movedAlready = movedAlready;
   }
   
   public boolean getMovedAlready()
   {
      return this.movedAlready;
   } 
   
   public void setTimesAttacked(int timesAttacked)
   {
      this.timesAttacked = timesAttacked;
   }
   
   public int getTimesAttacked()
   {
      return this.timesAttacked;
   } 
   
   public void setValue(int value)
   {
      this.value = value;
   }
   
   public int getValue()
   {
      return this.value;
   }  
   
}