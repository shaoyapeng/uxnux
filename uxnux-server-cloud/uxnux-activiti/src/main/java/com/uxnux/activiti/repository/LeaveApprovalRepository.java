package com.uxnux.activiti.repository;

import com.uxnux.activiti.model.LeaveApproval;
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
public interface LeaveApprovalRepository extends BaseRepository<LeaveApproval, String> {

    /**
     * 查询所有请假申请
     * @param sort 排序
     * @return 请假申请集合
     */
    @Override
    List<LeaveApproval> findAll(Sort sort);

    /**
     * 根据leaveUserId查询请假集合
     * @param approvalUserId 用户id
     * @return 请假申请集合
     */
    List<LeaveApproval> findByApprovalUserId(String approvalUserId);

    /**
     * 保存
     * @param leaveApproval 请假申请对象
     * @return 保存后的对象
     */
    @Override
    LeaveApproval save(LeaveApproval leaveApproval);

    /**
     * 根据taskId查询
     * @param taskId
     * @return
     */
    LeaveApproval findByTaskId(String taskId);
}
