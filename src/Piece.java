

public abstract class Piece {

    private final Color color;
    private  Location location;
    private final PieceType pieceType;
    private boolean killed;


    public Piece(Color color, PieceType pieceType, Location location) {
        this.color = color;
        this.pieceType = pieceType;
        this.location = location;
        this.killed=false;
    }

    public Color getColor() {
        return color;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public abstract boolean moveValidation(Location currentLocation, Location nextLocation);

    public abstract boolean specialMoves(ChessBoard board, Location newLoc);

    public boolean isKilled() {
        return killed;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    public boolean sameColor(Piece otherPiece) {
        if (otherPiece == null) {
            return false;
        }
        return (this.color == otherPiece.getColor());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return pieceType == piece.pieceType;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                ", location=" + location +
                ", pieceType=" + pieceType +
                '}';
    }

}
