<%-- 
    Document   : news-trash
    Created on : Apr 23, 2013, 2:28:57 PM
    Author     : Khatmau_sr
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>News Trash | NewsOnline Admin</title>
	<link rel="stylesheet" type="text/css" href="css/reset.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="css/text.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="css/grid.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="css/layout.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="css/nav.css" media="screen" />
	<!--[if IE 6]><link rel="stylesheet" type="text/css" href="css/ie6.css" media="screen" /><![endif]-->
	<!--[if IE 7]><link rel="stylesheet" type="text/css" href="css/ie.css" media="screen" /><![endif]-->
	<link href="css/table/demo_page.css" rel="stylesheet" type="text/css" />
	<!-- BEGIN: load jquery -->
	<script src="js/jquery-1.6.4.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/jquery-ui/jquery.ui.core.min.js"></script>
	<script src="js/jquery-ui/jquery.ui.widget.min.js" type="text/javascript"></script>
	<script src="js/jquery-ui/jquery.ui.accordion.min.js" type="text/javascript"></script>
	<script src="js/jquery-ui/jquery.effects.core.min.js" type="text/javascript"></script>
	<script src="js/jquery-ui/jquery.effects.slide.min.js" type="text/javascript"></script>
	<script src="js/jquery-ui/jquery.ui.mouse.min.js" type="text/javascript"></script>
	<script src="js/jquery-ui/jquery.ui.sortable.min.js" type="text/javascript"></script>
	<script src="js/table/jquery.dataTables.min.js" type="text/javascript"></script>
	<!-- END: load jquery -->
	<script type="text/javascript" src="js/table/table.js"></script>
	<script src="js/setup.js" type="text/javascript"></script>
	<script type="text/javascript">

	    $(document).ready(function () {
		setupLeftMenu();

		$('.datatable').dataTable();
		setSidebarHeight();


	    });
	</script>
    </head>
    <body>
	<div class="container_12">
	    <div class="grid_12 header-repeat">
		<div id="branding">
		    <div class="floatleft">
			<img src="img/logo.png" alt="Logo" /></div>
		    <div class="floatright">
			<div class="floatleft">
			    <img src="img/img-profile.jpg" alt="Profile Pic" /></div>
			<div class="floatleft marginleft10">
			    <ul class="inline-ul floatleft">
				<li>Hello Admin</li>
				<li><a href="#">Config</a></li>
				<li><a href="#">Logout</a></li>
			    </ul>
			    <br />
			    <span class="small grey">Last Login: 3 hours ago</span>
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
                    <li class="ic-typography"><a href="#"><span>User Manager</span></a></li>
                    <li class="ic-charts"><a href="#"><span>Category</span></a></li>
		    <li class="ic-grid-tables"><a href="#"><span>Comment</span></a></li>
                    <li class="ic-gallery dd"><a href="#"><span>Image Galleries</span></a></li>
                    <li class="ic-notifications"><a href="#"><span>Feedback</span></a></li>

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
                                    <li><a>Update profile</a> </li>
                                    <li><a>Change password</a> </li>
                                    <li><a>Logout</a> </li>
                                </ul>
                            </li>
                            <li><a class="menuitem" style="cursor: default" >Insert</a>
                                <ul class="submenu">
                                    <li><a href="/news-online-war/Admin?action=news-add" >Add News</a> </li>
                                    <li><a>Add Users</a> </li>
                                    <li><a>Add Category</a> </li>
                                </ul>
                            </li>
                            <li><a class="menuitem" style="cursor: default" >Trash</a>
                                <ul class="submenu">
                                    <li><a href="/news-online-war/Admin?action=news-trash">News</a> </li>
                                    <li><a>Users</a> </li>
                                    <li><a>Category</a> </li>
                                    <li><a>Comment</a> </li>
                                    <li><a>Feedback</a> </li>
                                </ul>
                            </li>
                        </ul>
		    </div>
		</div>
	    </div>
	    <div class="grid_10">
		<div class="box round first grid">
		    <h2>
			News Trash</h2>
		    <div class="block">

			<table class="data display datatable" id="example">
			    <thead>
				<tr>
				    <th width="70%">News Title</th>
				    <th width="10%">Posted Date</th>
				    <th width="10%">Edited Date</th>
				    <th width="5%" align="center">Restore</th>
				    <th width="5%">Remove</th>
				</tr>
			    </thead>
			    <tbody>
				<c:forEach items="${newsList}" var="item">
				<tr class="odd gradeX">
				    <td>${item.title}</td>
				    <td><fmt:formatDate pattern="MMM. dd, yyyy" value="${item.postedDate}" /></td>
				    <td><fmt:formatDate pattern="MMM. dd, yyyy" value="${item.editedDate}" /></td>
				    <td align="center">
					<a href="/news-online-war/Admin?action=Restore&newsId=${item.newsId}">
					    <img src="admin/img/up.png" alt="Restore News"/>
					</a>
				    </td>
				    <td align="center">
					<a href="/news-online-war/Admin?action=Delete&newsId=${item.newsId}">
					    <img src="admin/img/cross.png" alt="Delete News"/>
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
		Copyright <a href="/news-online-war/Admin?action=index">NewsOnline Admin</a>. All Rights Reserved.
	    </p>
	</div>
    </body>
</html>

