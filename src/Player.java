public class Player {
    private final String playerName;
    private final Color color;

    public Player(String playerName, Color color) {
        this.playerName = playerName;
        this.color = color;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Color getColor() {
        return color;
    }
}
