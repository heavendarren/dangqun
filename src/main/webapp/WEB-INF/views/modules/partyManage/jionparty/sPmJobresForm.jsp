<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        //$("#name").focus();
        $("#inputForm").validate({
            submitHandler: function (form) {
                loading('正在保存，请稍等...');
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

<form:form id="inputForm" modelAttribute="sPmJobres" action="${ctx}/partyManage/sPmJobres/save" method="post"
           class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group" id="box">
        <label class="control-label">工作简历：</label>
        <div class="controls">
            <form:textarea path="resume" htmlEscape="false" rows="4" maxlength="1000" class="input-xxlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">填报人：</label>
        <div class="controls">
            <form:input readonly="true" path="fillPeople" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">填报时间：</label>
        <div class="controls">
            <input name="fillTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmJobres.fillTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        </div>
    </div>
    <div class="form-actions">
        <input id="sPmJobresBtnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
            <%--<input id="sPmJobresBtnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>--%>
    </div>
</form:form>
