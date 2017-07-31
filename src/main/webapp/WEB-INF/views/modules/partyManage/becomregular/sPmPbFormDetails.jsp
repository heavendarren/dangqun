<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>

<script type="text/javascript">
    $(document).ready(function () {

    });
</script>

<form:form id="inputForm" modelAttribute="sPmPb" action="" method="post"
           class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>

    <div class="control-group">
        <label class="control-label">会议议题：</label>
        <div class="controls">
            <form:input readonly="true" path="conTop" htmlEscape="false" maxlength="255" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">时间：</label>
        <div class="controls">
            <input name="conTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmPb.conTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">地点：</label>
        <div class="controls">
            <form:input readonly="true" path="place" htmlEscape="false" maxlength="100" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">主持人：</label>
        <div class="controls">
            <form:input readonly="true" path="host" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">记录人：</label>
        <div class="controls">
            <form:input readonly="true" path="noteTaker" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">入党介绍人：</label>
        <div class="controls">
            <form:input readonly="true" path="perPar" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">列席人：</label>
        <div class="controls">
            <form:input readonly="true" path="perAtt" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">应到会有表决权的党员人数：</label>
        <div class="controls">
            <form:input readonly="true" path="arrNum" htmlEscape="false" maxlength="18" class="input-xlarge  digits"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">实到会有表决权的党员人数：</label>
        <div class="controls">
            <form:input readonly="true" path="actNum" htmlEscape="false" maxlength="18" class="input-xlarge  digits"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">参会人员姓名：</label>
        <div class="controls">
            <form:input readonly="true" path="attenList" htmlEscape="false" maxlength="255" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">缺席人员姓名：</label>
        <div class="controls">
            <form:input readonly="true" path="absList" htmlEscape="false" maxlength="255" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">会议内容：</label>
        <div class="controls">
            <form:input readonly="true" path="meetMin" htmlEscape="false" maxlength="255" class="input-xlarge "/>
        </div>
    </div>

    <div class="form-actions">
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
