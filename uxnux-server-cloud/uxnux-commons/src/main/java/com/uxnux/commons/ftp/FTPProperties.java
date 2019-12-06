package com.uxnux.commons.ftp;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author: 10785
 * @Date: 2019/12/5 12:19
 * @Version: 1.0
 *
 * 使用ConfigurationProperties注解可以吧application的配置想进行自动注入
 * prefix 是配置项的前缀，ignoreUnknownFields 是忽略不匹配的字段
 * 字段名称必须和 配置项名称保持一直
 * 如果有 aaa.bbb.ccc,那么bbb可以是一个单独的新的类，它里面的属性是ccc，然后在类 aaa里面进行注入bbb对象
 * 如果 不是application.properties和application.yml的配置项，
 * 那么必须使用 @PropertySource 注解指明用那个配置文件
 */
@Data
@Component
@ConfigurationProperties(prefix = "ftp", ignoreUnknownFields = true)
public class FTPProperties {
    private String username;

    private String password;

    private String host;

    private Integer port;

    private String baseUrl;

    private String defaultPathNameFormat;
}
