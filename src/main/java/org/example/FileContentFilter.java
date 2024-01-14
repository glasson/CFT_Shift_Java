package org.example;

import org.apache.commons.lang3.math.NumberUtils;

import java.io.*;
import java.util.ArrayList;

public class FileContentFilter {
    enum DataTypesEnum {STRING, INTEGER, FLOAT}

    boolean collectStatistics = false;
    ArrayList<String> dataFiles;
    FileManager fileManager;
    boolean needRewriteFile = true;
    Statistics statistics;


    public FileContentFilter(boolean needRewriteFile, ArrayList<String> dataFiles) {
        this.needRewriteFile = needRewriteFile;
        this.dataFiles = dataFiles;
    }

    public FileContentFilter(boolean needRewriteFile, ArrayList<String> dataFiles, Statistics statistics) {
        this.needRewriteFile = needRewriteFile;
        this.dataFiles = dataFiles;
        this.statistics = statistics;
        collectStatistics = true;
    }

    public void filterContent(String[] resultFileNames) {
        try (BufferedWriter intWriter = new BufferedWriter(new FileWriter(resultFileNames[0], !needRewriteFile));
             BufferedWriter floatWriter = new BufferedWriter(new FileWriter(resultFileNames[1], !needRewriteFile));
             BufferedWriter stringsWriter = new BufferedWriter(new FileWriter(resultFileNames[2], !needRewriteFile))) {

            for (String dataFile : dataFiles) {
                BufferedReader reader;
                try {
                    reader = new BufferedReader(new FileReader(dataFile));
                } catch (FileNotFoundException e) {
                    System.out.printf("file '%s' was not found and skipped\n", dataFile);
                    continue;
                }

                String line;
                while (true) {
                    line = reader.readLine();
                    if (line == null)
                        break;
                    DataTypesEnum type = identifyLineType(line);
                    switch (type) {
                        case FLOAT:
                            floatWriter.write(line);
                            floatWriter.newLine();
                            if (collectStatistics)
                                statistics.addFloat(line);
                            break;
                        case STRING:
                            stringsWriter.write(line);
                            stringsWriter.newLine();
                            if (collectStatistics)
                                statistics.addString(line);
                            break;
                        case INTEGER:
                            intWriter.write(line);
                            intWriter.newLine();
                            if (collectStatistics)
                                statistics.addInteger(line);
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static DataTypesEnum identifyLineType(String line) {
        if (NumberUtils.isCreatable(line)) {
            if (line.contains(".") || line.contains("e")) {
                return DataTypesEnum.FLOAT;
            } else {
                return DataTypesEnum.INTEGER;
            }
        } else {
            return DataTypesEnum.STRING;
        }
    }

    public void setStatistics(Statistics s) {
        statistics = s;
        collectStatistics = true;
    }
}
