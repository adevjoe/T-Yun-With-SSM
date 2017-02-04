/**
 * Created by Administrator on 2016/9/27.
 */ 
$(document).ready(function() {
	/*
	检测注册时密码是否一致
	 */
	$("#btn-signup").attr('disabled', 'true');
	$("#btn-signup").text("密码不一致，请检查！");
	$("#password2").mouseleave(function() {
		if (($("#password1").val() !="") && ($("#password1").val() == $("#password2").val())) {
			$("#btn-signup").removeAttr('disabled');
			$("#btn-signup").text("立即注册");
		}else{
			$("#btn-signup").attr('disabled', 'true');
			$("#btn-signup").text("密码不一致，请检查！");
		}
	});

	/*
	复制到剪切板
	 */
	var client = new ZeroClipboard($(".copy-button"));

	client.on( "ready", function( readyEvent ) {
	  client.on( "aftercopy", function( event ) {
		$(".model-copy").attr('aria-hidden', 'false');
	  } );
	} );

});