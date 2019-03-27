package com.lesliefish.test13filelock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PrintFileCreated {
    public static void print(String path) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String str = bufferedReader.readLine();
            System.out.println("The Content of javacopy.txt file is:");

            while (str != null) {
                System.out.println("    " + str);
                str = bufferedReader.readLine();
            }
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
