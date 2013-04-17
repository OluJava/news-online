/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.bean.CategorySBLocal;
import web.bean.HeadSBLocal;
import web.bean.NewsSBLocal;
import web.entity.Category;
import web.entity.HeadNews;
import web.entity.News;

/**
 *
 * @author Khatmau_sr
 */
public class Controller extends HttpServlet {
    @EJB
    private HeadSBLocal headSB;
    @EJB
    private NewsSBLocal newsSB;
    @EJB
    private CategorySBLocal categorySB;
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
	    String sAction = request.getParameter("action");
	    if(sAction == null)
	    {
		//List<Category> cateList = categorySB.getParentCategories();
		List<News> headNews = new ArrayList<News>();
		for(HeadNews head : headSB.getHeads())
		{
		    headNews.add(head.getNews());
		}
		request.setAttribute("cateList", categorySB.getParentCategories());
		request.setAttribute("headMain", headNews.get(0));
		request.setAttribute("headFirst", headNews.get(1));
		request.setAttribute("headSecond", headNews.get(2));
		request.setAttribute("headThirth", headNews.get(3));
		request.setAttribute("lineList", newsSB.getHeadLines());
		request.getRequestDispatcher("index.jsp").forward(request, response);
	    }
        } finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
