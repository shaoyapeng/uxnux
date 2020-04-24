package com.uxnux.activiti.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: 10785
 * @Date: 2019/12/31 12:03
 * @Version: 1.0
 */
@Data
@Component
@ConfigurationProperties("activiti")
public class ActivitiProperties {

    private String databaseType;
}
