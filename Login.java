import java.io.*;
public class Login{
   private static final File THE_FILE = new File("logins.txt");
   private String username;
   private String password;
   
   public Login(String name, String pw ){
      username = name;
      password = pw;
   }
   
   public boolean checkSuccessful(){   
      try{
         BufferedReader in = new BufferedReader(new FileReader(THE_FILE));
         String temp;
         while((temp = in.readLine()) != null){
            if(temp == username){
               temp = in.readLine();
               if(temp == password){
                  in.close();
                  return true;
               }
            }else{
               in.readLine();
            }
         }
         in.close();
      
      }
      catch(IOException e ){
         System.out.print("Problem with using file");
      }
      return false;   
   }
   
   public void Register(){
      if( this.checkRegisterDetails()){
         this.createNewAccount();
      }else{
         System.out.println("Username is already taken");
      }      
   }
   
   private boolean checkRegisterDetails(){
      try{
         BufferedReader in = new BufferedReader(new FileReader(THE_FILE));
         String temp;
         while((temp = in.readLine()) != null){
            if(temp == username){
               return false;
            }
            in.readLine();
         }
         in.close();
              
      }
      catch(IOException e ){
         System.out.print("Problem with using file");
      }
      return true;   
   }
   
   private void createNewAccount(){
      String [] information;
      try{
         BufferedReader check = new BufferedReader(new FileReader(THE_FILE));
         int count = 0;
         while(check.readLine() != null){
            count++;
         }
         check.close();
         information = new String [count+2];
         count = 0;
         BufferedReader read = new BufferedReader(new FileReader(THE_FILE));
         String temp;
         while((temp = read.readLine()) != null){
            information[count] = temp;
         } 
         information[count+1] = username;
         information[count+2] = password;
         read.close();
         BufferedWriter out = new BufferedWriter(new FileWriter(THE_FILE));
         for(int i = 0;i<=count+2;i++){
            out.write(information[count]);
            out.newLine();
         }  
         out.close();  
      }
      catch(IOException e ){
         System.out.print("Problem with using file");
      }  
   }
   
   public String getUsername(){
      return username;
   }
   public String getPassword(){
      return password;
   }
   
   public void setUsername(String s){
      username = s;
   }
   public void setPassword(String s){
      password = s;
   }
}
