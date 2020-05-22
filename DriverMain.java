/*
 * Name: Aarushi Pandey
 * Section Number: 2336-001
 * 
 * Analysis: 
 * 		Code to create an Ultmate Tic-Tac-Toe game is required. This game embeds a Tic-Tac-Toe game
 * 		in each of the 9 squares of the original Tic-Tac-Toe game. To win the game, a player needs to win
 * 		each of the smaller boards first until they get a line of 3 (won boards). Some new rules have been 
 * 		added to make the game more interesting. One of them is that a player can only place a mark on 
 * 		the opponent player's last placed mark. The opponent player can force a player to go to an already-
 * 		won/tie board. If a board is already full, the player can choose to go to any board and any position
 * 		they want (provided it is available). The rules to win a game in one of the Tic-Tac-Toe boards
 * 		is still the same. For this project, one of the players is human and the other is AI (the 
 * 		computer). An optional thing to do is to print the possible moves in a board for each
 * 		player.
 * 
 * Design:
 * 		I tried to add to the code of what was done in several in-class assignments for the class.
 * 		I created a few new classes in order to create the Ultimate Tic-Tac-Toe game like the UltimateBoard
 * 		and UltimateTTTGame class. An UltimateBoard consists of an array (of 9) of Board (objects). A Board
 * 		consists of an array (of 9) Box (objects). A Player (either AIPlayer or HumanPlayer) can place a
 * 		mark in a box. I tried to keep properties and operations related to a particular class in that class 
 * 		and called those function from different classes. That is why many of my functions are public. (They 
 * 		need to be since they are called from different classes.) The game starts (and ends) in the UltimateTTTGame
 * 		class in which the boards, players and Tic-Tac-Toe games are set up first. Then, the players are allowed 
 * 		to make moves accordingly and play to win. To make things easier for the player, the board is printed after 
 * 		every move and possible moves in a particular board are listed too. If a game is won on a board,
 * 		that is displayed too and the ultimate game winner is displayed at the end of all the games i.e.
 * 		when all the boxes are filled with a mark. If a board is already won, all the remaining available
 * 		spots are replaced by '*', something which is similar to the sample output given. After every move it
 * 		is checked if the board is full and hence if the game is over. I have tried to keep the output similar 
 * 		to the one given to us as sample output.
 * 
 * Test:
 * 		The program works with valid input. If invalid integers are entered as input, the user is asked to 
 * 		enter it again until the user enters an integer in the valid range. The same is true if the board 
 * 		entered is full or if the move cannot be made. If a char or String is entered, the user is given the 
 * 		chance to remedy their mistake once. If the user enters a char or String again an error message is 
 * 		shown. All in all, a game of Ultimate Tic-Tac-Toe can be played with no errors.
 * */

import java.io.IOException;
//driver class with main function for the game

public class DriverMain {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//initializing and starting game
		UltimateTTTGame game=new UltimateTTTGame();
	}
}
