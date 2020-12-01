package com.javaAdvance.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author hitopei
 *
 * 切面，用来管理那些需要进行数据源切换
 */
@Aspect
@Component
public class DataSourceAspect {

    @Pointcut("execution(* com.javaAdvance.*.service..*.*(..))")
    public void aspect(){}

    @Before("acpect()")
    private void before(JoinPoint joinPoint){
        Object target = joinPoint.getTarget();
        String method = joinPoint.getSignature().getName();
        Class<?> clazz = target.getClass();
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature())
                .getMethod().getParameterTypes();

        try {
            Method method1 = clazz.getMethod(method, parameterTypes);
            if (method1.isAnnotationPresent(MyDataSource.class)){
                MyDataSource dataSource = method1.getAnnotation(MyDataSource.class);
                DataSourceContextHolder.putDataSource(dataSource.value().getName());
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
