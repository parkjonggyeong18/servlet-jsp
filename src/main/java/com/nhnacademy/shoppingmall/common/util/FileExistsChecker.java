package com.nhnacademy.shoppingmall.common.util;

import java.io.File;

public class FileExistsChecker {
    public static boolean checkFileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }
}