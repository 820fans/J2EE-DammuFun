/**
 * 
 */

$(document).ready(function(){
	

    //实例化一个plupload对象
    uploader = Plupload("browse", "uploadVideoNewVersion.html", "updateFile");

    //在实例对象上调用init()方法进行初始化
    uploader.init();

    //绑定文件添加监听
    setFileAddedListener(uploader);

    //Binding progress change listener
    setUploadProgressListener(uploader);

    ////文件被删除
    setFileDeletedListener(uploader);

    //Fileuploaded
    setFileLoadedListener(uploader);

    //FileUpload complete
    setFileCompleteListener(uploader);

    //StartUpload
    addStartUploadListener(uploader, "#start_upload");

    //StopUpload
    addStopUploadListener(uploader, "#stop_upload");
    
    
    //监听上传按钮
    $("#start-plus").click(function(){
    	$("#start_upload").click();
    })
	
})

/**
 * 传统上传方式
 * 检查所有必需项是否都已填写或者上传 * @returns {Boolean}
 */
function checkValues(){

	coverImg=$("#coverImg").val();
	videotitle=$("#videotitle").val();
	videotype=document.getElementById("videotype").value;
	videobrief=$("#videobrief").val();
	videofile=$("#videofile").val();
	
	if(coverImg==null || coverImg==""){
		RaiseInform("请选择封面图片",2);
		return false;
	}
	
	if(videotitle.length<=0){
		RaiseInform("请输入视频标题",2);
		return false;
	}
	
	if(videotype=="0"){
		RaiseInform("请输入类别",2);
		return false;
	}
	
	if(videobrief.length<=0){
		RaiseInform("请输入简介",2);
		return false;
	}
	
	if(videofile==null || videofile==""){
		RaiseInform("请选择视频文件",2);
		return false;
	}
}