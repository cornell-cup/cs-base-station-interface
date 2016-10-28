package basestation.bot.modbot;

import basestation.bot.Bot;
import basestation.bot.connection.IceConnection;
import basestation.bot.sensors.SensorCenter;

/**
 * A early implementation of a modbot
 */
public class ModBot extends Bot {
    private final ModbotCommandCenter commandCenter;
    private final ModbotSensorCenter  sensorCenter;

    /**
     * Currently modbots are implemented using an ice connection
     *
     * @param c an ice connection that has already been created
     */
    public ModBot(IceConnection c) {
        super(c);
        this.commandCenter = new ModbotCommandCenter(c);
        this.sensorCenter  = new ModbotSensorCenter();
        c.setAssociatedBot(this);
    }

    @Override
    public ModbotCommandCenter getCommandCenter() {
        return commandCenter;
    }

    @Override
    public SensorCenter getSensorCenter() {
        return sensorCenter;
    }
}
