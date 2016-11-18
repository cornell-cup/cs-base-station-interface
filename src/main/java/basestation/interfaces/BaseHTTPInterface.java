package basestation.interfaces;

/**
 * Created by celinechoo on 10/29/16.
 */

import basestation.BaseStation;
import basestation.bot.Bot;
import basestation.bot.connection.IceConnection;
import basestation.bot.robot.modbot.ModBot;
import basestation.bot.robot.modbot.ModbotCommandCenter;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import spark.route.RouteOverview;

import java.util.Map;
import java.util.Set;

import static spark.Spark.*;

/* attempt at life */
public class BaseHTTPInterface {

    public static void main(String[] args) {
        port(8080);
        staticFiles.location("/public");
        JsonParser jp = new JsonParser();
        BaseStation station = new BaseStation();
        RouteOverview.enableRouteOverview("/");

        post("/addBot", (req,res) -> {
            String body = req.body();
            JsonObject addInfo = jp.parse(body).getAsJsonObject(); // gets (ip, port) from js

            /* storing json objects into actual variables */
            String ip = addInfo.get("ip").getAsString();
            int port = addInfo.get("port").getAsInt();

            /* setting up ice connection */
            IceConnection ice = new IceConnection(ip, port);

            /* new modbot is created to add */
            ModBot newBot = new ModBot(new BaseStation(), ice);
            station.getBotManager().addBot(newBot);

            return newBot;
        });

        post("/commandBot", (req,res) -> {
            String body = req.body();
            JsonObject commandInfo = jp.parse(body).getAsJsonObject(); // gets (botID, fl, fr, bl, br) from js

            /* storing json objects into actual variables */
            int botID = commandInfo.get("botID").getAsInt();
            int fl = commandInfo.get("fl").getAsInt();
            int fr = commandInfo.get("fr").getAsInt();
            int bl = commandInfo.get("bl").getAsInt();
            int br = commandInfo.get("br").getAsInt();

            ((ModbotCommandCenter)station.getBotManager().getBotById(botID).getCommandCenter()).setWheelPower(fl,fr,bl,br);

            return true;
        });


        get("/updateloc", (req, res) -> {
            // TODO: push to js the location of every modbot that is active.

            Set<Map.Entry<Integer, Bot>> addedBots = station.getBotManager().getAllTrackedBots();

            for(Map.Entry<Integer, Bot> entry : addedBots) {
                //TODO: do something
            }

            return true;
        });


    }
}