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
<form:form id="inputForm" modelAttribute="sPmPb" action="${ctx}/partyManage/regular/sPmPb/save" method="post"
           class="form-horizontal">
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
                   value="<fmt:formatDate value="${sPmPb.conTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">地点：</label>
        <div class="controls">
            <form:input path="place" htmlEscape="false" maxlength="100" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">主持人：</label>
        <div class="controls">
            <form:input path="host" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">记录人：</label>
        <div class="controls">
            <form:input path="noteTaker" htmlEscape="false" maxlength="64" class="input-xlarge "/>
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
        <div class="controls">
            <form:input path="perAtt" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">应到会有表决权的党员人数：</label>
        <span style="color:red;font-size:12px;">(注意：只能输入数字）</span>
        <div class="controls">
            <form:input path="arrNum" htmlEscape="false" maxlength="18" class="input-xlarge  digits"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">实到会有表决权的党员人数：</label>
        <span style="color:red;font-size:12px;">(注意：只能输入数字）</span>
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
        <input id="sPmPbBtnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
            <%--<input id="sPmPbBtnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>--%>
    </div>
</form:form>
</body>
</html>