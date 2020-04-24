package com.uxnux.activiti.model;

import lombok.Data;

import java.util.Map;

/**
 * @Author: 10785
 * @Date: 2019/12/23 10:04
 * @Version: 1.0
 */
@Data
public class WorkflowStart {

    /**
     * id
     */
    private String processesId;

    /**
     * 启动流程id
     */
    private String processesKey;

    /**
     * 参数
     */
    private Map<String, Object> variables;

    /**
     *
     */
    private String assignee;
}
