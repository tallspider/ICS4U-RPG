/*
Note to self:

   -class headers indicating:
o class name
o author
o date
o school
o purpose
   -method headers including:
o description of each parameters and return values
o purpose
   -sufficient and appropriate comments describing
o code that is difficult to understand
o constant, type and variable declarations
*/

/*
Class Name: Game
Author: David Qian 
Date: January 7, 2018
School: A.Y.Jackson S.S
Purpose: A class used to represent the current playing of the game
*/
import java.io.*;
class Game{
	private Player currentPlayer ;
	private GameBoard gameBoard;
	
	public Game(Player player){
		currentPlayer = player;
	}
	
	public void saveGame(){
		currentPlayer.save();
	}
	
	public void loadGame(){
		currentPlayer.load();
	}
	
	public void startCombat(){
		gameBoard.startCombat(currentPlayer);
	}
	
	public void displayLeaderBoard(){
		LeaderBoard lb = new LeaderBoard(currentPlayer.getUsername());
		lb.display();
	}
	
	public Player getPlaye(){
		return currentPlayer;
	}
	
	public void setPlayer(Player player){
		currentPlayer = player;
	}
	
	public GameBoard getGameBoard(){
		return gameBoard;
	}
	
	public void setGameBoard(GameBoard newGB){
		gameBoard = newGB;
	}
}
