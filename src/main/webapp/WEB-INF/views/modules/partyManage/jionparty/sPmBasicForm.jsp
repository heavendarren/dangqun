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
<form:form id="inputForm" modelAttribute="sPmBasic" action="${ctx}/partyManage/sPmBasic/save" method="post"
           class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">姓名：</label>
        <div class="controls">
            <form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">曾用名：</label>
        <div class="controls">
            <form:input path="oldName" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">身份证号：</label>
        <div class="controls">
            <form:input path="idcard" htmlEscape="false" maxlength="18" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">户口所在地：</label>
        <div class="controls">
            <form:input path="registeredResidence" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">性别：</label>
        <div class="controls">
            <sys:treeselect2 id="sPmBasicSex" name="sex" value="${sPmBasic.sex}" labelName="sexName"
                             labelValue="${fns:getDictLabel(sPmBasic.sex, 'XB', '')}"
                             title="性别" url="/sys/codeTree/treeData?type=XB" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">民族：</label>
        <div class="controls">
            <sys:treeselect2 id="sPmBasicNation" name="nation" value="${sPmBasic.nation}" labelName="nationName"
                             labelValue="${fns:getDictLabel(sPmBasic.nation, 'MZ', '')}"
                             title="民族" url="/sys/codeTree/treeData?type=MZ" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">籍贯：</label>
        <div class="controls">
            <sys:treeselect2 id="sPmBasicOriginPlace" name="originPlace" value="${sPmBasic.originPlace}"
                             labelName="originPlaceName"
                             labelValue="${fns:getDictLabel(sPmBasic.originPlace, 'JG', '')}"
                             title="籍贯" url="/sys/codeTree/treeData?type=JG" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">出生地：</label>
        <div class="controls">
            <form:input path="birthPlace" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">健康状况：</label>
        <div class="controls">
            <sys:treeselect2 id="sPmBasicHealth" name="health" value="${sPmBasic.health}" labelName="healthName"
                             labelValue="${fns:getDictLabel(sPmBasic.health, 'GB4767', '')}"
                             title="健康状况" url="/sys/codeTree/treeData?type=GB4767" cssClass="input-small"
                             allowClear="true" notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">家庭出身：</label>
        <div class="controls">
            <sys:treeselect2 id="sPmBasicFamily" name="family" value="${sPmBasic.family}" labelName="familyName"
                             labelValue="${fns:getDictLabel(sPmBasic.family, 'JTCS', '')}"
                             title="家庭出身" url="/sys/codeTree/treeData?type=JTCS" cssClass="input-small"
                             allowClear="true" notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">工作单位：</label>
        <div class="controls">
            <form:input path="workPlace" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">工作时间：</label>
        <div class="controls">
            <input name="workTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmBasic.workTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">个人身份：</label>
        <div class="controls">
            <sys:treeselect2 id="sPmBasicPersonIdentity" name="personIdentity" value="${sPmBasic.personIdentity}"
                             labelName="personIdentityName"
                             labelValue="${fns:getDictLabel(sPmBasic.personIdentity, 'ZYFL', '')}"
                             title="个人身份" url="/sys/codeTree/treeData?type=ZYFL" cssClass="input-small"
                             allowClear="true" notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">农民工：</label>
        <div class="controls">
            <form:input path="migrantWorkers" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">一线情况：</label>
        <div class="controls">
            <sys:treeselect2 id="sPmBasicFirstCondition" name="firstCondition" value="${sPmBasic.firstCondition}"
                             labelName="firstConditionName"
                             labelValue="${fns:getDictLabel(sPmBasic.firstCondition, 'YXQK', '')}"
                             title="一线情况" url="/sys/codeTree/treeData?type=YXQK" cssClass="input-small"
                             allowClear="true" notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">新阶层：</label>
        <div class="controls">
            <sys:treeselect2 id="sPmBasicNewStratum" name="newStratum" value="${sPmBasic.newStratum}"
                             labelName="newStratumName"
                             labelValue="${fns:getDictLabel(sPmBasic.newStratum, 'NEWCLASS', '')}"
                             title="新阶层" url="/sys/codeTree/treeData?type=NEWCLASS" cssClass="input-small"
                             allowClear="true" notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">民主党派：</label>
        <div class="controls">
            <form:input path="democraticParties" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">学历：</label>
        <div class="controls">
            <sys:treeselect2 id="sPmBasicEducation" name="education" value="${sPmBasic.education}"
                             labelName="educationName"
                             labelValue="${fns:getDictLabel(sPmBasic.education, 'WHCD', '')}"
                             title="学历" url="/sys/codeTree/treeData?type=WHCD" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">学位：</label>
        <div class="controls">
            <sys:treeselect2 id="sPmBasicAcademicDegree" name="academicDegree" value="${sPmBasic.academicDegree}"
                             labelName="academicDegreeName"
                             labelValue="${fns:getDictLabel(sPmBasic.academicDegree, 'XW', '')}"
                             title="学位" url="/sys/codeTree/treeData?type=XW" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">专业：</label>
        <div class="controls">
            <form:input path="major" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">毕业院校：</label>
        <div class="controls">
            <form:input path="graduatedUniversity" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">技术职务：</label>
        <div class="controls">
            <form:input path="technicalPost" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">职务级别：</label>
        <div class="controls">
            <form:input path="jobLevel" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">手机号码：</label>
        <div class="controls">
            <form:input path="phoneNumber" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">退休时间：</label>
        <div class="controls">
            <input name="retirementTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmBasic.retirementTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">离岗时间：</label>
        <div class="controls">
            <input name="departureTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmBasic.departureTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">离岗原因：</label>
        <div class="controls">
            <form:input path="leaveReason" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">出生年月：</label>
        <div class="controls">
            <input name="birthTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmBasic.birthTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
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
                   value="<fmt:formatDate value="${sPmBasic.operatorTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        </div>
    </div>
    <div class="form-actions">
        <input id="sPmBasicBtnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
            <%--<input id="sPmBasicBtnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>--%>
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