package basestation.bot;

import basestation.BaseStation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Holds a mapping of IDs and tracking for all bots
 */
public class BotManager {

    // Provides unique IDs for all bots
    int botCounter;

    BaseStation myStation;

    // Mapping from integer IDs to actual bots
    Map<Integer,Bot> botMap;

    public BotManager(BaseStation baseStation) {
        botCounter = 0;
        botMap = new HashMap<>();
        myStation = baseStation;
    }

    /**
     * Begins tracking bot under the BotManager and return its managed ID
     */
    public int addBot(Bot bot) {
        botMap.put(botCounter,bot);
        return(botCounter++);
    }

    /**
     * @param id
     * @return the bot associated with id or null if none exists
     */
    public Bot getBotById(int id) {
       return botMap.get(id);
    }

    /**kk
     * Removes bot with id botId from
     * @param botId
     */
    public void removeBotById(int botId) {
        botMap.remove(botId);
    }

    /**
     * Returns all currently tracked bots from the BotManager. These bots may or may not be active. They are only
     * actively added to the Manager and have not been removed.
     * @return All entries
     */
    public Set<Map.Entry<Integer,Bot>> getAllTrackedBots() {
        return botMap.entrySet();
    }

}
