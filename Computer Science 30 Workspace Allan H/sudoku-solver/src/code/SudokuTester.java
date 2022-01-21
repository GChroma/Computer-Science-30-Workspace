package code;



public class SudokuTester {

	public static void main(String[] args) throws Exception {
		/*Notes
		 * How to solve Sudoku Strategies:
		 * 1. If there is only one cell in a box/row/column that can be a number, it is that number.
		 * 2. If an unsolved number must be in one row or column of a box, then the rest of the cells in that row/column cannot be that number.
		 * 3. If a cell can only be one number, then it must be that number.
		 */
		
		Board board = new Board();
		board.loadPuzzle("hard");
		board.display();
		board.logicCycles();
		if(board.isSolved())
			System.out.println("Solved.");
		System.out.println("Error found: " + board.errorFound());
		board.display();
		
		
		/*boolean[] array = board.checkPossibility(3, 0);	
		for(int i = 0; i < 10; i++) {
			System.out.print(array[i] + " ");
		}
		 */
	}

}
