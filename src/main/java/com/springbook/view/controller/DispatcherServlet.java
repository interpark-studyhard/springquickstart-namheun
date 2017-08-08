package com.springbook.view.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;
import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

/**
 * Servlet implementation class DispatcherServlet
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	process(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("EUC-KR");
		process(request,response);
	}
	
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		//1. Ŭ���̾�Ʈ�� ��û path������ �����Ѵ�.
		String url = request.getRequestURI();
		String path = url.substring(url.lastIndexOf("/"));
		
		System.out.println("------->"+path);
		
		if(path.equals("/login.do")){
			System.out.println("�α��� ó��");
			
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
					response.sendRedirect("getBoardList.do");
				}else {
					response.sendRedirect("login.jsp");
				}
			
			
		}else if(path.equals("/logout.do")){
			System.out.println("�α׾ƿ� ó��");
			
			
			HttpSession session  =  request.getSession();
			session.invalidate();
			
			response.sendRedirect("login.jsp");
			
		}else if(path.equals("/insertBoard.do")){
			System.out.println("�� ��� ó�� ");
			

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
			response.sendRedirect("getBoardList.do");
			
			
		}else if(path.equals("/updateBoard.do")){
			System.out.println("�� ���� ó�� ");
			

			//����� �Է� ���� ����
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
			
			//ȭ�� �׺���̼�
			response.sendRedirect("getBoardList.do");

			
		}else if(path.equals("/deleteBoard.do")){
			System.out.println("�� ����ó�� ");
			
			//����� �Է� ���� ����
		 	String seq = request.getParameter("seq");
		 	
		 	BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));

			BoardDAO boardDao = new BoardDAO();
			boardDao.deleteBoard(vo);
			
			//ȭ�� �׺���̼�
			response.sendRedirect("getBoardList.do");

			
			
		}else if(path.equals("/getBoard.do")){
			System.out.println("�� �� ó�� ");
			
			
			//�˻��� �Խñ� ��ȣ ����
			String seq = request.getParameter("seq");

		  	//DB����ó��
		  	BoardVO vo = new BoardVO();
		  	vo.setSeq(Integer.parseInt(seq));
		  	
		  	BoardDAO dao = new BoardDAO();
		  	BoardVO board = dao.selectBoard(vo); //getBoard �ƴϾ�
		  	
		  	HttpSession session = request.getSession();
			session.setAttribute("board",board);
			response.sendRedirect("getBoard.jsp");
			
			
		}else if(path.equals("/getBoardList.do")){
			System.out.println("�� ��� �˻�");
			
		    BoardVO vo = new BoardVO();
		    BoardDAO boardDao = new BoardDAO();
		    List<BoardVO> boardList = boardDao.getBoardList(vo);
		    
		    
		    HttpSession session = request.getSession();
		    session.setAttribute("boardList",boardList);
		    response.sendRedirect("getBoardList.jsp");
			
		}
		
	}
	

}
