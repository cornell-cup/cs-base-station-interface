package basestation.robot.commands;

import basestation.robot.connection.Connection;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class for any wheel movement. Any wheel movement should be expressed as a percentage
 * of the maximum power from 0 to 100.
 */
public abstract class WheelMovement extends Command{

    public boolean applyCommand(Connection c, String[] args) {
        if (args.length != 1) {
            System.err.println("Invalid argument length in wheel movement");
            return false;
        }

        Map<String,String> commandMap = new HashMap<>();
        commandMap.put(getCommandName(), args[0]);

       return c.sendKV(commandMap);
    }
}
