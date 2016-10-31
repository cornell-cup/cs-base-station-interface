package basestation.bot;

import basestation.bot.commands.CommandCenter;
import basestation.bot.connection.Connection;
import basestation.bot.sensors.SensorCenter;

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
