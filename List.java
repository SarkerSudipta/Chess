//List contains an array of Data.
//Data contains every available piece on the board of the computer and its position

public class List {

   

    private Data array[][];
    private final int NUM_PIECES = 16;
    private int size;

    public List(Board board, int player) {
        array = new Data[NUM_PIECES][1];
        int n = 0;

        int i = 0;
        //if player is user, store white pieces in the list
        if(player == 2)
            i = 6;
        int end = i+1;
        while(i <= end) 
        {
            for (int j = 0; j < 8; j++)
            {
                Piece piece = board.getPiece(i, j);
                // if(piece == null)
                //     System.out.println("NULLLLLL");
                Data newPieceData = new Data(piece, i, j);
                if (n < NUM_PIECES)
                    array[n++][0] = newPieceData;
            }
        i++;
        }
        size = NUM_PIECES;
    }

    //updates the location of the piece moved
    public void updateListOnMove(Move move)
    {
        int row2 = move.getDestRow();
        int col2 = move.getDestCol();

        int index = findPiece(move.getOriginRow(), move.getOriginCol());
        if(index == -1)
            return;
        array[index][0].originRow = row2;
        array[index][0].originCol = col2;
    }

    public void updateListOnCapture(Move move)
    {
        int index = findPiece(move.getDestRow(), move.getDestCol());
        System.out.println("INDEX IS " + index);
        if(index == -1)
            return;
        
        //shift every piece after index to the left and decrement the size of the list
        for(int i=index; i+1<size; i++)
        {
            array[i][0] = array[i+1][0];
        }
        size--;
    }

    

    public void updateListOnPromotion(Piece piece, Move move)
    {   
        Data data = new Data(piece, move.getDestRow(), move.getDestCol());
        array[size][0] = data;
        size++;
    }

    //returns the index of the piece with the given origin location(move) in the array list
    private int findPiece(int row, int col)
    {
        for(int i=0; i<size; i++)
        {
            if(array[i][0].originRow == row  &&  array[i][0].originCol == col)
            {
                return i;
            }
        }
        return -1;
    }
    public int getSize(){
        return size;
    }
    public int getNumPieces(){
        return NUM_PIECES;
    }

    public Piece getPiece(int index){
        return array[index][0].piece;
    }
    public Data getKing(){
        for(int i=0; i<size; i++)
        {
            if(array[i][0].piece instanceof King)
                return array[i][0];
        }
        return null;
    }

    public int getRow(int index){
        return array[index][0].originRow;
    }
    public int getCol(int index){
        return array[index][0].originCol;
    }

    public void printList(){
        for(int i=0; i<size; i++)
        {
            array[i][0].piece.print();
            System.out.print("  ");
        }
   }


}
