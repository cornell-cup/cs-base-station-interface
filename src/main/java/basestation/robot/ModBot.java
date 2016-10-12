package basestation.robot;

import basestation.robot.commands.*;

import java.util.HashSet;
import java.util.Set;

public class ModBot extends Bot {

    public ModBot() {
        myCommands = new HashSet<>();

        // Populate commands
        myCommands.add(new Forward());
        myCommands.add(new Backward());
        myCommands.add(new Left());
        myCommands.add(new Right());
        myCommands.add(new CClockwise());
        myCommands.add(new Clockwise());
    }

    @Override
    public void sendCommand(Command c) {

    }
}
