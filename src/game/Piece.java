package game;

import java.io.Serializable;

/**
 * A piece is an an abstract object which extends a kind of piece object
 */

import java.util.Set;

public abstract class Piece implements Serializable{
	protected Coordinates mCord;

	protected Color mColor;
	private final int mID;

	private static int nextID = 0;

	public static int newID() {
		return nextID++;
	}

	public Piece(Color color, Coordinates cord) {
		
		mID = newID();
		mColor = color;
		setmCord(cord);
	}
	
	public Piece(int ID, Color color, Coordinates cord) {
		
		mID = ID;
		mColor = color;
		setmCord(cord);
	}

	/**
	 * A getter for the mCord field
	 * @return Coordinates for where on the board the piece is located
	 */
	public Coordinates getmCord() {
		return mCord;
	}
	
	/**
	 * A getter for the mID field
	 * @return the unique identifier of this particular piece.
	 */
	public int getmID() {
		return mID;
	}
	
	
	/**
	 * a setter for mCord
	 * @param mCord is the coordinates for which the piece is to be moved to.
	 */
	public void setmCord(Coordinates mCord) {
		if(mCord == null){
			throw new NullPointerException();
		}
		this.mCord = mCord;
	}

	/**
	 * a method which doesn't do anything on its own but is overridden by the 6
	 * child classes of Piece class, to.
	 * @param moves is for determining where pieces can and can't go on a board, based
	 * on where the other pieces are located.
	 * @return a list of all the valid places a piece can move, assuming the player
	 * is not in check and it is their turn.
	 */
	public Set<Coordinates> getPossibleMoves(BoardState moves) {

		return null;
	}
	

	
	/**
	 * a getter for the field mColor
	 * @return the color of this particular piece
	 */
	public Color getColor() {
		return this.mColor;
	}
	
	/**
	 * Overrides .equals of Piece, so it compares the IDs of two pieces
	 */
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(!(o instanceof Piece))
			return false;
		return ((Piece) o).mID == mID;
	}
		
	

}
