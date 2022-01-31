package game;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.HashSet;
import java.util.List;

import java.util.Set;


import org.junit.Assert;
import org.junit.Test;


//UNIT TESTS FOR THE GAME
public class ChessTest {
	
	
//Test to make sure two coordinates are equal if their x and y coordinates match
	@Test
	public void areCordinateEqual(){
		
		Coordinates equal = new Coordinates(1,2);
		Coordinates check = new Coordinates(1,2);
		
		Assert.assertEquals(equal,check);
		
		
	
	}

//Test make sure two sets of coordinates are equal if they have the same amount of coordinates
	//and those coordinates have the match x and y coords.
	@Test
	public void areCoordinateSetsEqual(){
		
		Coordinates equal1 = new Coordinates(1,2);
		Coordinates check1 = new Coordinates(1,2);
		
		Coordinates equal2 = new Coordinates(1,2);
		Coordinates check2 = new Coordinates(1,2);
		
		Set<Coordinates> set1 = new HashSet<Coordinates>();
		set1.add(equal1);
		set1.add(check1);
		
		Set<Coordinates> set2 = new HashSet<Coordinates>();
		set2.add(equal2);
		set2.add(check2);
		
		Assert.assertEquals(set1, set2);
		
	}
	
//boardState has a few convenience methods which maps IDs to Their Pieces
	//this test makes sure that what is returned from the piece map is in fact piece itself.
	@Test
	public void pieceMapWorks(){
		Piece king1 = new King(Color.White, 3, 3);
		
		Piece king2 = new King(Color.Black, 5, 5);
		
		BoardState state = new BoardState(
				king1,
				king2);
		
		Assert.assertEquals(state.getPieceFromID(king1.getmID()), king1);
		
		
	
	}
	
	
	
	//base test for when game calls move piece, to make sure the piece's cordinates in the games state 
	//have been changed to the expected values. And checks to make sure the boolean value of the movepiece 
	//function is true.
	@Test
	public void movePieceTest(){
		Piece king1 = new King(Color.White, 1, 2);
		Piece king2 = new King(Color.White, 6, 7);
		
		
		BoardState state = new BoardState(
						king1,
						king2,
						new Pawn(Color.White, 5, 2));
		ChessGame game = new ChessGame(state);
		int king1ID = king1.getmID();
		//int king2ID = king2.getmID();
	    
		
		boolean works = game.movePiece(king1ID, 2, 2);
		Coordinates check1 = new Coordinates(2,2);
	
		
		Assert.assertEquals(game.checkState().getPieces().get(0).getmCord(), 
				check1);

		Assert.assertTrue(works);
	}
	
	//Tests to make sure invalid moves return false, keep the players turn the same, and keep the piece in its original 
	//position. The last move is valid so it returns true, the turn changes, and the piece is no longer in its start position
	@Test
	public void movePieceOutofRangeTest(){
		Piece subject = new Queen(Color.White, 3, 3);
		Coordinates start = subject.getmCord();
		
		BoardState state = new BoardState(
				new King(Color.White, 1, 2),
				new King(Color.Black, 6, 7),
				
				subject,
				//Obstacles
				new Pawn(Color.White, 4, 2),
				new Pawn(Color.Black, 5, 5)
				
				);
		
		ChessGame game = new ChessGame(state);
		
		boolean try1 = game.movePiece(subject.getmID(), 5, 8);
		
		
		Assert.assertTrue(try1 == false && game.currentTurn() == Color.White && subject.getmCord() == start );
		
		boolean try2 = game.movePiece(subject.getmID(), 3, 30);
		
		Assert.assertTrue(try2 == false && game.currentTurn() == Color.White && subject.getmCord() == start);
		
		boolean try3 = game.movePiece(subject.getmID(), 3,8 );
		
		Assert.assertTrue(try3 == true);
		
		Assert.assertEquals(game.currentTurn(), Color.Black);
		
		Coordinates start3 = new Coordinates(3,3);
		
		Assert.assertFalse(game.checkState().getPieceFromID(subject.getmID()).equals( start3));
		
		
	}
	//Makes sure that each time a piece is move on its respecting color's turn, currentTurn changes.
	
	@Test
	public void changeTurnsTest(){
		BoardState state = new BoardState(
				new King(Color.White, 1, 2),
				new King(Color.Black, 6, 7),
				new Pawn(Color.White, 4, 2));
		ChessGame game = new ChessGame(state);
		int king1 = state.getPieces().get(0).getmID();
		int king2 = state.getPieces().get(1).getmID();
		
		Assert.assertEquals(game.currentTurn(), Color.White);//white always 
		//goes first
		
		game.movePiece(king1, 2, 2);
		
		Assert.assertEquals(game.currentTurn(), Color.Black); 
		game.movePiece(king2, 7, 7);
		
		Assert.assertEquals(game.currentTurn(), Color.White);
		
		//This last test checks to make sure the black piece can't be moved, as it is whites turn.
		Assert.assertFalse(game.movePiece(king2, 7, 8));
		
	}
	
	
	
	
	//The next 6 unit tests check example possible moves of each type of piece and make sure they equal how their movement
	//should behave given each tests setup, including colliding with enemies, capturing enemies, getting blocked by allies, and not going off
	//the board.
	
	
	
	//This king is also ristricted by not being able to put himself in check
	
	
	
	
	@Test
	public void queenPossibleMovesTest(){
		Piece subject = new Queen(Color.White, 3, 3);

		BoardState state = new BoardState(
				new King(Color.White, 1, 2),
				new King(Color.Black, 6, 7),
				
				subject,
				//Obstacles
				new Pawn(Color.White, 4, 2),
				new Pawn(Color.Black, 5, 5)
				
				);

		 
		 Set<Coordinates> expectedOutput = new HashSet<Coordinates>(
				 Arrays.asList(
						 new Coordinates(1,1),
						 new Coordinates(1,3),
						 new Coordinates(1,5),
						 new Coordinates(2,2),
						 new Coordinates(2,3),
						 new Coordinates(2,4),
						 new Coordinates(3,1),
						 new Coordinates(3,2),
						 new Coordinates(3,4),
						 new Coordinates(3,5),
						 new Coordinates(3,6),
						 new Coordinates(3,7),
						 new Coordinates(3,8),
						 new Coordinates(4,3),
						 new Coordinates(4,4),
						 new Coordinates(5,3), 
						 new Coordinates(5,5),
				 		 new Coordinates(6,3),
				 		 new Coordinates(7,3),
				 		 new Coordinates(8,3))
				 );
		 Assert.assertEquals(expectedOutput,subject.getPossibleMoves(state));
		
	}
	
	@Test
	public void rookPossibleMovesTest(){
		Piece subject = new Rook(Color.White, 3, 3);

		BoardState state = new BoardState(
				new King(Color.White, 1, 2),
				new King(Color.Black, 6, 7),
				
				subject,
				//Obstacles
				new Pawn(Color.White, 3, 2),
				new Pawn(Color.Black, 5, 3));
		
		
		 
		 Set<Coordinates> expectedOutput = new HashSet<Coordinates>(
				 Arrays.asList(
						
						 new Coordinates(1,3),
						
						 new Coordinates(2,3),
						
						 new Coordinates(3,4),
						 new Coordinates(3,5),
						 new Coordinates(3,6),
						 new Coordinates(3,7),
						 new Coordinates(3,8),
						 new Coordinates(4,3),
						
						 new Coordinates(5,3)) 
						 
				 );
		 Assert.assertEquals(subject.getPossibleMoves(state),
				 expectedOutput);
		
	}
	
	@Test
	public void BishopPossibleMovesTest(){
		Piece subject = new Bishop(Color.White, 3, 3);

		BoardState state = new BoardState(
				new King(Color.White, 1, 2),
				new King(Color.Black, 6, 7),
				
				subject,
				//Obstacles
				new Pawn(Color.White, 4, 2),
				new Pawn(Color.Black, 5, 3),
				new Pawn(Color.Black, 5, 1),
				new Pawn(Color.Black, 5, 5));
		
		

		
		 
		 Set<Coordinates> expectedOutput = new HashSet<Coordinates>(
				 Arrays.asList(
						 new Coordinates(1,1),
						 new Coordinates(1,5),
						 new Coordinates(2,2),
						 new Coordinates(2,4),
						 new Coordinates(4,4), 
						 new Coordinates(5,5))
			
				 );
		 Assert.assertEquals(subject.getPossibleMoves(state),
				 expectedOutput);
		
	}
	@Test
	public void KnightPossibleMovesTest(){
		Piece subject = new Knight(Color.White, 2, 3);

		BoardState state = new BoardState(
				new King(Color.White, 1, 2),
				new King(Color.Black, 6, 7),
				
				subject,
				//Obstacles
				new Pawn(Color.White, 3, 3),
				new Pawn(Color.White, 3, 2),
				new Pawn(Color.White, 3, 5),
				new Pawn(Color.Black, 4, 2),
				new Pawn(Color.Black, 2, 2));
		
		
		//ChessGame game = new ChessGame(state);
		
		 
		 Set<Coordinates> expectedOutput = new HashSet<Coordinates>(
				 Arrays.asList(
						 new Coordinates(1,1),
						 new Coordinates(1,5),
						 new Coordinates(3,1),
						 new Coordinates(4,2),
						 new Coordinates(4,4))
			
				 );
		 Assert.assertEquals(subject.getPossibleMoves(state),
				 expectedOutput);
		
	}
	
	//This tests two pawns. the second pawn, subject 2, is at a traditional pawn starting location so it can move
	//forward 2 spaces
	@Test
	public void pawnPossibleMovesTest(){
		Piece subject = new Pawn(Color.White, 3, 3);
		Piece subject2 = new Pawn(Color.White, 4, 7);

		BoardState state = new BoardState(
				new King(Color.White, 1, 2),
				new King(Color.Black, 6, 7),
				
				subject,
				subject2,
				//Obstacles
				
				new Pawn(Color.Black, 4, 2));
		 
		 Set<Coordinates> expectedOutput = new HashSet<Coordinates>(
				 Arrays.asList(
						
						 new Coordinates(4,2),
						
						 new Coordinates(3,2)) 
						 
				 );
		 
		 //white pawn can move two spaces up if on the 7th row
		 Set<Coordinates> twoMovesexpectedOutput = new HashSet<Coordinates>(
				 Arrays.asList(
						
						 new Coordinates(4,6),
						
						 new Coordinates(4,5)) 
						 
				 );
		 
		 Assert.assertEquals(subject.getPossibleMoves(state),
				 expectedOutput);
		 
		 Assert.assertEquals(subject2.getPossibleMoves(state),
				 twoMovesexpectedOutput);
	}
	
	
	//Tests to see if a pawn moves to the end of the board it's instance changes to a queen
	@Test
	public void pawnBecomesQueen()
	{
		
		Piece subject = new Pawn(Color.White, 3, 2);
	    BoardState state = new BoardState(
				new King(Color.White, 1, 2),
				subject,
				new King(Color.Black, 6, 7));
	    
	    int subjectID = subject.getmID();
	    
	    ChessGame game = new ChessGame(state);
	    
	    
	    Assert.assertTrue(state.getPieceFromID(subjectID) instanceof Pawn);
	    
	    Assert.assertTrue(game.movePiece(subjectID, 3, 1));
	    
	   
	    		
	    //Assert.assertTrue(game.checkState().getPieceFromID(subjectID) instanceof Pawn);
	     Assert.assertTrue(game.checkState().getPieceFromID(subjectID) instanceof Queen);
	            
	      
	}
	
	
	
	
	
	
	//Tests to see that a knight can only captures enemy pieces which it lands on.
	@Test
	public void knightOnlycapturesEnd()
	{
		Piece subject = new Knight(Color.White, 4, 4);
		Piece obst1 = new Pawn(Color.White, 4, 3);
		Piece obst2 = new Pawn(Color.Black, 4, 2);
		Piece obst3 = new Pawn(Color.Black, 5, 2);
		
	    BoardState state = new BoardState(
				new King(Color.White, 1, 2),
				new King(Color.Black, 6, 7),
				subject,
				obst1,
				obst2,
				obst3);
	    
	    int subjectID = subject.getmID();
	    
	    ChessGame game = new ChessGame(state);
	    
	    Assert.assertTrue(game.movePiece(subjectID, 5, 2));
	    
	   state = game.checkState();
	    Coordinates c = new Coordinates(5,2);
	    Assert.assertTrue(state.getPieceFromID(obst1.getmID())!= null);
	    Assert.assertTrue(state.getPieceFromID(obst2.getmID())!= null);
	    
	    Assert.assertTrue(state.getPieceFromID(subject.getmID()).getmCord().equals(c));
	    Assert.assertTrue(state.getPieceFromID(obst3.getmID())== null);
	    
	            
	      
	}
	
	
	
	
	//tests to see if this checkmate position causes getwinner to no longer be null
	//and makes sure getWinner is black in this case
	@Test
	public void isInCheckMate(){
		
		
		 BoardState state = new BoardState(
					new King(Color.White, 1, 1),
						
					new King(Color.Black, 6, 7),
					//Obstacles
					
					new Rook(Color.Black, 1, 3),
					new Queen(Color.Black, 3, 1));
		 
		ChessGame game = new ChessGame(state);
		 
		 Assert.assertTrue(state.isCheckMate(Color.White));
		 Assert.assertTrue(state.isGameOver());
		 
		 /*int times = 10000;
		 long start = System.currentTimeMillis();
		 for(int i = 0;i<times;i++) {
			 state.isCheckMate(Color.White);
		 }
		 long end = System.currentTimeMillis();
		 double avg = (times) / (((double)(end - start)) / 1000);
		 System.out.printf("Total time: %dms. Avg: %.5f checks/sec", (end - start), avg);*/
		// Assert.assertTrue(game.getWinner() != null);
		// Assert.assertTrue(game.getWinner() == Color.Black);
	}
	
	//Sets pieces in a stalemate position.
	//if no piece has any possible moves, it is a true stalemate.
	@Test
	public void isStalemate(){
		
		 BoardState state = new BoardState(
					new King(Color.White, 1, 1),
						
				
					//Obstacles
					new King(Color.Black, 2, 3),
					new Queen(Color.Black, 3, 2));
		 
		 List<Piece> pieces = state.getPieces();

		    for(Piece p: pieces){
		        
		                Assert.assertTrue(state.isStaleMate());
		                Assert.assertTrue(state.isGameOver());
		                Assert.assertTrue(state.getWinner()==null);

		    }
		 
		
	}
	
	//First checks to make sure the only available moves for the king is to get out of check
	//second, tests to make sure the only available move for the rook is to protect the king
	
	
	
	// Initially asserts that there are no winners and that it is whites turn. 
	//forfeit is then called on that turn and then afterward get winner equals black.
	@Test
	public void forfeitTest(){
		
		BoardState state = new BoardState(
				new King(Color.White, 1, 2),
				new King(Color.Black, 6, 7),
				
				//Obstacles
				new Pawn(Color.White, 4, 2),
				new Pawn(Color.Black, 5, 3));
		
		ChessGame game = new ChessGame(state);
		
		Assert.assertTrue(game.whoWon()== null && game.currentTurn() == Color.White);
		
		game.forfeit();
		
		Assert.assertTrue(game.whoWon()== Color.Black);

	}
	
	@Test
	public void isCheck(){
			Piece king = new King(Color.White, 8, 8);
			//Piece rook = new Rook(Color.White, 4, 6);
			
			 BoardState state = new BoardState(
					 	
						king,	
						new King(Color.Black, 6, 7),
						//Obstacles
						
						new Queen(Color.Black, 2, 8));
			 
			 Assert.assertTrue(state.isCheck(Color.White));
		
	}
	
	@Test
	public void deepCloneTest(){
		Piece king = new King(Color.White, 8, 4);
		Piece rook = new Rook(Color.White, 4, 6);
		
		 BoardState state = new BoardState(
				 	
					king,	
					new King(Color.Black, 6, 7),
					//Obstacles
					
					new Queen(Color.Black, 1, 4)); 
		 
		 BoardState clone = state.deepClone();
		 
		 
		 
		 Assert.assertFalse(clone == state);
		 Assert.assertTrue(state.getPieces().size() == clone.getPieces().size() );
	}
	
	
	
	@Test
	public void Premade(){
		
		BoardState state = new BoardState();
		Coordinates c = new Coordinates(5,1);
		Assert.assertTrue(state.getPieceAt(c) instanceof King);
		c = new Coordinates(1,2);
		Assert.assertTrue(state.getPieceAt(c) instanceof Pawn);
		c = new Coordinates(8,2);
		Assert.assertTrue(state.getPieceAt(c) instanceof Pawn);
	}
	
}
	// end
	
	
