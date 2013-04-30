<%-- 
    Document   : feedback-trash
    Created on : Apr 26, 2013, 11:49:01 AM
    Author     : Khatmau_sr
--%>

<%@page import="web.entity.Users"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>Feedback Trash</title>
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
	<script type="text/javascript" src="admin/js/jquery-ui/jquery.ui.core.min.js"></script>
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
			Feedback Trash</h2>
		    <div class="block">

			<table class="data display datatable" id="example">
			    <thead>
				<tr>
				    <th width="10%">Name</th>
				    <th width="20%">Email</th>
				    <th width="50%">Question</th>
				    <th style="text-align: center" width="10%">Posted Date</th>
				    <th width="5%">Restore</th>
				    <th width="5%">Delete</th>
				</tr>
			    </thead>
			    <tbody>
				<c:forEach items="${feedbackList}" var="item">
				<tr class="odd gradeX">
				    <td>${item.name}</td>
				    <td>${item.email}</td>
				    <td>${item.question}</td>
				    <td align="center"><fmt:formatDate pattern="dd MMM yyyy" value="${item.postedTime}" /></td>
				    <td align="center">
					<a href="/news-online-war/Admin?action=restoreFeedback&feedbackId=${item.feedbackId}">
					    <img src="admin/img/up.png" alt="Restore Feedback"/>
					</a>
				    </td>
				    <td align="center">
					<a onclick="return confirm('Are you sure you want to delete this feedback?')" href="/news-online-war/Admin?action=deleteFeedback&feedbackId=${item.feedbackId}">
					    <img src="admin/img/cross.png" alt="Delete Feedback"/>
					</a>
				    </td>
				</tr>
				</c:forEach>
			    </tbody>
			</table>



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

