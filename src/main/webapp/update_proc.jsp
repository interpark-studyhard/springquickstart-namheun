<%@page import="com.springbook.biz.board.BoardVO"%>
<%@page import="com.springbook.biz.board.impl.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
    
    
<%

	//사용자 입력 정보 추출
	request.setCharacterEncoding("EUC-KR");
 	String title = request. getParameter("title");
 	String content = request.getParameter("content");
 	String seq = request.getParameter("seq");
 	
 	BoardVO vo = new BoardVO();
	vo.setTitle(title);
	vo.setContent(content);
	vo.setSeq(Integer.parseInt(seq));

	BoardDAO boardDao = new BoardDAO();
	boardDao.updateBoard(vo);
	
	//화면 네비게이션
	response.sendRedirect("getBoardList.jsp");


%>