<%@ page import="com.yida.common.VideoItem"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<link href="css/editVideo.css" rel="stylesheet">
<link rel="shortcut icon" href="img/logo.png">
<script src="js/jquery-2.0.0.min.js"></script>
<script src="js/basic.js"></script>
<script src="js/jquery-ui.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/space/editVideo.js"></script>

<title>视频信息修改</title>
</head>

<body>

	<!-- 提示框 -->
	<div class="inform-box">
	<span class="inform-txt center-block"></span>
	</div>
	
	<!-- 引入网页公共header -->
	<div id="container-header">
		<%@ include file="basic/header.jsp"%>
	</div>
	
	<div class="container-fluid">
		<!-- 提示信息 -->
		<div class="container-fluid">
		<button class="btn btn-info">暂时不支持封面图片和视频的修改</button>
		</div>
		
		<!-- 视频介绍信息 -->
		<table class="table table-striped  table-bordered ">
			<tbody id="newVideoInfo">
				<tr>
					<td class="col-md-6">
					<center>
					<b>标题</b><br><span>40 字以内<br>请尽量使用中文
					</span><br> <span class="i">(必填)</span>
					</center>
					</td>
					<td class="col-md-6">
					<input type="text" class="form-control"
						maxlen="40" id="newvideotitle" value="${title }">
					</td>
				</tr>
				<tr>
					<td class="col-md-6">
					<center>
					<b>隶属栏目</b><br> <span>请按号入座</span><br> <span class="i">(必填)</span>
					</center>
					</td>
					<td class="col-md-6">
					<span>请选择</span>
						<select id="newvideotype" class="form-control">
							<option value="${type }" selected="" disabled="disabled">${type }</option>
							<option value="原创">原创</option>
							<option value="游戏">游戏</option>
							<option value="新闻">新闻</option>
							<option value="搞笑">搞笑</option>
							<option value="学习">学习</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
					<center>
					<b>简介</b><br> <span>200字以内<br>分P备注不填<br>则默认引用简介
					</span><br> <span class="i">(必填)</span>
					</center>
					</td>
					<td >
					<textarea id="newvideobrief"
						class="form-control" style="width: 350px; height:150px;"
						maxlen="200" >${brief }</textarea>
					</td>
				</tr>
			</tbody>
		</table>
		
		<!-- 提交修改 -->
		<div class="container-fluid">
		<button class="btn btn-block btn-primary" id="confirm-edit">确认修改</button>
		</div>
	</div>
</body>
</html>
