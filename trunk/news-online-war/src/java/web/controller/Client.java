/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import web.bean.CategorySBLocal;
import web.bean.FeedbackSBLocal;
import web.bean.HeadSBLocal;
import web.bean.NewsSBLocal;
import web.bean.UserSBLocal;
import web.entity.Category;
import web.entity.Feedback;
import web.entity.HeadNews;
import web.entity.News;
import web.entity.Users;

/**
 *
 * @author Khatmau_sr
 */
public class Client extends HttpServlet {
    @EJB
    private FeedbackSBLocal feedbackSB;
    @EJB
    private UserSBLocal userSB;
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
	    HttpSession session = request.getSession();
	    // <editor-fold defaultstate="collapsed" desc="Index page">
	    if (sAction == null || sAction.equals("goHome")) {
		ut.begin();
		List<Category> cateList = new ArrayList<Category>();
		for(Category item : categorySB.getParentCategories())
		{
		    if(!item.getNewsCollection().isEmpty())
		    {
			cateList.add(item);
		    }
		    else
		    {
			List<Category> subList = categorySB.getSubCategories(item.getCategoryId().toString());
			if(!subList.isEmpty())
			    cateList.add(item);
		    }
		}
		List<News> headNews = new ArrayList<News>();
		for (HeadNews head : headSB.getHeads()) {
		    headNews.add(head.getNews());
		}
		List<List<News>> lists = new ArrayList<List<News>>();
		for (Category cate : cateList) {
		    List<News> list = new ArrayList<News>();
		    List<Category> subList = categorySB.getSubCategories(cate.getCategoryId().toString());
		    if (subList.isEmpty()) {
			list = (List<News>) cate.getNewsCollection();
		    } else {
			for (Category subCate : subList) {
			    list.addAll(subCate.getNewsCollection());
			}
		    }
		    lists.add(list);
		}
		Date now = new Date();
		// EEE gives short day names, EEEE would be full length.
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd;EEE", Locale.US);
		
		request.setAttribute("cateList", cateList);
		request.setAttribute("popularList", newsSB.getPopularNews());
		request.setAttribute("recentList", newsSB.getRecentNews());

		request.setAttribute("cateSB", categorySB);
		request.setAttribute("headMain", headNews.get(0));
		request.setAttribute("headFirst", headNews.get(1));
		request.setAttribute("headSecond", headNews.get(2));
		request.setAttribute("headThirth", headNews.get(3));
		request.setAttribute("date", dateFormat.format(now).split(";")[0]);
		request.setAttribute("day", dateFormat.format(now).split(";")[1]);
		request.setAttribute("lineList", newsSB.getHeadLines());
		request.setAttribute("newsList", lists);

		request.getRequestDispatcher("index.jsp").forward(request, response);
		ut.commit();
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="Detail page">
	    else if (sAction.equals("viewDetail")) {
		ut.begin();
		News news = newsSB.getNewsById(Integer.parseInt(request.getParameter("newsId")));
		news.setViewed(news.getViewed()+1);
		newsSB.update(news);
		List<News> related = new ArrayList<News>();
		List<News> list = (List<News>) news.getCategory().getNewsCollection();
		for (News item : list) {
		    Collection<String> listTagOne = Arrays.asList(news.getTags().replace("; ", ";").replace(" ;", ";").split(";"));
		    Collection<String> listTagTwo = Arrays.asList(item.getTags().replace("; ", ";").replace(" ;", ";").split(";"));
		    Collection<String> similarTag = new HashSet<String>(listTagOne);
		    similarTag.retainAll(listTagTwo);
		    if (similarTag.size() >= 1) {
			Collection<String> listNewsOne = Arrays.asList(news.getTitle().split("\\s+"));
			Collection<String> listNewsTwo = Arrays.asList(item.getTitle().split("\\s+"));
			Collection<String> similarTitle = new HashSet<String>(listNewsOne);
			similarTitle.retainAll(listNewsTwo);
			if (similarTitle.size() >= listNewsOne.size() / 3) {
			    related.add(item);
			}
		    }
		    if (related.size() == 10) {
			break;
		    }
		}
		List<Category> cateList = new ArrayList<Category>();
		for(Category item : categorySB.getParentCategories())
		{
		    if(!item.getNewsCollection().isEmpty())
		    {
			cateList.add(item);
		    }
		    else
		    {
			List<Category> subList = categorySB.getSubCategories(item.getCategoryId().toString());
			if(!subList.isEmpty())
			    cateList.add(item);
		    }
		}
		request.setAttribute("cateList", cateList);
		request.setAttribute("popularList", newsSB.getPopularNews());
		request.setAttribute("recentList", newsSB.getRecentNews());
		request.setAttribute("news", news);
		request.setAttribute("tagList", news.getTags().split(";"));
		request.setAttribute("relatedList", related);
		request.getRequestDispatcher("detail.jsp").forward(request, response);
		ut.commit();
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="Search Page">
	    else if (sAction.equals("search") || sAction.equals("Search")) {
		ut.begin();
		String search = request.getParameter("search").trim().replace("%20", " ");
		if (search == null || search.equals("")) {
		    List<Category> cateList = new ArrayList<Category>();
		    for(Category item : categorySB.getParentCategories())
		    {
			if(!item.getNewsCollection().isEmpty())
			{
			    cateList.add(item);
			}
			else
			{
			    List<Category> subList = categorySB.getSubCategories(item.getCategoryId().toString());
			    if(!subList.isEmpty())
				cateList.add(item);
			}
		    }
		    request.setAttribute("cateList", cateList);
		    request.setAttribute("popularList", newsSB.getPopularNews());
		    request.setAttribute("recentList", newsSB.getRecentNews());
		    request.setAttribute("search", "");
		    request.getRequestDispatcher("404.jsp").forward(request, response);
		} else {
		    Collection<String> listSearch = Arrays.asList(search.split("\\s+"));
		    List<News> searchList = new ArrayList<News>(newsSB.getNewsByTitle(search));
		    List<News> listAll = newsSB.getRecentNews();
		    for (News n : listAll) {
			Collection<String> tagList = Arrays.asList(n.getTags().replace("; ", ";").replace(" ;", ";").split(";"));
			for (String tag : tagList) {
			    Collection<String> tagOne = Arrays.asList(tag.split("\\s+"));
			    Collection<String> similar = new HashSet<String>(listSearch);
			    similar.retainAll(tagOne);
			    if (similar.size() == listSearch.size()) {
				searchList.add(n);
				break;
			    }
			}
		    }
		    request.setAttribute("search", search);
		    List<Category> cateList = new ArrayList<Category>();
		    for(Category item : categorySB.getParentCategories())
		    {
			if(!item.getNewsCollection().isEmpty())
			{
			    cateList.add(item);
			}
			else
			{
			    List<Category> subList = categorySB.getSubCategories(item.getCategoryId().toString());
			    if(!subList.isEmpty())
				cateList.add(item);
			}
		    }
		    request.setAttribute("cateList", cateList);
		    request.setAttribute("popularList", newsSB.getPopularNews());
		    request.setAttribute("recentList", newsSB.getRecentNews());
		    if (searchList.size() > 0) {
			request.setAttribute("resultList", searchList);
			request.getRequestDispatcher("search.jsp").forward(request, response);
		    } else {
			request.getRequestDispatcher("404.jsp").forward(request, response);
		    }
		}
		ut.commit();
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="News by one Category page">
	    else if (sAction.equals("viewCategory")) {
		ut.begin();
		List<Category> cateList = new ArrayList<Category>();
		for(Category item : categorySB.getParentCategories())
		{
		    if(!item.getNewsCollection().isEmpty())
		    {
			cateList.add(item);
		    }
		    else
		    {
			List<Category> subList = categorySB.getSubCategories(item.getCategoryId().toString());
			if(!subList.isEmpty())
			    cateList.add(item);
		    }
		}
		request.setAttribute("cateList", cateList);
		request.setAttribute("popularList", newsSB.getPopularNews());
		request.setAttribute("recentList", newsSB.getRecentNews());

		int cateId = Integer.parseInt(request.getParameter("cateId"));
		Category cate = categorySB.getCategoryById(cateId);
		List<Category> subList = categorySB.getSubCategories(cateId + "");
		List<News> newsList = new ArrayList<News>();
		if (subList.isEmpty()) {
		    newsList = (List<News>) cate.getNewsCollection();
		    request.setAttribute("newsList", newsList);
		    request.getRequestDispatcher("category.jsp").forward(request, response);
		} else {
		    for (Category item : subList) {
			newsList.addAll(item.getNewsCollection());
		    }
		    request.setAttribute("newsList", newsList);
		    request.setAttribute("subList", subList);
		    request.setAttribute("parentCategory", cate.getTitle());
		    request.getRequestDispatcher("filter.jsp").forward(request, response);
		}

		ut.commit();
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="Categories page">
	    else if (sAction.equals("viewCategories")) {
		ut.begin();
		List<Category> cateList = new ArrayList<Category>();
		for(Category item : categorySB.getParentCategories())
		{
		    if(!item.getNewsCollection().isEmpty())
		    {
			cateList.add(item);
		    }
		    else
		    {
			List<Category> subList = categorySB.getSubCategories(item.getCategoryId().toString());
			if(!subList.isEmpty())
			    cateList.add(item);
		    }
		}
		request.setAttribute("cateList", cateList);
		request.setAttribute("popularList", newsSB.getPopularNews());
		request.setAttribute("recentList", newsSB.getRecentNews());

		request.getRequestDispatcher("categories.jsp").forward(request, response);
		ut.commit();
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="Support page">
	    else if (sAction.equals("support")) {
		ut.begin();
		List<Category> cateList = new ArrayList<Category>();
		for(Category item : categorySB.getParentCategories())
		{
		    if(!item.getNewsCollection().isEmpty())
		    {
			cateList.add(item);
		    }
		    else
		    {
			List<Category> subList = categorySB.getSubCategories(item.getCategoryId().toString());
			if(!subList.isEmpty())
			    cateList.add(item);
		    }
		}
		request.setAttribute("cateList", cateList);
		request.setAttribute("popularList", newsSB.getPopularNews());
		request.setAttribute("recentList", newsSB.getRecentNews());

		request.setAttribute("comList", feedbackSB.getFeedbacksByStatus(true));
		request.getRequestDispatcher("support.jsp").forward(request, response);
		ut.commit();
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="Send message">
	    else if (sAction.equals("Send Message")) {
		ut.begin();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String message = request.getParameter("message");

		Feedback feed = new Feedback();
		feed.setAnswer("");
		feed.setEmail(email);
		feed.setName(name);
		feed.setPostedTime(new Date());
		feed.setQuestion(message);
		feed.setStatus(Boolean.TRUE);
		feed.setUsers(null);
		feedbackSB.insert(feed);

		List<Category> cateList = new ArrayList<Category>();
		for(Category item : categorySB.getParentCategories())
		{
		    if(!item.getNewsCollection().isEmpty())
		    {
			cateList.add(item);
		    }
		    else
		    {
			List<Category> subList = categorySB.getSubCategories(item.getCategoryId().toString());
			if(!subList.isEmpty())
			    cateList.add(item);
		    }
		}

		request.setAttribute("cateList", cateList);
		request.setAttribute("popularList", newsSB.getPopularNews());
		request.setAttribute("recentList", newsSB.getRecentNews());

		request.setAttribute("comList", feedbackSB.getFeedbacksByStatus(true));
		request.getRequestDispatcher("support.jsp").forward(request, response);
		ut.commit();
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="AboutUs page">
	    else if (sAction.equals("aboutus")) {
		ut.begin();
		List<Category> cateList = new ArrayList<Category>();
		for(Category item : categorySB.getParentCategories())
		{
		    if(!item.getNewsCollection().isEmpty())
		    {
			cateList.add(item);
		    }
		    else
		    {
			List<Category> subList = categorySB.getSubCategories(item.getCategoryId().toString());
			if(!subList.isEmpty())
			    cateList.add(item);
		    }
		}
		request.setAttribute("cateList", cateList);
		request.setAttribute("popularList", newsSB.getPopularNews());
		request.setAttribute("recentList", newsSB.getRecentNews());

		request.getRequestDispatcher("about-us.jsp").forward(request, response);
		ut.commit();
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="Login - Logout">
	    else if (sAction.equals("goAdmin")) {
		request.getRequestDispatcher("admin/index.jsp").forward(request, response);
	    } else if (sAction.equals("Submit")) {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Users u = new Users();
		u = userSB.login(username, password);
		if (u.getRoles() == null) {
		    request.getRequestDispatcher("loginfail.jsp").forward(request, response);
		} else {
		    if (!u.getStatus()) {
			String message = "Your Account Has Been Banded";
			request.setAttribute("message", message);
			request.getRequestDispatcher("fail.jsp").forward(request, response);
		    } else {
			session.setAttribute("curUser", u);
			session.setAttribute("lastLogin", userSB.getLastLogin(u.getUserId()));
			userSB.setLastLogin(username);
			ut.begin();
			List<Category> cateList = new ArrayList<Category>();
			for(Category item : categorySB.getParentCategories())
			{
			    if(!item.getNewsCollection().isEmpty())
			    {
				cateList.add(item);
			    }
			    else
			    {
				List<Category> subList = categorySB.getSubCategories(item.getCategoryId().toString());
				if(!subList.isEmpty())
				    cateList.add(item);
			    }
			}
			List<News> headNews = new ArrayList<News>();
			for (HeadNews head : headSB.getHeads()) {
			    headNews.add(head.getNews());
			}
			List<List<News>> lists = new ArrayList<List<News>>();
			for (Category cate : cateList) {
			    List<News> list = new ArrayList<News>();
			    if (cate.getParent().equals("None")) {
				list = (List<News>) cate.getNewsCollection();
			    } else {
				List<Category> subList = categorySB.getSubCategories(cate.getCategoryId().toString());
				for (Category subCate : subList) {
				    list.addAll(subCate.getNewsCollection());
				}
			    }
			    lists.add(list);
			}

			request.setAttribute("cateList", cateList);
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
		}
	    } else if (sAction.equals("Register")) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		request.setAttribute("curdate", sdf.format(new Date()));
		request.getRequestDispatcher("register.jsp").forward(request, response);
	    } else if (sAction.equals("Submit Register")) {

		String username = request.getParameter("insertUsername");
		String email = request.getParameter("insertEmail");
		String img = request.getParameter("insertAvatar");
		boolean flag = false;
		if (userSB.checkusername(username)) {
		    request.setAttribute("message", "* Duplicate Username !");
		    flag = true;
		}
		if (userSB.checkemail(email)) {
		    request.setAttribute("message1", "* Duplicate Email !");
		    flag = true;
		}
		if (flag) {
		    request.getRequestDispatcher("fail.jsp").forward(request, response);
		} else {
		    String password = request.getParameter("insertPassword");
		    String fullname = request.getParameter("insertFullname");
		    String birthday = request.getParameter("insertBirthday");
		    String male = request.getParameter("insertrd1");
		    String address = request.getParameter("insertAddress");
		    String phone = request.getParameter("insertPhoneNumber");
		    String question = request.getParameter("insertQuestion");
		    String answer = request.getParameter("insertAnswer");
		    String strsms = request.getParameter("insertSMS");
		    boolean sms = true;
		    if (strsms == null) {
			sms = false;
		    }
		    if (img == null) {
			img = "image.jpg";
		    }
		    userSB.insert(username, password, img, fullname, birthday, male.equals("Male"), address, phone, email, question, answer, "User", 1, sms, true);
		    session.setAttribute("curUser", userSB.getUserByName(username));
		    userSB.setLastLogin(username);
		    ut.begin();
		    List<Category> cateList = new ArrayList<Category>();
		    for(Category item : categorySB.getParentCategories())
		    {
			if(!item.getNewsCollection().isEmpty())
			{
			    cateList.add(item);
			}
			else
			{
			    List<Category> subList = categorySB.getSubCategories(item.getCategoryId().toString());
			    if(!subList.isEmpty())
				cateList.add(item);
			}
		    }
		    List<News> headNews = new ArrayList<News>();
		    for (HeadNews head : headSB.getHeads()) {
			headNews.add(head.getNews());
		    }
		    List<List<News>> lists = new ArrayList<List<News>>();
		    for (Category cate : cateList) {
			List<News> list = new ArrayList<News>();
			List<Category> subList = categorySB.getSubCategories(cate.getCategoryId().toString());
			if (subList.isEmpty()) {
			    list = (List<News>) cate.getNewsCollection();
			} else {
			    for (Category subCate : subList) {
				list.addAll(subCate.getNewsCollection());
			    }
			}
			lists.add(list);
		    }

		    request.setAttribute("cateList", cateList);
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
		    request.setAttribute("message", "Insert Fail !");
		    request.getRequestDispatcher("index.jsp").forward(request, response);
		    ut.commit();
		}

	    } else if (sAction.equals("Update Profile")) {

		Users u = (Users) session.getAttribute("curUser");
		String email = request.getParameter("insertEmail");
		String message = "";
		if (userSB.checkemail(email)) {
		    message += "* Duplicate Email !";
		}
		if (!message.equals("") && !email.equals(u.getEmail())) {
		    request.setAttribute("message", message);
		    request.getRequestDispatcher("fail.jsp").forward(request, response);
		} else {
		    String fullname = request.getParameter("insertFullname");
		    String birthday = request.getParameter("insertBirthday");
		    String male = request.getParameter("insertrd1");
		    String address = request.getParameter("insertAddress");
		    String phone = request.getParameter("insertPhoneNumber");
		    String img = request.getParameter("insertAvatar");
		    if (img == null) {
			img = "image.jpg";
		    }
		    String strsms = request.getParameter("insertSMS");
		    boolean sms = true;
		    if (strsms == null) {
			sms = false;
		    }
		    userSB.update(u.getUserId(), img, fullname, birthday, male.equals("Male"), address, phone, email, u.getQuestion(), u.getAnswer(), 1, sms);
		    session.setAttribute("curUser", userSB.getUser(u.getUserId()));
		    ut.begin();
		    List<Category> cateList = new ArrayList<Category>();
		    for(Category item : categorySB.getParentCategories())
		    {
			if(!item.getNewsCollection().isEmpty())
			{
			    cateList.add(item);
			}
			else
			{
			    List<Category> subList = categorySB.getSubCategories(item.getCategoryId().toString());
			    if(!subList.isEmpty())
				cateList.add(item);
			}
		    }
		    List<News> headNews = new ArrayList<News>();
		    for (HeadNews head : headSB.getHeads()) {
			headNews.add(head.getNews());
		    }
		    List<List<News>> lists = new ArrayList<List<News>>();
		    for (Category cate : cateList) {
			List<News> list = new ArrayList<News>();
			List<Category> subList = categorySB.getSubCategories(cate.getCategoryId().toString());
			if (subList.isEmpty()) {
			    list = (List<News>) cate.getNewsCollection();
			} else {
			    for (Category subCate : subList) {
				list.addAll(subCate.getNewsCollection());
			    }
			}
			lists.add(list);
		    }
		    request.setAttribute("cateList", cateList);
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

	    } else if (sAction.equals("Logout")) {

		session.removeAttribute("curUser");
		ut.begin();
		List<Category> cateList = new ArrayList<Category>();
		for(Category item : categorySB.getParentCategories())
		{
		    if(!item.getNewsCollection().isEmpty())
		    {
			cateList.add(item);
		    }
		    else
		    {
			List<Category> subList = categorySB.getSubCategories(item.getCategoryId().toString());
			if(!subList.isEmpty())
			    cateList.add(item);
		    }
		}
		List<News> headNews = new ArrayList<News>();
		for (HeadNews head : headSB.getHeads()) {
		    headNews.add(head.getNews());
		}
		List<List<News>> lists = new ArrayList<List<News>>();
		for (Category cate : cateList) {
		    List<News> list = new ArrayList<News>();
		    List<Category> subList = categorySB.getSubCategories(cate.getCategoryId().toString());
		    if (subList.isEmpty()) {
			list = (List<News>) cate.getNewsCollection();
		    } else {
			for (Category subCate : subList) {
			    list.addAll(subCate.getNewsCollection());
			}
		    }
		    lists.add(list);
		}
		request.setAttribute("cateList", cateList);
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
	    } else if (sAction.equals("Next")) {
		String username = request.getParameter("username");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		if (!userSB.checkusername(username)) {
		    request.setAttribute("message", "Wrong Username");
		    request.getRequestDispatcher("fail.jsp").forward(request, response);
		} else if (userSB.getUserByName(username).getQuestion().equals(question) && userSB.getUserByName(username).getAnswer().equals(answer)) {
		    session.setAttribute("curRecoverUser", userSB.getUserByName(username));
		    request.getRequestDispatcher("newpassword.jsp").forward(request, response);
		} else {
		    request.setAttribute("message", "Wrong Question Or Password");
		    request.getRequestDispatcher("fail.jsp").forward(request, response);
		}
	    } else if (sAction.equals("Change Password")) {
		String oldpassword = request.getParameter("oldpassword");
		String password = request.getParameter("confirmpassword");
		Users u = (Users) session.getAttribute("curUser");
		if (!oldpassword.equals(u.getPassword())) {
		    request.setAttribute("message", "Wrong Old Password");
		    request.getRequestDispatcher("fail.jsp").forward(request, response);
		} else {
		    if (!userSB.changePassword(u.getUserId(), password)) {
			request.setAttribute("message", "Change Password Fail");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
		    } else {
			ut.begin();
			List<Category> cateList = new ArrayList<Category>();
			for(Category item : categorySB.getParentCategories())
			{
			    if(!item.getNewsCollection().isEmpty())
			    {
				cateList.add(item);
			    }
			    else
			    {
				List<Category> subList = categorySB.getSubCategories(item.getCategoryId().toString());
				if(!subList.isEmpty())
				    cateList.add(item);
			    }
			}
			List<News> headNews = new ArrayList<News>();
			for (HeadNews head : headSB.getHeads()) {
			    headNews.add(head.getNews());
			}
			List<List<News>> lists = new ArrayList<List<News>>();
			for (Category cate : cateList) {
			    List<News> list = new ArrayList<News>();
			    List<Category> subList = categorySB.getSubCategories(cate.getCategoryId().toString());
			    if (subList.isEmpty()) {
				list = (List<News>) cate.getNewsCollection();
			    } else {
				for (Category subCate : subList) {
				    list.addAll(subCate.getNewsCollection());
				}
			    }
			    lists.add(list);
			}
			request.setAttribute("cateList", cateList);
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
		}
	    } else if (sAction.equals("Recover")) {
		Users u = (Users) session.getAttribute("curRecoverUser");
		String password = request.getParameter("password");
		userSB.changePassword(u.getUserId(), password);
		ut.begin();
		List<Category> cateList = new ArrayList<Category>();
		for(Category item : categorySB.getParentCategories())
		{
		    if(!item.getNewsCollection().isEmpty())
		    {
			cateList.add(item);
		    }
		    else
		    {
			List<Category> subList = categorySB.getSubCategories(item.getCategoryId().toString());
			if(!subList.isEmpty())
			    cateList.add(item);
		    }
		}
		List<News> headNews = new ArrayList<News>();
		for (HeadNews head : headSB.getHeads()) {
		    headNews.add(head.getNews());
		}
		List<List<News>> lists = new ArrayList<List<News>>();
		for (Category cate : cateList) {
		    List<News> list = new ArrayList<News>();
		    List<Category> subList = categorySB.getSubCategories(cate.getCategoryId().toString());
		    if (subList.isEmpty()) {
			list = (List<News>) cate.getNewsCollection();
		    } else {
			for (Category subCate : subList) {
			    list.addAll(subCate.getNewsCollection());
			}
		    }
		    lists.add(list);
		}
		request.setAttribute("cateList", cateList);
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
	    }// </editor-fold>
	} catch (Exception ex) {
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
