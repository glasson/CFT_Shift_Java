package org.example;


import java.util.ArrayList;

public class Main {
    static ArrayList<String> inputFiles = new ArrayList<>();
    static String resultPath = null;
    static String filePrefix = null;
    static boolean needRewriteFile;
    static Statistics statistics;


    public static void main(String[] args) {
        getArgs(args);
        FileManager fileManager = new FileManager(needRewriteFile, filePrefix, resultPath);
        FileContentFilter filter = new FileContentFilter(needRewriteFile, inputFiles, fileManager, statistics);

        String[] resultFileNames = fileManager.createOrCheckFiles();
        filter.filterContent(resultFileNames);
    }

    public static void getArgs(String[] args) {
        int argsLen = args.length;
        for (int i = 0; i < argsLen; i++) {
            if (args[i].equals("-o")) {
                resultPath = args[i + 1];
                i++;
            } else if (args[i].equals("-p")) {
                filePrefix = args[i + 1];
                i++;
            } else if (args[i].equals("-a")) {
                needRewriteFile = false;
            } else if (args[i].equals("-s")) {
                statistics = new Statistics(new ShortStatistics());
            } else if (args[i].equals("-f")) {
                statistics = new Statistics(new FullStatistics());
            } else if (args[i].contains(".txt")) {
                inputFiles.add(args[i]);
            }
        }
    }
}