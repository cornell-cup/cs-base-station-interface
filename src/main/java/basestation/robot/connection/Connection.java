package basestation.robot.connection;

import com.google.gson.JsonObject;

/**
 * Represents a connection between the base station and a bot
 */
public abstract class Connection {
    /**
     * @return True if the connection is still active. Otherwise returns false.
     */
    public abstract boolean connectionActive();

    public boolean sendArbitrary(JsonObject data) {
        throw new Error("Unimplemented for this connection type");
    }

    /**
     * Safely disconnects from the robot
     */
    public abstract void destroy();
}
