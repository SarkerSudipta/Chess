public abstract class Piece extends Item{
    
    String color;

    public Piece(String c){
        color = c;
    }
    public String getColor(){
        return color;
    }
    public abstract void print();
    public abstract void printName();

    public abstract boolean validateMove(Move move, Board board);

    public abstract LinkedList possibleMovesOfBlackPiece(int originRow, int originCol, Board board);
    public abstract LinkedList possibleMovesOfWhitePiece(int originRow, int originCol, Board board);

    public boolean isSameColor(Piece piece){
        if ( color.equals(piece.getColor()) )
            return true;
        else
            return false;
    }

    // public boolean checkDiagonalMove(Move move, Board board){
    //     int originRow = move.getOriginRow();
    //     int originCol = move.getOriginCol();
    //     int destRow   = move.getDestRow();
    //     int destCol   = move.getDestCol();

    //     if( Math.abs(destRow - originRow) == Math.abs(destCol - originCol) && board.checkPieceOnDiagonalMove(move) )
    //         return true;
    //     else
    //         return false;
    // }

    // public boolean checkStraightMove(Move move, Board board){
    //     int originRow = move.getOriginRow();
    //     int originCol = move.getOriginCol();
    //     int destRow   = move.getDestRow();
    //     int destCol   = move.getDestCol();

    //     if( (destCol - originCol == 0  ||  destRow - originRow == 0)  &&  board.checkPieceOnStraightMove(move) )  
    //         return true;
    //     else
    //         return false;
    // }
}
