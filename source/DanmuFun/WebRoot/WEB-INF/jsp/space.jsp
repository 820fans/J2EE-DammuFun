<%@  page import="com.yida.entity.UserInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	UserInfo user1=(UserInfo) request.getSession().getAttribute("userInfo");
	if(user1==null) response.sendRedirect("login.html");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<link href="css/jquery-ui.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/space.css" rel="stylesheet">
<link rel="shortcut icon" href="img/logo.png">
<script src="js/jquery-2.0.0.min.js"></script>
<script src="js/basic.js"></script>
<script src="js/jquery-ui.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js"></script>

<title>用户空间</title>
</head>

<body>
	<!-- 提示框 -->
	<div class="inform-box">
	<span class="inform-txt center-block">赞同</span>
	</div>

	<!-- 引入网页公共header -->
	<div id="container-header">
		<%@ include file="basic/header.jsp"%>
	</div>
	
	<!-- 左侧导航栏 -->
	<div class="col-sm-2 panel panel-default col-md-offset-2 space-left" style="margin-top:20px;padding:0px;">
		<div class="panel-heading">
			<div id="head-img-wrap">
			<img alt="头像" src="<%=user1.getHeader() %>" width="150px" height="150px" />
			</div>
		</div>
		<div class="panel-body" style="padding:0px;">

			<!--左导航栏-->
			<ul class=" list-group space-nav">

				<li class="list-group-item"><a href="space.html?category=account&type=safe"><span
						class="glyphicon glyphicon-user" aria-hidden="true"></span> 我的信息
				</a></li>
				<li class="list-group-item"><a href="space.html?category=account&type=header-set"><span
						class="glyphicon glyphicon-picture" aria-hidden="true"></span> 头像设置
				</a></li>
				<li class="list-group-item"><a href="space.html?category=video&type=video"><span
						class="glyphicon glyphicon-film" aria-hidden="true"></span> 我的视频
				<li class="list-group-item"><a href="space.html?category=video&type=favorite"><span
						class="glyphicon glyphicon-heart" aria-hidden="true"></span> 我的收藏
				<li class="list-group-item"><a href="space.html?category=account&type=concern"><span
						class="glyphicon glyphicon-star" aria-hidden="true"></span> 我的关注
				</a></li>
			</ul>
		</div>
	</div>
	
	<!-- 右侧详细信息 -->
	<div class="col-md-6 space-right" style="margin-top:20px;padding:0px;">
		<% if(request.getParameter("type").equals("safe")){
			%>
			<%@ include file="basic/safe_body.jsp"%>
			<%
			}
		else if(request.getParameter("type").equals("header-set")){
			%>
			<%@ include file="basic/header-set_body.jsp"%>
			<%
			}
		else if(request.getParameter("type").equals("video")){
			%>
			<%@ include file="basic/video_body.jsp"%>
			<%
			}
		else if(request.getParameter("type").equals("favorite")){
			%>
			<%@ include file="basic/favorite_body.jsp"%>
			<%
			}
		else if(request.getParameter("type").equals("concern")){
			%>
			<%@ include file="basic/concern_body.jsp"%>
			<%
			}
		%>
	</div>
	
</body>
</html>
