/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import web.bean.CategorySBLocal;
import web.bean.NewsSBLocal;
import web.bean.UserSBLocal;
import web.entity.News;
import web.entity.Users;

/**
 *
 * @author Khatmau_sr
 */
public class Admin extends HttpServlet {
    @EJB
    private UserSBLocal userSB;
    @EJB
    private CategorySBLocal categorySB;
    @EJB
    private NewsSBLocal newsSB;
    @Resource
    UserTransaction ut;
   
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
	    if(sAction == null || sAction.equals("goHome"))
	    {
		request.getRequestDispatcher("admin/index.jsp").forward(request, response);
	    }
	    else if(sAction.equals("newsManager"))
	    {
		ut.begin();
		Users cur_user = userSB.getUserById(1);
		List<News> newsList = (List<News>) cur_user.getNewsCollection();
		request.setAttribute("newsList", newsList);
		request.getRequestDispatcher("admin/news-list.jsp").forward(request, response);
		ut.commit();
	    }
        } catch(Exception ex) {
	    ex.printStackTrace();
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