package basestation.vision;

/**
 * Created by trevor on 10/12/16.
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
}
