package com.uxnux.activiti.model;

import lombok.Data;

import java.io.File;
import java.util.List;

/**
 * @Author: 10785
 * @Date: 2019/12/30 10:08
 * @Version: 1.0
 */
@Data
public class CustomModel {

    /**
     * id
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * key
     */
    private String key;
    /**
     * 类别
     */
    private String category;
    /**
     * 部署id
     */
    private String deploymentId;
    /**
     * 租户概念，对应多个系统共享同一个数据库的数据。
     */
    private String tenantId;
    /**
     * 描述
     */
    private String describe;
    /**
     * 上传资源文件名称
     */
    private String sourceName;
}
