

public class Board {

    private Piece array[][];
    private final int ROW = 8;
    private final int COL = 8;
    private LinkedList capturedByWhiteList;
    private LinkedList capturedByBlackList;

    public Board() {
        array = new Piece[ROW][COL];
        capturedByBlackList = new LinkedList();
        capturedByWhiteList = new LinkedList();
    }

    public void resetBoard() {

        for (int i = ROW+3; i < ROW-2; i++) {
            for (int j = 0; j < COL ; j++)
                array[i][j] = null;
        }

        // Rook
        for (int j = 0; j < COL; j += COL - 1)
            array[0][j] = new Rook("Black");

        for (int j = 0; j < COL; j += COL - 1)
            array[ROW - 1][j] = new Rook("White");

        // Knight
        for (int j = 1; j < COL; j += COL - 3)
            array[0][j] = new Knight("Black");

        for (int j = 1; j < COL; j += COL - 3)
            array[ROW - 1][j] = new Knight("White");

        // Bishop
        for(int j = 2; j < COL; j+= COL - 5)
            array[0][j] = new Bishop("Black");
        
        for(int j = 2; j < COL; j += COL - 5)
            array[ROW - 1][j] = new Bishop("White");

        // King
        array[0][3] = new King("Black");
        array[7][3] = new King("White");

        // Queen
        array[0][4] = new Queen("Black");
        array[7][4] = new Queen("White");

        // Pawn
        for(int j=0; j<COL; j++){
            array[1][j] = new Pawn("Black");
            array[6][j] = new Pawn("White");
        }
    }


    Piece getPiece(int row, int col){
        if(row >= 0 && row < ROW && col >= 0 && col < COL)
            return array[row][col];
        else
            return null;
    }



    //returns true if the diagonal path of the piece to be moved is free
    //else returns false
    boolean checkPathOfDiagonalMove(Move move)
    {
        int originRow = move.getOriginRow();
        int originCol = move.getOriginCol();
        int destRow   = move.getDestRow();
        int destCol   = move.getDestCol();
        //change is +ve if piece moves forward
        // -ve if piece moves backwards
        int change_i;
        int change_j;
        if(originRow < destRow)
            change_i = 1;
        else
            change_i = -1;
        
        if(originCol < destCol)
            change_j = 1;
        else
            change_j = -1;

        //loops through the path of the piece to be moved to check if the path is occupied or not
        int i = originRow + change_i;
        int j = originCol + change_j;
        
        while( i != destRow )
        {   
            if(array[i][j] != null)
                return false;

            i += change_i;
            j += change_j;

        }

        return true;
    }


    //returns true if the straight path of the piece to be moved is free
    //else returns false
    boolean checkPathOfStraightMove(Move move)
    {
        int originRow = move.getOriginRow();
        int originCol = move.getOriginCol();
        int destRow   = move.getDestRow();
        int destCol   = move.getDestCol();

        //change is +ve if piece moves forward
        // -ve if piece moves backwards
        int change_i;
        int change_j;
        if(originRow < destRow)
            change_i = 1;
        else if (originRow > destRow)
            change_i = -1;
        else
            change_i = 0;
        
        if(originCol < destCol)
            change_j = 1;
        else if(originCol > destCol)
            change_j = -1;
        else
            change_j = 0;

        //loops through the path of the piece to be moved to check if the path is occupied or not
        int i = originRow + change_i;
        int j = originCol + change_j;

        while( i != destRow ||  j != destCol)
        {   
            
            if(array[i][j] != null)
                return false;

            i += change_i;
            j += change_j;
        }

        //no piece on the path found, so valid move
        return true;
    }

    //updates the board by
    //making the origin spot on the board null
    //and the destination spot hold the Piece from the origin spot
    //returns the piece if capture made else returns null
    public Piece makeTheMove(Move move)
    {
        int originRow = move.getOriginRow();
        int originCol = move.getOriginCol();
        int destRow   = move.getDestRow();
        int destCol   = move.getDestCol();

        Piece pieceCaptured = null;

        //if destination spot is not empty and occupied by opponent's piece, then make a capture
        if( array[destRow][destCol] != null  &&  ! array[destRow][destCol].isSameColor(array[originRow][originCol]) )
        {   
            pieceCaptured = array[destRow][destCol];
            capturePiece(move);
        }
        //move the piece
        array[destRow][destCol] = array[originRow][originCol];
        array[originRow][originCol] = null;
        return pieceCaptured;
    }
    
    //updates the list of captured Pieces of each player upon a capture
    //and make the position of the captured Piece null in the board
    private void capturePiece(Move move)
    {   
        int destRow = move.getDestRow();
        int destCol = move.getDestCol();

        Piece capturedPiece = array[destRow][destCol];
        array[destRow][destCol] = null;

        if(capturedPiece.getColor().equals("White") )
            capturedByBlackList.add(capturedPiece);
        else
            capturedByWhiteList.add(capturedPiece);
    }

    public void addPiece(Piece piecePromoted, Move move)
    {   
        if(piecePromoted == null || move == null)
            return;

        array[move.getDestRow()][move.getDestCol()] = piecePromoted;
    }


    public void printBoard() {
        
        printColNums();
        //print_lines();

        for(int i=0; i<ROW; i++)
        {   print_lines();
            System.out.print(i+1 + " | ");
            
            for(int j=0; j<COL; j++)
            {   
                Piece currPiece = array[i][j];
                if(currPiece == null)
                    System.out.print(" ");
                else
                    currPiece.print();

                System.out.print(" | ");
            }
            
            System.out.println();
        
        }

        print_lines();
        printColNums();
        
        printCapturedPieces();
    }

    //prints dashed lines - - in between rows
    private void print_lines(){
        for (int j = 0; j < COL+1; j++) {
            System.out.print("- - ");
        }
        System.out.println();
    }
    //prints column numbers above or below the columns
    private void printColNums(){
        System.out.print("    ");
        for (int j = 0; j < COL; j++) {
            System.out.format("%d   ", j + 1);
        }
        System.out.println();
    }
    private void printCapturedPieces()
    {
        System.out.print("Pieces captured by you: ");
        
        int i = 0;
        while(i < capturedByWhiteList.getSize())
        {
            Piece piece = (Piece) capturedByWhiteList.getItem(i);
            piece.print();
            System.out.print(" ");
            i++;
        }

        System.out.print("\nPieces captured by computer: ");
        
        i = 0;
        while(i < capturedByBlackList.getSize())
        {
            Piece piece = (Piece) capturedByBlackList.getItem(i);
            piece.print();
            System.out.print(" ");
            i++;
        }
        System.out.println();
        
    }
}


