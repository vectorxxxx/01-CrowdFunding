package com.vectorx.crowdfunding.util;

public class ResultEntity<T>
{
    private static final String SUCCESS = "SUCCESS";
    private static final String FAILED = "FAILED";
    private String result;
    private String message;
    private T data;

    public ResultEntity() {
    }

    public ResultEntity(String result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public static<T> ResultEntity<T> success(){
        return new ResultEntity<>(SUCCESS, null, null);
    }

    public static <T> ResultEntity<T> success(T data){
        return new ResultEntity<>(SUCCESS, null, data);
    }

    public static <T> ResultEntity<T> failed(){
        return new ResultEntity<>(FAILED, null, null);
    }

    public static <T> ResultEntity<T> failed(String message){
        return new ResultEntity<>(FAILED, message, null);
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultEntity{" + "result='" + result + '\'' + ", message='" + message + '\'' + ", data=" + data + '}';
    }
}
