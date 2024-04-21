public class Move extends Item{
    
    private int originRow;
    private int originCol;
    private int destRow;
    private int destCol;

    private final int ROW = 8;
    private final int COL = 8;

    public Move(int originRow, int originCol, int destRow, int destCol){
        this.originRow = originRow;
        this.originCol = originCol;
        this.destRow = destRow;
        this.destCol = destCol;
    }

    //checks if either origin or destination (row or column) is outside of board -- return false
    //checks if origin row and column has a piece -- if not return false
    //checks if the piece requested to move is valid for that particular piece -- if not return false
    //       this also checks the path of the piece being moved, i.e. if path is required to be free for the piece to moved
    //       checks the destination spot itself, if empty -- valid, if occupied by opponent -- valid, if occupied by own piece -- invalid
    public boolean validateMove(Board board)
    {   
        //checks if move (origin or destination is out of board)
        if( !checkMoveWithinBoard() )
            return false;
        
        Piece pieceToBeMoved = board.getPiece(this.originRow, this.originCol);
        Piece pieceOnDestination = board.getPiece(destRow, destCol);

        boolean validMove = false;

        //check if the piece requested to move is in that given origin spot
        if(pieceToBeMoved != null)
        {  
            //check if the piece can move like the given move and
            // the path of the move is also checked depending on the dynamic type of piece
            if(pieceToBeMoved.validateMove(this, board))
            {      
                //destination spot is not occupied so valid move
                if(pieceOnDestination == null)
                    validMove = true;
                else
                {    // checks if destionation spot is occupied by own piece, so invalid move
                    // even if destination has opponent's king -- valid move
                    if(pieceOnDestination.getColor().equals(pieceToBeMoved.getColor()) )
                        validMove = false;
                    else
                        validMove = true;
                }
            }
        }

        return validMove;

    }


    public boolean isMoveDiagonal()
    {
        if( Math.abs(destRow - originRow) == Math.abs(destCol - originCol) )
            return true;
        else
            return false;
    }

    public boolean isMoveStraight()
    {
        if( destCol - originCol == 0  ||  destRow - originRow == 0 )
            return true;
        else    
            return false;
    }


    //returns true if move is inside board and if destination isnt the same as origin
    //else returns false
    private boolean checkMoveWithinBoard(){

        if(     originRow >= 0  &&  originRow < ROW 
            &&  destRow >= 0  &&  destRow < ROW
            &&  originCol >= 0  &&  originCol < COL
            &&  destCol >= 0  &&  destCol < COL
            &&  (originCol != destCol  ||  originRow != destRow) )
            return true;
        else
            return false;
    }

    int getOriginRow(){
        return originRow;
    }
    int getOriginCol(){
        return originCol;
    }
    int getDestRow(){
        return destRow;
    }
    int getDestCol(){
        return destCol;
    }
}
