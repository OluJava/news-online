<%-- 
    Document   : news-popular
    Created on : Apr 23, 2013, 3:19:17 PM
    Author     : Khatmau_sr
--%>
<%@page import="web.entity.Users"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>News Popular | NewsOnline Admin</title>
	<link rel="stylesheet" type="text/css" href="admin/css/reset.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="admin/css/text.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="admin/css/grid.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="admin/css/layout.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="admin/css/nav.css" media="screen" />
	<!--[if IE 6]><link rel="stylesheet" type="text/css" href="admin/css/ie6.css" media="screen" /><![endif]-->
	<!--[if IE 7]><link rel="stylesheet" type="text/css" href="admin/css/ie.css" media="screen" /><![endif]-->
	<link href="admin/css/table/demo_page.css" rel="stylesheet" type="text/css" />
	<!-- BEGIN: load jquery -->
	<script src="admin/js/jquery-1.6.4.min.js" type="text/javascript"></script>
	<script src="admin/js/jquery-ui/jquery.ui.core.min.js" type="text/javascript"></script>
	<script src="admin/js/jquery-ui/jquery.ui.widget.min.js" type="text/javascript"></script>
	<script src="admin/js/jquery-ui/jquery.ui.accordion.min.js" type="text/javascript"></script>
	<script src="admin/js/jquery-ui/jquery.effects.core.min.js" type="text/javascript"></script>
	<script src="admin/js/jquery-ui/jquery.effects.slide.min.js" type="text/javascript"></script>
	<script src="admin/js/jquery-ui/jquery.ui.mouse.min.js" type="text/javascript"></script>
	<script src="admin/js/jquery-ui/jquery.ui.sortable.min.js" type="text/javascript"></script>
	<script src="admin/js/table/jquery.dataTables.min.js" type="text/javascript"></script>
	<!-- END: load jquery -->
	<script type="text/javascript" src="admin/js/table/table.js"></script>
	<script src="admin/js/setup.js" type="text/javascript"></script>
	<script type="text/javascript">

	    $(document).ready(function () {
		setupLeftMenu();

		$('.datatable').dataTable();
		setSidebarHeight();


	    });
	</script>
    </head>
    <body>
	<%
                    Users u = (Users) session.getAttribute("curUser");
                    String username = u.getUsername();
        %>
	<div class="container_12">
	    <div class="grid_12 header-repeat">
		<div id="branding">
		    <div class="floatleft">
			<img src="admin/img/logo.png" alt="Logo" /></div>
		    <div class="floatright">
			<div class="floatleft">
			    <img src="admin/img/img-profile.jpg" alt="Profile Pic" /></div>
			<div class="floatleft marginleft10">
			    <ul class="inline-ul floatleft">
				<li>Hello <%=username%></li>
				<li><a href="profile.jsp">Config</a></li>
				<li><a href="/news-online-war/Admin?action=Logout">Logout</a></li>
			    </ul>
			    <br />
			    <span class="small grey">Last Login:<%=u.getLastLogin()%></span>
			</div>
		    </div>
		    <div class="clear">
		    </div>
		</div>
	    </div>
	    <div class="clear">
	    </div>
	    <div class="grid_12">
		<ul class="nav main">
                    <li class="ic-form-style"><a href="/news-online-war/Admin?action=news-list"><span>News Manager</span></a></li>
                    <%if(u.getRoles().equals("Admin")){%><li class="ic-typography"><a href="/news-online-war/Admin?action=users-list"><span>User Manager</span></a></li><%}%>
                    <li class="ic-charts"><a href="/news-online-war/Admin?action=category-list"><span>Category</span></a></li>
		    <li class="ic-grid-tables"><a href="table.html"><span>Comment</span></a></li>
                    <li class="ic-gallery dd"><a href="/news-online-war/Admin?action=image-list"><span>Image Galleries</span></a></li>
                    <li class="ic-notifications"><a href="/news-online-war/Admin?action=feedback-list"><span>Feedback</span></a></li>
		    <li class="ic-dashboard"><a href="/news-online-war/Admin?action=news-popular"><span>News Popular</span></a></li>

                </ul>
	    </div>
	    <div class="clear">
	    </div>
	    <div class="grid_2">
		<div class="box sidemenu">
		    <div class="block" id="section-menu">
			<ul class="section menu">
			<li><a class="menuitem" style="cursor: default" >Account</a>
                                <ul class="submenu">
                                    <li><a href="profile.jsp">Update profile</a> </li>
                                    <li><a href="changepassword.jsp">Change password</a> </li>
                                    <li><a href="/news-online-war/Admin?action=Logout">Logout</a> </li>
                                </ul>
                            </li>
                            <li><a class="menuitem" style="cursor: default" >Insert</a>
                                <ul class="submenu">
                                    <li><a href="/news-online-war/Admin?action=news-add" >Add News</a> </li>
                                    <%if(u.getRoles().equals("Admin")){%><li><a href="/news-online-war/Admin?action=users-add">Add Users</a> </li><%}%>
                                    <li><a href="/news-online-war/Admin?action=category-add">Add Category</a> </li>
                                </ul>
                            </li>
                            <li><a class="menuitem" style="cursor: default" >Trash</a>
                                <ul class="submenu">
                                    <li><a href="/news-online-war/Admin?action=news-trash">News</a> </li>
                                    <%if(u.getRoles().equals("Admin")){%><li><a href="/news-online-war/Admin?action=users-trash">Users</a> </li><%}%>
                                    <li><a href="/news-online-war/Admin?action=category-trash">Category</a> </li>
                                    <li><a>Comment</a> </li>
                                    <li><a href="/news-online-war/Admin?action=feedback-trash">Feedback</a> </li>
                                </ul>
                            </li>
                        </ul>
		    </div>
		</div>
	    </div>
	    <div class="grid_10">
		<div class="box round first grid">
		    <h2>
			Popular News</h2>
		    <div class="block">
			<form action="/news-online-war/Admin" method="POST">
			    <table class="form">
				<tr>
				    <td width="150px">The Main News</td>
				    <td class="col2">
					<select style="width: 289px" name="news1">
					    <c:forEach items="${newsList}" var="item">
						<c:choose>
						    <c:when test="${cur_List[0] == item.newsId}">
							<option selected value="${item.newsId}">${item.title}</option>
						    </c:when>
						    <c:otherwise>
							<option value="${item.newsId}">${item.title}</option>
						    </c:otherwise>
						</c:choose>
					    </c:forEach>
					</select>
				    </td>
				</tr>
				<tr>
				    <td>The first News</td>
				    <td>
					<select style="width: 290px" name="news2">
					    <c:forEach items="${newsList}" var="item">
						<c:choose>
						    <c:when test="${cur_List[1] == item.newsId}">
							<option selected value="${item.newsId}">${item.title}</option>
						    </c:when>
						    <c:otherwise>
							<option value="${item.newsId}">${item.title}</option>
						    </c:otherwise>
						</c:choose>
					    </c:forEach>
					</select>
				    </td>
				</tr>
				<tr>
				    <td>The second News</td>
				    <td>
					<select  style="width: 290px" name="news3">
					    <c:forEach items="${newsList}" var="item">
						<c:choose>
						    <c:when test="${cur_List[2] == item.newsId}">
							<option selected value="${item.newsId}">${item.title}</option>
						    </c:when>
						    <c:otherwise>
							<option value="${item.newsId}">${item.title}</option>
						    </c:otherwise>
						</c:choose>
					    </c:forEach>
					</select>
				    </td>
				</tr>
				<tr>
				    <td>The thirth News</td>
				    <td>
					<select style="width: 290px" name="news4">
					    <c:forEach items="${newsList}" var="item">
						<c:choose>
						    <c:when test="${cur_List[3] == item.newsId}">
							<option selected value="${item.newsId}">${item.title}</option>
						    </c:when>
						    <c:otherwise>
							<option value="${item.newsId}">${item.title}</option>
						    </c:otherwise>
						</c:choose>
					    </c:forEach>
					</select>
				    </td>
				</tr>
				<tr>
				    <td></td>
				    <td>
					<div style="margin-top: 25px">
					    <button type="submit" name="action" value="Accept" class="btn-icon btn-grey btn-check"><span></span>Accept</button>
					</div>
				    </td>
				</tr>
			    </table>
			</form>
		    </div>
		</div>
	    </div>
	    <div class="clear">
	    </div>
	</div>
	<div class="clear">
	</div>
	<div id="site_info">
	    <p>
		Copyright <a href="#">NewsOnline Admin</a>. All Rights Reserved.
	    </p>
	</div>
    </body>
</html>

