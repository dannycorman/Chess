package game;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


/**
 * BoardState is where the main game info and piece positions are 
 * stored.
 * It also handles
 * The user doesn't directly interact with but accesses through commands in the 
 *ChessGame class.
 * 
 * @author Danny
 *
 */
public class BoardState implements Serializable{ 

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7753645969401131003L;
	private Color mTurn = Color.White;
	private boolean mGameOver = false;
	private int mboardSize = 8;
	private Color mWinner = null;

	//the main list where the all the pieces are stored
	private List<Piece> pieces = new ArrayList<Piece>();
	
	
	//experimental
	/**
	 * Creates a new arrayList and populates it with the pieces from the boardstate's 
	 * arrayList field, pieces,
	 * which match the color parameter.
	 * 
	 * @param sortedColor, a Color enum, either Color.Black, or Color.White,
	 * the color from which the user wants to receive all the piece objects 
	 * in the pieces ArrayList field
	 * @return an ArrayList of 
	 */
	public List<Piece> sortColor(Color sortedColor)
	{
		List <Piece> sortedPieces = new ArrayList<Piece>();
		for(Piece p: this.pieces){
			if (p.mColor == sortedColor){
				sortedPieces.add(p);
			}
		}
		return sortedPieces;
	}
	
	
	/**
	 * Checks to see if a player is in check or not, based off of if any of the 
	 * opposing player's possible moves can reach the king.
	 * @param a Color enum, Color.Black, or Color.White, representing the player 
	 * to be tested if they are in check.
	 * @return a true or false on whether the player is in check or not
	 */
	public boolean isCheck(Color sideColor){
		
		List<Piece> enemies = new ArrayList<Piece>();
		List<Piece> allies = new ArrayList<Piece>();
		Set<Coordinates> enemyMoves = new HashSet<Coordinates>();
		
		Piece CheckKing = new King(null,-1,-1);
		
		boolean inCheck = false;
		if(sideColor == Color.White){
				allies = sortColor(Color.White);
			 	enemies = sortColor(Color.Black);
		}else{
				allies = sortColor(Color.Black);
				enemies = sortColor(Color.White);
		}
		//finds king
		for(Piece a: allies){
			if(a instanceof King){
				CheckKing = a;
			}
		
		for(Piece e: enemies){
			
			
				 enemyMoves = e.getPossibleMoves(this);
		
			
			for(Coordinates coordinate: enemyMoves){
			
					if(coordinate.equals( CheckKing.getmCord())){
						inCheck = true;
				
					}
				
				
				}
		}
			
	}
		
		
		
		return inCheck;
		
	}
	
	
	/**
	 * This function tests to see whether a chosen player is in checkmate or not.
	 * if the player is not in check, the function assumes they can not be in checkmate. 
	 * if the player is in check, the function creates a deepclone of the boardstate and 
	 * sytematically, tests everyone of the players pieces in their possible moves,
	 * to see if any player move can prevent the enemy from reaching the king.
	 * if a piece can prevent the enemy. the player is not in check, and this 
	 * function returns a false. if the player is not able t prevent the enemy, this
	 * function returns true, and it calls the lose function, which makes the
	 * enemy player win, and the game ends.
	 * @param sideColor is a Color enum, Color.Black, or Color.White, representing
	 *  the player to be tested if they are in check.
	 * @return true or false on whether the player is in checkmate or not
	 */
	public boolean isCheckMate(Color sideColor){
		if(this.isCheck(sideColor) != true){
			return false;
		}
			
	
			BoardState clone = this.deepClone();
			List<Piece> allies = new ArrayList<Piece>();
			
			

			allies = clone.sortColor(sideColor);
			for(Piece p: allies){
				Coordinates orig = p.getmCord();
				for(Coordinates c: p.getPossibleMoves(this)){
					p.setmCord(c);
					if(!clone.isCheck(p.mColor)){
						return false;
					}
				}
				p.setmCord(orig);
			}
			
		this.lose(sideColor);
		return true;
	}
	//exp end
	
	
	
	/**
	 * very similar to the isCheckmate function, however instead of taking 
	 * a parameter, it looks at the player of whose turn it is for testing.
	 * And also unlike isCheckmate, This function can only return true if
	 * the player is not in check. So if a player who is not in check cannot make
	 * any moves, but is safe where they are, the function returns true, and also
	 * ends the game, but does not declare a winner.
	 * 
	 * 
	 * 
	 * @return true or false on whether the game is in stalemate or not.
	 */
	public boolean isStaleMate(){
		if(this.isCheck(mTurn) == true){
			return false;
		}
			
	
			BoardState clone = this.deepClone();
			List<Piece> allies = new ArrayList<Piece>();
			
			

			allies = clone.sortColor(mTurn);
			for(Piece p: allies){
				Coordinates orig = p.getmCord();
				for(Coordinates c: p.getPossibleMoves(this)){
					p.setmCord(c);
					if(!clone.isCheck(p.mColor)){
						return false;
					}
				}
				p.setmCord(orig);
			}
		mGameOver = true;
		System.out.println("StaleMate");
		return true;
	}
	
	
	
	/**
	 A function, for quick easy access to Piece objects from their corosponding Ids
	 * @return a map of the Piece IDs as keys, to their respective pieces.
	 */
	public Map<Integer,Piece> getMap(){
		
		Map<Integer,Piece> map = new HashMap<Integer,Piece>();
		for (Piece i : this.pieces) {
			map.put(i.getmID(),i);
		}
		
		return map;
		
		
	}
	
	
	/**
	 * a convenience method for finding a piece, given an ID
	 * @param ID is the uneque int id of the piece you want to find
	 * @return the Piece that has the param Id, if any, otherwise this returns a null.
	 */
	public Piece getPieceFromID(int ID){
		
		Map<Integer,Piece> idMap = getMap();
		return idMap.get(ID);
		
		}
	
	
	/**
	 A function, for quick easy access to Piece objects from their corresponding 
	 Coordinates
	 * @return a map of the Piece Coordinates as keys, to their respective pieces.
	 */
	public Map<Coordinates, Piece> cordToPieceMap(){
		
		Map<Coordinates,Piece> map = new HashMap<Coordinates,Piece>();
		for (Piece i : this.pieces) {
			map.put(i.getmCord(),i);
		}
		
		return map;	
	}

	/**
	 * a convenience method for finding a piece, given a Coordinates
	 * @param location is the Coordinates object of the piece you want to find
	 * @return the Piece that has the desired Coordinates, if any, otherwise this returns a null.
	 */
	public Piece getPieceAt(Coordinates location){
		
		Map<Coordinates,Piece> cordMap = cordToPieceMap();
		return cordMap.get(location);
		
		}

	
	/**
	 * 	hacky way of deep cloning a board state by serializing it and deserializing it
	 * @return An exact replica of this current boardState
	 */
	public BoardState deepClone(){
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);
			oos.flush();
			oos.close();
			byte[] bytes = baos.toByteArray();
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bis);
			return (BoardState) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException("Could not clone object", e);
		}
	}
	
	/**
	 * Creates a deep clone of the current boardstate, but with one piece moved to
	 * a different location. if the piece is moved to the location of an already
	 * existing piece, that piece gets captured and is removed.
	 * @param ID is the piece that should be moved within the deep clone
	 * @param cord is the location to which the piece in the deep clone should be placed
	 * @return an altered version of the current boardstate
	 */
	public BoardState cloneStateWithMovedPiece(int ID, Coordinates cord){
		BoardState clone = this.deepClone();

		Piece possibleVictim = clone.getPieceAt(cord);
		Piece toMove = clone.getPieceFromID(ID);

		clone.getPieces().remove(possibleVictim);
		Objects.requireNonNull(toMove, "THERE MUST NOT BE A PIECE WITH ID " + ID);
		toMove.setmCord(cord);
		
		
		return clone;
	}
	
	

	public BoardState(Piece... pieces) {
		this.pieces = new ArrayList<>(Arrays.asList(pieces));
	}
	
	
	
	public BoardState() {
		this.pieces = new ArrayList<>(Arrays.asList(
				new King(Color.White, 5, 8),
				new Queen(Color.White, 4, 8),
				new Bishop(Color.White, 3, 8),
				new Bishop(Color.White, 6, 8),
				new Knight(Color.White, 2, 8),
				new Knight(Color.White, 7, 8),
				new Rook(Color.White, 1, 8),
				new Rook(Color.White, 8, 8),
				
				new King(Color.Black, 5, 1),
				new Queen(Color.Black, 4, 1),
				new Bishop(Color.Black, 3, 1),
				new Bishop(Color.Black, 6, 1),
				new Knight(Color.Black, 2, 1),
				new Knight(Color.Black, 7, 1),
				new Rook(Color.Black, 1, 1),
				new Rook(Color.Black, 8, 1)
				));
		
		for (int i=1; i<9; i++){
			this.pieces.add(new Pawn(Color.White, i, 7));
		}
		for (int i=1; i<9; i++){
			this.pieces.add(new Pawn(Color.Black, i, 2));
		}
	}
	
	/**
	 * a getter for the Pieces arraylist field
	 * @return an arraylist of all the board pieces
	 */
	public List<Piece> getPieces() {
		return pieces;
	}

	/**
	 * modifies the turn field to alternate between Color.Black, and Color.White
	 * whenever this function is called.
	 */
	public void nextTurn(){
		if(mTurn == Color.White){
			mTurn = Color.Black;
		}else if(mTurn == Color.Black){
			mTurn = Color.White;
		}
	}
	
	/**
	 * calls the private function, lose on the player of the current turn.
	 * This causes the gameOver field to return true, and to make the winner field
	 * be the opposite of the current turn color.
	 */
	public void concede(){
		this.lose(mTurn);
		
	}
	
	
	private void lose(Color side){
		if(side == Color.White){
			mWinner = Color.Black;
			System.out.println("Game over. Black was victorious");
		}else if(side == Color.Black){
			mWinner = Color.White;
			System.out.println("Game over. White was victorious");
		}
		mGameOver = true;
		
	}
	
	/**
	 * a getter for the mTurn field
	 * @return which player's turn it is.
	 */
	public Color getColor(){
		return mTurn;
	}
	
	/**
	 * a getter for the mWinner field
	 * @return which color won, if either, otherwise this returns a null
	 */
	public Color getWinner(){
		return mWinner;
	}
	
	/**
	 * a getter for the mGameOver field
	 * @return a true or false, on whether the game has reached an end state or not.
	 */
	public boolean isGameOver(){
		return mGameOver;
	}
	
	

}

