<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>预备党员备案</title>
    <meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/fileUp.jsp" %>
    <script type="text/javascript">
        $(document).ready(function () {

        });
    </script>
</head>
<body>
<form:form id="inputForm" modelAttribute="sPmBeforePartyRecord" action="${ctx}/partyManage/sPmBeforePartyRecord/save"
           method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">姓名：</label>
        <div class="controls">
            <form:input readonly="true" path="name" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">性别：</label>
        <div class="controls">
                <%--<form:input readonly="true" path="sex" htmlEscape="false" maxlength="64" class="input-xlarge "/>--%>
            <sys:treeselect2 disabled="disabled" id="sPmBeforePartyRecordSex" name="sex"
                             value="${sPmBeforePartyRecord.sex}"
                             labelName="sexName"
                             labelValue="${fns:getDictLabel(sPmBeforePartyRecord.sex, 'XB', '')}"
                             title="性别" url="/sys/codeTree/treeData?type=XB" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">籍贯：</label>
        <div class="controls">
                <%--<form:input readonly="true" path="originPlace" htmlEscape="false" maxlength="64" class="input-xlarge "/>--%>
            <sys:treeselect2 disabled="disabled" id="sPmBeforePartyRecordOriginPlace" name="originPlace"
                             value="${sPmBeforePartyRecord.originPlace}"
                             labelName="originPlaceName"
                             labelValue="${fns:getDictLabel(sPmBeforePartyRecord.originPlace, 'JG', '')}"
                             title="籍贯" url="/sys/codeTree/treeData?type=JG" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">出生年月：</label>
        <div class="controls">
            <input name="birthTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmBeforePartyRecord.birthTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">文化程度：</label>
        <div class="controls">
                <%--<form:input readonly="true" path="education" htmlEscape="false" maxlength="64" class="input-xlarge "/>--%>
            <sys:treeselect2 disabled="disabled" id="sPmBeforePartyRecordEducation" name="education"
                             value="${sPmBeforePartyRecord.education}"
                             labelName="educationName"
                             labelValue="${fns:getDictLabel(sPmBeforePartyRecord.education, 'WHCD', '')}"
                             title="学历" url="/sys/codeTree/treeData?type=WHCD" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">参加工作时间：</label>
        <div class="controls">
            <input name="workTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmBeforePartyRecord.workTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">申请入党时间：</label>
        <div class="controls">
            <input name="jionAppTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmBeforePartyRecord.jionAppTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">确定积极分子时间：</label>
        <div class="controls">
            <input name="activistTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmBeforePartyRecord.activistTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">支部大会讨论时间：</label>
        <div class="controls">
            <input name="meetingTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmBeforePartyRecord.meetingTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">党委审批时间：</label>
        <div class="controls">
            <input name="examineTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmBeforePartyRecord.examineTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">工作单位及职务：</label>
        <div class="controls">
            <form:input readonly="true" path="workPost" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">党委意见：</label>
        <div class="controls">
            <form:input readonly="true" path="partyOption" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">上级党委组织部门备案：</label>
        <div class="controls">
            <form:input readonly="true" path="superPartyRecord" htmlEscape="false" maxlength="64"
                        class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">民族：</label>
        <div class="controls">
                <%--<form:input readonly="true" path="nation" htmlEscape="false" maxlength="64" class="input-xlarge "/>--%>
            <sys:treeselect2 disabled="disabled" id="sPmBeforePartyRecordNation" name="nation"
                             value="${sPmBeforePartyRecord.nation}"
                             labelName="nationName"
                             labelValue="${fns:getDictLabel(sPmBeforePartyRecord.nation, 'MZ', '')}"
                             title="民族" url="/sys/codeTree/treeData?type=MZ" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
        </div>
    </div>

    <div id="container">
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
                    <c:when test="${sPmBeforePartyRecord.fileUrl == null }">
                        <a href="#" role="button" class="btn">下载</a></c:when>
                    <c:otherwise>
                        <a href="${ctx}/partyManage/public/downfile?fileUrl=${sPmBeforePartyRecord.fileUrl}"
                           role="button"
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
                       value="<fmt:formatDate value="${sPmBeforePartyRecord.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
            </div>
        </div>
    </div>

    <div class="control-group">
        <div class="controls">
            <a href="${ctx}/partyManage/public/preprecord" role="button" class="btn">预备党员备案表下载</a>
        </div>
    </div>

    <div class="form-actions">
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>

</form:form>
</body>
</html>