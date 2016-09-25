/**
 * Singleton that exposes all endpoints of BaseStation through HTTP
 */

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import static spark.Spark.*;

public class BaseStationHTTPInterface {

    public static BaseStationHTTPInterface instance;

    private static BaseStation mStation;

    public void setmStation(BaseStation newStation) {
        this.instance.mStation = mStation;
    }

    public static void main(String[] args) {

        JsonParser mParser = new JsonParser();

        post("/sendCommand", (req,res) -> {
            JsonObject body = (JsonObject) mParser.parse(req.body());

            int botId = body.get("botId").getAsJsonPrimitive().getAsInt();
            JsonObject command = body.get("command").getAsJsonObject();

            mStation.sendCommand(botId, command);
            return "OK";
        });

        post("/linkBotToVision", (req,res) -> "Yay!");

    }
}
