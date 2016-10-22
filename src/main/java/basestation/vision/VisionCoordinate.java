package basestation.vision;

/**
 * All vision coordinates are with respect to a coordinate systems.
 * The canonical coordinate system is such that 0 degrees points in the positive x direction.
 */
public class VisionCoordinate {
    double x;
    double y;
    double theta; // degrees, optional and defaults to 0

    public VisionCoordinate(double x, double y) {
        this.x = x;
        this.y = y;
        this.theta = 0;
    }

    public VisionCoordinate(double x, double y, double theta) {
        this.x = x;
        this.y = y;
        this.theta = theta;
    }

    /**
     * Returns the angle from this coordinate to other in degrees. Angle is from 0 to 360.
     * @param other
     * @return
     */
    public double getAngleTo(VisionCoordinate other) {
        double dx = other.x - x;
        double dy = other.y - y;
        double res = (float) Math.toDegrees(Math.atan2(dy, dx));
        if (res < 0) res += 360;
        return res;
    }

    public double getDistanceTo(VisionCoordinate other) {
        return Math.sqrt(Math.pow(other.x - x,2.) + Math.pow(other.y -y,2.));
    }

    public double getTheta() {
        return theta;
    }

    public String toString() {
        return "("+String.format("%.2f",x)+","+String.format("%.2f",y)+","+String.format("%.2f",theta)+"degrees)";
    }

    public boolean equals(Object other) {
        return (other instanceof VisionObject && other.toString().equals(toString()));
    }
}
