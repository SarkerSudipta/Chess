public class Queen extends Piece {

    public Queen(String color) {
        super(color);
    }

    public void print() {
        if (super.getColor().equals("White"))
            System.out.print("Q");
        else
            System.out.print("q");
    }
    public void printName(){
        System.out.print("Queen");
    }

    public boolean validateMove(Move move, Board board) {
        // int originRow = move.getOriginRow();
        // int originCol = move.getOriginCol();
        // int destRow = move.getDestRow();
        // int destCol = move.getDestCol();

        boolean validMove = false;
        // move is a diagonal move so check if path of the diagonal move is free
        if (move.isMoveDiagonal()) 
        {   
            if (board.checkPathOfDiagonalMove(move))
                validMove = true;
            else
                validMove = false;
        }
        // move is a straight move so check if path of straight move is free
        else if (move.isMoveStraight()) {
            if (board.checkPathOfStraightMove(move))
                validMove = true;
            else
                validMove = false;
        }
        // else move is neither straight nor diagonal. i.e. invalid move return false
        return validMove;
    }

    //get all possible valid moves of the Computer's Queen
    public LinkedList possibleMovesOfBlackPiece(int originRow, int originCol, Board board)
    {   
        LinkedList possibleMoves = new LinkedList();

        //direction in x and y direction
        //up , down, upLeft, upRight, left, right, downLeft, downRight
        int dx[] = { -1, 1, -1, -1,  0, 0,  1, 1};
        int dy[] = { 0, 0, -1,  1, -1, 1, -1,  1};

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

    // get all possible valid moves of the User's Queen
    public LinkedList possibleMovesOfWhitePiece(int originRow, int originCol, Board board) 
    {
       return possibleMovesOfBlackPiece(originRow, originCol, board);
    }

    // // get all possible valid moves of the Queen
    // public LinkedList possibleMovesOfBlackPiece(int originRow, int originCol, Board board) {
    //     boolean downIsValid = true, downLeftIsValid = true, downRightIsValid = true;
    //     boolean upIsValid = true, upLeftIsValid = true, upRightIsValid = true;
    //     boolean leftIsValid = true, rightIsValid = true;

    //     Move down = null, downLeft = null, downRight = null, up = null, upLeft = null, upRight = null, left = null, right = null;

    //     LinkedList possibleMoves = new LinkedList();

    //     // loop throught all possible moves
    //     for (int i = 1; i <= 7; i++) 
    //     {   

    //         if (downIsValid)
    //             down = new Move(originRow, originCol, originRow + i, originCol);
    //         if (downLeftIsValid)
    //             downLeft = new Move(originRow, originCol, originRow + i, originCol - i);
    //         if (downRightIsValid)
    //             downRight = new Move(originRow, originCol, originRow + i, originCol + i);

    //         if (upLeftIsValid)
    //             upLeft = new Move(originRow, originCol, originRow - i, originCol - i);
    //         if (upIsValid)
    //             up = new Move(originRow, originCol, originRow - i, originCol);
    //         if (upRightIsValid)
    //             upRight = new Move(originRow, originCol, originRow - i, originCol + i);

    //         if (leftIsValid)
    //             left = new Move(originRow, originCol, originRow, originCol - i);
    //         if (rightIsValid)
    //             right = new Move(originRow, originCol, originRow, originCol + i);

    //         // if move is valid add it to the list of possible moves
    //         // else make the type of move false so that it doesn't check that type anymore
    //         // example if Queen can't move 4steps then no point in checking for steps 5,6,7 down 

    //         //For down moves
    //         if (downIsValid && down.validateMove(board))
    //             possibleMoves.add(down);
    //         else
    //             downIsValid = false;

    //         if (downLeftIsValid && downLeft.validateMove(board))
    //             possibleMoves.add(down);
    //         else
    //             downLeftIsValid = false;

    //         if(downRightIsValid && downRight.validateMove(board))
    //             possibleMoves.add(down);
    //         else
    //             downRightIsValid = false;


    //         //For up moves
    //         if(upIsValid && up.validateMove(board))
    //             possibleMoves.add(up);
    //         else
    //             upIsValid = false;

    //         if(upRightIsValid && upRight.validateMove(board))
    //             possibleMoves.add(upRight);
    //         else
    //             upRightIsValid = false;

    //         if(upLeftIsValid && upLeft.validateMove(board))
    //             possibleMoves.add(upLeft);
    //         else
    //             upLeftIsValid = false;

            
    //         //For left and right moves
    //         if(leftIsValid && left.validateMove(board))
    //             possibleMoves.add(left);
    //         else
    //             leftIsValid = false;

    //         if(rightIsValid && right.validateMove(board))
    //             possibleMoves.add(right);
    //         else
    //             rightIsValid = false;

    //     }

    //     return possibleMoves;
    // }
}
