<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>

<form:form id="inputForm" modelAttribute="sPmMinutes"
           action="" method="post"
           class="form-horizontal">
    <form:hidden path="id"/>
    <div class="control-group">
        <label class="control-label">会议议题：</label>
        <div class="controls">
            <form:input readonly="true" path="conTop" htmlEscape="false" maxlength="255"
                        class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">时间：</label>
        <div class="controls">
            <input name="conTime" type="text" readonly="readonly"
                   maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmMinutes.conTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">地点：</label>
        <div class="controls">
            <form:input readonly="true" path="place" htmlEscape="false" maxlength="100"
                        class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">主持人：</label>
        <div class="controls">
            <input type="text" readonly="true" value="${sPmMinutes.hostName}"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">记录人：</label>
        <div class="controls">
            <input type="text" readonly="true" value="${sPmMinutes.noteTakerName}"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">应到人数：</label>
        <div class="controls">
            <form:input path="arrNum" htmlEscape="false" maxlength="18"
                        class="input-xlarge  digits" readonly="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">实到人数：</label>
        <div class="controls">
            <form:input path="actNum" htmlEscape="false" maxlength="18"
                        class="input-xlarge  digits" readonly="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">参加人员名单：</label>
        <div class="controls">
            <input id="userDataRelation" type="text" readonly="true" value="${sPmMinutes.attenList}"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">缺席人员名单：</label>
        <div class="controls">
            <input id="userDataRelation1" type="text" readonly="true" value="${sPmMinutes.absList}"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">缺席原因：</label>
        <div class="controls">
            <form:input path="absReason" htmlEscape="false" maxlength="255"
                        class="input-xlarge " readonly="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">会议纪要：</label>
        <div class="controls">
            <form:textarea path="meetMin" htmlEscape="false" rows="4"
                           maxlength="255" class="input-xxlarge " readonly="true"/>
        </div>
    </div>

    <div class="controls-group" id="viewImg">
        <label class="control-label">文件名：</label>
        <div class="controls">
            <div hidden>
                <form:input readonly="true" path="fileUrl" htmlEscape="false" maxlength="255"
                            class="input-xlarge file_url"/>
            </div>
            <form:input readonly="true" path="fileName" htmlEscape="false" maxlength="255"
                        class="input-xlarge file_name"/>
            <c:choose>
                <c:when test="${sPmMinutes.fileUrl == null }">
                    <a href="#" role="button" class="btn">下载</a></c:when>
                <c:otherwise>
                    <a href="${ctx}/partyManage/public/downfile?fileUrl=${sPmMinutes.fileUrl}" role="button"
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
                   value="<fmt:formatDate value="${sPmMinutes.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        </div>
    </div>

    <div class="form-actions">
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
