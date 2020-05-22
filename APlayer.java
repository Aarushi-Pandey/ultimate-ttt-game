//This file contains all the Player classes

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//This abstract class has 2 data members and 9 functions.
public abstract class APlayer {
	
	//data members
	private String name;		//to store name of the player
	private String mark;		//to store whether the player plays an "X" or a "O"
	
	//constructor to accept parameters to initialize a player object
	public APlayer(String name, String mark) {
		//calling setters for the data members
		setName(name);
		setMark(mark);
	}
	
	//getter method for name
	public String getName() {
		return name;
	}
	
	//setter method for name
	public void setName(String name) {
		this.name = name;
	}
	
	//getter method for mark
	public String getMark() {
		return mark;
	}
	
	//setter method for mark
	public void setMark(String mark) {
		this.mark = mark;
	}
	
	//abstract methods to select a board and a square to place the mark
	public abstract int selectBoard(int range) throws NumberFormatException, IOException;
	public abstract int selectMove(int range) throws NumberFormatException, IOException;
	
}

//This class inherits from the above class(Player) and has 6 functions.
class AIPlayer extends APlayer {

	//constructor
	public AIPlayer(String name, String mark) {
		super(name, mark);
	}
	
	//generates a random number by using Math.random()
	public int randomNumber (int range) {
		return (int) (Math.random() * range);
	}

	//selects a random row value to place a mark by calling the randomNumber() method
	public int selectRowValue(int range) {
		return randomNumber(range);
	}

	//selects a random column value to place a mark by calling the randomNumber() method
	public int selectColValue(int range) {
		return randomNumber(range);
	}

	//selects a board number in the given range by calling the randomNumber() method
	@Override
	public int selectBoard(int range) {
		return randomNumber(range);
	}

	//selects a move by selecting a random row and column in the board
	@Override
	public int selectMove(int boardNo) {
		return selectRowValue(3)*3+selectColValue(3);
	}	
}

//This class inherits from the above class(Player) and has 6 functions.
class HumanPlayer extends APlayer {

	//to accept input values
	BufferedReader in=new BufferedReader (new InputStreamReader(System.in));
			
	//constructor
	public HumanPlayer(String name, String mark) {
		super(name, mark);
	}

	//selects and returns the board number chosen by the user
	@Override
	public int selectBoard(int range) throws IOException{
		int boardNo;
		//checks if the input is an integer by using the try-catch block
		//it is assumed that user will make the mistake of entering a char or String value only once
		//checking if the board number entered is valid
		do {
			try {
				System.out.println("Please select a valid board: ");
				System.out.print("Selected Board : ");
				boardNo=Integer.parseInt(in.readLine());
			}
			catch (NumberFormatException e){
				System.out.println("Input is not valid. Please try again.");	//error message
				System.out.println("Please select a valid board: ");
				System.out.print("Selected Board : ");
				boardNo=Integer.parseInt(in.readLine());
			}
		} while (boardNo<0 || boardNo>8);
		return boardNo;
	}

	//selects and returns the move chosen by the user
	@Override
	public int selectMove(int range) throws IOException {
		int move;
		//checks if the input is an integer by using the try-catch block
		//it is assumed that user will make the mistake of entering a char or String value only once
		//checking if the move entered is valid
		do {
			try {
				System.out.println("Please select a valid square on the selected board: ");
				System.out.print("Selected Square : ");
				move=Integer.parseInt(in.readLine());
			}
			catch (NumberFormatException e) {
				System.out.println("Input is not valid. Please try again.");	//error message
				System.out.println("Please select a valid square on the selected board: ");
				System.out.print("Selected Square : ");
				move=Integer.parseInt(in.readLine());
			}
		} while (move<0 || move>8);	
		return move;
	}	
}	
