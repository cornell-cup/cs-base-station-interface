package basestation.interfaces;

import basestation.AbstractBaseStation;
import basestation.vision.VisionCoordinate;

/**
 * Example Algorithm to make modbots dance in a small square
 */
public class SquareDance extends Thread {

    AbstractBaseStation abs;
    Navigator ng;

    public SquareDance(AbstractBaseStation abs) {
        this.abs = abs;
        ng = new Navigator(abs, 0);
    }

    public void run() {
        System.out.println(abs.getAllLocationData());
        boolean first = false;
        while (true) {
            if (ng.destinationReached()) {
                if (first) {
                    ng.setDestination(new VisionCoordinate(-1.6,-0.64));
                } else {
                    ng.setDestination(new VisionCoordinate(-0.3,-0.64));
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
