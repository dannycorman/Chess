package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import game.BoardState;
import game.ChessGame;
import game.Coordinates;
import game.Piece;

//Gui for chess game


public class GuiGoo extends JComponent {
	static final int GRID_SIZE = 8;
	boolean squareSelected = false;
	int selectedRow = -1;
	int selectedCol = -1;
	ChessGame gam;
	public GuiGoo() {
		gam = new ChessGame(new BoardState());
		System.out.println(gam.checkState());
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			/**
			 * Listens for if and where a mouse was clicked on the board
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = e.getY() / (getHeight() / GRID_SIZE);
				int col = e.getX() / (getWidth() / GRID_SIZE);
				attemptSelect(row, col);
				System.out.println("Clicked on x= " + e.getX() + ", y = " + e.getY());
			}
		});
	}
	
	private void attemptSelect(int row, int col) {
		repaint();
		if(squareSelected && row == selectedRow && col == selectedCol) {
			// de-select
			deselect();
		} else if (row >= GRID_SIZE || col >= GRID_SIZE ){
			deselect();
		} else if (squareSelected) {
			// There was a square selected, and they just clicked on another one
			// Consider it an attempted move
			attemptMove(row, col);
			deselect();
		} else{
			selectedRow = row;
			selectedCol = col;
			squareSelected = true;
			System.out.println("Selected a square!");
		}
	}
	
	/**
	 * handles attempted movement after a piece has been selected.
	 * utilizes the classes chessgame object for rule reinforcement, 
	 * @param row represents vertically what space was selected for an attempted move
	 * @param col represents horizontally what space was selected for an attempted move
	 */
	public void attemptMove(int row, int col) {
		
		
		Piece source = gam.checkState().getPieceAt(new Coordinates(selectedCol+1, selectedRow+1));
		if(null == source) {
			// do nothing
		}else if(gam.checkState().isGameOver() == true ) {
			
		
		} else {
			if(source.getColor() != gam.currentTurn()) {
				JOptionPane.showMessageDialog(this, "IT'S NOT YOUR TURN");
			} else {
				boolean result = gam.movePiece(source.getmID(), col + 1, row + 1);
				if(!result) {
					JOptionPane.showMessageDialog(this, "INVALID MOVE");
				}
			}
		}
	}
	
	private void deselect() {
		selectedRow = -1;
		selectedCol = -1;
		squareSelected = false;
		
		if(gam.whoWon() == game.Color.Black ) {
		JOptionPane.showMessageDialog(this, "Game over. Black wins.");
	}else if (gam.whoWon() == game.Color.White ){
		JOptionPane.showMessageDialog(this, "Game over. White wins");
	}else if (gam.checkState().isGameOver() == true ){
		JOptionPane.showMessageDialog(this, "Stalemate");
	}
		repaint();
	}
	
	/**
	 * Paints the actual board, and current state of the board
	 */
	@Override
	public void paintComponent(Graphics g) {
		
		
		super.paintComponent(g);
		BoardState gamState = gam.checkState();
		Map<Coordinates, Piece> pcMap = gamState.cordToPieceMap();
		
		System.out.println("Painted!");
		

		// first draw grid
		int squareWidth = this.getWidth() / GRID_SIZE;
		int squareHeight = this.getHeight() / GRID_SIZE;
		// i is row
		for(int i = 0;i<GRID_SIZE;i++) {
			boolean whiteSquare = i % 2 == 0;
			// k is column
			for(int k = 0;k<GRID_SIZE;k++) {
				int y = i * squareHeight;
				int x = k * squareWidth;
				if(whiteSquare) {
					g.setColor(Color.WHITE);
				} else {
					g.setColor(Color.GRAY);
				}
				g.fillRect(x, y, squareWidth, squareHeight);
				// Check if a PIECE is here
				Piece p = pcMap.get(new Coordinates(k + 1, i + 1));
				if(null != p) {
					if(p.getColor() == game.Color.Black)
						g.setColor(Color.BLACK);
					else
						g.setColor(Color.CYAN);
					g.drawString(p.getClass().getSimpleName(), x + 10, y + 10);
				}
				if(i == selectedRow && k == selectedCol) {
					// make a little border
					g.drawRect(x, y, squareWidth-2, squareHeight-2);
					g.drawRect(x + 2, y + 2, squareWidth - 4, squareHeight - 4);
				}
				whiteSquare = !whiteSquare;
			}
		}
		
	}
}
