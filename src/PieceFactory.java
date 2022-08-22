public class PieceFactory {


        public Piece createPiece(PieceType pieceType,Color color,Location location){
            if(pieceType == PieceType.KING)
                return new King(color , location);
            else if (pieceType == PieceType.QUEEN)
                return new Queen(color , location);
            else if (pieceType == PieceType.ROOK)
                return new Rook(color , location);
            else if (pieceType == PieceType.BISHOP)
                return new Bishop(color , location);
            else if (pieceType == PieceType.KNIGHT)
                return new Knight(color , location);
            else if (pieceType == PieceType.PAWN)
                return new Pawn(color , location);
            return null;
        }
}
