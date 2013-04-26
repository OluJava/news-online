<%@page import="web.entity.Users"%>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"> <!--<![endif]-->

<head>

	<!-- Your Basic Site Informations -->
	<title>Enews Responsive News Template</title>
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
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/datepicker.js"></script>
    <script type="text/javascript" src="js/eye.js"></script>
    <script type="text/javascript" src="js/utils.js"></script>
    <script type="text/javascript" src="js/layout.js?ver=1.0.2"></script>
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
    <script language="javascript">
        function validate() {
            if(document.registerform.fullname.value == '')
            {
                alert ('Fullname Cant Be Blank');
		registerform.fullname.focus();
                return false;
            }
            if(document.registerform.address.value == '')
            {
                alert ('Adress Cant Be Blank');
                return false;
            }
            if(document.registerform.phone.value == '')
            {
                alert ('Phone Number Cant Be Blank');
                return false;
            }
            if(document.registerform.email.value == '')
            {
                alert ('Email Cant Be Blank');
                return false;
            }
            if(document.registerform.answer.value == '')
            {
                alert ('Please Answer The Secret Question');
                return false;
            }
            return true;

        }
    </script>
</head>

<body>

 <div id="top-navigation">
  <div class="container">

   <!-- Navigation -->
   <ul class="nav-menu pull-left">
    <li class="active"><a href="index_5.html">Home</a></li>
    <li><a href="#">About Us</a></li>
    <li><a href="contact.html">Feedback</a></li>
   </ul>

   <!-- Search Form -->
   <form name="form-search" method="post" action="http://dotstheme.com/demo/premium/enews_responsive_news_template/html/stretched/search.html" class="form-search pull-right">
    <input type="text" name="search" placeholder="Search...." class="input-icon input-icon-search" />
   </form>

  </div> <!-- End Container -->
 </div> <!-- End Top-Navigation -->

 <div class="container">
  <header id="header" class="clearfix">

   <!-- Logo -->
   <div class="logo pull-left">
    <a href="index-2.html"><img src="images/logo.png" alt="Enews" /></a>
   </div>

   <!-- Ads -->
   <div class="ads pull-right">
    <img src="images/ads/480x80.png" alt="Ads" />
   </div>

  </header> <!-- End Header -->

  <nav id="main-navigation" class="clearfix margin-bottom40">
   <ul>
    <li><a href="blog_posts.html">Business</a></li>
    <li><a href="blog_posts.html">Technology</a></li>
    <li><a href="blog_posts.html">Education</a></li>
    <li><a href="blog_posts.html">Entertainment</a></li>
    <li><a href="blog_photos.html">Photo <i class="arrow-main-nav"></i></a>
     <ul>
      <li><a href="single_photo.html">Single Photo</a></li>
     </ul>
    </li>
    <li><a href="blog_videos.html">Video <i class="arrow-main-nav"></i></a>
     <ul>
      <li><a href="single_video.html">Single Video</a></li>
     </ul>
    </li>
    <li><a href="blog_musics.html">Music <i class="arrow-main-nav"></i></a>
     <ul>
      <li><a href="single_music.html">Single Music</a></li>
     </ul>
    </li>
    <li><a href="blog_reviews.html">Review <i class="arrow-main-nav"></i></a>
     <ul>
      <li><a href="single_review.html">Single Review</a></li>
     </ul>
    </li>
   </ul>
  </nav> <!-- End Main-Navigation -->

  <div class="row-fluid">
   <div id="main" class="span8 page image-preloader">

    <div class="row-fluid">

     <h1>Update Profile</h1>
<%
Users u = (Users) session.getAttribute("curUser");
String img = u.getImage();
if(img==null)
    {
    img="";
    }
String fullname=u.getFullName();
String birthday = u.getBirthday();
boolean gender = u.getGender();
String address =u.getAddress();
String phone = u.getPhone();
String email = u.getEmail();
boolean sms = u.getActiveSMS();
%>
     <form action="Client" name="registerform">
                        <table>
                            <tr>
                                <td><label>Preview</label></td>
                                <td><img style="padding: 4px 4px 5px 4px;
                                         border-top: 1px solid #b3b3b3;border-left: 1px solid #b3b3b3;
                                         border-right: 1px solid #eaeaea;border-bottom: 1px solid #eaeaea" src="images/no-user.gif" alt="" width="105px" height="120px"/>
                                    <div><input type="file" name="insertAvatar" value="<%=img%>" class="btn btn-small" /></div>
                                </td>
                            </tr>
                            <tr>
                                <td><label>Full Name <span class="font-required">*</span></label></td>
                                <td><input type="text" name="insertFullname" value="<%=fullname%>" maxlength="20" id="fullname"/></td>
                            </tr>
                            <tr>
                                <td><label>Birthday <span class="font-required">*</span></label></td>
                                <td><input class="inputDate" id="inputDate" value="<%=birthday%>" name="insertBirthday"/></td>
                            </tr>
                            <tr>
                                <td><label>Gender <span class="font-required">*</span></label></td>
                                <td><input type="radio" name="insertrd1" checked <%if(gender){%>checked<%}%>/> Male <input type="radio" name="rd1" <%if(!gender){%>checked<%}%> /> Female</td>
                            </tr>

                            <tr>
                                <td><label>Address <span class="font-required">*</span></label></td>
                                <td><input type="text" name="insertAddress" maxlength="20" id="address" value="<%=address%>"/></td>
                            </tr>
                            <tr>
                                <td><label>Phone number <span class="font-required">*</span></label></td>
                                <td><input type="text" name="insertPhoneNumber" maxlength="20" id="phone" value="<%=phone%>" /></td>
                            </tr>
                            <tr>
                                <td><label>Email <span class="font-required">*</span></label></td>
                                <td><input type="text" name="insertEmail" maxlength="20" id="email" value="<%=email%>"/></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input type="checkbox" name="insertSMS" value="ON" <%if(sms){%>checked<%}%> />I Agree To Receive SMS Service</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>
                                    <input type="submit" name="action" value="Update Profile" class="btn btn-small" onclick="return validate();"/>
                                    <input type="button" onclick="history.go(-1);" value="Cancel" class="btn btn-small"/>
                                </td>
                            </tr>
                        </table>
                    </form>

    </div> <!-- End Row-Fluid -->
   </div> <!-- End Main -->

   <div id="sidebar" class="span4">

    <div class="widget clearfix">
     <div class="contact-details">

      <div class="header">
       <h4>Contact Informations</h4>
      </div>

      <div class="content">
       <p><strong>Mugiwara Kiwolen</strong></p>
       <p>1234 South Something Street</p>
       <p>Madison, WI 555555</p>
       <p>Call: <i>+00-000-0000</i></p>
       <p>Email: <a href="#">contact@email_domain.com</a></p>
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