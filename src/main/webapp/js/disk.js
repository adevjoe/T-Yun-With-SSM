$(document).ready(function() {
	init();//TODO 加入正则表达式  验证和过滤错误格式的输入
});

/**
 * 初始化disk 页面
 */
function init(){
	$.getJSON("../disk/list", { path: "根目录", level: 1}, function(list){
		addROW(list);
	});
}

/**
 * 上传文件
 */
function upload(){//TODO 上传进度条的设计
	var parent_path = $("#path-list>li")[$("#path-list>li").length-1].innerHTML;
	var level = $("#path-list>li").length;
	$("#upload-form").ajaxSubmit({
		url: "../disk/upload",
		type: "post",
		enctype: 'multipart/form-data',
		dataType:'json',
		data:{'parent_path':parent_path, 'level':level},
		success: function (data)
		{
			//上传成功后重载页面，以显示上传的文件
			loadFolder(parent_path);
		},
		error: function (data)
		{
			loadFolder(parent_path);
		}
	});
}

/**
 * 加载某个路径的文件信息
 * @param path
 */
function loadFolder(path){
	var level = $("#path-list>li").length;
	$.getJSON("../disk/list", { path: path, level:level}, function(list){
		//正式加载目录
		if (path === "根目录"){//如果是'根目录' ，则没有a标签
			addROW(list);
			$("#path-list").empty();
			$("#path-list").append("<li class='active'>根目录</li>");
		}
		else {//遍历目录列表
			addROW(list);
			$("#path-list").empty();
			$.getJSON("../api/disk/pathList", { path: path, level:level}, function(pathList){
				var str = pathList["object"].split("/");//解析获取的字符串，递归文件路径
				for (var i = 0;i<str.length-1; i++){
					$("#path-list").append("<li class='active'><a href='javascript:;' onclick='loadFolder("+
						"\""+str[i]+"\""+")'>" + str[i] +"</a></li>");
				}
				//最后一个路径不用a标签
				$("#path-list").append("<li class='active'>" + str[str.length-1] +"</li>");
			});
		}

	});
}

/**
 * 新建文件夹
 * @param file_name 文件名
 */
function newFolder(file_name){
	if (file_name === ""){
		$("#something_fail>span").remove();
		$("#something_fail").append("<span>您输入的文件夹名为空，请重新输入！</span>");
		$("#something_fail").removeAttr('hidden');
	}
	else {
		var parent_path = $("#path-list>li")[$("#path-list>li").length-1].innerHTML;
		var level = $("#path-list>li").length;
		$.getJSON("../api/disk/createFolder", { file_name: file_name, parent_path: parent_path, level:level}, function(data){
			loadFolder(parent_path);
			$("#something_success>span").remove();
			$("#something_success").append("<span>"+ data['msg'] +"</span>");
			$("#something_success").removeAttr('hidden');
		});
	}
}

function showFileInfo(){
	alert("hello");
}

/**
 * 添加文件的行
 * @param list 传进json类型的文件信息数组
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
	$("#file-list>tbody").empty();
	//遍历文件信息
	for (var i= 0;i<list.length;i++){
		if (list[i]["is_dir"]>0){//遍历文件夹
			//暂时不用复选框    "<td class='checkbox-td'>"+"<input type='checkbox' name='"+list[i]["id"]+"'>"+"</td>" +
			$("#file-list").append("<tr>"+
				"<td><a href='javascript:;' onclick='loadFolder("+"\""+list[i]['file_name']+"\""+")'>"+
				"<span class='glyphicon glyphicon-folder-close' aria-hidden='true'></span>&nbsp;&nbsp;"+list[i]['file_name']+"</a></td>"+
				"<td>"+formatSize(list[i]["size"])+"</td>" +
				"<td>"+formatDate(list[i]["update_time"])+
				"&nbsp;&nbsp;<span class='glyphicon glyphicon-info-sign' aria-hidden='true'><a href='javascript:;' onclick='showFileInfo()'></span>"+"</td>" +
				"</tr>");
		}
		else {//遍历文件
			//暂时不用复选框    "<td class='checkbox-td'>"+"<input type='checkbox' name='"+list[i]["id"]+"'>"+"</td>" +
			$("#file-list").append("<tr>"+
				"<td>"+
				"<span class='glyphicon glyphicon-list-alt' aria-hidden='true'></span>&nbsp;&nbsp;"+ list[i]["file_name"]+"</td>"+
				"<td>"+formatSize(list[i]["size"])+"</td>" +
				"<td>"+formatDate(list[i]["update_time"])+
				"&nbsp;&nbsp;<span class='glyphicon glyphicon-info-sign' aria-hidden='true'><a href='javascript:;' onclick='showFileInfo()'></span>"+"</td>" +
				"</tr>");
		}
	}
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
