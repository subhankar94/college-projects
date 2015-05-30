import java.util.Scanner;

/**
 * This program creates a matrix based on the length and width
 * parameters specified by the user and populates it with random
 * numbers between -10 and 10. It then provides the user with
 * various operations that can be performed on the matrix,
 * e.g., horizontal/vertical flip, transpose, etc.
 * 
 * @author subhankarghosh
 * @version Oct 21, 2014
 *
 */

public class Matrix {

	public static void main(String[] args){
		
		Scanner in = new Scanner(System.in);
		
		int rows, cols;
		
//		Prompts user for number of rows in matrix and checks if number is between 1 & 5, inclusive
		System.out.println("Hello Neo, if you want to see the matrix, please enter:"
				+ "\n1. Number of rows (between 1 & 5, inclusive): ");
		rows = in.nextInt();
		if((rows < 1) | (rows > 5)){
			do{
				System.out.println("Please re-enter an integer between 1 & 5, inclusive:");
				rows = in.nextInt();
			} while ((rows < 1) | (rows > 5));
		}
		
//		Prompts user for number of columns in matrix and checks if number is between 1 & 5, inclusive
		System.out.println("2. Number of columns (between 1 & 5, inclusive): ");
		cols = in.nextInt();
		if((cols < 1) | (cols > 5)){
			do{
				System.out.println("Please re-enter an integer between 1 & 5, inclusive:");
				cols = in.nextInt();
			} while ((cols < 1) | (cols > 5));
		}
		
//		Creates matrix with user-specified dimensions
		int [] [] userMatrix = new int [rows] [cols];
		
//		Populates matrix with random integers between -10 & 10
		for(int row = 0; row < rows; row++){
			for(int col = 0; col < cols; col++){
				userMatrix [row] [col] = (((int) (Math.random() * 21)) - 10);
			}
		}
		
		printMatrix(userMatrix);
		
//		Lists functions available to the user
		System.out.println("Choose an option by entering the relevant key: "
				+ "\nH: horizontal flip - each row is reversed"
				+ "\nV: vertical flip   - each column is reversed"
				+ "\nT: transpose       - rows become columns & vice versa"
				+ "\nR: rowMax          - finds largest value in each row"
				+ "\nC: columnSum       - finds sum of the values in each column"
				+ "\nQ: quit\n");
		
		char control;
		
//		Do-while loop to allow user to keep playing with functions
//		until boredom strikes and she/he decides to quit
		do{
			control = in.next().toLowerCase().charAt(0);
			switch (control) {
			case 'h' : printMatrix(horizontalFlip(userMatrix)); break;
			case 'v' : printMatrix(verticalFlip(userMatrix)); break;
			case 't' : printMatrix(transpose(userMatrix)); break;
			case 'r' : printMatrix(rowMax(userMatrix)); break;
			case 'c' : printMatrix(columnSum(userMatrix)); break;
			case 'q' : System.out.println("\nNow leaving the matrix. "
											+ "And I thought you were the chosen one..."); System.exit(1);
			default  : System.out.println("\nThat is not a valid input. Please re-enter a valid character:"
					+ "\nH: horizontal flip - each row is reversed"
					+ "\nV: vertical flip   - each column is reversed"
					+ "\nT: transpose       - rows become columns & vice versa"
					+ "\nR: rowMax          - finds largest value in each row"
					+ "\nC: columnSum       - finds sum of the values in each column"
					+ "\nQ: quit\n"); break;
			}
		} while (control != 'q');	
		
	}
	
	/**
	 * Prints the matrix with fixed spacing for every element
	 * @param 2D matrix of integers 
	 */
	public static void printMatrix (int [] [] matrix){
		
		System.out.println();
		for(int row = 0; row < matrix.length; row++){
			System.out.println();
			for(int col = 0; col< matrix[row].length; col++){
				System.out.printf("%4d", matrix[row][col]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 * Prints the matrix with fixed spacing for every element
	 * (Overload printMatrix method to apply to 
	 * both 1D & 2D matrices)
	 * @param 1D matrix of integers
	 */
	public static void printMatrix (int [] matrix){
		
		System.out.println();
		for(int i = 0; i < matrix.length; i++){
			System.out.printf("%4d", matrix[i]);
		}
		System.out.println();
	}

	/**
	 * Creates a new matrix of same dimensions as the one passed to it,
	 * containing the elements of every column in reverse order
	 * @param 2D integer matrix
	 * @return 2D integer matrix with contents of columns in reverse order
	 */
	public static int [] [] horizontalFlip (int [] [] matrix){
		
		int [] [] horizontalFlipped = new int [matrix.length] [matrix[0].length];
		
		for(int row = 0; row < matrix.length; row++){
			for(int col = 0; col < matrix[row].length; col++){
				horizontalFlipped [row] [matrix[row].length - col - 1] = matrix [row] [col];
			}
		}
		return horizontalFlipped;
	}
	
	/**
	 * Creates a new matrix of same dimensions as the one passed to it,
	 * containing the elements of every row in reverse order
	 * @param 2D integer matrix
	 * @return 2D integer matrix with elements of rows in reverse order
	 */
	public static int [] [] verticalFlip (int [] [] matrix){
		
		int [] [] verticalFlipped = new int [matrix.length] [matrix[0].length];
		
		for(int col = 0; col < matrix[0].length; col++){
			for(int row = 0; row < matrix.length; row++){
				verticalFlipped [row] [col] = matrix [matrix.length - row - 1] [col];
			}
		}
		return verticalFlipped;
	}
	
	/**
	 * Creates a new matrix of same dimensions as the one passed to it,
	 * and populates it with values of the transpose
	 * @param 2D integer matrix
	 * @return transpose of 2D integer matrix 
	 */
	public static int [] [] transpose (int [] [] matrix){
		
		int [] [] transposedMatrix = new int [matrix[0].length] [matrix.length];
		
		for(int row = 0; row < matrix.length; row++){
			for(int col = 0; col < matrix[0].length; col++){
				transposedMatrix [col] [row] = matrix [row] [col];
			}
		}
		return transposedMatrix;
	}
	
	/**
	 * This method creates a 1D integer matrix with number of elements
	 * equal to the number of rows in the matrix passed to it, and stores
	 * the value of largest element in each row
	 * @param 2D integer matrix
	 * @return 1D integer matrix with maximum values of corresponding rows
	 */
	public static int [] rowMax (int [] [] matrix){
		
		int [] rowMax = new int [matrix.length];
		
		for (int row = 0; row < matrix.length; row++){
			int max = matrix [row] [0];
			for (int col = 0; col < matrix[0].length; col++){
				if (matrix [row] [col] > max)
					max = matrix [row] [col];
			}
			rowMax [row] = max;
		}
		return rowMax;
	}
	
	/**
	 * This method creates a 1D integer with matrix number of elements
	 * equal to the number of columns of the matrix passed to it,
	 * and stores the sum of elements of each corresponding column
	 * @param 2D integer matrix
	 * @return 1D integer matrix with sum of elements of corresponding columns
	 */
	public static int [] columnSum (int [] [] matrix){
		
		int [] columnSum = new int [matrix[0].length];
		
		for(int col = 0; col < matrix[0].length; col++){
			int sum = 0;
			for(int row = 0; row < matrix.length; row++){
				sum += matrix [row] [col];
			}
			columnSum [col] = sum;
		}
		return columnSum;
	}

}
