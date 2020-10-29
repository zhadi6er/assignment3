package util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class FileReader {
    public static List<File> listFilesForFolder(final File folder) {
        return new ArrayList<>(Arrays.asList(folder.listFiles()));
    }

    public static List<File> listAllFiles(File folder){
        List<File> files = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                files.addAll(listFilesForFolder(fileEntry));
            } else {
                files.add(fileEntry);
            }
        }
        return files;
    }

    public static List<File> searchFile(File folder, String name){
        List<File> result = new ArrayList<>();
        List<File> files = listAllFiles(folder);
        for (File file:files){
            if (file.getName().equals(name)){
                result.add(file);
            }
        }
        return result;
    }
}
