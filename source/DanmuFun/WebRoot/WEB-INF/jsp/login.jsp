<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<link rel="shortcut icon" href="img/logo.png">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/login.css" rel="stylesheet">

<script src="js/jquery-2.0.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<script src="js/basic.js" type="text/javascript"></script>
<script src="js/login.js" type="text/javascript"></script>

<title>登录页面</title>
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
				<img src="img/login.png" class="col-md-offset-4">
			</div>
		</div>

		<div class="row">
			<form action="toIndex.html" method="post" onsubmit="return CheckInput();">
				<div class="form-group">
					<div class="col-md-4 col-md-offset-4" style="margin-top:20px">
						<input type="text" class="form-control" id="account" placeholder="账号"
							name="account">
					</div>
				</div>

				<div class="form-group">
					<div class="col-md-4 col-md-offset-4" style="margin-top:20px">
						<input type="password" class="form-control" id="password" placeholder="密码"
							name="password">
					</div>
					<div class="col-md-1 text-left" style="margin-top:25px">
						<a href="##">忘记密码?</a>
					</div>
				</div>

				<!-- 验证码 -->
				<div class="col-md-2 col-md-offset-4" style="margin-top:20px;">
					<input type="text" class="form-control" name="vcode" id="vcode1"
						placeholder="验证码">
				</div>
				<div class="col-md-2" style="margin-top:10px;">
					<img alt="验证码看不清，换一张" src="vcode.html" onclick="changecode()"
						id="vcode" />
				</div>

				<div class="row">
					<div class="col-md-4 col-md-offset-4" style="margin-top:20px;">
						<button type="submit" class="btn btn-info btn-block"
							id="loginBtn">登录</button>
					</div>
				</div>

				<!-- 错误提示信息 -->
				<div class="col-md-4 col-md-offset-4" style="margin-top:20px;">
					<h4 style="color:red" id="info">${error }</h4>
				</div>

				<div class="col-md-4 col-md-offset-4" style="margin-top:10px;">
					<h5>
						还没有账号?立即<a href="register.html">注册</a>
					</h5>
				</div>
			</form>
		</div>
	</div>

	<!-- 网页脚部 -->

</body>
<script type="text/javascript">

	var i = 1;
	function changecode(){
		document.getElementById('vcode').src="vcode.html?v=" + (i++);
	}
</script>
</html>
