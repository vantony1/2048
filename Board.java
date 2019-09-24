/*Name: Victor Nikhil Antony
NetID: vantony
ClassID: 134
Assignment # Project1 -- 2048
TR 4:50-6:05
TA Name: Sifan Ye
I did not collaborate with anyone on this assignment
 */


//Imports Neccessary Java Utilities 
import java.util.Random;
import java.util.Scanner;

//Board class that controls the game and the UI 
public class Board {

	//Declaring and initializing needed variables and objects
	Scanner listen = new Scanner(System.in);
	Random random = new Random();
	public int [][] board;
	public int validMoves = 0; 
	public boolean moves = true;
	boolean validMove;
	String entry;

	//getters and setters for the board class
	public Board(int [][]board) {
		this.board = board; 
	}
	
	public int[][] getBoard() {
		return board;
	}
	
	public int getRows() {
		return this.board.length;
	}
	
	public int getColumns() {
		return this.board[0].length;
	}

	public void setBoard(int x, int y, int value) {
		this.board[x][y]=value;
	}
	
	public int getBoard(int x, int y) {
		return board[x][y];
	}
	
	//printBoard method that calls on print2Darray to print the board's arry
	public void printBoard() {
		print2Darray(board);
	}
	
	//Score method that calculates the score as the sum of all the tiless
	public int Score(int[][]array) {
		int r = array.length;
		int c = array[0].length;
		int total = 0;
		
		for(int i = 0; i < r; i++) {
			for (int n = 0; n < c; n++) {
				total += array[i][n];
			}
		}
		
		return total;
	}
	
	//print2Darray method that runs through a array and format prints every value
	public static void print2Darray(int[][]array) {
		int r = array.length;
		int c = array[0].length;
		
		for(int i = 0; i < r; i++) {
			for (int n = 0; n < c; n++) {
				System.out.printf("%-4d",array[i][n]);
			}
			System.out.println();
		}
	}
	
	//the method that controls the movement of the array values based on direction value
	public int[][] runningSum2DArray (int[][]array, int direction) {
		int r = array.length;
		int c = array[0].length;
		boolean zero = false;
		this.moves = false;
		this.validMove = false;
		
		//Moves array digits up
		if (direction == 3) { //up
			
			//to inform user what was the last move made
			System.out.println("UP");
			 System.out.println();
			//runs through all the rows and columns of the array
			for(int i = r-1; i > 0; i--) {
				for (int n = c; n > 0; n--) {
					
					//adds two tiles if they are equal and sets moves to true
					if (array[i-1][n-1] == array[i][n-1]) {
					this.moves = true;
					this.validMove = true;
					array[i-1][n-1] = array[i-1][n-1] + array[i][n-1];
					array[i][n-1] = 0;
					}
					
					//switches two tiles if nexxt one is 0 and sets moves to true to 
					//signal that a valid move was made
					if(array[i-1][n-1] == 0 && !(array[i][n-1] == 0)) {
						this.validMove = true;
						this.moves = true;
						array[i-1][n-1] = array[i][n-1];
						array[i][n-1] = 0;	
					}
					
					//moves all the values up after the additions have been done
					//to make sure that no 0s are left in the middle
					//does this by running through the all the rows and columns again
					//detecting zeros and moving all values below up 
					if (n == 1) {
						for(int l = r-1; l > 0; l--) {
						for (int m = c-1; m >= 0; m--) {
							if(array[l-1][m]==0 && !(array[l][m]==0)) {
								array[l-1][m]= array[l][m];
								array[l][m] = 0;
							}
						}
					}
				
						//Rest of functions work similarly to the up function
				}}}} else if (direction == 1) { //left
				System.out.println("LEFT");
				 System.out.println();
			for(int i = r-1; i >= 0; i--) { 
				for (int n = c - 1; n > 0; n--) {
					
					if(array[i][n-1]==array[i][n]) {
					this.moves = true;
					this.validMove = true;
					array[i][n-1] += array[i][n];
					array[i][n] = 0;
					}
					
					
					if(array[i][n-1] == 0) {
						this.validMove = true;
						array[i][n-1] = array[i][n];
						array[i][n] = 0;
					}
					
					if (n == 1) { 
							for (int m = c - 1; m > 0; m--){
							if(array[i][m-1]==0 && !(array[i][m]==0)) {
								array[i][m-1]= array[i][m];
								array[i][m] = 0;
							}
						}
					
				
				}
					
		
				
					
		  } }} else if (direction == 4) { //down
		  
			  System.out.println("DOWN");
			  System.out.println();
			     for (int n = 0; n < c; n++) {
			    	     for(int i = 0; i < r-1; i++){
			    	    	 
						if (array[i+1][n]==array[i][n]) {
						this.moves = true;
						this.validMove = true;
					     array[i+1][n] += array[i][n];
					     array[i][n] = 0;
						} 
						
						if(array[i+1][n] == 0) {
							this.validMove = true;
							array[i+1][n] = array[i][n];
							array[i][n] = 0;
							
							for (int m = i; m > 0; m--) {
								array[m][n] = array[m-1][n];
								array[m-1][n] = 0;
							}
							
						}
						if (n == r-1) {
							for (int m = c-1; m > 0; m--)  {
								for(int l = r-1; l > 0; l--){
								if(array[l][m-1]==0 && !(array[l-1][m-1]==0)) {
									array[l][m-1]= array[l-1][m-1];
									array[l-1][m-1] = 0;
								}
							}
						}
					
					}

						
		  }}} else { //right
			  
			  System.out.println("RIGHT");
			  System.out.println();
			for(int i = 0; i < r; i++) {
				for (int n = 0; n < c-1; n++) {
					
					if (array[i][n+1]==array[i][n]) {
					this.moves = true;	
					this.validMove = true;
					array[i][n+1] += array[i][n];
					array[i][n] = 0;
					
					}
					
					if(array[i][n+1] == 0 && !(array[i][n] == 0)) {
						this.validMove = true;
						array[i][n+1] = array[i][n];
						array[i][n] = 0;
		  }
					
					if (n == c-2) { 
							for (int m = 0; m < c-1; m++){
							if(array[i][m+1]==0 && !(array[i][m]==0)) {
								array[i][m+1]= array[i][m];
								array[i][m] = 0;
							}
						}
					
				
				}
						
				
				}}}
		
		//if a validMove was made adds to valid moves
		if(moves) {
			validMoves++;
			this.validMove = true;
		}
		
		return array;
	}
	
	//Score method that calculates the score as the sum of all the tiless
	public void nextMove(int direction) {
		runningSum2DArray(this.board, direction);
	}
	
	//spawns random numbers at avaiable random spots and also tells if further moves are possible
	public void addNumber() {
		int r = random.nextInt(this.board.length);
		int c = random.nextInt(this.board[0].length);
		int rand = random.nextInt(1000) + 1;
		int value = 2;
		boolean zero = false;
		this.moves = false;
		
		for(int i = 0; i < this.board.length; i++) {
			for (int n = 0; n < this.board[0].length; n++) {
				if (this.board[i][n] == 0) {
					zero = true;
				}
			}
		}
		
		for(int i = 0; i < this.board.length; i++) {
			for (int n = 0; n < this.board[0].length; n++) {
	
				
				if((n+1) < this.board[0].length-1) {
				if ((this.board[i][n] == this.board[i][n+1]) ) {
	
					moves = true;
				}
				}
				
				if((i+1) < this.board.length-1) {
				if(this.board[i][n]==this.board[i+1][n]) {

					moves = true;
				}
				}
				
				if(this.board[i][n] == 0) {

					moves = true;
				}
				
			}
		}
		
		if (zero && this.moves) {
			
			//makes sure the chances of 2 spawing is 80% and 4 is 20%
		if (this.getBoard(r, c) == 0) {
			if(rand > 0 && rand <= 800) {
				value = 2;
			}
			
			if(rand > 800 && rand <=1000) {
				value = 4;
			} 
			
			this.setBoard(r, c, value);

			//if random spot is not open then recalls the same function
		} else {
			addNumber();
		}
		} 
	}
	
	//method that basically restarts the game by clearing the tile array and spawning two new tiles
	public void restart(int [][] array) {
		int rows = array.length;
		int columns = array[0].length;
		this.board = array;
		
		for(int i = 0; i < rows; i++) {
			for (int n = 0; n < columns; n++) {
					array[i][n] = 0;
			}
		}
		
		this.addNumber();
		this.addNumber();
	}
	
	//method that finds the max tile by doing a simple run through of all
	//rows and columns and finding the maximum value and returns it
	public int findMaxTiles() {
		
		int rows = this.board.length;
		int columns = this.board[0].length;
		int max = 2;
		
		for(int i = 0; i < rows; i++) {
			for (int n = 0; n < columns; n++) {
					if (this.board[i][n] > max) {
						max = this.board[i][n];
					};
			}
		}
		
		return max; 
	}
	
	//method that confirms whether the user wants to quit and responds appropriately
	public void quit() {
		String entry;
		System.out.println("Are you sure you want to quit? (y/n)");
		entry = this.listen.next();
		if (entry.equals("y")) {
			this.moves = false;
		}
		
	}
	
	//method that confirms whether the user wants to restart and responds appropriately
	public void restarting() {
		String entry;
		System.out.println("Are you sure you want to restart? (y/n)");
		entry = this.listen.next();
		if (entry.equals("y")) {
			this.restart(this.board);
		}
		
	}
	
	//main method that kick starts the game
	public static void main(String[]args) {
	
		//scanner
		Scanner start = new Scanner(System.in);
		
		
		//Welcomes user and asks what kind of array to create and intitiates game with those setting
		System.out.println("WELCOME TO 2048");
		System.out.println("How many rows do you want to play with?");
		int rows = start.nextInt();
		System.out.println("How many columns do you want to play with?");
		int columns = start.nextInt();
		
		int [][] gameArray = new int [rows][columns];
		
		
		for(int i = 0; i < rows; i++) {
			for (int n = 0; n < columns; n++) {
					gameArray[i][n] = 0;
			}
		}
		
		
		
		//creates new board with user's requests and spawns two random initial tiles
		//in two random spots and prints the board
		Board current = new Board(gameArray);
		
		current.addNumber();
		current.addNumber();
		current.printBoard();
		
		//while moves are possible asks user what move they'd like to make and reacts appropriately
		while(current.moves) {
			System.out.println();
			System.out.println("What's your next move?");
			current.entry = current.listen.next();
			System.out.println();
			
			//switch loop which reacts appropriately to users entries for movement, restart and quit
			//if anything else is entered prompts user to re-enter
			switch(current.entry) {
			case "w": 
			current.nextMove(3);
			current.addNumber();
			current.printBoard();
			System.out.println();	
			System.out.println("w was pressed -- valid move: " + current.validMove);	
			
			break;
			case "s": 
			current.nextMove(4);
			current.addNumber();
			current.printBoard();
			System.out.println();	
			System.out.println("s was pressed -- valid move:" + current.validMove);
			
			break;
			case "a": 
			current.nextMove(1);
			current.addNumber();
			current.printBoard();
			System.out.println();	
			System.out.println("a was pressed -- valid move:" + current.validMove);	
			break;
			
			case "d": 
			current.nextMove(2);
			current.addNumber();
			current.printBoard();
			System.out.println();	
			System.out.println("d was pressed -- valid move:" + current.validMove);	
			break;	
			
			case "q": 
				System.out.println("q was pressed -- is not a valid move but a request to quit");		
			current.quit();break;
			
			case "r": 
				System.out.println("q was pressed -- is not a valid move but a request to restart");	
				current.restarting();
			current.printBoard(); break;
			
			default: 
				System.out.println(current.entry + " was pressed -- is not a valid move. Please enter valid move");		
				continue;
			}
			
			System.out.println("Max Tile: " + current.findMaxTiles());
			
		}
		
		//if game is over, i.e. the above loop is broken, 
		//prints out game over, final score, total moves and max tile
		System.out.println("Score: " + current.Score(current.board));
		System.out.println("Total Valid Moves Made: " + current.validMoves);
		System.out.println("Max Tile: " + current.findMaxTiles());
		System.out.println("GAME OVER");
		
		
		
		
	}
	
	
}
