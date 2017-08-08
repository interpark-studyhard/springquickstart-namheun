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
		
		//1. 클라이언트의 요청 path정보를 추출한다.
		String url = request.getRequestURI();
		String path = url.substring(url.lastIndexOf("/"));
		
		System.out.println("------->"+path);
		
		if(path.equals("/login.do")){
			System.out.println("로그인 처리");
			
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
					response.sendRedirect("getBoardList.do");
				}else {
					response.sendRedirect("login.jsp");
				}
			
			
		}else if(path.equals("/logout.do")){
			System.out.println("로그아웃 처리");
			
			
			HttpSession session  =  request.getSession();
			session.invalidate();
			
			response.sendRedirect("login.jsp");
			
		}else if(path.equals("/insertBoard.do")){
			System.out.println("글 등록 처리 ");
			

			//사용자 입력 정보 추출
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
			
			//화면 네비게이션
			response.sendRedirect("getBoardList.do");
			
			
		}else if(path.equals("/updateBoard.do")){
			System.out.println("글 수정 처리 ");
			

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
			response.sendRedirect("getBoardList.do");

			
		}else if(path.equals("/deleteBoard.do")){
			System.out.println("글 삭제처리 ");
			
			//사용자 입력 정보 추출
		 	String seq = request.getParameter("seq");
		 	
		 	BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));

			BoardDAO boardDao = new BoardDAO();
			boardDao.deleteBoard(vo);
			
			//화면 네비게이션
			response.sendRedirect("getBoardList.do");

			
			
		}else if(path.equals("/getBoard.do")){
			System.out.println("글 상세 처리 ");
			
			
			//검색할 게시글 번호 추출
			String seq = request.getParameter("seq");

		  	//DB연동처리
		  	BoardVO vo = new BoardVO();
		  	vo.setSeq(Integer.parseInt(seq));
		  	
		  	BoardDAO dao = new BoardDAO();
		  	BoardVO board = dao.selectBoard(vo); //getBoard 아니야
		  	
		  	HttpSession session = request.getSession();
			session.setAttribute("board",board);
			response.sendRedirect("getBoard.jsp");
			
			
		}else if(path.equals("/getBoardList.do")){
			System.out.println("글 목록 검색");
			
		    BoardVO vo = new BoardVO();
		    BoardDAO boardDao = new BoardDAO();
		    List<BoardVO> boardList = boardDao.getBoardList(vo);
		    
		    
		    HttpSession session = request.getSession();
		    session.setAttribute("boardList",boardList);
		    response.sendRedirect("getBoardList.jsp");
			
		}
		
	}
	

}
