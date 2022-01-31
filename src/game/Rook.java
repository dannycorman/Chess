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
public class Rook extends Piece implements Serializable{
	private Color mcolor = super.mColor;
	private Coordinates mcord = super.mCord;

	public Rook(Color color, Coordinates cord) {
		super(color, cord);
		// TODO Auto-generated constructor stub
	}
	
	public Rook(Color color, int x, int y) {
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

		Set<Coordinates> result = new HashSet<>();
		for(int x =-1; x <=1; x++){
			for(int y= -1; y <=1; y++){
				if(x==0&&y==0 || x!=0 && y!=0){
					continue;
				}
				int checkx = super.getmCord().getx();
				int checky = super.getmCord().gety();
				while (true){
					checkx+=x;
					checky+=y;
					Coordinates checkCord = new Coordinates(checkx, checky);
					if(checkCord.outOfBounds()){
						break;
					}
					Piece p = moves.getPieceAt(checkCord);
					if(p == null){
						result.add(checkCord);
					}else if(p.getColor()!= mcolor){
						result.add(checkCord);
						break;
					}else{
						break;
					}
				}
				
			}
		}
		return result;
	}

}
