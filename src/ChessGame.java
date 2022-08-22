import java.util.Scanner;
import java.util.regex.Pattern;

public class ChessGame {
    static Scanner scanner = new Scanner(System.in);
    static String whitePlayerName;
    static String blackPlayerName;


    private final ChessBoard chessBoard;
    private boolean isWhiteTurn;
    private static ChessGame game;

    public ChessGame(String whitePlayerName, String blackPlayerName) {
        chessBoard = new ChessBoard(whitePlayerName, blackPlayerName);
        isWhiteTurn = true;
    }


    public boolean isDone() {
        return chessBoard.isGameOver();
    }

    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }

    public void playWhite(String move, Color color) {
        if (isValidMove(move, color))
            isWhiteTurn = false;
        else
            System.out.println("invalid move");
    }

    public static void startGame() {
        System.out.println("welcome to Atypon chess Game");
        System.out.print("Enter the White player Name :");
        whitePlayerName = scanner.next();
        System.out.print("Enter the Black player Name :");
        blackPlayerName = scanner.next();


        game = new ChessGame(whitePlayerName, blackPlayerName);

        while (!game.isDone()) {
            ChessBoard.printBoard();
            String move = read_move_from_console();
            if (game.isWhiteTurn())
                game.playWhite(move, Color.WHITE);
            else
                game.playBlack(move, Color.BLACK);
        }
        game.printWinnerName();
    }

    private static String read_move_from_console() {


        if (game.isWhiteTurn())
            System.out.print("your turn " + whitePlayerName + ": ");
        else
            System.out.print("your turn " + blackPlayerName + ": ");

        String move = scanner.nextLine();
        if (isValidMove(move))
            return move;
        System.out.println("invalid move, please try again ");
        return read_move_from_console();
    }

    private static boolean isValidMove(String move) {
        return Pattern.matches("move ([a-hA-H][0-9] [a-hA-H][0-9])", move);
    }

    public void playBlack(String move, Color color) {
        if (isValidMove(move, color))
            isWhiteTurn = true;
        else
            System.out.println("invalid move");
    }

    public void printWinnerName() {
        System.out.println("the winner is " + chessBoard.getWinnerName());
    }

    private boolean isValidMove(String move, Color color) {
        String currentLoc, newLoc;
        currentLoc = move.split(" ")[1];
        newLoc = move.split(" ")[2];
        Location currentLocation = new Location(currentLoc.toUpperCase());
        Location newLocation = new Location(newLoc.toUpperCase());
        if (ChessBoard.pieceAt(currentLocation) == null) {
            System.out.println("there is no piece here ");
            return false;
        }
        if (ChessBoard.pieceAt(currentLocation).getColor() != color) return false;
        return chessBoard.move(currentLocation, newLocation);
    }


}
