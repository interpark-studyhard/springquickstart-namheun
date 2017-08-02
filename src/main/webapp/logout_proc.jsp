<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

    
    <%
    	//브라우저와 연결된 세션객체를 강제종료
    	session.invalidate();
    
    	//세션 종료후, 메인화면으로 이동
    	response.sendRedirect("login.jsp");
    %>