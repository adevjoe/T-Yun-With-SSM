$(document).ready(function() {
	init();
});

function upload(){
	$("#upload-form").ajaxSubmit({
		url: "../disk/upload",
		type: "post",
		async:false,
		enctype: 'multipart/form-data',
		dataType:'json',
		success: function (data)
		{
			window.location.reload();
		},
		error: function (data)
		{
			alert("出错" + data);
		}
	});
}

function loadFolder(path){
	$("#list-path").empty();
	$.getJSON("../disk/list", { path: path}, function(list){
		addROW(list);
		$("#list-path").append("");
	});
}

function addROW(list){
	$("#file-list").empty();
	for (var i= 0;i<list.length;i++){
		$("#file-list").append("<tr>"+"<td>"+list[i]["file_name"]+"</td>"+
			"<td>"+formatSize(list[i]["size"])+"</td>" +
			"<td>"+formatDate(list[i]["update_time"])+"</td>" +
			"</tr>");
	}
}

function init(){
	$.getJSON("../disk/list", { path: "/"}, function(list){
		addROW(list);
	});
}

/**
 * 格式化文件大小
 * @param size
 * @returns {*}
 */
function formatSize(size){
	if(null==size||size==''){
		return "";
	}
	if (size<1024){
		return size.toFixed(2) + "Bytes";
	}
	else if (size<Math.pow(1024, 2)){
		size = size/1024;
		return size.toFixed(2) + "KB";
	}
	else if (size<Math.pow(1024, 3)){
		size = size/1024/1024;
		return size.toFixed(2) + "MB";
	}
	else if (size<Math.pow(1024, 4)){
		size = size/1024/1024/1024;
		return size.toFixed(2) + "GB";
	}
}

/**
 * 格式化日期
 * @param str
 */
function formatDate(str){
	Date.prototype.pattern=function(fmt) {
	var o = {
		"M+" : this.getMonth()+1, //月份
		"d+" : this.getDate(), //日
		"h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时
		"H+" : this.getHours(), //小时
		"m+" : this.getMinutes(), //分
		"s+" : this.getSeconds(), //秒
		"q+" : Math.floor((this.getMonth()+3)/3), //季度
		"S" : this.getMilliseconds() //毫秒
	};
	var week = {
		"0" : "/u65e5",
		"1" : "/u4e00",
		"2" : "/u4e8c",
		"3" : "/u4e09",
		"4" : "/u56db",
		"5" : "/u4e94",
		"6" : "/u516d"
	};
	if(/(y+)/.test(fmt)){
		fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	}
	if(/(E+)/.test(fmt)){
		fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);
	}
	for(var k in o){
		if(new RegExp("("+ k +")").test(fmt)){
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
		}
	}
	return fmt;
	}
	var date = new Date(str);
	return date.pattern("yyyy-MM-dd hh:mm:ss")
}
