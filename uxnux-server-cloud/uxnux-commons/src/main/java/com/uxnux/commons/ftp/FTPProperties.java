package com.uxnux.commons.ftp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: 10785
 * @Date: 2019/12/5 12:19
 * @Version: 1.0
 *
 * 使用ConfigurationProperties注解可以吧application的配置想进行自动注入
 * prefix 是配置项的前缀，ignoreUnknownFields 是忽略不匹配的字段
 * 字段名称必须和 配置项名称保持一直
 * 如果有 aaa.bbb.ccc,那么bbb可以是一个单独的新的类，它里面的属性是ccc，然后在类 aaa里面进行注入bbb对象
 * 如果 不是application.properties和application.yml的配置项，
 * 那么必须使用 @PropertySource 注解指明用那个配置文件
 */
@Component
@ConfigurationProperties(prefix = "ftp", ignoreUnknownFields = true)
public class FTPProperties {
    private String username;

    private String password;

    private String host;

    private Integer port;

    private String baseUrl;

    private String defaultPathNameFormat;

    public FTPProperties() {
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getHost() {
        return this.host;
    }

    public Integer getPort() {
        return this.port;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public String getDefaultPathNameFormat() {
        return this.defaultPathNameFormat;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setDefaultPathNameFormat(String defaultPathNameFormat) {
        this.defaultPathNameFormat = defaultPathNameFormat;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof FTPProperties)) return false;
        final FTPProperties other = (FTPProperties) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username))
            return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password))
            return false;
        final Object this$host = this.getHost();
        final Object other$host = other.getHost();
        if (this$host == null ? other$host != null : !this$host.equals(other$host))
            return false;
        final Object this$port = this.getPort();
        final Object other$port = other.getPort();
        if (this$port == null ? other$port != null : !this$port.equals(other$port))
            return false;
        final Object this$baseUrl = this.getBaseUrl();
        final Object other$baseUrl = other.getBaseUrl();
        if (this$baseUrl == null ? other$baseUrl != null : !this$baseUrl.equals(other$baseUrl))
            return false;
        final Object this$defaultPathNameFormat = this.getDefaultPathNameFormat();
        final Object other$defaultPathNameFormat = other.getDefaultPathNameFormat();
        if (this$defaultPathNameFormat == null ? other$defaultPathNameFormat != null : !this$defaultPathNameFormat.equals(other$defaultPathNameFormat))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof FTPProperties;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $username = this.getUsername();
        result = result * PRIME + ($username == null ? 43 : $username.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $host = this.getHost();
        result = result * PRIME + ($host == null ? 43 : $host.hashCode());
        final Object $port = this.getPort();
        result = result * PRIME + ($port == null ? 43 : $port.hashCode());
        final Object $baseUrl = this.getBaseUrl();
        result = result * PRIME + ($baseUrl == null ? 43 : $baseUrl.hashCode());
        final Object $defaultPathNameFormat = this.getDefaultPathNameFormat();
        result = result * PRIME + ($defaultPathNameFormat == null ? 43 : $defaultPathNameFormat.hashCode());
        return result;
    }

    public String toString() {
        return "FTPProperties(username=" + this.getUsername() + ", password=" + this.getPassword() + ", host=" + this.getHost() + ", port=" + this.getPort() + ", baseUrl=" + this.getBaseUrl() + ", defaultPathNameFormat=" + this.getDefaultPathNameFormat() + ")";
    }
}
