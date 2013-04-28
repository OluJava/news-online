<%-- 
    Document   : index
    Created on : Apr 22, 2013, 5:25:13 PM
    Author     : Khatmau_sr
--%>

<%@page import="web.entity.Category"%>
<%@page import="java.util.List"%>
<%@page import="web.entity.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>NewsOnline Admin</title>
	<link rel="stylesheet" type="text/css" href="css/reset.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="css/text.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="css/grid.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="css/layout.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="css/nav.css" media="screen" />
        <link rel="stylesheet" href="css/datepicker1.css" type="text/css" />
        <link rel="stylesheet" media="screen" type="text/css" href="css/layout1.css" />
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
        <script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/datepicker.js"></script>
        <script type="text/javascript" src="js/eye.js"></script>
        <script type="text/javascript" src="js/utils.js"></script>
        <script type="text/javascript" src="js/layout.js?ver=1.0.2"></script>
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
        <script>
            $(function() {
                $('#inputDate').DatePicker({
                    format:'Y-m-d',
                    date: $('#inputDate').val(),
                    current: $('#inputDate').val(),
                    starts: 1,
                    position: 'r',
                    onBeforeShow: function(){
                        $('#inputDate').DatePickerSetDate($('#inputDate').val(), true);
                    },
                    onChange: function(formated, dates){
                        $('#inputDate').val(formated);
                        if ($('#closeOnSelect input').attr('checked')) {
                            $('#inputDate').DatePickerHide();
                        }
                    }
                });
            });
        </script>
        <script>
            function validate() {
                //
                var rexuser= /^[a-zA-Z0-9._-]{6,}$/;
                if(!rexuser.test(document.registerform.username.value == ''))
                {
                    alert ('Username Must Be A-Z, 0-9 And Length Must Be At Least 6');
                    registerform.username.focus();
                    return false;
                }
                if(document.registerform.confirmpassword.value == '')
                {
                    alert ('Confirm Password Can Not Be Blank');
                    registerform.confirmpassword.focus();
                    return false;
                }
                if(document.registerform.password.value.length < 6)
                {
                    alert ('Password Length Must Be At Least 6');
                    registerform.password.focus();
                    return false;
                }
                if(document.registerform.confirmpassword.value != document.registerform.password.value)
                {
                    alert ('Confirm Password And Password Are Not The Same');
                    registerform.confirmpassword.focus();
                    return false;
                }
                if(document.registerform.fullname.value == '')
                {
                    alert ('Fullname Can Not Be Blank');
                    registerform.fullname.focus();
                    return false;
                }
                if(document.registerform.address.value == '')
                {
                    alert ('Adress Can Not Be Blank');
                    registerform.address.focus();
                    return false;
                }
                //
                var rexphone=/^0\d{7,12}$/;
                if(!rexphone.test(document.registerform.phone.value))
                {
                    alert ('Phone Number Must Be 0-9, Start By 0 And Length Must Be At Least 8');
                    registerform.phone.focus();
                    return false;
                }
                var rexemail=/^\w+([\-\.]\w+)*@\w+\.\w+$/;
                if(!rexemail.test(document.registerform.email.value))
                {
                    alert ("Email Must Be In Formated: xxxx@xxx.xxx!");
                    registerform.email.focus();
                    return false;
                }
                if(document.registerform.answer.value == '')
                {
                    alert ('Please Answer The Secret Question');
                    registerform.answer.focus();
                    return false;
                }
                return true;

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
			<img src="img/logo.png" alt="Logo" /></div>
		    <div class="floatright">
			<div class="floatleft">
			    <img src="img/img-profile.jpg" alt="Profile Pic" /></div>
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
			Add User</h2>
		    <div class="block">
			<div style="width: 100%; height: 400px; text-align: center;">
			    <form action="/news-online-war/Admin" method="POST" name="registerform">
                            <table class="form">
                                <tr>
                                    <td>
                                        <label>
                                            Avatar</label>
                                    </td>
                                    <td align="left">
                                        <input type="file" class="btn btn-small btn-grey" name="insertAvatar"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="vertical-align: middle; padding-top: 9px;">
                                        <label>
                                            Preview</label>
                                    </td>
                                    <td align="left">
                                        <img style="padding: 4px 4px 5px 4px;
                                             border-top: 1px solid #b3b3b3;border-left: 1px solid #b3b3b3;
                                             border-right: 1px solid #eaeaea;border-bottom: 1px solid #eaeaea" src="admin/img/no-user.gif" alt="" width="105px" height="110px"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="col2"><label>Username <span class="font-required">*</span></label></td>
                                    <td align="left"><input type="text" name="insertUsername" maxlength="20" id="username"/></td>
                                </tr>
                                <tr>
                                    <td><label>Password <span class="font-required">*</span></label></td>
                                    <td align="left"><input type="password" name="insertPassword" id="password" maxlength="20"/></td>
                                </tr>
                                <tr>
                                    <td><label>Confirm Password <span class="font-required">*</span></label></td>
                                    <td align="left"><input type="password" name="insertConfirmPassword" id="confirmpassword" maxlength="20" /></td>
                                </tr>
                                <tr>
                                    <td width="150px">
                                        <label>Full Name <span class="font-required">*</span></label>
                                    </td>
                                    <td align="left">
                                        <input type="text" name="insertFullname" maxlength="20" id="fullname"/>

                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Birthday <span class="font-required">*</span></label>
                                    </td>
                                    <td align="left">
                                        <input style="width: 150px" class="inputDate" id="inputDate" type="text" readonly value="${curDate}" name="insertBirthday"/></td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Gender <span class="font-required">*</span></label>
                                    </td>
                                    <td align="left">
                                        <input type="radio" name="insertrd1" value="Male" checked/> Male <input type="radio" value="Female" name="insertrd1" /> Female
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Address <span class="font-required">*</span></label>
                                    </td>
                                    <td align="left">
                                        <input type="text" name="insertAddress" maxlength="100" id="address"/></td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Phone number <span class="font-required">*</span></label>
                                    </td>
                                    <td align="left">
                                        <input type="text" name="insertPhoneNumber" maxlength="11" id="phone"  />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Email <span class="font-required">*</span></label>
                                    </td>
                                    <td align="left">
                                        <input type="text" name="insertEmail" maxlength="20" id="email"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Question<span class="font-required">*</span></label>
                                    </td>
                                    <td align="left">
                                        <select style="width: 205px" name="insertQuestion" id="question">
                                            <option value="What's Your Hobby ?" >What's Your Hobby ?</option>
                                            <option value="What's Your High School Name ?">What's Your High School Name ?</option>
                                            <option value="What's Your Pet Name ?">What's Your Pet Name ?</option>
                                            <option value="What's Your Secondary School Name ?">What's Your Secondary School Name ?</option>
                                            <option value="What Will You Want To Be ?">What Will You Want To Be ?</option>
                                            <option value="Which Country Had You Visited ?">Which Country Had You Visited ?</option>
                                            <option value="What's Your College Name ?">What's Your College Name ?</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Answer <span class="font-required">*</span></label>
                                    </td>
                                    <td align="left">
                                        <input type="text" name="insertAnswer" maxlength="20" id="answer" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Work Zone<span class="font-required">*</span></label>
                                    </td>
                                    <td align="left">
                                        <select style="width: 205px" name="insertcateId" >
                                            <%
                                                        List<Category> cateList = (List<Category>) request.getAttribute("cateList");
                                                        for (int i = 0; i < cateList.size(); i++) {
                                                            int value = cateList.get(i).getCategoryId();
                                                            String title = cateList.get(i).getTitle();
                                            %>
                                            <option value="<%=value%>"><%=title%></option>
                                            <%}%>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                    </td>
                                    <td align="left"><input type="checkbox" name="insertSMS" value="ON" checked/>Agree To Receive SMS Service</td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td>
                                        <div style="margin-top: 25px">
                                            <button  class="btn-icon btn-grey btn-plus"  type="submit" name="action" value="Add User" onclick="return validate();" ><span></span>Add Employee</button>
                                            <button class="btn-icon btn-grey btn-cross" type="button" onclick="history.go(-1);" ><span></span>Cancel</button>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </form>
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

