import basestation.bot.connection.TCPConnection;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Administrator on 2/15/2017.
 */
public class TCPTest {

    /**
     * Requires: a server is running at the ip and port specified
     */
    @Test
    public void testConnect() {
        String ip = "192.168.4.84";
        int port = 10000;
        TCPConnection tcon = new TCPConnection(ip,port);
        assertTrue(tcon.sendKV("IMPORTANT","HI"));
    }
}
