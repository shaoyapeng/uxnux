package com.uxnux.activiti.service.impl;

import com.uxnux.activiti.service.ModelService;
import com.uxnux.activiti.service.ProcessService;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 10785
 * @Date: 2019/12/24 14:07
 * @Version: 1.0
 */
@Service(value = "modelService")
public class ModelServiceImpl implements ModelService {

    @Autowired
    private ProcessService processService;

    @Override
    public List<Model> getModelList() {
        return processService.getModelList();
    }

    @Override
    public List<ProcessDefinition> getProcessDefinitionList() {
        List<ProcessDefinition> processDefinitionList = processService.getProcessDefinitionList();
        return processDefinitionList;
    }
}
