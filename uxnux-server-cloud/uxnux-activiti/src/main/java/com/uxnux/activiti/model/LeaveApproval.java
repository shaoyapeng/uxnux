package com.uxnux.activiti.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import org.hibernate.annotations.GenericGenerator;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 审批
 * @Author: 10785
 * @Date: 2019/12/23 10:19
 * @Version: 1.0
 */
@Data
@Entity
@Table(name = "LEAVE_APPROVAL")
public class LeaveApproval implements Serializable {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	@Column(name = "LEAVE_APPROVAL_ID")
	private String leaveApprovalId;

	@Column(name = "APPROVAL_USER_ID")
	private String approvalUserId;

	@Column(name = "APPROVAL_RESULT")
	private Integer approvalResult;

	@Column(name = "APPROVAL_COMMENT")
	private String approvalComment;

	@Column(name = "APPROVAL_TIME")
	private String approvalTime;

	@Column(name = "TASK_ID")
	private String taskId;

	@Column(name = "PROCESS_INSTANCE_ID")
	private String processInstanceId;

}
