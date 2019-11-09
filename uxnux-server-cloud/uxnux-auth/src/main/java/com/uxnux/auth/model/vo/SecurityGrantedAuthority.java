package com.uxnux.auth.model.vo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @Author: 10785
 * @Date: 2019/11/9 19:55
 * @Version: 1.0
 */
@Data
public class SecurityGrantedAuthority implements GrantedAuthority {

    private String name;

    @Override
    public String getAuthority() {
        return name;
    }

}
