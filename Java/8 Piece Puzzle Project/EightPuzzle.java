import java.util.Scanner;

public class EightPuzzle {
	
	private int [] startState = {0,1,2,3,4,5,6,7,8}; // 0 is blank space
	private int [] goalState;
	
	/**
	 * @constructor EightPuzzle
	 * Creates an Eight Puzzle object and asks user for goal state input
	 * Solves the puzzle and outputs the moved to make to get to the goal state
	 */
	public EightPuzzle(){
		Scanner scan = new Scanner( System.in );
		boolean loop = true;
		while ( loop ){
			System.out.println("Enter a goal state using the following rules:\n\t- Use numbers 0 through 8 only once each\n\t- Numbers must have a space in between\n\t- Example:  3 5 2 8 0 1 7 4 6\n\nEnter goal state: ");
			//if it is checkReachable(), then call solve(), otherwise prompt user for another goal state		
			int[] goalState = new int[9];
			String entry = scan.nextLine();
			Scanner intScan = new Scanner( entry );
			int index = 0;
			while (intScan.hasNext()){
				goalState[index] = intScan.nextInt();
				index++;
			}
			if ( checkReachable( goalState )){ //if the goal state is reachable, solve it
				this.goalState = goalState;
				solve();
				loop = false;
			}else{
				System.out.println("Your goal state is unreachable. Try again! \n");
			}
			intScan.close();
		}
		scan.close();
	}
	
	/**
	 * @method makeChildren
	 * 		Takes a 1D array board state and returns a linked list containing its children states
			Will be used in solve()
	 * @param boardState
	 * @return Linked List with all possible moves of the Board State
	 */
	public LinkedList makeChildren( BoardState boardState ){ 
		LinkedList childrenStates = new LinkedList();
		int[] array = new int[9];
		System.arraycopy(boardState.boardState, 0, array, 0, 9); //so that the original array is not overwritten
		int[] childA = null;
		int[] childB = null;
		int[] childC = null;
		int[] childD = null; 
		int x = getIndex( 0, array ); //this is 0's index in the board
		int y; //y is the place we will be swapping x with
		if( x > 2 ){ //if 0's index is greater than 2, y = x - 3
			y = x - 3;
			childA = swapPlaces( x, y, array );
			childrenStates.insertFront( new BoardState( childA, boardState.getG() + 1, Manhattan( goalState, childA ), boardState )); //EDIT: was man dist with boardState.getBoardState() instead of goalState
		}
		if ( x < 6 ){ //if x is less than 6
			y = x + 3;
			childB = swapPlaces( x, y, array );
			childrenStates.insertFront( new BoardState( childB, boardState.getG() + 1, Manhattan( goalState, childB ), boardState ));
		}
		if ( x % 3 != 0 ){ //if x is not divisible by 3
			y = x - 1;
			childC = swapPlaces( x, y, array );
			childrenStates.insertFront( new BoardState( childC, boardState.getG() + 1, Manhattan( goalState, childC ), boardState ));
		}
		if ( x == 0 || x == 1 || x == 3 || x == 4 || x == 6 || x == 7 ) { //if x is not 2, 5, or 8, then y is x + 1
			y = x + 1;
			childD = swapPlaces( x, y, array );
			childrenStates.insertFront( new BoardState( childD, boardState.getG() + 1, Manhattan( goalState, childD ), boardState )); 
		}
		return childrenStates;
	} 
	
	/**
	 * @method swapPlaces
	 * Helper method swaps the places of x and y in an array without modifying original array
	 * @param x
	 * @param y
	 * @param array
	 * @return int[] representing the array with the values now swapped
	 */
    private int[] swapPlaces( int x, int y, int[] array ){ 
        int[] rtnArray = new int[9];
        System.arraycopy(array, 0, rtnArray, 0, 9);
    	int temp = rtnArray[x]; 
        rtnArray[x] = rtnArray[y]; 
        rtnArray[y] = temp; 
        return rtnArray;
    }
    
	/**
	 * @method printPath
	 * MUST be recursive!! Prints the path to the goal state
	 * @param current
	 */
	public void printPath( BoardState current ){ 
		if (current != null ){
			printPath( current.getParentBoardState() );
			System.out.println( current );
		}
	}
	
	/**
	 * @method solve
	 * Performs Eight Puzzle Algorithm which uses the PriorityQueue and LinkedList data structures
	 * Uses the makeChildren method to generate a LinkedList containing the board state’s children
	 * When goal is found, prints the path to the goal using the printPath method
	 */
	public void solve(){
		
		BoardState goalBoardState = new BoardState( goalState, 0, 0, null );	
		PriorityQueue closedNodesQ = new PriorityQueue();
		PriorityQueue openNodesQ = new PriorityQueue(); //unvisited leaf nodes
		BoardState startStateBoard = new BoardState( startState, 0, Manhattan( goalState, startState ), null );
		openNodesQ.priorityEnqueue( startStateBoard );
		boolean goal = false;
		while (!goal){
			BoardState current = (BoardState) openNodesQ.dequeue();
			if ( current.equals(goalBoardState) ){ //you've found it!
				System.out.println("\nFollow these moves from the start state to get to your goal:\n");
				printPath(current);
				goal = true;
				System.out.println("Yay!!");
			}else{
				LinkedList children = makeChildren( current );
				closedNodesQ.enqueue( current );
				LinkedListNode childNode = children.getHead();
				while ( childNode != null ){ //traversing the children linked list
					if ( closedNodesQ.find( (BoardState) childNode.getData()) == null ){ //if closedNodesQ does not have the childNode's boardstate
						openNodesQ.priorityEnqueue((BoardState) childNode.getData());
					}
					childNode = childNode.getNext();
				}
			}
		}
	}
	
	/**
	 * @method checkReachable
	 * Takes goal state (1D array of ints of size 1x9) and returns true if it is reachable
	 * An array is reachable if the number of inversions (sum of steps to get tiles 
	 * back into their start start positions) is an even number
	 * @param goalState
	 * @return boolean representing whether or not the goal state is reachable from the start state
	 */
	public boolean checkReachable( int [] goalState ){ 
		boolean rtnBool = false;
		int count = 0;
		for ( int i = 0; i < goalState.length; i ++ ){ //for each number in goal state
			if ( goalState[i] > 0 ){ //unless the number is 0 = we don't count the inversion of the blank space
				count += returnInversion(goalState[i], goalState ); //return the inversion number and add to the count
			}
		}
		if ( count % 2 == 0){
			rtnBool = true;
		}
		return rtnBool;
	}
	
	/**
	 * @method returnInversion
	 * @param num
	 * @param goalState
	 * @return int which represents how many inversions a number is off in an array from where it should be in the start state
	 */
	private int returnInversion( int num, int [] goalState ){ 
		int inversionCount = Math.abs(startState[num] - getIndex(num, goalState));
		return inversionCount;
	}
	
	/**
	 * @method getIndex
	 * @param num
	 * @param array
	 * @return int which represents the number's index in the array parameter
	 */
	private int getIndex( int num, int [] array ){ 
		int index = 0;
		for ( int i = 1; i < array.length; i++ ){
			if ( array[i] == num ){
				index = i;
			}
		}
		return index;
	}
	
	/**
	 * @method convertToCoordinates
	 * Helper method to convert the index of a number into coordinates on a 3x3 grid WITHOUT using a 2D array!!
	 * @param index
	 * @return int[] array of two values which serve as the x and y coordinates of the number at the parameter's index
	 */
	private int[] convertToCoordinates( int index ){ 
		int[] coords = {0,0};
		if ( index < 3 ){
			coords[0] = 0;
			coords[1] = index;
		}else if ( index < 6 ){
			coords[0] = 1;
			coords[1] = index - 3;
		}else{
			coords[0] = 2;
			coords[1] = index - 6;
		}
		return coords;
	}
	
	/**
	 * @method Manhattan
	 * Takes two 1x9 arrays of ints and returns Manhattan Distance between them
	 * @param goalState
	 * @param otherState
	 * @return int with the value of the Manhattan Distance between the two Board States
	 */
	public int Manhattan( int [] goalState, int [] otherState ){ 
		int manhattanDistance = 0;
		for ( int i = 0; i < goalState.length; i++ ){ //convert the goal state indexes to coordinates without using a 2d array
			if ( goalState[i] > 0 ){
				int[] otherStateCoords = convertToCoordinates( getIndex( goalState[i], otherState ));	
				int[] goalCoords = convertToCoordinates( getIndex( goalState[i], goalState ));
				manhattanDistance += Math.abs( otherStateCoords[0] - goalCoords[0]) + Math.abs( otherStateCoords[1] - goalCoords[1] );
			}
		}
		return manhattanDistance;
	}
	
}
