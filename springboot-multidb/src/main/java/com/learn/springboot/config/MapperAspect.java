package com.learn.springboot.config;

import com.learn.springboot.annotation.DataSource;
import com.learn.springboot.datasource.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author zhangyugu
 * @Date 2020/9/13 9:56 上午
 * @Version 1.0
 */
@Aspect
@Component
@Slf4j
public class MapperAspect {

    @Pointcut("execution(* com.learn.springboot.mapper.*Mapper.*(..))")
    public void pointCut(){
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        DataSource annotation = getDataSourceAnnotation(joinPoint);

        String dsId = annotation == null ? "master" : annotation.value();
        log.info("选择数据源:{}", dsId);
        DynamicDataSourceContextHolder.setDataSourceRouterKey(dsId);
    }

    private DataSource getDataSourceAnnotation(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        DataSource annotation = method.getAnnotation(DataSource.class);
        if(annotation!=null) {
            return annotation;
        }

        Class<?>[] interfaces = joinPoint.getTarget().getClass().getInterfaces();
        for(Class<?> anInterface: interfaces) {
            annotation = anInterface.getAnnotation(DataSource.class);
            if(annotation!=null) {
                return annotation;
            }
        }
        return null;
    }

    @After("pointCut()")
    public void after() {
        DynamicDataSourceContextHolder.removeDataSourceRouterKey();
    }
}
