package basestation.bot.robot.minibot;

import basestation.BaseStation;
import basestation.bot.robot.Bot;
import basestation.bot.connection.MotorConnection;
import basestation.bot.sensors.SensorCenter;

/**
 * Maintains our interface with a MiniBot
 */
public class MiniBot extends Bot{
    private final MinibotCommandCenter commandCenter;
    private final MinibotSensorCenter sensorCenter;


    /**
     * Currently modbots are implemented using an ice connection
     *
     * @param c an ice connection that has already been created
     */
    public MiniBot(BaseStation myStation, MotorConnection c) {
        super(c);
        this.commandCenter = new MinibotCommandCenter(myStation, c, this);
        this.sensorCenter = new MinibotSensorCenter();
    }

    @Override
    public MinibotCommandCenter getCommandCenter() {
        return commandCenter;
    }

    @Override
    public SensorCenter getSensorCenter() {
        return sensorCenter;
    }
}

