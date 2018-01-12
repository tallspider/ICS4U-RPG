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
	
	public LeaderBoard(String un){
		loadFromFile();
		sortScoreOrder();
		sortNameOrder();
		currentPlayerUsername = un;
	}
	
	private void loadFromFile(){
		try{
			BufferedReader f = new BufferedReader(new FileReader(FILENAME));
			ArrayList<String> usernames = new ArrayList<String>();
			String temp;
			while( (temp = f.readLine()) != null){
				usernames.add(temp);
				f.readLine();
			}
			
			playersByName = new ComparisonPlayer[usernames.size()];
			playersByScore = new ComparisonPlayer[usernames.size()];
			
			for(int eachPlayer = 0; eachPlayer < usernames.size(); eachPlayer++){
				ComparisonPlayer tempHold = new ComparisonPlayer(usernames.get(eachPlayer));
            playersByName[eachPlayer] = tempHold;
            playersByScore[eachPlayer] = tempHold;
         }
			
		} catch(IOException e){
			System.out.println("File error");
		}
	}
   
   private void sortScoreOrder(){
      for(int end = playersByScore.length - 1; end > 0; end--){
         int min = playersByScore[0].getScore();
         int minID = 0;
         for(int at = 1; at <= end; at++){
            if(playersByScore[at].getScore() < min){
               min = playersByScore[at].getScore();
               minID = at;
            }
         }
         ComparisonPlayer tempPlayer = playersByScore[minID];
         playersByScore[minID] = playersByScore[end];
         playersByScore[end] = tempPlayer;
      }
   }
	
	private void sortNameOrder(){
		for(int end = playersByName.length - 1; end > 0; end--){
			String last = playersByName[0].getUsername();
			int lastID = 0;
			for(int at = 1; at <= end; at++){
				if(playersByName[at].getUsername().compareTo(last) > 0){
					last = playersByName[at].getUsername();
					lastID = at;
				}
			}
			ComparisonPlayer tempPlayer = playersByName[lastID];
			playersByName[lastID] = playersByName[end];
			playersByName[end] = tempPlayer;
		}
	}
	
	public ComparisonPlayer[] getPlayersByScore(){
		return playersByScore;
	}
	
	public void setPlayersByScore(ComparisonPlayer[] players){
		playersByScore = players;
	}
	
	public ComparisonPlayer[] getPlayersByName(){
		return playersByName;
	}
	
	public void setPlayersByName(ComparisonPlayer[] players){
		playersByName = players;
	}
	
	public String getCurrentPlayerUsername(){
		return currentPlayerUsername;
	}
	
	public void setCurrentPlayerUsername(String un){
		currentPlayerUsername = un;
	}
}