<%@  page import="com.yida.entity.UserInfo"%>
<%@ page import="com.yida.common.VideoItem"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%

UserInfo user2=(UserInfo) request.getSession().getAttribute("userInfo");
if(user2==null) response.sendRedirect("login.html");

//获取视频列表List
List<VideoItem> videos=(List<VideoItem>)request.getAttribute("VideoList");
%>
<html>
<link href="css/header/favorite_body.css" rel="stylesheet">
<script type="text/javascript" src="js/space/favorite_body.js"></script>
<body>
	<div class="panel-heading">
		<div class="panel-heading">
			<span class="glyphicon glyphicon-heart" aria-hidden="true"
				style="color:#3175b0;padding:5px;"></span><span class="text-primary">我的收藏</span>
		</div>
	</div>
	<div class="panel-body safe-body" style="padding:0px">
	
		<!-- 搜索视频 -->
		<c:forEach items="${VideoList }" var="item">
		
			<div class="container-fluid col-md-12 video-item">
			
				<div class="col-md-2 block">
					<a href="detail.html?videoId=${item.id }">
					<img src="${item.coverPath }" class=" coverImg" width="120" height="90px">
					</a>
				</div>
				
				<div class="col-md-10 block item-text" style="height:110px;padding-left:30px;">
					<span class="col-md-1">
						<span class="label label-default itemType">${item.type }</span>
					</span>
					<span class="col-md-8">
						<span class="item-title">${item.getHighLightTitle(searchValue) }</span>
					</span>
					<br>
					<div class="col-md-12 item-brief">
						<span class="">${item.brief }</span>
						<input type="hidden" value="${item.id }" class="collect-item-videoId" />
						<span class="btn btn-primary pull-right" onClick="CancelCollect(this)">取消收藏</span>
					</div>
					<div class="col-md-12 item-statistics">
						<span class="col-md-2" data-toggle="tooltip" data-placement="top"  title="播放量">
						<img alt="播放量" src="img/item-viewNum.png"> ${item.viewNum }
						</span>
						<span class="col-md-2" data-toggle="tooltip" data-placement="top"   title="弹幕量">
						<img alt="弹幕量" src="img/item-danmuNum.png"> ${item.danmuNum }
						</span>
						<span class="col-md-2" data-toggle="tooltip" data-placement="top"  title="收藏量">
						<img alt="收藏量" src="img/item-collectNum.png"> ${item.collectNum }
						</span>
						<span class="col-md-3" data-toggle="tooltip" data-placement="top"  title="UP主">
						<img alt="UP主" src="img/item-upperName.png"> ${item.upperName }
						</span>
					</div>
				</div>
			</div>
		</c:forEach>
		<!-- 没有任何视频找到的情况 -->
		<%
			if(videos!=null&& videos.size()<=0){
		%><center>
			<img alt="没有找到" src="img/no-comment.png">
		</center>
		<center style="margin-top:20px;font-size:20px;font-family:'微软雅黑'">
			<div>没有找到符合条件的视频呢</div>
		</center><%
			}
		%>
	</div>
</body>
</html>
