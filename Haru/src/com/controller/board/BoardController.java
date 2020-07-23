package com.controller.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.board.dao.BoardDao;
import com.board.dto.BoardDto;


@WebServlet("/board.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public BoardController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		
		BoardDao dao = new BoardDao();
		
		String command = request.getParameter("command");
		if(command==null) {command="list";}
		System.out.println("[command:"+command+"]");
		if(command.equals("list")) {
			int pageNum=1; //기본 pageNum은 1
			
			if(request.getParameter("pageNum") != null) {
				pageNum = Integer.parseInt(request.getParameter("pageNum"));	
			}
			
			int pageSize=8;
			
			int totalArticle = dao.getTotalArticle();
			int totalPage = totalArticle/pageSize +(totalArticle % pageSize == 0?0:1); // 
			
			if(pageNum <=0 || pageNum > totalPage) {
				pageNum=1;
			}
			int startRow = (pageNum -1) * pageSize +1;
			int endRow = pageNum * pageSize;
			
			if(endRow>totalArticle) endRow = totalArticle;
		
			List<BoardDto> articleList = dao.getArticleList(startRow, endRow);
			int number = totalArticle -(pageNum-1)*pageSize;
					
			int blockSize=5;
			int startPage =(pageNum/blockSize -(pageNum%blockSize != 0?0:1))*blockSize+1;
			
			int endPage =startPage+blockSize-1;
			if(endPage>totalPage) {
				endPage = totalPage;
			}
			
			request.setAttribute("articleList", articleList);
			request.setAttribute("number", number);
			request.setAttribute("startPage",startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("blockSize",blockSize);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("totalPage", totalPage);
			
			
			List<BoardDto> list = dao.selectAll();
			
			request.setAttribute("list",list);
			Dispatch("board.jsp",request,response);
			
		}else if(command.equals("writeform")) {
			Dispatch("board_detail.jsp",request,response);
		}
		else if(command.equals("write")) {
			String title= request.getParameter("title");
			String content=request.getParameter("content");
			String id = request.getParameter("id");
			
			BoardDto dto = new BoardDto();
			dto.setTitle(title);
			dto.setContent(content);
			dto.setId(id);
			
			int res =dao.insert(dto);
			if(res>0) {
				Dispatch("board.do?command=list",request,response);
			}else{
				Dispatch("board.do?command=writeform",request,response);
			}
			
		}else if(command.equals("boarddetail")) {
			int bbs_no =Integer.parseInt(request.getParameter("bbs_no"));
			BoardDto dto = dao.selectOne(bbs_no);
			request.setAttribute("dto", dto);
			Dispatch("board_detail2.jsp",request,response);	
		}else if(command.equals("delete")) {
			int bbs_no= Integer.parseInt(request.getParameter("bbs_no"));
			BoardDto dto = new BoardDto();
			dto.setBbs_no(bbs_no);
			int res=dao.delete(bbs_no);
			
			if(res>0) {
				Dispatch("board.jsp",request,response);
			}else {
				Dispatch("board_detail2",request,response);
			}
		}else if(command.equals("updateform")) {
			int bbs_no=Integer.parseInt(request.getParameter("bbs_no"));
			BoardDto dto=dao.selectOne(bbs_no);
			request.setAttribute("dto", dto);
			Dispatch("board_detail3.jsp",request,response);
			
		}else if(command.equals("update")) {
			System.out.println("실행");
			int bbs_no=Integer.parseInt(request.getParameter("bbs_no"));
			System.out.println("실행2");
			String title= request.getParameter("title");
			String content =request.getParameter("content");
			
			BoardDto dto = new BoardDto();
			dto.setBbs_no(bbs_no);
			dto.setTitle(title);
			dto.setContent(content);
			
			int res= dao.update(dto);
			
			if(res>0) {
				Dispatch("board.do?command=updateform&bbs_no="+bbs_no,request,response);
			}else {
				Dispatch("board.jsp",request,response);
			}
			
		}else if(command.equals("error")) {
			jsResponse("일반회원은 글등록이 불가 합니다.","board.jsp", response);
			return ;
		}
			
			
	}



	private void Dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		//
		String s = "<script type='text/javascript'>alert('"+msg+"');"
				+ "location.href='"+url+"';</script>";
		PrintWriter out = response.getWriter();
		out.print(s);
	}

}
