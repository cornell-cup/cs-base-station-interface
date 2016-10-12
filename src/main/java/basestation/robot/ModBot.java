package basestation.robot;

import basestation.robot.commands.*;
import basestation.robot.connection.Connection;

import java.util.HashSet;
import java.util.Set;

public class ModBot extends Bot {

    public ModBot(Connection c) {
        super(c);
        myCommands = new HashSet<>();

        // Populate commands
        myCommands.add(new Forward());
        myCommands.add(new Backward());
        myCommands.add(new Left());
        myCommands.add(new Right());
        myCommands.add(new CClockwise());
        myCommands.add(new Clockwise());
    }
}
