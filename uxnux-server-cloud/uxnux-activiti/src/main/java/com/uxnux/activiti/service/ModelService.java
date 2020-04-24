package com.uxnux.activiti.service;

import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;

import java.util.List;

/**
 * @Author: 10785
 * @Date: 2019/12/24 14:04
 * @Version: 1.0
 */
public interface ModelService {

    /**
     * 获得模型list
     * @return
     */
    List<Model> getModelList();

    /**
     * 获得ProcessDefinition集合
     * @return
     */
    List<ProcessDefinition> getProcessDefinitionList();
}
