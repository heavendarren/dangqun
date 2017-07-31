<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>

<%@include file="/WEB-INF/views/include/fileUp.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {

    });
</script>


<form:form id="inputForm" modelAttribute="sPmPmss" action="" method="post"
           class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">党支部：</label>

        <div class="controls">
            <form:input path="partyBranch" readonly="true" htmlEscape="false" maxlength="255" class="input-xlarge "/>
                <%--<sys:treeselect2 disabled="disabled" id="sPmPmssPartyBranch" name="partyBranch"
                                 value="${sPmPmss.partyBranch}"
                                 labelName="partyBranchName"
                                 labelValue="${fns:getDictLabel(sPmPmss.partyBranch, 'CYLB', '')}"
                                 title="党支部" url="/sys/codeTree/treeData?type=CYLB" cssClass="input-small" allowClear="true"
                                 notAllowSelectParent="true"/>--%>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">时间：</label>
        <div class="controls">
            <input name="recTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmPmss.recTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">姓名：</label>
        <div class="controls">
            <form:input readonly="true" path="name" htmlEscape="false" maxlength="255" class="input-xlarge "/>
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
        <label class="control-label">有效票：</label>
        <div class="controls">
            <form:input readonly="true" path="validVote" htmlEscape="false" maxlength="18"
                        class="input-xlarge  digits"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">同意票：</label>
        <div class="controls">
            <form:input readonly="true" path="consentVote" htmlEscape="false" maxlength="18"
                        class="input-xlarge  digits"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">不同意票：</label>
        <div class="controls">
            <form:input readonly="true" path="differentVote" htmlEscape="false" maxlength="18"
                        class="input-xlarge  digits"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">弃权票：</label>
        <div class="controls">
            <form:input readonly="true" path="abstention" htmlEscape="false" maxlength="18"
                        class="input-xlarge  digits"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">计票人签字：</label>
        <div class="controls">
            <form:input readonly="true" path="staSign" htmlEscape="false" maxlength="255" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">监票人签字：</label>
        <div class="controls">
            <form:input readonly="true" path="scrSign" htmlEscape="false" maxlength="255" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">支部书记签字：</label>
        <div class="controls">
            <form:input readonly="true" path="branSign" htmlEscape="false" maxlength="255" class="input-xlarge "/>
        </div>
    </div>

    <div id="container">
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
                    <c:when test="${sPmPmss.fileUrl == null }">
                        <a href="#" role="button" class="btn">下载</a></c:when>
                    <c:otherwise>
                        <a href="${ctx}/partyManage/public/downfile?fileUrl=${sPmPmss.fileUrl}" role="button"
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
                       value="<fmt:formatDate value="${sPmPmss.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
            </div>
        </div>
        <div class="controls-group" id="fileOperat">
            <div class="controls">
                <input id="sPmPmssFiles" class="btn btn-primary" type="button" value="选择文件"/>
            </div>
        </div>
    </div>

    <div class="form-actions">
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
