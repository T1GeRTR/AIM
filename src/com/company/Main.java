package com.company;

public class Main {

    public static void main(String[] args) {
        FileCollector fc = new FileCollector();
        fc.addFiles(args);
        new ThreadsStarter().start(fc.getFileList(), fc.getOutputPath(), ";");
    }
}
