package basestation.bot.connection;

import com.google.gson.JsonObject;

/**
 * Represents a connection between the base station and a bot
 */
public abstract class Connection {
    /**
     * @return True if the connection is still active. Otherwise returns false.
     */
    public abstract boolean connectionActive();

    /**
     * Used for being able to send arbitrary commands in the form of JSON. Useful for rapid prototyping
     *
     * @param data A command to send
     * @return true if successful, else false
     */
    public boolean sendArbitrary(JsonObject data) {
        throw new Error("Unimplemented for this connection type");
    }

    /**
     * Safely disconnects from the bot
     */
    public abstract void destroy();
}
