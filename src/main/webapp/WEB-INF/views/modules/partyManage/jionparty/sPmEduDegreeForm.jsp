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

<form:form id="inputForm" modelAttribute="sPmEduDegree" action="${ctx}/partyManage/sPmEduDegree/save" method="post"
           class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">学历：</label>
            <%--<div class="controls">
                <form:input path="education" htmlEscape="false" maxlength="64" class="input-xlarge "/>
            </div>--%>
        <div class="controls">
            <sys:treeselect2 id="sPmEduDegreeEducation" name="education" value="${sPmEduDegree.education}"
                             labelName="educationName"
                             labelValue="${fns:getDictLabel(sPmEduDegree.education, 'WHCD', '')}"
                             title="学历" url="/sys/codeTree/treeData?type=WHCD" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">入学日期：</label>
        <div class="controls">
            <input name="entranceDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmEduDegree.entranceDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">毕业院校：</label>
        <div class="controls">
            <form:input path="graduatedUniversity" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">教育类别：</label>
        <div class="controls">
            <form:input path="educationType" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">学位：</label>
            <%--<div class="controls">
                <form:input path="academicDegree" htmlEscape="false" maxlength="64" class="input-xlarge "/>
            </div>--%>
        <div class="controls">
            <sys:treeselect2 id="sPmEduDegreeAcademicDegree" name="academicDegree"
                             value="${sPmEduDegree.academicDegree}"
                             labelName="academicDegreeName"
                             labelValue="${fns:getDictLabel(sPmEduDegree.academicDegree, 'XW', '')}"
                             title="学位" url="/sys/codeTree/treeData?type=XW" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">学位授予日期：</label>
        <div class="controls">
            <input name="degreeGrantDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmEduDegree.degreeGrantDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">毕业日期：</label>
        <div class="controls">
            <input name="graduationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmEduDegree.graduationDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">专业：</label>
        <div class="controls">
            <form:input path="major" htmlEscape="false" maxlength="10" class="input-xlarge "/>
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
                   value="<fmt:formatDate value="${sPmEduDegree.operatorTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        </div>
    </div>
    <div class="form-actions">
        <input id="sPmEduDegreeBtnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
            <%--<input id="sPmEduDegreeBtnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>--%>
    </div>
</form:form>
