/**
 * 
 */

$(document).ready(function(){

	//初始化tootip，用于鼠标悬停提示
	$(function () {
		$('[data-toggle="tooltip"]').tooltip()
	});
	
	var items=$(".upper-item-wrapper");
	items.eq(items.length-1).css("border","none");
	
})


/**
 * 取消关注
 * @param obj
 */
function CancelConcern(obj){
	
	var friend_id=$(obj).siblings("input.concern-item-upperId").val();
	
	$.post("unconcernHim.html", {friend_id:friend_id}, function(data) {
		if(data=="success"){
			RaiseInform("取消关注成功",1);
			setTimeout('window.location.reload()',1000);
		}
	})
	
}