package basestation.bot.modbot;

import basestation.BaseStation;
import basestation.bot.commands.ExtendedFourWheelMovement;
import basestation.bot.connection.Connection;
import basestation.bot.connection.IceConnection;
import basestation.bot.commands.Navigator;

/**
 * Class who's methods are all the commands that can be issued to a bot
 * <p>
 * Each bot must implement this class with their own commands.
 */
public class ModbotCommandCenter extends ExtendedFourWheelMovement {

    //Modbot command center currently implemented with ice connection
    private Connection connection;
    private Navigator navigator;
    private BaseStation parent;
    private ModBot myBot;

    public ModbotCommandCenter(BaseStation parent, Connection connection, ModBot myBot) {
        this.connection = connection;
        this.parent = parent;
        this.myBot = myBot;
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

    /**
     * bot translates forward
     *
     * @param power the amount of power that should be supplied to the wheels. It should be between 0 and 100
     */
    public void forward(double power) {
        assert power >= 0 && power <= 100;
        setWheelPower(power, power, power, power);
    }

    /**
     * bot translates backward
     *
     * @param power the amount of power that should be supplied to the wheels. It should be between 0 and 100
     */
    public void backward(double power) {
        assert power >= 0 && power <= 100;
        setWheelPower(-power, -power, -power, -power);
    }

    /**
     * bot rotates clockwise
     *
     * @param power the amount of power that should be supplied to the wheels. It should be between 0 and 100
     */
    public void clockwise(double power) {
        assert power >= 0 && power <= 100;
        setWheelPower(power, -power, power, -power);
    }

    /**
     * bot rotates counterclockwise
     *
     * @param power the amount of power that should be supplied to the wheels. It should be between 0 and 100
     */
    public void counterClockwise(double power) {
        assert power >= 0 && power <= 100;
        setWheelPower(-power, power, -power, power);
    }

    @Override
    public void setWheelPower(double fl, double fr, double bl, double br) {
        if (connection instanceof IceConnection) {
            ((IceConnection) connection).setMotorPower(fl, fr, bl, br);
        }
    }

    /**
     * Navigates the bot to (x,y) using its built in navigator.
     * Requires an active vision system and association between the bot and the system.
     * @param x
     * @param y
     */
    public void gotoCoord(double x, double y) {
        if (this.navigator == null) {
            this.navigator = new Navigator(parent,myBot);
        }
    }
}