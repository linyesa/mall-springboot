package com.example.mall.exceptionhandler;


import com.example.mall.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody //为了返回数据
    public R error(Exception e) {
        e.printStackTrace();
        return R.error(20002,"执行了全局异常处理..");
    }

    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody //为了返回数据
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error(20003,"执行了ArithmeticException异常处理..");
    }

    //自定义异常
    @ExceptionHandler(LysException.class)
    @ResponseBody //为了返回数据
    public R error(LysException e) {
        log.error(e.getMessage());
        e.printStackTrace();

        return R.error((e.getCode()),e.getMsg());
    }
}
