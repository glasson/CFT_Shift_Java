package org.example;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static ArrayList<String> inputFiles = new ArrayList<>();
    static boolean needRewriteFile;
    static String resultPath = null;
    static String filePrefix = null;
    static Statistics statistics = null;


    public static void main(String[] args) {
        getArgs(args);
        FileManager fileManager = new FileManager(needRewriteFile, filePrefix, resultPath);
        FileContentFilter filter = new FileContentFilter(needRewriteFile, inputFiles);
        if (statistics != null)
            filter.setStatistics(statistics);

        String[] resultFileNames = fileManager.createOrCheckFiles();
        filter.filterContent(resultFileNames);
        if (statistics != null)
            filter.statistics.printResult();
        fileManager.deleteBlankFiles();
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
        if (inputFiles.size() == 0)
            inputFiles = loopInputDataFiles();
    }

    private static ArrayList<String> loopInputDataFiles() {
        System.out.println("Missing Data files");
        while (inputFiles.size() <= 0) {
            System.out.print("Input correct files>");
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            inputFiles = new ArrayList<>(Arrays.asList(line.split("\\s+")));
        }
        return inputFiles;
    }
}