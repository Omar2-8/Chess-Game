public class Location {
    private final int xAxis;
    private final int yAxis;

    public Location(int xAxis, int yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }
    public Location(String location) {
        this(56-location.charAt(1) , location.charAt(0)-65 );
    }

    public int getxAxis() {
        return xAxis;
    }

    public int getyAxis() {
        return yAxis;
    }
}
