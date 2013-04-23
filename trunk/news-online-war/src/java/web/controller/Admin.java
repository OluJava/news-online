/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
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
	    if (sAction == null || sAction.equals("index")) {
		request.getRequestDispatcher("admin/index.jsp").forward(request, response);
	    }
	    // <editor-fold defaultstate="collapsed" desc="News Process">
	    else if (sAction.equals("news-list")) {
		ut.begin();
		Users cur_user = userSB.getUserById(1);
		List<News> newsList = new ArrayList<News>();
		for (News news : cur_user.getNewsCollection()) {
		    if (news.getStatus()) {
			newsList.add(news);
		    }
		}
		request.setAttribute("newsList", newsList);
		request.getRequestDispatcher("admin/news-list.jsp").forward(request, response);
		ut.commit();
	    } else if (sAction.equals("news-add")) {
		request.getRequestDispatcher("admin/news-add.jsp").forward(request, response);
	    } else if (sAction.equals("news-edit")) {
		int newsId = Integer.parseInt(request.getParameter("newsId"));
		News news = newsSB.getNewsById(newsId);
		request.setAttribute("news", news);
		request.getRequestDispatcher("admin/news-edit.jsp").forward(request, response);
	    } else if (sAction.equals("news-popular")) {
		ut.begin();
		Users cur_user = userSB.getUserById(1);
		List<News> newsList = new ArrayList<News>();
		for (News news : cur_user.getNewsCollection()) {
		    if (!news.getStatus()) {
			newsList.add(news);
		    }
		}
		request.setAttribute("newsList", newsList);
		request.getRequestDispatcher("admin/news-trash.jsp").forward(request, response);
		ut.commit();
	    } else if (sAction.equals("news-trash")) {
		ut.begin();
		Users cur_user = userSB.getUserById(1);
		List<News> newsList = new ArrayList<News>();
		for (News news : cur_user.getNewsCollection()) {
		    if (!news.getStatus()) {
			newsList.add(news);
		    }
		}
		request.setAttribute("newsList", newsList);
		request.getRequestDispatcher("admin/news-trash.jsp").forward(request, response);
		ut.commit();
	    }
	    // <editor-fold defaultstate="collapsed" desc="Insert News">
	    else if (sAction.equals("Publish")) {
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String image = request.getParameter("image");
		String description = request.getParameter("description");
		String content = request.getParameter("content");
		String tag = request.getParameter("tags");

		News news = new News();

		if (newsSB.getNews(title).isEmpty()) {
		    news.setTitle(title);
		    news.setImage(image);
		    news.setAuthor(author);
		    news.setDescription(description);
		    news.setContent(content);
		    news.setTags(tag);
		    news.setPostedDate(new Date());
		    news.setEditedDate(null);
		    news.setViewed(0);
		    news.setCategory(userSB.getUserById(1).getCategory());
		    news.setStatus(Boolean.TRUE);
		    news.setUsers(userSB.getUserById(1));
		    newsSB.insert(news);

		    request.setAttribute("sucsess", "Published successfuly!");
		    request.getRequestDispatcher("admin/message.jsp").forward(request, response);
		} else {
		    request.setAttribute("error", "Title is duplicated!");
		    request.getRequestDispatcher("admin/message.jsp").forward(request, response);
		}
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="Update News">
	    else if (sAction.equals("Save")) {
		int newsId = Integer.parseInt(request.getParameter("newsId"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String image = request.getParameter("image");
		String description = request.getParameter("description");
		String content = request.getParameter("content");
		String tag = request.getParameter("tags");

		News news = newsSB.getNewsById(newsId);

		if (newsSB.getNews(title).size() < 2) {
		    news.setTitle(title);
		    news.setAuthor(author);
		    news.setImage(image);
		    news.setDescription(description);
		    news.setContent(content);
		    news.setTags(tag);
		    news.setEditedDate(new Date());
		    newsSB.update(news);

		    request.setAttribute("sucsess", "Save successfuly!");
		    request.getRequestDispatcher("admin/message.jsp").forward(request, response);
		} else {
		    request.setAttribute("error", "Title is duplicated!");
		    request.getRequestDispatcher("admin/message.jsp").forward(request, response);
		}
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="Remove News">
	    else if (sAction.equals("Remove")) {
		int newsId = Integer.parseInt(request.getParameter("newsId"));
		News news = newsSB.getNewsById(newsId);
		news.setStatus(Boolean.FALSE);
		newsSB.update(news);
		ut.begin();
		Users cur_user = userSB.getUserById(1);
		List<News> newsList = new ArrayList<News>();
		for (News item : cur_user.getNewsCollection()) {
		    if (item.getStatus()) {
			newsList.add(item);
		    }
		}
		request.setAttribute("newsList", newsList);
		request.getRequestDispatcher("admin/news-trash.jsp").forward(request, response);
		ut.commit();
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="Restore News">
	    else if (sAction.equals("Restore")) {
		int newsId = Integer.parseInt(request.getParameter("newsId"));
		News news = newsSB.getNewsById(newsId);
		news.setStatus(Boolean.TRUE);
		newsSB.update(news);
		ut.begin();
		Users cur_user = userSB.getUserById(1);
		List<News> newsList = new ArrayList<News>();
		for (News item : cur_user.getNewsCollection()) {
		    if (!item.getStatus()) {
			newsList.add(item);
		    }
		}
		request.setAttribute("newsList", newsList);
		request.getRequestDispatcher("admin/news-trash.jsp").forward(request, response);
		ut.commit();
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="Delete News">
	    else if (sAction.equals("Delete")) {
		int newsId = Integer.parseInt(request.getParameter("newsId"));
		newsSB.delete(newsSB.getNewsById(newsId));
		ut.begin();
		Users cur_user = userSB.getUserById(1);
		List<News> newsList = new ArrayList<News>();
		for (News item : cur_user.getNewsCollection()) {
		    if (!item.getStatus()) {
			newsList.add(item);
		    }
		}
		request.setAttribute("newsList", newsList);
		request.getRequestDispatcher("admin/news-trash.jsp").forward(request, response);
		ut.commit();
	    }// </editor-fold>
	    // </editor-fold>
	} catch (Exception ex) {
	    ex.getStackTrace();
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
