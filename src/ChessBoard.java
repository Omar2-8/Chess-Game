import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class ChessBoard {

    private static final Piece[][] chessBoard = new Piece[8][8];
    private final Player whitePlayer;
    private final Player blackPlayer;
    private final PieceFactory pieceFactory;
    private Piece whiteKing, blackKing;
    private String winnerName;

    public ChessBoard(String playerWhite,String playerBlack) {

        whitePlayer = new Player(playerWhite,Color.WHITE);
        this.blackPlayer = new Player(playerBlack,Color.BLACK);
        pieceFactory = new PieceFactory();
        createBlackPieces(blackPlayer.getColor());
        createWhitePieces(whitePlayer.getColor());

    }

    public Piece[][] getChessBoard() {
        return chessBoard;
    }

    public static Piece pieceAt(Location location) {
        return chessBoard[location.getxAxis()][location.getyAxis()];
    }

    public static Piece pieceAt(int xAxis, int yAxis) {
        return chessBoard[xAxis][yAxis];
    }


    public static boolean isPathClear(Location currentLocation,Location nextLocation) {
        int x1=currentLocation.getxAxis(),y1=currentLocation.getyAxis(),
            x2=nextLocation.getxAxis(), y2=nextLocation.getyAxis();
        int xDistance = x2 - x1;
        int yDistance = y2 - y1;
        int xDir = 0;
        int yDir = 0;
        int size;

        if (xDistance < 0) {
            xDir = -1;
        } else if (xDistance > 0) {
            xDir = 1;
        }

        if (yDistance < 0) {
            yDir = -1;
        } else if (yDistance > 0) {
            yDir = 1;
        }

        if (xDistance != 0) {
            size = Math.abs(xDistance) - 1;
        } else {
            size = Math.abs(yDistance) - 1;
        }


        for (int i = 0; i < size; i++) {
            x1 += xDir;
            y1 += yDir;
            if (x1 > 7 || y1 > 7) return false;
            if (chessBoard[x1][y1] != null) {
                return false;
            }
        }
        return true;

    }

    static void printBoard() {
        PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        System.out.println();
        System.out.println("    a   b   c   d   e   f   g   h");

        System.out.println("  ---------------------------------");
        int count = 8;
        for (int i = 0; i < 8; i++) {
            System.out.print(count + " ");
            System.out.print("| ");
            for (int j = 0; j < 8; j++) {
                if (chessBoard[i][j] == null) {
                    out.print("\u200A\u200A\u2009 | ");
                } else {
                    //System.out.print(chessBoard[i][j] + " | ");
                    out.print(chessBoard[i][j] + "\u200A| ");
                }
            }
            System.out.print(count);
            count--;
            System.out.println();
            System.out.println("  ---------------------------------");
        }
        System.out.println("    a   b   c   d   e   f   g   h");
        System.out.println();
    }

    private void createBlackPieces(Color color) {
        for (int i = 0; i < 8; i++)
            chessBoard[1][i] = pieceFactory.buildPiece(PieceType.PAWN, color, new Location(1, i));

        chessBoard[0][0] = pieceFactory.buildPiece(PieceType.ROOK, color, new Location(0, 0));
        chessBoard[0][1] = pieceFactory.buildPiece(PieceType.KNIGHT, color, new Location(0, 1));
        chessBoard[0][2] = pieceFactory.buildPiece(PieceType.BISHOP, color, new Location(0, 2));

        chessBoard[0][3] = pieceFactory.buildPiece(PieceType.QUEEN, color, new Location(0, 3));

        blackKing = pieceFactory.buildPiece(PieceType.KING, Color.BLACK, new Location(0, 4));
        chessBoard[0][4] = blackKing;

        chessBoard[0][5] = pieceFactory.buildPiece(PieceType.BISHOP, color, new Location(0, 5));
        chessBoard[0][6] = pieceFactory.buildPiece(PieceType.KNIGHT, color, new Location(0, 6));
        chessBoard[0][7] = pieceFactory.buildPiece(PieceType.ROOK, color, new Location(0, 7));
    }

    public String getWinnerName() {
        return winnerName;
    }

    private void createWhitePieces(Color color) {
        for (int i = 0; i < 8; i++)
            chessBoard[6][i] = pieceFactory.buildPiece(PieceType.PAWN, color, new Location(6, i));

        chessBoard[7][0] = pieceFactory.buildPiece(PieceType.ROOK, color, new Location(7, 0));
        chessBoard[7][1] = pieceFactory.buildPiece(PieceType.KNIGHT, color, new Location(7, 1));
        chessBoard[7][2] = pieceFactory.buildPiece(PieceType.BISHOP, color, new Location(7, 2));

        chessBoard[7][3] = pieceFactory.buildPiece(PieceType.QUEEN, color, new Location(7, 3));

        whiteKing = pieceFactory.buildPiece(PieceType.KING, Color.WHITE, new Location(7, 4));
        chessBoard[7][4] = whiteKing;

        chessBoard[7][5] = pieceFactory.buildPiece(PieceType.BISHOP, color, new Location(7, 5));
        chessBoard[7][6] = pieceFactory.buildPiece(PieceType.KNIGHT, color, new Location(7, 6));
        chessBoard[7][7] = pieceFactory.buildPiece(PieceType.ROOK, color, new Location(7, 7));
    }

    public boolean isGameOver() {
        if (Piece.isAllWhitePiecesKilled() && Piece.isAllBlackPiecesKilled()) {
            System.out.println("Draw");
            ChessBoard.printBoard();
            return true;
        }
        if (whiteKing.isKilled()) {
            winnerName = blackPlayer.getPlayerName();
            ChessBoard.printBoard();
            return true;
        } else if (blackKing.isKilled()) {
            winnerName = whitePlayer.getPlayerName();
            ChessBoard.printBoard();
            return true;
        } else
            return false;
    }

    public boolean move(Location currentLocation, Location newLocation) {
        if (pieceAt(currentLocation).moveValidation(currentLocation, newLocation)
                || pieceAt(currentLocation).specialMoves(this, newLocation)) {
            if (pieceAt(newLocation) != null)
                pieceAt(newLocation).setKilled(true);
            Checkmate(currentLocation);
            chessBoard[newLocation.getxAxis()][newLocation.getyAxis()] = pieceAt(currentLocation);
            chessBoard[currentLocation.getxAxis()][currentLocation.getyAxis()] = null;
            pieceAt(newLocation).setLocation(newLocation);

            return true;
        }
        System.out.println("invalid move, the piece can't move to this spot");
        return false;

    }

    public void Checkmate(Location currentLocation) {
        if (pieceAt(currentLocation).getColor() == Color.WHITE) {
            if (pieceAt(currentLocation).moveValidation(currentLocation, blackKing.getLocation())) {
                if (isTheKingWillSurvive(currentLocation, blackKing.getLocation())) {
                    System.out.println("Checkmate!!! Your King is being attacked " + blackPlayer.getPlayerName());
                } else {
                    blackKing.setKilled(true);
                }

            }

        } else {
            if (pieceAt(currentLocation).moveValidation(currentLocation, whiteKing.getLocation())) {
                if (isTheKingWillSurvive(currentLocation, whiteKing.getLocation())) {
                    System.out.println("Checkmate!!! Your King is being attacked " + whitePlayer.getPlayerName());
                } else {
                    whiteKing.setKilled(true);
                }

            }

        }

    }

    private boolean isTheKingWillSurvive(Location currentLocation, Location newLocation) {
        for (int i = newLocation.getxAxis() - 1; i <= newLocation.getxAxis() + 1; i++)
            for (int j = newLocation.getyAxis() - 1; j <= newLocation.getyAxis() + 1; j++) {
                if (i < 0 || i > 7 || j < 0 || j > 8) continue;
                if (chessBoard[i][j] == null && !pieceAt(currentLocation).moveValidation(currentLocation, new Location(i, j)))
                    return true;

            }
        return false;
    }


}