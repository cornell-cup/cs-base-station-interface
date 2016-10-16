package basestation.robot.connection;

/**
 * Represents a connection between the base station and a bot
 */
public abstract class Connection {
    /**
     * @return True if the connection is still active. Otherwise returns false.
     */
    public abstract boolean connectionActive();

    /**
     * Safely disconnects from the robot
     */
    public abstract void destroy();
}
