package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

/**

 * This is what the GUI interacts with. 

 * Any game requests from the users come through here in the form of functions calls.
 * The Chessgame object has an encapsulated BoardState object, which it can access 
 * and modify through getters and setters.  
 * This class also contains movement rule enforcement.
 * @author Danny Corman
*/	
	

public class ChessGame implements Serializable{
	
	private BoardState mState;
	
	
	
	
	public ChessGame() {
		
	}
	
	
	public ChessGame(BoardState initialState) {
		this.mState = initialState.deepClone();
	}
	

	
	//The user enters in a pieces ID, and where they want to place it
	
	/**
	 * The user enters in a pieces ID, 
	 * and x and y coordinates representing where they want to move it.
	 * This function then validates whether that move is legal based on the boardState
	 * and rules of a chess game.
	 * if the move is legal, the piece is placed at its new location. any pieces that
	 * were at that location get removed from the boardstate, and it becomes the other 
	 * players turn.
	 * @param ID is the Id of the piece that is to be moved
	 * @param x is where horizontally the piece going to attempt to move
	 * @param y is where vertically the piece going to attempt to move
	 * @return true or false on whether or not the attempted move was successful.
	 */
	public boolean movePiece(int ID, int x, int y ){
		Piece move = mState.getPieceFromID(ID);
		boolean check = false;
		if(mState.isGameOver()==true){
			
			System.out.println("Move failed. Game over");
			return false;
		}
		if(move.getColor() != mState.getColor()){
			
			System.out.println("Move failed. It is not that color's turn");
			return false;
		}
		Coordinates cord = new Coordinates(x,y);
		
		if(move.getPossibleMoves(mState).contains(cord)){
			BoardState clone = mState.cloneStateWithMovedPiece(ID, cord);
			if(clone.isCheck(move.getColor())){
				check = true;
				System.out.println("Move failed. That would put your king in check");
				return false;
			}else{
				mState = clone;
				
			if(mState.getPieceFromID(ID) instanceof Pawn && (y==1 || y==8)){
				Piece pawn = mState.getPieceFromID(ID);
					
				
					
					int pwnID = pawn.getmID();
				Color pwnColor = pawn.getColor();
	//				
						mState.getPieces().remove(pawn);
					Piece q = new Queen(pwnID, pwnColor, x, y);
	////					
					mState.getPieces().add(q);
					System.out.println("your pawn became a queen");	
//					
			}
						
				mState.nextTurn();
				System.out.println(mState.getColor()+"'s turn");
				mState.isCheckMate(mState.getColor());
				mState.isStaleMate();
				return true;
			}
		}else{ 
		
			System.out.println("Move failed. You can't move that piece there");
			return false;
		}
	}
	/**
	 *Gives the user an exact replica of the ChessGame's current BoardState
	 *Will never return null
	 * 
	 * @return clone of this chessGame's BoardState
	 */
	public BoardState checkState(){
		return mState.deepClone();
	}
	
	/**
	 *Displays to the user whose turn it is
	 * 
	 * @return enum Color.Black, or Color.White
	 */
	public Color currentTurn(){
		return mState.getColor();
	}
	
	
	
	/**
	*Displays to the user who won as represented by a Color,
	* if anyone has, otherwise it returns a Null
	 * 
	 * @return enum Color.Black, or Color.White
	 */
	public Color whoWon(){
		return mState.getWinner();
	}
	
	
	
	/**
	 * 
	 * when its the users turn they can forfeit meaning their opponent instantly wins.
	 * This method calls a chain of BoardState methods which ends the game, and makes
	 * the opponent of the current turn's color win.
	 */
	public void forfeit(){
		mState.concede();
	}
	
	
	
}
