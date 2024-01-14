package org.example;

import java.io.File;
import java.io.IOException;

public class FileManager {
    boolean needRewriteFile;
    String filePrefix;
    String filteredFilesPath = "./";
    String[] fileNames = {"integers.txt", "floats.txt", "strings.txt"};

    public FileManager(boolean needRewriteFile, String filePrefix, String filteredFilesPath){
        this.needRewriteFile = needRewriteFile;
        this.filePrefix = filePrefix;
        if (filteredFilesPath!=null)
            this.filteredFilesPath = filteredFilesPath;
        checkSlash();
    }

    public String[] createOrCheckFiles(){//result
        //concat prefix
        String[] fileNames = getFileNamesWithPrefix();
        //mk files
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

    private String[] getFileNamesWithPrefix(){
        if (filePrefix!=null) {
            for (int i = 0; i < fileNames.length; i++)
                fileNames[i] = filePrefix.concat(fileNames[i]);
        }
        return fileNames;
    }

    private void checkSlash(){
        String lastSymbol = filteredFilesPath.substring(filteredFilesPath.length() - 1);
        if (!lastSymbol.equals("/"))
            filteredFilesPath = filteredFilesPath.concat("/");
    }
    private void checkOrMakeDirs(){
        //mkdirs
        try {
            File dirs = new File(filteredFilesPath);
            if (!dirs.exists())
                dirs.mkdirs();
        }catch (Exception e){
            System.out.println("Path is not exist or can`t be made");
        }
    }

    public void deleteBlankFiles(Statistics statistics){
        checkSlash();
        String[] fileNames = getFileNamesWithPrefix();
        int i = statistics.getIntegerCount();
        int f = statistics.getFloatCount();
        int s = statistics.getStringCount();
        if (i==0){
            var intFile = filteredFilesPath.concat(fileNames[0]);
            (new File(intFile)).delete();
        }
        if (f==0){
            var floatFile = filteredFilesPath.concat(fileNames[1]);
            (new File(floatFile)).delete();
        }
        if (s==0){
            var strFile = filteredFilesPath.concat(fileNames[2]);
            (new File(strFile)).delete();
        }
    }

}
