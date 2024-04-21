public class Bishop extends Piece {

    public Bishop(String color) {
        super(color);
    }

    public void print() {
        if (super.getColor().equals("White"))
            System.out.print("B");
        else
            System.out.print("b");
    }
    public void printName(){
        System.out.print("Bishop");
    }

    // true if move is diagonal and path of move is clear
    public boolean validateMove(Move move, Board board) {
        if (move.isMoveDiagonal() && board.checkPathOfDiagonalMove(move))
            return true;
        else
            return false;
    }

    // get all possible valid moves of the Bishop
    public LinkedList possibleMovesOfBlackPiece(int originRow, int originCol, Board board) {
        LinkedList possibleMoves = new LinkedList();

        //direction in x and y direction
        //upLeft, upRight, downLeft, downRight
        int dx[] = {-1, -1,  1,  1};
        int dy[] = {-1,  1, -1,  1};

        // Loop through all possible directions
        for (int k = 0; k < dx.length; k++) 
        {   //Loop through each step in the current direction
            for (int i = 1; i <= 7; i++) 
            {
                int newRow = originRow + dx[k] * i;
                int newCol = originCol + dy[k] * i;
                Move move = new Move(originRow, originCol, newRow, newCol);

                if (move.validateMove(board))
                    possibleMoves.add(move);
                    // If a move is invalid, stop searching in that direction
                else 
                    break;
            }
        }
        return possibleMoves;
    }

    // get all possible valid moves of the User's Bishop
    public LinkedList possibleMovesOfWhitePiece(int originRow, int originCol, Board board) 
    {
       return possibleMovesOfBlackPiece(originRow, originCol, board);
    }
}
