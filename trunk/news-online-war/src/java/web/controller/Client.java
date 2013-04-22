/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web.controller;

import java.io.*;
import java.util.*;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
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
public class Client extends HttpServlet {
    @EJB
    private HeadSBLocal headSB;
    @EJB
    private NewsSBLocal newsSB;
    @EJB
    private CategorySBLocal categorySB;
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
		ut.begin();
		List<Category> cateList = categorySB.getParentCategories();
		List<News> headNews = new ArrayList<News>();
		for(HeadNews head : headSB.getHeads())
		{
		    headNews.add(head.getNews());
		}
		List<List<News>> lists = new ArrayList<List<News>>();
		for(Category cate : cateList )
		{
		    List<News> list = new ArrayList<News>();
		    if(cate.getParent().equals("None"))
		    {
			list = (List<News>)cate.getNewsCollection();
		    }
		    else
		    {
			List<Category> subList = categorySB.getSubCategories(cate.getCategoryId().toString());
			for(Category subCate : subList)
			{
			    list.addAll(subCate.getNewsCollection());
			}
		    }
		    lists.add(list);
		}
		request.setAttribute("cateList", categorySB.getParentCategories());
		request.setAttribute("popularList", newsSB.getPopularNews());
		request.setAttribute("recentList", newsSB.getRecentNews());

		request.setAttribute("cateSB", categorySB);
		request.setAttribute("headMain", headNews.get(0));
		request.setAttribute("headFirst", headNews.get(1));
		request.setAttribute("headSecond", headNews.get(2));
		request.setAttribute("headThirth", headNews.get(3));
		request.setAttribute("lineList", newsSB.getHeadLines());
		request.setAttribute("newsList", lists);

		request.getRequestDispatcher("index.jsp").forward(request, response);
		ut.commit();
	    }
	    else if(sAction.equals("viewDetail"))
	    {
		ut.begin();
		News news = newsSB.getNewsById(Integer.parseInt(request.getParameter("newsId")));
		List<News> related = new ArrayList<News>();
		List<News> list = (List<News>)news.getCategory().getNewsCollection();
		for(News item : list)
		{
		    Collection<String> listTagOne = Arrays.asList(news.getTags().replace("; ", ";").replace(" ;", ";").split(";"));
		    Collection<String> listTagTwo = Arrays.asList(item.getTags().replace("; ", ";").replace(" ;", ";").split(";"));
		    Collection<String> similarTag = new HashSet<String>( listTagOne );
		    similarTag.retainAll(listTagTwo);
		    if(similarTag.size() >= 1)
		    {
			Collection<String> listNewsOne = Arrays.asList(news.getTitle().split("\\s+"));
			Collection<String> listNewsTwo = Arrays.asList(item.getTitle().split("\\s+"));
			Collection<String> similarTitle = new HashSet<String>( listNewsOne );
			similarTitle.retainAll(listNewsTwo);
			if(similarTitle.size() >= listNewsOne.size() / 3)
			    related.add(item);
		    }
		    if(related.size() == 10)
			break;
		}

		request.setAttribute("cateList", categorySB.getParentCategories());
		request.setAttribute("popularList", newsSB.getPopularNews());
		request.setAttribute("recentList", newsSB.getRecentNews());
		request.setAttribute("news", news);
		request.setAttribute("tagList", news.getTags().split(";"));
		request.setAttribute("relatedList", related);
		request.getRequestDispatcher("detail.jsp").forward(request, response);
		ut.commit();
	    }
	    else if(sAction.equals("search") || sAction.equals("Search")){
		String search = request.getParameter("search").trim().replace("%20", " ");
		Collection<String> listSearch = Arrays.asList(search.split("\\s+"));
		List<News> searchList = new ArrayList<News>(newsSB.getNewsByTitle(search));
		List<News> listAll = newsSB.getRecentNews();
		for(News n : listAll)
		{
		    Collection<String> tagList = Arrays.asList(n.getTags().replace("; ", ";").replace(" ;", ";").split(";"));
		    for(String tag : tagList)
		    {
			Collection<String> tagOne = Arrays.asList(tag.split("\\s+"));
			Collection<String> similar = new HashSet<String>( listSearch );
			similar.retainAll(tagOne);
			if(similar.size() == listSearch.size())
			{
			    searchList.add(n);
			    break;
			}
		    }
		}

		request.setAttribute("search", search);
		request.setAttribute("cateList", categorySB.getParentCategories());
		request.setAttribute("popularList", newsSB.getPopularNews());
		request.setAttribute("recentList", newsSB.getRecentNews());
		if(searchList.size() > 0)
		{
		    request.setAttribute("resultList", searchList);
		    request.getRequestDispatcher("search.jsp").forward(request, response);
		}
		else
		{
		    request.getRequestDispatcher("404.jsp").forward(request, response);
		}
	    }
	    else if(sAction.equals("viewCategory")){
		ut.begin();
		Category cate = categorySB.getCategoryById(Integer.parseInt(request.getParameter("cateId")));
		List<News> newsList = (List<News>)cate.getNewsCollection();
		List<Category> subList = categorySB.getSubCategories(cate.getCategoryId().toString());

		request.setAttribute("cateList", categorySB.getParentCategories());
		request.setAttribute("popularList", newsSB.getPopularNews());
		request.setAttribute("recentList", newsSB.getRecentNews());

		if(subList.isEmpty())
		{
		    request.setAttribute("newsList", newsList);
		    request.getRequestDispatcher("category.jsp").forward(request, response);
		}
		else
		{
		    for(Category item : subList)
		    {
			newsList.addAll(item.getNewsCollection());
			request.setAttribute("newsList", newsList);
			request.setAttribute("subList", subList);
			request.getRequestDispatcher("filter.jsp").forward(request, response);
		    }
		}
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
