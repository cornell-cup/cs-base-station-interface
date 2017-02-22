package basestation.bot;

import basestation.bot.robot.Bot;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Tracks and manages all bots. Any bot that you create a connection to should be tracked under
 * this manager to prevent errors.
 */
public class BotManager {
    // Provides unique IDs for all bots
    private int botCounter;

    // Mapping from integer IDs to actual bots
    private Map<Integer, Bot> botMap;

    /**
     * Initializes the bot manager with a fresh map and counter
     */
    public BotManager() {
        botCounter = 0;
        botMap = new HashMap<>();
    }

    /**
     * Begins tracking bot under the BotManager and return its managed ID
     *
     * @param bot The bot object that was created for the bot
     * @return The ID now associated with the bot
     */
    public int addBot(Bot bot) {
        botMap.put(botCounter, bot);
        return (botCounter++);
    }

    /**
     * @param id the id of the robot
     * @return the bot associated with id or null if none exists
     */
    public Bot getBotById(int id) {
        return botMap.get(id);
    }

    /**
     * Removes bot with id botId from
     *
     * @param botId the id of the robot
     */
    public void removeBotById(int botId) {
        botMap.remove(botId);
    }

    /**
     * Returns the IDs of all currently tracked bots from the BotManager. The bots may not be
     * active if the module using the BotManager is not careful to remove inactive bots.
     *
     * @return Integer IDs representing all bots being tracked by the BaseStation
     */
    public Set<Map.Entry<Integer, Bot>> getAllTrackedBotIDs() {
        return botMap.entrySet();
    }

    /**
     *
     * @return A collection of all bots being tracked by the BotManager.
     */
    public Collection<Bot> getAllTrackedBots() {
        return botMap.values();
    }

}
