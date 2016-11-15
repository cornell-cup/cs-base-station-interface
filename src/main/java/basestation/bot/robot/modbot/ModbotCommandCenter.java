package basestation.bot.robot.modbot;

import basestation.BaseStation;
import basestation.bot.commands.ExtendedFourWheelMovement;
import basestation.bot.connection.Connection;
import basestation.bot.connection.IceConnection;
import basestation.vision.VisionCoordinate;

/**
 * Class who's methods are all the commands that can be issued to a bot
 * <p>
 * Each bot must implement this class with their own commands.
 */
public class ModbotCommandCenter extends ExtendedFourWheelMovement {

    private ModbotNavigator navigator;


    public ModbotCommandCenter(BaseStation parent, Connection connection, ModBot myBot) {
        super(connection);
        this.navigator = new ModbotNavigator(parent, myBot);
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
        if (connection instanceof IceConnection) {
            ((IceConnection) connection).setMotorPower(fl, fr, bl, br);
        }
    }

}