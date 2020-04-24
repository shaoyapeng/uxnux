package com.uxnux.activiti.model;

import lombok.Data;

/**
 * @Author: 10785
 * @Date: 2019/12/26 20:21
 * @Version: 1.0
 */
@Data
public class TestModel {
    /**
     * 业务的id
     */
    private String businessId;

    /**
     * 不同流程选择的路径
     */
    private String path;

    /**
     * 任务id
     */
    private String taskId;

    /**
     * 流程实例id
     */
    private String processInstanceId;
}
