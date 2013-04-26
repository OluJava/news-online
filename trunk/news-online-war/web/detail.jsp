<%-- 
    Document   : detail
    Created on : Apr 22, 2013, 2:06:39 PM
    Author     : Khatmau_sr
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

    <head>

	<!-- Your Basic Site Informations -->
	<title>${news.title}</title>
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
	<script type='text/javascript' src='js/jquery.isotope.min.js'></script>
	<script type='text/javascript' src='js/jquery.hoverdir.js'></script>
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
		<div id="main" class="span8 single single-post image-preloader">

		    <div class="row-fluid">

			<div class="breadcrumb clearfix">
			    <span class="base">You are here</span>
			    <p><a href="Client?action=goHome">Home</a>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;<a href="Client?action=viewCategory&cateId=${news.category.categoryId}" title="View articles in ${news.category.title}">${news.category.title}</a>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;${news.title}</p>
			</div> <!-- End Breadcrumb -->

			<figure class="head-section">
			    <img src="admin/img/news/${news.image}" alt="${news.title}" />
			    <div class="head-section-content">
				<h5>By ${news.author}</h5>
				<h1>${news.title}</h1>
				<p class="meta"><fmt:formatDate pattern="MMM. dd, yyyy" value="${news.postedDate}" />&nbsp;&nbsp;|&nbsp;&nbsp;<a href="Client?action=viewCategory&cateId=${news.category.categoryId}" title="View all posts in ${news.category.title}">${news.category.title}</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#comments" title="View all comments">${fn:length(item.commentCollection)} comments</a></p>
			    </div>
			</figure>

			<div class="content">

			    <p>${news.content}</p>

			    <div class="tags">
				<strong>Tags:</strong>&nbsp;
				<c:forEach items="${tagList}" var="item">
				    <a href="Client?action=search&search=${item}" title="View posts in ${item}">${item}</a>
				</c:forEach>
			    </div> <!-- End Tags -->

			</div> <!-- End Content -->

			<div class="sep-border no-margin-bottom"></div> <!-- Separator --><div style="margin-bottom: 25px"></div>

			<div class="related-posts">
			    <h3>Related Posts</h3>

			    <ul class="list-arrow-bold" style="margin-left: 25px">
				<c:forEach items="${relatedList}" var="item">
				    <li style="font-size: 13px; line-height:1.5em;">
					<a href="Client?action=viewDetail&newsId=${item.newsId}">${item.title}</a>
				    </li>
				</c:forEach>
			    </ul>
			</div> <!-- End Related-Posts -->

			<div class="sep-border"></div> <!-- Separator -->

			<div id="comments">

			    <div class="comment-lists">

				<ul>
				    <li> <!-- One -->
				    <figure><img src="images/content/avatar/1.jpg" alt="Avatar 1" /></figure>
				    <div class="content">
					<h5><a href="#">Amah Syner Holland</a></h5>
					<p class="meta">on Sep 12th, 2012 at 3:05 PM</p>
					<span class="comment-id">1</span>
					<p class="text">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum semper nulla vitae diam lobortis interdum varius arcu tincidunt. Quisque sed nisi vel lorem blandit pharetra.</p>
				    </div>
				    </li>
				    <li> <!-- Two -->
				    <figure><img src="images/content/avatar/2.jpg" alt="Avatar 2" /></figure>
				    <div class="content">
					<h5><a href="#">Mugiwara Kiwolen</a></h5>
					<p class="meta">on Sep 12th, 2012 at 3:05 PM</p>
					<span class="comment-id">2</span>
					<p class="text">Vivamus mollis blandit elit, nec lobortis tellus laoreet id. Integer sodales, lorem eu pellentesque scelerisque, urna orci lobortis mauris, sed facilisis mi est eu enim.</p>
				    </div>
				    </li>
				    <li> <!-- Eight -->
				    <figure><img src="images/content/avatar/4.jpg" alt="Avatar 8" /></figure>
				    <div class="content">
					<h5>Rendy Jagerjack</h5>
					<p class="meta">on Sep 12th, 2012 at 3:05 PM</p>
					<span class="comment-id">3</span>
					<p class="text">Pellentesque sed eros sit amet eros congue dictum. Nullam fringilla adipiscing placerat. Mauris feugiat elit et nisi dapibus sodales. Aenean pulvinar odio non sapien tincidunt pellentesque. Donec ac elit ut mi suscipit mattis. In hac habitasse platea dictumst. Fusce nunc lectus, condimentum id interdum quis, ullamcorper posuere nulla.</p>
				    </div>
				    </li>
				</ul>
			    </div> <!-- End Comment-Lists -->

			    <div class="form-comment">
				<h4>Leave a Comment</h4>
				<textarea name="message"></textarea>
				<input type="submit" name="submit" value="Submit Comment" />
				</form>
			    </div> <!-- End Form-Comment -->
			</div> <!-- End Comments -->

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
						<figure class="pull-left"><img src="admin/img/news/${item.image}" alt="" /></figure>
						<div class="pull-right content">
						    <h4><a href="Client?action=viewDetail&newsId=${item.newsId}" title="">${item.title}</a></h4>
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
						<figure class="pull-left"><img src="admin/img/news/${item.image}" alt="" /></figure>
						<div class="pull-right content">
						    <h4><a href="Client?action=viewDetail&newsId=${item.newsId}" title="">${item.title}</a></h4>
						    <p class="meta">In <a href="#">${item.category.title}</a> on <fmt:formatDate pattern="MMM. dd, yyyy" value="${item.postedDate}" /></p>
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
				<form id="enews-contact-form" method="post" action="#">
				    <table style="margin-left: 5px">
					<tr>
					    <td><input type="text" name="n" maxlength="20" placeholder="Username"/></td>
					</tr>
					<tr>
					    <td><input type="password" style="width: 206px; height: 35px;
						       -webkit-border-radius: 0px;-moz-border-radius: 0px;
						       -border-radius: 0px;border:1px solid #DBDBDB;
						       font-size: 15px" name="p" maxlength="20" placeholder="Password"/></td>
					</tr>
					<tr>
					    <td>
						<input type="submit" name="submit" value="Submit" class="btn btn-small" />
						<input type="submit" name="submit" value="Register" class="btn btn-small" />
					    </td>
					</tr>
				    </table>
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
