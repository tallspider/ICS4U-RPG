/*
Class Name: Fleet
Author: Chongpu Zhao
Date: January 8, 2018
Purpose: Used to load the registered user's information from the text file, save the new user's information into the text file.
*/
import java.io.*;
public class Login{
   private static final File THE_FILE = new File("logins.txt");
   private String username;
   private String password;
   
   public Login(String name, String pw ){
   //Constructor
      username = name;
      password = pw;
   }
   
   public boolean checkSuccessful(){ 
   //If the given username and password matches any account saved in the text file, return true. Otherwise return false;
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
   // Call the createNewAccount method to save the current username and password field into the txt file.
      if( this.checkRegisterDetails()){
         this.createNewAccount();
      }else{
         System.out.println("Username is already taken");
      }      
   }
   
   private boolean checkRegisterDetails(){
   //Check if the current username field matchs any username which already in the text file. If so return false , otherwise return true.
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
   //Loads all the lines in the txt field into a string array. Add the current username and password field to the end of this array.
   //And then replace the contents in the text file by the contents in the string array.
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
   //Return the username field.
      return username;
   }
   public String getPassword(){
   //Return the password field.
      return password;
   }
   
   public void setUsername(String s){
   //Set the username field to the give String.
      username = s;
   }
   public void setPassword(String s){
   //Set the password field to the give String.
      password = s;
   }
}
