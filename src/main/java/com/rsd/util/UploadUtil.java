package com.rsd.util;

import java.io.*;
import java.util.UUID;

public class UploadUtil {

    public static final String baseDir = "/uploadFiles/";
    public static String getNewFileName(String oldFileName) {
        String suffix = oldFileName.substring(oldFileName.lastIndexOf(".") + 1);
        return UUID.randomUUID().toString().replace("-","") + "." + suffix;
    }

    public static boolean upload(InputStream in , String uploadPath) {
        boolean result = false;
        OutputStream out = null;
        try {
            out = new FileOutputStream(uploadPath);

            int b = in.read();
            while (b != -1) {
                out.write(b);
                b = in.read();
            }
            result = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
}
