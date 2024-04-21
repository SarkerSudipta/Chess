public class GameLogic {

    private Board board;
    private GameDisplay display;
    ChessPlayer computer;

    public GameLogic(GameDisplay display) {
        board = new Board();
        this.display = display;
        computer = null;
    }

    public void playGame() {
        board.resetBoard();
        int difficulty = display.promptForOpponentDifficulty(2);

        if (difficulty == 1)
            computer = new AIVersion1(board);
        else if (difficulty == 2)
            computer = new AIVersion2(board);

        display.displayBoard(board);

        Move move = display.promptForMove();
        // while user doesn't exit game
        while (move != null) {
            Piece pieceMoved = board.getPiece(move.getOriginRow(), move.getOriginCol());

            // if player chose a white piece and is a valid move then make the move
            // check if the piece moved is a pawn and is moved to the far end of the board
            // for check for promotion
            // check if user captured a piece. If so update the computer's list of pieces
            // and ask the computer to make a move aswell
            if (move.validateMove(board) && pieceMoved.getColor().equals("White")) {
                Piece blackCaptured = board.makeTheMove(move);
                computer.updateWhiteListOnMove(move);
                // if user captured the computer King, then game over -- won by user
                if (blackCaptured instanceof King) {
                    display.gameOver(1);
                    return;
                }
                if (blackCaptured != null)
                    computer.updateBlackListOnCapture(move);

                // if white pawn reached end of board, prompt user for promotion. pawnPromotion
                // returns false if user entered 0 i.e Quit
                if (pieceMoved instanceof Pawn && move.getDestRow() == 0) {
                    Piece pawnPromoted = pawnPromotion(move);
                    if (pawnPromoted == null) {
                        display.gameOver(0);
                        return;
                    }
                    computer.updateWhiteListOnPromotion(pawnPromoted, move);
                }

                // if user made a capture, update the computer's list of pieces
                if (blackCaptured != null)
                    computer.updateBlackListOnCapture(move);

                board.printBoard();
                display.summarizeMove(move, pieceMoved, blackCaptured);

                // computer's turn
                Move computerMove = computer.generateMove(move);
                if (computerMove == null) {
                    display.gameOver(1);
                    return;
                }
                computer.updateBlackListOnMove(computerMove);

                Piece computerPieceMoved = board.getPiece(computerMove.getOriginRow(), computerMove.getOriginCol());

                if (computerPieceMoved instanceof Pawn && computerMove.getDestRow() == 7) {
                    Piece piecePromoted = pawnPromotion(computerMove);
                    computer.updateBlackListOnPromotion(piecePromoted, computerMove);
                }

                Piece whiteCaptured = board.makeTheMove(computerMove);

                // if computer captured user's King, game over -- Computer won
                if (whiteCaptured instanceof King) {
                    display.gameOver(2);
                    return;
                }
                if (whiteCaptured != null)
                    computer.updateWhiteListOnCapture(computerMove);

                board.printBoard();
                display.summarizeMove(computerMove, computerPieceMoved, whiteCaptured);
                move = display.promptForMove();

            }
        }

        // move is null -- user entered 0 so game over
        display.gameOver(0);
    }

    public Piece pawnPromotion(Move move) {
        int row = move.getDestRow();
        Piece piecePromoted = null;

        if (row == 0) {
            piecePromoted = display.promptForPromotion();
            if (piecePromoted == null)
                display.gameOver(0);
        } else if (row == 7)
            piecePromoted = computer.promotePawn(move);

        board.addPiece(piecePromoted, move);
        return piecePromoted;
    }

}
