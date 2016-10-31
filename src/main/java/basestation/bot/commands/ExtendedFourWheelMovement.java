package basestation.bot.commands;

import basestation.BaseStation;
import basestation.bot.connection.Connection;
import basestation.bot.robot.modbot.ModBot;
import basestation.vision.VisionCoordinate;

/**
 * Extends four wheel movement with some useful commands.
 * <p>
 * Power is the power that will be supplied to all the wheels to achieve this command. It should be a number
 * that is between 0 and 100
 */
public abstract class ExtendedFourWheelMovement implements FourWheelMovement {

    private Navigator navigator;
    private BaseStation parent;
    private ModBot myBot;
    protected Connection connection;

    public ExtendedFourWheelMovement(BaseStation parent, Connection connection, ModBot myBot) {
        this.parent = parent;
        this.connection = connection;
        this.myBot = myBot;
    }

    public void forward(double power) {
        assert power >= 0 && power <= 100;
        setWheelPower(power, power, power, power);
    }

    public void backward(double power) {
        assert power >= 0 && power <= 100;
        setWheelPower(-power, -power, -power, -power);
    }

    public void clockwise(double power) {
        assert power >= 0 && power <= 100;
        setWheelPower(power, -power, power, -power);
    }

    public void counterClockwise(double power) {
        assert power >= 0 && power <= 100;
        setWheelPower(-power, power, -power, power);
    }

    /**
     * Navigates the bot to (x,y) using its built in navigator.
     * Requires an active vision system and association between the bot and the system.
     */
    public void gotoCoord(VisionCoordinate vc) {
        if (this.navigator == null) {
            this.navigator = new Navigator(parent, myBot);
        }

        this.navigator.setDestination(vc);
    }

    /**
     * @return true if the bot has reached its destination. TODO: convert to a destination queue
     */
    public boolean destinationReached() {
        return this.navigator.destinationReached();
    }

    public void stop() {
        setWheelPower(0, 0, 0, 0);
    }
}