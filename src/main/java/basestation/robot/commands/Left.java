package basestation.robot.commands;

/**
 * Describes a left movement of a bot.
 */
public class Left extends WheelMovement {

    @Override
    public String getCommandName() {
        return "LEFT";
    }
}
