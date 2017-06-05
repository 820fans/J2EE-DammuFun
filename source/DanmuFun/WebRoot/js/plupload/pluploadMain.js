
//**********************************************
//This javascript help you manage your plupload 
//
//Be Cautious this file is imported After plupload.full.min.js
//
//@author YIDA
//
//@place the base
//
//HAHA~~Copyrights reserved
//************************************************


//initailize a 实例 of plupload /laugh~~
//You can leave dropId,flashUrl,sliverUrl NULL
//But it is strongly recommended that you fill in browseId&url AT least
function Plupload(browseId,url,dropId,flashUrl,sliverUrl) {

    return new plupload.Uploader({

        //button of choosing file
        browse_button: browseId,

        //url to post your files
        url: url,

        filters: {

            //Limit the type of Files
            //You can only place LIMIT here,none of other places
            mime_types: [
              { title: "图片文件", extensions: "jpg,jpeg,gif,png,bmp" },
              //{ title: "压缩文件", extensions: "rar,7z,zip" },
              { title: "视频文件", extensions: "mov,avi,flv,mp4,rmvb,f4v,wmv" }
            ],

            //LIMIT the size
            max_file_size: '6097mb', 

            //Refuse duplication of file name
            prevent_duplicates: false, 
        },

        //limit the num of files
        max_file_count: 1,

        //default transport method:multipart/form-data
        multipart: true,
        
        //key&value pairs when posting files
        //default method :POST
        multipart_params: {
            //userId : "1",
            //one: '1',
            //two: '2',
            //three: { //值还可以是一个字面量对象
            //    a: '4',
            //    b: '5'
            //},
            //four: ['6', '7', '8']  //也可以是一个数组
        },

        //retry to upload before sending error message
        max_retries: 3,

        //minimum size of file piece when enable chunk
        //disable chunk upload when seted to 0
        chunk_size: "1mb",

        //create Thumbnails (ONLY effective for pictures)
        resize: {
            //width: 80,
            //height: 80,
            //quality: 90,
            //crop: true // crop to exact dimensions
        },

        //set an element where you can pull&drop your files into the file Queue
        //input: selector
        drop_element: dropId,

        //Allow users to rename the file before upload
        rename: true,

        //enable user to change the priority of the files
        sortable: true,

        // Views to activate
        //I haven't known what these means
        views: {
            list: true,
            thumbs: true, // Show thumbs
            active: 'thumbs',
        },

        //Path of plupload.flash.swf,used if HTML5 is not supported
        flash_swf_url: flashUrl,

        //Path of silverlight,used if HTML5&flash fails
        silverlight_xap_url: sliverUrl,

        /*****************************************
        *In the following I defined several inner functions to help you manage the settings
        *
        */
    });
}

//文件添加监听函数
function setFileAddedListener(uploader) {
	
	var videoTypes=new Array("video/mov","video/avi","video/flv","video/mp4","video/rmvb","video/f4v","video/wmv");
	var pictureTypes=new Array("image/jpg","image/jpeg","image/gif","image/png","image/bmp");
	
    uploader.bind('FilesAdded', function (uploader, files) {
    	
    	//超过2个文件，就删了
    	if(uploader.files.length>2){
    		for (var i in files) 
    			uploader.removeFile(files[i].id);
    		
    		RaiseInform("您只能选择一个封面图片和一个视频",3)
    		return;
    	}
    	
    	//将当前文件显示到列表里面
        for (var i in files) {
        	
        	//限制视频和封面总数
        	pictureCount=0;
        	videoCount=0;
        	for(var j in uploader.files){
        		
        		for(k=0;k<pictureTypes.length;k++){
        			if(uploader.files[j].type==pictureTypes[k])
        				pictureCount++;
        		}

        		for(k=0;k<videoTypes.length;k++){
        			if(uploader.files[j].type==videoTypes[k])
        				videoCount++;
        		}
        	}
        	
        	//超过一张图片
        	if(pictureCount>1){
        		uploader.removeFile(files[i].id);
        		RaiseInform("您已经选择了封面图片",2,1);
        		return;
        	}
        	
        	//超过一个视频
        	if(videoCount>1){
        		uploader.removeFile(files[i].id);
        		RaiseInform("您已经选择了视频",2,1);
        		return;
        	}
        	
            $("#placeFile").append("<tr id='" + files[i].id + "'></tr>");

            var tr=$("#"+files[i].id);

            //Change appearance by JS
            tr.append("<td>" + files[i].name + "</td>");
            tr.append("<td>" + plupload.formatSize(files[i].size).toUpperCase() + "</td>");
            tr.append("<td id='" + files[i].id + "_progress'>" + "</td>");
            tr.append("<td id='" + files[i].id + "_speed'>未传输" + "</td>");
            tr.append("<td id='" + files[i].id + "_status'></td>");
            
            typeCount=0;
            for(k=0;k<pictureTypes.length;k++){
    			if(files[i].type==pictureTypes[k])
    				typeCount++;
    		}
            
            //在状态栏显示上传的类型
            if(typeCount>0)
            	$("#"+files[i].id+"_status").append("<b>封面</b>");
            else
            	$("#"+files[i].id+"_status").append("<b>视频</b>");
            
//            percent=0;
//            $("#"+files[i].id+"_progress").append("<div class='progress'><div class='progress-bar"
//          + " progress-bar-default progress-bar-striped active' role='progressbar'"
//          + " aria-valuenow=" + percent + " aria-valuemin=0 aria-valuemax=100 "
//          + "style='width:" + percent + "%'>" + percent + "%</div></div>");
            
            tr.append("<td><button class='btn btn-primary btn-sm unread' name='" +
                files[i].id+"' id='" + files[i].id + "_delete' onClick='deleteFile("+
                "this)' >删除</button></td>");
            
            //initialize some params to post
            var name = files[i].name;
            var id = files[i].id;
            var num = Math.ceil(files[i].size / uploader.settings.chunk_size);
            var size = uploader.settings.chunk_size;
            
            inf = "checkUploaded.html";
            
            //根据文件唯一ID获取未上传完成的大小
            $.post(inf, {fileId: id,fileName:name ,num:num,size:size}, function (data) {

                obj = JSON.parse(data);
                var percent = obj.percent;
                
                $("#" + obj.id + "_progress").append("<div class='progress'><div class='progress-bar"
                    + " progress-bar-default progress-bar-striped active' role='progressbar'"
                    + " aria-valuenow=" + percent + " aria-valuemin=0 aria-valuemax=100 "
                    + "style='width:" + percent + "%'>" + percent + "%</div></div>");

                //store loaded percent
                offsets[obj.id] = parseInt(obj.loadedSize);
            });
        }

    });
}

//文件上传过程监听函数
function setUploadProgressListener(uploader) {
    uploader.bind('UploadProgress', function (uploader, file) {

        var id = file.id;

        //更新文件上传量信息
        offsets[file.id] = parseInt(file.loaded);

        //更改UI显示
        $("#" + id + "_status").text("正在上传");
        $("#" + id + "_speed").text(plupload.formatSize(uploader.total.bytesPerSec).toUpperCase() + "/s");
        $("#" + id + "_progress .progress-bar").attr("aria-valuenow", file.percent);
        $("#" + id + "_progress .progress-bar").css("width", file.percent + "%");
        $("#" + id + "_progress .progress-bar").text(file.percent + "%");

    });
}

//文件删除监听函数
function setFileDeletedListener(uploader) {
	
    uploader.bind('FilesRemoved', function (uploader, files) {
    	RaiseInform("已从队列移除",2);
    });
}

//单个文件上传结束监听函数
function setFileLoadedListener(uploader) {
    uploader.bind('FileUploaded', function (uploader, file, responseObject) {
        var id = file.id;

        //云端转存结束
        if (responseObject.status == 200) {

            if (responseObject.response == "success") {

                $("#" + id + "_status").text("上传成功");
                $("#" + id + "_speed").text("-");

                $("#" + id + "_progress .progress-bar").removeClass("active");
                $("#" + id + "_progress .progress-bar").removeClass("btn-default");
                $("#" + id + "_progress .progress-bar").addClass("progress-bar-success");
                
                $("#"+id+"_delete").text("已上传");
                
                //RaiseInform("成功上传，请完善视频信息",2);
            }
            else {
                $("#" + id + "_status").text("上传出错");
                $("#" + id + "_speed").text("-");
                $("#" + id + "_progress .progress-bar").addClass("btn-danger");

                RaiseInform("上传出错",1);
            }
        }
        else {
            alert(responseObject.response);
        }
    });
}

//全部文件上传结束监听函数
function setFileCompleteListener(uploader) {
    uploader.bind('UploadComplete', function (uploader, files) {
    	
    	//视频信息--再传一次
        title=$("#newvideotitle").val();
    	type=document.getElementById("newvideotype").value;
        brief=$("#newvideobrief").val();
        
    	$.post("checkFinish.html",{title:title,type:type,brief:brief}, function(data) {
    		if(data=="false"){
    			RaiseInform("文件上传出错，请重新上传视频",2);
    		}
    		else if(data=="success"){
    			RaiseInform("文件上传成功",2);
    			
    			//setTimeout("window.location.reload()",1000);
				setTimeout("window.location.href='space.html?category=video&type=video'",1000);
    		}
    	})
    });
}

//开始上传监听
function addStartUploadListener(uploader, uploadBtn) {
    $(uploadBtn).click(function () {
    	
    	//alert(uploader.settings.url)
    	//开始上传，检查参数是否整齐
        var length = uploader.files.length;

        //上传开始之前，设置loaded大小
        if(length<2){
        	RaiseInform("请选择视频和封面",2);
        	return;
        }
        
        //检查视频信息
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
        
        if (length > 0) {
            for (i = 0; i < length; i++) {

                var file = uploader.files[i];

                //将文件唯一ID
                uploader.settings.multipart_params.fileId=file.id;
                
                if (offsets[file.id] != null && offsets[file.id] != undefined) {
                    file.loaded = offsets[file.id];
                }
            }
            
            //给uploader增加参数
            uploader.settings.multipart_params.videoTitle=title;
            uploader.settings.multipart_params.videoType=type;
            uploader.settings.multipart_params.videoBrief=brief;
            
            
            //调用实例对象的start()方法开始上传文件，当然你也可以在其他地方调用该方法
            uploader.start(); 
        }
        else {
            showMsg("您尚未选择任何文件","fail")
        }
    });
}

//停止上传监听
function addStopUploadListener(uploader, stopBtn) {

    $(stopBtn).click(function () {
        uploader.stop();
        
        flag=0;
        for(var i in uploader.files){
        	if(uploader.files[i].percent<100 && uploader.files[i].percent>0){
        		flag=1;
        	}
        }
        
        if(flag>0)
    	RaiseInform("上传被暂停，部分文件可能尚未传入服务器",3);
    });
}

//删除队列中的文件
function deleteFile(obj) {
    var id = obj.name;

    uploader.removeFile(id);
    $("#" + id).remove();
}
