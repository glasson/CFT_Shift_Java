package org.example;

import java.io.*;
import java.util.ArrayList;
import org.apache.commons.lang3.math.*;

public class Main {
    enum StatisticEnum {SHORT, FULL, NO}
    enum DataTypesEnum {STRING, INTEGER, FLOAT}

    static String resultPath = "./";
    static String filePrefix = null;
    static boolean needRewriteFile = true;
    static StatisticEnum statistics = StatisticEnum.NO;
    static ArrayList<String> inputFiles = new ArrayList<>();
    static String inputFilesPath = "./";

    public static void main(String[] args) {
        int argsLen = args.length;
        for (int i=0; i<argsLen; i++) {
            if (args[i].equals("-o")) {
                resultPath = args[i + 1];
                i++;
            } else if (args[i].equals("-p")) {
                filePrefix = args[i + 1];
                i++;
            } else if (args[i].equals("-a")) {
                needRewriteFile = false;
            }else if (args[i].equals("-s")) {
                statistics = StatisticEnum.SHORT;
            }else if (args[i].equals("-f")) {
                statistics = StatisticEnum.FULL;
            } else if (args[i].contains(".txt")) {
                inputFiles.add(args[i]);
            }
        }

        String[] resultFileNames = createOrCheckFiles(filePrefix, resultPath, needRewriteFile);

        if (needRewriteFile) {
            try (BufferedWriter intWriter = new BufferedWriter(new FileWriter(resultFileNames[0]));
                 BufferedWriter floatWriter = new BufferedWriter(new FileWriter(resultFileNames[1]));
                 BufferedWriter stringsWriter = new BufferedWriter(new FileWriter(resultFileNames[2]))) {

                for (String dataFile : inputFiles) {
                    String dataFilePath = inputFilesPath.concat(dataFile);
                    BufferedReader reader = new BufferedReader(new FileReader(dataFilePath));

                    String line;
                    while (true) {
                        line = reader.readLine();
                        if (line == null) break;
                        DataTypesEnum type = identifyLineType(line);
                        switch (type) {
                            case FLOAT:
                                floatWriter.write(line);
                                floatWriter.newLine();
                                break;
                            case STRING:
                                stringsWriter.write(line);
                                stringsWriter.newLine();
                                break;
                            case INTEGER:
                                intWriter.write(line);
                                intWriter.newLine();
                                break;
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        else{
            try (BufferedWriter intWriter = new BufferedWriter(new FileWriter(resultFileNames[0], true));
                 BufferedWriter floatWriter = new BufferedWriter(new FileWriter(resultFileNames[1], true));
                 BufferedWriter stringsWriter = new BufferedWriter(new FileWriter(resultFileNames[2], true))) {

                for (String dataFile : inputFiles) {
                    String dataFilePath = inputFilesPath.concat(dataFile);
                    BufferedReader reader = new BufferedReader(new FileReader(dataFilePath));

                    String line;
                    while (true) {
                        line = reader.readLine();
                        if (line == null) break;
                        DataTypesEnum type = identifyLineType(line);
                        switch (type) {
                            case FLOAT:
                                floatWriter.write(line);
                                floatWriter.newLine();
                                break;
                            case STRING:
                                stringsWriter.write(line);
                                stringsWriter.newLine();
                                break;
                            case INTEGER:
                                intWriter.write(line);
                                intWriter.newLine();
                                break;
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String[] createOrCheckFiles(String filePrefix, String path, boolean needRewriteFile){
        //concat prefix
        String[] fileNames = {"integers.txt", "floats.txt", "strings.txt"};
        if (filePrefix!=null) {
            for (int i = 0; i < fileNames.length; i++)
                fileNames[i] = filePrefix.concat(fileNames[i]);
        }
        // check slash
        String lastSymbol = path.substring(path.length() - 1);
        if (!lastSymbol.equals("/"))
            path = path.concat("/");

        //mkdirs
        File dirs = new File(path);
        if (!dirs.exists())
            dirs.mkdirs();

        //mk files
        for (int i = 0; i < fileNames.length; i++) {
            fileNames[i] = path.concat(fileNames[i]);
            if (needRewriteFile) {
                File file = new File(fileNames[i]);
                try {
                    if (!file.exists()) {
                        boolean isCreated = file.createNewFile();
                        if (!isCreated)
                            throw new IOException();
                    }
                } catch (IOException e) {
                    System.out.println("File can`t be created");
                }
            }
        }
        return fileNames;
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