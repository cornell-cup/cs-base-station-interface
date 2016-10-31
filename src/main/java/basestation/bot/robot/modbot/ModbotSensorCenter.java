package basestation.bot.robot.modbot;

import basestation.bot.sensors.SensorCenter;
import com.google.gson.JsonObject;

/**
 * Hosts methods to retrieve data from all applicate modbot sensors
 */
public class ModbotSensorCenter extends SensorCenter {
    @Override
    public JsonObject getAllData() {
        return new JsonObject();
    }
}
