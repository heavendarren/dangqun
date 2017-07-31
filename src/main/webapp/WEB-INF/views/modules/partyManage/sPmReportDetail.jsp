<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@include file="/WEB-INF/views/include/fileUp.jsp" %>
	<title></title>
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
	<form:form id="inputForm" modelAttribute="sPmReport" action="" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<h3>政审报告上传：</h3>
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
                    <c:when test="${sPmReport.fileUrl == null }">
                        <a href="#" role="button" class="btn">下载</a></c:when>
                    <c:otherwise>
                        <a href="${ctx}/partyManage/public/downfile?fileUrl=${sPmReport.fileUrl}" role="button"
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
                       value="<fmt:formatDate value="${sPmReport.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
            </div>
        </div>

        <div class="form-actions">
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
        </div>
	</form:form>
</div>
