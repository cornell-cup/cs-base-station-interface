package basestation.bot.robot.minibot;

/**
 * Created by Administrator on 1/28/2017.
 */

import basestation.BaseStation;
import basestation.bot.commands.ExtendedFourWheelMovement;
import basestation.bot.connection.Connection;
import basestation.bot.connection.IceConnection;
import basestation.bot.connection.MotorConnection;
import basestation.bot.robot.minibot.MiniBot;
import basestation.bot.robot.minibot.MinibotNavigator;
import basestation.vision.VisionCoordinate;

/**
 * Class who's methods are all the commands that can be issued to a bot
 * <p>
 * Each bot must implement this class with their own commands.
 */
public class MinibotCommandCenter extends ExtendedFourWheelMovement {
    private final Connection connection;

    private final MinibotNavigator navigator;

    public MinibotCommandCenter(BaseStation parent, Connection connection, MiniBot myBot) {
        super(connection);
        this.navigator = new MinibotNavigator(parent, myBot);
        this.connection = connection;
    }

    /**
     * @return true if the bot has reached its destination. TODO: convert to a destination queue
     */
    public boolean destinationReached() {
        return this.navigator.destinationReached();
    }

    /**
     * Navigates the bot to (x,y) using its built in navigator.
     * Requires an active vision system and association between the bot and the system.
     */
    public void gotoCoord(VisionCoordinate vc) {
        this.navigator.setDestination(vc);
    }


    /**
     * bot translates left
     *
     * @param power the amount of power that should be supplied to the wheels. It should be between 0 and 100
     */
    public void left(double power) {
        assert power >= 0 && power <= 100;
        setWheelPower(-power, power, power, -power);
    }

    /**
     * bot translates right
     *
     * @param power the amount of power that should be supplied to the wheels. It should be between 0 and 100
     */
    public void right(double power) {
        assert power >= 0 && power <= 100;
        setWheelPower(power, -power, -power, power);
    }

    @Override
    public void setWheelPower(double fl, double fr, double bl, double br) {
        if (connection instanceof MotorConnection) {
            ((MotorConnection) connection).setMotorPower(fl, fr, bl, br);
        }
    }

}
