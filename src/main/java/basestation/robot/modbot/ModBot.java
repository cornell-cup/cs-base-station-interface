package basestation.robot.modbot;

import basestation.robot.Bot;
import basestation.robot.connection.IceConnection;

/**
 * A early implementation of a modbot
 */
public class ModBot extends Bot {
    private final ModbotCommandCenter commandCenter;

    /**
     * Currently modbots are implemented using an ice connection
     *
     * @param c an ice connection that has already been created
     */
    public ModBot(IceConnection c) {
        super(c);
        this.commandCenter = new ModbotCommandCenter(c);
    }

    @Override
    public ModbotCommandCenter getCommandCenter() {
        return commandCenter;
    }
}
