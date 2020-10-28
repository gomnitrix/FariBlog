package com.gomnitrix.commons.utils;

import com.gomnitrix.commons.configuration.GeneralConfig;
import com.gomnitrix.commons.exception.RsaKeyException;

import java.io.File;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

public abstract class RsaKeyPairUtil {
    private static final KeyFactory rsaKeyFactory;

    static {
        try {
            // init KeyFactory
            rsaKeyFactory = KeyFactory.getInstance(GeneralConfig.RSA);

            // check if rsa key exist, if not, then create it.
            File pubKeyFile = new File(GeneralConfig.PUB_FILE);
            File priKeyFile = new File(GeneralConfig.PRI_FILE);
            if (!pubKeyFile.exists() || !priKeyFile.exists()) {
                generateAndSaveKeyPair();
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RsaKeyException();
        }
    }

    public static void generateAndSaveKeyPair() {
        try {
            KeyPairGenerator gen = KeyPairGenerator.getInstance(GeneralConfig.RSA);
            gen.initialize(GeneralConfig.KEY_SIZE);
            KeyPair keyPair = gen.genKeyPair();
            byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
            IOUtil.writeFileWithBase64Encode(publicKeyBytes, GeneralConfig.PUB_FILE);
            byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
            IOUtil.writeFileWithBase64Encode(privateKeyBytes, GeneralConfig.PRI_FILE);
        } catch (Exception e) {
            throw new RsaKeyException();
        }
    }

    public static PublicKey getPublicKeyFromResource() {
        try {
            byte[] publicKeyBytes = IOUtil.readFileToBytesWithBase64Decode(GeneralConfig.PUB_FILE);
            return rsaKeyFactory.generatePublic(getX509SpecFromKeyBytes(publicKeyBytes));
        } catch (Exception e) {
            throw new RsaKeyException();
        }
    }

    public static PrivateKey getPrivateKeyFromResource() {
        try {
            byte[] privateKeyBytes = IOUtil.readFileToBytesWithBase64Decode(GeneralConfig.PRI_FILE);
            return rsaKeyFactory.generatePrivate(getX509SpecFromKeyBytes(privateKeyBytes));
        } catch (Exception e) {
            throw new RsaKeyException();
        }
    }

    public static KeyPair getKeyPair() {
        return new KeyPair(getPublicKeyFromResource(), getPrivateKeyFromResource());
    }

    private static X509EncodedKeySpec getX509SpecFromKeyBytes(byte[] keyBytes) {
        return new X509EncodedKeySpec(Objects.requireNonNull(keyBytes));
    }


}
