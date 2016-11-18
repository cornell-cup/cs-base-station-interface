package basestation.vision;

import basestation.bot.robot.Bot;

import java.util.*;

/**
 * Collects input from multiple vision systems and exposes a unified vision API across the systems
 */
public class VisionManager { //TODO incomplete

    private VisionSystem canonicalVisionSystem; // Represents the BaseStation's understanding of locations
    private Map<Integer, VisionSystem> visionSystemMap;
    private int visionCounter;

    public VisionManager() {
        visionCounter = 0;
        canonicalVisionSystem = new CanonicalVisionSystem();
        visionSystemMap = new HashMap<>();
        visionSystemMap.put(visionCounter++, canonicalVisionSystem);
    }

    public int addVisionSystem(VisionSystem vs) {
        int ct = visionCounter;
        this.visionSystemMap.put(visionCounter++, vs);
        return ct;
    }

    public Set<Map.Entry<Integer, VisionSystem>> getAllVisionMappings() {
        return visionSystemMap.entrySet();
    }

    /**
     * Returns a list of vision objects with vision ids. Coordinates are made canonical.
     */
    public List<VisionObject> getAllLocationData() {
        ArrayList<VisionObject> tracked = new ArrayList<>();
        for (VisionSystem vs : visionSystemMap.values()) {
            tracked.addAll(vs.getAllObjectsWithRespectTo(canonicalVisionSystem));
        }

        return tracked;
    }

    /**
     * Gets the location of a bot relative to the BaseStation's interpretation or null if
     * no such coordinate exists
     *
     * @param bot
     * @return
     */
    public VisionCoordinate getBotCoordinate(Bot bot) {
        for (VisionSystem vs : visionSystemMap.values()) {
            Integer vid;
            if ((vid = vs.getIdForBot(bot)) != null) {
                return vs.getById(vid).coord;
            }
        }

        return null;
    }

    /**
     * @param visionSystemId the int associated with the desired VisionSystem
     * @return the vision system associated with visionSystemId or null if none exists
     */
    public VisionSystem getVisionSystemById(int visionSystemId) {
        return visionSystemMap.get(visionSystemId);
    }
}
