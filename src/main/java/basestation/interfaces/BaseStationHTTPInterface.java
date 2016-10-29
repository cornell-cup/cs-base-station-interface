package basestation.interfaces; /**
 * Singleton that exposes all endpoints of basestation.basestation through HTTP
 */

import basestation.BaseStation;
import basestation.bot.connection.IceConnection;
import basestation.bot.modbot.ModBot;
import basestation.bot.modbot.ModbotCommandCenter;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import spark.route.RouteOverview;

import static spark.Spark.*;

public class BaseStationHTTPInterface {

    public static void main(String[] args) {
        JsonParser mParser = new JsonParser();
        Gson gson = new Gson();
        JsonParser jp = new JsonParser();
        BaseStation mStation = new BaseStation();
        RouteOverview.enableRouteOverview("/");

        /*
        post("/addBot", (req,res) -> {
            String body = req.body();
            JsonObject rBody = jp.parse(body).getAsJsonObject();
            String ip = rBody.get("ip").getAsString();
            int port = rBody.get("port").getAsInt();
            IceConnection ic = new IceConnection(ip, port);
            mStation.addBot(mb);
            mStation.linkBotToVision(mb, 1);
            return mb;
        });

        post("/commandBot", (req,res) -> {
            String body = req.body();
            JsonObject rBody = jp.parse(body).getAsJsonObject();
            int botid = rBody.get("bid").getAsInt();
            int fl = rBody.get("fl").getAsInt();
            int fr = rBody.get("fr").getAsInt();
            int bl = rBody.get("bl").getAsInt();
            int br  = rBody.get("br").getAsInt();
            ((ModbotCommandCenter)mStation.getBotById(botid).getCommandCenter()).setWheelPower(fl,fr,bl,br);
            return true;
        });
        */

    }
}
