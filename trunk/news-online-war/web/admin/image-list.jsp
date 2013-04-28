<%-- 
    Document   : image-list
    Created on : Apr 23, 2013, 4:30:34 PM
    Author     : Khatmau_sr
--%>

<%@page import="web.entity.Users"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>Image Gallery | NewsOnline Admin</title>
	<link rel="stylesheet" type="text/css" href="admin/css/reset.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="admin/css/text.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="admin/css/grid.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="admin/css/layout.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="admin/css/nav.css" media="screen" />
	<link href="admin/css/gallery.css" rel="stylesheet" type="text/css" />
	<link href="admin/css/facebox.css" rel="stylesheet" type="text/css" />
	<link href="admin/css/tabs.css" rel="stylesheet" type="text/css" />
	<!--[if IE 6]><link rel="stylesheet" type="text/css" href="admin/css/ie6.css" media="screen" /><![endif]-->
	<!--[if IE 7]><link rel="stylesheet" type="text/css" href="admin/css/ie.css" media="screen" /><![endif]-->
	<!-- BEGIN: load jquery -->
	<script src="admin/js/jquery-1.6.4.min.js" type="text/javascript"></script>
	<script src="admin/js/jquery-ui/jquery.ui.core.min.js" type="text/javascript"></script>
	<script src="admin/js/jquery-ui/jquery.ui.widget.min.js" type="text/javascript"></script>
	<script src="admin/js/jquery-ui/jquery.ui.accordion.min.js" type="text/javascript"></script>
	<script src="admin/js/jquery-ui/jquery.effects.core.min.js" type="text/javascript"></script>
	<script src="admin/js/jquery-ui/jquery.effects.slide.min.js" type="text/javascript"></script>
	<!-- END: load jquery -->
	<script src="admin/js/popup/jquery.facebox.js" type="text/javascript"></script>
	<script src="admin/js/quick-sand/jquery.quicksand.js" type="text/javascript"></script>
	<script src="admin/js/setup.js" type="text/javascript"></script>
	<script type="text/javascript">

	    $(document).ready(function () {
		setupGallery();
		setupLeftMenu();
		setSidebarHeight();

	    });
	    function validate(){
		var name = myform.file1.value;

		if(name.length==0){
		    alert("Please select a picture!");
		    return false;
		}
		else{
		    var ext = name.substring(name.lastIndexOf('.') + 1).toUpperCase();
		    if(ext != "GIF" && ext != "JPG" && ext != "PNG"){
			alert(ext + " file is not allowed! Only GIF, PNG, JPG!");
			return false;
		    }
		    else{
			return true;
		    }
		}
	    }
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
		<div class="box round first">

                    <h2>Image Gallery</h2>
                    <div class="block">
			<div class="gallery-sand">
			    <form id="myform" action="/news-online-war/Admin?action=Upload" method="POST" onsubmit="return validate()" enctype="multipart/form-data">
			    <div class="filter-options">
				<input type="file" size="50" id="file1" name="file1" value="" style="border: 1px solid #777777;
				      background: #9E9E96;
				      color: white;
				      font: bold 11px 'Trebuchet MS';
				      padding: 4px;
				      cursor: pointer;
				      -moz-border-radius: 4px;
				      -webkit-border-radius: 4px;"/>
				<button type="submit" class="btn-icon btn-grey btn-plus"><span></span>Upload Image</button>
			    </div>
			    </form>
			    <!-- Big Gallery Sorting: End -->
			    <!-- Small Gallery Content: Start -->
			    <div class="filter-results">
				<ul class="gallery small">
				    <!-- Small Gallery Image: Start -->
				    <c:forEach items="${imageList}" var="item">
				    <li>
					<div class="actions">
					    <a href="/news-online-war/Admin?action=deleteimage&img=${item}" class="delete">delete</a>
					</div>
                                        <img width="111px" height="91" src="admin/img/news/${item}" alt="" />
                                    </li>
				    </c:forEach>
				    <!-- Small Gallery Image: End -->
				</ul>
			    </div>
			    <!-- Small Gallery Content: End -->
			</div>
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
		Copyright <a href="/news-online-war/Admin?action=index">NewsOnline Admin</a>. All Rights Reserved.
	    </p>
	</div>
    </body>
</html>

