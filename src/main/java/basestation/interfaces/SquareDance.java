package basestation.interfaces;

import basestation.BaseStation;
import basestation.bot.robot.modbot.ModBot;
import basestation.bot.robot.modbot.ModbotCommandCenter;
import basestation.vision.VisionCoordinate;
import basestation.vision.VisionManager;

/**
 * Example Algorithm to make modbots dance in a small square
 */
public class SquareDance extends Thread {

    BaseStation abs;
    ModBot myBot;

    public SquareDance(BaseStation abs, ModBot myBot) {
        this.abs = abs;
        this.myBot = myBot;
        VisionManager vm = abs.getVisionManager();
        vm.getVisionSystemById(0).mapBotToVisionId(myBot, 0);
    }

    public void run() {
        boolean first = false;
        while (true) {
            ModbotCommandCenter mcc;
            if ((mcc = (ModbotCommandCenter) myBot.getCommandCenter()).destinationReached()) {
                if (first) {
                    mcc.gotoCoord(new VisionCoordinate(-1.6, -0.64));
                } else {
                    mcc.gotoCoord(new VisionCoordinate(-0.3, -0.64));
                }

                first = !first;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
