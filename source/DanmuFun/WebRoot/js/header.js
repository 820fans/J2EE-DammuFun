/**
 * 
 */

$(document).ready(function(){
	
	$("#header_login").click(function(){
		 window.location.href="login.html";
	});
	
	$("#header_register").click(function(){
		 window.location.href="register.html";
	});
	
	//头像效果
	$("#header_img").hover(function(){
		$(".header_content").dropdown('toggle');
	})
	
	//用户菜单响应
	$("#my-account").click(function(){
		window.location.href="space.html?category=account&type=safe";
	});
	
	$("#my-video").click(function(){
		window.location.href="space.html?category=video&type=video";
	});
	
	$("#my-favorite").click(function(){
		window.location.href="space.html?category=video&type=favorite";
	});
	
	$("#my-concern").click(function(){
		window.location.href="space.html?category=account&type=concern";
	});
	
	$("#my-logout").click(function(){
		window.location.href="toLogout.html";
	});
	
	//上传视频

	$("#uploadVideo1").click(function(){
		window.location.href="uploadVideo.html";
	})
})