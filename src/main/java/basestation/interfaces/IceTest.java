package basestation.interfaces;

import CommModule.BaseInterfacePrx;
import CommModule.BaseInterfacePrxHelper;

/**
 * Created by trevor on 10/12/16.
 */
public class IceTest {
      public static void
    main(String[] args)
    {
        int status = 0;
        Ice.Communicator ic = null;
        try {
            ic = Ice.Util.initialize(args);
            System.out.println("wee");
            Ice.ObjectPrx base = ic.stringToProxy("control -t -e 1.0:tcp -h 192.168.4.20 -p 10000");
            BaseInterfacePrx printer = BaseInterfacePrxHelper.checkedCast(base);
            if (printer == null)
                throw new Error("Invalid proxy");

            printer.begin_setMotorSpeeds(37,37,15,15);
            System.out.println("ok");
        } catch (Ice.LocalException e) {
            e.printStackTrace();
            status = 1;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            status = 1;
        }
        if (ic != null) {
            // Clean up
            //
            try {
                ic.destroy();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                status = 1;
            }
        }
        System.exit(status);
    }
}
