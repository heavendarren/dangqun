<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@include file="/WEB-INF/views/include/fileUp.jsp" %>

<script type="text/javascript">
    $(document).ready(function () {
        //$("#name").focus();
        $("#sPmDevelopmentOptionForm").validate({
            submitHandler: function (form) {
//                loading('正在提交，请稍等...');
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

        var uploadDevelopmentOption = new plupload.Uploader({
            runtimes: 'html5,flash,silverlight,html4',
            browse_button: 'developmentOptionFiles', // you can pass in id...
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
                    uploadDevelopmentOption.start();
                },
                UploadProgress: function (up, file) {

                },
                Error: function (up, err) {
                    alert(err.message);
                }
            }
        });
        uploadDevelopmentOption.init();

        /*$("#sPmDevelopmentOptionForm").on("submit", function () {
         if(confirm("确定要保存数据吗？")){
         $("#sPmDevelopmentOptionForm").submit();
         }
         });*/

    });
</script>

<div id="container">
    <form:form id="sPmDevelopmentOptionForm" modelAttribute="sPmDevelopmentOption"
               action="${ctx}/partyManage/sPmDevelopmentOption/save" method="post" class="form-horizontal">
        <form:hidden path="id"/>
        <%--<sys:message content="${message}"/>--%>
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
                    <c:when test="${sPmDevelopmentOption.fileUrl == null }">
                        <a href="#" role="button" class="btn">下载</a></c:when>
                    <c:otherwise>
                        <a href="${ctx}/partyManage/public/downfile?fileUrl=${sPmDevelopmentOption.fileUrl}"
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
                       value="<fmt:formatDate value="${sPmDevelopmentOption.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
            </div>
        </div>

        <div class="controls-group" id="fileOperat">
            <div class="controls">
                <input id="developmentOptionFiles" class="btn btn-primary" type="button" value="选择文件"/>
            </div>
        </div>

        <div class="form-actions">
            <input id="sPmDevelopmentOptionBtnSubmit" class="btn btn-primary" type="button"
                   onclick="sPmDevelopmentOptionFormSub()"
                   value="保 存"/>
            <script type="text/javascript">
                function sPmDevelopmentOptionFormSub() {
                    if (confirm("确定要提交审核吗？")) {
                        $("#sPmDevelopmentOptionForm").submit();
                    } else {
                        return;
                    }
                }
            </script>
            <%--<input id="sPmDevelopmentOptionBtnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>--%>
        </div>
    </form:form>
</div>