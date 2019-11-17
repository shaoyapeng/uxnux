package com.uxnux.boot.utils;

import com.uxnux.boot.security.UxnuxGrantedAuthority;
import com.uxnux.boot.security.UxnuxUserDetails;
import io.jsonwebtoken.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 10785
 * @Date: 2019/11/15 8:42
 * @Version: 1.0
 */
@Slf4j
@Data
@Component
public class JwtUtils {

    @Value("${jwt.expiration}")
    private Long expiration;
    @Value("${jwt.secret}")
    private String secret;

    private static PrivateKey privateKey;

    private static PublicKey publicKey;

    /**
     * 加载私钥和公钥
     * keytool -genkey -alias uxnuxkey -keypass 011500 -keyalg RSA
     * -keysize 1024 -validity 365 -keystore uxnux.jks
     * -storepass 011500 -dname “CN=SHAO, OU=UX, O=UXNUX, L=LZ, ST=GS, C=CH”
     */
    static {
        try {
            InputStream inputStream = new ClassPathResource("uxnux.jks").getInputStream();
            log.info("---------- 获取私钥和公钥开始 ----------");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(inputStream, "011500".toCharArray());
            privateKey = (PrivateKey)keyStore.getKey("uxnuxkey", "011500".toCharArray());
            publicKey = keyStore.getCertificate("uxnuxkey").getPublicKey();
            log.info("---------- 获取私钥和公钥成功 ----------");
        } catch (Exception e) {
            log.error("---------- 获取私钥和公钥异常 ----------", e);
            e.printStackTrace();
        }
    }
    /**
     * 生成token 使用私钥加密，超时时长默认配置文件配置
     * @param userDetails
     * @return
     */
    public String generateJwt(UxnuxUserDetails userDetails) {
        log.info("---------- 生成token开始 ----------", userDetails.getUsername());
        return generateJwt(userDetails.getUsername(), null, SignatureAlgorithm.RS256, null, privateKey, null);
    }

    /**
     * 解析token，返回Claims 对象
     * @param token
     * @param key
     * @param secret
     * @return
     */
    public Claims parseToken(String token, Key key, String secret) {
        try {
            log.info("---------- 解析token开始 ----------");
            JwtParser jwtParser = Jwts.parser();
            jwtParser = key != null ? jwtParser.setSigningKey(key) : StringUtils.isBlack(secret) ?
                    jwtParser.setSigningKey(this.secret) : jwtParser.setSigningKey(secret);
            Claims claims = jwtParser.parseClaimsJws(token)
                    .getBody();
            log.info("---------- 解析token成功 ----------");
            return claims;
        } catch (Exception e) {
            log.info("---------- 解析token失败 ----------");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据token获取Subject
     * @param token
     * @return
     */
    public String getSubjectFromToken(String token) {
        Claims claims = parseToken(token, privateKey, null);
        return claims == null ? "" : claims.getSubject();
    }

    /**
     * 根据token获取Expiration
     * @param token
     * @return
     */
    public Date getExpiredDateFromToken(String token) {
        Claims claims = parseToken(token, privateKey, null);
        return claims == null ? null : claims.getExpiration();
    }
    /**
     * 返回token过期时间
     * @return
     */
    public Date getExpiration() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 判断token是否过期
     * @param token
     * @return
     */
    public boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     *
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getSubjectFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
    /**
     * 生成token
     * @param subject
     * @param claimsMap
     * @param sign
     * @param expiration
     * @param key
     * @param secret
     * @return
     */
    public String generateJwt(String subject, Map claimsMap, SignatureAlgorithm sign,
                              Long expiration, Key key, String secret) {
        log.info("---------- 生成token开始 ----------");
        try {
            JwtBuilder jwtBuilder = Jwts.builder();
            jwtBuilder = jwtBuilder.addClaims(claimsMap);
            this.expiration = expiration != null && expiration > 0L ? expiration : this.expiration;
            jwtBuilder.setExpiration(getExpiration());
            jwtBuilder.setSubject(subject);
            // key 不为空，使用密钥加密，为空，使用固定字符串加密
            jwtBuilder = key == null ? jwtBuilder.signWith(sign, key) : StringUtils.isBlack(secret)
                    ? jwtBuilder.signWith(sign, this.secret) : jwtBuilder.signWith(sign, secret);
            log.info("---------- 生成token成功 ----------");
            return jwtBuilder.compact();
        } catch (Exception e) {
            log.error("---------- 生成token失败返回空字符串 ----------", e);
            e.printStackTrace();
            return "";
        }
    }



}
