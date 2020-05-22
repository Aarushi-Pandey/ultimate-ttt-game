//This class has 2 data members and 19 functions.
public class UltimateBoard {

	//data members
	private Board[] boards;		//to play the game in 
	private int boardRowSize=3,boardColSize=3;	//assuming only UlTimate TicTacToe is played
	
	//constructor to create and initialize the required tic-tac-toe boards in the game
	public UltimateBoard() {
		//creating array of boards (of size 9) to play the game
		boards=new Board[boardRowSize*boardColSize];
		//calling function to initialize boards in the array
		this.initBoards();
	}
	
	//getter for boards
	public Board[] getBoards() {
		return boards;
	}

	//getter for specific board at index boardNo
	public Board getBoard(int boardNo) {
		return boards[boardNo];
	}
	
	//setter for boards
	public void setBoards(Board[] boards) {
		this.boards = boards;
	}

	//getter for boardRowSize
	public int getBoardRowSize() {
		return boardRowSize;
	}

	//setter for boardRowSize
	public void setBoardRowSize(int boardRowSize) {
		this.boardRowSize = boardRowSize;
	}

	//getter for boardColSize
	public int getBoardColSize() {
		return boardColSize;
	}

	//setter for boardColSize
	public void setBoardColSize(int boardColSize) {
		this.boardColSize = boardColSize;
	}

	//this method initializes the boards in the game
	private void initBoards() {
		//loop executes until all boards in the game have been created
		for (int i=0;i<boards.length;i++)
			boards[i]=new Board();
		
		System.out.println("===== WELCOME TO THE ULTIMATE TIC-TAC-TOE GAME!! =====");		
	}
	
	//checks if the board is full of marks and no space is left for more marks
	public boolean isFull() {
		//loop executes until a box is available on the board
		for(Board b: boards) {
			//checks if the box is available
			if (!b.isFull())
				return false;
		}
		//System.out.println("full");
		return true;
	}
		
	//makes a move on the board by first checking if it is empty
	public boolean makeMove(String mark,int board,int row,int col) {
		//return boards[board].getBoxes()[row*this.boardColSize+col].setPlaceholder(mark);
		return boards[board].makeMove(mark, row, col);
	}
		
	//makes a move on the board by first checking if it is empty
	public boolean makeMove(String mark,int board,int index) {
		//return boards[board].getBoxes()[row*this.boardColSize+col].setPlaceholder(mark);
		return boards[board].makeMove(mark, index);
	}
		
	//obtains the value of the mark on a particular board
	public String getMark(int board, int row, int col) {
		return boards[board].getBoxes()[row*this.boardColSize+col].getPlaceholder();
	}
		
	//method to print all Boards in UltimateBoard
	public void printUltimateBoard() {
		//prints boards in each row of the UltimateBoard
		for (int i=0;i<boards.length-2;i=i+3)
			printBoards(i);
	}
	
	//method to print all boards in a row in UltimateBoard
	private void printBoards(int boardNo) {
		//prints boards
		for (int k=0;k<boards.length;k=k+3) {
			for (int j=boardNo;j<boardNo+3;j++) {
				System.out.print("		BOARD#"+j+" ");
				boards[j].print(k,j);
			}
			System.out.println();	
		}
	}

	//method to check if a particular board in the array is full by calling the required function
	public boolean isFull(int boardNo) {
		return boards[boardNo].isFull();
	}
	
	//method to set winner of particular board by calling the required method
	public void setBoardWinner(int boardNo,String mark) {
		boards[boardNo].setWinner(mark);
	}
	
	//method to get winner of particular board by calling the required method
	public String getBoardWinner(int boardNo) {
		return boards[boardNo].getWinner();
	}	

	//method to replace all available spots in a board that has been won by calling the required function
	public void replaceAvailableSpots(int boardNo) {
		boards[boardNo].replaceAvailableSpots();
	}

	//method to print all available spots in a board by calling the required function
	public void printAvailableSpots(int boardNo) {
		boards[boardNo].printAvailableSpots();
	}
}
