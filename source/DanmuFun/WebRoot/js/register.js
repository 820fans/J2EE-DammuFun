
var account;
var password;
var confirm_password;

$(document).ready(function () {

	//隐藏所有提示符
	$("#account_plus").hide();
	$("#password_plus").hide();
	$("#confirm_password_plus").hide();

	$("#account").keyup(function(){
		if($("#account").val().length>0)
			$("#account_plus").show();
		else
			$("#account_plus").hide();
	});

	$("#password").keyup(function(){
		if($("#password").val().length>0){
			if($("#password").val().length<6){
				$("#password_plus").show();
				$("#password_plus span").eq(2).show();
				$("#password_plus span").eq(2).siblings().hide();
			}
			else{
				$("#password_plus span").eq(0).show();
				$("#password_plus span").eq(0).siblings().hide();
			}
		}
		else{
			$("#password_plus").hide();
		}
	});

	$("#confirm_password").keyup(function(){

		if($("#confirm_password").val().length>0){
			if($("#password").val()!=$("#confirm_password").val()){
				$("#confirm_password_plus").show();
				$("#confirm_password_plus span").eq(1).show();
				$("#confirm_password_plus span").eq(1).siblings().hide();
			}
			else{
				$("#confirm_password_plus span").eq(0).show();
				$("#confirm_password_plus span").eq(0).siblings().hide();
			}
		}
		else{
			$("#confirm_password_plus").hide();
		}
	});

});

//校验数据格式
function Register() {
	
	account=$("#account").val();
	password=$("#password").val();
	confirm_password=$("#confirm_password").val();
	introduce=$("#introduce").val();

	if (account.length < 1) {
		$("#info").text("用户名格式错误");
		return false;
	}
	if (password.length < 6 ) {
		$("#info").text("密码至少6位");
		return false;
	}
	else if(password.length >= 6 && (confirm_password != password)){
		$("#info").text("两次密码输入不一致");
		return false;
	}
}