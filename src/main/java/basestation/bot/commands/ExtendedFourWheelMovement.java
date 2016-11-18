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
    public ExtendedFourWheelMovement(Connection connection) {
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

    public void stop() {
        setWheelPower(0, 0, 0, 0);
    }
}