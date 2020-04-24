package com.uxnux.activiti.service;

import com.uxnux.activiti.model.CustomModel;
import com.uxnux.activiti.model.ProcessImgFormat;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.task.Task;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @Author: 10785
 * @Date: 2019/12/20 17:23
 * @Version: 1.0
 */
public interface WorkflowService {

    /**
     * 启动类
     * @param processId 流程id 自动生成
     * @param processKey 流程key 创建模板配置的
     * @param variables 变量
     * @return 任务
     * @throws ActivitiException 自定义异常
     */
    Task start(String processId, String processKey, Map variables) throws ActivitiException;

    /**
     * 获得流程图IO
     * @param processDefinitionId 流程定义id
     * @param processInstanceId 流程实例id
     * @param processImgFormat 格式设置
     * @return 流程图IO流
     * @throws ActivitiException 自定义异常
     */
    InputStream getProcessImgIO(String processDefinitionId, String processInstanceId,
                                ProcessImgFormat processImgFormat) throws ActivitiException;

    /**
     * 获得流程图IO
     * @param processInstanceId 流程实例id
     * @param processImgFormat 格式设置
     * @return 流程图IO流
     * @throws ActivitiException 自定义异常
     */
    InputStream getProcessImgIO(String processInstanceId, ProcessImgFormat processImgFormat) throws ActivitiException;

    /**
     * 获得默认流程图
     * @param processId
     * @param processKey
     * @param processImgFormat
     * @return
     * @throws ActivitiException
     */
    InputStream getDefaultProcessImg(String processId, String processKey, ProcessImgFormat processImgFormat)
            throws ActivitiException;

    /**
     * 执行流程
     * @param taskId 任务id
     * @param processInstanceId 流程实例id
     * @param authenticatedUserId 流程发起人
     * @param message 批注
     * @param variables 变量 类似流程发生改变在变量中进行设置
     * @return
     * @throws ActivitiException
     */
    Task execute(String taskId, String processInstanceId, String authenticatedUserId, String message, Map variables)
            throws ActivitiException;

    /**
     * 执行流程
     * @param processInstanceId 流程实例id
     * @param authenticatedUserId 流程发起人
     * @param message 批注
     * @param variables 变量 类似流程发生改变在变量中进行设置
     * @return
     * @throws ActivitiException
     */
    Task execute(String processInstanceId, String authenticatedUserId, String message, Map variables)
            throws ActivitiException;

    Task end();

    /**
     * 上传文件
     * @param file
     * @param sourceFileName
     * @return
     * @throws ActivitiException
     */
    boolean uploadXml(File file, String sourceFileName) throws ActivitiException;

    /**
     * 保存模型
     * @param customModel
     * @return
     * @throws ActivitiException
     */
    CustomModel saveModel(CustomModel customModel) throws ActivitiException;

    /**
     * 保存模型文件到数据库
     * @param modelId
     * @param is
     * @return
     * @throws ActivitiException
     */
    boolean saveModelSource(InputStream is, String modelId) throws ActivitiException;

    /**
     * 部署
     * @param customModel
     * @return
     * @throws ActivitiException
     */
    CustomModel deploy(CustomModel customModel) throws ActivitiException;

    /**
     * 查询模板数据
     * @param customModel 查询条件
     * @return
     */
    List<CustomModel> getModelList(CustomModel customModel) throws ActivitiException;

    /**
     *
     * @param modelId
     * @param processImgFormat
     * @return
     * @throws ActivitiException
     */
    InputStream getModelImgById(String modelId, ProcessImgFormat processImgFormat) throws ActivitiException;
}
