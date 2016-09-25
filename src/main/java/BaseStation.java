import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * The shared interface between the ConcreteBaseStation and Simulator.
 */

public interface BaseStation {

    /**
     * Send a command to bot with id botId.
     * Command must be of an agreed upon form. For example, a modbot command to move forward may
     * be:
     *
     *
      {
         "frontLeft":100,
         "frontRight":100,
         "backLeft":100,
         "backRight":100
     }
     *
     * @param botId The Base station's association of a bot ID
     * @param command An agreed upon json object for the command
     */
    public void sendCommand(int botId, JsonObject command);


    /**
     * Associates a bot with botId with a vision object with visionId
     */
    public void linkBotToVision(int botId, int visionId);

    /**
     * Adds a controllable bot to the BaseStation by connection to ipAddress
     */
    public int addBot(String ipAddress);

    /**
     * Stops the tracking of bot with id botId
     */
    public void removeBot(int botId);

    /**
     * Returns all bot data known by the BaseStation
     */
    public JsonArray getAllBotData();

    /**
     * Returns data for the bot with id botId
     */
    public JsonArray getBotStatus(int botId);

    /**
     * Gets the data from bot with id botId for sensor of type sensorType
     */
    public JsonArray getBotStatusBySensor(int botId, String sensorType);

    /**
     * Returns a JsonArray of vision objects with vision ids
     */
    public JsonArray getAllLocationData();

}
