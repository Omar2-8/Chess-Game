public class Knight extends Piece{
    public Knight(Color color, Location location) {
        super(color, PieceType.KNIGHT, location);
    }

    @Override
    public boolean moveValidation(Location currentLocation,Location nextLocation) {
        if (this.sameColor(ChessBoard.pieceAt(nextLocation))) {
            return false;
        }

        else return Math.abs(currentLocation.getxAxis() - nextLocation.getxAxis()) == 1 && Math.abs(currentLocation.getyAxis() - nextLocation.getyAxis()) == 2
                    || Math.abs(currentLocation.getxAxis() - nextLocation.getxAxis()) == 2 && Math.abs(currentLocation.getyAxis() - nextLocation.getyAxis()) == 1;
    }

    @Override
    public boolean specialMoves(ChessBoard board, Location newLoc) {
        return false;
    }

    @Override
    public String toString() {
        if (this.getColor() == Color.WHITE) {
            return "\u2658";
        }
        return "\u265E";
    }
}
