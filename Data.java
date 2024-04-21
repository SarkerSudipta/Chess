public class Data 
{
    Piece piece;
    int originRow;
    int originCol;

    public Data(Piece piece, int row, int col) {
        this.piece = piece;
        originRow = row;
        originCol = col;
    }

    public int getRow(){
        return originRow;
    }
    public int getCol(){
        return originCol;
    }
    public Piece getPiece(){
        return piece;
    }

}