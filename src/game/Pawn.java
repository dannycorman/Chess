package game;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
* 
* 
* 
* A kind of game piece class which inherits piece class. 
*It constructs the object with a color, and coordinates, and carries out unit
* specific movements.
*  
*  It is used by the BoardState class as in-game Pieces, which can be manipulated
*   by the ChessGame class. 
*   
*   @author Daniel Corman
*/
public class Pawn extends Piece implements Serializable{
	
	

	public Pawn(Color color, Coordinates cord) {
		super(color, cord);
		// TODO Auto-generated constructor stub
	}
	
	public Pawn(Color color, int x, int y) {
		super(color, new Coordinates(x, y));
	}

	/**
	 * 
	 * 
	 *
	 *getPossibleMoves assumes the user is not currently in check, and 
	 *that it is the user's turn.
	 *
	 *@return Set of legal coordinates that the Piece can move to
	 *with it's specific movement rules in place.
	 * 
	 */
	@Override
	public Set<Coordinates> getPossibleMoves(BoardState moves)
	{	
		int direct = 1;
		if(mColor == Color.White){
			direct = -1;
		}
		Set<Coordinates> result = new HashSet<>();
		
		int checkx = super.getmCord().getx();
		int checky = super.getmCord().gety();
		
		if(mCord.gety() == 7 && mColor == Color.White || 
				mCord.gety() == 2 && mColor == Color.Black){
			Coordinates frontTwo = new Coordinates(checkx, checky+direct*2);
			result.add(frontTwo);
		}
		Coordinates front = new Coordinates(checkx, checky+direct);
		
		
		
		if(front.outOfBounds() != true){
			Coordinates left = new Coordinates(checkx-1, checky+direct);
			Coordinates right = new Coordinates(checkx+1, checky+direct);
			
			//Piece front = moves.getPiece(front);
			
			if(moves.getPieceAt(front) == null){
				result.add(front);
			}
			if(moves.getPieceAt(left) != null && 
					moves.getPieceAt(left).getColor() != mColor){
				result.add(left);
			}
			if(moves.getPieceAt(right) != null && 
					moves.getPieceAt(right).getColor() != mColor){
				result.add(right);
			}
			
		
		}
		
		
		
		
		
		return result;
	}

}
