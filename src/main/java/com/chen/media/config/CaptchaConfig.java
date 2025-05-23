package com.chen.media.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @className: CaptchaConfig
 * @Description: TODO
 * @author: 陈明亮
 * @date: 2025/5/23 10:31
 */

@Configuration
public class CaptchaConfig {
    @Bean
    public DefaultKaptcha getDefaultKaptcha() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        // 是否有边框 默认为true 我们可以自己设置yes，no
        properties.setProperty("kaptcha.border", "yes");
        // 边框颜色 默认为Color.BLACK
        properties.setProperty("kaptcha.border.color", "105,179,90");
        // 验证码文本字符颜色 默认为Color.BLACK
//        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        // 验证码图片宽度 默认为200
        properties.setProperty("kaptcha.image.width", "150");
        // 验证码图片高度 默认为50
        properties.setProperty("kaptcha.image.height", "50");
        // 验证码文本字符大小 默认为40
        properties.setProperty("kaptcha.textproducer.font.size", "40");
        // KAPTCHA_SESSION_KEY
        properties.setProperty("kaptcha.session.key", "kaptchaCodeMath");
        properties.setProperty("kaptcha.textproducer.case.sensitive", "false");
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789abcdefghjkmnpqrstuvwxyABCDEFGHJKLMNPQRSTUVWXYZ");
        // 验证码文本生成器
//        properties.setProperty("kaptcha.textproducer.impl", "com.yolo.springboot.kaptcha.config.KaptchaTextCreator");
        // 验证码文本字符间距 默认为2
        properties.setProperty("kaptcha.textproducer.char.space", "10");
        // 验证码文本字符长度 默认为5
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        // 验证码文本字体样式 默认为new Font("Arial", 1, fontSize), new Font("Courier", 1,
        // fontSize)
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,Arial,Courier,cmr10,楷体,微软雅黑");
        // 验证码噪点颜色 默认为Color.BLACK
        properties.setProperty("kaptcha.noise.color", "white");
        // 干扰实现类
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
        // 图片样式 水纹com.google.code.kaptcha.impl.WaterRipple
        // 鱼眼com.google.code.kaptcha.impl.FishEyeGimpy
        // 阴影com.google.code.kaptcha.impl.ShadowGimpy
        properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}