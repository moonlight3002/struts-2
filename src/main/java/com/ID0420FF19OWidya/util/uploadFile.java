package com.ID0420FF19OWidya.util;

import java.io.IOException;
import org.apache.commons.io.FileUtils;
import java.io.File;

public class uploadFile
{
    public static boolean upload(final File file, final String fileName, final String path) {
        boolean isUploadSuccess = false;
        System.out.println("uploadPicture");
        System.out.println("path" + path);
        final File d = new File(path);
        if (!d.exists()) {
            d.mkdir();
        }
        final File f = new File(path, fileName);
        try {
            FileUtils.copyFile(file, f);
            isUploadSuccess = f.exists();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return isUploadSuccess;
    }
}