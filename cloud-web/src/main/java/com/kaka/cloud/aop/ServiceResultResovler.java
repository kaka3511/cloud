package com.kaka.cloud.aop;

import com.kaka.cloud.common.ServiceRequestDto;
import com.kaka.cloud.common.ServiceResultDto;
import com.kaka.cloud.util.LogUtils;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/8/7 14:15
 */
@Aspect
@Component
public class ServiceResultResovler {

  @Pointcut("execution(* com.kaka.cloud.impl..*(..))")
  public void aspect() {
  }

  @Around("aspect()")
  public Object around(ProceedingJoinPoint pjp) throws Throwable {
    String methodName = pjp.getTarget().getClass().getSimpleName() + "." + pjp.getSignature().getName() + "()";
    Long startTime = System.currentTimeMillis();
    Class<?> type = resVoType(pjp.getTarget().getClass(), pjp.getSignature().getName());
    if (type.newInstance() instanceof String) {
      return type.newInstance();
    }

    logPjp(pjp);

    ServiceResultDto respDto = (ServiceResultDto) type.newInstance();

    try {
      respDto = (ServiceResultDto) pjp.proceed();
    } catch (Exception ex) {
      respDto.setResultCode("1");
      respDto.setErrorCode("cloud-系统错误");
      respDto.setErrorDesc("cloud-系统错误");
      LogUtils.logError("cloud error", ex);
    }
    return respDto;

  }

  /**
   * 返回值类型
   *
   * @param csp
   * @return
   */
  private static Class<?> resVoType(Class<?> csp, String methodName) {

    Method[] methods = csp.getDeclaredMethods();
    Class<?> returnType = null;
    for (Method method : methods) {
      if (method.getName().equals(methodName)) {
        returnType = method.getReturnType();
        return returnType;
      }
    }
    return returnType;
  }

  private void logPjp(ProceedingJoinPoint pjp) {
    Signature signature = pjp.getSignature();
    MethodSignature methodSignature = (MethodSignature)signature;
    Method targetMethod = methodSignature.getMethod();

    Object[] objects = pjp.getArgs();
    ServiceRequestDto requestDto = (ServiceRequestDto)objects[0];

    LogUtils.logAccess(  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+
            "【" + pjp.getTarget().getClass().getName() + ":"
            + targetMethod.getName() + ":" + requestDto.getValues() + "】");
  }
}
