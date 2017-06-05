/**
 * 
 */

//全屏交由screenfull.js完成
//video标签全屏事件
//function launchFullScreen(element) {
//	if(element.requestFullScreen) {
//		element.requestFullScreen();
//	} else if(element.mozRequestFullScreen) {
//		element.mozRequestFullScreen();
//	} else if(element.webkitRequestFullScreen) {
//		element.webkitRequestFullScreen();
//	}
//}

var videoId;

$(document).ready(function(){
	
	//从地址栏获取videoId
	videoId=$_GET["videoId"];
	
	//初始化tootip，用于鼠标悬停提示
	$(function () {
		$('[data-toggle="tooltip"]').tooltip()
	});
	
	var oriSrc=$("#collect-img").attr("src");
	
	//收藏按钮 $( selector ).hover( handlerIn, handlerOut )
	//鼠标悬停提示
	$("#collect-img").hover(function(){
		$("#collect-img").attr("src", "img/faved.png");
	},function(){
		$("#collect-img").attr("src", oriSrc);
	});
	
	//更新关注状态
	//关注某人
	$("#concern-him").click(function(){
		
		if($("#concern-him").hasClass("concern-btn")){
		
		$.post("concernHim.html", {friend_id:$("#upperId").val()}, function(data) {
			if(data=="success"){
				RaiseInform("关注成功",1);
				$("#concern-him b").text("-");
				$("#concern-him").removeClass("concern-btn");
				$("#concern-him").addClass("unconcern-btn");
			}
			else if(data=="false"){
				RaiseInform("您尚未登录，无法关注",2);
			}
		})
		}
		else if($("#concern-him").hasClass("unconcern-btn")){

			$.post("unconcernHim.html", {friend_id:$("#upperId").val()}, function(data) {
				if(data=="success"){

					RaiseInform("取消关注成功",1);
					$("#concern-him b").text("+");
					$("#concern-him").removeClass("unconcern-btn");
					$("#concern-him").addClass("concern-btn");
				}
			})	
		}
	});
	
	//更改收藏状态
	$("#collect-img").click(function(){
		
		if($("#collect-img").hasClass("fav-icon")){
			
			$.post("collectVideo.html", {videoId:videoId}, function(data) {
				if(data=="success"){
					//成功提示
					RaiseInform("收藏成功",1);
					
					//改变按键功能
					$("#collect-img").removeClass("fav-icon");
					$("#collect-img").addClass("faved-icon");
					
					//改变图标
					$("#collect-img").attr("src", "img/faved.png");
					
					//改变缓存图标地址
					oriSrc=$("#collect-img").attr("src");
					
					//改变鼠标悬停字样
					$("#collect-img").attr({title:"取消收藏"});
				}
				else if(data=="false"){
					RaiseInform("您尚未登录，无法收藏",2);
				}
			})
		}
		else{
			//var r=confirm("是否取消收藏？");
			//if (!r) return;
			
			$.post("uncollectVideo.html", {videoId:videoId}, function(data) {
				if(data=="success"){

					RaiseInform("取消收藏成功",1);
					$("#collect-img").attr("src", "img/fav.png");
					$("#collect-img").removeClass("faved-icon");
					$("#collect-img").addClass("fav-icon");
					
					oriSrc=$("#collect-img").attr("src");

					$("#collect-img").attr({title:"收藏本视频"});
				}
			})
		}
		
	});
	
	//用户评论
	$("#comment-inputbox").keyup(function(){
		var comment_txt = $("#comment-inputbox").val();

		if(comment_txt.length>0){
			$("#comment-inputbox-wrapper").removeClass("has-error");
			return;
		}
	})
	//评论提交
	$("#comment-inputbtn").click(function(){
		
		var comment_txt = $("#comment-inputbox").val();
		
		if(comment_txt==null || comment_txt==""){
			$("#comment-inputbox-wrapper").addClass("has-error");
			return;
		}
		
		$.post("addComment.html", {videoId:videoId,content:comment_txt}, function(data) {
			
			obj=JSON.parse(data);
			
			if(obj.id!=null){
				
				//去除没有评论的提示
				var noComment=$("#no-comment-inform");
				if(noComment!=null && noComment!=undefined){
					noComment.remove();
				}
				
				//显示发布的评论
				$("#comment-items-wrapper").prepend(
				'<div class="container-fluid col-md-12 comment-item"><div class="col-md-2">'+
				'<img class=" comment-header-img" alt="评论用户头像" src="'+obj.header+
				'" width="60px" height="60px"></div>'+
				'<div class="col-md-10 comment-info">'+
					'<div class="container-fluid">'+
						'<span class=" commenter-account">'+obj.account+'</span></div>'+
					'<div class="container-fluid comment-content">'+
						'<span>'+obj.content+'</span></div>'+
					'<div class="container-fluid comment-detail">'+
						'<span class="pull-left">'+
							'<span class="comment-time">'+obj.commentTime+'</span></span>'+
						'<span class="col-md-3">'+
							'<input type="hidden" id="comment-id" value="'+obj.id+'" />'+
							'<input type="hidden" id="comment-self" value="" >'+
							'<span class="comment-zan" id="comment-zan" onClick="ZanComment(this)">'+
							 '<span class="self"></span>赞(<span class="zan-num">'+obj.zanNum+
							'</span>)</span></span>'+
						'<span class="col-md-3">'+
							'<span class=" comment-reply">评论</span></span>'+
					'</div></div></div>');
				
				$("#comment-inputbox").val("");
				RaiseInform("成功发布评论",1);
				
			}
		})
		
	});
	
	//给评论点赞
	$("#comment-zan").click(function(){
		
	})
	

//	//播放暂停快捷键
//	function fnKeyup(event)
//	{
//		alert("ssss")
//		var b = document.getElementById("myButton");
//	}
//
//	// 捕获系统的Keyup事件
//	// 如果是Mozilla系列浏览器
//	if (document.addEventListener){
//		document.addEventListener("keyup",fnKeyup,false);
//	}
//	else
//	document.attachEvent("onkeyup",fnKeyup);
	
});

function ZanComment(obj){
	
	var comment_id=$(obj).siblings("#comment-id").val();
	var self=$(obj).siblings("#comment-self").val();
	
	//说明是自己赞过的
	if(self.length==0){
		
		$.post("zanComment.html", {comment_id:comment_id}, function(data) {
			if(data=="success"){

				RaiseInform("成功赞评论",1);
				$(obj).siblings("#comment-self").val("已");
				$(obj).children("span.self").text("已");
				
				num=parseInt($(obj).children("span.zan-num").text());
				$(obj).children("span.zan-num").text(num+1)
			}
			else if(data=="false"){
				RaiseInform("您尚未登录，不能操作",2);
			}
		})
	}
	else{
		//说明没有赞过，或者没有登录
		$.post("unzanComment.html", {comment_id:comment_id}, function(data) {
			if(data=="success"){

				RaiseInform("成功取消赞",1);
				$(obj).siblings("#comment-self").val("");
				$(obj).children("span.self").text("");
				
				num=parseInt($(obj).children("span.zan-num").text());
				$(obj).children("span.zan-num").text(num-1)
			}
			else if(data=="false"){
				RaiseInform("您尚未登录，不能操作",2);
			}
		})		
		
	}
	
}
