public class Location{
   private int x;
   private int y;
   
   public Location(int x, int y){
      this.x = x;
      this.y = y;
   }
   
   public Location(){
   }
   
   public static int compareLocations(Location a, Location b){
      return (Math.abs(a.getX() - b.getX()) + Math.abs(a.getY()-b.getY()));
   }
   public int getX(){
      return x;
   }
   public int getY(){
      return y;
   }
   public void setX(int x){
      this.x = x;
   }
   public void setY(int y){
      this.y = y;
   }
}
