package basestation.vision;

/**
 * Represents an object being tracked by a Vision System. Must have a persistent id in order to be
 * maintained between receipt of data. VisionObjects are functional and thus should be
 * retrieved again if a newer version is desired.
 */
public class VisionObject {
    /**
     * The vision system this object is associated with
     */
    public final VisionSystem vs;

    /**
     * The id of this object (vision id)
     */
    public final int vid;

    /**
     * The coordinate of this object.
     */
    public final VisionCoordinate coord;

    public VisionObject(VisionSystem vs, int vid, VisionCoordinate myCoord) {
        this.vs = vs;
        this.vid = vid;
        this.coord = myCoord;
    }

    public VisionObject(VisionObject old, VisionCoordinate newCoord) {
        this.vs = old.vs;
        this.vid = old.vid;
        this.coord = newCoord;
    }

    public String toString() {
        return "[Vision Object|" + vs + "|" + coord + "|" + vid + "]";
    }

    public boolean equals(Object o) {
        if (!(o instanceof VisionObject)) return false;
        return o.toString().equals(toString());
    }

    public int hashCode() {
        return toString().hashCode();
    }
}
