package com.uxnux.boot.model.entity.sys;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import org.hibernate.annotations.GenericGenerator;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: 10785
 * @Date: 2019/11/26 12:41
 * @Version: 1.0
 */
@Component
@Data
@Entity
@NoArgsConstructor
@Table(name = "sys_user_role_rel")
public class SysUserRoleRel implements Serializable {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	@Column(name = "PK_ID")
	private String pkId;

	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "ROLE_ID")
	private String roleId;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "CREATE_TIME")
	private String createTime;

	@Column(name = "UPDATE_TIME")
	private String updateTime;

}
