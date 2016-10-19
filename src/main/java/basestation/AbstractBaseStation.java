package basestation;

import basestation.robot.Bot;
import basestation.robot.commands.CommandCenter;
import basestation.robot.sensors.SensorCenter;
import basestation.vision.VisionCoordinate;
import basestation.vision.VisionObject;
import basestation.vision.VisionSystem;
import com.google.gson.JsonObject;

import java.util.*;

/**
 * The shared interface between the ConcreteBaseStation and Simulator.
 */

public abstract class AbstractBaseStation {

    int botCounter;
    int visionCounter;
    Map<Integer, Bot> botMap;
    VisionSystem canonicalVisionSystem; // Represents the BaseStation's understanding of locations
    Set<VisionSystem> inputSystems;

    // These keep a mapping between our bots and vision which are separate until explicitly linked
    Map<VisionObject, Bot> visionToBot;
    Map<Bot, VisionObject> botToVision;

    public AbstractBaseStation() {
        botCounter = 0;
        botMap = new HashMap<>();
        inputSystems = new HashSet<>();
    }

    /**
     * Get the command center for a particular bot.
     *
     * @return A command center object which can be used to issue commands to the robot.
     */
    public CommandCenter getCommandCenter(Bot bot) {
        return bot.getCommandCenter();
    }

    /**
     * Returns the sensor center for a bot
     * @param bot
     * @return A sensor center object which can be used to retrieve all current sensor data for the robot
     */
    public SensorCenter getSensorCenter(Bot bot) {
        return bot.getSensorCenter();
    }

    /**
     * Associates a bot with botId with a vision object with visionId
     */
    public abstract void linkBotToVision(Bot bot, VisionObject vo);

    /**
     * Adds a controllable bot to the basestation.basestation to be tracked.
     */
    public void addBot(Bot bot) {
        botMap.put(botCounter++, bot);
    }

    /**
     * Stops the tracking of bot with id botId
     */
    public abstract void removeBot(Bot bot);

    /**
     * Returns all bots known by base station
     */
    public Collection<Bot> getAllBots() {
        return botMap.values();
    }

    /**
     * Returns a list of vision objects with vision ids
     */
    public abstract List<List<VisionObject>> getAllLocationData();

    /**
     * Gets the location of a bot relative to the AbstractBaseStation's interpretation
     *
     * @param bot
     * @return
     */
    public abstract VisionCoordinate getBotLocation(Bot bot);

    public void addVisionSystem(VisionSystem vs) {
        inputSystems.add(vs);
    }
}
