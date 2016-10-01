package basestation;

import basestation.robot.Command;
import basestation.robot.Robot;
import basestation.vision.VisionLocation;
import basestation.vision.VisionObject;

import java.util.*;

/**
 * The shared interface between the ConcreteBaseStation and Simulator.
 */

public abstract class BaseStation {

    int botCounter;
    Map<Integer, Robot> botMap;

    public BaseStation() {
        botCounter = 0;
        botMap = new HashMap<>();
    }

    /**
     * Send a command to bot with id botId.
     * @param bot The Base station's association of a bot ID
     * @param command An agreed upon json object for the command
     */
    public abstract void sendCommand(Robot bot, Command command);

    /**
     * Associates a bot with botId with a vision object with visionId
     */
    public abstract void linkBotToVision(Robot bot, VisionObject vo);

    /**
     * Adds a controllable bot to the basestation.basestation to be tracked.
     */
    public void addBot(Robot bot) {
        botMap.put(botCounter++, bot);
    }

    /**
     * Stops the tracking of bot with id botId
     */
    public abstract void removeBot(Robot bot);

    /**
     * Returns all bots known by base station
     */
    public Collection<Robot> getAllBots() {
        return botMap.values();
    }

    /**
     * Returns a list of vision objects with vision ids
     */
    public abstract List<List<VisionObject>> getAllLocationData();

    /**
     * Gets the location of a bot relative to the BaseStation's interpretation
     * @param bot
     * @return
     */
    public abstract VisionLocation getBotLocation(Robot bot);

}
