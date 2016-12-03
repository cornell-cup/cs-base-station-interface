package basestation.vision;

import java.util.Optional;

/**
 * All vision coordinates are with respect to a coordinate systems.
 * The canonical coordinate system is such that 0 degrees points in the positive x direction and positive
 * rotation is in the clockwise direction.
 * // TODO: Refactor to radians
 */
public class VisionCoordinate {
    // Position in meters
    public final double x;
    public final double y;

    // Radians, optional in case a system cannot present angles
    private final Optional<Double> theta;

    public VisionCoordinate(double x, double y) {
        this.x = x;
        this.y = y;
        this.theta = Optional.empty();
    }

    public VisionCoordinate(double x, double y, double theta) {
        this.x = x;
        this.y = y;
        this.theta = Optional.of(theta);
    }

    /**
     * Returns the angle from this coordinate to other in degrees. Angle is from 0 to 360.
     *
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

    /**
     * Returns the 2d euclidean distances to the other coordinate, assuming they
     * are based on the same coordinate system
     * @param other
     * @return
     */
    public double getDistanceTo(VisionCoordinate other) {
        return Math.sqrt(Math.pow(other.x - x, 2.) + Math.pow(other.y - y, 2.));
    }

    public Optional<Double> getTheta() {
        return theta;
    }

    public double getThetaOrZero(){
        return theta.orElse(0.0);
    }

    public String toString() {
        return "(" + String.format("%.2f", x) + "," + String.format("%.2f", y) + "," + String.format("%.2f", theta.orElse(0.0)) + "deg)"; // TODO: Format in velocities and optional theta
    }

    public boolean equals(Object other) {
        return (other instanceof VisionObject && other.toString().equals(toString()));
    }
}
