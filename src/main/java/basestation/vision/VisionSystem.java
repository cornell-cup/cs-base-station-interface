package basestation.vision;

import java.util.Set;

/**
 * Represents a source of input for vision
 */
public abstract class VisionSystem {
    int id;
    VisionCoordinate origin;

    public VisionSystem(int id, VisionCoordinate o) {
        this.id = id;
        this.origin = o;
    }

    /**
     * Transforms a coordinate from another system to this system.
     * @param other The other coordinate
     * @param otherSystem The other system
     * @return a transformed coordinate
     */
    public VisionCoordinate transformCoordinates(VisionCoordinate other, VisionSystem otherSystem) {
        double newX = otherSystem.origin.x + other.x;
        double newY = otherSystem.origin.y + other.y;
        double newTheta = ((otherSystem.origin.theta + other.theta - origin.theta) + 360) % 360;

        return new VisionCoordinate(newX, newY, newTheta);
    }

    public abstract Set<VisionObject> getAllObjects();

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        return this.hashCode() == other.hashCode();
    }
}
