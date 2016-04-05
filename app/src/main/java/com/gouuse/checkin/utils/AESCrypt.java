package com.gouuse.checkin.utils;

import android.util.Base64;

import com.gouuse.checkin.constant.URL;

import java.security.MessageDigest;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * Created by zhengjiong on 15/10/23.
 */
public class AESCrypt {
    public static AESCrypt aesCrypt;


    private final Cipher cipher;
    private final SecretKeySpec key;
    private AlgorithmParameterSpec spec;
    public static String SEED_16_CHARACTER;

    public static AESCrypt getInstance() {
        if (aesCrypt == null) {
            synchronized (AESCrypt.class) {
                if (aesCrypt == null) {
                    try {
                        aesCrypt = new AESCrypt();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return aesCrypt;
    }
    public AlgorithmParameterSpec getIV() {
        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };
        IvParameterSpec ivParameterSpec;
        ivParameterSpec = new IvParameterSpec(iv);
        return ivParameterSpec;
    }

    /**
     * 加密
     * @param plainText
     * @return
     * @throws Exception
     */
    public String encrypt(String plainText) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);
        byte[] encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
        String encryptedText = new String(Base64.encode(encrypted, Base64.DEFAULT), "UTF-8");
        return encryptedText.replaceAll("\n", "");
    }

    /**
     * 解密
     * @param cryptedText
     * @return
     * @throws Exception
     */
    public String decrypt(String cryptedText) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
        byte[] bytes = Base64.decode(cryptedText, Base64.DEFAULT);
        byte[] decrypted = cipher.doFinal(bytes);
        String decryptedText = new String(decrypted, "UTF-8");
        return decryptedText;
    }

    public AESCrypt() throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        //Time time = new Time();
        //time.setToNow();
        String formatTime = URL.APPKEY;// + time.format("%Y-%m-%d");
        //String formatTime = "8a48b55150d5879c0150d7fe593e17ae";
        SEED_16_CHARACTER = formatTime + "gou";
        SEED_16_CHARACTER = Basic_Util.getInstance().MD5_Encryption(SEED_16_CHARACTER).replaceAll("\n", "").substring(0, 8);
        digest.update(SEED_16_CHARACTER.getBytes("UTF-8"));
        byte[] keyBytes = new byte[32];
        System.arraycopy(digest.digest(), 0, keyBytes, 0, keyBytes.length);
        cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        key = new SecretKeySpec(keyBytes, "AES");
        spec = getIV();
    }
}
