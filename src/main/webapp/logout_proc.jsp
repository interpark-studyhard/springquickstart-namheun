<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

    
    <%
    	//�������� ����� ���ǰ�ü�� ��������
    	session.invalidate();
    
    	//���� ������, ����ȭ������ �̵�
    	response.sendRedirect("login.jsp");
    %>