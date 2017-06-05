<%@  page import="com.yida.entity.UserInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%

UserInfo user2=(UserInfo) request.getSession().getAttribute("userInfo");
if(user2==null) response.sendRedirect("login.html");
%>
<html>
<link href="css/header/safe-body.css" rel="stylesheet">
<script type="text/javascript" src="js/space/safe_body.js"></script>
<body>
	<div class="panel-heading">
		<div class="panel-heading">
			<span class="glyphicon glyphicon-lock" aria-hidden="true"
				style="color:#3175b0;padding:5px;"></span><span class="text-primary">账户安全</span>
		</div>
	</div>
	<div class="panel-body safe-body">
		<div class="center-block row">
			<span class="col-md-3 col-md-offset-1 safe-level">用户名</span> </span> <span
				class="col-md-2 col-md-offset-1 safe-level"><%=user2.getAccount() %></span>

			<span class="col-md-2 col-md-offset-1 btn btn-info" data-toggle="collapse"
				data-target="#collapseExample" aria-expanded="false"
				aria-controls="collapseExample">修改密码</span>
		</div>
		<div class="collapse container-fluid edit-input" id="collapseExample">
			<div class="container-fluid">

				<span class="col-md-offset-2 col-md-2">原始密码</span> <span
					class="col-md-4  "> <input type="password"
					class="form-control" value="" id="originalPsw" /></span>
			</div>
			<div class="container-fluid">
				<span class="col-md-offset-2 col-md-2">目标密码</span> <span
					class="col-md-4  "> <input type="password"
					class="form-control" value="" id="newPsw" /></span>
			</div>
			<div class="container-fluid">
				<span class="col-md-offset-2 col-md-2">确认密码</span> <span
					class="col-md-4  "> 
			
			<input type="password"
					class="form-control" value="" id="confirmPsw" /></span>
			
			<span
					class="col-md-2 col-md-offset-1 btn btn-info" id="confirm-edit">确认修改 </span>
			</div>
		</div>
		<div class="center-block row form-group">
			<span class="col-md-2 col-md-offset-1">简介</span> <span
				class="col-md-6  ">
				<textarea rows="3" class="form-control" id="my-introduce"
					><%=user2.getIntroduce() %></textarea></span>
				
			<span
				class="col-md-2 col-md-offset-1 btn btn-info" id="edit-introduce">修改简介</span>
		</div>

	</div>

</body>
</html>
