package basestation.interfaces; /**
 * Singleton that exposes all endpoints of basestation.basestation through HTTP
 */

import basestation.AbstractBaseStation;
import basestation.robot.Bot;
import basestation.robot.connection.IceConnection;
import basestation.robot.modbot.ModBot;
import basestation.robot.modbot.ModbotCommandCenter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import spark.route.RouteOverview;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static spark.Spark.*;

public class BaseStationHTTPInterface {

    public static void main(String[] args) {
        JsonParser mParser = new JsonParser();
        Gson gson = new Gson();
        JsonParser jp = new JsonParser();
        AbstractBaseStation mStation = new AbstractBaseStation();
        RouteOverview.enableRouteOverview("/");

        post("/addBot", (req,res) -> {
            System.out.println("addbot");
            String body = req.body();
            JsonObject rBody = jp.parse(body).getAsJsonObject();
            String ip = rBody.get("ip").getAsString();
            int port = rBody.get("port").getAsInt();
            IceConnection ic = new IceConnection(ip, port);
            ModBot mb = new ModBot(ic);
            mStation.addBot(mb);
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

        get("/derp", (req, res) -> renderContent("file:/C://Users/CornellCup/Documents/GitHub/cs-base-station-interface/index.html"));
        get("/derp.js", (req, res) -> renderContent("file:/C://Users/CornellCup/Documents/GitHub/cs-base-station-interface/derp.js"));

    }
    private static String renderContent(String htmlFile) {
        try {
            // If you are using maven then your files
            // will be in a folder called resources.
            // getResource() gets that folder
            // and any files you specify.
            System.out.println(htmlFile);
            URL url = new URL(htmlFile);
            System.out.println(url);

            // Return a String which has all
            // the contents of the file.
            Path path = Paths.get(url.toURI());
            return new String(Files.readAllBytes(path), Charset.defaultCharset());
        } catch (IOException | URISyntaxException e) {
            // Add your own exception handlers here.
        }
        return null;
    }
}
