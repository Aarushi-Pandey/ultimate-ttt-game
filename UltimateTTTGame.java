import java.io.IOException;

//This class has 7 data members and 26 functions.

public class UltimateTTTGame {
	
	//data members
	private UltimateBoard board;		//creating a board to play the game
	private APlayer[] players;	//2 Computer players only
	private TTTGame[] games;
	
	private int gameRowSize;	//storing the row size
	private int gameColSize;	//storing the column size
	
	private String name ="Ultimate TicTacToe";	//storing name of the game
	private int indexCurrentPlayer = -1;		//storing index of the current player
	
	//constructor to initialize board 
	public UltimateTTTGame(UltimateBoard board) {
		this.board=board;
	}
	
	//constructor to start the game
	public UltimateTTTGame() throws NumberFormatException, IOException {
		start();
	}
	
	//getter for gameRowSize
	public int getGameRowSize() {
		return gameRowSize;
	}

	//setter for gameRowSize
	public void setGameRowSize(int gameRowSize) {
		this.gameRowSize = gameRowSize;
	}

	//getter for gameColSize
	public int getGameColSize() {
		return gameColSize;
	}

	//setter for gameColSize
	public void setGameColSize(int gameColSize) {
		this.gameColSize = gameColSize;
	}

	//function to start the game by calling a function to set(initialize) the board,
	//a function to set the players, a function to set the games, 
	//and a function for the players to make their moves one-by-one
	public void start() throws NumberFormatException, IOException {
		setBoard();		//initializing board to play game
		setPlayers();	//initializing players
		setGames();		//creating games
		//setting boardRowSize and boardColSize
		setGameRowSize(board.getBoardRowSize());
		setGameColSize(board.getBoardColSize());
		playGame();		//playing the game
	}

	//method to create new games for each board in the ultimate board
	private void setGames() {
		//creating new games
		games=new TTTGame[9];
		for (int i=0;i<games.length;i++)
			games[i]=new TTTGame();
	}

	//creates an object of class Board with required parameters of row size,
	//column size, and the name of the game
	private void setBoard() {
		board=new UltimateBoard();
	}

	//initializes the array and creates 2 elements in the Player array
	private void setPlayers() {
		players=new APlayer[2];		//initializing the array
		indexCurrentPlayer=0;		//assuming that the 1st player starts the game
		
		//By default 1st player plays 'X' and 2nd player plays 'O'
		players[indexCurrentPlayer++]=new HumanPlayer("Player 1", "X");	//user
		players[indexCurrentPlayer--]=new AIPlayer("Player 2", "O");	//computer
	}
	
	//checks if the game is over and if it isn't, allows the player to make a
	//move on the board, prints the board, and switches players.
	private void playGame() throws NumberFormatException, IOException {
		//printing board
		board.printUltimateBoard();
		System.out.println();
		
		//creating and initializing elements in array to check if there are winners in the tic-tac-toe games
		boolean ifReplacedAvailableSpots[]=new boolean[9];
		setReplacedAvailableSpots(ifReplacedAvailableSpots);

		//printing current player
		System.out.println("Current player: "+players[indexCurrentPlayer].getMark());
		//letting the player pick the board number to start the game
		int boardNo=players[indexCurrentPlayer].selectBoard(board.getBoardRowSize()*board.getBoardColSize());
		
		//loop executes until there are no spots left to place mark on the board (might continue even if there is a winner!)
		while (!gameOver()) {
			//calling method to make a move on the board number
			int move= makeMove(boardNo);

			//checks if there is a winner on the board and if the available spots have not been replaced by '*' yet
			if(!ifReplacedAvailableSpots[boardNo] && ifWinner(boardNo)) {
				replaceAvailableSpots(boardNo);		//replacing available spots by '*' on a board
				ifReplacedAvailableSpots[boardNo]=true;		//since the spots have been replaced now
				board.setBoardWinner(boardNo, getWinner(boardNo));	//setting winner of the particular board
			}
			
			board.printUltimateBoard();	//prints board
			
			//checks if the board is full
			if (board.isFull(boardNo)) {
				//sets the winner of that board
				board.setBoardWinner(boardNo, getWinner(boardNo));
			}
			
			//prints winners of boards if there are any
			printWinners();	
			switchPlayers();	//switches players
			boardNo=move;		//since the move is the new boardNo
			
			//checks if the game is over
			if (!gameOver()) {
				//prints current player and board
				System.out.println("Current player: "+players[indexCurrentPlayer].getMark());
				System.out.println("Selected board : "+boardNo);
			}
		}
		//printing winner of the ultimate tic-tac-toe game
		printGameWinner();
	}

	//this method makes a move in a particular board and returns the move
	private int makeMove(int boardNo) throws NumberFormatException, IOException {
		//checks if the board is full (so new board is chosen)
		if (board.isFull(boardNo)) {
			System.out.println("The selected board is full");	//message to user
			//keep selecting new boards until a board with available boxes is chosen
			while (board.isFull(boardNo)) { 	
				boardNo= players[indexCurrentPlayer].selectBoard(getGameColSize()*getGameRowSize());
			}
			System.out.println("Selected board : "+boardNo);	//printing new boardNo
		}
		
		//printing all possible moves in that board (optional)
		printPossibleMoves(boardNo);
		
		//selecting move by calling required function
		int move=players[indexCurrentPlayer].selectMove(boardNo);
		
		//checks if the move is feasible and keeps choosing moves until it is
		while(!board.makeMove(players[indexCurrentPlayer].getMark(), boardNo, move)) {
			move=players[indexCurrentPlayer].selectMove(boardNo);
		}
		
		//prints the move made by the AI
		if (players[indexCurrentPlayer].getMark()=="O")
			System.out.println("Selected Square : "+move);
		
		return move;
	}

	//method to print all possible moves on a particular board number (optional)
	private void printPossibleMoves(int boardNo) {
		System.out.print("Possible move(s): ");
		//calling required function to print all available spots on the board
		board.printAvailableSpots(boardNo);
	}

	//method to set ifReplacedAvailableSpots array (a default value)
	private void setReplacedAvailableSpots(boolean[] ifReplacedAvailableSpots) {
		for (int i=0;i<9;i++) {
			ifReplacedAvailableSpots[i]=false;
		}
	}

	//method to print winner of the game
	private void printGameWinner() {
		//checks if there is a winner
		if (isWinner("X"))
			System.out.println("Game winner is X");
		else if (isWinner("O"))
			System.out.println("Game winner is O");
		else
			System.out.println("There is no winner");
	}

	//method to replace all available spots in a board with '*' by calling the required method
	private void replaceAvailableSpots(int boardNo) {
		board.replaceAvailableSpots(boardNo);
	}

	//method to check if a board has a winner or not by calling the required method
	private boolean ifWinner(int boardNo) {
		return getWinner(boardNo).equals("X") || getWinner(boardNo).equals("O");
	}

	//method to print winners of boards after every move
	private void printWinners() {
		//loop executes until all boards are checked for a winner
		for (int i=0;i<9;i++) {
			if (!board.getBoardWinner(i).equals("")) {
				System.out.println("Winner of board "+i+ " is " +board.getBoardWinner(i));
			}
		}
		System.out.println();
	}

	//switches from 1 player to another
	private void switchPlayers() {
		//if it is the 1st player
		if (indexCurrentPlayer==0) {
			indexCurrentPlayer++;
		}
		//if it is the 2nd player
		else {
			indexCurrentPlayer--;
		}
	}
	
	//method to return winner of a particular board
	private String getWinner(int boardNo) {
		games[boardNo].setBoard(board.getBoard(boardNo));
		return games[boardNo].getWinner(players[0].getMark(),players[1].getMark());
	}
	
	//checks if the game is over by checking if the board is full
	private boolean gameOver () {
		return board.isFull();
	}
	
	//checks if the mark occurs in all rows of a column, all columns of a row, or in a diagonal
	private boolean isWinner(String mark) {
		//sets winners of all the boards by calling required methods
		for (int i=0;i<board.getBoardRowSize()*board.getBoardColSize();i++) {
			if (games[i].checkRow(mark) || games[i].checkCol(mark) || games[i].checkDiagRL(mark) || games[i].checkDiagLR(mark) )
				board.setBoardWinner(i, mark);
		}
		
		//checks if the player with the mark is the winner of the game
		return checkRow(mark) || checkCol(mark) || checkDiagRL(mark) || checkDiagLR(mark);
	}

	//checks if the mark occurs in all boards in the left diagonal
	private boolean checkDiagLR(String mark) {
		//checks if mark occurs in the 1st, 5th and 9th bards
		return board.getBoardWinner(0).equals(mark) && board.getBoardWinner(4).equals(mark) && board.getBoardWinner(8).equals(mark);
	}

	//checks if the mark occurs in all boards in the right diagonal
	private boolean checkDiagRL(String mark) {
		//checks if mark occurs in the 3rd, 5th and 7th boards
		return board.getBoardWinner(2).equals(mark) && board.getBoardWinner(4).equals(mark) && board.getBoardWinner(6).equals(mark);
	}

	//checks if a particular column has all boards containing a particular mark
	private boolean checkCol(String mark) {
		for (int i=0;i<board.getBoardRowSize()*board.getBoardColSize()-6;i=i+3) {
			if (board.getBoardWinner(i).equals(mark) && board.getBoardWinner(i+3).equals(mark) && board.getBoardWinner(i+6).equals(mark))
				return true;
		}
		return false;
	}

	//checks if a particular row has all columns containing a particular mark
	private boolean checkRow(String mark) {
		for (int i=0;i<board.getBoardRowSize()*board.getBoardColSize()-3;i=i+3) {
			if (board.getBoardWinner(i).equals(mark) && board.getBoardWinner(i+1).equals(mark) && board.getBoardWinner(i+2).equals(mark))
				return true;
		}
		return false;
	}
	
}
