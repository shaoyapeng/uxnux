package com.uxnux.activiti.service.impl;

import com.uxnux.activiti.model.LeaveProcess;
import com.uxnux.activiti.model.WorkflowStart;
import com.uxnux.activiti.service.ProcessService;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

/**
 * @Author: 10785
 * @Date: 2019/12/20 9:47
 * @Version: 1.0
 */
@Transactional(rollbackOn = Exception.class)
@Service("ProcessService")
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private FormService formService;

    @Autowired
    private ProcessService processService;

    @Override
    public Model getModelById(String modelId) {
        Model model = repositoryService.getModel(modelId);
        return model;
    }

    @Override
    public List<Model> getModelList() {
        List<Model> modelList = repositoryService.createModelQuery().list();
        return modelList;
    }

    @Override
    public List<Deployment> getDeploymentList() {
        List<Deployment> deploymentList = repositoryService.createDeploymentQuery().desc().list();
        return deploymentList;
    }

    @Override
    public Deployment getDeployment(String id) {
        Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(id).singleResult();
        return deployment;
    }

    @Override
    public void zipDeploy(String name, File file) {
       try {
           ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file));
           Deployment deployment = repositoryService.createDeployment()
                   .name(name)
                   .addZipInputStream(zipInputStream)
                   .deploy();
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       }
    }

    @Override
    public Task startUpProcess(WorkflowStart workflowStart) {
        Task task = null;
        if (workflowStart != null) {
            ProcessInstance processInstance = runtimeService.startProcessInstanceById(workflowStart.getProcessesId(),
                    workflowStart.getProcessesKey(), workflowStart.getVariables());
            if (processInstance != null) {
                String processInstanceId = processInstance.getProcessInstanceId();
                task = taskService.createTaskQuery()
                        .processInstanceId(processInstanceId)
                        .singleResult();
            }
        }
        return task;
    }

    @Override
    public List<ProcessDefinition> getProcessDefinitionList() {
        List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery().list();
        return processDefinitionList;
    }

    @Override
    public InputStream getImgInputStreamByTaskId(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(task.getProcessInstanceId()).singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
        List<String> activeIds = runtimeService.getActiveActivityIds(processInstance.getId());
        ProcessDiagramGenerator processDiagramGenerator = new DefaultProcessDiagramGenerator();
        InputStream inputStream = processDiagramGenerator.generateDiagram(bpmnModel,
                "png", activeIds, new ArrayList<String>(), "宋体", "宋体", "宋体", null, 1.0);
        return inputStream;
    }

    @Override
    public Task submitTask(String taskId, String processInstanceId, String authenticatedUserId, Map variables) {
        Authentication.setAuthenticatedUserId(authenticatedUserId);
        taskService.addComment(taskId, processInstanceId, "");
        taskService.complete(taskId, variables);
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        Task resultTask = null;
        if (processInstance != null) {
            processInstanceId = processInstance.getProcessInstanceId();
            resultTask = taskService.createTaskQuery()
                    .processInstanceId(processInstanceId)
                    .singleResult();
        }
        return resultTask;
    }

    @Override
    public List<Task> getTaskListByAssignee(String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }

    @Override
    public Task getTaskById(String taskId) {
        return null;
    }
}
