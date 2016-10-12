package basestation.robot.commands;

import basestation.robot.connection.Connection;

/**
 * Represents any command that can be sent to a bot.
 */
public abstract class Command {
    /**
     * Applies the command over c with args[] as its arguments.
     * @param args
     * Return true if the commmand was well formatted and successfully sent
     */
    public abstract boolean applyCommand(Connection c, String[] args);

    /**
     * @return The unique name for the command
     */
    public abstract String getCommandName();

    @Override
    public String toString() {
        return getCommandName();
    }

    @Override
    public int hashCode() {
       return this.toString().hashCode();
    }
}
