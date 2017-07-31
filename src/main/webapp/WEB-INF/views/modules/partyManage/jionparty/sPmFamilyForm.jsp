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

<form:form id="inputForm" modelAttribute="sPmFamily" action="${ctx}/partyManage/sPmFamily/save" method="post"
           class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">成员姓名：</label>
        <div class="controls">
            <form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">性别：</label>
            <%--<div class="controls">
                <form:input path="sex" htmlEscape="false" maxlength="255" class="input-xlarge "/>
            </div>--%>
        <div class="controls">
            <sys:treeselect2 id="sPmFamilySex" name="sex" value="${sPmFamily.sex}" labelName="sexName"
                             labelValue="${fns:getDictLabel(sPmFamily.sex, 'XB', '')}"
                             title="性别" url="/sys/codeTree/treeData?type=XB" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">出生年月：</label>
        <div class="controls">
            <input name="birthDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmFamily.birthDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">政治面貌：</label>
            <%--<div class="controls">
                <form:input path="polLook" htmlEscape="false" maxlength="255" class="input-xlarge "/>
            </div>--%>
        <div class="controls">
            <sys:treeselect2 id="sPmFamilyPolLook" name="polLook" value="${sPmFamily.polLook}" labelName="polLookName"
                             labelValue="${fns:getDictLabel(sPmFamily.polLook, 'ZZMM', '')}"
                             title="政治面貌" url="/sys/codeTree/treeData?type=ZZMM" cssClass="input-small"
                             allowClear="true"
                             notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">工作单位及职务：</label>
        <div class="controls">
            <form:input path="workPos" htmlEscape="false" maxlength="255" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">个人身份：</label>
            <%--<div class="controls">
                <form:input path="identity" htmlEscape="false" maxlength="255" class="input-xlarge "/>
            </div>--%>
        <div class="controls">
            <sys:treeselect2 id="sPmFamilyIdentity" name="identity" value="${sPmFamily.identity}"
                             labelName="identityName"
                             labelValue="${fns:getDictLabel(sPmFamily.identity, 'ZYFL', '')}"
                             title="个人身份" url="/sys/codeTree/treeData?type=ZYFL" cssClass="input-small"
                             allowClear="true" notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">关系类别：</label>
            <%--<div class="controls">
                <form:input path="relCate" htmlEscape="false" maxlength="255" class="input-xlarge "/>
            </div>--%>
        <div class="controls">
            <sys:treeselect2 id="sPmFamilyRelCate" name="relCate" value="${sPmFamily.relCate}" labelName="relCateName"
                             labelValue="${fns:getDictLabel(sPmFamily.relCate, 'GSLB', '')}"
                             title="关系类别" url="/sys/codeTree/treeData?type=GSLB" cssClass="input-small"
                             allowClear="true" notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">与本人关系：</label>
        <div class="controls">
            <form:input path="relWe" htmlEscape="false" maxlength="255" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">填报人：</label>
        <div class="controls">
            <form:input readonly="true" path="entryPeo" htmlEscape="false" maxlength="255" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">填报时间：</label>
        <div class="controls">
            <input name="entryDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmFamily.entryDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        </div>
    </div>
    <div class="form-actions">
        <input id="sPmFamilyBtnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
            <%--<input id="sPmFamilyBtnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>--%>
    </div>
</form:form>
