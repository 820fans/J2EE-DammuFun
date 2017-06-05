<%@  page import="com.yida.entity.UserInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
UserInfo user=(UserInfo) request.getSession().getAttribute("userInfo");
%>
<link href="css/header/header.css" rel="stylesheet">
<script type="text/javascript" src="js/header.js"></script>

<html>
<body>
	
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-4 col-md-offset-2">
					<div class="col-md-2">
						<a href="index.html"> <img alt="Brand" src="img/logo4.png">
						</a>
					</div>

					<div class="col-md-6" id="index_logoside">
						<a href="index.html" style="text-decoration: none;" class="index_font_ctrl">弹幕视频网</a>
					</div>
				</div>

				<div class="col-md-3">
					<form class="navbar-form" role="search" action="search.html?type='video'">
						<div class="form-group">
							<input type="text" name="value" class="search_box form-control col-md-2"
								placeholder="想搜什么呢~">
					 		<input type="hidden" name="type" value="video" />
					 		<input type="hidden" name="orderType" value="default" />
							<button type="submit" class="btn btn-default index_font_ctrl">搜索</button>
						</div>
					</form>
				</div>

					<%  
						
						if(user==null){
							%>
							<div class="col-md-2 top_text">
							<span id="header_login" class="index_font_ctrl ">登录</span>
							| <span id="header_register" class="index_font_ctrl ">注册</span><%		
						}
						else{
							%>
							<div class="col-md-2">
							<!-- 显示头像 -->
							<span id="header_img" class="dropdown">
								<span  id="dLabel" data-toggle="dropdown" data-animation="true" aria-haspopup="true" aria-expanded="false">
								<img src="<%=user.getHeader() %>" class="img-circle" width="50px" height="50px" />
								</span>
								<ul class="dropdown-menu header_content list-group user-list" aria-labelledby="dLabel">
								  <li class="list-group-item" id="my-account">账户管理</li>
								  <li class="list-group-item" id="my-video">我的视频</li>
								  <li class="list-group-item" id="my-favorite">我的收藏</li>
								  <li class="list-group-item" id="my-concern">我的关注</li>
								  <li class="list-group-item" id="my-logout">退出登录</li>
								</ul>
							</span>
							 <span class="separator">|</span>
							 <span id="uploadVideo1" style="cursor:pointer"
								class="index_font_ctrl top_text"> 投稿 </span>
					<%
						}
					%>

				</div>

			</div>
		</div>
		<!-- /.container-fluid -->
	</nav>
	<div class="top_bg">
	<% if(request.getServletPath().contains("search")){
		
	}
	else{
		%><img width="100%" src="img/bg_top.jpg" /><%
	}
	%>
	</div>
</body>
</html>