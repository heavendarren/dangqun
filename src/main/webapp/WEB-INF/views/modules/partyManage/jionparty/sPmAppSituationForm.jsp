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
<form:form id="inputForm" modelAttribute="sPmAppSituation" action="${ctx}/partyManage/sPmAppSituation/save"
           method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">政治面貌：</label>
        <div class="controls">
            <sys:treeselect2 id="sPmAppSituationPoliticalOutlook" name="politicalOutlook"
                             value="${sPmAppSituation.politicalOutlook}"
                             labelName="politicalOutlookName"
                             labelValue="${fns:getDictLabel(sPmAppSituation.politicalOutlook, 'ZZMM', '')}"
                             title="政治面貌" url="/sys/codeTree/treeData?type=ZZMM" cssClass="input-small"
                             allowClear="true" notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">加入时间：</label>
        <div class="controls">
            <input name="jionTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmAppSituation.jionTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">曾任职务：</label>
        <div class="controls">
            <form:input path="oldPost" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">入党申请时间：</label>
        <div class="controls">
            <input name="jionAppTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmAppSituation.jionAppTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">发展类型：</label>
        <div class="controls">
            <sys:treeselect2 id="sPmAppSituationDevelopmentType" name="developmentType"
                             value="${sPmAppSituation.developmentType}"
                             labelName="developmentTypeName"
                             labelValue="${fns:getDictLabel(sPmAppSituation.developmentType, 'FZLX', '')}"
                             title="发展类型" url="/sys/codeTree/treeData?type=FZLX" cssClass="input-small"
                             allowClear="true" notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">奖惩情况：</label>
        <div class="controls">
            <form:input path="rewardPunishmentSituation" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">现时表现：</label>
        <div class="controls">
            <form:input path="nowPerformance" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">入党联系人：</label>
        <div class="controls">
            <form:input path="jionContacts" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">确定为积极分子时间：</label>
        <div class="controls">
            <input name="activistTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmAppSituation.activistTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">确定为发展对象时间：</label>
        <div class="controls">
            <input name="developmentTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmAppSituation.developmentTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">是否为历史申请人员：</label>
        <div class="controls">
            <form:input path="whetherHistorical" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">历史申请时间：</label>
        <div class="controls">
            <input name="historicalAppTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmAppSituation.historicalAppTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">确定为预备党员时间：</label>
        <div class="controls">
            <input name="preparationMemberTime" type="text" readonly="readonly" maxlength="20"
                   class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmAppSituation.preparationMemberTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">历史申请次数：</label>
        <span style="color:red;font-size:12px;">(注意：只能输入数字）</span>
        <div class="controls">
            <form:input path="historicalAppNum" htmlEscape="false" maxlength="10" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">操作人：</label>
        <div class="controls">
            <form:input readonly="true" path="operator" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">操作时间：</label>
        <div class="controls">
            <input name="operatorTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmAppSituation.operatorTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        </div>
    </div>
    <div class="form-actions">
        <input id="sPmAppSituationBtnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
            <%--<input id="sPmAppSituationBtnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>--%>
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