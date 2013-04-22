<%--
    Document   : index
    Created on : Apr 16, 2013, 6:10:03 PM
    Author     : Khatmau_sr
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"> <!--<![endif]-->

    <head>

        <!-- Your Basic Site Informations -->
        <title>Home | NewsOnline</title>
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

    <noscript><link rel="stylesheet" href="css/no-js.css"></noscript> <!-- If JavaScript Disabled -->

    <!--[if lt IE 9]>
		<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

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
                <li><a href="about-us.jsp">About Us</a></li>
                <li><a href="feedback.jsp">Feedback</a></li>
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

        <nav id="main-navigation" class="clearfix">
            <ul>
		<c:forEach items="${cateList}" var="item">
		    <li><a href="Client?action=viewCategory&cateId=${item.categoryId}">${item.title}</a></li>
		</c:forEach>
            </ul>
        </nav> <!-- End Main-Navigation -->
        <div id="highlight-posts" class="clearfix">
            <ul>
                <li class="masked masked-big">
                    <div class="flexslider highlight-one loading">
                        <ul class="slides"> <!-- Images -->
                            <li><figure style="background-image:url(images/content/full/1.jpg);"></figure></li>
                        </ul>
                    </div>
                    <a href="Client?action=viewDetail&newsId=${headMain.newsId}" title="Title"><div class="masked-hover"></div></a> <!-- Masked Hover -->
                    <div class="masked-base"></div> <!-- Masked Base -->
                    <div class="masked-color masked-violet"></div> <!-- Masked Color -->
                    <div class="text">
                        <h2><c:out value="${headMain.title}"/></h2>
                        <span class="meta">By <c:out value="${headMain.author}"/> on <fmt:formatDate pattern="MMM. dd, yyyy" value="${headMain.postedDate}" /></span>
                        <p><c:out value="${headMain.description}"/> [...]</p>
                    </div>
                </li>

                <li class="masked masked-small"> <!-- Two -->
                    <div class="flexslider highlight-two loading">
                        <ul class="slides"> <!-- Images -->
                            <li><figure style="background-image:url(images/content/600/11.jpg);"></figure></li>
                        </ul>
                    </div>
                    <a href="Client?action=viewDetail&newsId=${headFirst.newsId}" title="Title"><div class="masked-hover"></div></a> <!-- Masked Hover -->
                    <div class="masked-base"></div> <!-- Masked Base -->
                    <div class="masked-color masked-red"></div> <!-- Masked Color -->
                    <div class="text">
                        <h2><c:out value="${headFirst.title}"/></h2>
                        <span class="meta">By <c:out value="${headFirst.author}"/> on <fmt:formatDate pattern="MMM. dd, yyyy" value="${headFirst.postedDate}" /></span>
                    </div>
                </li>

                <li class="masked masked-small"> <!-- Three -->
                    <div class="flexslider highlight-three loading">
                        <ul class="slides"> <!-- Images -->
                            <li><figure style="background-image:url(images/content/600/3.jpg);"></figure></li>
                        </ul>
                    </div>
                    <a href="Client?action=viewDetail&newsId=${headSecond.newsId}" title="Title"><div class="masked-hover"></div></a> <!-- Masked Hover -->
                    <div class="masked-base"></div> <!-- Masked Base -->
                    <div class="masked-color masked-blue"></div> <!-- Masked Color -->
                    <div class="text">
                        <h2><c:out value="${headSecond.title}"/></h2>
                        <span class="meta">By <c:out value="${headSecond.author}"/> on <fmt:formatDate pattern="MMM. dd, yyyy" value="${headSecond.postedDate}" /></span>
                    </div>
                </li>

                <li class="masked masked-small no-margin-bottom"> <!-- Four -->
                    <div class="flexslider highlight-four loading">
                        <ul class="slides"> <!-- Images -->
                            <li><figure style="background-image:url(images/content/600/8.jpg);"></figure></li>
                        </ul>
                    </div>
                    <a href="Client?action=viewDetail&newsId=${headThirth.newsId}" title="#"><div class="masked-hover"></div></a> <!-- Masked Hover -->
                    <div class="masked-base"></div> <!-- Masked Base -->
                    <div class="masked-color masked-orange"></div> <!-- Masked Color -->
                    <div class="text">
                        <h2><c:out value="${headThirth.title}"/></h2>
                        <span class="meta">By <c:out value="${headThirth.author}"/> on <fmt:formatDate pattern="MMM. dd, yyyy" value="${headThirth.postedDate}" /></span>
                    </div>
                </li>

            </ul>
        </div> <!-- End Highlight Posts -->

        <div class="headlines clearfix">
            <span class="base">30<i>Tue</i></span>
            <div class="text-rotator">
		<c:forEach items="${lineList}" var="item">
		    <div><a href="Client?action=viewDetail&newsId=${item.newsId}">${item.title}</a></div>
		</c:forEach>
            </div>
        </div> <!-- End Headlines -->

        <div class="row-fluid">
            <div id="main" class="span8 image-preloader">

                <div class="row-fluid">
		    <c:set var="index" value="${0}"/>
		    <c:forEach items="${newsList}" var="list">
			<c:if test="${not empty list}">
			    <c:choose>
				<c:when test="${index % 2 == 0}">
				    <div class="span6 post no-margin-left">
				    </c:when>
				    <c:otherwise>
					<div class="span6 post">
					</c:otherwise>
				    </c:choose>
				    <figure>
					<img src="images/content/600/1.jpg" alt="#" />
					<div class="cat-name">
					    <span class="base">
						<a style="color: #FFF" href="Client?action=viewCategory&cateId=${list[0].category.categoryId}">${list[0].category.title}</a>
					    </span>
					    <span class="arrow"></span>
					</div>
				    </figure>
				    <div class="text" style="height: 175px">
					<h2><a href="Client?action=viewDetail&newsId=${list[0].newsId}">${list[0].title}</a></h2>
					<ul class="list-arrow-bold" style="margin-left: 10px">
					    <c:forEach items="${list}" var="item" varStatus="loop">
						<c:if test="${loop.index > 0 && loop.index < 5}">
						    <li style="font-size: 13px; line-height:1.5em; font-weight: bold"><a href="Client?action=viewDetail&newsId=${item.newsId}">${item.title}</a></li>
						</c:if>
					    </c:forEach>
					</ul>
				    </div>
				</div>
				<c:set var="index" value="${index+1}"/>
			    </c:if>
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
						<figure class="pull-left"><img src="images/content/300/5.jpg" alt="" /></figure>
						<div class="pull-right content">
						    <h4><a href="Client?action=viewDetail&newsId=${item.newsId}" title="">${item.title}</a></h4>
						    <p class="meta">${item.viewed} views&nbsp;&nbsp;|&nbsp;&nbsp;49 comments</p>
						</div>
					    </div>
					</c:if>
				    </c:forEach>
				</div> <!-- End Populars -->

				<div class="tab-pane" id="tab-recents">

				    <c:forEach items="${recentList}" var="item" varStatus="loop">
					<c:if test="${loop.index < 6}">
					    <div class="item">
						<figure class="pull-left"><img src="images/content/300/2.jpg" alt="" /></figure>
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
