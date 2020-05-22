//This class has 5 data members and 22 functions.

public class TTTGame {
	
	//data members
	private Board board;		//creating a board to play the game
	private int gameRowSize;	//storing the row size
	private int gameColSize;	//storing the column size
	private String name ="Simple TicTacToe";	//storing name of the game
	private int indexCurrentPlayer = -1;		//storing index of the current player
	
	//constructor to initialize Board
	public TTTGame(Board board) {
		this.board=board;
	}
	
	//constructor to start the game
	public TTTGame() {
		start();
	}
	
	//getter for board
	public Board getBoard() {
		return board;
	}

	//setter for board
	public void setBoard(Board board) {
		this.board = board;
	}

	//getter for name
	public String getName() {
		return name;
	}

	//setter for name
	public void setName(String name) {
		this.name = name;
	}

	//getter for index of current Player
	public int getIndexCurrentPlayer() {
		return indexCurrentPlayer;
	}

	//setter for index of current player
	public void setIndexCurrentPlayer(int indexCurrentPlayer) {
		this.indexCurrentPlayer = indexCurrentPlayer;
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

	//function to start the game by calling a function to set(create) the board,
	//and functions to set row and column size for the game
	public void start() {
		setBoard();
		setGameRowSize(board.getRowSize());
		setGameColSize(board.getColSize());
	}

	//creates an object of class Board with required parameters of row size,
	//column size, and the name of the game
	private void setBoard() {
		board=new Board();
	}
	
	//checks if the mark occurs in all rows of a column, all columns of a row, or in a diagonal
	public boolean isWinner(String mark) {
		if (checkRow(mark) || checkCol(mark) || checkDiagRL(mark) || checkDiagLR(mark) ) {
			return true;
		}
		else 
			return false;
	}

	//checks if the mark occurs in all elements in the left diagonal
	 public boolean checkDiagLR(String mark) {
		 //checks if mark occurs in the 1st, 5th and 9th boxes
		return board.getMark(0, 0).equals(mark) && board.getMark(1, 1).equals(mark) && board.getMark(2, 2).equals(mark);
	}

	//checks if the mark occurs in all elements in the right diagonal
	 public boolean checkDiagRL(String mark) {
		//checks if mark occurs in the 3rd, 5th and 7th boxes
		return board.getMark(0, 2).equals(mark) && board.getMark(1, 1).equals(mark) && board.getMark(2, 0).equals(mark);
	}

	//checks if a particular column has all rows containing a particular mark
	 public boolean checkCol(String mark) {
		 //executes until all boxes in a column have the mark or until all the boxes of the board have been checked
		 for (int i=0;i<board.getRowSize();i++) {
				int c=0;
				for (int j=0;j<board.getRowSize();j++) {
					if (!board.getMark(j,i).equals(mark)) {
						c=1;
						break;
					}
				}
				if (c==0)
					return true;
			}
			return false;
	}

	//checks if a particular row has all columns containing a particular mark
	 public boolean checkRow(String mark) {
		//executes until all boxes in a row have the mark or until all the boxes of the board have been checked
		for (int i=0;i<board.getRowSize();i++) {
			int c=0;
			for (int j=0;j<board.getRowSize();j++) {
				if (!board.getMark(i,j).equals(mark)) {
					c=1;
					break;
				}
			}
			if (c==0)
				return true;
		}
		return false;
	}

	//returns the winner of the game
	public String getWinner(String player0, String player1) {
		//checks if one of the players is the winner or if there is a tie by calling the required function
		if (isWinner(player0)) {
			return player0;
		}
		else if (isWinner(player1)) {
			return player1;
		}
		else
			return "Tie";
	}
	
}
