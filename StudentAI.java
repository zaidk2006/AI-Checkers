import java.util.Random;
import java.util.Vector;
import java.util.ArrayList;

// The following part should be completed by students.
// Students can modify anything except the class name and exisiting functions and varibles.

public class StudentAI extends AI {
    public StudentAI(int col, int row, int k) throws InvalidParameterError {
        super(col, row, k);

        this.board = new Board(col, row, k);
        this.board.initializeGame();
        this.player = 2;
    }

    public Move GetMove(Move move) throws InvalidMoveError {
        if (!move.seq.isEmpty())
            board.makeMove(move, (player == 1) ? 2 : 1);
        else
            player = 1;
        Vector<Vector<Move>> moves = board.getAllPossibleMoves(player);
  //im commitng right here
        //Random randGen = new Random();
        //int index = randGen.nextInt(moves.size());
        //int innerIndex = randGen.nextInt(moves.get(index).size());
        Move resMove = ChooseBestMove(moves);
        board.makeMove(resMove, player);
        return resMove;
		
    }
	
	public Move ChooseBestMove(Vector<Vector<Move>> moves) throws InvalidMoveError {
		//ArrayList<Integer> moveScore = new ArrayList<>();
		int max = -500;
		int index = -1; 
		int innerIndex = -1;
		int i = 0;
		int j = 0;
		for(Vector<Move> vector : moves) {
			for(Move move : vector) {
        board.makeMove(move, player);
        int score;
        if(player == 1){
		      score = board.blackCount - board.whiteCount;
        }
        else
        {
          score =  board.whiteCount - board.blackCount;
				}
        //moveScore.add(score);
				//System.out.println("SCORE: " + score);
        if(score > max) {
       		max = score;
					index = i;
					innerIndex = j;
				}
	      board.Undo();
				j++;
			}
			i++;	
    }
	
	  //System.out.println("Index: " + index + ", Inner Index: " + innerIndex);	
    Move bestMove = moves.get(index).get(innerIndex);
    return bestMove;
  }
}

/*
 * Compute difference of checkers count for each possible move, count King as 2 (Ours - Opponent) 
 * Choose move with highest value
 */
