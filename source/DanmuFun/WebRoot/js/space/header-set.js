/**
 * 
 */

$(document).ready(function(){

	$("#re-select").click(function(){
		cancel();
	})
	
	$("#confirm-upload").click(function(){
		
		if($("#aimPic").attr("src")==""){
			RaiseInform("请先选择图片区域",2);
			return;
		}
		
		var dataUrl=$("#aimPic").attr("src");
		picture = encodeURIComponent(dataUrl);
		
		$.post("uploadHeader.html",{data:picture},function(data){
			if(data=="false"){
				window.location.href="login.html";
			}
			else if(data=="true"){
				window.location.reload();
			}
		});
	})
	
	$("#pic_file").change(function(e){//pic_file是上传控件的id$('#imgRaw').load(function(e) {
		e.preventDefault();

		var file=$("#pic_file").get(0).files[0];
		truewidth=0,trueheight=0;

		// Check file type
		if (file.type.match(/image.*/)){

			if($("#alertNotImage").css("display")!='none'){
				$("#alertNotImage").replaceWith("");
			}

			var url;
			if (window.createObjectURL!=undefined ) { // basic
				url = window.createObjectURL(file) ;
			} else if (window.URL!=undefined && window.URL.createObjectURL!=undefined) { // mozilla(firefox)
				url = window.URL.createObjectURL(file) ;
			} else if (window.webkitURL!=undefined && window.webkitURL.createObjectURL!=undefined) { // webkit or chrome
				url = window.webkitURL.createObjectURL(file) ;
			}

			$("#raw_img").attr("src",url);
		}
		else{
			$("#pictures").append('<div id="alertNotImage" class="alert">文件格式错误</div>');
			$("#pic_file").val('');
			return false;
		}
	})

	$("#raw_img").load(function(e){
		e.preventDefault();

		truewidth=0,trueheight=0;

		var raw_width = this.width;
		var raw_height = this.height;
		$('#raw_size').text('原图 '+raw_width+' x '+raw_height);
		$('#select_size').text("未选择区域");

		// Calculate scaled width and height of the image
		var width = raw_width > raw_height ?
				600 : raw_width / raw_height * 600;
		var height = raw_width <= raw_height ?
				600 : raw_height / raw_width * 600;

		// Show the image in scaled size
		$(this).css({
			width: width + 'px',
			height: height + 'px'
		});
		
		//显示截图区域
		$('#myModal').modal('show');
		
		//调整区域大小
		wrapperHeight=height;
		canvasHeight=document.getElementById("img_preview").height;
		targetHeight=wrapperHeight>canvasHeight?wrapperHeight:canvasHeight;
		$("#modalBodyContainer").css("height",targetHeight+50);
		
		
		// Start image cropping
		$('#raw_img').imgAreaSelect({
			fadeSpeed: 400,
			handles: true,
			aspectRatio: '1:1',
			maxWidth: 600,
			maxHeight: 600,
			minWidth: 10,
			minHeight: 10,
			onSelectChange: function(img, selection){
				truewidth=0,trueheight=0;
				// Get preview canvas and its context
				canvas = document.getElementById("img_preview");
				var ctx = canvas.getContext('2d');

				// Get selection coordinates

				var x1 = selection.x1;
				var y1 = selection.y1;
				var s_width = selection.width;truewidth=s_width;
				var s_height = selection.height;trueheight=s_height;

				// Deal zero selection
				if (s_width == 0) {
					++s_width;
				}
				if (s_height == 0) {
					++s_height;
				}

				// Calculate scale
				var scale = raw_width > raw_height ?
						raw_width * 100 / 600 : raw_height * 100 / 600;
				// Show selected size
				$('#select_size').text('已选择 '+Math.floor(s_width * scale / 100)
						+ ' x ' + Math.floor(s_height * scale / 100));

				ctx.clearRect(0, 0, canvas.width, canvas.height);
				ctx.drawImage(img, x1 * scale / 100, y1 * scale / 100,
						s_width * scale / 100, s_height * scale / 100,
						0, 0, 180,180);
			}
		});
	});
	$("#cancel").click(function(){
		$("#pic_file").val("");
		clearSelection();
	})
	$("#confirm").click(function(){
		if(truewidth==0&& trueheight==0){
			clearSelection();
			$("#pictures").append('<div id="alertNotImage" class="alert">未选择区域</div>');
			$("#pic_file").val("");
		}
		else{
			convertCanvasToImage(canvas);
			truewidth=0,trueheight=0;
			clearSelection();
			$("#pic_file").val("");
		}
	})
	
})


function convertCanvasToImage(canvas) {//图片机制改为点击上传按钮之后检测 而不是选区选定就写入
	var selectimg=$("#pictures span.holder");
	selectimg.children(".pre_img").attr("src",canvas.toDataURL("image/png"));
}

function cancel(){//直接对点上的按钮对应的图片清除
	var selectimg=$("#pictures span.holder");
	selectimg.children(".pre_img").attr("src","");
}

function clearSelection() {
	$('#raw_img').imgAreaSelect({
		instance: true
	}).cancelSelection();

	var canvas = document.getElementById('img_preview');
	var ctx = canvas.getContext('2d');
	ctx.clearRect(0, 0, canvas.width, canvas.height);

	$('#select_size').text('未选择区域');

	$('#myModal').modal('hide');
}
