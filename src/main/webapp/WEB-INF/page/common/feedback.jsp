<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="contactable">
</div>
<script type="text/javascript">
$(function(){
	 $('#contactable').contactable({
		 url:'${pageContext.request.contextPath}/feedback!save.bc',
		 name: '昵称',
		 submit: '提交',
		 email: 'Email',
		 message : '我说',
		 subject : '我的问题/反馈',
		 recievedMsg : '谢谢你的反馈!  ^_^',
		 notRecievedMsg : '对不起，您的反馈暂时还不能提交，请稍后再试...',
		 disclaimer: '感谢您帮助我们成长，我们会及时通过email回复您！',
		 hideOnSubmit: true
	 });
	});
</script>


