//interface of display
//used for input from user and output to console 
public interface GameDisplay {

    public int promptForOpponentDifficulty(int maxDifficulty);
    public void displayBoard(Board board);
    public Move promptForMove();
    public Piece promptForPromotion();

    public void summarizeMove(Move move, Piece pieceMoved, Piece pieceCaptured);
    public void gameOver(int winner);
    
    //returns true if user entered 0 to play again else returns false if user entered 1
    public boolean promptPlayAgain();
}
