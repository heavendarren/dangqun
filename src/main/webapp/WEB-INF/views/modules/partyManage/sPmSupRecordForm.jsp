<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
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

        var map = {};
        // Custom example logic
        var uploaders = new plupload.Uploader({
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
                PostInit: function () {
                    document.getElementById('uploadfiles').onclick = function () {
                        uploaders.start();
                        return false;
                    };
                },
                FileUploaded: function (up, file, info) {
                    var res = $.parseJSON(info.response);
                    if (res.status) {
                        $("#" + file.id + "btn").parent().html("上传完毕");
                        $("#" + file.id + "btn").remove();
                        var newhtml = "<input readonly='true' name='fileUrls' value='" + res.fileUrl + "'/>";
                        $("#" + file.id + "tfn").html(newhtml);
                    }
                },
                FilesAdded: function (up, files) {
                    plupload.each(files, function (file) {
                        map[file.id] = $("#fileType").val();
                        $("#uploadTable").append(
                            "<tr id='" + file.id + "tr'><td id='" + file.id + "tfn'>"
                            + "待生成" + "</td><td>" + "<input readonly='true' name='fileNames' value='" + file.name + "'/>"
                            + "</td><td><button class='btn btn-default' type='button' id='" +
                            file.id + "btn' v-fid='" + file.id + "'>删除</button> </td></tr>");
//                        <td id='file.id_progress'></td>
                    });
                },
                UploadProgress: function (up, file) {
//                    $("#" + file.id + "_progress").html('<span>' + file.percent + "%</span>");
                },
                Error: function (up, err) {
                    alert(err.message);
                }
            }
        });
        uploaders.init();
        $("#uploadTable").on("click", "button", function () {
            function del(id) {
                uploaders.removeFile(id);
                $("#" + id + "tr").remove();
                map[id] = "";
            }

            del($(this).attr('v-fid'));
        });
    });
</script>

<form:form id="inputForm" modelAttribute="sPmSupRecord" action="${ctx}/partyManage/sPmSupRecord/save" method="post"
           class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div id="container">
        <table id="uploadTable" class="table table-striped table-bordered table-condensed">
            <thead>
            <tr>
                <th>文件路径</th>
                <th>文件名称</th>
                <th>操作</th>
                    <%--<th>上传进度</th>--%>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>

        <div class="controls-group">
            <div class="controls">
                <label>
                    上传操作人：<input readonly="true" name="uploader" value="${sPmSupRecord.uploader}" maxlength="255"
                                 class="input-xlarge"/>
                </label>
                <label>
                    上传时间：<input name="uploadTime" type="text" readonly="readonly" maxlength="20"
                                class="input-medium Wdate "
                                value="<fmt:formatDate value="${sPmSupRecord.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
                </label>
            </div>
        </div>

        <div class="controls-group" id="fileOperat">
            <div class="controls">
                <input id="pickfiles" class="btn btn-primary" type="button" value="选择文件"/>
                <input id="uploadfiles" class="btn btn-primary" type="button" value="上传"/>
            </div>
        </div>
    </div>

    <div class="form-actions">
        <input id="sPmSupRecordBtnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
            <%--<input id="sPmSupRecordBtnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>--%>
    </div>
</form:form>
