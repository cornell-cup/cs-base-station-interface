import com.google.gson.JsonObject;

/**
 * The shared interface between the ConcreteBaseStation and Simulator.
 */

public interface BaseStation {

    public void sendCommand(int botId, JsonObject command);

    public void linkBotToVision(int botId, int visionId);

}
