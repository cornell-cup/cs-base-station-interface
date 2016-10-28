package basestation;

import basestation.bot.BotManager;
import basestation.vision.*;

/**
 * Contains logic to manage and unify input and output between bots and vision sources.
 */

public class BaseStation {

    private BotManager    bManager;
    private VisionManager vManager;

    public BaseStation() {
        bManager = new BotManager();
        vManager = new VisionManager();
    }

    /**
     * Returns the bot manager held by BaseStation which can be used to lookup bots
     * @return The bot manager
     */
    public BotManager getBotManager() {
        return bManager;
    }

    /**
     * Returns the vision manager held by BaseStation which can be used to track bots and objects
     * @return The vision manager
     */
    public VisionManager getVisionManager() {
        return vManager;
    }

}
