package org.example;

import java.io.File;
import java.io.IOException;

public class FileManager {
    String filePrefix;
    String filteredFilesPath = "./";
    String[] fileNames = {"integers.txt", "floats.txt", "strings.txt"};
    boolean needRewriteFile;

    public FileManager(boolean needRewriteFile, String filePrefix, String filteredFilesPath) {
        this.needRewriteFile = needRewriteFile;
        this.filePrefix = filePrefix;
        if (filteredFilesPath != null)
            this.filteredFilesPath = filteredFilesPath;
        checkSlash();
    }

    public String[] createOrCheckFiles() {//result
        String[] fileNames = getFileNamesWithPrefix();
        checkOrMakeDirs();
        //mk result files
        for (int i = 0; i < fileNames.length; i++) {
            fileNames[i] = filteredFilesPath.concat(fileNames[i]);
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

    private String[] getFileNamesWithPrefix() {
        if (filePrefix != null) {
            for (int i = 0; i < fileNames.length; i++)
                fileNames[i] = filePrefix.concat(fileNames[i]);
        }
        return fileNames;
    }

    private void checkSlash() {
        String lastSymbol = filteredFilesPath.substring(filteredFilesPath.length() - 1);
        if (!lastSymbol.equals("/"))
            filteredFilesPath = filteredFilesPath.concat("/");
    }

    private void checkOrMakeDirs() {
        //mkdirs
        try {
            File dirs = new File(filteredFilesPath);
            if (!dirs.exists())
                dirs.mkdirs();
        } catch (Exception e) {
            System.out.println("Path is not exist or can`t be made");
        }
    }

    public void deleteBlankFiles() {
        checkSlash();
        var intFilePath = fileNames[0];
        var intFile = new File(intFilePath);
        if (intFile.exists() && intFile.length() == 0) {
            intFile.delete();
            System.out.println("Integers was not found");
        }

        var floatFilePath = fileNames[1];
        var floatFile = new File(floatFilePath);
        if (floatFile.exists() && floatFile.length() == 0) {
            floatFile.delete();
            System.out.println("Real numbers was not found");
        }

        var strFilePath = fileNames[2];
        var strFile = new File(strFilePath);
        if (strFile.exists() && strFile.length() == 0) {
            strFile.delete();
            System.out.println("Strings was not found");
        }


    }

}
