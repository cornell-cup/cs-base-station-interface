package basestation.bot.sensors;

/**
 * Hosts methods to retrieve data from all applicable sensors
 */
public abstract class SensorCenter {
    /**
     * @return a JSON string with the data for all the sensors.
     */
    public abstract String getAllDataJson();
}
