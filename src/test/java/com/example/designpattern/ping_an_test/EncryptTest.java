package com.example.designpattern.ping_an_test;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Security;

public class EncryptTest {

    private static final Logger log = LoggerFactory.getLogger(EncryptTest.class);

    static {
        Security.addProvider(new BouncyCastleProvider());
    }


    /**
     * 生成国密Key：SM4，密钥为 128bit， 16byte
     */
    public static SecretKeySpec getSm4Key(byte[] key) {
        if (key.length != 16) {
            log.warn("SM4's key should be 16bytes, 128bits");
        }
        return new SecretKeySpec(key, "SM4");
    }

    /**
     * 初始化向量
     *
     * @param len 长度
     * @return
     */
    public static IvParameterSpec getIv(int len) {
        byte[] zero = new byte[len];
        return new IvParameterSpec(zero);
    }

    /**
     * sm4加密
     *
     * @param hexKey   16进制密钥（忽略大小写）
     * @param paramStr 待加密字符串
     * @return 返回16进制的加密字符串
     * @throws Exception
     * @explain 加密模式：CBC
     * 密文长度不固定，会随着被加密字符串长度的变化而变化
     */
    public static String encryptCbc(String hexKey, String paramStr) {
        String cipherText = "";
        // 16进制字符串-->byte[]
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        // String-->byte[]
        byte[] srcData = paramStr.getBytes(StandardCharsets.UTF_8);
        // 加密后的数组
        byte[] cipherArray = encryptCbcPadding(keyData, srcData, "SM4/CBC/PKCS7Padding");
        // byte[]-->hexString
        cipherText = ByteUtils.toHexString(cipherArray);
        return cipherText;
    }

    /**
     * SM4 Cbc模式 加密
     * @param key 密钥
     * @param data 明文
     * @param algorithm 加密模式+填充模式
     * @return 密文
     */
    public static byte[] encryptCbcPadding(byte[] key, byte[] data, String algorithm){
        byte[] res = null;
        try {
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance(algorithm,"BC");
            SecretKeySpec secretKeySpec = getSm4Key(key);
            IvParameterSpec ivParameterSpec = getIv(cipher.getBlockSize());
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            res = cipher.doFinal(data);
            return res;
        } catch (Exception e) {
            log.error("Fail: Sm4 Cbc Encrypt {1}",e);
        }
        return res;
    }

    public static void main(String[] args) {
        String data = "[{\n" +
                "    \"deviceT\": 1617946230000,\n" +
                "    \"deviceName \": \"868626044622416\",\n" +
                "    \"deviceTypeCode \": \"65537\",\n" +
                "    \"useCode \": \"2\",\n" +
                "    \"positiveTotal \": \"2333.2\",\n" +
                "    \"positivePoint \": \"1633.24 \",\n" +
                "    \"positiveValley \": \"699.96\",\n" +
                "    \"ReverseAactivePower \": \"564.5\",\n" +
                "    \"electricity \": \"87.5\"\n" +
                "},\n" +
                "{\n" +
                "    \"deviceT\": 1617946230000,\n" +
                "    \"deviceName \": \"868626044622416\",\n" +
                "    \"deviceTypeCode \": \"65537\",\n" +
                "    \"useCode \": \"2\",\n" +
                "    \"positiveTotal \": \"2333.2\",\n" +
                "    \"positivePoint \": \"1633.24 \",\n" +
                "    \"positiveValley \": \"699.96\",\n" +
                "    \"ReverseAactivePower \": \"564.5\",\n" +
                "    \"electricity \": \"87.5\"\n" +
                "}]";
        String key = "2B3D4B5D3FA847FBCDF768D67BF973CA";
        String encryptCbcData = encryptCbc(key, data);
        System.out.println(encryptCbcData);

    }
}
