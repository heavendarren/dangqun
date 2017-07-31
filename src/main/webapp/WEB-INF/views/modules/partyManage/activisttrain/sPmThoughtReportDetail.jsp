<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>

<html>
<head>
    <title>思想汇报</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<form:form id="inputForm" modelAttribute="sPmThoughtReport" action=""
           method="post" class="form-horizontal">
    <%--<form:hidden path="id"/>--%>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">编号：</label>
        <div class="controls">
            <form:input path="id" readonly="true" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">提交时间：</label>
        <div class="controls">
            <input name="updateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmThoughtReport.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">主题：</label>
        <div class="controls">
            <form:input path="title" readonly="true" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">概要：</label>
        <div class="controls">
            <form:input path="outline" readonly="true" htmlEscape="false" rows="3" maxlength="64"
                        class="input-xlarge "/>
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
                <c:when test="${sPmThoughtReport.fileUrl == null }">
                    <a href="#" role="button" class="btn">下载</a></c:when>
                <c:otherwise>
                    <a href="${ctx}/partyManage/public/downfile?fileUrl=${sPmThoughtReport.fileUrl}" role="button"
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
                   value="<fmt:formatDate value="${sPmThoughtReport.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        </div>
    </div>
    <%--<div class="form-actions">
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>--%>
</form:form>
</body>
</html>