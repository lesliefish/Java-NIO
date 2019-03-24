package com.lesliefish.test09path;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTester {

    public static void test(){
        try
        {
            Path path = Paths.get("C:\\Windows\\regedit.exe");
            FileSystem fileSystem = path.getFileSystem();
            System.out.println(fileSystem.toString());
            System.out.println(path.isAbsolute());
            System.out.println(path.getFileName());// 文件名
            System.out.println(path.toAbsolutePath().toString());// 绝对路径
            System.out.println(path.getRoot());//根目录
            System.out.println(path.getParent());//父路径
            System.out.println(path.getNameCount());//路径中名称元素的数量
            System.out.println(path.getName(0));//返回第一个名称元素
            System.out.println(path.getName(1));//返回第二个名称元素
            System.out.println(path.subpath(0, 2));// 子路经
            System.out.println(path.toString());//路径String

            Path realPath = path.toRealPath(LinkOption.NOFOLLOW_LINKS);
            System.out.println(realPath.toString()); // 返回现有文件的实际路径

            String originalPath = "C:\\Windows\\..\\Windows\\regedit.exe";
            Path path1 = Paths.get(originalPath);
            Path path2 = path1.normalize();
            System.out.println("path2 = " + path2); // 路径常规化 打印
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
