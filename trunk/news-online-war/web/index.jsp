<%-- 
    Document   : index
    Created on : Apr 1, 2013, 9:17:42 AM
    Author     : Khatmau_sr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>News Online</title>
        <style type="text/css">
            body{
                background:#DDDDDD;
                color:#333;
                width:100%;
                margin:0;
                text-align:left;
                font-family:Arial, Verdana, sans-serif;
                font-size:13px;
                line-height:1.5em
            }
            @font-face {
                font-family: 'Francois One';
                font-style: normal;
                font-weight: 400;
                src: local('Francois One Regular'), local('FrancoisOne-Regular'), url(http://themes.googleusercontent.com/static/fonts/francoisone/v6/bYbkq2nU2TSx4SwFbz5sCHhCUOGz7vYGh680lGh-uXM.woff) format('woff');
            }
            #outer-wrapper{
                width:950px;
                margin:0 auto 20px;
                padding:0;
                word-wrap:break-word;
                overflow:hidden;
            }
            * {
                margin:0;
                padding:0
            }
            #header-wrapper{
                width:950px;
                height:80px;
                margin:5px 0 0 0;
                padding:0;
            }
            #content-wrapper{
                background:#fff;
                width:950px;
                margin:0 auto;
                padding:15px 0;
                word-wrap:break-word;
                overflow:hidden;
            }
            #main-wrapper{
                width:620px;
                float:left;
                margin:0;
                padding-left:15px;
                word-wrap:break-word;
                overflow:hidden
            }
            #right{
                width:280px;
                float:right;
                margin:0;
                padding-right:15px;
                word-wrap:break-word;
                overflow:hidden
            }
            #footer{
                color:#fff;
                width:950px;
                background:url('img/bottom-bg.png') no-repeat bottom;
                height:40px;
                padding:0;
                font-size:12px;
                text-align:center;
            }
            .credit{
                padding:10px 0;
                margin:0;
            }
            #menu{
                background:url('img/top-bg.png') no-repeat;
                width:950px;
                height:40px;
                display:block;
                padding:0;
                padding:0px 0 0 0;
                margin:0;
                font-size:12px;
                font-weight:bold;
                text-transform:uppercase;
            }
            #menu ul, #menu li{
                float:left;
                list-style:none;
            }
            #menu li a, #menu li a:link, #menu li a:visited{
                color:#fff;
                display:block;
                padding:11px 15px 9px 15px
            }
            #menu li a:hover{
                color:#f8e4b8;
                text-decoration:none
            }
            #menu li li a, #menu li li a:link, #menu li li a:visited{
                background:#044A7C;
                color:#efefe3;
                width:160px;
                font-weight:normal;
                float:none;
                margin:0;
                padding:4px 10px
            }
            #menu li li a:hover, #menu li li a:active{
                color:#fff4c7;
                background:#0614A7;
                padding:4px 10px;
                margin:0
            }
            #menu li ul{
                z-index:9999;
                position:absolute;
                left:-999em;
                height:auto;
                width:160px;
                margin:0;
                padding:0
            }
            #menu li:hover ul, #menu li li:hover ul, #menu li li li:hover ul, #menu li.sfhover ul, #topmenu li li.sfhover ul, #topmenu li li li.sfhover ul{
                left:auto
            }
            #menu li:hover, #menu li.sfhover{
                position:static
            }
        </style>    
    </head>
    <body>
        <div id="outer-wrapper">
            <div id="header-wrapper">
                <img src="img/Logo.png" />
            </div>
            <div id="menu">
                <ul>
                    <li class="first"><a href="http://remark-btemplates.blogspot.com/">Home</a></li>
                    <li><a href="#">Drop Menu 1 »</a>
                    <ul>
                        <li><a href="#" title="Edit Me">Child Menu 1.1</a></li>
                        <li><a href="#" title="Edit Me">Child Menu 1.2</a></li>
                        <li><a href="#" title="Edit Me">Child Menu 1.3</a></li>
                    </ul>
                     </li>
                <li><a href="http://remark-btemplates.blogspot.com/">Drop Menu 2 »</a>
                    <ul>
                        <li><a href="#" title="Edit Me">Child Menu 2.1</a></li>
                        <li><a href="#" title="Edit Me">Child Menu 2.2</a></li>
                        <li><a href="#" title="Edit Me">Child Menu 2.3</a></li>
                    </ul>
                </li>
            </div>
            <div id="content-wrapper">
                <div id="main-wrapper">
                    Bên phải
                </div>
                <div id="right">
                        Bên trái
                </div>
            </div>  
            <div id="footer">
                <div class="credit">
                © 2013 News Online. Design by <strong>Group 1</strong>
                </div>
            </div>
        </div>
    </body>
</html>
