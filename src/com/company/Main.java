package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> fileList = new ArrayList<>();
        String outputPath = "";
        for(int i = 0; i < args.length - 1; i++){
            fileList.add(args[i]);
        }
        outputPath = args[args.length - 1];
        new ThreadsStarter().start(fileList, outputPath, ";");
    }
}
