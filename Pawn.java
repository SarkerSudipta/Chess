public class Pawn extends Piece {

    public Pawn(String color) {
        super(color);
    }

    public void print() {
        if (super.getColor().equals("White"))
            System.out.print("P");
        else
            System.out.print("p");
    }
    public void printName(){
        System.out.print("Pawn");
    }

    // true if move is one step forward and path of move is clear
    // true if move is one step diagonal to the forward direction and path is
    // occupied by opponent's piece
    public boolean validateMove(Move move, Board board)
    {   
        int originRow = move.getOriginRow();
        int originCol = move.getOriginCol();
        int destRow   = move.getDestRow();
        int destCol   = move.getDestCol();

        boolean validMove = false;

        //get the piece and check its color
        Piece pawn = board.getPiece(originRow, originCol);
        //if pawn is white, it can move backward in the 2D-array
        //if black it moves forward in the 2D-array
        int change;
        if( pawn.getColor().equals("White") )
            change = -1;
        else
            change = 1;

        //Piece on the destination position
        Piece destPiece = board.getPiece(destRow, destCol);

            //check if move is one step forward/backwards depending on color and destination spot is empty
            if(originCol == destCol  &&  originRow + change == destRow  &&  destPiece == null)
                validMove = true;
            //else if move is diagonal check if destination spot is occupied by opponent's piece
            else if(destPiece != null &&  ! destPiece.isSameColor(this))
            {
                //check if move is one step right in diagonal direction
                if( originCol + 1 == destCol  &&  originRow + change == destRow )  
                    validMove = true;
                //check if move is one step left in diagonal direction
                else if (originCol - 1 == destCol  &&  originRow + change == destRow )
                    validMove = true;
            }

        return validMove;
    }

    // get all possible valid moves of the Computer's Pawn
    public LinkedList possibleMovesOfBlackPiece(int originRow, int originCol, Board board) {
        LinkedList possibleMoves = new LinkedList();

        for(int i = -1; i<=1 ; i++)
        {
            Move downMove = new Move(originRow, originCol, originRow + 1, originCol + i);
            if(downMove.validateMove(board))
                possibleMoves.add(downMove);
        }
        
        return possibleMoves;
    }

    // get all possible valid moves of the User's Pawn
    public LinkedList possibleMovesOfWhitePiece(int originRow, int originCol, Board board) {
        LinkedList possibleMoves = new LinkedList();

        for(int i = -1; i<=1 ; i++)
        {
            Move downMove = new Move(originRow, originCol, originRow - 1, originCol + i);
            if(downMove.validateMove(board))
                possibleMoves.add(downMove);
        }
        return possibleMoves;
    }

}
