package com.uxnux.activiti.controller;

import com.uxnux.activiti.config.ActivitiResult;
import com.uxnux.activiti.service.ModelService;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 10785
 * @Date: 2019/12/24 13:57
 * @Version: 1.0
 */
@RestController
public class ModelController {

    @Autowired
    private ModelService modelService;

    @RequestMapping(value = "/getModelList")
    public ActivitiResult getModelList() {
        List<Model> modelList = modelService.getModelList();
        return ActivitiResult.OK(modelList);
    }

    @RequestMapping(value = "/addModel")
    public ActivitiResult createModel() {
        return ActivitiResult.OK("");
    }

    @RequestMapping(value = "/getProcessDefinitionList")
    public ActivitiResult getProcessDefinitionList() {
        try {
            List<ProcessDefinition> processDefinitionList = modelService.getProcessDefinitionList();
            List<Map> mapList = new ArrayList<>();
            for (ProcessDefinition processDefinition: processDefinitionList) {
                Map map = new HashMap();
                map.put("id", processDefinition.getId());
                map.put("name", processDefinition.getName());
                map.put("key", processDefinition.getKey());
                map.put("version", processDefinition.getVersion());
                mapList.add(map);
            }
            return ActivitiResult.OK(mapList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
