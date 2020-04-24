package com.uxnux.activiti.controller;

import com.uxnux.activiti.config.ActivitiResult;
import com.uxnux.activiti.model.ProcessImgFormat;
import com.uxnux.activiti.model.TestModel;
import com.uxnux.activiti.service.WorkflowService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author: 10785
 * @Date: 2019/12/26 17:02
 * @Version: 1.0
 */
@RestController
public class TestProcessController {

    @Autowired
    private WorkflowService workflowService;

    private static final String PROCESS_ID = "TEST-PROCESSS:1:7";

    private static final String PROCESS_KEY = "TEST-PROCESSS";

    private static final String BUSINESS_ID = "0";

    private static final String USER_ID = "1";

    private static final String MESSAGE = "测试";

    @GetMapping(value = "/testStart")
    public ActivitiResult start(TestModel testModel) {
        testModel.setBusinessId(BUSINESS_ID);
        Task task = workflowService.start(PROCESS_ID, PROCESS_KEY, new HashMap());
        if (task == null) {
            return ActivitiResult.OK(testModel, "启动失败");
        }
        testModel.setTaskId(task.getId());
        testModel.setProcessInstanceId(task.getProcessInstanceId());
        return ActivitiResult.OK(testModel, "启动成功");
    }

    @GetMapping(value = "/")
    public void getProcessImg(TestModel testModel, HttpServletResponse response) {
        ProcessImgFormat format = new ProcessImgFormat();
        try {
            InputStream inputStream = workflowService.getProcessImgIO(testModel.getProcessInstanceId(), format);
            OutputStream outputStream = response.getOutputStream();
            for(int b=-1;(b=inputStream.read())!=-1;){
                outputStream.write(b);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/execute")
    public ActivitiResult execute(TestModel testModel) {
        Map map = new HashMap();
        map.put("PATH", testModel.getPath());
        Task task = workflowService.execute(testModel.getTaskId(), testModel.getProcessInstanceId(), USER_ID,
                MESSAGE, map);
        if (task == null) {
            return ActivitiResult.OK(testModel, "执行失败");
        }
        testModel.setTaskId(task.getId());
        testModel.setProcessInstanceId(task.getProcessInstanceId());
        return ActivitiResult.OK(testModel, "执行成功");
    }

    @RequestMapping(value = "/getDefaultProcessImg")
    public void getDefaultProcessImg(HttpServletResponse response, String processId, String processKey) {
        ProcessImgFormat format = new ProcessImgFormat();
        format.setImgType("png");
        format.setActivityFontName("宋体");
        format.setAnnotationFontName("宋体");
        format.setLabelFontName("宋体");
        format.setScaleFactor(1.0);
        format.setHighLightedFlows(new ArrayList<String>());
        try {
            InputStream inputStream = workflowService.getDefaultProcessImg(processId, processKey, format);
            OutputStream outputStream = response.getOutputStream();
            for(int b=-1;(b=inputStream.read())!=-1;){
                outputStream.write(b);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
