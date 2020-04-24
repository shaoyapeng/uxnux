package com.uxnux.activiti.repository;

import com.uxnux.activiti.model.LeaveProcess;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: 10785
 * @Date: 2019/12/23 15:11
 * @Version: 1.0
 */
@Repository
public interface LeaveProcessRepository extends BaseRepository<LeaveProcess, String> {

    /**
     * 查询所有请假申请
     * @param sort 排序
     * @return 请假申请集合
     */
    @Override
    List<LeaveProcess> findAll(Sort sort);

    /**
     * 根据leaveUserId查询请假集合
     * @param leaveUserId 用户id
     * @return 请假申请集合
     */
    List<LeaveProcess> findByLeaveUserId(String leaveUserId);

    /**
     * 保存
     * @param leaveProcess 请假申请对象
     * @return 保存后的对象
     */
    @Override
    LeaveProcess save(LeaveProcess leaveProcess);

    /**
     * 根据taskId查询
     * @param taskId
     * @return
     */
    LeaveProcess findByTaskId(String taskId);

    /**
     * 根据id获得申请
     * @param leaveProcessId
     * @return
     */
    LeaveProcess findByLeaveProcessId(String leaveProcessId);
}
