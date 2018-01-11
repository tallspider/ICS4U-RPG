import java.util.*;
public class Ship
{
      private String name;
      private int attackRange;
      private int travelRange;
      private int firingSpeed;
      private int upgradesLeft;
      private int AR_Upgrades;
      private int TR_Upgrade;
      private int FS_Upgrade;
      private boolean ownedByPlayer; 
      private boolean movedAlready; 
      private int timesAttacked;
      private static final int BASIC_COST = 500;
      private  int value;
      
       public Ship(String name, int attackRange, int travelRange , int firingSpeed ,int upgradesLeft ,int AR_Upgrades,int FS_Upgrade,int TR_Upgrade, boolean ownedByPlayer)
      {
         this.name = name;
         this.attackRange = attackRange;
         this.travelRange = travelRange;
         this.firingSpeed = firingSpeed;
         this.upgradesLeft = upgradesLeft;
         this.AR_Upgrades = AR_Upgrades;
         this.TR_Upgrade = TR_Upgrade;
         this.FS_Upgrade = FS_Upgrade;
         this.ownedByPlayer = ownedByPlayer;
      }
   	
       public Ship(String name)
      {
        this.name 
      }
      
   	public boolean upgrade(int AR_Upgrades)
   	{
   	  if(AR_Upgrades == 1)
   	  {
   	    if ()
          {
          
          }
          else if ()
          {
          
          }
          else
          {
          
          } 
   	  }
   	}
   }