package com.uxnux.activiti.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.uxnux.activiti.model.CustomModel;
import com.uxnux.activiti.model.ProcessImgFormat;
import com.uxnux.activiti.service.ProcessService;
import com.uxnux.activiti.service.WorkflowService;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.converter.util.InputStreamProvider;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.util.io.InputStreamSource;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: 10785
 * @Date: 2019/12/20 17:23
 * @Version: 1.0
 */
@Transactional(rollbackOn = Throwable.class)
@Service(value = "workflowService")
public class WorkflowServiceImpl implements WorkflowService {

    /**
     * 定义model版本
     */
    private static final Integer MODEL_VERSION = 1;

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
    public Task start(String processId, String processKey, Map variables) throws ActivitiException {
        Task task = null;
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(
                processId, processKey, variables
        );
        if (processInstance != null) {
            String processInstanceId = processInstance.getProcessInstanceId();
            task = taskService.createTaskQuery()
                    .processInstanceId(processInstanceId)
                    .singleResult();
        }
        return task;
    }

    @Override
    public InputStream getProcessImgIO(String processDefinitionId, String processInstanceId, ProcessImgFormat processImgFormat)
            throws ActivitiException {
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        List<String> activeIds = runtimeService.getActiveActivityIds(processInstanceId);
        ProcessDiagramGenerator processDiagramGenerator = new DefaultProcessDiagramGenerator();
        InputStream inputStream = processDiagramGenerator.generateDiagram(bpmnModel,
                processImgFormat.getImgType(), activeIds,
                processImgFormat.getHighLightedFlows(),
                processImgFormat.getActivityFontName(),
                processImgFormat.getLabelFontName(),
                processImgFormat.getAnnotationFontName(),
                processImgFormat.getCustomClassLoader(),
                processImgFormat.getScaleFactor());
        return inputStream;
    }

    @Override
    public InputStream getProcessImgIO(String processInstanceId, ProcessImgFormat processImgFormat)
            throws ActivitiException {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        String processDefinitionId = processInstance.getProcessDefinitionId();
        InputStream inputStream = getProcessImgIO(processDefinitionId, processInstanceId, processImgFormat);
        return inputStream;
    }

    @Override
    public InputStream getDefaultProcessImg(String processId, String processKey, ProcessImgFormat processImgFormat) throws ActivitiException {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(processId)
                .singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinition.getId());
        ProcessDiagramGenerator processDiagramGenerator = new DefaultProcessDiagramGenerator();
        InputStream inputStream = processDiagramGenerator.generateDiagram(bpmnModel,
                processImgFormat.getImgType(), new ArrayList<>(),
                processImgFormat.getHighLightedFlows(),
                processImgFormat.getActivityFontName(),
                processImgFormat.getLabelFontName(),
                processImgFormat.getAnnotationFontName(),
                processImgFormat.getCustomClassLoader(),
                processImgFormat.getScaleFactor());
        return inputStream;
    }

    @Override
    public Task execute(String taskId, String processInstanceId, String authenticatedUserId, String message, Map variables) {
        Authentication.setAuthenticatedUserId(authenticatedUserId);
        taskService.addComment(taskId, processInstanceId, message);
        taskService.complete(taskId, variables);
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        Task task = null;
        if (processInstance != null) {
            processInstanceId = processInstance.getProcessInstanceId();
            task = taskService.createTaskQuery()
                    .processInstanceId(processInstanceId)
                    .singleResult();
        }
        return task;
    }

    @Override
    public Task execute(String processInstanceId, String authenticatedUserId, String message, Map variables)
        throws ActivitiException {
        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        return execute(task.getId(), processInstanceId, authenticatedUserId, message, variables);
    }

    @Override
    public Task end() throws ActivitiException {
        return null;
    }

    @Override
    public boolean uploadXml(File file, String sourceFileName) throws ActivitiException {

        return false;
    }

    @Override
    public CustomModel saveModel(CustomModel customModel) throws ActivitiException {
        Model model = repositoryService.newModel();
        model.setName(customModel.getName());
        model.setKey(customModel.getKey());
        model.setCategory(customModel.getCategory());
        model.setTenantId(customModel.getTenantId());
        model.setVersion(MODEL_VERSION);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode modelObjectNode = objectMapper.createObjectNode();
        modelObjectNode.put("name", customModel.getName());
        modelObjectNode.put("version", MODEL_VERSION);
        modelObjectNode.put("describe", customModel.getDescribe());
        model.setMetaInfo(modelObjectNode.toString());
        repositoryService.saveModel(model);
        customModel.setId(model.getId());
        return customModel;
    }

    @Override
    public boolean saveModelSource(InputStream is, String modelId) throws ActivitiException {
        if (is != null && modelId != null) {
            BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
            InputStreamProvider inputStreamProvider  = new InputStreamSource(is);
            BpmnModel bpmnModel = bpmnXMLConverter.convertToBpmnModel(inputStreamProvider,
                    true ,true, "UTF-8");
            byte[] bytes = bpmnXMLConverter.convertToXML(bpmnModel);
            repositoryService.addModelEditorSource(modelId, bytes);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public CustomModel deploy(CustomModel customModel) throws ActivitiException {
        byte[] bytes = repositoryService.getModelEditorSource(customModel.getId());
        Deployment deployment = repositoryService.createDeployment()
                .name(customModel.getName())
                .addBytes(customModel.getSourceName(), bytes)
                .deploy();
        if (deployment != null) {
            customModel.setDeploymentId(deployment.getId());
        }
        return customModel;
    }

    @Override
    public List<CustomModel> getModelList(CustomModel customModel) throws ActivitiException {
        ModelQuery modelQuery = repositoryService.createModelQuery();
        if (customModel.getName() != null) {
            modelQuery.modelNameLike(customModel.getName());
        }
        List<Model> modelList = modelQuery.list();
        return modelListToCustomModelList(modelList);
    }

    @Override
    public InputStream getModelImgById(String modelId, ProcessImgFormat processImgFormat) throws ActivitiException {
//        ProcessDiagramGenerator processDiagramGenerator = new DefaultProcessDiagramGenerator();
//        repositoryService.getBpmnModel();
//        InputStream inputStream = processDiagramGenerator.generateDiagram(bpmnModel,
//                processImgFormat.getImgType(), new ArrayList<>(),
//                processImgFormat.getHighLightedFlows(),
//                processImgFormat.getActivityFontName(),
//                processImgFormat.getLabelFontName(),
//                processImgFormat.getAnnotationFontName(),
//                processImgFormat.getCustomClassLoader(),
//                processImgFormat.getScaleFactor());
        return null;
    }

    private CustomModel modelToCustomModel(Model model) {
        CustomModel customModel = new CustomModel();
        customModel.setId(model.getId());
        customModel.setDeploymentId(model.getDeploymentId());
        customModel.setCategory(model.getCategory());
        customModel.setKey(model.getKey());
        customModel.setTenantId(model.getTenantId());
        return customModel;
    }

    private List<CustomModel> modelListToCustomModelList(List<Model> modelList) {
        List<CustomModel> customModelList = new ArrayList<>(modelList.size());
        for (Model m: modelList) {
            customModelList.add(modelToCustomModel(m));
        }
        return customModelList;
    }
}
