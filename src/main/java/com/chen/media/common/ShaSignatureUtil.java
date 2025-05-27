package com.chen.media.common;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

/**
 * @className: ShaSignatureUtil
 * @Description: TODO
 * @author: 陈明亮
 * @date: 2025/5/27 14:34
 */
public class ShaSignatureUtil {
    public static String generateHmacSha256Signature(Map<String, String> params, String secret) throws Exception {
        // 使用TreeMap来确保参数按字典顺序排序
        TreeMap<String, String> sortedParams = new TreeMap<>(params);

        // 构建签名字符串
        StringBuilder signStrBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : sortedParams.entrySet()) {
            if (!signStrBuilder.isEmpty()) {
                signStrBuilder.append("&");
            }
            signStrBuilder.append(entry.getKey()).append("=").append(entry.getValue());
        }

        String signStr = signStrBuilder.toString();

        // 创建HMAC-SHA256实例
        Mac mac = Mac.getInstance("HmacSHA256");

        // 创建密钥
        SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

        // 初始化HMAC-SHA256实例
        mac.init(secretKeySpec);

        // 更新签名字符串
        mac.update(signStr.getBytes(StandardCharsets.UTF_8));

        // 计算签名
        byte[] signatureBytes = mac.doFinal();

        // 将字节数组转换为十六进制字符串
        StringBuilder hexSignature = new StringBuilder();
        for (byte b : signatureBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexSignature.append('0');
            }
            hexSignature.append(hex);
        }

        return hexSignature.toString();
    }
}