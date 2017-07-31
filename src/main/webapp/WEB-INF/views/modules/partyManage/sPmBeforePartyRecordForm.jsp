<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>预备党员备案</title>
    <meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/fileUp.jsp" %>
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

            // Custom example logic

            var uploads = new plupload.Uploader({
                runtimes: 'html5,flash,silverlight,html4',
                browse_button: 'pickfiles', // you can pass in id...
                container: document.getElementById('container'), // ... or DOM Element itself
                url: '${ctx}/sys/fileUp/upFile',
                flash_swf_url: '${ctxStatic}/plupload/2.3.1/Moxie.swf',
                silverlight_xap_url: '${ctxStatic}/plupload/2.3.1/Moxie.xap',
                chunk_size: '1mb',
                filters: {
                    max_file_size: '10mb',
                    prevent_duplicates: true,
                    mime_types: [{
                        title: "图片类型",
                        extensions: "jpg,gif,png"
                    }]
                },
                init: {
                    FileUploaded: function (up, file, info) {
                        var res = $.parseJSON(info.response);
                        if (res.status) {
                            $(".file_url").val(res.fileUrl);
                            $(".file_name").val(file.name);
                            $(".file_name").next(".btn").remove();
                            var filea = "<a href='${ctx}/partyManage/public/downfile?fileUrl=" + res.fileUrl + "' role='button' class='btn'>下载</a>";
                            $(".file_name").after(filea);
                        }
                    },
                    FilesAdded: function (up, files) {
                        uploads.start();
                    },
                    UploadProgress: function (up, file) {

                    },
                    Error: function (up, err) {
                        alert(err.message);
                    }
                }
            });
            uploads.init();
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
            <form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">性别：</label>
        <div class="controls">
                <%--<form:input path="sex" htmlEscape="false" maxlength="64" class="input-xlarge "/>--%>
            <sys:treeselect2 id="sPmBeforePartyRecordSex" name="sex" value="${sPmBeforePartyRecord.sex}"
                             labelName="sexName"
                             labelValue="${fns:getDictLabel(sPmBeforePartyRecord.sex, 'XB', '')}"
                             title="性别" url="/sys/codeTree/treeData?type=XB" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">籍贯：</label>
        <div class="controls">
                <%--<form:input path="originPlace" htmlEscape="false" maxlength="64" class="input-xlarge "/>--%>
            <sys:treeselect2 id="sPmBeforePartyRecordOriginPlace" name="originPlace"
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
                   value="<fmt:formatDate value="${sPmBeforePartyRecord.birthTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">文化程度：</label>
        <div class="controls">
                <%--<form:input path="education" htmlEscape="false" maxlength="64" class="input-xlarge "/>--%>
            <sys:treeselect2 id="sPmBeforePartyRecordEducation" name="education"
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
                   value="<fmt:formatDate value="${sPmBeforePartyRecord.workTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">申请入党时间：</label>
        <div class="controls">
            <input name="jionAppTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmBeforePartyRecord.jionAppTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">确定积极分子时间：</label>
        <div class="controls">
            <input name="activistTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmBeforePartyRecord.activistTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">支部大会讨论时间：</label>
        <div class="controls">
            <input name="meetingTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmBeforePartyRecord.meetingTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">党委审批时间：</label>
        <div class="controls">
            <input name="examineTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmBeforePartyRecord.examineTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">工作单位及职务：</label>
        <div class="controls">
            <form:input path="workPost" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">党委意见：</label>
        <div class="controls">
            <form:input path="partyOption" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">上级党委组织部门备案：</label>
        <div class="controls">
            <form:input path="superPartyRecord" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">民族：</label>
        <div class="controls">
                <%--<form:input path="nation" htmlEscape="false" maxlength="64" class="input-xlarge "/>--%>
            <sys:treeselect2 id="sPmBeforePartyRecordNation" name="nation" value="${sPmBeforePartyRecord.nation}"
                             labelName="nationName"
                             labelValue="${fns:getDictLabel(sPmBeforePartyRecord.nation, 'MZ', '')}"
                             title="民族" url="/sys/codeTree/treeData?type=MZ" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
        </div>
    </div>
    <%--<div class="control-group">
        <label class="control-label">备注信息：</label>
        <div class="controls">
            <form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
        </div>
    </div>--%>

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

    <div class="controls-group" id="fileOperat">
        <div class="controls">
            <input id="pickfiles" class="btn btn-primary" type="button" value="选择文件"/>
        </div>
    </div>

    <div class="form-actions">
        <input id="sPmBeforePartyRecordBtnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
        <input id="sPmBeforePartyRecordBtnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>

    <div class="control-group">
        <label class="control-label">请先保存,之后再下载：</label>
        <div class="controls">
            <a href="${ctx}/partyManage/public/preprecord" role="button" class="btn">预备党员备案表下载</a>
        </div>
    </div>
</form:form>
</body>
</html>