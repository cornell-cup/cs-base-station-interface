package basestation.vision;

import Ice.Current;
import icemodules.VisionModule.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Adapter for the OverheadVision Ice communication.
 * This serves as a listen server for vision data. Vision connects to this
 * on port 10009, then data is constantly received and can be accessed through the VisionSystem methods.
 */
public class OverheadVisionSystem extends VisionSystem {

    private HashSet<VisionObject> set;
    private VisionListenServerThread vlst;

    public OverheadVisionSystem() {
        super(new VisionCoordinate(0,0,0));
        vlst = new VisionListenServerThread(this);
        vlst.start();
        set = new HashSet<VisionObject>();
    }

    /**
     * Handles a new input of data
     * @param data The data sent from overhead vision
     */
    private void processBlobs(Blob[] data) {
        HashSet<VisionObject> newSet = new HashSet<VisionObject>();
        for (Blob b : data) {
            VisionCoordinate vc = new VisionCoordinate(b.x, b.y, ((((b.orientation % (2 * Math.PI)) * 180 / (Math.PI))) + 180) % 360);
            VisionObject vo = new VisionObject(this, b.botID, vc);
            newSet.add(vo);
        }

        this.set = newSet;
    }


    @Override
    public Set<VisionObject> getAllObjects() {
        return set;
    }

    private class UpdateHandler extends _BaseInterfaceDisp {

        OverheadVisionSystem parent;

        UpdateHandler(OverheadVisionSystem parent) {
            this.parent = parent;
        }

        @Override
        public double ping(Current __current) {
            System.out.println("I have been pinged!");
            return 0;
        }

        @Override
        public int update(Blob[] data, Current __current) {
            parent.processBlobs(data);
            return 0;
        }
    }

    private class VisionListenServerThread extends Thread {

        OverheadVisionSystem parent;

        VisionListenServerThread(OverheadVisionSystem parent) {
            this.parent = parent;
        }

        public void run() {
            Ice.Communicator ic = Ice.Util.initialize();
            Ice.ObjectAdapter adapter = ic.createObjectAdapterWithEndpoints("Ping", "tcp -h * -p 10009");
            adapter.add(new UpdateHandler(parent), ic.stringToIdentity("Ping"));
            adapter.activate();
            System.out.println("waiting for vision connection");
            System.out.println(adapter.getEndpoints().toString());
            ic.waitForShutdown();
            ic.destroy();
        }
    }

}
