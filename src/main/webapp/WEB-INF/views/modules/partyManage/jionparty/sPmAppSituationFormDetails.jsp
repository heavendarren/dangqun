<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {

    });
</script>

<form:form id="inputForm" modelAttribute="sPmAppSituation" action=""
           method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">政治面貌：</label>
        <div class="controls">
            <sys:treeselect2 disabled="disabled" id="politicalOutlook" name="politicalOutlook"
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
            <form:input path="oldPost" readonly="true" htmlEscape="false" maxlength="64" class="input-xlarge "/>
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
            <sys:treeselect2 disabled="disabled" id="developmentType" name="developmentType"
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
            <form:input path="rewardPunishmentSituation" readonly="true" htmlEscape="false" maxlength="64"
                        class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">现时表现：</label>
        <div class="controls">
            <form:input path="nowPerformance" readonly="true" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">入党联系人：</label>
        <div class="controls">
            <form:input path="jionContacts" readonly="true" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">确定为积极分子时间：</label>
        <div class="controls">
            <input name="activistTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmAppSituation.activistTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">确定为发展对象时间：</label>
        <div class="controls">
            <input name="developmentTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmAppSituation.developmentTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">是否为历史申请人员：</label>
        <div class="controls">
            <form:input path="whetherHistorical" readonly="true" htmlEscape="false" maxlength="64"
                        class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">历史申请时间：</label>
        <div class="controls">
            <input name="historicalAppTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmAppSituation.historicalAppTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">确定为预备党员时间：</label>
        <div class="controls">
            <input name="preparationMemberTime" type="text" readonly="readonly" maxlength="20"
                   class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmAppSituation.preparationMemberTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">历史申请次数：</label>
        <div class="controls">
            <form:input path="historicalAppNum" readonly="true" htmlEscape="false" maxlength="10"
                        class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">操作人：</label>
        <div class="controls">
            <form:input disabled="true" path="operator" readonly="true" htmlEscape="false" maxlength="64"
                        class="input-xlarge "/>
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
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>