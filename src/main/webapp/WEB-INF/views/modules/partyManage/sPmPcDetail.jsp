<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

	<title>表单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
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

	<form:form id="inputForm" modelAttribute="sPmPc" action="" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">会议议题：</label>
			<div class="controls">
				<form:input path="conTop" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">时间：</label>
			<div class="controls">
				<input name="conTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sPmPc.conTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地点：</label>
			<%-- <div class="controls">
				<form:input path="place" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div> --%>
			<div class="controls">
            	<sys:treeselect2 id="sPmPcPlace" name="sPmPc" value="${sPmPc.place}"
                             labelName="placeName"
                             labelValue="${fns:getDictLabel(sPmPc.place, 'JG', '')}"
                             title="地点" url="/sys/codeTree/treeData?type=JG" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
        	</div>
		</div>
		<div class="control-group">
				<label class="control-label">主持人：</label>
				<div class="controls">
					<sys:treeselect2 id="sPmPcHost" name="sPmPc" value="${sPmPc.host}"
                             labelName="hostName"
                             labelValue="${fns:getDictLabel(sPmPc.host, 'CYLB', '')}"
                             title="主持人" url="/sys/codeTree/treeData?type=CYLB" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
				</div>
		</div>
		<div class="control-group">
				<label class="control-label">记录人：</label>
				<div class="controls">
					<sys:treeselect2 id="sPmPcnoteTaker" name="sPmPc" value="${sPmPc.noteTaker}"
                             labelName="noteTakerName"
                             labelValue="${fns:getDictLabel(sPmPc.noteTaker, 'CYLB', '')}"
                             title="记录人" url="/sys/codeTree/treeData?type=CYLB" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
				</div>
		</div>
		<div class="control-group">
			<label class="control-label">入党介绍人：</label>
			<div class="controls">
				<form:input path="perPar" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">列席人：</label>
			<%-- <div class="controls">
				<form:input path="perAtt" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div> --%>
			<div class="controls">
            	<sys:treeselect2 id="sPmPcperAtt" name="sPmPc" value="${sPmPc.perAtt}"
                             labelName="perAttName"
                             labelValue="${fns:getDictLabel(sPmPc.perAtt, 'CYLB', '')}"
                             title="列席人" url="/sys/codeTree/treeData?type=CYLB" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
        	</div>
		</div>
		<div class="control-group">
			<label class="control-label">应到会有表决权的党员人数：</label>
			<div class="controls">
				<form:input path="arrNum" htmlEscape="false" maxlength="18" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实到会有表决权的党员人数：</label>
			<div class="controls">
				<form:input path="actNum" htmlEscape="false" maxlength="18" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参会人员姓名：</label>
			<div class="controls">
				<form:input path="attenList" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">缺席人员姓名：</label>
			<div class="controls">
				<form:input path="absList" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">会议内容：</label>
			<div class="controls">
				<form:input path="meetMin" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>