public interface ChessPlayer {
    
    public Move generateMove(Move lastMove);

    public void updateBlackListOnCapture(Move move);
    public void updateWhiteListOnCapture(Move move);

    public void updateWhiteListOnMove(Move move);
    public void updateBlackListOnMove(Move move);

    public void updateBlackListOnPromotion(Piece piecePromoted, Move move);
    public void updateWhiteListOnPromotion(Piece piecePromoted, Move move);

    public Piece promotePawn(Move move);
}
