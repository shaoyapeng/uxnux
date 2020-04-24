package com.uxnux.activiti.controller;

import com.uxnux.activiti.config.ActivitiResult;
import com.uxnux.activiti.model.CustomModel;
import com.uxnux.activiti.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author: 10785
 * @Date: 2019/12/30 9:21
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "/activity")
public class WorkflowController {

    @Autowired
    private WorkflowService workflowService;

    /**
     * 获得模板list
     * @param customModel
     * @return
     */
    @RequestMapping(value = "/getModelList")
    public ActivitiResult getModelList(CustomModel customModel) {
        List<CustomModel> customModelList = workflowService.getModelList(customModel);
        return ActivitiResult.OK(customModelList);
    }

    /**
     * 上传模板文件
     * @param request
     * @return
     */
    @RequestMapping(value = "/uploadXml", headers = "content-type=multipart/form-data")
    public ActivitiResult uploadXml(MultipartHttpServletRequest request,  CustomModel customModel) {
        try {
            CustomModel model = workflowService.saveModel(customModel);
            String modelId = request.getParameter("modelId");
            String uploadFileList = request.getParameter("uploadFileList");
            List<MultipartFile> fileList = request.getFiles("uploadFileList");
            for (MultipartFile file: fileList) {
                InputStream is = file.getInputStream();
                workflowService.saveModelSource(is, model.getId());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ActivitiResult.Error("上传错误");
        }
        return ActivitiResult.OK("");
    }

    /**
     * 保存model
     * @return
     */
    @RequestMapping(value = "/saveModel")
    public ActivitiResult saveModel(MultipartHttpServletRequest request, CustomModel customModel) {
        CustomModel model = workflowService.saveModel(customModel);
        return ActivitiResult.OK(model);
    }

    /**
     * 部署
     * @param customModel
     * @return
     */
    @RequestMapping(value = "/deploy")
    public ActivitiResult deploy(CustomModel customModel) {
        CustomModel model = workflowService.deploy(customModel);
        return ActivitiResult.OK(model);
    }


}
