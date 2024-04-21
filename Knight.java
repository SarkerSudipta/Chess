

public class Knight extends Piece {

    public Knight(String color) {
        super(color);
    }

    public void print() {
        if (super.getColor().equals("White"))
            System.out.print("N");
        else
            System.out.print("n");
    }

    public void printName(){
        System.out.print("Knight");
    }

    public boolean validateMove(Move move, Board board) {

        int changeInRow = Math.abs(move.getDestRow() - move.getOriginRow());
        int changeInCol = Math.abs(move.getDestCol() - move.getOriginCol());

        if( changeInRow == 1  &&  changeInCol == 2)
            return true;
        else if( changeInRow == 2 && changeInCol == 1)
            return true;
        else
            return false;
    }

    // get all possible valid moves of the Computer's Knight
    public LinkedList possibleMovesOfBlackPiece(int originRow, int originCol, Board board) 
    {
        LinkedList possibleMoves = new LinkedList();

        int[] dx = { -1, -1, 1, 1, -2, -2, 2, 2 };
        int[] dy = { -2, 2, -2, 2, -1, 1, -1, 1 };

        for (int k = 0; k < 8; k++) 
        { 
            int newRow = originRow + dx[k];
            int newCol = originCol + dy[k];
            Move move = new Move(originRow, originCol, newRow, newCol);
            if (move.validateMove(board)) {
                possibleMoves.add(move);
            }
        }
        return possibleMoves;
    }


    // get all possible valid moves of the User's Knight
    public LinkedList possibleMovesOfWhitePiece(int originRow, int originCol, Board board) 
    {
       return possibleMovesOfBlackPiece(originRow, originCol, board);
    }
}
