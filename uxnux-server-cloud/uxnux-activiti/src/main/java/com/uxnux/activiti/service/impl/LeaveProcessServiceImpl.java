package com.uxnux.activiti.service.impl;

import com.uxnux.activiti.model.LeaveProcess;
import com.uxnux.activiti.model.WorkflowStart;
import com.uxnux.activiti.repository.LeaveProcessRepository;
import com.uxnux.activiti.service.LeaveProcessService;
import com.uxnux.activiti.service.ProcessService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: 10785
 * @Date: 2019/12/23 10:52
 * @Version: 1.0
 */
@Transactional(rollbackOn = Exception.class)
@Service(value = "leaveProcessService")
public class LeaveProcessServiceImpl implements LeaveProcessService {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final String USER_ID = "1";

    private static final String PROCESSES_ID = "leave-01:1:6";

    private static final String PROCESSES_KEY = "leave-01";

    private static final String ASSIGNEE_LEAVE_PROJECT = "zbq";

    private static final String ASSIGNEE_LEAVE_DEPARTMENT = "qhc";

    @Autowired
    private LeaveProcessRepository leaveProcessRepository;

    @Autowired
    private ProcessService processService;

    @Override
    public LeaveProcess saveLeaveProcess(@RequestBody LeaveProcess leaveProcess) {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        leaveProcess.setLeaveUserId(USER_ID);
        leaveProcess.setCreateTime(dateFormat.format(new Date()));
        leaveProcess.setProcessState(0);
        return leaveProcessRepository.save(leaveProcess);
    }

    @Override
    public List<LeaveProcess> getLeaveProcessList(String leaveUserId) {
        return leaveProcessRepository.findByLeaveUserId(leaveUserId);
    }

    @Override
    public LeaveProcess startUpLeave(LeaveProcess leaveProcess) {
        LeaveProcess resultLeaveProcess = saveLeaveProcess(leaveProcess);
        Map<String, Object> variables = new HashMap<>(10);
        variables = objectToMap(resultLeaveProcess);
        variables.put("userId", USER_ID);
        WorkflowStart workflowStart = new WorkflowStart();
        workflowStart.setProcessesId(PROCESSES_ID);
        workflowStart.setProcessesKey(PROCESSES_KEY);
        workflowStart.setVariables(variables);
        Task task = processService.startUpProcess(workflowStart);
        resultLeaveProcess = submitTask(resultLeaveProcess, task);
        return resultLeaveProcess;
    }

    @Override
    public LeaveProcess submitTask(LeaveProcess leaveProcess, Task task) {
        Map<String, Object> variables = new HashMap<>(10);
        variables.put("userId", USER_ID);
        String authenticatedUserId = "";
        if (leaveProcess.getLeaveDays() >= 3) {
            variables.put("type", 2);
            authenticatedUserId = ASSIGNEE_LEAVE_PROJECT;
        } else {
            variables.put("type", 1);
            authenticatedUserId = ASSIGNEE_LEAVE_DEPARTMENT;
        }
        Task resultTask = processService.submitTask(task.getId(), task.getProcessInstanceId(), authenticatedUserId, variables);
        if (task != null) {
            leaveProcess.setTaskId(resultTask.getId());
            leaveProcess.setProcessInstanceId(resultTask.getProcessInstanceId());
            leaveProcess = saveLeaveProcess(leaveProcess);
        }
        return leaveProcess;
    }

    @Override
    public InputStream getImgInputStreamByTaskId(String taskId) {
        return processService.getImgInputStreamByTaskId(taskId);
    }

    @Override
    public List<LeaveProcess> getApprovalList(String assignee) {
        List<Task> taskList = processService.getTaskListByAssignee(assignee);
        List<LeaveProcess> processList = new ArrayList<>();
        for (Task task: taskList) {
            LeaveProcess leaveProcess = leaveProcessRepository.findByTaskId(task.getId());
            processList.add(leaveProcess);
        }
        return processList;
    }

    /**
     * objectToMap
     * @param o
     * @return
     */
    public Map<String, Object> objectToMap(Object o) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Class c = o.getClass();
            Field[] fields = c.getDeclaredFields();
            for (Field f: fields) {
                f.setAccessible(true);
                map.put(f.getName(), f.get(o) == null ? "" : f.get(o).toString());
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }
}
