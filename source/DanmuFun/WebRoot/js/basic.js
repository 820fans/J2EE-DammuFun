

var IP = "";//"http://10.4.20.25:8081"
//var IP = "";
//var IP = "..";
//var URLP = "/ashx/fileHandler.ashx";
var URL = "../../ashx/ClientHandler.ashx?inf=";

//a initialization of Pluploader
var uploader;

var userId = "1";
//param to represent interface 
var inf;
//param to store the percentage of loaded file
var offsets = new Array();

//定义setTimeout执行方法
var TimeFn = null;

//Replace $.post with an encapsulation of $.ajax
$.post = function (a, b, c) {
    $.ajax({
        url: a,
        type: 'POST',
        data: typeof (b) == 'object' ? b : {},
        //contentType: "application/json; charset=utf-8",
        //dataType: 'jsonp',
        cache: false,
        traditional: true,
        success: typeof (b) == 'function' ? b :
				typeof (c) == 'function' ? c : function () { },
        error:function(){
        	alert("请求失败");
            //showMsg("请求失败", "fail");
            //HandleWrapper(0);
        }
    });
};

/**
 * 显示提示信息
 * @param str 要输出的串
 * @param type 输出面板的长宽
 */
//function RaiseInform(str,type){
//	$(".inform-box").css("width", SetToastWidth(type));
//	$(".inform-txt").text(str);
//	$(".inform-box" ).toggle("puff");
//	setTimeout(function() {
//		$(".inform-box" ).toggle( "puff");
//	}, 1000);
//}

function RaiseInform(str,type,length){
	if(length==undefined) length=1000;
	else length=2000;
	$(".inform-box").css("width", SetToastWidth(type));
	$(".inform-txt").text(str);
	$(".inform-box" ).toggle("puff");
	setTimeout(function() {
		$(".inform-box" ).toggle( "puff");
	}, length);
}

//设置Toast宽度
function SetToastWidth(type){
	//type会对应长度
	//默认1
	if(type==1) return "120px";
	else if(type==2) return "240px";
	else if(type==3) return "360px";
}


//消息提醒框
function showMsg(text, des) {
    if (des == "success") {
        $("#msgBoard").attr("class", "btn btn-success btn-block");
        $("#msgBoard").text(text);
        $("#msgBoard").slideDown(200);
        setTimeout('$("#msgBoard").slideUp(250)', 1250);
    }
    else if (des == "fail") {
        $("#msgBoard").attr("class", "btn btn-danger btn-block");
        $("#msgBoard").text(text);
        $("#msgBoard").slideDown(200);
        setTimeout('$("#msgBoard").slideUp(250)', 1250);
    }
}

//模仿PHP中的get函数
$_GET = (function(){
    var url = window.document.location.href.toString();
    var u = url.split("?");
    if(typeof(u[1]) == "string"){
        u = u[1].split("&");
        var get = {};
        for(var i in u){
            var j = u[i].split("=");
            get[j[0]] = j[1];
        }
        return get;
    } else {
        return {};
    }
})();