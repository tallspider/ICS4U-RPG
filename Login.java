import java.io.*;
public class Login{
   private static final File FILE_NAME = new File("logins.txt");
   private String username;
   private String password;
   
   public Login(String name, String pw ){
      username = name;
      password = pw;
      return this;
   }
   
   public static boolean checkSuccessful(){   
      try{
         BufferedReader in = new BufferedReadaer(new FileRedaer(FILE_NAME));
         String temp;
         while((temp = in.readLine()) != null){
            if(temp == username){
               temp = in.readLine();
               if(temp == password)
                  in.close();
               return true;
            }else{
               in.readLine();
            }
         }
         in.close();
         return false;      
      }
      catch(IOException e ){
         System.out.print("Problem with using file");
      }
   }
   
   public static void Register(){
      if( this.checkRegisterDetiels()){
         this.createNewAccount();
      }else{
         System.out.println("Username is already taken");
      }      
   }
   
   private static boolean checkRegisterDetails(){
      try{
         BufferedReader in = new BufferedReadaer(new FileRedaer(FILE_NAME));
         String temp;
         while((temp = in.readLine()) != null){
            if(temp == username){
               return false;
            }
            in.readLine();
         }
         in.close();
         return true;      
      }
      catch(IOException e ){
         System.out.print("Problem with using file");
      }  
   }
   
   private static void createNewAccount(){
   
   }
   
   public static String getUsername(){
      return username;
   }
   public static String getPassword(){
      return password;
   }
   
   public static void setUsername(String s){
      username = s;
   }
   public static void setPassword(String s){
      password = s;
   }
}