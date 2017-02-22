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
    private String name;

    public Bot(Connection c) {
        this.myConnection = c;
        this.name = "Unnamed Bot";
    }

    public Bot(Connection c, String name) {
        this.myConnection = c;
        this.name = name;
    }

    /**
     * TODO: Use reflections on this if necessary.
     * @return A command center for controlling the bot.
     */
    public abstract CommandCenter getCommandCenter();

    /**
     *
     * @return The bot's sensor center.
     */
    public abstract SensorCenter getSensorCenter();

    /**
     * Returns the name associated with the bot, if one was provided.
     * @return The name associated with the bot
     */
    public String getName() {
        return name;
    }

    /**
     * Terminates the connection to the bot as safely as possible
     */
    public void destroy() {
        myConnection.destroy();
    }

    @Override
    public String toString() {
        return "[Bot|" + myConnection + "|" + getName() + "]";
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Bot && o.toString().equals(toString()));
    }

}
