<%@ page language="java" contentType="text/html; charset=EUC-KR"   pageEncoding="EUC-KR"%>
<%@page import = "com.springbook.biz.user.impl.UserDAO" %>
<%@page import = "com.springbook.biz.user.UserVO" %>


<%
		//����� �Է� ���� ����
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		
		//DB���� ó��
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPassword(password);
		
		UserDAO userDao = new UserDAO();
		UserVO user = userDao.getUser(vo);
		
		//ȭ�� �׺���̼�
		if(user != null){
			response.sendRedirect("getBoardList.jsp");
		}else {
			response.sendRedirect("login.jsp");
		}
		
%>