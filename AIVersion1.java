public class AIVersion1 implements ChessPlayer{

    private List list;
    private Board board;

    public AIVersion1(Board board)
    {   
        this.board = board;
        list = new List(board, 1);
    }
    public Move generateMove(Move lastMove)
    {   
        LinkedList possibleMoves;
        Piece piece;
        do
        {   
            //get a random piece from the list of pieces of computer
            int randomPiece = (int) (Math.random() * list.getNumPieces());
            piece = list.getPiece(randomPiece);
            System.out.println("random piece is " + randomPiece);
            //get the list of possibles moves of that piece
            possibleMoves = piece.possibleMovesOfBlackPiece( list.getRow(randomPiece), list.getCol(randomPiece), board);
            //makeMove(piece, list.getRow(randomPiece), list.getCol(randomPiece));
            //keep looping until the list of possible moves of a random piece selected isn't empty
        }while(possibleMoves.getSize() == 0);

        //get a random move from the list of possible moves of the piece
        //and make the move
        int randomMove = (int) (Math.random() * possibleMoves.getSize() );
        Move move = (Move)  possibleMoves.getItem(randomMove) ;
        

        //update the list of Pieces of computer(since a piece has moved)
        list.updateListOnMove(move);

        return move;
    }

    public void updateBlackListOnCapture(Move move){
        list.updateListOnCapture(move);
    }
    public void updateWhiteListOnCapture(Move move){ }

    public void updateWhiteListOnMove(Move move){

    }
    public void updateBlackListOnMove(Move move){
        list.updateListOnMove(move);
    }

    public void updateBlackListOnPromotion(Piece piecePromoted, Move move){
        list.updateListOnPromotion(piecePromoted, move);
    }
    public void updateWhiteListOnPromotion(Piece piecePromoted, Move move){} 

    public Piece promotePawn(Move move)
    {   
        Piece piecePromoted;
        int random =(int) Math.random() * 4;
        if(random == 0)
            piecePromoted = new Queen("Black");
        else if(random == 1)
            piecePromoted = new Bishop("Black");
        else if(random == 2)
            piecePromoted = new Knight("Black");
        else
            piecePromoted = new Rook("Black");

        list.updateListOnPromotion(piecePromoted, move);
        return piecePromoted;
    }
    //get all possible valid moves of the King
    //and choose one of them by random
    // public boolean moveKing(int originRow, int originCol)
    // {   
        
    //     int numPossMoves = possibleMoves.getSize();
    //     if(numPossMoves == 0)
    //         return false;
        
    //     int randomMove = (int) Math.random() * numPossMoves;
    //     Move move = possibleMoves.getMove(randomMove);
    //     board.makeTheMove(move);
    //     return true;

    // }
}
