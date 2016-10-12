package basestation.robot.commands;

/**
 * Describes a forward movement of a bot.
 */
public class Forward extends WheelMovement {

    @Override
    public String getCommandName() {
        return "FORWARD";
    }
}
