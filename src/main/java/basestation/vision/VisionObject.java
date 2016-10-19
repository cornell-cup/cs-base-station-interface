package basestation.vision;

/**
 * Represents an object being tracked by a Vision System. Must have a persistent id in order to be
 * maintained between receipt of data.
 */
public class VisionObject {
    VisionSystem vs;
    int vid;
    VisionCoordinate myCoord;

    public VisionObject(VisionSystem vs, int vid, VisionCoordinate myCoord) {
        this.vs = vs;
        this.vid = vid;
        this.myCoord = myCoord;
    }

    public VisionObject(VisionObject old, VisionCoordinate newCoord) {
        this.vs = old.vs;
        this. vid = old.vid;
        this.myCoord = newCoord;
    }

    public VisionSystem getVisionSystem() {
        return vs;
    }

    public VisionCoordinate getVisionCoordinate() {
        return myCoord;
    }

    public String toString() {
        return "[Vision Object|" + vs + "|" + myCoord + "|" + vid + "]";
    }

    public boolean equals(Object o) {
        if (!(o instanceof  VisionObject)) return false;
        return o.toString().equals(toString());
    }

    public int hashCode() {
        return toString().hashCode();
    }
}
