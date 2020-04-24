package com.uxnux.activiti.service;

import com.uxnux.activiti.model.LeaveApproval;

import java.util.List;

/**
 * @Author: 10785
 * @Date: 2019/12/25 20:30
 * @Version: 1.0
 */
public interface LeaveApprovalService {
    /**
     * 根据用户获取请假申请
     * @param approvalUserId 用户id
     * @return
     */
    List<LeaveApproval> getLeaveApprovalList(String approvalUserId);

    /**
     * 保存
     * @param leaveApproval
     * @return
     */
    LeaveApproval saveLeaveProcess(LeaveApproval leaveApproval);

    /**
     * 审批
     * @param leaveApproval
     * @return
     */
    LeaveApproval submitApproval(LeaveApproval leaveApproval);
}
