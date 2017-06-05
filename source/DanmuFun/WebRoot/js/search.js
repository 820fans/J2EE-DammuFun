/**
 * 
 */

$(document).ready(function(){

	type=$_GET["type"];
	orderType=$_GET["orderType"];

	//初始化tootip，用于鼠标悬停提示
	$(function () {
		$('[data-toggle="tooltip"]').tooltip()
	});
	
	//隐藏部分标签
	var noInform=$(".no-inform");
	
	for(i=0;i<noInform.length;i++){
		
		if(noInform.eq(i).siblings(".upperVideoItemWrapper").hasClass("upperVideoItemWrapper")){
			noInform.eq(i).css("display","none");
		}
	}
	
	//设置排序分类提示
	lis = $("#orderRuls li");
	for(i=0;i<lis.length;i++){
		if(lis.eq(i).hasClass(orderType)){
			lis.eq(i).addClass("active");
		}
	}

	//设置搜索分类提示
	if(type=="video")
		$("#typeWrapper li.video").addClass("active");
	else if(type=="upper"){
		$("#typeWrapper li.upper").addClass("active");
	}

	//不同搜索内容
	//搜索视频
	$("#typeWrapper li.video").click(function(){
		$("#type").val("video");
		ReSearch();
	})
	//阿婆主高亮
	$("#typeWrapper li.upper").click(function(){
		$("#type").val("upper");
		ReSearch();
	})

	//不同排序方式搜索
	/***********视频和阿婆主搜索都有默认选项************/
	$("#orderRuls li.default").click(function(){
		$("#orderType").val("default");
		if(type!="video"){
			$("#type").val("upper");
		}
			
		ReSearch();
	})

	/***********搜索视频************/
	$("#orderRuls li.collect").click(function(){
		$("#orderType").val("collect");
		ReSearch();
	})

	$("#orderRuls li.view").click(function(){
		$("#orderType").val("view");
		ReSearch();
	})

	$("#orderRuls li.danmu").click(function(){
		$("#orderType").val("danmu");
		ReSearch();
	});
	/***********************/
	
	/***********搜索阿婆主************/
	$("#orderRuls li.upload").click(function(){
		$("#type").val("upper");
		$("#orderType").val("upload");
		ReSearch();
	});
	
	$("#orderRuls li.fans").click(function(){
		$("#type").val("upper");
		$("#orderType").val("fans");
		ReSearch();
	});
	/***********************/

	//显示视频列表分割线
	videoItems = $(".video-item");
	videoItems.eq(videoItems.length-1).css("border","none");
	
});

function ReSearch(){
	$("#inner_search").click();
}
