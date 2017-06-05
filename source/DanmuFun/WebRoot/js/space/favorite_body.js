/**
 * 
 */

$(document).ready(function(){
	
	//初始化tootip，用于鼠标悬停提示
	$(function () {
		$('[data-toggle="tooltip"]').tooltip()
	});
	
	var items=$(".video-item");
	items.eq(items.length-1).css("border","none");
});

/**
 * 取消收藏
 * @param obj
 */
function CancelCollect(obj){
	
	var videoId=$(obj).siblings("input.collect-item-videoId").val();
	
	$.post("uncollectVideo.html", {videoId:videoId}, function(data) {
		if(data=="success"){
			RaiseInform("取消收藏成功",1);
			setTimeout('window.location.reload()',1000);
		}
	})
	
}