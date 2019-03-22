import com.lesliefish.test02datagramchannel.DatagramChannelTester;
import com.lesliefish.test03socketchannel.SocketChannelTester;
import com.lesliefish.test04scatteringbytechannel.ScatteringByteChannelTester;
import com.lesliefish.test05gatheringbytechannel.GatheringByteChannelTester;
import com.lesliefish.test06bytebuffer.ByteBufferTester;
import com.lesliefish.test07selector.SelectorTester;
import com.lesliefish.test08pipe.PipeTester;

public class Main {
    public static void main(String[] args) {
        // ChannelTester.test();
        // FileChannelTester.test();
        // DatagramChannelTester.test();
        // SocketChannelTester.test();
        // ScatteringByteChannelTester.test();
        // GatheringByteChannelTester.test();
        // ByteBufferTester.test();
        //SelectorTester.test();
        PipeTester.test();
    }
}
