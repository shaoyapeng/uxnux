package com.uxnux.activiti;

import com.uxnux.activiti.service.ProcessService;
import org.activiti.engine.repository.Model;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UxnuxActivitiApplicationTests {

    @Autowired
    private ProcessService processService;

    @Test
    void contextLoads() {
    }

    @Test
    void getModelListTest() {
        List<Model> modelList = processService.getModelList();
        System.out.println(modelList);
    }

}
