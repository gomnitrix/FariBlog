package com.gomnitrix.commons.utils;

import com.sun.source.tree.AssertTree;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class IOUtilTest {
    private static final byte[] testBytes = "this is a test".getBytes();
    private static final String rawPath = IOUtil.class.getResource("").toString();
    private static final String path = rawPath.substring(rawPath.indexOf("/"));
    private static final String testFilePath = path+"Test.txt";
    private static final String testFilePathB64 = path+"TestB64.txt";

    @Test
    void createFile() throws IOException{
        IOUtil.createFile(testFilePath);
        IOUtil.createFile(testFilePathB64);
        Assert.assertTrue(new File(testFilePath).exists());
        Assert.assertTrue(new File(testFilePathB64).exists());
    }

    @Test
    void writeFile() throws IOException {
        IOUtil.writeFile(testBytes, testFilePath);
    }

    @Test
    void writeFileWithBase64Encode() throws IOException {
        IOUtil.writeFileWithBase64Encode(testBytes, testFilePathB64);
    }

    @Test
    void readFileToBytes() throws IOException {
        Assert.assertArrayEquals(testBytes, IOUtil.readFileToBytes(testFilePath));
    }

    @Test
    void readFileToBytesWithBase64Decode() throws IOException {
        Assert.assertArrayEquals(testBytes, IOUtil.readFileToBytesWithBase64Decode(testFilePathB64));
    }
}