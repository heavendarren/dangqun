<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>发展对象短期集中培训管理</title>
    <meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/fileUp.jsp" %>
    <script type="text/javascript">
        $(document).ready(function () {

        });
    </script>
</head>
<body>
<div id="container">
    <form:form id="inputForm" modelAttribute="sPmTermTrain" action="" method="post"
               class="form-horizontal">
        <form:hidden path="id"/>
        <sys:message content="${message}"/>
        <%--<div class="control-group">
            <label class="control-label">备注信息：</label>
            <div class="controls">
                <form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
            </div>
        </div>--%>
        <div class="control-group">
            <a href="${ctx}/partyManage/public/termtrain" role="button" class="btn" data-toggle="modal">发展对象短期集中培训考核鉴定表下载</a>
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
                    <c:when test="${sPmTermTrain.fileUrl == null }">
                        <a href="#" role="button" class="btn">下载</a></c:when>
                    <c:otherwise>
                        <a href="${ctx}/partyManage/public/downfile?fileUrl=${sPmTermTrain.fileUrl}" role="button"
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
                       value="<fmt:formatDate value="${sPmTermTrain.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
            </div>
        </div>

        <div class="form-actions">
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
        </div>
    </form:form>
</div>
</body>
</html>