/**
 * 
 */

$(document).ready(function(){
	
	videoId=$_GET["videoId"];
	
	//初始化tootip，用于鼠标悬停提示
	$(function () {
		$('[data-toggle="tooltip"]').tooltip()
	});
	
	$("#confirm-edit").click(function(){

        title=$("#newvideotitle").val();
    	type=document.getElementById("newvideotype").value;
        brief=$("#newvideobrief").val();
        
        if(title==null || title==""){
        	RaiseInform("请填写视频标题",2);
        	return;
        }
        
        if(type=="0"){
        	RaiseInform("请填写视频分类",2);
        	return;
        }
        if(brief=="" || brief==null){
        	RaiseInform("请填写视频简介",2);
        	return;
        }
        if(videoId=="" ||videoId==null){
        	RaiseInform("页面出错",1);
        	return;
        }
		
		$.post("editVideoSubmit.html",{videoId:videoId,title:title,type:type,brief:brief} , function(data) {
			
			if(data=="success"){
				RaiseInform("修改信息成功",2);
				setTimeout("window.location.href='space.html?category=video&type=video'",1000);
			}
		})
		
	});
	
})
