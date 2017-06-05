<%@ page import="com.yida.common.VideoItem"%>
<%@ page import="com.yida.common.MoreVideoItem"%>
<%@ page import="com.yida.common.SearchUpperItem"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	//获取视频列表List
	List<VideoItem> videos=(List<VideoItem>)request.getAttribute("VideoList");
	
	//获取阿婆主列表
	List<SearchUpperItem> searchUppers=(List<SearchUpperItem>)request.getAttribute("UpperList");
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
<link href="css/search.css" rel="stylesheet">
<link href="css/space.css" rel="stylesheet">
<link rel="shortcut icon" href="img/logo.png">
<script src="js/jquery-2.0.0.min.js"></script>
<script src="js/basic.js"></script>
<script src="js/jquery-ui.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/search.js"></script>

<title>搜索页面</title>
</head>

<body>

	<!-- 引入网页公共header -->
	<div id="container-header">
		<%@ include file="basic/header.jsp"%>
	</div>

	<!-- 搜索页面框 -->
	<div class="container-fluid search-header">
		<div class="col-md-2 search-left">
			<img src="img/search-left.png" class="center-block" />
		</div>
		<div class="col-md-2 search-inform">
			<center>DanmuFun</center>
			<center>搜索</center>
		</div>
		<div class="col-md-6">
			<form class="form-inline" action="search.html" method="get">
				<div class="input-group search-area col-md-9">
					<input type="text" name="value" class="form-control"
						placeholder="输入你想搜索的内容" value="<%=request.getParameter("value")%>">
					<!-- 搜up主还是视频 -->
					<input type="hidden" name="type" id="type" value="<%=request.getParameter("type")%>" />
					<!-- 排序方式 -->
					<input type="hidden" name="orderType" id="orderType"
						value="<%=request.getParameter("orderType")%>" /> <span class="input-group-btn"> <input
						type="submit" class="btn btn-primary" id="inner_search" value="搜索">搜索
					</span>
				</div>
				<!-- /input-group -->
			</form>
		</div>
		<div class="col-md-2 search-right">
			<img src="img/search-right.png" class="center-block" />
		</div>
	</div>

	<!-- 搜索分类页头 -->
	<div class="container-fluid col-md-offset-2 col-md-8 search-type">
		<ul class="wrap col-md-12" id="typeWrapper">
			<li class="col-md-3 col-md-offset-2 video">视频</li>
			<li class="col-md-1">|</li>
			<li class="col-md-3 upper">UP主</li>
		</ul>
	</div>

	<!-- 视频展示 -->
	<div class="panel panel-default  col-md-offset-2 col-md-8 "
		style="border-radius:0px;padding:0px">
		<div class="panel-heading col-md-12" style="float:left">
			<div class="panel-title">
				<!-- 按照不同规则排序 -->
				<ul id="orderRuls" class="orderRuls col-md-12">
				<%
					String type=request.getParameter("type");
					if(type.equals("video")){
						%><li class="col-md-2 default">最新发布</li>
					<li class="col-md-2 collect">最多收藏</li>
					<li class="col-md-2 view">最多点击</li>
					<li class="col-md-2 danmu">最多弹幕</li><%
					}
					else if(type.equals("upper")){
						%><li class="col-md-2 default">默认排序</li>
						<li class="col-md-2 upload">最多投稿</li>
						<li class="col-md-2 fans">最多关注</li><%
					}
				%>
					
				</ul>
			</div>

		</div>
		<div class="panel-body col-md-12" style="">
		
			<!-- 搜索视频 -->
			<c:forEach items="${VideoList }" var="item">
				<div class="container-fluid col-md-12 video-item">
					<div class="col-md-3 block">
						<a href="detail.html?videoId=${item.id }">
						<img src="${item.coverPath }" class=" coverImg" width="180" height="110px">
						</a>
					</div>
					<div class="col-md-9 block item-text">
						<span class="col-md-1">
							<span class="label label-default itemType">${item.type }</span>
						</span>
						<span class="col-md-8">
							<span class="item-title">${item.getHighLightTitle(searchValue) }</span>
						</span>
						<br>
						<div class="col-md-11 item-brief">
							<span class="item-brief-content">${item.brief }</span>
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
							<span class="col-md-3"  data-toggle="tooltip" data-placement="top"  title="上传时间">
							<img alt="上传时间" src="img/item-uploadTime.png"> ${item.getUploadTime() }
							</span>
							<span class="col-md-2" data-toggle="tooltip" data-placement="top"  title="UP主">
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
			
			<!-- 搜索阿婆主 -->
			<c:forEach items="${UpperList }" var="item" varStatus="status">

				<!-- 阿婆主本人的信息 -->
				<div class="upper-item-wrapper col-md-12">
					<div class="container-fluid col-md-12 upper-item">
						<!-- 用户头像 -->
						<div class="col-md-2 col-md-offset-2">
							<img alt="用户头像" class="upper-header" src="${item.header }"
								width="60px" height="60px">
						</div>

						<!-- 用户信息 -->
						<div class="col-md-8">
							<div class="container-fluid">
								<span class="upper-account">${item.getHighLightAccount(searchValue) }</span>
							</div>
							<div class="container-fluid" style="height:30px">
								<span class="upper-introduce">${item.introduce }</span>
							</div>
							<div class="container-fluid item-statistics">
								<span class="col-md-2" data-toggle="tooltip"
									data-placement="top" title="投稿量"> <img alt="投稿量"
									class="upper-info-num" src="img/item-uploadNum.png">
									${item.uploadNum }
								</span> <span class="col-md-2" data-toggle="tooltip"
									data-placement="top" title="粉丝量"> <img alt="粉丝量"
									class="upper-info-num" src="img/item-fansNum.png">
									${item.fansNum }
								</span>
							</div>
						</div>
						
					</div>
					<!-- 阿婆主的信息部分 -->

					<!-- 阿婆主上传过的视频的信息 -->
					<div class="container-fluid col-md-8  col-md-offset-2  upper-video-item-wrapper">
						<c:forEach items="${item.myVideos }" var="upperVideoItem">
							<a href="detail.html?videoId=${upperVideoItem.id }"
								class="upperVideoItemWrapper center-block">
								<div class="col-md-3 col-md-offset-1 upperVideoItem">
									<span class="col-md-12"> <img alt="阿婆主上传的视频"
										class="upper-video-item" src="${upperVideoItem.coverPath }"
										width="120px" height="75px">
									</span> <span class=" col-md-12 upvwrapper"> <span
										class="upper-video-item-info col-md-12">
											${upperVideoItem.title } </span>
									</span>
								</div>
							</a>
						</c:forEach>

						<!-- 没有上传过任何视频
					${status.index} -->
						<div class="no-inform">
							<center>
								<img alt="没有找到" src="img/no-more.png">
							</center>
							<center style="margin-top:0px;font-size:16px;font-family:'微软雅黑'">
								<div>阿婆主没有上传视频</div>
							</center>
						</div>

					</div><!-- 单个阿婆主上传的视频 -->
					
				</div><!-- 包含单个upper -->
			</c:forEach>
			
			<!-- 没有任何阿婆主找到的情况 -->
			<%
				if(searchUppers!=null&& searchUppers.size()<=0){
			%><center>
				<img alt="没有找到" src="img/no-comment.png">
			</center>
			<center style="margin-top:20px;font-size:20px;font-family:'微软雅黑'">
				<div>没有找到符合条件的阿婆主呢</div>
			</center><%
				}
			%>
		</div>
	</div>

	<!-- 用户展示 -->

</body>
</html>
