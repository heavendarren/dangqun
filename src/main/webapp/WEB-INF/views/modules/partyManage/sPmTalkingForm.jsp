<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>记录谈话内容管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
        $(document).ready(function() {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function(form){
                    //loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function(error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
        });
	</script>
</head>
<body>
<ul class="nav nav-tabs">
	<%--<li><a href="${ctx}/partyManage/sPmTalking/">记录谈话内容列表</a></li>--%>
	<%--<li class="active"><a href="${ctx}/partyManage/sPmTalking/form?id=${sPmTalking.id}">记录谈话内容<shiro:hasPermission name="talking:sPmTalking:edit">${not empty sPmTalking.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="talking:sPmTalking:edit">查看</shiro:lacksPermission></a></li>--%>
</ul><br/>
<form:form id="inputForm" modelAttribute="sPmTalking" action="${ctx}/partyManage/sPmTalking/save" method="post" class="form-horizontal">
	<form:hidden path="id"/>
	<sys:message content="${message}"/>
	<input type="hidden" name="proId" value="${sPmTalking.proId}"/>
	<div class="control-group">
		<label class="control-label">谈话人：</label>
		<div class="controls">
			<form:input path="talker" htmlEscape="false" maxlength="64" class="input-xlarge "/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">谈话时间：</label>
		<div class="controls">
			<input name="talkTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
				   value="<fmt:formatDate value="${sPmTalking.talkTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">谈话地点：</label>
		<div class="controls">
			<form:input path="talkPlace" htmlEscape="false" maxlength="100" class="input-xlarge "/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">谈话纪实：</label>
		<div class="controls">
			<form:input path="talkDoc" htmlEscape="false" maxlength="255" class="input-xlarge "/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">备注：</label>
		<div class="controls">
			<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
		</div>
	</div>
	<div class="form-actions">
			<%--<shiro:hasPermission name="talking:sPmTalking:edit">--%>
		<input id="btnSubmit" class="btn btn-primary" type="button" onclick="formSubmit()" value="保 存"/>
		<script type="text/javascript">
            function formSubmit() {
                if (confirm('注意:敬请确认信息无误后再确认!')) {
                    document.getElementById("inputForm").submit();
                }
            }
		</script>
			<%--
			&nbsp;</shiro:hasPermission>
			--%><input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
</form:form>
</body>
</html>