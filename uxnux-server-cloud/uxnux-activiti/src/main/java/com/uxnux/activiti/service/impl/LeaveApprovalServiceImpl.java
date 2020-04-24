package com.uxnux.activiti.service.impl;

import com.uxnux.activiti.model.LeaveApproval;
import com.uxnux.activiti.model.LeaveProcess;
import com.uxnux.activiti.repository.LeaveApprovalRepository;
import com.uxnux.activiti.repository.LeaveProcessRepository;
import com.uxnux.activiti.service.LeaveApprovalService;
import com.uxnux.activiti.service.LeaveProcessService;
import com.uxnux.activiti.service.ProcessService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 10785
 * @Date: 2019/12/25 20:32
 * @Version: 1.0
 */
@Transactional
@Service(value = "leaveApprovalService")
public class LeaveApprovalServiceImpl implements LeaveApprovalService {

    private static final String USER_ID = "0";

    @Autowired
    private LeaveApprovalRepository leaveApprovalRepository;

    @Autowired
    private LeaveProcessRepository leaveProcessRepository;

    @Autowired
    private ProcessService processService;

    @Override
    public List<LeaveApproval> getLeaveApprovalList(String approvalUserId) {
        return leaveApprovalRepository.findByApprovalUserId(approvalUserId);
    }

    @Override
    public LeaveApproval saveLeaveProcess(LeaveApproval leaveApproval) {
        return leaveApprovalRepository.save(leaveApproval);
    }

    @Override
    public LeaveApproval submitApproval(LeaveApproval leaveApproval) {
        LeaveApproval resultLeaveApproval = new LeaveApproval();
        leaveApproval.setApprovalUserId(USER_ID);
        resultLeaveApproval = leaveApprovalRepository.save(leaveApproval);
        LeaveProcess leaveProcess = leaveProcessRepository.findByTaskId(leaveApproval.getTaskId());
        Map<String, Object> variables = new HashMap<>(10);
        variables.put("userId", USER_ID);
        String authenticatedUserId = USER_ID;
        Task resultTask = processService.submitTask(leaveApproval.getTaskId(),
                leaveApproval.getProcessInstanceId(), authenticatedUserId, variables);
        leaveProcess.setTaskId(resultTask.getId());
        leaveProcess.setProcessInstanceId(resultTask.getProcessInstanceId());
        resultLeaveApproval.setTaskId(resultTask.getId());
        resultLeaveApproval.setProcessInstanceId(resultTask.getProcessInstanceId());
        resultLeaveApproval = leaveApprovalRepository.save(resultLeaveApproval);
        return resultLeaveApproval;
    }
}
