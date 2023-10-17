package com.learn.java.indepth.encryp;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 对称加密过程
 * AES (Advanced Encryption Standard) 数据加密标准
 * 注意点:
 *  密钥的长度至少为128bits 即16bytes
 *   具备因素:
 *      加密算法,
 *      秘钥,
 *      密钥对象 -> 加密器 -> 加密器初始化    -> 执行操作doFinal
 *
 */
public class AesTest {

    //密钥
    public static final byte[] SECRET_KEY = "djien67612345678".getBytes();

    // 算法名字
    public static final String ALGORITHM_NAME = "aes";
    public static void main(String[] args) throws Exception {
        byte[] en = AesTest.encrypt("hello you are the best".getBytes());
        System.out.println(new String(en));
        byte[] de = AesTest.decrypt(en);
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
        // 获取密钥对象
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY, ALGORITHM_NAME);
        // 将加密算法名对象传入加密器
        Cipher cipher = Cipher.getInstance(ALGORITHM_NAME);
        // 加密器根据加密方式, 秘钥, 进行一个初始化
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        // 执行最终操作
        return cipher.doFinal(data);
    }

}
