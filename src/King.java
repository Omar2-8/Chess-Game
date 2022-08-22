public class King extends Piece{

    public King(Color color,  Location location) {
        super(color, PieceType.KING, location);
    }


    @Override
    public boolean moveValidation(Location currentLocation,Location nextLocation) {
        if (this.sameColor(ChessBoard.pieceAt(nextLocation))) {
            return false;
        } else if (Math.abs(currentLocation.getxAxis() - nextLocation.getxAxis()) == 1
                && (Math.abs(currentLocation.getyAxis() - nextLocation.getyAxis()) == 1)) {
            return true;
        } else
            return Math.abs(currentLocation.getxAxis() - nextLocation.getxAxis()) == 1 && Math.abs(currentLocation.getyAxis() - nextLocation.getyAxis()) == 0
                    || Math.abs(currentLocation.getxAxis() - nextLocation.getxAxis()) == 0 && Math.abs(currentLocation.getyAxis() - nextLocation.getyAxis()) == 1;
    }

    private boolean isCastlingQueenSide(Location nextLocation) {
        if (nextLocation.getyAxis() - getLocation().getyAxis() == -2 && nextLocation.getxAxis() == getLocation().getxAxis()) {
            System.out.println("castling O-O-O");
            return true;
        }
        return false;
    }

    private boolean isCastlingKingSide(Location nextLocation) {
        if (nextLocation.getyAxis() - getLocation().getyAxis() == 2 && nextLocation.getxAxis() == getLocation().getxAxis()) {
            System.out.println("castling O-O");
            return true;
        }
        return false;
    }

    @Override
    public boolean specialMoves(ChessBoard chessBoard, Location nextLocation) {
        if (isCastlingKingSide(nextLocation)) {
            chessBoard.getChessBoard()[nextLocation.getxAxis()][5] = chessBoard.getChessBoard()[nextLocation.getxAxis()][7];
            chessBoard.getChessBoard()[nextLocation.getxAxis()][7] = null;
        }
        if (isCastlingQueenSide(nextLocation)) {
            chessBoard.getChessBoard()[nextLocation.getxAxis()][3] = chessBoard.getChessBoard()[nextLocation.getxAxis()][0];
            chessBoard.getChessBoard()[nextLocation.getxAxis()][0] = null;
        }
        return true;
    }

    @Override
    public String toString() {
        if (this.getColor() == Color.WHITE) {
            return "\u2654";
        }
        return "\u265A";
    }
}
