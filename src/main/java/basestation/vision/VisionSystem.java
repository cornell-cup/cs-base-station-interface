package basestation.vision;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a source of input for vision
 */
public abstract class VisionSystem {
    int id;
    double scalingFactor;       // Used in case one vision system has a different scale from another
    VisionCoordinate origin;

    public VisionSystem(int id, VisionCoordinate o) {
        this.id = id;
        this.origin = o;
        this.scalingFactor = 1.0;
    }

    /**
     * Transforms a coordinate from another system to this system.
     * @param other The other coordinate
     * @param otherSystem The other system
     * @return a transformed coordinate
     */
    public VisionCoordinate transformCoordinates(VisionCoordinate other, VisionSystem otherSystem) {
        double newX = (otherSystem.origin.x + other.x) / otherSystem.scalingFactor;
        double newY = (otherSystem.origin.y + other.y) / otherSystem.scalingFactor;
        double newTheta = ((otherSystem.origin.theta + other.theta - origin.theta) + 360) % 360;

        return new VisionCoordinate(newX, newY, newTheta);
    }

    public abstract Set<VisionObject> getAllObjects();

    public VisionObject getById(int target) {
        Set<VisionObject>  all = this.getAllObjects();
        for (VisionObject vo : all) {
            if (vo.vid == target) return vo;
        }

        return null;
    }

    public Set<VisionObject> getAllObjectsWithRespectTo(VisionSystem other) {
        Set<VisionObject> vset = getAllObjects();
        HashSet<VisionObject> transformed = new HashSet<>();
        for (VisionObject vo : vset) {
            transformed.add(new VisionObject(vo,other.transformCoordinates(vo.getVisionCoordinate(),this)));
        }

        return transformed;
    }

    public String toString() {
        return "[Vision System|" + id + "]";
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        return this.hashCode() == other.hashCode();
    }
}
