<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>会议记录管理</title>
<meta name="decorator" content="default" />
</head>
<body>
		<form:form id="inputForm" modelAttribute="sPmMinutes"
			action="" method="post"
			class="form-horizontal">
			<form:hidden path="id" />
			<div class="control-group">
				<label class="control-label">会议议题：</label>
				<div class="controls">
					<form:input path="conTop" htmlEscape="false" maxlength="255"
						class="input-xlarge " readonly="readonly"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">时间：</label>
				<div class="controls">
					<input name="conTime" type="text" readonly="readonly"
						maxlength="20" class="input-medium Wdate "
						value="<fmt:formatDate value="${sPmMinutes.conTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">地点：</label>
				<div class="controls">
					<form:input path="place" htmlEscape="false" maxlength="100"
						class="input-xlarge " readonly="readonly"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">主持人：</label>
				<div class="controls">
				<input type="text" readonly="readonly" value="${sPmMinutes.hostName}" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">记录人：</label>
				<div class="controls">
				<input type="text" readonly="readonly" value="${sPmMinutes.noteTakerName}" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">应到人数：</label>
				<div class="controls">
					<form:input path="arrNum" htmlEscape="false" maxlength="18"
						class="input-xlarge  digits" readonly="readonly"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">实到人数：</label>
				<div class="controls">
					<form:input path="actNum" htmlEscape="false" maxlength="18"
						class="input-xlarge  digits" readonly="readonly"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">参加人员名单：</label>
				<div class="controls">
					<input id="userDataRelation" type="text" readonly="readonly" value="${sPmMinutes.attenList}" />
					</div>
			</div>
			<div class="control-group">
				<label class="control-label">缺席人员名单：</label>
				<div class="controls">
					<input id="userDataRelation1" type="text" readonly="readonly" value="${sPmMinutes.absList}" />
					</div>
			</div>
			<div class="control-group">
				<label class="control-label">缺席原因：</label>
				<div class="controls">
					<form:input path="absReason" htmlEscape="false" maxlength="255"
						class="input-xlarge " readonly="readonly"/>
				</div>
			</div>
			<div class="control-group" id="fileOperat">
				<label class="control-label">会议纪要：</label>
				<div class="controls">
					<form:textarea path="meetMin" htmlEscape="false" rows="4"
						maxlength="255" class="input-xxlarge " readonly="readonly"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">备注信息：</label>
				<div class="controls">
					<form:textarea path="remarks" htmlEscape="false" rows="4"
						maxlength="255" class="input-xxlarge " readonly="readonly"/>
				</div>
			</div>
			<div class="form-actions">
			 <input id="btnCancel" class="btn" type="button"value="返 回" onclick="history.go(-1)" />
			</div>
		</form:form>
</html>