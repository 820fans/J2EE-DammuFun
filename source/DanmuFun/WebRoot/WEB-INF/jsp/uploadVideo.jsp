<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<link href="css/uploadVideo.css" rel="stylesheet">
<link rel="shortcut icon" href="img/logo.png">
<script src="js/jquery-2.0.0.min.js"></script>
<script src="js/basic.js"></script>
<script src="js/jquery-ui.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/plupload/plupload.full.min.js"></script>
<script src="js/plupload/zh_CN.js" charset="UTF-8"></script>
<script src="js/plupload/pluploadMain.js" ></script>
<script src="js/space/uploadVideo.js"></script>

<title>视频上传</title>
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

	<!-- 文件上传部分 -->
	<div class="col-sm-2 panel panel-default col-md-offset-1 space-left"
		style="margin-top:20px;padding:0px;border-radius:0px;">

		<!--左导航栏-->
		<ul class="nav nav-pills nav-stacked" role="tablist">
			<li role="presentation" class=""><a href="#home" role="tab"
				data-toggle="tab" aria-expanded="false"> <span
					class="glyphicon glyphicon-home" aria-hidden="true"
					style="padding:5px"></span>上传介绍
			</a></li>
			<li role="presentation" class="active"><a href="#new_upload"
				role="tab" data-toggle="tab" aria-expanded="false"> <span
					class="glyphicon glyphicon-send" aria-hidden="true"
					style="padding:5px"></span>新版上传
			</a></li>
			<li role="presentation" class=""><a href="#old_upload"
				role="tab" data-toggle="tab"><span
					class="glyphicon glyphicon-open" aria-hidden="true"
					style="padding:5px"></span>旧版上传 </a></li>
		</ul>
	</div>

	<!--右侧详细信息-->
	<div class="col-sm-8 tab-content space-right"
		style="margin-top:20px;padding:0px;">
		<!-- 主页信息 -->
		<div class="tab-pane fade" id="home">
			<div class="jumbotron">
				<div class="container">
					<h1>弹幕视频网上传系统</h1>
					<h4>你可以通过这里上传你的视频，分为新版和旧版</h4>
					<h4>旧版采取最原始的form表单上传，支持的功能较少</h4>
					<h4>新版采用plupload.js插件，支持断点续传，不过相对来说不是很稳定</h4>
				</div>
			</div>
		</div>
		<!--新版上传-->
		<div class="tab-pane fade active in" id="new_upload">

			<ul class="nav nav-tabs" role="tablist">
				<li role="presentation" class="active"><a href="#updateFile"
					aria-controls="home" role="tab" data-toggle="tab"> <span
						class="glyphicon glyphicon-send" aria-hidden="true"></span>新版上传
				</a></li>
			</ul>

			<div class="tab-content" style="padding:10px">
				<div role="tabpanel" class="tab-pane fade in active" id="updateFile"style="padding:10px">
					
					<div class="container-fluid" style="padding:5px">
						<button class="btn btn-default" id="browse" style="padding:10px">选择文件</button>
						<button class="btn btn-success" id="start_upload"
							style="padding:10px">开始上传</button>
						<button class="btn btn-warning" id="stop_upload"
							style="padding:10px">暂停上传</button>
					</div>
					
					<!-- 上传提示 -->
					<div class="container-fluid new-upload-inform">
						<center class="label label-primary center-block" style="padding:5px">请选择<b>一个</b>视频封面和<b>一个</b>视频，并完善下面的视频信息，然后点击上传</center>
					</div>
					
					<!-- 上传文件列表 -->
					<table class="table table-striped order-table table-hover table-bordered ">
						<thead>
							<tr>
								<div class="container">
									<div class="row">
										<th class="col-md-2">文件名</th>
										<th class="col-md-1">文件大小</th>
										<th class="col-md-3">上传进度</th>
										<th class="col-md-1">上传速度</th>
										<th class="col-md-1">状态</th>
										<th class="col-md-1">操作</th>
									</div>
								</div>
							</tr>
						</thead>
						<tbody id="placeFile">
						</tbody>
					</table>
					
					<!-- 添加视频的介绍信息 -->
					<div class="container-fluid new-upload-inform">
						<center class="label label-primary center-block" style="padding:5px">请完善该视频的信息</center>
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
									maxlen="40" id="newvideotitle">
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
										<option value="0" selected="" disabled="disabled">请选择</option>
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
									maxlen="200" ></textarea>
								</td>
							</tr>
						</tbody>
					</table>
					
					<!-- 开始上传的按钮 -->
					<div class="container-fluid">
					<button class="btn btn-block btn-success" id="start-plus">开始上传</button>
					</div>
				</div>
			</div>
		</div>

		<!-- 旧版上传 -->
		<div class="tab-pane fade" id="old_upload">

			<ul class="nav nav-tabs " role="tablist">
				<li role="presentation" class="active"><a href="#updateFile"
					aria-controls="home" role="tab" data-toggle="tab"> <span
						class="glyphicon glyphicon-open" aria-hidden="true"></span>旧版上传
				</a></li>
			</ul>

			<div class="tab-content active" style="padding:10px">

				<form class="" action="videoUpload.html" method="post"
					enctype="multipart/form-data" onSubmit="return checkValues()">

					<ul class="wrapper-item">
						<li class=" container-fluid">
							<div class="item-l col-md-3">
								<b>封面图</b><br> <span>点击图片上传</span><br> <span>建议至少480*300<br>
									支持高清封面960*600
								</span>
							</div>
							<div class="item-r col-md-9 center-block">
								<div class="mask">上传封面</div>
								<br> <input type="file" class="col-md-6" accept='image/*' name="cover" id="coverImg">
							</div>
						</li>
						<li class=" container-fluid">
							<div class="item-l col-md-3">
								<b>标题</b><br><span>40 字以内<br>请尽量使用中文
								</span><br> <span class="i">(必填)</span>
							</div>
							<div class="item-r col-md-9">
								<input type="text" name="title" class="form-control col-md-4"
									maxlen="40" id="videotitle">
							</div>
						</li>
						<li class=" container-fluid">
							<div class="item-l  col-md-3">
								<b>隶属栏目</b><br> <span>请按号入座</span><br> <span class="i">(必填)</i>
							</div>
							<div class="item-r col-md-9" id="lanmu">
								<div class="col-md-5">
									<span>请选择</span>
									<select name="type" id="videotype" class="form-control">
										<option value="0" selected="" disabled="disabled">请选择</option>
										<option value="原创">原创</option>
										<option value="游戏">游戏</option>
										<option value="新闻">新闻</option>
										<option value="搞笑">搞笑</option>
										<option value="学习">学习</option>
									</select>
								</div>
							</div>
						</li>
						<li class="container-fluid">
							<div class="item-l col-md-3">
								<b>简介</b><br><span>200字以内<br>分P备注不填<br>则默认引用简介
								</span><br> <span class="i">(必填)</span>
							</div>
							<div class="item-r col-md-9">
								<textarea name="brief" id="videobrief"
									class="form-control" style="width: 350px; height:150px;"
									maxlen="200" ></textarea>
							</div>
						</li>
						<li class="container-fluid">
							<div class="item-l col-md-3">
								<b>投稿内容</b><br>
								<b>请注意控制视频格式</b>
							</div>
							<div class="item-r col-md-9">
								<span>视频文件：</span> <input type="file"
									name="videofile" id="videofile" accept="video/*">
							</div>
						</li>
					</ul>
					
					<div class="panel-footer">
					<input class="btn btn-primary form-control" type="submit" />
					</div>

				</form>
			</div>
		</div>
	</div>
</body>
</html>
