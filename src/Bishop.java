

public class Bishop extends Piece{


    public Bishop(Color color, Location location) {
        super(color, PieceType.BISHOP, location);
    }

    @Override
    public boolean moveValidation(Location currentLocation,Location nextLocation) {
        if (this.sameColor(ChessBoard.pieceAt(nextLocation))) {
            return false;
        }

        if (Math.abs(currentLocation.getxAxis() - nextLocation.getxAxis())
                !=
                Math.abs(currentLocation.getyAxis() - nextLocation.getyAxis())) {
            return false;
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
            return "\u2657";
        }
        return "\u265D";
    }
}
