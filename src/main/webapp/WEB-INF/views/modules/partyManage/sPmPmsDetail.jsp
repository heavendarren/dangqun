<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@include file="/WEB-INF/views/include/fileUp.jsp" %>
	
	
<title>党员推荐意见汇总</title>
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
<div id="container">
	<form:form id="inputForm" modelAttribute="sPmPms" action="" 
	method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">推荐时间：</label>
			<div class="controls">
				<input name="recTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sPmPms.recTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">推荐地点：</label>
			<%-- <div class="controls">
				<form:input path="recLoca" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div> --%>
			<div class="controls">
            <sys:treeselect2 disabled="disabled" id="recLoca" name="recLoca" value="${sPmPms.recLoca}"
                             labelName="recLocaName"
                             labelValue="${fns:getDictLabel(sPmPms.recLoca, 'JG', '')}"
                             title="推荐地点" url="/sys/codeTree/treeData?type=JG" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
        	</div>
		</div>
		<div class="control-group">
			<label class="control-label">党员总数：</label>
			<div class="controls">
				<form:input readonly="true" path="tnoPm" htmlEscape="false" maxlength="18" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参加推荐党员人数：</label>
			<div class="controls">
				<form:input readonly="true" path="numPm" htmlEscape="false" maxlength="18" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发出推荐票：</label>
			<div class="controls">
				<form:input readonly="true" path="lssueRec" htmlEscape="false" maxlength="18" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收回推荐票：</label>
			<div class="controls">
				<form:input readonly="true" path="takeRec" htmlEscape="false" maxlength="18" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">有效票：</label>
			<div class="controls">
				<form:input readonly="true" path="validVote" htmlEscape="false" maxlength="18" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">无效票：</label>
			<div class="controls">
				<form:input readonly="true" path="invalidBall" htmlEscape="false" maxlength="18" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">被推荐人结果：</label>
			<div class="controls">
				<form:input readonly="true" path="resultRec" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计票人签字：</label>
			<div class="controls">
				<form:input readonly="true" path="staSign" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">监票人签字：</label>
			<div class="controls">
				<form:input readonly="true" path="scrSign" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支部书记签字：</label>
			<div class="controls">
				<form:input readonly="true" path="branSign" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
	
		
		<div class="controls-group" id="viewImg">
            <label class="control-label">文件名：</label>
            <div class="controls">
                <div hidden class="controls">
                    <form:input readonly="true" path="fileUrl" htmlEscape="false" maxlength="255"
                                class="input-xlarge file_url"/>
                </div>
                <form:input readonly="true" path="fileName" htmlEscape="false" maxlength="255"
                            class="input-xlarge file_name"/>
                <c:choose>
                    <c:when test="${sPmPms.fileUrl == null }">
                        <a href="#" role="button" class="btn">下载</a></c:when>
                    <c:otherwise>
                        <a href="${ctx}/partyManage/public/downfile?fileUrl=${sPmPms.fileUrl}" role="button"
                           class="btn">下载</a>
                    </c:otherwise>
                </c:choose>
            </div>
            <label class="control-label">上传操作人：</label>
            <div class="controls">
                <form:input readonly="true" path="uploader" htmlEscape="false" maxlength="255"
                            class="input-xlarge uploader"/>
            </div>
            <label class="control-label">上传时间：</label>
            <div class="controls">
                <input name="uploadTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                       value="<fmt:formatDate value="${sPmPms.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
            </div>
        </div>
        <div class="form-actions">
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
        </div>
	</form:form>
</div>