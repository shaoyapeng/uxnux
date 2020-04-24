package com.uxnux.activiti.service;

import com.uxnux.activiti.model.LeaveApproval;
import com.uxnux.activiti.model.LeaveProcess;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.io.InputStream;
import java.util.List;

/**
 * @Author: 10785
 * @Date: 2019/12/23 10:52
 * @Version: 1.0
 */
public interface LeaveProcessService {

    /**
     * 根据用户获取请假申请
     * @param leaveUserId 用户id
     * @return
     */
    List<LeaveProcess> getLeaveProcessList(String leaveUserId);

    /**
     * 保存
     * @param leaveProcess
     * @return
     */
    LeaveProcess saveLeaveProcess(LeaveProcess leaveProcess);

    /**
     * 根据leaveId去启动请假流程
     * @param leaveProcess
     * @return
     */
    LeaveProcess startUpLeave(LeaveProcess leaveProcess);

    /**
     * 提交
     * @param leaveProcess
     * @param task
     * @return
     */
    LeaveProcess submitTask(LeaveProcess leaveProcess, Task task);


    /**
     * 根据taskId获得图片
     * @param taskId
     * @return
     */
    InputStream getImgInputStreamByTaskId(String taskId);

    /**
     * 获得审核列表
     * @param assignee
     * @return
     */
    List<LeaveProcess> getApprovalList(String assignee);
}
