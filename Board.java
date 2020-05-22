//This class has 5 variables and 22 functions.

public class Board{
	
	//data members
	private Box[] boxes;		//creating single-dimensional array to store values/placeholders 
	private int boardRowSize,boardColSize;		//number of rows and columns in the box
	private String name;		//name of the board
	private String winner="";		//winner of board
	
	//constructor to create a default 3 by 3 box with name as null
	public Board() {
		this(3,3,"3*3 board");		//calling constructor with arguments
	}
	
	//constructor which accepts number of rows, number of columns, and name of the
	//box as parameters
	public Board(int boardRowSize,int boardColSize, String name) {	
		//storing values of number of rows,number of columns, and name of box in global variables
		this.setSize(boardRowSize, boardColSize);
		this.setName(name);
		//initializing the array
		this.init();
	}

	//getter for boxes
	public Box[] getBoxes() {
		return boxes;
	}

	//setter for boxes
	private void setBoxes(Box[] boxes) {
		this.boxes = boxes;
	}

	//getter for boardRowSize
	public int getRowSize() {
		return boardRowSize;
	}

	//setter for boardRowSize
	private void setRowSize(int boardRowSize) {
		//assuming that board can be made of any row size, not just 3
		//checks if the row size is greater than 2
		if (boardRowSize > 2)
			this.boardRowSize = boardRowSize;
		else {
			System.out.println("Minimum row size is 3");
			this.boardRowSize = 3;
		}
	}

	//getter for boardColumnSize
	public int getColSize() {
		return boardColSize;
	}

	//setter for boardColumnSize
	private void setColSize(int boardColSize) {
		//assuming that board can be made of any row size, not just 3
		//checks if the column size is greater than 2
		if (boardColSize > 2)
			this.boardColSize = boardColSize;
		else {
			System.out.println("Minimum column size is 3");
			this.boardColSize = 3;
		}
	}

	//getter method for name
	public String getName() {
		return name;
	}

	//setter method for name
	private void setName(String name) {
		this.name = name;
	}
	
	//getter method for winner
	public String getWinner() {
		return winner;
	}

	//setter method for winner
	public void setWinner(String winner) {
		this.winner = winner;
	}
	
	//method to initialize the array
	private void init() {
		//initializing single dimensional array of length row*column
		boxes=new Box[boardRowSize*boardColSize];
				
		//constructing a new Box for each element in the array of Boxes
		for (int i=0;i<boxes.length;i++) {
			boxes[i]=new Box();
		}		
		
		//calling method to initialize boxes of the board
		initBoxes();	
	}

	//method to initialize boxes of a board
	private void initBoxes() {
		//loop executes until it reaches the last box on the board
		for (int i=0;i<boxes.length;i++)
			boxes[i].setPlaceholder(i+"");	//calls function to set placeholder as the index of the box in the array
	}

	//checks if the board is full of marks and no space is left for more marks
	public boolean isFull() {
		//loop executes until a box is available on the board
		for(Box b: boxes) {
			//checks if the box is available
			if (b.isAvailable())
				return false;
		}
		return true;
	}
	
	//makes a move on the board by first checking if it is empty
	public boolean makeMove(String mark, int row,int col) {
		return boxes[row*this.boardColSize+col].setPlaceholder(mark);
	}
	
	//makes a move on the board by first checking if it is empty
	public boolean makeMove(String mark, int index) {
		return boxes[index].setPlaceholder(mark);
	}
	
	//obtains the value of the mark on a particular board
	public String getMark(int row, int col) {
		return boxes[row*this.boardColSize+col].getPlaceholder();
	}
	
	//prints value of the placeholder of each element in the boxes array
	public void print(int index,int boardNo) {
		//for loop to print the placeholder of each Box element
		for (int i=index;i<index+3;i++) {
			boxes[i].print();	//calling method to print placeholder of each box
		}
		System.out.print("|");
	}

	//sets values of rows and columns by calling the setter functions
	public void setSize(int row, int col) {
		setRowSize(row);
		setColSize(col);
	}

	//method to replace the available spots with '*' in a board that has a winner
	public void replaceAvailableSpots() {
		//loop executes until all the boxes have been checked for availability
		for (int i=0;i<boxes.length;i++) {
			//calling function to check if a box is available
			if (boxes[i].isAvailable())
				boxes[i].setPlaceholder("*");
		}
		
	}

	//method to print all the available spots in a board
	public void printAvailableSpots() {
		//creating new array to store available spots in board
		int empty[]=new int[9];
		int k=0;	//stores index 
		//loop executes until all the boxes have been checked for availability
		for (int i=0;i<boxes.length;i++) {
			//calling function to check if a box is available
			if (boxes[i].isAvailable()) {
				//System.out.println(i+" is available");
				empty[k++]=i;
			}
		}	
		//printing out all elements in array of available/empty spots
		for (int i=0;i<k-1;i++) 
			System.out.print(empty[i]+ ", ");
		System.out.println(empty[k-1]);
	}

}
