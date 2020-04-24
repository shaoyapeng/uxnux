package com.uxnux.activiti.service;

import com.uxnux.activiti.model.LeaveProcess;
import com.uxnux.activiti.model.WorkflowStart;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @Author: 10785
 * @Date: 2019/12/20 9:46
 * @Version: 1.0
 */
public interface ProcessService {


    /**
     * 获得Model对象合集
     * @return Model对象
     */
    List<Model> getModelList();

    /**
     * 根据modelId获得Model对象
     * @param modelId id
     * @return Model对象
     */
    Model getModelById(String modelId);

    /**
     * 获得部署对象Deployment集合
     * @return Deployment集合
     */
    List<Deployment> getDeploymentList();

    /**
     * 根据id获得单个Deployment对象
     * @param id id
     * @return Deployment对象
     */
    Deployment getDeployment(String id);

    /**
     * 部署
     * @param name 部署的名称
     * @param file 部署的文件
     */
    void zipDeploy(String name, File file);

    /**
     * 启动流程
     * @param workflowStart
     * @return 流程任务对象
     */
    Task startUpProcess(WorkflowStart workflowStart);

    /**
     * 获得ProcessDefinition集合
     * @return
     */
    List<ProcessDefinition> getProcessDefinitionList();

    /**
     * 根据taskId获得图片
     * @param taskId
     * @return
     */
    InputStream getImgInputStreamByTaskId(String taskId);

    /**
     * 提交
     * @param taskId
     * @param processInstanceId
     * @param authenticatedUserId
     * @param variables
     * @return
     */
    Task submitTask(String taskId, String processInstanceId, String authenticatedUserId, Map variables);

    /**
     * 根据assignee获得task集合
     * @param assignee
     * @return
     */
    List<Task> getTaskListByAssignee(String assignee);

    /**
     * 根据id获得task
     * @param taskId
     * @return
     */
    Task getTaskById(String taskId);

}
