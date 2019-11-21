package com.uxnux.boot.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @Author: 10785
 * @Date: 2019/11/17 7:46
 * @Version: 1.0
 */
@Slf4j
public class CryptoUtils {

    /**
     *
     */
    private static final String AES_KEY = "0123456789abcdef";

    private static final String IV = "0123456789abcdef";

    private static final String AES_TRANSFORMATION = "AES/CBC/PKCS5Padding";

    private static final String AES_ALGORITHM = "AES";

    /**
     * 加密
     * @param src
     * @param transformation
     * @param algorithm
     * @param ivs
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws UnsupportedEncodingException
     */
    private static byte[] encrypt(String src,String transformation, String algorithm, byte[] ivs, byte[] key) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException,
            UnsupportedEncodingException {
        // 根据给定的字节数组构造一个密钥。
        SecretKey secretKey = new SecretKeySpec(key, algorithm);
        // 创建Cipher对象  transformation - 转换的名称
        Cipher cipher = Cipher.getInstance(transformation);
        // 用取自给定证书的公钥初始化此 Cipher
        // 第一个参数Cipher 的操作模式（为以下之一：ENCRYPT_MODE、DECRYPT_MODE、WRAP_MODE 或 UNWRAP_MODE）
        // 第二个参数证书
        // 第三个参数 偏移量IV
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(ivs));
        // 按单部分操作加密或解密数据（具体取决于此 Cipher 的初始化方式，即ENCRYPT_MODE、DECRYPT_MODE、WRAP_MODE 或 UNWRAP_MODE）。
        byte[] cipherBytes = cipher.doFinal(src.getBytes());
        return cipherBytes;
    }

    /**
     * 解密
     * @param src
     * @param transformation
     * @param algorithm
     * @param ivs
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws UnsupportedEncodingException
     */
    private static byte[] decrypt(String src, String transformation, String algorithm, byte[] ivs, byte[] key) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException,
            UnsupportedEncodingException {
        SecretKey secretKey = new SecretKeySpec(key, algorithm);
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(ivs));
        byte[] cipherBytes = cipher.doFinal(Base64.getDecoder().decode(src));
        return cipherBytes;
    }

    /**
     * AES 加密
     * @param src
     * @return
     */
    public static String encryptAES(String src) {
        String encryptResult = "";
        try {
            byte[] aesBytes =  encrypt(src, AES_TRANSFORMATION, AES_ALGORITHM,
                    IV.getBytes(),
                    AES_KEY.getBytes());
            encryptResult = Base64.getEncoder().encodeToString(aesBytes);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encryptResult;
    }

    /**
     * AES解密
     * @param src
     * @return
     */
    public static String decryptAES(String src) {
        String decryptResult = "";
        try {
            byte[] aesBytes =  decrypt(src, AES_TRANSFORMATION, AES_ALGORITHM,
                    IV.getBytes(),
                    AES_KEY.getBytes());
            decryptResult = new String(aesBytes, "utf-8");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decryptResult;
    }

    public static void main(String[] args) {
        String password = "123456";
        String eResult = CryptoUtils.encryptAES(password);
        System.out.println("加密后：-----" + eResult);
        String dResult = CryptoUtils.decryptAES(eResult);
        System.out.println("解密后：-----" + dResult);
    }
}
