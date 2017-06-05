<%@  page import="com.yida.entity.UserInfo"%>
<%@ page import="com.yida.common.SearchUpperItem"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%

UserInfo user2=(UserInfo) request.getSession().getAttribute("userInfo");
if(user2==null) response.sendRedirect("login.html");


//获取阿婆主列表
List<SearchUpperItem> searchUppers=(List<SearchUpperItem>)request.getAttribute("UpperList");
%>
<html>
<link href="css/header/concern_body.css" rel="stylesheet">
<script type="text/javascript" src="js/space/concern_body.js"></script>
<body>

	<div class="panel-heading">
		<div class="panel-heading">
			<span class="glyphicon glyphicon-star" aria-hidden="true"
				style="color:#3175b0;padding:5px;"></span><span class="text-primary">我的关注</span>
		</div>
	</div>
	<div class="panel-body">
	<!-- 搜索阿婆主 -->
			<c:forEach items="${UpperList }" var="item" varStatus="status">

				<!-- 阿婆主本人的信息 -->
				<div class="upper-item-wrapper col-md-12">
					<div class="container-fluid col-md-12 upper-item">
						<!-- 用户头像 -->
						<div class="col-md-2">
							<img alt="用户头像" class="upper-header" src="${item.header }"
								width="60px" height="60px">
						</div>

						<!-- 用户信息 -->
						<div class="col-md-8">
							<div class="col-md-6">
							<div class="container-fluid">
								<span class="upper-account">${item.getHighLightAccount(searchValue) }</span>
							</div>
							<div class="container-fluid" style="height:30px">
								<span class="upper-introduce">${item.introduce }</span>
							</div>
							</div>
							<div class="col-md-6 item-statistics">
								<span class="col-md-6" data-toggle="tooltip"
									data-placement="top" title="投稿量"> <img alt="投稿量"
									class="upper-info-num" src="img/item-uploadNum.png">
									${item.uploadNum }
								</span> <span class="col-md-6" data-toggle="tooltip"
									data-placement="top" title="粉丝量"> <img alt="粉丝量"
									class="upper-info-num" src="img/item-fansNum.png">
									${item.fansNum }
								</span>
							</div>
						</div>
						
						<!-- 取消关注 -->
						<div class="col-md-2">
						<input type="hidden" value="${item.upperId }" class="concern-item-upperId" />
						<span class="btn btn-primary pull-right" onClick="CancelConcern(this)">取消关注</span>
						</div>
						
					</div>
					<!-- 阿婆主的信息部分 -->
					
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
</body>
</html>
