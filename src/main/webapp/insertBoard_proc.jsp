<%@page import="com.springbook.biz.board.BoardVO"%>
<%@page import="com.springbook.biz.board.impl.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
    
    
<%

	//����� �Է� ���� ����
	request.setCharacterEncoding("EUC-KR");
 	String title = request. getParameter("title");
 	String writer = request.getParameter("writer");
 	String content = request.getParameter("content");
 	
 	BoardVO vo = new BoardVO();
	vo.setTitle(title);
	vo.setWriter(writer);
	vo.setContent(content);

	BoardDAO boardDao = new BoardDAO();
	boardDao.insertBoard(vo);
	
	//ȭ�� �׺���̼�
	response.sendRedirect("getBoardList.jsp");


%>