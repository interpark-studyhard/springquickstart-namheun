<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import = "com.springbook.biz.board.impl.BoardDAO" %>
<%@page import = "com.springbook.biz.board.BoardVO" %>
<%@page import = "java.util.List" %>

    <%
    
    //1. 사용자 입력 정보 추출(검색기능은 나중에 구현)
    //2. db연동 처리
    
    BoardVO vo = new BoardVO();
    BoardDAO boardDao = new BoardDAO();
    List<BoardVO> boardList = boardDao.getBoardList(vo);
    
    //3. 응답 화면 구성
    
    %>
    
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    
    <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>글 목록</title>
</head>
<body>
<center>
<h1>글 목록 </h1>
<h3> 테스트님 환영 합니다 <a href="logout_proc.jsp">Log-out</a></h3>

<form action = "getBoardList.jsp" method="post">
	<table border="1" cellpadding = "0" cellspacing="0" width="700">
		<tr>
			<td align = "right">
				<select name="searchCondition">
					<option value="title">제목</option>
					<option value="content">내용</option>
				</select>
				<input name="searchKeyword" type="text"/>
				<input name="submit" value="검색"/>
			</td>
		</tr>
	</table>
</form>


	<table border="1" cellpadding = "0" cellspacing="0" width="700">
	<tr>
		<th bgcolor = "orange" width = "100"> 번호</th>
		<th bgcolor = "orange" width = "200"> 제목</th>
		<th bgcolor = "orange" width = "150"> 작성자</th>
		<th bgcolor = "orange" width = "150"> 등록일</th>
		<th bgcolor = "orange" width = "100"> 조희수</th>
		
	<% for(BoardVO board : boardList ){ %>

	<tr>
		<td> <%=board.getSeq() %> </td>
		<td align = "left" > <a href="getBoard.jsp?seq=<%=board.getSeq() %>">
			<%=board.getTitle() %></a></td>
		<td><%=board.getWriter() %></td>
		<td><%=board.getRegDate() %></td>
		<td><%=board.getCnt() %></td>
	</tr>
	<%} %>
	</table>


<br>
	<a href="insertBoard.jsp">새글 등록</a>

</center>
</body>
</html>