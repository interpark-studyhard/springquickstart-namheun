package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import com.springbook.biz.user.UserVO;



@Service
@Aspect
public class AfterReturningAdvice {
	
	/*@Pointcut("execution(* com.springbook.biz..*Impl.get*(..))")
	public void getAnnotationPointcut(){}
	
	@AfterReturning(pointcut="getAnnotationPointcut()", returning="returnObj")
*/

	@AfterReturning(pointcut = "PointcutCommon.getPointcut()", returning="returnObj")
	public Object afterLog(JoinPoint jp, Object returnObj){
		String method = jp.getSignature().getName();
	
		if(returnObj instanceof UserVO){
			UserVO user = (UserVO) returnObj;
			if(user.getRole().equals("Admin")){
				System.out.println(user.getName()+" 로그인 (Admin)");
			}
		}
		System.out.println("[ 사후처리gg ] "+method+"() 메소드 리턴값 : "+returnObj.toString());
		return returnObj;

	}
}
