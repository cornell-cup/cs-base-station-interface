package basestation.bot.robot.modbot;

import basestation.BaseStation;
import basestation.bot.robot.Bot;
import basestation.bot.connection.IceConnection;
import basestation.bot.connection.MotorConnection;
import basestation.bot.sensors.SensorCenter;

/**
 * A early implementation of a modbot.
 */
public class ModBot extends Bot {
    private final ModbotCommandCenter commandCenter;
    private final ModbotSensorCenter sensorCenter;

    /**
     * Currently modbots are implemented using an ice connection
     *
     * @param c an ice connection that has already been created
     */
    public ModBot(BaseStation myStation, MotorConnection c) {
        super(c);
        this.commandCenter = new ModbotCommandCenter(myStation, c, this);
        this.sensorCenter = new ModbotSensorCenter();
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
