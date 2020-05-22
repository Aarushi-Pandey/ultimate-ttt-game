//This class has 1 data member and 6 methods.
public class Box {

	//data members
	private String placeholder="";
	
	//default constructor
	public Box () {
	}
	
	//constructor with parameter to set placeholder
	public Box (String placeholder) {
		setPlaceholder(placeholder);
	}

	//getter function for placeholder
	public String getPlaceholder() {
		return placeholder;
	}

	//setter function for placeholder
	public boolean setPlaceholder(String player) {
		//checks if there is space available on the board to place the mark
		if (isAvailable()) {
			this.placeholder = player;
			return true;
		}
		else
			return false;
	}
	
	//prints the placeholder value
	public void print() {
		System.out.print("| "+getPlaceholder()+" ");
	}
	
	//checks if the placeholder contains a dash or not
	public boolean isAvailable() {
		return !getPlaceholder().equals("X") && !getPlaceholder().equals("O");
	}

}
