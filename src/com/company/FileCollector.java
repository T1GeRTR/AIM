package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileCollector {
    private final List<String> fileList;
    private String outputPath;

    public FileCollector(){
        this.fileList = new ArrayList<>();
    }

    public void addFiles(String[] args){
        outputPath = "D:\\output\\";
        fileList.addAll(Arrays.asList(args).subList(0, args.length - 1));
        if (args[args.length - 1].endsWith(".csv")) {
            fileList.add(args[args.length - 1]);
        } else {
            outputPath = args[args.length - 1];
        }
    }

    public List<String> getFileList() {
        return fileList;
    }

    public String getOutputPath() {
        return outputPath;
    }
}
