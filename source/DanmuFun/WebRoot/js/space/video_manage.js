/**
 * 
 */

$(document).ready(function(){
	
	$("#uploadVideo2").click(function(){
		window.location.href="uploadVideo.html";
	})

	//初始化tootip，用于鼠标悬停提示
	$(function () {
		$('[data-toggle="tooltip"]').tooltip()
	});
	
	//去除最后一个下划线
	var items=$(".video-item");
	items.eq(items.length-1).css("border","none");
})

function Download(obj){
	
	var path=$(obj).siblings("#item-path").val();
	RaiseInform("开启下载",1);
	
	window.open("download.html?fileName="+path);
}

function EditVideo(obj){
	RaiseInform("开启编辑",1);
}