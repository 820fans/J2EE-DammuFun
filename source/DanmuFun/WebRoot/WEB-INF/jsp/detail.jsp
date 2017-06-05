<%@ page import="com.yida.common.VideoItem"%>
<%@ page import="com.yida.common.MoreVideoItem"%>
<%@ page import="com.yida.common.CommentItem"%>
<%@ page import="com.yida.entity.UserInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
//获取用户信息
UserInfo user3=(UserInfo) request.getSession().getAttribute("userInfo");

//获取评论信息
List<CommentItem> myComments=(List<CommentItem>) request.getAttribute("comments");
%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/jquery-ui.css" rel="stylesheet">
<link href="css/detail.css" rel="stylesheet">
<link rel="shortcut icon" href="img/logo.png">
<script src="js/jquery-2.0.0.min.js"></script>
<script src="js/basic.js"></script>
<script src="js/screenfull.js"></script>
<script src="js/jquery-ui.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js"></script>
<link href="css/danmu/scojs.css" rel="stylesheet">
<link href="css/danmu/colpick.css" rel="stylesheet">
<link href="css/danmu/main.css" rel="stylesheet">
<script src="js/detail.js"></script>

<title>视频详情</title>
</head>

<body>

	<!-- 引入网页公共header -->
	<div class="container-header" id="nav-header">
		<%@ include file="basic/header.jsp"%>
	</div>
	
	<!-- 提示框 -->
	<div class="inform-box">
	<span class="inform-txt center-block">赞同</span>
	</div>
	
	<!-- 信息介绍 -->
	<div class="container-fluid col-md-12 info-wrapper">

		<div class="col-md-2 search-left">
			<img src="img/search-left.png" class="center-block" />
		</div>

		<!-- 视频头部介绍等 -->
		<div class="col-md-8">
			<!-- 视频头信息 -->
			<div class="col-md-8 video-info-wrapper">
				<div class="container-fluid video-title">
					<h3>${Item.title }</h3>
				</div>
				<div class="label label-info video-type">
					<span>${Item.type }</span> <input type="hidden" id="videoPath"
						value="${Item.videoPath}">
				</div>
				<div class="container-fluid" style="padding:5px;margin-top:5px;">
					<span class="col-md-2" data-toggle="tooltip" data-placement="top"  title="播放量"> <img alt="播放量"
						src="img/item-viewNum.png"> ${Item.viewNum }
					</span> <span class="col-md-2" data-toggle="tooltip" data-placement="top"  title="弹幕量"> <img alt="弹幕量"
						src="img/item-danmuNum.png"> ${Item.danmuNum }
					</span> <span class="col-md-2" data-toggle="tooltip" data-placement="top"  title="收藏量"> <img alt="收藏量"
						src="img/item-collectNum.png"> ${Item.collectNum }
					</span> <span class="col-md-6" data-toggle="tooltip" data-placement="top"  title="上传时间"> <img alt="上传时间"
						src="img/item-uploadTime.png"> ${Item.getUploadDetailTime() }
					</span>
				</div>
			</div>
			<!-- 阿婆主信息 -->
			<div class="col-md-4 user-info-wrapper">
				<div class="col-md-3">
					<img alt="用户头像" src="${upper.header }" class="upper-header"
						width="60px" height="60px">
				</div>
				<div class="col-md-6">
					<div class="container-fluid upper-account">${upper.account }</div>
					<div class="container-fluid upper-num">
						<span>投稿量:</span> <span>${upper.uploadNum }</span>
					</div>
					<div class="container-fluid upper-num">
						<span>他关注谁:</span> <span>${upper.concernNum }</span>
					</div>
					<div class="container-fluid upper-num">
						<span>谁关注他:</span> <span>${upper.fansNum }</span>
					</div>
				</div>
				<div class="col-md-2">
					<br> <br>
					<%  String type=(String) request.getAttribute("concern");
						if(type.equals("true")){
							%><button class="btn btn-primary concern-btn" id="concern-him">
							<input type="hidden" id="upperId" value="${upper.id }" />
							<b>+</b>&nbsp关注</button><%
						}
						else if(type.equals("false")){
							%><button class="btn btn-primary unconcern-btn" id="concern-him">
							<input type="hidden" id="upperId" value="${upper.id }" />
							<b>-</b>&nbsp关注</button><%
						}
						else{
							%><a href="space.html?category=account&type=safe">
							<button class="btn btn-primary " id="concern-him">我的空间</button></a><%
						}
					%>
				</div>
			</div>
		</div>

		<div class="col-md-2 search-right">
			<img src="img/search-right.png" />
		</div>
	</div>

	<!-- 视频播放器 -->
	<div class="container-fluid col-md-12 player-wrapper">

		<div class="container-fluid">
			<div class="col-md-2"></div>
			<!-- 视频播放器本体 -->
			<div class="col-md-8">
				<div id="danmup"></div>
			</div>
			<div class="col-md-2"></div>
		</div>

		<div class="container-fluid">
			<div class="col-md-2"></div>
			<!-- 视频播放详细内容本体 -->
			<div class="col-md-8" style="padding:10px">

				<div class="player-plus">
					<div class="col-md-8 video-brief-wrapper">
						<span class="col-md-12 brief-separator">视频简介</span> <span
							class="col-md-12 video-brief"> ${Item.brief }</span>
					</div>
					<div class="col-md-4">
						<%
							String fav = (String) request.getAttribute("favorite");
							if (fav.equals("true")) {
						%><img alt="取消收藏" id="collect-img" class="col-md-offset-1 faved-icon" 
						title="取消收藏" src="img/faved.png">
						<%
							} else {
						%><img alt="收藏" id="collect-img" class="col-md-offset-1 fav-icon" 
						title="收藏本视频"	src="img/fav.png">
						<%
							}
						%>

					</div>
				</div>
			</div>
			<div class="col-md-2"></div>
		</div>

	</div>

	<!-- 评论区  更多视频区-->
	<div class="container-fluid col-md-12 comment-area">
		<div class="col-md-2">
			<img src="img/search-left-bottom.png" class="center-block" />
		</div>
		<div class="col-md-8">
			<div class="col-md-8 comment-entity">
				<h3>评论区</h3>
				<hr />
				<div class="panel">
					<div class="panel-heading">

						<div class="container-fluid comment-input-area">
							<div class="col-md-2">
								<%
									if(user3==null){
										%><img alt="用户头像" class=" comment-header-img" src="img/noface.gif"
									width="60px" height="60px"><%
									}
									else{
										%><img alt="用户头像" class=" comment-header-img" src="<%=user3.getHeader() %>"
									width="60px" height="60px"><%
									}
								%>
								
							</div>
							
							<div class="col-md-9 comment-inputbox" id="comment-inputbox-wrapper">
							
								<%
									if(user3==null){
										%><textarea class="form-control" rows="3" cols="45" 
									id="comment-inputbox" disabled>请先登录再评论</textarea><%
									}
									else{
										%><textarea class="form-control" rows="3" cols="45" 
									id="comment-inputbox"></textarea><%
									}
								%>
							</div>
							
							<div class="col-md-1 comment-inputbtn">
								<center>
								<%
									if(user3==null){
										%><span class="btn btn-primary" id="comment-inputbtn" disabled>评论</span><%
									}
									else{
										%><span class="btn btn-primary" id="comment-inputbtn">评论</span><%
									}
								%>
								
								</center>
							</div>
							
						</div>
					</div>
					<div class="panel-body" id="comment-items-wrapper">
						<c:forEach items="${comments }" var="comment" varStatus="idx">
							<div class="container-fluid col-md-12 comment-item">
								<div class="col-md-2">
								<img class=" comment-header-img" alt="评论用户头像" src="${comment.header }" width="60px" height="60px">
								</div>
								<div class="col-md-10 comment-info">
									<div class="container-fluid">
										<span class=" commenter-account">${comment.account }</span>
									</div>
									<div class="container-fluid comment-content">
										<span>${comment.content }</span>
									</div>
									<div class="container-fluid comment-detail">
										<span class="pull-left">
											<span class="comment-time">${comment.getCommentTime() }</span>
										</span>
										<span class="col-md-3">
											<input type="hidden" id="comment-id" value="${comment.id }" />
											<input type="hidden" id="comment-self" value="${comment.self }" >
											<span class="comment-zan" id="comment-zan" onClick="ZanComment(this)">
											 <span class="self">${comment.self }</span>赞(<span class="zan-num">${comment.zanNum }</span>)</span>
											
										</span>
										<span class="col-md-3">
											<span class=" comment-reply"></span>
										</span>
									</div>
								</div>
							</div>
						</c:forEach>
						
						<!-- 没有任何评论 -->
						<%
							if(myComments.size()<=0){
								%>
								<div id="no-comment-inform">
								<center>
								<img alt="没有评论" src="img/no-comment.png">
								</center>
								<center style="margin-top:10px;">
								<div>一条评论都没有，快来抢沙发吧</div>
								</center>
								</div>
								<%
							}
						%>
					</div>
				</div>
			</div>
			
			<div class="col-md-4 more-video-area">
				<h4 class="text-primary">更多视频</h4>
				<div class="col-md-12">
					<c:forEach items="${moreVideos }" var="moreVideoItem">
						<div class="container-fluid moreVideoItem">
							<div class="">
								<a href="detail.html?videoId=${moreVideoItem.id }">
								<img alt="更多视频" class="morecoverImg" src="${moreVideoItem.coverPath }" width="180px" height="110px">
								</a>
							</div>
							
							<div class="moreInfo text-primary">
								<span class="moreType">【${moreVideoItem.type }】</span>
								<span class="moreTitle">${moreVideoItem.title }</span>
							</div>
						</div>
						
					</c:forEach>
				</div>
				
			</div>
		</div>
		
		<div class="col-md-2">
			<img src="img/search-right-bottom.png" />
		</div>
	</div>
</body>

<script src="js/danmu/jquery.shCircleLoader.js"></script>
<script src="js/danmu/sco.tooltip.js"></script>
<script src="js/danmu/colpick.js"></script>
<script src="js/danmu/jquery.danmu.js"></script>
<script src="js/danmu/main.js"></script>

<script>
  var aimWidth=$(".player-plus").css("width");
  $("#danmup").DanmuPlayer({
    src:$("#videoPath").val(),
    height: "480px", //区域的高度
    width: aimWidth //区域的宽度
    ,urlToGetDanmu:"getDanmu.html?videoId="+${Item.id}
    ,urlToPostDanmu:"setDanmu.html?videoId="+${Item.id}
  });
  
  //测试弹幕正常显示
  /*$("#danmup .danmu-div").danmu("addDanmu",[
    { "text":"这是滚动弹幕" ,color:"white",size:1,position:0,time:200}
    ,{ "text":"我是帽子绿" ,color:"#FF0000",size:1,position:0,time:3}
    ,{ "text":"哈哈哈啊哈" ,color:"black",size:1,position:0,time:10}
    ,{ "text":"这是顶部弹幕" ,color:"yellow" ,size:1,position:1,time:3}
    ,{ "text":"这是底部弹幕" , color:"red" ,size:1,position:2,time:9}
    ,{ "text":"大家好，我是伊藤橙" ,color:"orange",size:1,position:1,time:3}

  ])*/
</script>
</html>
