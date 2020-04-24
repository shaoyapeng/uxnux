package com.uxnux.activiti.controller;

import com.uxnux.activiti.config.ActivitiResult;
import com.uxnux.activiti.model.LeaveApproval;
import com.uxnux.activiti.model.LeaveProcess;
import com.uxnux.activiti.service.LeaveApprovalService;
import com.uxnux.activiti.service.LeaveProcessService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @Author: 10785
 * @Date: 2019/12/23 10:53
 * @Version: 1.0
 */
@RestController
public class LeaveProcessController {

    @Autowired
    private LeaveProcessService leaveProcessService;

    @Autowired
    private LeaveApprovalService leaveApprovalService;

    @RequestMapping(value = "/startUpLeave")
    public ActivitiResult startUpLeave(LeaveProcess leaveProcess) {
        LeaveProcess process = leaveProcessService.startUpLeave(leaveProcess);
        return ActivitiResult.OK(process);
    }

    @RequestMapping(value = "/getApplyList")
    public ActivitiResult getApplyList(String leaveUserId) {
        List<LeaveProcess> leaveProcessList = leaveProcessService.getLeaveProcessList(leaveUserId);
        return ActivitiResult.OK(leaveProcessList);
    }

    @RequestMapping(value = "/delLeaveProcess")
    public ActivitiResult delLeaveProcess(String leaveProcessId) {
        return ActivitiResult.OK("");
    }

    @RequestMapping(value = "/getProcessImg")
    public void getProcessImg(HttpServletResponse response, String taskId) {
        try {
            InputStream inputStream = leaveProcessService.getImgInputStreamByTaskId(taskId);
            OutputStream outputStream = response.getOutputStream();
            for(int b=-1;(b=inputStream.read())!=-1;){
                outputStream.write(b);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getApprovalList")
    public ActivitiResult getApprovalList(String assignee) {
        List<LeaveProcess> leaveProcessList = leaveProcessService.getApprovalList(assignee);
        return ActivitiResult.OK(leaveProcessList);
    }

    @RequestMapping(value = "/submitApproval")
    public ActivitiResult submitApproval(LeaveApproval leaveApproval) {
        LeaveApproval approval = leaveApprovalService.submitApproval(leaveApproval);
        return ActivitiResult.OK(approval);
    }
}
