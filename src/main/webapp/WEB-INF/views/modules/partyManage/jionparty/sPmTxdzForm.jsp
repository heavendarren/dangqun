<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title></title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function (form) {
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
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
<sys:message content="${message}"/>
<form:form id="inputForm" modelAttribute="sPmTxdz" action="${ctx}/partyManage/sPmTxdz/save" method="post"
           class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">工作单位：</label>
        <div class="controls">
            <form:input path="workUnit" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">单位地址：</label>
        <div class="controls">
            <form:input path="unitAddress" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">单位邮编：</label>
        <div class="controls">
            <form:input path="postCode" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">工作电话：</label>
        <div class="controls">
            <form:input path="workPhone" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">家庭地址：</label>
        <div class="controls">
            <form:input path="homeAddress" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">住宅邮编：</label>
        <div class="controls">
            <form:input path="homeZip" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">住宅电话：</label>
        <div class="controls">
            <form:input path="homePhone" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">电子邮箱：</label>
        <div class="controls">
            <form:input path="email" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">填报人：</label>
        <div class="controls">
            <form:input readonly="true" path="entryPerson" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">填报时间：</label>
        <div class="controls">
            <input name="entryTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmTxdz.entryTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        </div>
    </div>
    <div class="form-actions">
        <input id="sPmTxdzBtnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
            <%--<input id="sPmTxdzBtnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>--%>
        <%--<c:choose>
            <c:when test="${recordId == null }">
                <a href="${ctx}/partyManage/DQRecord/record" role="button" class="btn">返回</a></c:when>
            <c:otherwise>
                <a href="${ctx}/partyManage/DQRecord/schedule/?id=${recordId}" role="button" class="btn">返回</a>
            </c:otherwise>
        </c:choose>--%>
    </div>
</form:form>
</body>
</html>