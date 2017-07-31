<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>

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

<form:form id="inputForm" modelAttribute="sPmWorkPost" action="${ctx}/partyManage/sPmWorkPost/save" method="post"
           class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">工作岗位：</label>
        <div class="controls">
            <form:input path="workPost" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">开始时间：</label>
        <div class="controls">
            <input name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmWorkPost.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">截止时间：</label>
        <div class="controls">
            <input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmWorkPost.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">个人身份：</label>
            <%--<div class="controls">
                <form:input path="personIdentity" htmlEscape="false" maxlength="64" class="input-xlarge "/>
            </div>--%>
        <div class="controls">
            <sys:treeselect2 id="sPmWorkPostPersonIdentity" name="personIdentity" value="${sPmWorkPost.personIdentity}"
                             labelName="personIdentityName"
                             labelValue="${fns:getDictLabel(sPmWorkPost.personIdentity, 'ZYFL', '')}"
                             title="个人身份" url="/sys/codeTree/treeData?type=ZYFL" cssClass="input-small"
                             allowClear="true" notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">一线情况：</label>
            <%--<div class="controls">
                <form:input path="firstCondition" htmlEscape="false" maxlength="64" class="input-xlarge "/>
            </div>--%>
        <div class="controls">
            <sys:treeselect2 id="sPmWorkPostFirstCondition" name="firstCondition" value="${sPmWorkPost.firstCondition}"
                             labelName="firstConditionName"
                             labelValue="${fns:getDictLabel(sPmWorkPost.firstCondition, 'YXQK', '')}"
                             title="一线情况" url="/sys/codeTree/treeData?type=YXQK" cssClass="input-small"
                             allowClear="true" notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">新阶层：</label>
            <%--<div class="controls">
                <form:input path="newStratum" htmlEscape="false" maxlength="64" class="input-xlarge "/>
            </div>--%>
        <div class="controls">
            <sys:treeselect2 id="sPmWorkPostNewStratum" name="newStratum" value="${sPmWorkPost.newStratum}"
                             labelName="newStratumName"
                             labelValue="${fns:getDictLabel(sPmWorkPost.newStratum, 'NEWCLASS', '')}"
                             title="新阶层" url="/sys/codeTree/treeData?type=NEWCLASS" cssClass="input-small"
                             allowClear="true" notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">填报人：</label>
        <div class="controls">
            <form:input readonly="true" path="operator" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">填报时间：</label>
        <div class="controls">
            <input name="operatorTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmWorkPost.operatorTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        </div>
    </div>
    <div class="form-actions">
        <input id="sPmWorkPostBtnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
            <%--<input id="sPmWorkPostBtnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>--%>
    </div>
</form:form>