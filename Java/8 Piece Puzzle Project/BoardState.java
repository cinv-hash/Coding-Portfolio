
public class BoardState implements Comparable<Object> {
	
	int[] boardState;
	int g; //depth
	int h; //manhattan distance
	BoardState parentBoardState; 
	
	public BoardState( int[] boardState, int g, int h, BoardState parent ){
		this.boardState = boardState;
		this.g = g;
		this.h = h;
		this.parentBoardState = parent;
	}
	
	/**
	 * @method equals
	 * @param object
	 * Overrides Object’s equals() method which returns true if both BoardStates have the same state
	 * @return boolean representing whether the board states have the same array
	 */
	public boolean equals( Object other ){
		boolean rtnBool = false;
		BoardState otherBoard = (BoardState) other;
		int tally = 0;
		for ( int i = 0; i < boardState.length; i++ ){
			if ( this.getBoardState()[i] == otherBoard.getBoardState()[i] ){
				tally++;
			}
		}
		if ( tally == 9 ){
			rtnBool = true;
		}
			
		return rtnBool;
	}

	/**
	 * @method compareTo
	 * Overrides Object's compareTo() so in order to compare Board States's f (g+h) values to one another
	 * @param object
	 * @return int representing whether the board state should come before or after the other board state, based on its f value (g + h)
	 */
	public int compareTo( Object other){
		BoardState otherBoard = (BoardState) other;
		int rtnval = 0;
		int thisF = g + h;
		int otherF = otherBoard.getG() + otherBoard.getH();
		if( thisF == otherF ){ //if this boards f (g + h) is the same as the 
			rtnval = 0;
		}else if( thisF < otherF ){
			rtnval = -1;
		}else{
			rtnval = 1;
		}
		return rtnval;
	}
	
	public int[] getBoardState() {
		return boardState;
	}

	public void setBoardState(int[] boardState) {
		this.boardState = boardState;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public BoardState getParentBoardState() {
		return parentBoardState;
	}

	public void setParentBoardState(BoardState parentBoardState) {
		this.parentBoardState = parentBoardState;
	}

	/**
	 * @method toString
	 * Overrides Object’s toString() method which should return a String containing the board state
	 * @return String of the board's array that looks like an eight puzzle board
	 */
	public String toString(){ 
		String one = boardState[0] + " " + boardState[1] + " " + boardState[2];
		String two = boardState[3] + " " + boardState[4] + " " + boardState[5];
		String three = boardState[6] + " " + boardState[7] + " " + boardState[8];
		String dash = "-";
		return one + "\n" + two + "\n" + three + "\n" + dash ; 
	}
	
}
