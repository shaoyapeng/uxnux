package com.uxnux.auth.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: 10785
 * @Date: 2019/12/18 11:24
 * @Version: 1.0
 */
@Data
@ConfigurationProperties(prefix = "crypto", ignoreUnknownFields = true)
public class CryptoProperties {

    public static String aesKey;


    public static String iv;


    public static String aesTransformation;


    public static String aesAlgorithm;

    @Value("${crypto.AES_KEY}")
    public static void setAesKey(String aesKey) {
        CryptoProperties.aesKey = aesKey;
    }

    @Value("${crypto.IV}")
    public static void setIv(String iv) {
        CryptoProperties.iv = iv;
    }

    @Value("${crypto.AES_TRANSFORMATION}")
    public static void setAesTransformation(String aesTransformation) {
        CryptoProperties.aesTransformation = aesTransformation;
    }

    @Value("${crypto.AES_ALGORITHM}")
    public static void setAesAlgorithm(String aesAlgorithm) {
        CryptoProperties.aesAlgorithm = aesAlgorithm;
    }
}
