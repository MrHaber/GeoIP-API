package ru.Haber.GeoIP.Utils;

import java.io.File;
import java.io.FilenameFilter;

import org.jetbrains.annotations.NotNull;

public class FileUtils {
/*
 * 
 * [RU] Алгоритмы для работы с папками
 * [EN] Folder Algorithms
 */
    public final static File[] getFileList(@NotNull String dirPath, @NotNull String endWith) {
        File dir = new File(dirPath);   

        File[] fileList = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(endWith);
            }
        });
        return fileList;
    }
    
    public File getCurrentFileFromArray(@NotNull File[] file, @NotNull int index) {
    	return file[index];
    }
    
    public static String getFileNameFromArray(@NotNull File[] file, @NotNull int index) {
		return file[index].getName();
    	
    }

}
