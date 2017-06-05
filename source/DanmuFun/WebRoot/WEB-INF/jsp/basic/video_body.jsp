<%@  page import="com.yida.entity.UserInfo"%>
<%@  page import="com.yida.common.MyVideoItem" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%

UserInfo user2=(UserInfo) request.getSession().getAttribute("userInfo");
if(user2==null) response.sendRedirect("login.html");

List<MyVideoItem> myVideos=(List<MyVideoItem>)request.getAttribute("MyVideoList");
%>
<html>
<link href="css/header/video_body.css" rel="stylesheet">
<script type="text/javascript" src="js/space/video_manage.js"></script>

<body>

	
	<div class="panel-heading">
		<div class="panel-heading">
			<span class="glyphicon glyphicon-film" aria-hidden="true"
				style="color:#3175b0;padding:5px;"></span><span class="text-primary">视频管理</span>
			<span class="btn btn-primary pull-right" id="uploadVideo2">我要投稿</span>
		</div>
	</div>
	
	<div class="panel-body video-body">
		<c:forEach items="${MyVideoList }" var="item">
		
				<div class="container-fluid col-md-12 video-item">
					<div class="col-md-3 block">
						<a href="detail.html?videoId=${item.id }">
						<img src="${item.coverPath }" class=" coverImg" width="150" height="90px">
						</a>
					</div>
					<div class="col-md-7 block item-text" style="height:90px">
						<span class="col-md-1 col-md-offset-1">
							<span class="label label-default itemType">${item.type }</span>
						</span>
						<span class="col-md-8">
							<span class="item-title">${item.title }</span>
						</span>
						<br>
						<div class="col-md-12 item-uploadTime" style="height:30px">

							<span class="col-md-12" data-toggle="tooltip" data-placement="top" title="上传时间">
							<img alt="上传时间" src="img/item-uploadTime.png"> 
							${item.getUploadDetailTime() }
							</span>
						</div>
						<div class="col-md-12 item-statistics pull-down">
							<span class="col-md-3" data-toggle="tooltip" data-placement="top"  title="播放量">
							<img alt="播放量" src="img/item-viewNum.png"> ${item.viewNum }
							</span>
							<span class="col-md-3" data-toggle="tooltip" data-placement="top"   title="弹幕量">
							<img alt="弹幕量" src="img/item-danmuNum.png"> ${item.danmuNum }
							</span>
							<span class="col-md-3" data-toggle="tooltip" data-placement="top"  title="收藏量">
							<img alt="收藏量" src="img/item-collectNum.png"> ${item.collectNum }
							</span>
							<span class="col-md-3" data-toggle="tooltip" data-placement="top"  title="评论量">
							<img alt="评论量" src="img/item-commentNum.png"> ${item.commentNum }
							</span>
						</div>
					</div>

				<!-- 对视频的操作 -->
				<div class="col-md-2 item-manage">
					<a href="editVideo.html?videoId=${item.id }">
					<button class="btn btn-default" onClick="EditVideo(this)">编辑</button>
					</a>
					<button class="btn btn-primary" id="item-download" onClick="Download(this)">下载</button>
					<input type="hidden" id="item-path" value="${item.videoPath }" />
				</div>
			</div>

		</c:forEach>
		
		<!-- 没有任何视频找到的情况 -->
		<%
			if (myVideos != null && myVideos.size() <= 0) {
		%><center>
			<img alt="没有找到" src="img/no-myvideo.png">
		</center>
		<center style="margin-top:20px;font-size:18px;color:grey;font-family:'微软雅黑'">
			<div>您还没有上传过视频</div>
		</center>
		<%
			}
		%>
	</div>
</body>
</html>
