<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title></title>
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

        });
    </script>
</head>
<body>
<form:form id="inputForm" modelAttribute="sPmPmss" action="${ctx}/partyManage/regular/sPmPmsS/save" method="post"
           class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">党支部：</label>

        <div class="controls">
            <form:input path="partyBranch" readonly="true" htmlEscape="false" maxlength="255" class="input-xlarge "/>
                <%--<sys:treeselect2 id="sPmPmssPartyBranch" name="partyBranch" value="${sPmPmss.partyBranch}"
                                 labelName="partyBranchName"
                                 labelValue="${fns:getDictLabel(sPmPmss.partyBranch, 'CYLB', '')}"
                                 title="党支部" url="/sys/codeTree/treeData?type=CYLB" cssClass="input-small" allowClear="true"
                                 notAllowSelectParent="true"/>--%>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">时间：</label>
        <div class="controls">
            <input name="recTime" type="text" readonly="readonly"
                   maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmPmss.recTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">姓名：</label>
        <div class="controls">
            <form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">应到会有表决权的党员人数：</label>
        <div class="controls">
            <form:input path="arrNum" htmlEscape="false" maxlength="18" class="input-xlarge  digits"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">实到会有表决权的党员人数：</label>
        <div class="controls">
            <form:input path="actNum" htmlEscape="false" maxlength="18" class="input-xlarge  digits"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">有效票：</label>
        <div class="controls">
            <form:input path="validVote" htmlEscape="false" maxlength="18" class="input-xlarge  digits"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">同意票：</label>
        <div class="controls">
            <form:input path="consentVote" htmlEscape="false" maxlength="18" class="input-xlarge  digits"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">不同意票：</label>
        <div class="controls">
            <form:input path="differentVote" htmlEscape="false" maxlength="18" class="input-xlarge  digits"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">弃权票：</label>
        <div class="controls">
            <form:input path="abstention" htmlEscape="false" maxlength="18" class="input-xlarge  digits"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">计票人签字：</label>
        <div class="controls">
            <form:input path="staSign" htmlEscape="false" maxlength="255" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">监票人签字：</label>
        <div class="controls">
            <form:input path="scrSign" htmlEscape="false" maxlength="255" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">支部书记签字：</label>
        <div class="controls">
            <form:input path="branSign" htmlEscape="false" maxlength="255" class="input-xlarge "/>
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
        <input id="sPmPmssBtnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
        <%--<input id="sPmPmssBtnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>--%>
    </div>
</form:form>
<script type="text/javascript">
    var map = {};
    var uploaderes = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: 'sPmPmssFiles',
        container: document.getElementById('container'),
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
                    //var img = "<img src='" + res.fileUrl + "'/>";
                    //$("#viewImg").append(img);
                    $(".file_url").val(res.fileUrl);
                    $(".file_name").val(file.name);
                    $(".file_name").next(".btn").remove();
                    var filea = "<a href='${ctx}/partyManage/public/downfile?fileUrl=" + res.fileUrl + "' role='button' class='btn'>下载</a>";
                    $(".file_name").after(filea);
                }
            },
            FilesAdded: function (up, files) {
                uploaderes.start();
            },
            UploadProgress: function (up, file) {

            },
            Error: function (up, err) {
                alert(err.message);
            }
        }
    });
    uploaderes.init();
</script>
</body>
</html>
