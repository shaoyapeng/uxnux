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
@Table(name = "sys_user")
public class SysUser implements Serializable {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	@Column(name = "PK_ID")
	private String pkId;

	@Column(name = "USER_NO")
	private String userNo;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "USER_SEX")
	private String userSex;

	@Column(name = "USER_AGE")
	private String userAge;

	@Column(name = "USER_PHONE")
	private String userPhone;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "NATION_CODE")
	private String nationCode;

	@Column(name = "CREATE_TIME")
	private String createTime;

	@Column(name = "UPDATE_TIME")
	private String updateTime;

}
