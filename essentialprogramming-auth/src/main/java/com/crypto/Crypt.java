package com.crypto;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

/**
 * Implements AES (Advanced Encryption Standard) with Galois/Counter Mode (GCM), which is a mode of
 * operation for symmetric key cryptographic block ciphers that has been widely adopted because of
 * its efficiency and performance.
 *
 * Based on open source code from Patrick Favre-Bulle:
 * https://proandroiddev.com/security-best-practices-symmetric-encryption-with-aes-in-java-7616beaaade9?gi=3f161fd33990
 */
public final class Crypt {

    private static final String SALT = "supercalifragilisticexpialidocious";
    private static final byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static final String CIPHER_ALGORITHM = "AES/GCM/NoPadding";
    private static final String SECRET_KEY_FACTORY_INSTANCE = "PBKDF2WithHmacSHA256";
    private static final String ENCRYPTION_ALGORITHM = "AES";

    private Crypt() {
        throw new IllegalAccessError("Instantiation prohibited");
    }

    /**
     * Encrypts a value
     *
     * @param value to encrypt
     * @return encrypted value in base64 format
     */
    public static String encrypt(String value, String secretKey)
            throws GeneralSecurityException {

        Cipher cipher = initCipher(secretKey, Cipher.ENCRYPT_MODE);

        byte[] utf8 = value.getBytes(StandardCharsets.UTF_8);
        byte[] encrypted = cipher.doFinal(utf8);

        return Base64.getUrlEncoder().withoutPadding().encodeToString(encrypted);
    }

    /**
     * Decrypts an encrypted value
     *
     * @param value to decrypt
     * @return decrypted value
     */
    public static String decrypt(String value, String secretKey)
            throws GeneralSecurityException {

        Cipher cipher = initCipher(secretKey, Cipher.DECRYPT_MODE);

        byte[] decoded = Base64.getUrlDecoder().decode(value);
        byte[] decrypted = cipher.doFinal(decoded);

        // Decode using utf-8
        return new String(decrypted, StandardCharsets.UTF_8);
    }

    private static Cipher initCipher(String secretKey, int encryptMode) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        GCMParameterSpec parameterSpec = new GCMParameterSpec(128, iv);

        SecretKeyFactory factory = SecretKeyFactory.getInstance(SECRET_KEY_FACTORY_INSTANCE);
        KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), SALT.getBytes(), 65536, 128);
        SecretKey temporaryKey = factory.generateSecret(spec);

        Key secretKeySpec = new SecretKeySpec(temporaryKey.getEncoded(), ENCRYPTION_ALGORITHM);


        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(encryptMode, secretKeySpec, parameterSpec);
        return cipher;
    }


}
