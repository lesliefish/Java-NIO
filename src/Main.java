import com.lesliefish.test02datagramchannel.DatagramChannelTester;
import com.lesliefish.test03socketchannel.SocketChannelTester;
import com.lesliefish.test04scatteringbytechannel.ScatteringByteChannelTester;

public class Main {
    public static void main(String[] args) {
        // ChannelTester.test();
        // FileChannelTester.test();
        // DatagramChannelTester.test();
        // SocketChannelTester.test();
        ScatteringByteChannelTester.test();
    }
}
