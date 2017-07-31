<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>

<form:form id="inputForm" modelAttribute="sPmTalking" action="" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <div class="control-group">
        <label class="control-label">谈话人：</label>
        <div class="controls">
            <form:input path="talker" htmlEscape="false" maxlength="64" class="input-xlarge " readonly="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">谈话时间：</label>
        <div class="controls">
            <input name="talkTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmTalking.talkTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">谈话地点：</label>
        <div class="controls">
            <form:input path="talkPlace" htmlEscape="false" maxlength="100" class="input-xlarge " readonly="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">谈话纪实：</label>
        <div class="controls">
            <form:input path="talkDoc" htmlEscape="false" maxlength="255" class="input-xlarge " readonly="true"/>
        </div>
    </div>

    <div class="form-actions">
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
