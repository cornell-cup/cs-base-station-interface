package basestation.bot.robot;

import basestation.bot.commands.CommandCenter;
import basestation.bot.connection.Connection;
import basestation.bot.sensors.SensorCenter;

/**
 * Represents any bot. We assume bots may present information and receive information,
 * so this is separated into the commandCenter and sensorCenter. Bots also have a persistent
 * connection which is represented by myConnection.
 */
public abstract class Bot {
    private Connection myConnection;

    public Bot(Connection c) {
        myConnection = c;
    }

    /**
     * @return A command center for controlling the bot. Use reflections on this if necessary.
     */
    public abstract CommandCenter getCommandCenter();

    public abstract SensorCenter getSensorCenter();

    /**
     * Terminates the connection to the bot as safe as possible
     */
    public void destroy() {
        myConnection.destroy();
    }

    public String toString() {
        return "[Bot|" + myConnection + "]";
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public boolean equals(Object o) {
        return (o instanceof Bot && o.toString().equals(toString()));
    }

}
