<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>基本情况</title>
    <meta name="decorator" content="default"/>

</head>
<body>
<form:form id="inputForm" modelAttribute="sPmBasic" action="" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <div class="control-group">
        <label class="control-label">姓名：</label>
        <div class="controls">
            <form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge " readonly="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">曾用名：</label>
        <div class="controls">
            <form:input path="oldName" htmlEscape="false" maxlength="64" class="input-xlarge " readonly="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">身份证号：</label>
        <div class="controls">
            <form:input path="idcard" htmlEscape="false" maxlength="18" class="input-xlarge " readonly="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">户口所在地：</label>
        <div class="controls">
            <form:input path="registeredResidence" htmlEscape="false" maxlength="64" class="input-xlarge "
                        readonly="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">性别：</label>
        <div class="controls">
            <sys:treeselect2 disabled="disabled" id="sex" name="sex" value="${sPmBasic.sex}" labelName="sexName"
                             labelValue="${fns:getDictLabel(sPmBasic.sex, 'XB', '')}"
                             title="性别" url="/sys/codeTree/treeData?type=XB" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">民族：</label>
        <div class="controls">
            <sys:treeselect2 disabled="disabled" id="nation" name="nation" value="${sPmBasic.nation}"
                             labelName="nationName"
                             labelValue="${fns:getDictLabel(sPmBasic.nation, 'MZ', '')}"
                             title="民族" url="/sys/codeTree/treeData?type=MZ" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">籍贯：</label>
        <div class="controls">
            <sys:treeselect2 disabled="disabled" id="originPlace" name="originPlace" value="${sPmBasic.originPlace}"
                             labelName="originPlaceName"
                             labelValue="${fns:getDictLabel(sPmBasic.originPlace, 'JG', '')}"
                             title="籍贯" url="/sys/codeTree/treeData?type=JG" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">出生地：</label>
        <div class="controls">
            <form:input path="birthPlace" htmlEscape="false" maxlength="64" class="input-xlarge " readonly="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">健康状况：</label>
        <div class="controls">
            <sys:treeselect2 disabled="disabled" id="health" name="health" value="${sPmBasic.health}"
                             labelName="healthName"
                             labelValue="${fns:getDictLabel(sPmBasic.health, 'GB4767', '')}"
                             title="健康状况" url="/sys/codeTree/treeData?type=GB4767" cssClass="input-small"
                             allowClear="true" notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">家庭出身：</label>
        <div class="controls">
            <sys:treeselect2 disabled="disabled" id="family" name="family" value="${sPmBasic.family}"
                             labelName="familyName"
                             labelValue="${fns:getDictLabel(sPmBasic.family, 'JTCS', '')}"
                             title="家庭出身" url="/sys/codeTree/treeData?type=JTCS" cssClass="input-small"
                             allowClear="true" notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">工作单位：</label>
        <div class="controls">
            <form:input path="workPlace" htmlEscape="false" maxlength="64" class="input-xlarge " readonly="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">工作时间：</label>
        <div class="controls">
            <input name="workTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmBasic.workTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">个人身份：</label>
        <div class="controls">
            <sys:treeselect2 disabled="disabled" id="personIdentity" name="personIdentity"
                             value="${sPmBasic.personIdentity}" labelName="personIdentityName"
                             labelValue="${fns:getDictLabel(sPmBasic.personIdentity, 'ZYFL', '')}"
                             title="个人身份" url="/sys/codeTree/treeData?type=ZYFL" cssClass="input-small"
                             allowClear="true" notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">农民工：</label>
        <div class="controls">
            <form:input path="migrantWorkers" htmlEscape="false" maxlength="64" class="input-xlarge " readonly="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">一线情况：</label>
        <div class="controls">
            <sys:treeselect2 disabled="disabled" id="firstCondition" name="firstCondition"
                             value="${sPmBasic.firstCondition}" labelName="firstConditionName"
                             labelValue="${fns:getDictLabel(sPmBasic.firstCondition, 'YXQK', '')}"
                             title="一线情况" url="/sys/codeTree/treeData?type=YXQK" cssClass="input-small"
                             allowClear="true" notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">新阶层：</label>
        <div class="controls">
            <sys:treeselect2 disabled="disabled" id="newStratum" name="newStratum" value="${sPmBasic.newStratum}"
                             labelName="newStratumName"
                             labelValue="${fns:getDictLabel(sPmBasic.newStratum, 'NEWCLASS', '')}"
                             title="新阶层" url="/sys/codeTree/treeData?type=NEWCLASS" cssClass="input-small"
                             allowClear="true" notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">民主党派：</label>
        <div class="controls">
            <form:input path="democraticParties" htmlEscape="false" maxlength="64" class="input-xlarge "
                        readonly="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">学历：</label>
        <div class="controls">
            <sys:treeselect2 disabled="disabled" id="education" name="education" value="${sPmBasic.education}"
                             labelName="educationName"
                             labelValue="${fns:getDictLabel(sPmBasic.education, 'WHCD', '')}"
                             title="学历" url="/sys/codeTree/treeData?type=WHCD" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">学位：</label>
        <div class="controls">
            <sys:treeselect2 disabled="disabled" id="academicDegree" name="academicDegree"
                             value="${sPmBasic.academicDegree}" labelName="academicDegreeName"
                             labelValue="${fns:getDictLabel(sPmBasic.academicDegree, 'XW', '')}"
                             title="学位" url="/sys/codeTree/treeData?type=XW" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">专业：</label>
        <div class="controls">
            <form:input path="major" htmlEscape="false" maxlength="64" class="input-xlarge " readonly="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">毕业院校：</label>
        <div class="controls">
            <form:input path="graduatedUniversity" htmlEscape="false" maxlength="64" class="input-xlarge "
                        readonly="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">技术职务：</label>
        <div class="controls">
            <form:input path="technicalPost" htmlEscape="false" maxlength="64" class="input-xlarge " readonly="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">职务级别：</label>
        <div class="controls">
            <form:input path="jobLevel" htmlEscape="false" maxlength="64" class="input-xlarge " readonly="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">手机号码：</label>
        <div class="controls">
            <form:input path="phoneNumber" htmlEscape="false" maxlength="64" class="input-xlarge " readonly="true"/>
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
            <form:input path="leaveReason" htmlEscape="false" maxlength="64" class="input-xlarge " readonly="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">出生年月：</label>
        <div class="controls">
            <input name="birthTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmBasic.birthTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">操作人：</label>
        <div class="controls">
            <form:input path="operator" htmlEscape="false" maxlength="64" class="input-xlarge " readonly="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">操作时间：</label>
        <div class="controls">
            <input name="operatorTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmBasic.operatorTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        </div>
    </div>
    <%--<div class="control-group">
        <label class="control-label">备注信息：</label>
        <div class="controls">
            <form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge"
                           readonly="true"/>
        </div>
    </div>--%>
    <div class="form-actions">
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>