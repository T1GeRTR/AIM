package com.company;

import java.io.File;
import java.util.List;

public class ThreadsStarter {

    public ThreadsStarter() {
    }

    public void start(List<String> fileList, String outputPath, String splitter) {
        for (String elem : fileList) {
            CsvReader csvReader = new CsvReader(new File(elem), splitter, outputPath);
            csvReader.start();
        }
    }
}
