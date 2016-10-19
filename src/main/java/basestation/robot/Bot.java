package basestation.robot;

import basestation.robot.commands.CommandCenter;
import basestation.robot.connection.Connection;

public abstract class Bot {
    private Connection myConnection;

    public Bot(Connection c) {
        myConnection = c;
    }

    /**
     * @return A command center for controlling the robot. Use reflections on this if necessary.
     */
    public abstract CommandCenter getCommandCenter();

    /**
     * Terminates the connection to the robot as safe as possible
     */
    public void destroy() {
        myConnection.destroy();
    }
}
