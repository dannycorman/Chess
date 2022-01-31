package game;

import java.io.Serializable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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

import java.util.Set;

public class Knight extends Piece implements Serializable{
	private static final List<Coordinates> moveList = 
			Arrays.asList(new Coordinates(-2,-1),
					new Coordinates(-1,-2),
					new Coordinates(1,-2),
					new Coordinates(2,-1),
					new Coordinates(2,1),
					new Coordinates(1,2),
					new Coordinates(-1,2),
					new Coordinates(-2,1)
					);
	public Knight(Color color, Coordinates cord)
	{
		super(color, cord);
	}
	
	public Knight(Color color, int x, int y) {
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
	public Set<Coordinates> getPossibleMoves(BoardState moves){
	
		Set<Coordinates> result = new HashSet<>();
		for (Coordinates c : moveList){
			
	
			Coordinates checkCord = c.addCords(mCord);
			if(checkCord.outOfBounds() != true){
				Piece p = moves.getPieceAt(checkCord);
				if( p == null){
					result.add(checkCord);
				}else if(p.getColor()!= mColor){
					result.add(checkCord);
				}
			
			}
		}
		return result;
	}

	
		
}
