/**
 * 
 */

$(document).ready(function(){

	$("#my-introduce").attr("disabled", "true");
	
	//修改个人简介
	$("#edit-introduce").click(function(){
		//用户要对其进行编辑
		if($("#my-introduce").attr("disabled")!=null){
			$("#my-introduce").removeAttr("disabled");
		}
		//用户完成对简介的编辑，此时应当上传
		else{
			$("#my-introduce").attr("disabled", "true");
			
			if($("#my-introduce").val()==""){
				RaiseInform("修改的简介不能为空",2);
				return;
			}
			
			$.post("editIntroduce.html", {introduce:$("#my-introduce").val()}, function(data) {
				
				if(data=="false"){
					RaiseInform("登录状态已失效",2);
					return;
				}
				else if(data=="success"){
					RaiseInform("修改简介成功",2);
					//setTimeout("window.location.reload();",1000);
					return;
				}
			})
			
		}
			
	});
	
	$("#confirm-edit").click(function(){
		
		if($("#originalPsw").val()==""){
			RaiseInform("请输入原始密码",2);
			return;
		}
		
		if($("#newPsw").val()==""){
			RaiseInform("请输入新密码",2);
			return;
		}
		
		if($("#confirmPsw").val()==""){
			RaiseInform("请确认新密码",2);
			return;
		}
		
		if($("#newPsw").val()!=$("#confirmPsw").val()){
			RaiseInform("两次输入密码不一致",2);
			return;
		}
		
		$.post("editPassword.html", {originalPsw:$("#originalPsw").val(),newPsw:$("#newPsw").val()}, function(data) {
			if(data=="wrongPsw"){
				RaiseInform("原始密码错误",2);
				return;
			}
			else if(data=="false"){
				RaiseInform("登录状态已失效",2);
				return;
			}
			else if(data=="weak"){
				RaiseInform("新密码不足6位",2);
				return;
			}
			else if(data=="success"){
				RaiseInform("修改密码成功",2);
				window.location.reload();
				return;
			}
		})
	})
	
})