package com.ht.university.common.exception;

import com.ht.university.common.result.ResultBody;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: ht
 * @Date: Create in 14:15 2020/2/28
 * @Describe:全局异常处理
 * @Last_change:
 */
@ControllerAdvice
public class GlobalException {
    Logger log=Logger.getLogger(GlobalException.class);
    @ExceptionHandler(value = Exception.class)
    public ResultBody<Object> exceptionHandler(Exception e){
        log.error("发生异常:"+e.getMessage());
        return ResultBody.fail("全局异常提醒："+e.getMessage());
    }
}
