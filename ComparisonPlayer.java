//Class name: ComparisonPlayer
//Author: Annie Gao
//Date: Jan. 9, 2018
//School: A.Y.Jackson S.S.
//Purpose: a simplified version of the Player class used for displaying the rankings in the leaderboard

import java.io.*;
public class ComparisonPlayer{
	private String username;
	private int score;
	
	//constructor of the ComparisonPlayer class
	//takes in the username of the player as a String 
	public ComparisonPlayer(String un){
	
		username = un;
		
		//calls loadScore() to fill in the score of the player
		loadScore();
	}
	
	//private method for loading the score of the player from the appropriate information file
	private void loadScore(){
		try{
		
			//creates a BufferedReader to read from the file containing the information of this player, whcih is named (username).txt
			BufferedReader f = new BufferedReader(new FileReader(username + ".txt"));
			
			//read in a line to skip the username line: the score is written as the second line of each file
			f.readLine();
			
			//read in the score as a String and parse it to an int
			score = Integer.parseInt(f.readLine());
			
			//close the reader
			f.close();
			
		} catch (IOException e){
			System.out.println("File error");
		}
	}
	
	//Accessor for the player username
	public String getUsername(){
      if(username.length() > 10)
         return username.substring(0, 10);
      
		return username;
	}
	
	//Mutator for the player username
	//takes in a String to which the username of this ComparisonPlayer will be set
	public void setUsername(String un){
		username = un;
	}
	
	//Accessor for the player score
	public int getScore(){
		return score;
	}
	
	//Mutator for the player score
	//takes in an int to which the score of this ComparisonPlayer will be set
	public void setScore(int sc){
		score = sc;
	}
}
