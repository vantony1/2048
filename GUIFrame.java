/*Name: Victor Nikhil Antony
NetID: vantony
ClassID: 134
Assignment # Project1 -- 2048
TR 4:50-6:05
TA Name: Sifan Ye
I did not collaborate with anyone on this assignment
 */

//Imports Neccessary Java Utilities 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;

//GUIFrame class that extends JFrame and controls the game and the UI 
public class GUIFrame extends JFrame {
	
	//Declaring and initializing needed variables and objects
	public PaintCanvas canvas;
	public int rows;
	public int validMoves = 0; 
	public int columns;
	public int number;
	public int font; 
	public int adjustment;
	Color background;
	JPanel data; 
	JLabel scoreLabel;
	JLabel levelLabel;
	JButton Quit;
	JButton Restart;
	FontMetrics centerFont;
	boolean zero = true;
	boolean over = false;
	boolean moves = true;
	boolean quit = false;
	boolean restart = false;
	boolean quitforsure = false;
	boolean restartforsure = false;
	Random random = new Random();
	int x = 3;
	String y = Integer.toString(x);
	public Timer timer = new Timer(100, new AnimationHandler());
	
	//Creates tiles array with all 0s
	int [][] tiles = new int[][] {
		{0, 0, 0, 0},
		{0, 0, 0, 0},
		{0, 0, 0, 0},
		{0, 0, 0, 0}
	};
	
	
	//gets the rows and columns of the tiles to assign to various uses
	int r = tiles.length;
	int c = tiles[0].length;
	
	
	
	//PaintCanvas class that extends JPanel and does all the drawing
	public class PaintCanvas extends JPanel{
		public void paintComponent(Graphics g) {
			
			//Draws the dark gray background
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0,0,1000, 1000);
			g.setColor(Color.WHITE);
			
			//conditional that asks the user if he's sure of the decision to quit
			if (quit) {
				
				g.setFont(new Font("monospaced", Font.PLAIN, 15));
				g.drawString("Are you sure you want to quit? (y/n)", 0, this.getHeight()/2);
				
			}
			
			//conditional that asks the user if he's sure of the decision to restart
			if (restart) {
				
				g.setFont(new Font("monospaced", Font.PLAIN, 15));
				g.drawString("Are you sure you want to restart? (y/n)", 0, this.getHeight()/2);
				
			}
			
			//conditional that controls the drawing of the game tiles 
			if (moves) {
			
			//Nested for loops allow for appropriate printing of each instance of the array
			for(int i = 0; i < r; i++) {
				for (int n = 0; n < c; n++) {
					number = tiles[i][n];
					System.out.println(number);
					
					//switch loop which decides the background of the tile based on its number
					switch (number) {
					case 0: background = Color.LIGHT_GRAY;
					break;
			        case 2:    background  = new Color(255,235,205);
			        break;
			        case 4:    background  = new Color(222,184,135);
			        break;
			        case 8:    background  = new Color(205,133,63);
			        break;
			        case 16:   background  = new Color(244,164,96);
			        break;
			        case 32:   background  = new Color(210,105,30);
			        break;
			        case 64:    background  = new Color(160,82,45);
			        break;
			        case 128:   background  = new Color(139,69,19);
			        break;
			        case 256:   background  = new Color(218,165,32);
			        break;
			        case 512:   background  = new Color(184,134,11);
			        break;
			        case 1024:  background  = new Color(255,215,0);
			        break;
			        case 2048:  background  = new Color(255,165,0);
			        break;
			        
			      }
					
					//switch loop which decides the font and adjustment of the tile based on its number
					switch (number) {
			
			        case 2:    font  = 40;
			        adjustment = 30;
			        break;
			        case 4:    font  = 40;
			        adjustment = 30;
			        break;
			        case 8:    font  = 40;
			        adjustment = 30;
			        break;
			        case 16:   font  = 40;
			        adjustment = 30;
			        break;
			        case 32:   font  = 40;
			        adjustment = 28;
			        break;
			        case 64:    font  = 40;
			        adjustment = 28;
			        break;
			        case 128:   font  = 30;
			        adjustment = 26;
			        break;
			        case 256:   font  = 30;
			        adjustment = 26;
			        break;
			        case 512:   font  = 30;
			        adjustment = 26;
			        break;
			        case 1024:  font  = 20;
			        adjustment = 24;
			        break;
			        case 2048:  font  = 20;
			        adjustment = 24;
			        break;
			        
			      }
					
					//converts the int value to string to allow for drawing
					String value = Integer.toString(tiles[i][n]);
					
					//draws tile with appropriate color and set text. 
					g.setColor(background);
					g.fillRect(100*i+10, 100*n+10, 80, 80);
					g.setColor(Color.WHITE);
					g.setFont(new Font("monospaced", Font.BOLD, font));
					
					if (!(number == 0)) {
					g.drawString(value, 105*i+(adjustment-5), 105*n+(2*adjustment)+5);
					}
				}
			}
			
			
			
			} 
			
			//conditional triggered when user quits or loses the game
			//draws out game over, final score, total moves and max tile
			if(quitforsure || over) {
				g.setFont(new Font("monospaced", Font.BOLD, 60));
				g.drawString("GAME OVER", 20, this.getHeight()/2);
				
				g.setFont(new Font("monospaced", Font.BOLD, 30));
				g.drawString("final score: " + Score(tiles), 30, this.getHeight()/2+50);
				scoreLabel.setText("");
				
				g.setFont(new Font("monospaced", Font.BOLD, 18));
				g.drawString("Total Valid Moves Made: " + validMoves, 30, this.getHeight()/2+100);
				
				g.drawString("Max Tiles: " + findMaxTiles(), 30, this.getHeight()/2+150);
	
			}
		}
		}
		
	
	
	
	//constructor == kickstarter of the Game
	public GUIFrame(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		
	}
	

	//class that creates the GUI components for the game and adds keylistener to the JFrame
	public void initializeGraphics() {
		setTitle("2048");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(400,500));
		
		canvas = new PaintCanvas();
		canvas.setPreferredSize(new Dimension(400,400));
		add(canvas, BorderLayout.SOUTH);
		
		
		data = new JPanel();
		scoreLabel = new JLabel("SCORE: 0");
		scoreLabel.setFont(new Font("sansserif", Font.PLAIN, 15));
		levelLabel = new JLabel("2048");
		levelLabel.setFont(new Font("sansserif", Font.BOLD, 60));
		Quit = new JButton("Q");
		
		
		data.setLayout(new BorderLayout());
		data.add(levelLabel, BorderLayout.WEST);
		data.add(scoreLabel, BorderLayout.EAST);
		
		add(data, BorderLayout.NORTH);

		
		addKeyListener(new KeyCommand());
		setResizable(false);
		setFocusable(true);
		pack();
		
		
	}
	
	
	//start game class which starts timer, makes Frame and canvas visible 
	public void startGame() {
		
		setVisible(true);
		timer.start();
		canvas.setVisible(true);
	}
	
	//main class that kickstarts the game
	public static void main(String[]args) {
		
		GUIFrame current = new GUIFrame(4,4);
		current.addNumber();
		current.addNumber();
		current.initializeGraphics();
		current.startGame();
	}
	
	
	//method that implements KeyListener and allows for appropriate reaction to user input through the keyboard
	public class KeyCommand implements KeyListener {
		
		public void keyPressed(KeyEvent e) {
			System.out.println("key triggered");
			
			switch (e.getKeyCode()) {
			
			//for movement of the tiles
			case KeyEvent.VK_DOWN:
				System.out.println("DOWN");
				nextMove(2);
				addNumber();
				break;
				
			case KeyEvent.VK_UP:
				System.out.println("UP");
				nextMove(1);
				addNumber();
				
				break;
				
			case KeyEvent.VK_LEFT:
				System.out.println("LEFT");
				nextMove(3);
				addNumber();
				break;
				
			case KeyEvent.VK_RIGHT:
				System.out.println("RIGHT");
				nextMove(4);
				addNumber();
				break;
				
				//for quit and restart and yes and no
			case 81: 
				quit = true;
				moves = false;
				break;
				
			case 82: 
				restart = true;
				moves = false;
				break;
				
			case 89:
				if (restart) {
					restart(tiles);
					restart = false;
					moves = true;
					}
				
				if (quit) {
					moves = false;
					quit = false;
					quitforsure =true;
					}
				break;
				
			case 78:
				restart = false;
				quit = false;
				moves = true;
				break;
				
				
				}
			
			//updates score everytime a move is attempted
			scoreLabel.setText("SCORE: " + Score(tiles));
		}
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
	
	//method that basically restarts the game by clearing the tile array and spawning two new tiles
	public void restart(int [][] array) {
		int rows = array.length;
		int columns = array[0].length;
		
		for(int i = 0; i < rows; i++) {
			for (int n = 0; n < columns; n++) {
					array[i][n] = 0;
			}
		}
		
		this.addNumber();
		this.addNumber();
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
	
	
	//AnimationHandler that updates game everytimer timer is triggered by implementing ActionListener 
		public class AnimationHandler implements ActionListener {
			public void actionPerformed(ActionEvent t) {
				repaint();
			}
		}
		
		//nextMove method that calls on runningSum2DArray appropriately to move the array properly
		public void nextMove(int direction) {
			runningSum2DArray(tiles, direction);
		}
		
		//the method that controls the movement of the array values based on direction value
		public int[][] runningSum2DArray (int[][]array, int direction) {
			int r = array.length;
			int c = array[0].length;
			zero = false;
			moves = false;
			
			//Moves array digits up
			if (direction == 3) { //up
				
				System.out.println("UP");
				
				//runs through all the rows and columns of the array
				for(int i = r-1; i > 0; i--) {
					for (int n = c; n > 0; n--) {
						
						//adds two tiles if they are equal and sets moves to true
						if (array[i-1][n-1] == array[i][n-1]) {
							moves = true;	
						array[i-1][n-1] = array[i-1][n-1] + array[i][n-1];
						array[i][n-1] = 0;
						}
						
						//switches two tiles if nexxt one is 0 and sets moves to true to 
						//signal that a valid move was made
						if(array[i-1][n-1] == 0 && !(array[i][n-1] == 0)) {
							moves = true;	
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
					
				for(int i = r-1; i >= 0; i--) { 
					for (int n = c - 1; n > 0; n--) {
						
						if(array[i][n-1]==array[i][n]) {
							moves = true;	
						array[i][n-1] += array[i][n];
						array[i][n] = 0;
						}
						
						
						if(array[i][n-1] == 0) {
							moves = true;	
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
				     for (int n = 0; n < c; n++) {
				    	     for(int i = 0; i < r-1; i++){
				    	    	 
							if (array[i+1][n]==array[i][n]) {
								moves = true;	
						     array[i+1][n] += array[i][n];
						     array[i][n] = 0;
							} 
							
							if(array[i+1][n] == 0) {
								moves = true;	
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
				for(int i = 0; i < r; i++) {
					for (int n = 0; n < c-1; n++) {
						
						if (array[i][n+1]==array[i][n]) {
							moves = true;	
						array[i][n+1] += array[i][n];
						array[i][n] = 0;
						
						}
						
						if(array[i][n+1] == 0 && !(array[i][n] == 0)) {
							moves = true;	
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
			}
			
			return array;
		}
		
		
		//spawns random numbers at avaiable random spots and also tells if further moves are possible
		public void addNumber() {
			
			int r = random.nextInt(tiles.length);
			int c = random.nextInt(tiles[0].length);
			int rand = random.nextInt(1000) + 1;
			int value = 2;
			boolean zero = false;
			over = true;
			this.moves = false;
			
			for(int i = 0; i < tiles.length; i++) {
				for (int n = 0; n < tiles[0].length; n++) {
					if (tiles[i][n] == 0) {
						zero = true;
						over = false;
					}
				}
			}
			
			for(int i = 0; i < tiles.length; i++) {
				for (int n = 0; n < tiles[0].length; n++) {
		
					
					if((n+1) < tiles[0].length-1) {
					if ((tiles[i][n] == tiles[i][n+1]) ) {
		
						moves = true;
						over = false;
					}
					}
					
					if((i+1) < tiles.length-1) {
					if(tiles[i][n]==tiles[i+1][n]) {

						moves = true;
						over = false;
					}
					}
					
					if(tiles[i][n] == 0) {

						moves = true;
						over = false;
					}
					
				}
			}
			
			//makes sure the chances of 2 spawing is 80% and 4 is 20%
			if (zero && moves) {
			if (tiles[r][c] == 0) {
				
				if(rand > 0 && rand <= 800) {
					value = 2;
				}
				
				if(rand > 800 && rand <=1000) {
					value = 4;
				} 
				
				tiles[r][c] = value;

				//if random spot is not open then recalls the same function
			} else {
				addNumber();
			}
			} 
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
		
		//method that finds the max tile by doing a simple run through of all
		//rows and columns and finding the maximum value and returns it
		public int findMaxTiles() {
			
			int rows = tiles.length;
			int columns = tiles[0].length;
			int max = 2;
			
			for(int i = 0; i < rows; i++) {
				for (int n = 0; n < columns; n++) {
						if (tiles[i][n] > max) {
							max = tiles[i][n];
						};
				}
			}
			
			return max; 
		}
	

}


