package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CsvReader extends Thread {
    private File file;
    private String splitter;
    private String outputPath;

    public CsvReader(File file, String splitter, String outputPath) {
        this.file = file;
        this.splitter = splitter;
        this.outputPath = outputPath;
    }

    public void run() {
        if (!outputPath.endsWith("\\")) {
            outputPath = outputPath.concat("\\");
        }
        try (BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line = br1.readLine();
            String[] names = line.split(splitter);
            for (String elem : names) {
                if (new File(outputPath + elem + ".txt").createNewFile()) {
                    System.out.println("file created: " + elem);
                } else {
                    System.out.println("file exists: " + elem);
                }
            }
            while ((line = br1.readLine()) != null) {
                String[] values = line.split(splitter);
                for (int i = 0; i < values.length; i++) {
                    synchronized (CsvReader.class) {
                        try (BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(new File(outputPath + names[i] + ".txt")), StandardCharsets.UTF_8))) {
                            String lineValues = "";
                            String filename = names[i];
                            String value = values[i];
                            if ((lineValues = br2.readLine()) != null) {
                                List<String> listValues = Arrays.asList(lineValues.split(splitter));
                                if (!listValues.contains(values[i])) {
                                    write(filename, value);
                                    System.out.println("filename: " + filename + ", added value: " + value + ", position: " + listValues.size());
                                } else {
                                    System.out.println("filename: " + filename + ", value exists: " + value);
                                }
                            } else {
                                write(filename, value);
                                System.out.println("filename: " + filename + ", added value: " + value + ", position: " + 1);
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized void write(String filename, String value) throws IOException {
        new FileWriter(new File(outputPath + filename + ".txt"), true).append(value).append(splitter).close();
    }
}
