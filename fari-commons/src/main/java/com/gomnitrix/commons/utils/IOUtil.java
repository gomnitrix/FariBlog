package com.gomnitrix.commons.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class IOUtil {
    public static boolean isFileExist(String fileName){
        File file = new File(fileName);
        return file.exists();
    }

    public static boolean isFileExist(File file){
        return file.exists();
    }

    public static boolean createFile(String fileName) throws IOException {
        File file = new File(fileName);
        return file.createNewFile();
    }

    public static void writeFile(byte[] stream, String fileName) throws IOException {
        if(!isFileExist(fileName)){
            createFile(fileName);
        }
        FileOutputStream fos = new FileOutputStream(fileName);
        fos.write(stream);
        fos.close();
    }

    public static void writeFileWithBase64Encode(byte[] stream, String fileName) throws IOException {
        writeFile(Base64Util.base64EncodeToBytes(stream), fileName);
    }

    public static byte[] readFileToBytes(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        byte[] content = fis.readAllBytes();
        fis.close();
        return content;
    }

    public static byte[] readFileToBytesWithBase64Decode(String fileName) throws IOException {
        byte[] cipherBytes = readFileToBytes(fileName);
        return Base64Util.base64DecodeToBytes(cipherBytes);
    }
}
