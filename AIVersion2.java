public class AIVersion2 implements ChessPlayer{
    
    private List listOfBlackPieces;
    private Board board;
    private List listOfWhitePieces;

    public AIVersion2(Board board)
    {   
        this.board = board;
        listOfBlackPieces = new List(board, 1);
        listOfWhitePieces = new List(board, 2);
    }

    public Move generateMove(Move lastMove)
    {  
        for(int i=0; i<listOfBlackPieces.getSize(); i++)
        {
            listOfBlackPieces.getPiece(i).print();
        }
        Move move = checkForCapture();
        if (move != null){
            return move;
        }
        
        
        if( (move = checkForKingSafety()) != null ){
            return move;
        }

        move = developePiece();
    
        return move;
    }

    private Move checkForCapture()
    {
        for(int i=0; i<listOfBlackPieces.getSize(); i++)
        {
            Piece currPiece = listOfBlackPieces.getPiece(i);
            LinkedList possibleMoves = currPiece.possibleMovesOfBlackPiece(listOfBlackPieces.getRow(i), listOfBlackPieces.getCol(i), board);

            for(int j=0; j<possibleMoves.getSize(); j++)
            {
                Move move = (Move) possibleMoves.getItem(j);
                Piece destPiece = board.getPiece(move.getDestRow(), move.getDestCol());
                //destination has a white piece, so capture possible -- return the move
                if( destPiece != null  &&  destPiece.getColor().equals("White") )
                    return move;
            }
        }
        return null;
    }


    public Move checkForKingSafety()
    {
        for(int i=0; i<listOfWhitePieces.getSize(); i++)
        {
            Piece whitePiece = listOfWhitePieces.getPiece(i);
            LinkedList possibleMovesOfWhite = whitePiece.possibleMovesOfWhitePiece( listOfWhitePieces.getRow(i), listOfWhitePieces.getCol(i), board);

            Data kingData = listOfBlackPieces.getKing();
            int kingRow = kingData.getRow();
            int kingCol = kingData.getCol();

            for(int j=0; j<possibleMovesOfWhite.getSize(); j++)
            {
                Move whiteMove = (Move) possibleMovesOfWhite.getItem(j);
                
                //check if destination of the move == Black King's Position
                //if so, return the king's move
                
                if(kingRow == whiteMove.getDestRow() && kingCol == whiteMove.getDestCol())
                {
                    King king =(King) board.getPiece(kingRow, kingCol);
                    LinkedList possibleMovesOfKing = king.possibleMovesOfBlackPiece(kingRow, kingCol, board);
                    if(possibleMovesOfKing.getSize() != 0)
                        return (Move) possibleMovesOfKing.getItem(0);
                }
            }
        }

        return null;
    }

    

    public Move developePiece()
    {   
        //loop through all the pieces of black and check if any of them can move itself in the middle of the board
        for(int i=0; i<listOfBlackPieces.getSize(); i++)
        {
            Piece blackPiece = listOfBlackPieces.getPiece(i);
            //don't make the King move to the middle
            if(blackPiece instanceof King)
                continue;
            
            LinkedList possibleMovesOfBlack = blackPiece.possibleMovesOfBlackPiece(listOfBlackPieces.getRow(i), listOfBlackPieces.getCol(i), board);
            
            for(int j=0; j<possibleMovesOfBlack.getSize(); j++)
            {
                Move move = (Move)possibleMovesOfBlack.getItem(j);
                                
                //check if curr black piece can place itself in row 4 or 5 , do it
                if(move.getDestRow() == 3  ||  move.getDestRow() == 4)
                    return move;
            }
        }

        System.out.println("RANDOM MOVE WILL BE MADE");
        Move move = null;
        while(move == null)
        {
            int randomNum = (int) (Math.random() * listOfBlackPieces.getSize() );
            Piece blackPiece = listOfBlackPieces.getPiece(randomNum);
            LinkedList possibleMovesOfBlackPiece = blackPiece.possibleMovesOfBlackPiece(listOfBlackPieces.getRow(randomNum), listOfBlackPieces.getCol(randomNum), board);

            if(possibleMovesOfBlackPiece.getSize() != 0)
            {
                int randomMove = (int) ( Math.random() * possibleMovesOfBlackPiece.getSize() );
                move = (Move) possibleMovesOfBlackPiece.getItem(randomMove);
            }
        }
        // for(int i=0; i<listOfBlackPieces.getSize(); i++)
        // {
        //     Piece pawn = listOfBlackPieces.getPiece(i);
        //     {
        //         LinkedList possibleMovesOfBlack = pawn.possibleMovesOfBlackPiece(listOfBlackPieces.getRow(i), listOfWhitePieces.getCol(i), board);
            
        //         if(possibleMovesOfBlack.getSize() != 0)
        //         {
        //             Move move = (Move)possibleMovesOfBlack.getItem(0);
        //             return move;
        //         }
        //     }
        // }
        return move;
    }



    public void updateBlackListOnCapture(Move move){
        listOfBlackPieces.updateListOnCapture(move);
    }

    public void updateWhiteListOnCapture(Move move){
        listOfWhitePieces.updateListOnCapture(move);
    }

    public void updateBlackListOnMove(Move move){
        listOfBlackPieces.updateListOnMove(move);
    }
    public void updateWhiteListOnMove(Move move){
        listOfWhitePieces.updateListOnMove(move);
    }

    public void updateBlackListOnPromotion(Piece piecePromoted, Move move){
        listOfBlackPieces.updateListOnPromotion(piecePromoted, move);
    }
    public void updateWhiteListOnPromotion(Piece piecePromoted, Move move){
        listOfWhitePieces.updateListOnPromotion(piecePromoted, move);
    }

    public Piece promotePawn(Move move)
    {   
        Piece piecePromoted = new Queen("Black");
        listOfBlackPieces.updateListOnPromotion(piecePromoted, move);
        return piecePromoted;
    }

    

}
