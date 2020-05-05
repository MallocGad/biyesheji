package com.ht.university.common.result;

/**
 * @Author: ht
 * @Date: Create in 19:00 2020/1/14
 * @Describe:
 * @Last_change:
 */
public class ResultBody<T> {
    private int code=0;
    private String msg="成功";
    private T data=null;

    public static ResultBody success() {
        return new ResultBody();
    }

    public static ResultBody fail(){
        ResultBody resultBody = new ResultBody<>();
        resultBody.setMsg("失败");
        resultBody.setCode(1);
        return resultBody;
    }
    public static ResultBody fail(String msg){
        ResultBody fail = fail();
        fail.setMsg(msg);
        return fail;
    }
    public static ResultBody fail(String msg,int code){
        ResultBody resultBody = fail(msg);
        resultBody.setCode(code);
        return resultBody;
    }
    public  static <T> ResultBody success(T data){
        ResultBody<T> resultBody = new ResultBody<>();
        resultBody.setData(data);
        return resultBody;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultBody{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
