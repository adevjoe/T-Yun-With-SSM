$(document).ready(function() {
	init();
});

/**
 * 上传文件
 */
function upload(){
	$("#upload-form").ajaxSubmit({
		url: "../disk/upload",
		type: "post",
		async:false,
		enctype: 'multipart/form-data',
		dataType:'json',
		success: function (data)
		{
			//上传成功后重载页面，以显示上传的文件
			window.location.reload();
		},
		error: function (data)
		{
			alert("出错" + data);
		}
	});
}

/**
 * 加载某个路径的文件信息
 * @param path
 */
function loadFolder(path){
	$.getJSON("../disk/list", { path: path}, function(list){
		addROW(list);
		$("#list-path").append("<li>/</li><li class='active'><a href='javascript:;' onclick='loadFolder("+"\""+path+"\""+")'>"+path+"</a></li>");
	});
}

/**
 * 添加文件的行
 * @param list 传进json类型的参数
 */
function addROW(list){
	list = list["object"];
	//把文件夹排到前面
	var num = 0;
	for (var i = 0;i<list.length;i++){
		if (list[i]["is_dir"]>0){
			var ex = list[num];
			//交换
			list[num] = list[i];list[i] = ex;
			num++;
		}
	}
	//先清空列表
	$("#file-list").empty();
	//遍历文件信息
	for (var i= 0;i<list.length;i++){
		if (list[i]["is_dir"]>0){
			$("#file-list").append("<tr>"+"<td><a href='javascript:;' onclick='loadFolder("+"\""+list[i]['file_name']+"\""+")'>"+list[i]['file_name']+"</a></td>"+
				"<td>"+formatSize(list[i]["size"])+"</td>" +
				"<td>"+formatDate(list[i]["update_time"])+"</td>" +
				"</tr>");
		}
		else {
			$("#file-list").append("<tr>"+"<td>"+list[i]["file_name"]+"</td>"+
				"<td>"+formatSize(list[i]["size"])+"</td>" +
				"<td>"+formatDate(list[i]["update_time"])+"</td>" +
				"</tr>");
		}
	}
}

/**
 * 初始化disk 页面
 */
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
	return date.pattern("yyyy-MM-dd HH:mm:ss")
}
