package basestation.robot.commands;

/**
 * Describes a backward movement of a bot.
 */
public class Backward extends WheelMovement {

    @Override
    public String getCommandName() {
        return "BACKWARD";
    }
}
