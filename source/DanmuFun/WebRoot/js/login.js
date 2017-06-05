/**
 * 
 */

$(document).ready(function(){
	
})

function CheckInput(){
	if($("#account").val()==""){
		$("#info").text("请输入用户名");
		return false;	
	}
	
	if($("#password").val()==""){
		$("#info").text("请输入密码");
		return false;	
	}
	
	if($("#vcode1").val()==""){
		$("#info").text("请输入验证码");
		return false;	
	}
	
}