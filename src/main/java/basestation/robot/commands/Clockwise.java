package basestation.robot.commands;

/**
 * Describes a clockwise movement of a bot.
 */
public class Clockwise extends WheelMovement {

    @Override
    public String getCommandName() {
        return "CLOCKWISE";
    }
}
