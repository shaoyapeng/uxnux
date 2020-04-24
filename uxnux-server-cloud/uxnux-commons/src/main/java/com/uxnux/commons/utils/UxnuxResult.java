package com.uxnux.commons.utils;

/**
 * @Author: 10785
 * @Date: 2019/12/9 12:58
 * @Version: 1.0
 */
public class UxnuxResult {

    private Integer code;

    private String msg;

    private Object data;

    public UxnuxResult() {
    }


    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public Object getData() {
        return this.data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UxnuxResult)) return false;
        final UxnuxResult other = (UxnuxResult) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$code = this.getCode();
        final Object other$code = other.getCode();
        if (this$code == null ? other$code != null : !this$code.equals(other$code))
            return false;
        final Object this$msg = this.getMsg();
        final Object other$msg = other.getMsg();
        if (this$msg == null ? other$msg != null : !this$msg.equals(other$msg))
            return false;
        final Object this$data = this.getData();
        final Object other$data = other.getData();
        if (this$data == null ? other$data != null : !this$data.equals(other$data))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UxnuxResult;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $code = this.getCode();
        result = result * PRIME + ($code == null ? 43 : $code.hashCode());
        final Object $msg = this.getMsg();
        result = result * PRIME + ($msg == null ? 43 : $msg.hashCode());
        final Object $data = this.getData();
        result = result * PRIME + ($data == null ? 43 : $data.hashCode());
        return result;
    }

    public String toString() {
        return "UxnuxResult(code=" + this.getCode() + ", msg=" + this.getMsg() + ", data=" + this.getData() + ")";
    }
}
