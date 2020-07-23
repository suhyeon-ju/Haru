package com.controller.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.classinfo.dao.ClassInfoDao;
import com.classinfo.dto.ClassInfoDto;
import com.classreview.dao.ClassReviewDao;
import com.classreview.dto.ClassReviewDto;


@WebServlet("/main.do")
public class MainController extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    
    public MainController() {
        super();
    }
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doPost(request,response);
   }

   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
      response.setContentType("text/html; charset=UTF-8");
      String command = request.getParameter("command");
      System.out.println("[command:"+command+"]");
      
      ClassInfoDao dao = new ClassInfoDao();
      ClassReviewDao rdao = new ClassReviewDao();
   
      if(command == null) {
         List<ClassInfoDto> list = dao.selectAll();
         request.setAttribute("list", list);
         List<ClassReviewDto> review =rdao.mainselectAll();
         request.setAttribute("review", review);
         dispatch("main.jsp",request,response);
      }else if(command.equals("category")) {
         //String category = request.getParameter("key");
         //int key = Integer.parseInt("key");
         int key = Integer.parseInt(request.getParameter("key"));
         
         List<ClassInfoDto> list = dao.selectCategory(key);
         
         request.setAttribute("list", list);
         dispatch("detail.jsp",request,response);
         
      }else if(command.equals("customselect")) {
         int[] param = {
               Integer.parseInt(request.getParameter("days")),
               Integer.parseInt(request.getParameter("times")),
               Integer.parseInt(request.getParameter("tTypes")),
               Integer.parseInt(request.getParameter("loc"))
            };
            List<ClassInfoDto> list = dao.customselect(param);
            for (ClassInfoDto dto : list) {
               System.out.println(dto);
            }
            System.out.println("리스트 출력 완료");
            request.setAttribute("list", list);
            dispatch("detail.jsp", request, response);
            
      }else if(command.equals("search")) {
         String keyword = request.getParameter("keyword");
         System.out.println(keyword);
         List<ClassInfoDto> list = dao.selectOne(keyword);
//         List<ClassInfoDto> list = dao.selectAll();
         request.setAttribute("list", list);
         dispatch("detail.jsp", request, response);
      }else if(command.equals("review")) {
         
      }else if(command.equals("error")) {
    	  jsResponse("잘못된 요청입니다.", "main.do", response);
    	  return;
      }
   }


   private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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