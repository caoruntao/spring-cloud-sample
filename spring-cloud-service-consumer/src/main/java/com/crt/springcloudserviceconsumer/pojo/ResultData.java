package com.crt.springcloudserviceconsumer.pojo;

/**
 * @author Reed
 * @date 2020/10/16 9:43 上午
 */
public class ResultData<T> {
    private static final int SUCCESS = 200;
    private int code;
    private T data;

    public ResultData() {
        this.code = SUCCESS;
    }

    public static <T> ResultData<T> success(T data){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS);
        resultData.setData(data);
        return resultData;
    }

    public static <T> ResultData<T> error(int code, T data){
        ResultData resultData = new ResultData();
        resultData.setCode(code);
        resultData.setData(data);
        return resultData;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
