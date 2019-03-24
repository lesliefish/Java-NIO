package com.lesliefish.test10file;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.List;

public class FileTester {
    public static void test() {
        FileTester fileTester = new FileTester();
        //fileTester.createFile();
        fileTester.writeFile();
        fileTester.readFile();
        fileTester.copyFile();
    }

    // 创建文件
    public void createFile() {
        try {
            Path path = Paths.get("D:\\java.txt");
            Path createFilePath = Files.createFile(path);
            System.out.println("Created a file at : " + createFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 读文件
    public void readFile() {
        Path path = Paths.get("D:\\java.txt");

        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 读文件
    public void writeFile() {
        Path path = Paths.get("D:\\java.txt");
        String question = "To be or not to be?";
        try {
            Files.write(path, question.getBytes(), StandardOpenOption.APPEND);
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 复制文件
    public void copyFile() {
        Path sourceFile = Paths.get("D:\\java.txt");
        Path targetFile = Paths.get("D:\\javaCopy.txt");
        try {
            Files.copy(sourceFile, targetFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            System.err.format("I/O Error when copying file");
        }
        Path newCopyFile = Paths.get("D:\\javaCopy.txt");
        Charset charset = Charset.forName("UTF-8");
        try {
            List<String> lines = Files.readAllLines(newCopyFile, charset);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
