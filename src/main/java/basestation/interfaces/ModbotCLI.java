package basestation.interfaces;

import basestation.robot.connection.IceConnection;

import java.util.HashMap;
import java.util.Map;
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

        while (true) {
            System.out.println(">");
            String cmd = in.nextLine();
            String[] splt = cmd.split(" ");
            if (splt.length == 1) {
                if (splt[0].equals("STOP")) {
                    Map<String,String> m = new HashMap<>();
                    m.put(splt[0], "0");
                    c.sendKV(m);
                }
            } else if (splt.length == 2) {
                Map<String,String> m = new HashMap<>();
                m.put(splt[0], splt[1]);
                c.sendKV(m);
            } else {
                // Safety stop
                Map<String,String> m = new HashMap<>();
                m.put("STOP", "0");
                c.sendKV(m);
            }
        }
    }
}
