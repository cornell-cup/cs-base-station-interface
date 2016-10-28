package basestation.interfaces;

import basestation.bot.connection.IceConnection;
import basestation.bot.modbot.ModBot;
import basestation.bot.modbot.ModbotCommandCenter;

import java.util.Scanner;

/**
 * CLI to test modbot commands
 */
public class ModbotCLI {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("ip?");
        String ip = in.nextLine();
        System.out.println("port?");
        int port = in.nextInt();

        IceConnection c = new IceConnection(ip, port);
        ModbotCommandCenter commandCenter = new ModBot(c).getCommandCenter();

        while (true) {
            System.out.println(">");
            String cmd = in.nextLine();
            String[] splt = cmd.split(" ");
            if (splt.length == 1) {
                if (splt[0].equals("STOP")) {
                    commandCenter.stop();
                }
            } else if (splt.length == 2) {
                double power = Double.parseDouble(splt[1]);
                switch (splt[0]) {
                    case ("FORWARD"):
                        commandCenter.forward(power);
                        break;
                    case ("BACKWARD"):
                        commandCenter.backward(power);
                        break;
                    case ("LEFT"):
                        commandCenter.left(power);
                        break;
                    case ("RIGHT"):
                        commandCenter.right(power);
                        break;
                    case ("CLOCKWISE"):
                        commandCenter.clockwise(power);
                        break;
                    case ("CCLOCKWISE"):
                        commandCenter.counterClockwise(power);
                        break;
                    case ("STOP"):
                        commandCenter.stop();
                        break;
                    default:
                        System.err.println("unrecognized command to ice interface");
                }
            } else {
                // Safety stop
                commandCenter.stop();
            }
        }
    }
}
