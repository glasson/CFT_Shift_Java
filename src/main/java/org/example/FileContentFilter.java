package org.example;

import org.apache.commons.lang3.math.NumberUtils;

import java.io.*;
import java.util.ArrayList;

public class FileContentFilter {
    enum DataTypesEnum {STRING, INTEGER, FLOAT}

    boolean needRewriteFile = true;
    ArrayList<String> inputFiles;
    FileManager fileManager;
    Statistics statistics;

    public FileContentFilter(boolean needRewriteFile, ArrayList<String> inputFiles, FileManager fileManager){
        this.needRewriteFile=needRewriteFile;
        this.inputFiles=inputFiles;
        this.fileManager = fileManager;
    }
    public FileContentFilter(boolean needRewriteFile, ArrayList<String> inputFiles, FileManager fileManager, Statistics statistics){
        this.needRewriteFile=needRewriteFile;
        this.inputFiles=inputFiles;
        this.statistics = statistics;
        this.fileManager = fileManager;
    }

    public void filterContent(String[] resultFileNames){
        try (BufferedWriter intWriter = new BufferedWriter(new FileWriter(resultFileNames[0], !needRewriteFile));
             BufferedWriter floatWriter = new BufferedWriter(new FileWriter(resultFileNames[1], !needRewriteFile));
             BufferedWriter stringsWriter = new BufferedWriter(new FileWriter(resultFileNames[2], !needRewriteFile))) {

            for (String dataFile : inputFiles) {
                BufferedReader reader = new BufferedReader(new FileReader(dataFile));

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
                            statistics.addFloat(line);
                            break;
                        case STRING:
                            stringsWriter.write(line);
                            stringsWriter.newLine();
                            statistics.addString(line);
                            break;
                        case INTEGER:
                            intWriter.write(line);
                            intWriter.newLine();
                            statistics.addInteger(line);
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static DataTypesEnum identifyLineType(String line){
        if (NumberUtils.isCreatable(line)){
            if (line.contains(".") || line.contains("e")){
                return DataTypesEnum.FLOAT;
            } else {
                return DataTypesEnum.INTEGER;
            }
        } else {
            return DataTypesEnum.STRING;
        }
    }
}
