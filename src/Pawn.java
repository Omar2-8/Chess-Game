
import static java.lang.Math.abs;

public class Pawn extends Piece {
    public Pawn(Color color, Location location) {
        super(color, PieceType.PAWN, location);
    }

    boolean isFirstMove = true;

    @Override
    public boolean moveValidation(Location currentLocation, Location nextLocation) {
        if (this.getColor() == Color.WHITE && currentLocation.getxAxis() - nextLocation.getxAxis() < 0
                || this.getColor() == Color.BLACK && currentLocation.getxAxis() - nextLocation.getxAxis() > 0) {
            return false;
        }

        if (this.isFirstMove && currentLocation.getyAxis() - nextLocation.getyAxis() == 0 && abs(currentLocation.getxAxis() - nextLocation.getxAxis()) == 2
                && ChessBoard.isPathClear(currentLocation, nextLocation) && ChessBoard.pieceAt(nextLocation) == null) {
            this.isFirstMove = false;
            return true;
        }

        if (currentLocation.getyAxis() - nextLocation.getyAxis() == 0 && abs(currentLocation.getxAxis() - nextLocation.getxAxis()) == 1
                && ChessBoard.pieceAt(nextLocation) == null) {
            return true;
        }


        return abs(currentLocation.getyAxis() - nextLocation.getyAxis()) == 1 && abs(currentLocation.getxAxis() - nextLocation.getxAxis()) == 1 &&
                ChessBoard.pieceAt(nextLocation) != null
                && !this.sameColor(ChessBoard.pieceAt(nextLocation));

    }

    private boolean isEnPassant(ChessBoard chessBoard, Location nextLocation) {
        if (nextLocation.getyAxis() != this.getLocation().getyAxis())
            if (ChessBoard.pieceAt(this.getLocation().getxAxis(), nextLocation.getyAxis()).getPieceType() == (PieceType.PAWN))
                return chessBoard.getChessBoard()[this.getLocation().getxAxis()][nextLocation.getyAxis()].getColor() != this.getColor();
        return false;
    }

    private boolean isPromotion(Location nextLocation) {
        return (this.getColor() == Color.BLACK && nextLocation.getxAxis() == 0) || (this.getColor() == Color.WHITE && nextLocation.getxAxis() == 7);
    }

    @Override
    public boolean specialMoves(ChessBoard chessBoard, Location nextLocation) {

        if (isEnPassant(chessBoard, nextLocation)) {
            chessBoard.getChessBoard()[this.getLocation().getxAxis()][nextLocation.getyAxis()] = null;
            return true;
        }
        if (isPromotion(nextLocation)) {
            chessBoard.getChessBoard()[this.getLocation().getxAxis()][this.getLocation().getyAxis()] = new Queen(this.getColor(), nextLocation);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {

        if (this.getColor() == Color.WHITE) {
            return "\u2659";
        }
        return "\u265F";
    }
}
