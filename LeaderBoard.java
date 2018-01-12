//Class name: LeaderBoard
//Author: Annie Gao
//Date: Jan. 9, 2018
//School: A.Y.Jackson S.S.
//Purpose: a class that represents the leaderboard of the entire game

import java.io.*;
import java.util.*;
public class LeaderBoard{

	private ComparisonPlayer[] playersByName;
	private ComparisonPlayer[] playersByScore;
	static final String FILENAME = "logins.txt";
	private String currentPlayerUsername;
	
	//constructor of the LeaderBoard class taking in the username of the current player
	//so that the current player can be highlighted
	public LeaderBoard(String un){
		loadFromFile();
		sortScoreOrder();
		sortNameOrder();
		currentPlayerUsername = un;
	}
	
	//loads the score of each player from their respective files
	//and creates ComparisonPlayer objects to represent them
	private void loadFromFile(){
		try{
			//creates BufferedReader to read in usernames from the login file
			BufferedReader f = new BufferedReader(new FileReader(FILENAME));
			
			//create an ArrayList to hold the usernames of each player
			ArrayList<String> usernames = new ArrayList<String>();
			
			String temp;
			
			//while there are more users written in the logins file
			while( (temp = f.readLine()) != null){
			
				//add the new username to the ArrayList of usernames
				usernames.add(temp);
				
				//skip the password line
				f.readLine();
			}
			
			//initialize the two rankings arrays
			playersByName = new ComparisonPlayer[usernames.size()];
			playersByScore = new ComparisonPlayer[usernames.size()];
			
			//fill in the arrays with the ComparisonPlayer objects to represent each player
			//arrays are not sorted at this point
			for(int eachPlayer = 0; eachPlayer < usernames.size(); eachPlayer++){
				//make a temporary ComparisonPlayer variable to hold the object to be assigned to both arrays
				ComparisonPlayer tempHold = new ComparisonPlayer(usernames.get(eachPlayer));
            
				//assign the object to a place in both arrays
			   playersByName[eachPlayer] = tempHold;
            playersByScore[eachPlayer] = tempHold;
         }
			
		} catch(IOException e){
			System.out.println("File error");
		}
	}
   
	//sorts the score ranking array by order of decreasing score
	//uses selection sort algorithm
   private void sortScoreOrder(){
		
		//setting the endpoint of each pass to one before the previous endpoint
      for(int end = playersByScore.length - 1; end > 0; end--){
         
			//create a temporary variable to hold the minimum score found
			int min = playersByScore[0].getScore();
         //create a temporary variable to hold the id of the player with minimum score
			int minID = 0;
         
			//go through the array until the endpoint, find the player with minimum score
			//who will be moved to the endpoint
			for(int at = 1; at <= end; at++){
            if(playersByScore[at].getScore() < min){
               min = playersByScore[at].getScore();
               minID = at;
            }
         }
			
			//swap the player with minimum score with the player at the endpoint
         ComparisonPlayer tempPlayer = playersByScore[minID];
         playersByScore[minID] = playersByScore[end];
         playersByScore[end] = tempPlayer;
      }
   }
	
	//sorts the name ranking array be lexicographic order
	private void sortNameOrder(){
		
		//setting the endpoint of each pass to one before the previous endpoint
      for(int end = playersByName.length - 1; end > 0; end--){
			
			//create a temporary variable to hold the lexicographically-last username found
			String last = playersByName[0].getUsername();
			//create a temporary variable to hold the id of the player with the lexicographically-last username
			int lastID = 0;
			
			//go through the array until the endpoint, find the player with lexicographically-last username
			//who will be moved to the endpoint
			for(int at = 1; at <= end; at++){
				if(playersByName[at].getUsername().compareTo(last) > 0){
					last = playersByName[at].getUsername();
					lastID = at;
				}
			}
			
			//swap the player with lexicographically-last username with the player at the endpoint
         ComparisonPlayer tempPlayer = playersByName[lastID];
			playersByName[lastID] = playersByName[end];
			playersByName[end] = tempPlayer;
		}
	}
	
	//accessor method for the score ranking array
	public ComparisonPlayer[] getPlayersByScore(){
		return playersByScore;
	}
	
	//mutator method for the score ranking array
	//takes in a ComparisonPlayer array to set the score ranking array to
	public void setPlayersByScore(ComparisonPlayer[] players){
		playersByScore = players;
	}
	
	//accessor method for the name ranking array
	public ComparisonPlayer[] getPlayersByName(){
		return playersByName;
	}
	
	//mutator method for the name ranking array
	//takes in a ComparisonPlayer array to set the name ranking array to
	public void setPlayersByName(ComparisonPlayer[] players){
		playersByName = players;
	}
	
	//accessor method for the username of the current player
	public String getCurrentPlayerUsername(){
		return currentPlayerUsername;
	}
	
	//mutator method for the username of the current player
	//takes in a String to set the currentPlayerUsername field to contain
	public void setCurrentPlayerUsername(String un){
		currentPlayerUsername = un;
	}
}