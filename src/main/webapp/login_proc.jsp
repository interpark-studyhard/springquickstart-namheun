<%@ page language="java" contentType="text/html; charset=EUC-KR"   pageEncoding="EUC-KR"%>
<%@page import = "com.springbook.biz.user.impl.UserDAO" %>
<%@page import = "com.springbook.biz.user.UserVO" %>


<%
		//사용자 입력 정보 추출
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		
		//DB연동 처리
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPassword(password);
		
		UserDAO userDao = new UserDAO();
		UserVO user = userDao.getUser(vo);
		
		//화면 네비게이션
		if(user != null){
			response.sendRedirect("getBoardList.jsp");
		}else {
			response.sendRedirect("login.jsp");
		}
		
%>