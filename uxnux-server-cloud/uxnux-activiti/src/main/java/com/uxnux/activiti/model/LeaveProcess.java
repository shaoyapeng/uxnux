package com.uxnux.activiti.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 请假流程
 * @Author: 10785
 * @Date: 2019/12/23 10:19
 * @Version: 1.0
 */
@Entity
@Table(name = "LEAVE_PROCESS")
@Data
public class LeaveProcess {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
    private String leaveProcessId;
    /**
     * 请假用户id
     */
    private String leaveUserId;
    /**
     * 请假天数
     */
    private Integer leaveDays;
    /**
     * 请假开始时间
     */
    private String leaveBeginDate;
    /**
     * 结束事件
     */
    private String leaveEndDate;
    /**
     * 请假类型
     */
    private Integer leaveType;
    /**
     * 请假原因
     */
    private String leaveReason;
    /**
     * 审核状态
     */
    private Integer processState;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * taskId
     */
    private String taskId;

    /**
     * processInstanceId
     */
    private String processInstanceId;
}
