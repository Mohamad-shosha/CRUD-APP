package com.luv2code.springboot.thymeleafdemo.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {
    private Logger mylogger = Logger.getLogger(getClass().getName());
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.* (..) )")
    public void forControllerPackage (){};
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.* (..) )")
    public void forDaoPackage (){};

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.* (..) )")
    public void forServicePackage (){};

    @Pointcut("forControllerPackage() || forDaoPackage() || forServicePackage()")
    public void forAppFlow(){};

    @Before("forAppFlow()")
    public void before (JoinPoint joinPoint){
        String themethod = joinPoint.getSignature().toShortString();
        mylogger.info(" ======>> in @Before calling method : "+themethod);

        Object [] args = joinPoint.getArgs();
        for (Object object : args){
            mylogger.info(" ======>> The argument is : " +object);
        }
    }
    @AfterReturning(pointcut = "forAppFlow()", returning = "theresult")
    public void after(JoinPoint joinPoint , Object theresult){
        String themethod = joinPoint.getSignature().toShortString();
        mylogger.info(" ======>> in @AfterReturning calling method : "+themethod);
        mylogger.info(" The Result is : "+theresult);


    }

}
