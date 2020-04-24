package com.uxnux.activiti.config;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 10785
 * @Date: 2019/12/31 12:01
 * @Version: 1.0
 */
@Configuration
public class ProcessEngineConfiguration implements ProcessEngineConfigurationConfigurer {

    @Autowired
    private ActivitiProperties activitiProperties;

    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        springProcessEngineConfiguration.setDatabaseType(activitiProperties.getDatabaseType());
    }
}
