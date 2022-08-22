public class Queen extends Piece{
    public Queen(Color color, Location location) {
        super(color, PieceType.QUEEN, location);
    }

    @Override
    public boolean moveValidation(Location currentLocation,Location nextLocation) {
        if (this.sameColor(ChessBoard.pieceAt(nextLocation))) {
            return false;
        }
        if (Math.abs(currentLocation.getxAxis() - nextLocation.getxAxis())
                !=
                Math.abs(currentLocation.getyAxis() - nextLocation.getyAxis())) {
            return true;
        }
        if (Math.abs(currentLocation.getxAxis() - nextLocation.getxAxis()) != 0
                && Math.abs(currentLocation.getyAxis() - nextLocation.getyAxis()) != 0) {
            return true;
        }

        return ChessBoard.isPathClear(currentLocation, nextLocation);
    }

    @Override
    public boolean specialMoves(ChessBoard board, Location newLoc) {
        return false;
    }

    @Override
    public String toString() {
        if (this.getColor() == Color.WHITE) {
            return "\u2655";
        }
        return "\u265B";
    }
}
