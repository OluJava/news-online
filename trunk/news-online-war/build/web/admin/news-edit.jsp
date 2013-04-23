<%-- 
    Document   : news-edit
    Created on : Apr 22, 2013, 6:48:27 PM
    Author     : Khatmau_sr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>Edit News</title>
        <link rel="stylesheet" type="text/css" href="admin/css/reset.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="admin/css/text.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="admin/css/grid.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="admin/css/layout.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="admin/css/nav.css" media="screen" />
        <!--[if IE 6]><link rel="stylesheet" type="text/css" href="admin/css/ie6.css" media="screen" /><![endif]-->
        <!--[if IE 7]><link rel="stylesheet" type="text/css" href="admin/css/ie.css" media="screen" /><![endif]-->
        <!-- BEGIN: load jquery -->
        <script type="text/javascript" src="admin/ckeditor/ckeditor.js"></script>
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
	    function trim (str) {
		str = str.replace(/^\s+/, '');
		for (var i = str.length - 1; i >= 0; i--) {
		    if (/\S/.test(str.charAt(i))) {
			str = str.substring(0, i + 1);
			break;
		    }
		}
		return str;
	    }
	    function validateTitle() {
		if(trim(myform.title.value)==0)
		{
		    document.getElementById('errTitle').innerHTML="This is a required field.";
		    document.getElementById('title').className="medium error";
		}
		else
		{
		    document.getElementById('errTitle').innerHTML="";
		    document.getElementById('title').className="medium";
		}
		if(trim(myform.title.value).length < 20)
		{
		    document.getElementById('errTitle').innerHTML="At least 20 words.";
		    document.getElementById('title').className="medium error";
		}
		else
		{
		    document.getElementById('errTitle').innerHTML="";
		    document.getElementById('title').className="medium";
		}
	    }
	    function validateAuthor() {
		if(trim(myform.author.value)==0)
		{
		    document.getElementById('errAuthor').innerHTML="This is a required field.";
		    document.getElementById('author').className="mini error";
		}
		else
		{
		    document.getElementById('errAuthor').innerHTML="";
		    document.getElementById('author').className="mini";
		}
		if(trim(myform.author.value).length < 5)
		{
		    document.getElementById('errAuthor').innerHTML="At least 5 words.";
		    document.getElementById('author').className="mini error";
		}
		else
		{
		    document.getElementById('errAuthor').innerHTML="";
		    document.getElementById('author').className="mini";
		}
	    }
	    function validateDesc() {
		if(trim(myform.desc.value)==0)
		{
		    document.getElementById('errDesc').innerHTML="This is a required field.";
		}
		else
		{
		    document.getElementById('errDesc').innerHTML="";
		}
		if(trim(myform.desc.value).length < 50)
		{
		    document.getElementById('errDesc').innerHTML="At least 50 words.";
		}
		else
		{
		    document.getElementById('errDesc').innerHTML="";
		}
	    }
	    function validateCont() {
		if(trim(myform.cont.value)==0)
		{
		    document.getElementById('errCont').innerHTML="This is a required field.";
		}
		else
		{
		    document.getElementById('errCont').innerHTML="";
		}
		if(trim(myform.cont.value).length < 200)
		{
		    document.getElementById('errCont').innerHTML="At least 200 words.";
		}
		else
		{
		    document.getElementById('errCont').innerHTML="";
		}
	    }
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
                    <li class="ic-form-style"><a href="/news-online-war/Admin?action=newsManager"><span>News Manager</span></a></li>
                    <li class="ic-typography"><a href="#"><span>User Manager</span></a></li>
                    <li class="ic-charts"><a href="#"><span>Category</span></a></li>
		    <li class="ic-grid-tables"><a href="table.html"><span>Comment</span></a></li>
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
                                    <li><a href="news-add.jsp" >Add News</a> </li>
                                    <li><a>Add Users</a> </li>
                                    <li><a>Add Category</a> </li>
                                </ul>
                            </li>
                            <li><a class="menuitem" style="cursor: default" >Trash</a>
                                <ul class="submenu">
                                    <li><a href="/news-online-war/Admin?action=newsTrash">News</a> </li>
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
                        Edit News</h2>
                    <div class="block ">
                        <form name="myform" action="/news-online-war/Admin" method="POST">
                            <table class="form">
				<tr>
				    <td width="150px">
					<label>
					    Title</label>
				    </td>
				    <td width="500px">
					<input type="hidden" name="newsId" value="${news.newsId}"/>
					<input type="text" id="title" name="title" class="medium" maxlength="100" value="${news.title}" onblur="validateTitle()"/>
					
				    </td>
				    <td><span id="errTitle" class="error"></span></td>
				</tr>
				<tr>
				    <td>
					<label>
					    Author</label>
				    </td>
				    <td>
					<input type="text" id="author" name="author" class="mini" maxlength="30" value="${news.author}" onblur="validateAuthor()"/>
					
				    </td>
				    <td><span id="errAuthor" class="error"></span></td>
				</tr>
				<tr>
				    <td>
					<label>
					    Image</label>
				    </td>
				    <td>
					<input type="text" name="image" readonly="true" class="mini" value="${news.image}"/>
					<button class="btn btn-small btn-grey">Browse</button>
				    </td>
				</tr>
				<tr>
				    <td style="vertical-align: middle; padding-top: 9px;">
					<label>
					    Preview</label>
				    </td>
				    <td>
					<img src="admin/img/no-user.gif" alt="" width="240px" height="180px"/>
				    </td>
				</tr>
				<tr>
				    <td style="vertical-align: top; padding-top: 9px;">
					<label>
					    Description</label>
				    </td>
				    <td>
					<textarea cols="" rows=""  style="width:450px;height:65px; resize: none;padding: 4px 4px 5px 4px;
						  border-top: 1px solid #b3b3b3;border-left: 1px solid #b3b3b3;
						  border-right: 1px solid #eaeaea;border-bottom: 1px solid #eaeaea;
						  font-family:Helvetica Neue, Arial" maxlength="200" id="desc" name="description" onblur="validateDesc()">
${news.description}
					</textarea>
				    </td>
				    <td style="vertical-align: top; padding-top: 9px;"><span id="errDesc" class="error"></span></td>
				</tr>
				<tr>
				    <td style="vertical-align: top; padding-top: 9px;">
					<label>
					    Content</label>
				    </td>
				    <td colspan="2">
					<div style="width: 750px">
					    <textarea class="ckeditor" id="cont" name="content" onblur="validateCont()">
					    ${news.content}
					    </textarea>
					    <button style="float: right; margin-top: 5px" class="btn btn-small btn-grey">Insert Image</button>
					</div>
				    </td>
				</tr>
				<tr>
				    <td>
					<label>
					    Tags</label>
				    </td>
				    <td>
					<input type="text" name="tags" class="mini" maxlength="50" value="${news.tags}"/> (Optional)
				    </td>
				</tr>
				<tr>
				    <td></td>
				    <td>
					<div style="margin-top: 35px">
					    <button type="submit" name="action" value="Save" class="btn-icon btn-grey btn-check"><span></span>Save</button>
					    <button type="submit" name="action" value="Reset" class="btn-mini btn-black btn-arrow-left"><span></span>Back</button>
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
                Copyright <a href="/news-online-war/Admin?action=goHome">NewsOnline Admin</a>. All Rights Reserved.
            </p>
        </div>
    </body>
</html>

