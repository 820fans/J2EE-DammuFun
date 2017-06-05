<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<link href="css/login.css" rel="stylesheet">
<link rel="shortcut icon" href="img/logo.png">
<script src="js/jquery-2.0.0.min.js"></script>
<script src="js/basic.js"></script>
<script src="js/register.js"></script>
<script src="js/bootstrap.min.js"></script>

<title>注册账户</title>
</head>

<body>

	<!-- 引入网页公共header -->
	<div id="container-header">
		<%@ include file="basic/header.jsp"%>
	</div>
	
	<!-- 主体网页部分 -->
	<div class="container" style="padding-top:20px">

		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<img src="img/register.png" class="col-md-offset-4">
			</div>
		</div>
	
		<div class="row">
			<form class="form-horizontal" action="toRegister.html" onsubmit="return Register();" method="post">
				<div class="form-group has-feedback">
					<div class="col-md-4 col-md-offset-4" style="margin-top:20px">
						<input type="text" class="form-control" placeholder="账号"
							id="account" name="account" >
							 <span id="account_plus"> <span
							class="glyphicon glyphicon-ok form-control-feedback"
							style="color:green" aria-hidden="true"></span>
						</span>
					</div>
				</div>

				<div class="form-group has-feedback">
					<div class="col-md-4 col-md-offset-4" style="margin-top:20px">
						<input type="password" class="form-control" placeholder="密码"
							id="password" name="password">
							<span id="password_plus"> <span
							class="glyphicon glyphicon-ok form-control-feedback"
							style="color:green" aria-hidden="true"></span> <span
							class="glyphicon glyphicon-remove form-control-feedback"
							style="color:brown" aria-hidden="true"></span> <span
							class="glyphicon glyphicon-warning-sign form-control-feedback"
							style="color:orange" aria-hidden="true"></span>
						</span>
					</div>
				</div>

				<div class="form-group  has-feedback">
					<div class="col-md-4 col-md-offset-4" style="margin-top:20px">
						<input type="password" class="form-control" placeholder="确认密码"
							id="confirm_password"> <span id="confirm_password_plus">
							<span class="glyphicon glyphicon-ok form-control-feedback"
							style="color:green" aria-hidden="true"></span> <span
							class="glyphicon glyphicon-remove form-control-feedback"
							style="color:red" aria-hidden="true"></span> <span
							class="glyphicon glyphicon-warning-sign form-control-feedback"
							style="color:orange" aria-hidden="true"></span>
						</span>
					</div>
				</div>

				<div class="form-group has-feedback">
					<div class="col-md-4 col-md-offset-4" style="margin-top:20px">
						<textarea type="text" class="form-control" placeholder="个人简介" 
						name="introduce" id="introduce" style="overflow-y:hidden; width=200px;" ></textarea>
					</div>
				</div>

				<div class="col-md-4 col-md-offset-4" style="margin-top:20px;">
					<button type="submit" class="btn btn-success btn-block"
						id="register">注册</button>
				</div>
				
				<!-- 错误提示信息 -->
				<div class="col-md-4 col-md-offset-4" style="margin-top:20px;">
					<h4 style="color:red" id="info">${error }</h4>
				</div>

				<div class="col-md-4 col-md-offset-4" style="margin-top:10px;">
					<h5>
						已经有账户?立即<a href="login.html">登录</a>
					</h5>
				</div>
			</form>
		</div>
	</div>


</body>
</html>

