package basestation.bot.connection;

import basestation.bot.Bot;
import com.google.gson.JsonObject;

/**
 * Represents a connection between the base station and a bot
 */
public abstract class Connection {

    Bot associatedBot;

    /**
     * @return True if the connection is still active. Otherwise returns false.
     */
    public abstract boolean connectionActive();

    public boolean sendArbitrary(JsonObject data) {
        throw new Error("Unimplemented for this connection type");
    }

    /**
     * Safely disconnects from the bot
     */
    public abstract void destroy();

    public void setAssociatedBot(Bot b) {
        this.associatedBot = b;
    }

    public Bot getAssociatedBot() {
        return this.associatedBot;
    }
}
