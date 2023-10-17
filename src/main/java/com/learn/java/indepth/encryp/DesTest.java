package com.learn.java.indepth.encryp;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * 对称加密过程
 * DES (Data Encryption Standard) 数据加密标准
 * 注意点:
 *  密钥的长度至少为64bits 即8bytes
 *   具备因素:
 *      加密算法,
 *      秘钥,
 *      钥匙信息 -> 秘钥工厂 -> 秘钥对象
 *      加密器 -> 初始化加密器(根据模式和秘钥对象) -> 执行编码doFinal
 *
 */
public class DesTest {

    //密钥
    public static final byte[] SECRET_KEY = "djien676".getBytes();

    // 算法名字
    public static final String ALGORITHM_NAME = "des";
    public static void main(String[] args) throws Exception {
        byte[] en = DesTest.encrypt("hello you are the best".getBytes());
        System.out.println(new String(en));
        byte[] de = DesTest.decrypt(en);
        System.out.println(new String(de));


    }

    public static byte[] encrypt(byte[] data) throws Exception {
        // 获取密钥对象
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY, ALGORITHM_NAME);
        // 将加密算法名对象传入加密器
        Cipher cipher = Cipher.getInstance(ALGORITHM_NAME);
        // 加密器根据加密方式, 秘钥, 进行一个初始化
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        // 执行最终操作
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data) throws Exception{
        DESKeySpec desKeySpec = new DESKeySpec(SECRET_KEY);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM_NAME);
        SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
        Cipher cipher = Cipher.getInstance(ALGORITHM_NAME);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

}
