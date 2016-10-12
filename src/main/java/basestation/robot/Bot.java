package basestation.robot;

import basestation.robot.commands.Command;
import basestation.robot.connection.Connection;

import java.util.Set;

public abstract class Bot {
    Connection myConnection;
    Set<Command> myCommands;

    public Bot (Connection c) {
        myConnection = c;
    }

    public Set<Command> getCommands() {
        return myCommands;
    }
    public void sendCommand(Command c, String[] args) {
        c.applyCommand(myConnection, args);
    }
}
