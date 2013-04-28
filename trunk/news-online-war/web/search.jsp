<%-- 
    Document   : search
    Created on : Apr 22, 2013, 3:06:13 PM
    Author     : Khatmau_sr
--%>
<%@page import="web.entity.Users"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="/WEB-INF/tlds/myTag" prefix="mt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en"> 

    <head>

	<!-- Your Basic Site Informations -->
	<title>${search}</title>
	<meta http-equiv="content-type" content="text/html;charset=utf-8" />
	<meta name="description" content="Enews is a news or magazine site template that built with very cool responsive template with clean design, fast load, seo friendly, beauty color and a slew of features." />
	<meta name="keywords" content="Site Template, News, Magazine, Portofolio, HTML, CSS, jQuery, Newsletter, PHP Contact, Subscription, Responsive, Marketing, Clean, SEO" />
	<meta name="author" content="Dots Theme" />

	<!-- Mobile Specific Meta -->
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

	<!-- Stylesheets -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/bootstrap-responsive.min.css">
	<link rel="stylesheet" href="css/flexslider.css">
	<link rel="stylesheet" href="css/prettyPhoto.css">
	<link rel="stylesheet" href="css/style.css">
	<link href="css/Oswald.css" rel='stylesheet' type='text/css'>
	<link href="css/Bitter.css" rel='stylesheet' type='text/css'>
	<link href="css/Open_Sans.css" rel='stylesheet' type='text/css'>

	<!-- Favicons -->
	<link rel="shortcut icon" href="images/favicon.ico">
	<link rel="apple-touch-icon" href="images/apple-touch-icon.png">
	<link rel="apple-touch-icon" sizes="72x72" href="images/apple-touch-icon-72x72.png">
	<link rel="apple-touch-icon" sizes="114x114" href="images/apple-touch-icon-114x114.png">

	<!-- JavaScript -->
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type='text/javascript' src='js/bootstrap.min.js'></script>
	<script type='text/javascript' src='js/jquery.easing.js'></script>
	<script type='text/javascript' src='js/jquery.flexslider-min.js'></script>
	<script type='text/javascript' src='js/jflickrfeed.min.js'></script>
	<script type='text/javascript' src='js/jquery.fitvids.min.js'></script>
	<script type='text/javascript' src='js/jquery.lazyload.mini.js'></script>
	<script type='text/javascript' src='js/jquery.prettyPhoto.js'></script>
	<script type='text/javascript' src='js/jquery.placeholder.min.js'></script>
	<script type='text/javascript' src='js/jquery.jticker.js'></script>
	<script type='text/javascript' src='js/jquery.mobilemenu.js'></script>
	<script type='text/javascript' src='js/jquery.hoverdir.js'></script>
	<script type='text/javascript' src='js/jquery.isotope.min.js'></script>
	<script type='text/javascript' src='js/modernizr.custom.js'></script>
	<script type="text/javascript" src="js/main.js"></script>

    </head>

    <body>

	<div id="top-navigation">
	    <div class="container">

		<!-- Navigation -->
		<ul class="nav-menu pull-left">
		    <li class="active"><a href="Client?action=goHome">Home</a></li>
		    <li><a href="Client?action=aboutus">About Us</a></li>
		    <li><a href="Client?action=support">Support</a></li>
		</ul>

		<!-- Search Form -->
		<form name="form-search" method="post" action="Client?action=search" class="form-search pull-right">
		    <input type="text" name="search" placeholder="Search...." class="input-icon input-icon-search" />
		</form>

	    </div> <!-- End Container -->
	</div> <!-- End Top-Navigation -->

	<div class="container">
	    <header id="header" class="clearfix">

		<!-- Logo -->
		<div class="logo pull-left">
		    <a href="Client?action=goHome"><img src="images/logo.png" alt="Enews" /></a>
		</div>

		<!-- Ads -->
		<div class="ads pull-right">
		    <img src="images/ads/480x80.png" alt="Ads" />
		</div>

	    </header> <!-- End Header -->

	    <nav id="main-navigation" class="clearfix margin-bottom40">
		<ul>
		    <c:forEach items="${cateList}" var="item" varStatus="loop">
			<c:if test="${loop.index <= 8}">
			    <li><a href="Client?action=viewCategory&cateId=${item.categoryId}">${item.title}<mt:catemenu cateId="${item.categoryId}"/></a>
				<mt:subcates cateId="${item.categoryId}"/>
			    </li>
			</c:if>
			<c:if test="${loop.index == 9}">
			    <li><a href="Client?action=viewCategories">More</a></li>
			</c:if>
		    </c:forEach>
		</ul>
	    </nav> <!-- End Main-Navigation -->

	    <div class="row-fluid">
		<div id="main" class="span8 blog-posts search-page image-preloader">

		    <div class="row-fluid">

			<!-- Search Results -->
			<h2>Search Results for “<i>${search}</i>”</h2>
			<form name="form-search" method="post" action="Client">
			    <input type="text" name="search" value="${search}" />
			    <input type="submit" name="action" value="Search" />
			</form>
			<p class="search-info">Found <i>${fn:length(resultList)}</i> results</p>

			<div class="sep-border margin-bottom20"></div> <!-- Separator -->
			<c:forEach items="${resultList}" var="item">
			    <!-- One -->
			    <div class="post clearfix">
				<figure>
				    <img src="admin/img/news/${item.image}" alt="${item.title}" />
				    <div class="cat-name">
					<span class="base">${item.category.title}</span> <!-- Category Name -->
				    </div>
				</figure>
				<div class="content">
				    <h2><a href="Client?action=viewDetail&newsId=${item.newsId}" title="View permalink ${item.title}">${item.title}</a></h2>
				    <div style="width: 460px"><p>${item.description}</p></div>
				</div>
				<div class="meta">
				    <span style="color: black" class="pull-left">By ${item.author} - <fmt:formatDate pattern="MMM. dd, yyyy" value="${item.postedDate}" /></span>
				    <span class="pull-right"><a href="Client?action=viewDetail&newsId=${item.newsId}">Readmore...</a></span>
				</div>
			    </div>
			</c:forEach>

		    </div> <!-- End Row-Fluid -->
		</div> <!-- End Main -->

		<div id="sidebar" class="span4">

			<div class="widget clearfix">
			    <div class="enews-tab">

				<!-- Tab Menu -->
				<ul class="nav nav-tabs" id="enewsTabs">
				    <li class="active"><a href="#tab-populars" data-toggle="tab">Populars</a></li>
				    <li><a href="#tab-recents" data-toggle="tab">Recents</a></li>
				    <li><a href="#tab-comments" data-toggle="tab">Comments</a></li>
				</ul>

				<div class="tab-content">
				    <div class="tab-pane active" id="tab-populars">

					<c:forEach items="${popularList}" var="item" varStatus="loop">
					    <c:if test="${loop.index < 6}">
						<div class="item">
						    <figure class="pull-left"><img src="admin/img/news/${item.image}" alt="${item.title}" /></figure>
						    <div class="pull-right content">
							<h4><a href="Client?action=viewDetail&newsId=${item.newsId}" title="${item.title}">${item.title}</a></h4>
							<p class="meta">${item.viewed} views&nbsp;&nbsp;|&nbsp;&nbsp;${fn:length(item.commentCollection)} comments</p>
						    </div>
						</div>
					    </c:if>
					</c:forEach>

				    </div> <!-- End Populars -->

				    <div class="tab-pane" id="tab-recents">

					<c:forEach items="${recentList}" var="item" varStatus="loop">
					    <c:if test="${loop.index < 6}">
						<div class="item">
						    <figure class="pull-left"><img src="admin/img/news/${item.image}" alt="${item.title}" /></figure>
						    <div class="pull-right content">
							<h4><a href="Client?action=viewDetail&newsId=${item.newsId}" title="${item.title}">${item.title}</a></h4>
							<p class="meta">On <fmt:formatDate pattern="MMM. dd, yyyy" value="${item.postedDate}" /></p>
						    </div>
						</div>
					    </c:if>
					</c:forEach>
				    </div> <!-- End Recents -->

				    <div class="tab-pane" id="tab-comments">

					<!-- One -->
					<div class="item">
					    <figure class="pull-left"><img src="images/content/avatar/1.jpg" alt="Avatar 1" /></figure>
					    <div class="pull-right content">
						<p><a href="#">mdkiwol</a> on <a href="single_post.html" title="View comment on Glass House Below The Dark of Moon Light">Glass House Below The Dark of Moon Light</a></p>
					    </div>
					</div>

				    </div> <!-- End Comments -->
				</div> <!-- End Tab-Content -->

			    </div> <!-- End Enews-Tab -->
			</div> <!-- End Widget -->

			<div class="widget clearfix">
                            <div class="contact-details">
                                <div class="header">
                                    <h4>Login Form</h4>
                                </div>
                                <div class="content">
                                    <form method="post" action="Client" name="loginform">
                                        <%
                                                    Users u = (Users) session.getAttribute("curUser");
                                                    if (u == null) {
                                        %>
                                        <table style="margin-left: 5px">
                                            <tr>
                                                <td><input type="text" name="username" maxlength="20" placeholder="Username" id="lgusername"/></td>
                                            </tr>
                                            <tr>
                                                <td><input type="password" name="password" maxlength="20" placeholder="Password" id="lgpassword" style="width: 206px;
							   height: 35px;-webkit-border-radius: 0px;-moz-border-radius: 0px;
							   -border-radius: 0px;border:1px solid #DBDBDB; font-size: 15px"/></td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="submit" name="action" value="Submit" class="btn btn-small" onclick="return validate();" />
                                                    <input type="submit" name="action" value="Register" class="btn btn-small" /><br>
                                                    <label><b><a href="recoverpassword.jsp">I Forgot My Password !</a></b></label>
                                                </td>
                                            </tr>
                                        </table>
                                        <%} else {
                                                                                            String username = u.getUsername();
                                                                                            String lastLogin = (String) session.getAttribute("lastLogin");

                                        %>
                                        <table style="margin-left: 5px" cellpadding="1" >
                                            <%
                                                                                                if (u.getRoles().equals("User")) {
                                            %>
                                            <tr>
                                                <td><b><a href="profile.jsp"><%=username%></a></b></td>
                                                <td><a href="Client?action=Logout"><b>Logout</b></a></td>
                                            </tr>
                                            <tr>
                                                <td><b><%if (lastLogin != null) {%>Last Login : <%=lastLogin%><%}%><br></b></td><td></td>
                                            </tr>
                                            <tr><td><a href="changepassword.jsp"><b>Change Password</b></a></td><td></td></tr>
                                            <%} else {%>
                                            <tr>
                                                <td><b><a href="Client?action=goAdmin"><%=username%></a></b></td>
                                                <td><a href="Client?action=Logout"><b>Logout</b></a></td>
                                            </tr>
                                            <tr>
                                                <td><b><%if (lastLogin != null) {%>Last Login : <%=lastLogin%><%}%><br></b></td><td></td>
                                            </tr>
                                            <%}%>
                                        </table>

                                        <%}%>
                                    </form>
                                </div>
                            </div>
                        </div> <!-- End Widget -->

			<div class="widget clearfix">
			    <div class="sponsors">

				<div class="header">
				    <h4>Sponsors</h4>
				</div>

				<div class="content">
				    <img src="images/ads/180x180.png" alt="Sponsor 1" />
				    <img src="images/ads/180x180.png" alt="Sponsor 2" />
				    <img src="images/ads/180x180.png" alt="Sponsor 3" />
				    <img src="images/ads/180x180.png" alt="Sponsor 4" />
				</div>

			    </div>
			</div> <!-- End Widget -->

		    </div> <!-- End Sidebar -->

	    </div> <!-- End Row-Fluid -->
	</div> <!-- End Container -->

	<div style="margin-top: 35px" ></div>
	<div id="footer">
	    <div class="container">

		<p class="pull-left">Copyright 2013 News Online&nbsp;&nbsp;|&nbsp;&nbsp;All Rights Reserved&nbsp;&nbsp;|&nbsp;&nbsp;FPT-Aptech</p>

	    </div> <!-- End Container -->
	</div> <!-- End Footer -->

	<a href="#" class="scrollup" title="Back to Top!">Scroll</a>

    </body>
</html>
