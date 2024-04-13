package LSystem;

public class Turtle {
    private double x;
    private double y;
    private double angle;

    public Turtle(double x, double y, double angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void goForward(double distance) {
        double newX = x + distance * Math.cos(Math.toRadians(angle));
        double newY = y + distance * Math.sin(Math.toRadians(angle));
        System.out.printf("Moving forward %.2f units to (%.2f, %.2f)%n", distance, newX, newY);
        x = newX;
        y = newY;
    }

    public void turnLeft(double angleChange) {
        angle += angleChange;
        System.out.printf("Turning left %.2f degrees%n", angleChange);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getAngle() {
        return angle;
    }

    // Renamed method for setting the scale of X
    public void setScaleX(double minX, double maxX) {
        // Set the scale of X
    }

    // Renamed method for getting the angle increment
    public double getAngleIncrement() {
        return angle;
    }
}
