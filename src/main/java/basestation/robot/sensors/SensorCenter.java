package basestation.robot.sensors;

import com.google.gson.JsonObject;

/**
 * Hosts methods to retrieve data from all applicable sensors
 */
public abstract class SensorCenter {

    public abstract JsonObject getAllData();

}
