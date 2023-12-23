

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

import javafx.stage.Stage;


public class MainUI extends JPanel{

   public static final int XO = 50;
   public static final int YO = 60;
   public static final int UNIT = 60;
   public static final int GRID_ROWS = 10;	//vertices, not squares
   public static final int GRID_COLS = 9;	//vertices, not squares
   public static final int X_OFFSET = 8;
   public static final int Y_OFFSET = 30;
   public int SEARCH_TIME;	//in miliseconds
   public static final int PIECE_TYPES = 7;
   public static int startSide;
   public GameState gameState;
   public GameTree agent;
   public Image[][] chessImgs;
   public Point selectedPos; 
   public int selectedPieceType;
   public int selectedPieceIdx;
   public boolean waitForSelection;
   public MainUI(){
	   SEARCH_TIME = 5000; //miliseconds
	   newGame();
	   initChessImages();
   }
   private void newGame(){
	   startSide = GameState.MIN_PLAYER;
	   gameState = new GameState(GRID_ROWS,GRID_COLS,startSide);
	   agent = new GameTree(gameState, SEARCH_TIME);
	   selectedPos = null;
	   selectedPieceType = -1;
	   selectedPieceIdx = -1;
	   waitForSelection = true;
   }
   private void initChessImages(){
	   chessImgs = new Image[4][PIECE_TYPES];
	   chessImgs[0][0] = new ImageIcon(this.getClass().getResource("/Images/Pictures/chinese_black_cannon.png")).getImage(); 
	   chessImgs[0][1] = new ImageIcon(this.getClass().getResource("/Images/Pictures/chinese_black_guard.png")).getImage(); 
	   chessImgs[0][2] = new ImageIcon(this.getClass().getResource("/Images/Pictures/chinese_black_elephant.png")).getImage(); 
	   chessImgs[0][3] = new ImageIcon(this.getClass().getResource("/Images/Pictures/chinese_black_horse.png")).getImage(); 
	   chessImgs[0][4] = new ImageIcon(this.getClass().getResource("/Images/Pictures/chinese_black_chariot.png")).getImage(); 
	   chessImgs[0][5] = new ImageIcon(this.getClass().getResource("/Images/Pictures/chinese_black_general.png")).getImage(); 
	   chessImgs[0][6] = new ImageIcon(this.getClass().getResource("/Images/Pictures/chinese_black_soldier.png")).getImage(); 
	   chessImgs[1][0] = new ImageIcon(this.getClass().getResource("/Images/Pictures/chinese_red_general.png")).getImage(); 
	   chessImgs[1][1] = new ImageIcon(this.getClass().getResource("/Images/Pictures/chinese_red_guard.png")).getImage(); 
	   chessImgs[1][2] = new ImageIcon(this.getClass().getResource("/Images/Pictures/chinese_red_elephant.png")).getImage(); 
	   chessImgs[1][3] = new ImageIcon(this.getClass().getResource("/Images/Pictures/chinese_red_horse.png")).getImage(); 
	   chessImgs[1][4] = new ImageIcon(this.getClass().getResource("/Images/Pictures/chinese_red_chariot.png")).getImage(); 
	   chessImgs[1][5] = new ImageIcon(this.getClass().getResource("/Images/Pictures/chinese_red_cannon.png")).getImage(); 
	   chessImgs[1][6] = new ImageIcon(this.getClass().getResource("/Images/Pictures/chinese_red_soldier.png")).getImage(); 

	   chessImgs[2][0] = new ImageIcon(this.getClass().getResource("/Images/Pictures/chinese_black_cannon.png")).getImage(); 
	   chessImgs[2][1] = new ImageIcon(this.getClass().getResource("/Images/Pictures/chinese_black_cannon.png")).getImage(); 
	   chessImgs[2][2] = new ImageIcon(this.getClass().getResource("/Images/Pictures/chinese_black_cannon.png")).getImage(); 
	   chessImgs[2][3] = new ImageIcon(this.getClass().getResource("/Images/Pictures/chinese_black_cannon.png")).getImage(); 
	   chessImgs[2][4] = new ImageIcon(this.getClass().getResource("/Images/Pictures/chinese_black_cannon.png")).getImage(); 
	   chessImgs[2][5] = new ImageIcon(this.getClass().getResource("/Images/Pictures/chinese_black_cannon.png")).getImage(); 
	   chessImgs[2][6] = new ImageIcon(this.getClass().getResource("/Images/Pictures/chinese_black_cannon.png")).getImage(); 
	   chessImgs[3][0] = new ImageIcon(this.getClass().getResource("/Images/Pictures/chinese_black_cannon.png")).getImage(); 
	   chessImgs[3][1] = new ImageIcon(this.getClass().getResource("/Images/Pictures/chinese_black_cannon.png")).getImage(); 
	   chessImgs[3][2] = new ImageIcon(this.getClass().getResource("/Images/Pictures/chinese_black_cannon.png")).getImage(); 
	   chessImgs[3][3] = new ImageIcon(this.getClass().getResource("/Images/Pictures/chinese_black_cannon.png")).getImage(); 
	   chessImgs[3][4] = new ImageIcon(this.getClass().getResource("/Images/Pictures/chinese_black_cannon.png")).getImage(); 
	   chessImgs[3][5] = new ImageIcon(this.getClass().getResource("/Images/Pictures/chinese_black_cannon.png")).getImage(); 
	   chessImgs[3][6] = new ImageIcon(this.getClass().getResource("/Images/Pictures/chinese_black_cannon.png")).getImage(); 
	   
   }
   private Point posOnGrid(Point p){
	   Point pos = new Point((int)((p.getX()-XO+UNIT/2.0)/UNIT),(int)((p.getY()-YO+UNIT/2.0)/UNIT));
	   return pos;
   }
   private Point posAbsolute(Point p){
	   Point pos = new Point((int)(p.getX()*UNIT+XO),(int)(p.getY()*UNIT+YO));
	   return pos;
   }
   public void agentMove(){
	   if(gameState.getCurrSide()==startSide){
		   return;
	   }else{
		   long start = System.currentTimeMillis();
		   Move nextBestMove = agent.nextMove();
		   long end = System.currentTimeMillis();
		   System.out.println("ai move = "+nextBestMove+" move calculation time = "+(end-start)/1000.0+" s");
		   gameState.makeMove(nextBestMove);
	   }
   }
   @Override
   public void paint (Graphics g) {
	   super.paintComponent(g);			//this will clear the current frame
	   
	   //game board
	   Graphics2D g2d = (Graphics2D) g;
	   g2d.setColor(Color.BLACK);
	   for(int i=0; i<GRID_ROWS-1; i++){
			for(int j=0; j<GRID_COLS-1; j++){
				if(i!=4){
					int x = XO+j*UNIT-X_OFFSET;
					int y = YO+i*UNIT-Y_OFFSET;
					g2d.drawRect(x,y,UNIT,UNIT);
				}
			}
	   }
	   g2d.drawLine(XO-X_OFFSET, YO+((GRID_ROWS/2)-1)*UNIT-Y_OFFSET, XO-X_OFFSET, YO+(GRID_ROWS/2)*UNIT-Y_OFFSET);
	   g2d.drawLine(XO+(GRID_COLS-1)*UNIT-X_OFFSET, YO+((GRID_ROWS/2)-1)*UNIT-Y_OFFSET, XO+(GRID_COLS-1)*UNIT-X_OFFSET, YO+(GRID_ROWS/2)*UNIT-Y_OFFSET);
	   g2d.drawLine(XO+3*UNIT-X_OFFSET, YO+0*UNIT-Y_OFFSET, XO+5*UNIT-X_OFFSET, YO+2*UNIT-Y_OFFSET);
	   g2d.drawLine(XO+5*UNIT-X_OFFSET, YO+0*UNIT-Y_OFFSET, XO+3*UNIT-X_OFFSET, YO+2*UNIT-Y_OFFSET);
	   g2d.drawLine(XO+3*UNIT-X_OFFSET, YO+(GRID_ROWS-1)*UNIT-Y_OFFSET, XO+5*UNIT-X_OFFSET, YO+(GRID_ROWS-3)*UNIT-Y_OFFSET);
	   g2d.drawLine(XO+5*UNIT-X_OFFSET, YO+(GRID_ROWS-1)*UNIT-Y_OFFSET, XO+3*UNIT-X_OFFSET, YO+(GRID_ROWS-3)*UNIT-Y_OFFSET);
	   
	   //game pieces
	   int[]state = gameState.getGameState(); 
	   for(int i=0; i<state.length; i++){
		   int pieceId = state[i];
		   if(pieceId!=GameState.UNOCCUPIED){
			   int picGroup = pieceId/PIECE_TYPES;
			   int picType	= pieceId%PIECE_TYPES;
			   int r		= i>>4;
			   int c		= i%16;
			   if(selectedPos!=null && (selectedPos.getX()==c && selectedPos.getY()==r)){
				   picGroup = picGroup+2;
			   }
			   
			   Image img = chessImgs[picGroup][picType];
			   Point ap = posAbsolute(new Point(c,r));
			   g2d.drawImage(img, (int) ap.getX()-X_OFFSET-img.getHeight(this)/2, (int) ap.getY()-Y_OFFSET-img.getWidth(this)/2, this);
		   }

	   }
	  System.out.println(gameState);
	  
   }
   
   public static void main(String[] args){
	  final JFrame mainFrame = new JFrame("Chinese Chess");
	  final MainUI gameboard = new MainUI();
	  mainFrame.setSize(gameboard.XO*2+gameboard.GRID_COLS*gameboard.UNIT-gameboard.UNIT,
						  gameboard.YO*2+gameboard.GRID_ROWS*gameboard.UNIT-gameboard.UNIT);

	  final JPanel statusPanel = new JPanel();
	  //   statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
	  mainFrame.add(statusPanel, BorderLayout.SOUTH);
	  statusPanel.setPreferredSize(new Dimension(mainFrame.getWidth(), 16));
	  statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
	  JLabel statusLabel = new JLabel("<html><div style='text-align: center;'>Your turn. Press [Backspace] to revert a step.</div></html>");
	  statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
	  statusPanel.add(statusLabel);

	  JButton backButton = new JButton("Back to Menu");
    	statusPanel.add(backButton);

		backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(mainFrame, "Are you sure you want to go back to the menu?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    // Chuyển về trang menu
                    mainFrame.setVisible(false); // Ẩn cửa sổ trò chơi
                    new StartScreen().start(new Stage()); // Hiển thị cửa sổ menu
                }
            }
        });
	
	  mainFrame.addWindowListener(new WindowAdapter() {
	     public void windowClosing(WindowEvent windowEvent){
	        System.exit(0);
	     }        
	  });
	  mainFrame.addMouseListener(new MouseAdapter() {
	     public void mousePressed(MouseEvent e) {
	    	if(gameboard.gameState.getCurrSide()!=gameboard.startSide){
	    		return;
	    	}else{
		        Point p = gameboard.posOnGrid(e.getPoint());
		        
		        if(gameboard.waitForSelection){
		        	if(gameboard.gameState.isValidSelection((int) p.getY(), (int) p.getX())){
			        	gameboard.selectedPos = p;
			        	gameboard.waitForSelection = false;
		        	}else{
		        		return;
		        	}
		        }else{
		        	if(gameboard.gameState.isValidMove((int) gameboard.selectedPos.getY(), (int) gameboard.selectedPos.getX(), (int) p.getY(), (int) p.getX())){
		        		gameboard.gameState.makeMove((int) gameboard.selectedPos.getY(), (int) gameboard.selectedPos.getX(), (int) p.getY(), (int)p.getX());
		        		System.out.println("player move = "+gameboard.gameState.getMoves().peek());
		        		int over = gameboard.gameState.isGameOver();
				        if(over!=-1){
				        	String message;
				        	if(over==gameboard.gameState.MAX_PLAYER) message = "Black Win";
				        	else if(over==gameboard.gameState.MIN_PLAYER) message = "Red Win";
				        	else message = "Tie";
				        	int input = JOptionPane.showOptionDialog(mainFrame, message, "Message",JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,null,null,null);
				        	if(input == JOptionPane.OK_OPTION)
				        	{
				        		gameboard.newGame();
				        	}
				        }else{
							statusLabel.setText("<html><div style='text-align: center;'>AI Thinking...</div></html>");
							new SwingWorker<Void, Void>() {
								@Override
								protected void done() {
									// update UI
									int over = gameboard.gameState.isGameOver();
									if(over!=-1){
										String message;
										if(over==gameboard.gameState.MAX_PLAYER) message = "Black Win";
										else if(over==gameboard.gameState.MIN_PLAYER) message = "Red Win";
										else message = "Tie";
										int input = JOptionPane.showOptionDialog(mainFrame, message, "Message",JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,null,null,null);
										if(input == JOptionPane.OK_OPTION)
										{
											gameboard.newGame();
										}
									}
									statusLabel.setText("<html><div style='text-align: center;'>Your turn. Press [Backspace] to revert a step.</div></html>");
									gameboard.repaint();
								}
	
								@Override
								protected Void doInBackground() throws Exception {
									//copy state so that it does not affect drawing
									GameState gameState = new GameState(gameboard.GRID_ROWS, gameboard.GRID_COLS, startSide);
									gameState.setState(gameboard.gameState.getGameState());
									gameState.setCurrSide(gameboard.gameState.getCurrSide());
									GameTree agent = new GameTree(gameState, gameboard.SEARCH_TIME);
									
									//do work
									long start = System.currentTimeMillis();
									Move nextBestMove = agent.nextMove();
									long end = System.currentTimeMillis();
									System.out.println("ai move = "+nextBestMove+" move calculation time = "+(end-start)/1000.0+" s");

									//update current game state
									gameboard.gameState.makeMove(nextBestMove);

									return null;
								}
							}.execute();

				        
				        	
				        }
		        	}
		        	gameboard.selectedPos = null;
		        	gameboard.waitForSelection = true;
		        }
		        gameboard.repaint();
	     	}
	     }
	  });
	  
	  mainFrame.addKeyListener(new KeyAdapter() {
		  public void keyPressed(KeyEvent e) {
			  if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
				  System.out.println("revert move");
				  gameboard.gameState.revertOneMove();
				  if(gameboard.gameState.getCurrSide()!=gameboard.startSide){
					  gameboard.gameState.revertOneMove();
				  }
				  gameboard.repaint();
			  }
		  }
	  });
	  
	  mainFrame.add(gameboard);
	  mainFrame.setVisible(true); 
   }
}