package basestation.interfaces; /**
 * Singleton that exposes all endpoints of basestation.basestation through HTTP
 */

import basestation.AbstractBaseStation;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static spark.Spark.*;

public class BaseStationHTTPInterface {

    public static BaseStationHTTPInterface instance;

    private static AbstractBaseStation mStation;

    public void setmStation(AbstractBaseStation newStation) {
        this.instance.mStation = mStation;
    }

    public static void main(String[] args) {

        JsonParser mParser = new JsonParser();

        post("/sendCommand", (req,res) -> {
            JsonObject body = (JsonObject) mParser.parse(req.body());

            int botId = body.get("botId").getAsJsonPrimitive().getAsInt();
            JsonObject command = body.get("command").getAsJsonObject();

            //mStation.sendCommand(null, command);
            return "OK";
        });

        post("/linkBotToVision", (req,res) -> "Yay!");
    }
}
