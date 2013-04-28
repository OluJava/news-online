<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
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

    <link rel="stylesheet" href="css/datepicker.css" type="text/css" />
    <link rel="stylesheet" media="screen" type="text/css" href="css/layout.css" />
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
    <script type="text/javascript" src="js/jquery.datePicker.js"></script>
    //
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
    <script>
        function validate() {
            //
            var rexuser= /^[a-zA-Z0-9._-]{6,}$/;
            if(!rexuser.test(document.registerform.username.value))
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

                    <h1>Register Form</h1>
                    <p>Required fields are marked *</p>
                    <form action="Client" name="registerform">
                        <table>
                            <tr>
                                <td><label>Preview</label></td>
                                <td><img style="padding: 4px 4px 5px 4px;
                                         border-top: 1px solid #b3b3b3;border-left: 1px solid #b3b3b3;
                                         border-right: 1px solid #eaeaea;border-bottom: 1px solid #eaeaea" src="images/no-user.gif" alt="" width="105px" height="120px"/>
                                    <div><input type="file" name="insertAvatar" value="Upload image" class="btn btn-small" /></div>
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 180px"><label>Username <span class="font-required">*</span></label></td>
                                <td><input type="text" name="insertUsername" maxlength="20" id="username"/></td>
                            </tr>
                            <tr>
                                <td><label>Password <span class="font-required">*</span></label></td>
                                <td><input type="password" name="insertPassword" id="password" maxlength="20" style="width: 206px; height: 35px;-webkit-border-radius: 0px;-moz-border-radius: 0px;border:1px solid #DBDBDB; font-size: 15px"/></td>
                            </tr>
                            <tr>
                                <td><label>Confirm Password <span class="font-required">*</span></label></td>
                                <td><input type="password" name="insertConfirmPassword" id="confirmpassword" maxlength="20" style="width: 206px; height: 35px;-webkit-border-radius: 0px;-moz-border-radius: 0px;border:1px solid #DBDBDB; font-size: 15px"/></td>
                            </tr>
                            <tr>
                                <td><label>Full Name <span class="font-required">*</span></label></td>
                                <td><input type="text" name="insertFullname" maxlength="20" id="fullname"/></td>
                            </tr>
                            <tr>
                                <td><label>Birthday <span class="font-required">*</span></label></td>
                                <td><input class="inputDate" id="inputDate" value="${curdate}" name="insertBirthday" readonly /></td>
                            </tr>
                            <tr>
                                <td><label>Gender <span class="font-required">*</span></label></td>
                                <td><input type="radio" name="insertrd1" value="Male" checked /> Male <input type="radio" value="Female" name="insertrd1" /> Female</td>
                            </tr>

                            <tr>
                                <td><label>Address <span class="font-required">*</span></label></td>
                                <td><input type="text" name="insertAddress" maxlength="100" id="address"/></td>
                            </tr>
                            <tr>
                                <td><label>Phone number <span class="font-required">*</span></label></td>
                                <td><input type="text" name="insertPhoneNumber" maxlength="11" id="phone" /></td>
                            </tr>
                            <tr>
                                <td><label>Email <span class="font-required">*</span></label></td>
                                <td><input type="text" name="insertEmail" maxlength="20" id="email"/></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><label><b>* These Question And Answer To Help You Recover Password<br>* You Can't Change It Again</b></label></td>
                            </tr>
                            <tr>
                                <td><label>Secret Question <span class="font-required">*</span></label></td>
                                <td>
                                    <select style="width: 205px" name="insertQuestion" id="question">
                                        <option value="What's Your Hobby ?" selected>What's Your Hobby ?</option>
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
                                <td><label>Secret Answer <span class="font-required" >*</span></label></td>
                                <td><input type="text" name="insertAnswer" maxlength="20" id="answer"/></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input type="checkbox" name="insertSMS" value="ON" checked />I Agree To Receive SMS Service</td>
                            </tr>
                            <tr><td></td><td></td></tr>
                            <tr>
                                <td></td>
                                <td>
                                    <input type="submit" name="action" value="Submit Register" class="btn btn-small" onclick="return validate();"/>
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