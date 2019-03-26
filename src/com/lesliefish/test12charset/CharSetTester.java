package com.lesliefish.test12charset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class CharSetTester {
    public static void test() {

        Charset charset = Charset.forName("US-ASCII");
        System.out.println(charset.displayName());
        System.out.println(charset.canEncode());

        String str= "Demo text for conversion.";
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());
        CharBuffer charBuffer = charset.decode(byteBuffer);


        ByteBuffer newByteBuffer = charset.encode(charBuffer);
        while(newByteBuffer.hasRemaining()){
            char ch = (char) newByteBuffer.get();
            System.out.print(ch);
        }
        newByteBuffer.clear();
    }
}
