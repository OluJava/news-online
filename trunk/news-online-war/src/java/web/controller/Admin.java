/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.persistence.PersistenceException;
import javax.servlet.ServletContext;
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
import web.entity.HeadNews;
import web.entity.News;
import web.entity.Users;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import web.entity.Category;
import web.entity.Feedback;
/**
 *
 * @author Khatmau_sr
 */
public class Admin extends HttpServlet {
    @EJB
    private FeedbackSBLocal feedbackSB;
    @EJB
    private CategorySBLocal categorySB;
    @EJB
    private HeadSBLocal headSB;
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
	    HttpSession session = request.getSession();
	    if (sAction == null || sAction.equals("index")) {
		request.getRequestDispatcher("admin/index.jsp").forward(request, response);
	    }
	    // <editor-fold defaultstate="collapsed" desc="Image Process">
	    // <editor-fold defaultstate="collapsed" desc="Image page">
	    else if (sAction.equals("image-list")) {
		ServletContext context = getServletContext();
		List<String> list = new ArrayList<String>();
		File folder = new File(context.getRealPath("admin/img/news/"));
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
		    if (listOfFiles[i].isFile()) {
			list.add(listOfFiles[i].getName());
		    }
		}
		request.setAttribute("imageList", list);
		request.getRequestDispatcher("admin/image-list.jsp").forward(request, response);
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="Upload image">
	    else if (sAction.equals("Upload")) {
		List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
		InputStream inputStream = null;
		OutputStream outputStream = null;
		for (FileItem item : items) {
		    if (!item.isFormField()) {
			if (item.getFieldName().equals("file1")) {
			    if(item.getSize() < 1048576)
			    {
				String filename = FilenameUtils.getName(item.getName());
				inputStream = item.getInputStream();
				String relativeWebPath = "admin/img/news";
				String absoluteFilePath = getServletContext().getRealPath(relativeWebPath);
				File copyFile = new File(absoluteFilePath, filename);
				outputStream = new FileOutputStream(copyFile);
				int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = inputStream.read(bytes)) != -1) {
				    outputStream.write(bytes, 0, read);
				}
			    }
			    else
			    {
				request.setAttribute("error", "Image size is too large! Must under 1MB!");
				request.getRequestDispatcher("admin/message.jsp").forward(request, response);
			    }
			}
		    }
		}
		ServletContext context = getServletContext();
		List<String> list = new ArrayList<String>();
		File folder = new File(context.getRealPath("admin/img/news/"));
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
		    if (listOfFiles[i].isFile()) {
			list.add(listOfFiles[i].getName());
		    }
		}
		request.setAttribute("imageList", list);
		request.getRequestDispatcher("admin/image-list.jsp").forward(request, response);
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="Delete image">
	    else if (sAction.equals("deleteimage")) {

		ServletContext context = getServletContext();
		File file = new File(context.getRealPath("admin/img/news/" + request.getParameter("img")));
		file.delete();

		List<String> list = new ArrayList<String>();
		File folder = new File(context.getRealPath("admin/img/news/"));
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
		    if (listOfFiles[i].isFile()) {
			list.add(listOfFiles[i].getName());
		    }
		}
		request.setAttribute("imageList", list);
		request.getRequestDispatcher("admin/image-list.jsp").forward(request, response);
	    }// </editor-fold>
	    // </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="News Process">
	    // <editor-fold defaultstate="collapsed" desc="News List page">
	    else if (sAction.equals("news-list")) {
		ut.begin();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		Users cur_user = userSB.getUserById(userId);
		List<News> newsList = new ArrayList<News>();
		for (News news : cur_user.getNewsCollection()) {
		    if (news.getStatus()) {
			newsList.add(news);
		    }
		}
		request.setAttribute("newsList", newsList);
		request.getRequestDispatcher("admin/news-list.jsp").forward(request, response);
		ut.commit();
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="News Add page">
	    else if (sAction.equals("news-add")) {

		ServletContext context = getServletContext();
		List<String> list = new ArrayList<String>();
		File folder = new File(context.getRealPath("admin/img/news/"));
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
		    if (listOfFiles[i].isFile()) {
			list.add(listOfFiles[i].getName());
		    }
		}
		request.setAttribute("imageList", list);
		request.getRequestDispatcher("admin/news-add.jsp").forward(request, response);
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="News Edit page">
	    else if (sAction.equals("news-edit")) {
		int newsId = Integer.parseInt(request.getParameter("newsId"));
		News news = newsSB.getNewsById(newsId);

		ServletContext context = getServletContext();
		List<String> list = new ArrayList<String>();
		File folder = new File(context.getRealPath("admin/img/news/"));
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
		    if (listOfFiles[i].isFile()) {
			list.add(listOfFiles[i].getName());
		    }
		}
		request.setAttribute("imageList", list);

		request.setAttribute("news", news);
		request.getRequestDispatcher("admin/news-edit.jsp").forward(request, response);
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="News Hot page">
	    else if (sAction.equals("news-popular")) {
		List<Integer> cur_List = new ArrayList<Integer>();
		for (HeadNews item : headSB.getHeads()) {
		    cur_List.add(item.getNews().getNewsId());
		}
		List<News> newsList = new ArrayList<News>();
		for (News news : newsSB.getRecentNews()) {
		    newsList.add(news);
		    if (newsList.size() == 30) {
			break;

		    }
		}
		request.setAttribute("cur_List", cur_List);
		request.setAttribute("newsList", newsList);
		request.getRequestDispatcher("admin/news-popular.jsp").forward(request, response);
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="News Trash page">
	    else if (sAction.equals("news-trash")) {
		ut.begin();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		Users cur_user = userSB.getUserById(userId);
		List<News> newsList = new ArrayList<News>();
		for (News news : cur_user.getNewsCollection()) {
		    if (!news.getStatus()) {
			newsList.add(news);
		    }
		}
		request.setAttribute("newsList", newsList);
		request.getRequestDispatcher("admin/news-trash.jsp").forward(request, response);
		ut.commit();
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="Insert News">
	    else if (sAction.equals("Publish")) {
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String image = request.getParameter("image");
		String description = request.getParameter("description");
		String content = request.getParameter("content");
		String tag = request.getParameter("tags");

		News news = new News();

		if (newsSB.getNewsInsert(title).isEmpty()) {
		    news.setTitle(title);
		    news.setImage(image);
		    news.setAuthor(author);
		    news.setDescription(description);
		    news.setContent(content);
		    news.setTags(tag);
		    news.setPostedDate(new Date());
		    news.setEditedDate(null);
		    news.setViewed(0);
		    news.setCategory(userSB.getUserById(userId).getCategory());
		    news.setStatus(Boolean.TRUE);
		    news.setUsers(userSB.getUserById(userId));
		    newsSB.insert(news);

		    request.setAttribute("done", "Published successfuly!");
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

		if (newsSB.getNewsUpdate(newsId, title).size() < 1) {
		    news.setTitle(title);
		    news.setAuthor(author);
		    news.setImage(image);
		    news.setDescription(description);
		    news.setContent(content);
		    news.setTags(tag);
		    news.setEditedDate(new Date());
		    newsSB.update(news);

		    request.setAttribute("done", "Save successfuly!");
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
		request.getRequestDispatcher("admin/news-list.jsp").forward(request, response);
		ut.commit();
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="Restore News">
	    else if (sAction.equals("Restore")) {
		int newsId = Integer.parseInt(request.getParameter("newsId"));
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		News news = newsSB.getNewsById(newsId);
		news.setStatus(Boolean.TRUE);
		newsSB.update(news);
		ut.begin();
		Users cur_user = userSB.getUserById(userId);
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
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		try
		{
		    newsSB.delete(newsSB.getNewsById(newsId));
		} catch(PersistenceException ex) {
		    request.setAttribute("error", "This news is related to other data!");
		    request.getRequestDispatcher("admin/message.jsp").forward(request, response);
		}
		ut.begin();
		Users cur_user = userSB.getUserById(userId);
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
	    // <editor-fold defaultstate="collapsed" desc="Config popular news">
	    else if (sAction.equals("Accept")) {
		int news1 = Integer.parseInt(request.getParameter("news1"));
		int news2 = Integer.parseInt(request.getParameter("news2"));
		int news3 = Integer.parseInt(request.getParameter("news3"));
		int news4 = Integer.parseInt(request.getParameter("news4"));
		List<Integer> list = Arrays.asList(news1, news2, news3, news4);
		int i = 0;
		for (HeadNews item : headSB.getHeads()) {
		    item.setNews(newsSB.getNewsById(list.get(i)));
		    item.setImages(newsSB.getNewsById(list.get(i)).getImage());
		    headSB.update(item);
		    i++;
		}
		List<Integer> cur_List = new ArrayList<Integer>();
		for(HeadNews item : headSB.getHeads())
		{
		    cur_List.add(item.getNews().getNewsId());
		}
		List<News> newsList = new ArrayList<News>();
		for (News news : newsSB.getRecentNews()) {
		    newsList.add(news);
		    if (newsList.size() == 30)
			break ;
		}
		request.setAttribute("cur_List", cur_List);
		request.setAttribute("newsList", newsList);
		request.getRequestDispatcher("admin/news-popular.jsp").forward(request, response);
	    }// </editor-fold>
	    // </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="Category Process">
	    // <editor-fold defaultstate="collapsed" desc="Insert category Page">
	    else if (sAction.equals("category-add")) {
		
		request.setAttribute("cateList", categorySB.getParentCategories());
		request.getRequestDispatcher("admin/category-add.jsp").forward(request, response);
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="Edit category Page">
	    else if (sAction.equals("category-edit")) {
		int cateId = Integer.parseInt(request.getParameter("cateId"));
		
		request.setAttribute("category", categorySB.getCategoryById(cateId));
		request.setAttribute("cateList", categorySB.getParentCategories());
		request.getRequestDispatcher("admin/category-edit.jsp").forward(request, response);
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="List categories Page">
	    else if (sAction.equals("category-list")) {
		request.setAttribute("cateList", categorySB.getCategoryByStatus(true));
		request.getRequestDispatcher("admin/category-list.jsp").forward(request, response);
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="Trash categories page">
	    else if (sAction.equals("category-trash")) {
		
		request.setAttribute("cateList", categorySB.getCategoryByStatus(false));
		request.getRequestDispatcher("admin/category-trash.jsp").forward(request, response);
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="add category">
	    else if (sAction.equals("addCategory")) {
		String title = request.getParameter("title");
		if (categorySB.checkInsert(title)) {
		    String parent = request.getParameter("parent");

		    Category cate = new Category();
		    cate.setTitle(title);
		    cate.setParent(parent);
		    cate.setStatus(Boolean.TRUE);
		    categorySB.insert(cate);
		    request.setAttribute("done", "Added successfuly!");
		    request.getRequestDispatcher("admin/message.jsp").forward(request, response);
		} else {
		    request.setAttribute("error", "Title is duplicated!");
		    request.getRequestDispatcher("admin/message.jsp").forward(request, response);
		}
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="edit category">
	    else if (sAction.equals("editCategory")) {
		int cateId = Integer.parseInt(request.getParameter("cateId"));
		String title = request.getParameter("title");
		if (categorySB.checkUpdate(cateId, title)) {
		    String parent = request.getParameter("parent");

		    Category cate = categorySB.getCategoryById(cateId);
		    cate.setTitle(title);
		    cate.setParent(parent);
		    categorySB.update(cate);
		    request.setAttribute("done", "Saved successfuly!");
		    request.getRequestDispatcher("admin/message.jsp").forward(request, response);
		} else {
		    request.setAttribute("error", "Title is duplicated!");
		    request.getRequestDispatcher("admin/message.jsp").forward(request, response);
		}
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="remove category">
	    else if (sAction.equals("removeCategory")) {
		int cateId = Integer.parseInt(request.getParameter("cateId"));

		Category cate = categorySB.getCategoryById(cateId);

		cate.setStatus(Boolean.FALSE);
		categorySB.update(cate);

		request.setAttribute("cateList", categorySB.getCategoryByStatus(true));
		request.getRequestDispatcher("admin/category-list.jsp").forward(request, response);
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="restore category">
	    else if (sAction.equals("restoreCategory")) {
		int cateId = Integer.parseInt(request.getParameter("cateId"));

		Category cate = categorySB.getCategoryById(cateId);

		cate.setStatus(Boolean.TRUE);
		categorySB.update(cate);

		request.setAttribute("cateList", categorySB.getCategoryByStatus(false));
		request.getRequestDispatcher("admin/category-trash.jsp").forward(request, response);
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="delete category">
	    else if (sAction.equals("deleteCategory")) {
		int cateId = Integer.parseInt(request.getParameter("cateId"));

		Category cate = categorySB.getCategoryById(cateId);
		try{
		    categorySB.delete(cate);
		} catch(PersistenceException ex) {
		    request.setAttribute("error", "This category is related to other data!");
		    request.getRequestDispatcher("admin/message.jsp").forward(request, response);
		}

		request.setAttribute("cateList", categorySB.getCategoryByStatus(false));
		request.getRequestDispatcher("admin/category-list.jsp").forward(request, response);
	    }// </editor-fold>
	    // </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="Feedback Process">
	    // <editor-fold defaultstate="collapsed" desc="Feedback list page">
	    else if (sAction.equals("feedback-list")) {
		request.setAttribute("feedbackList", feedbackSB.getFeedbacksByStatus(true));
		request.getRequestDispatcher("admin/feedback-list.jsp").forward(request, response);
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="Feedback Edit page">
	    else if (sAction.equals("feedback-edit")) {
		int id = Integer.parseInt(request.getParameter("feedbackId"));
		request.setAttribute("feedback", feedbackSB.getFeedbackById(id));
		request.setAttribute("feedbackList", feedbackSB.getFeedbacksByStatus(true));
		request.getRequestDispatcher("admin/feedback-edit.jsp").forward(request, response);
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="Feedback trash page">
	    else if (sAction.equals("feedback-trash")) {
		request.setAttribute("feedbackList", feedbackSB.getFeedbacksByStatus(false));
		request.getRequestDispatcher("admin/feedback-trash.jsp").forward(request, response);
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="Answer feedback">
	    else if (sAction.equals("saveFeedback")) {
		int id = Integer.parseInt(request.getParameter("feedbackId"));
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		Users cur_user = userSB.getUserById(userId);
		String answer = request.getParameter("answer");
		Feedback f = feedbackSB.getFeedbackById(id);
		f.setAnswer(answer);
		f.setUsers(cur_user);
		feedbackSB.update(f);
		request.setAttribute("feedbackList", feedbackSB.getFeedbacksByStatus(true));
		request.getRequestDispatcher("admin/feedback-list.jsp").forward(request, response);
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="Remove feedback">
	    else if (sAction.equals("removeFeedback")) {
		int id = Integer.parseInt(request.getParameter("feedbackId"));
		Feedback f = feedbackSB.getFeedbackById(id);
		f.setStatus(Boolean.FALSE);
		feedbackSB.update(f);
		request.setAttribute("feedbackList", feedbackSB.getFeedbacksByStatus(true));
		request.getRequestDispatcher("admin/feedback-list.jsp").forward(request, response);
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="Restore feedback">
	    else if (sAction.equals("restoreFeedback")) {
		int id = Integer.parseInt(request.getParameter("feedbackId"));
		Feedback f = feedbackSB.getFeedbackById(id);
		f.setStatus(Boolean.TRUE);
		feedbackSB.update(f);
		request.setAttribute("feedbackList", feedbackSB.getFeedbacksByStatus(false));
		request.getRequestDispatcher("admin/feedback-trash.jsp").forward(request, response);
	    }// </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="deleteFeedback">
	    else if (sAction.equals("deleteFeedback")) {
		int id = Integer.parseInt(request.getParameter("feedbackId"));
		Feedback f = feedbackSB.getFeedbackById(id);
		feedbackSB.delete(f);
		request.setAttribute("feedbackList", feedbackSB.getFeedbacksByStatus(false));
		request.getRequestDispatcher("admin/feedback-trash.jsp").forward(request, response);
	    }// </editor-fold>
	    // </editor-fold>
	    // <editor-fold defaultstate="collapsed" desc="User process">
	    else if (sAction.equals("Logout")) {
		session.removeAttribute("curUser");
		ut.begin();
		List<Category> cateList = categorySB.getParentCategories();
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
	    } else if (sAction.equals("changerole")) {
		String role = request.getParameter("role");
		request.setAttribute("userList", userSB.getUsers(role, true));
		request.setAttribute("selectedRole", role);
		request.getRequestDispatcher("admin/users-list.jsp").forward(request, response);
	    } else if (sAction.equals("Change Password")) {
		String oldpassword = request.getParameter("oldpassword");
		String password = request.getParameter("confirmpassword");
		Users u = (Users) session.getAttribute("curUser");
		if (!oldpassword.equals(u.getPassword())) {
		    request.setAttribute("message", "Wrong Old Password");
		    request.getRequestDispatcher("admin/fail.jsp").forward(request, response);
		} else {
		    userSB.changePassword(u.getUserId(), password);
		    request.setAttribute("message", "Password Has Been Changed");
		    request.getRequestDispatcher("admin/fail.jsp").forward(request, response);
		}
	    } else if (sAction.equals("users-list")) {
		request.setAttribute("userList", userSB.getUsers("All", true));
		request.getRequestDispatcher("admin/users-list.jsp").forward(request, response);
	    } else if (sAction.equals("users-trash")) {
		request.setAttribute("userTrash", userSB.getUsers("All", false));
		request.getRequestDispatcher("admin/users-trash.jsp").forward(request, response);
	    } else if (sAction.equals("block")) {
		String userId = request.getParameter("userId");
		userSB.Block(Integer.parseInt(userId), false);
		request.setAttribute("userList", userSB.getUsers("All", true));
		request.getRequestDispatcher("admin/users-list.jsp").forward(request, response);
	    } else if (sAction.equals("unblock")) {
		String userId = request.getParameter("userId");
		userSB.Block(Integer.parseInt(userId), true);
		request.setAttribute("userTrash", userSB.getUsers("All", false));
		request.getRequestDispatcher("admin/users-trash.jsp").forward(request, response);
	    } else if (sAction.equals("deleteuser")) {
		String userId = request.getParameter("userId");
		userSB.delete(Integer.parseInt(userId));
		request.setAttribute("userTrash", userSB.getUsers("All", false));
		request.getRequestDispatcher("admin/users-trash.jsp").forward(request, response);
	    } else if (sAction.equals("Update Profile")) {
		Users u = (Users) session.getAttribute("curUser");
		String email = request.getParameter("insertEmail");
		String message = "";
		if (userSB.checkemail(email)) {
		    message += "* Duplicate Email !";
		}
		if (!message.equals("") && !email.equals(u.getEmail())) {
		    request.setAttribute("message", message);
		    request.getRequestDispatcher("admin/fail.jsp").forward(request, response);
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
		    userSB.update(u.getUserId(), img, fullname, birthday, male.equals("Male"), address, phone, email, u.getQuestion(), u.getAnswer(), u.getCategory().getCategoryId(), u.getActiveSMS());
		    session.setAttribute("curUser", userSB.getUser(u.getUserId()));
		    request.setAttribute("message", "Your Profile Has Been Updated");
		    request.getRequestDispatcher("admin/fail.jsp").forward(request, response);
		}
	    } else if (sAction.equals("users-edit")) {
		session.setAttribute("curUserEdit", userSB.getUserById(Integer.parseInt(request.getParameter("userId"))));
		request.setAttribute("cateList", userSB.getCategories());
		request.getRequestDispatcher("admin/users-edit.jsp").forward(request, response);
	    } else if (sAction.equals("updatePage")) {
		response.sendRedirect("admin/profile.jsp");
	    } else if (sAction.equals("users-add")) {
		request.setAttribute("cateList", userSB.getCategories());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		request.setAttribute("curDate", sdf.format(new Date()));
		request.getRequestDispatcher("admin/users-add.jsp").forward(request, response);
	    } else if (sAction.equals("Edit User")) {
		Users u = (Users) session.getAttribute("curUserEdit");
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
		    String question = request.getParameter("insertQuestion");
		    String answer = request.getParameter("insertAnswer");
		    userSB.update(u.getUserId(), img, fullname, birthday, male.equals("Male"), address, phone, email, question, answer, 1, sms);
		    request.setAttribute("userList", userSB.getUsers("All", true));
		    request.getRequestDispatcher("admin/users-list.jsp").forward(request, response);
		}
	    } else if (sAction.equals("Add User")) {
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
		    request.getRequestDispatcher("admin/fail.jsp").forward(request, response);
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
		    String cateId = request.getParameter("insertcateId");
		    userSB.insert(username, password, img, fullname, birthday, male.equals("Male"), address, phone, email, question, answer, "Employee", Integer.parseInt(cateId), sms, true);
		    session.setAttribute("curUser", userSB.getUserByName(username));
		    request.setAttribute("userList", userSB.getUsers("All", true));
		    request.getRequestDispatcher("admin/users-list.jsp").forward(request, response);
		}
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
