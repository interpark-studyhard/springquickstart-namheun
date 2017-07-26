package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterThrowingAdvice {

	@Pointcut("execution(* com.springbook.biz..*Impl.get*(..))")
	public void allPointCut(){}
	
	@AfterThrowing(pointcut="allPointCut()", throwing="exceptObj")
	public void afterLog(JoinPoint jp, Exception exceptObj){
		String method = jp.getSignature().getName();
		
		System.out.println(method +" 수행중 예외 발생");
		
		if(exceptObj instanceof IllegalArgumentException) {
			System.out.println("부적합한값이 입력되었ㅅ브니다. ");
		} else if(exceptObj instanceof NumberFormatException) {
			System.out.println("숫자형식의 값이 아닙니다. ");
		} else if(exceptObj instanceof Exception){
			System.out.println("문제가 발생했습니다 ");
		}
		
	}
	
}
