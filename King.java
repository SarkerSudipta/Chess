public class King extends Piece{

    public King(String color){
        super(color);
    }
    public void print(){
        if( super.getColor().equals("White") )
            System.out.print("K");
        else 
            System.out.print("k");
    }
    public void printName(){
        System.out.print("King");
    }

    //King has a valid move of 1step striaght or diagonal
    public boolean validateMove(Move move, Board board){

        int originRow = move.getOriginRow();
        int originCol = move.getOriginCol();
        int destRow = move.getDestRow();
        int destCol = move.getDestCol();

        if( Math.abs(destCol - originCol) <= 1 && Math.abs(destRow - originRow) <= 1  &&  ! (board.getPiece(destRow, destCol) instanceof King) )
            return true;
        else
            return false;
    }

    //get all possible valid moves of the Computer's King
    public LinkedList possibleMovesOfBlackPiece(int originRow, int originCol, Board board)
    {
        LinkedList possibleMoves = new LinkedList();
        for(int i=-1; i<=1; i++)
        {
            for(int j=-1; j<=1; j++)
            {
                Move move = new Move(originRow, originCol, originRow + i, originCol + j);
                //if move is valid add it to the list of possible moves
                if(move.validateMove(board))
                    possibleMoves.add(move);
            }
        }
        return possibleMoves;
    }

    // get all possible valid moves of the User's King
    public LinkedList possibleMovesOfWhitePiece(int originRow, int originCol, Board board) 
    {
       return possibleMovesOfBlackPiece(originRow, originCol, board);
    }
}
