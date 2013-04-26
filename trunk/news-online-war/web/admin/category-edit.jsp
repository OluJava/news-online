<%--
    Document   : category-edit
    Created on : Apr 26, 2013, 8:48:44 AM
    Author     : Khatmau_sr
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>Edit Category | NewsOnline Admin</title>
        <link rel="stylesheet" type="text/css" href="admin/css/reset.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="admin/css/text.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="admin/css/grid.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="admin/css/layout.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="admin/css/nav.css" media="screen" />
        <!--[if IE 6]><link rel="stylesheet" type="text/css" href="admin/css/ie6.css" media="screen" /><![endif]-->
        <!--[if IE 7]><link rel="stylesheet" type="text/css" href="admin/css/ie.css" media="screen" /><![endif]-->
        <!-- BEGIN: load jquery -->
        <script type="text/javascript" src="ckeditor/ckeditor.js"></script>
        <script type="text/javascript" src="admin/js/jquery-1.6.4.min.js" ></script>
        <script type="text/javascript" src="admin/js/jquery-ui/jquery.ui.core.min.js"></script>
        <script src="admin/js/jquery-ui/jquery.ui.widget.min.js" type="text/javascript"></script>
        <script src="admin/js/jquery-ui/jquery.ui.accordion.min.js" type="text/javascript"></script>
        <script src="admin/js/jquery-ui/jquery.effects.core.min.js" type="text/javascript"></script>
        <script src="admin/js/jquery-ui/jquery.effects.slide.min.js" type="text/javascript"></script>
        <!-- END: load jquery -->
        <!-- BEGIN: load jqplot -->
        <link rel="stylesheet" type="text/css" href="admin/css/jquery.jqplot.min.css" />
        <!--[if lt IE 9]><script language="javascript" type="text/javascript" src="admin/js/jqPlot/excanvas.min.js"></script><![endif]-->
        <script language="javascript" type="text/javascript" src="admin/js/jqPlot/jquery.jqplot.min.js"></script>
        <script type="text/javascript" src="admin/js/jqPlot/plugins/jqplot.canvasTextRenderer.min.js"></script>
        <script type="text/javascript" src="admin/js/jqPlot/plugins/jqplot.canvasAxisLabelRenderer.min.js"></script>
        <script language="javascript" type="text/javascript" src="admin/js/jqPlot/plugins/jqplot.barRenderer.min.js"></script>
        <script language="javascript" type="text/javascript" src="admin/js/jqPlot/plugins/jqplot.pieRenderer.min.js"></script>
        <script language="javascript" type="text/javascript" src="admin/js/jqPlot/plugins/jqplot.categoryAxisRenderer.min.js"></script>
        <script language="javascript" type="text/javascript" src="admin/js/jqPlot/plugins/jqplot.highlighter.min.js"></script>
        <script language="javascript" type="text/javascript" src="admin/js/jqPlot/plugins/jqplot.pointLabels.min.js"></script>
        <script type="text/javascript" src="admin/js/jqPlot/plugins/jqplot.donutRenderer.min.js"></script>
        <script type="text/javascript" src="admin/js/jqPlot/plugins/jqplot.bubbleRenderer.min.js"></script>
        <!-- END: load jqplot -->
        <script src="admin/js/setup.js" type="text/javascript"></script>
        <script type="text/javascript">

            $(document).ready(function () {
                setupLeftMenu();

                setSidebarHeight();
            });
        </script>
    </head>
    <body>
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
                    <li class="ic-form-style"><a href="#"><span>News Manager</span></a></li>
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
                            <li><a class="menuitem" style="cursor: default" >Insert Record</a>
                                <ul class="submenu">
                                    <li><a>News</a> </li>
                                    <li><a>Users</a> </li>
                                    <li><a>Category</a> </li>
                                </ul>
                            </li>
                            <li><a class="menuitem" style="cursor: default" >Restore</a>
                                <ul class="submenu">
                                    <li><a>News</a> </li>
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
                <div class="box round first">
                    <h2>
                        Insert Category</h2>
                    <div class="block ">
                        <form action="/news-online-war/Admin?action=editCategory&cateId=${category.categoryId}" method="POST">
                            <table class="form">
				<tr>
				    <td width="150px">
					<label>
					    Title</label>
				    </td>
				    <td class="col2">
					<input type="text" name="title" value="${category.title}" class="mini" maxlength="50"/>
				    </td>
				</tr>
				<tr>
				    <td>
					<label>
					    Parent</label>
				    </td>
				    <td>
					<select style="margin-top: 20px" id="select" name="parent">
					    <option value="None">None parent</option>
					    <c:forEach items="${cateList}" var="item">
						<c:set var="id">${item.categoryId}</c:set>
						<c:if test="${category.parent eq id}">
						    <option selected value="${item.categoryId}">${item.title}</option>
						</c:if>
						<c:if test="${category.parent ne id}">
						    <option value="${item.categoryId}">${item.title}</option>
						</c:if>
					    </c:forEach>
					</select>
				    </td>
				</tr>
				<tr>
				    <td></td>
				    <td>
					<div style="margin-top: 25px">
					    <button class="btn-icon btn-grey btn-check"><span></span>Save</button>
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

