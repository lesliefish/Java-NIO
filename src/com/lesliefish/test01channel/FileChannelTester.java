package com.lesliefish.test01channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Set;

public class FileChannelTester {
    final String currentFilePath = System.getProperty("user.dir") + "/res/test.txt";

    public void readFileChannel() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(currentFilePath, "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        Charset charset = Charset.forName("US-ASCII");
        while (fileChannel.read(byteBuffer) > 0) {
            byteBuffer.rewind();
            System.out.print(charset.decode(byteBuffer));
            byteBuffer.flip();
        }
        fileChannel.close();
        randomAccessFile.close();
    }

    public void writeFileChannel(ByteBuffer byteBuffer) throws IOException {
        Set<StandardOpenOption> options = new HashSet<>();
        options.add(StandardOpenOption.CREATE);
        options.add(StandardOpenOption.APPEND);
        Path path = Paths.get(currentFilePath);

        FileChannel fileChannel = FileChannel.open(path, options);
        fileChannel.write(byteBuffer);
        fileChannel.close();
    }

    public static void test() {
        FileChannelTester fileChannelTester = new FileChannelTester();
        try {
            // 给已存在的文件附件内容
            fileChannelTester.writeFileChannel(ByteBuffer.wrap("\nmy name is lesliefish.".getBytes()));
            // 读文件
            fileChannelTester.readFileChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
