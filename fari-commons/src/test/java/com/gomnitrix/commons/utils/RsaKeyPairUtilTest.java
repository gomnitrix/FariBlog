package com.gomnitrix.commons.utils;

import com.gomnitrix.commons.configuration.GeneralConfig;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class RsaKeyPairUtilTest {

    @Test
    void generateAndSaveKeyPair() {
        RsaKeyPairUtil.generateAndSaveKeyPair();
        File pub = new File(GeneralConfig.PUB_FILE);
        File pri = new File(GeneralConfig.PRI_FILE);
        Assert.assertTrue(pub.exists());
        Assert.assertTrue(pri.exists());
    }
}