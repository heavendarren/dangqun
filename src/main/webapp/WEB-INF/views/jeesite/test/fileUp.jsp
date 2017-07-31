<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>文件上传</title>
    <meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/fileUp.jsp" %>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/sys/fileUp/demo">文件上传</a></li>
</ul>
<div id="container">
    <table id="uploadTable" class="table table-striped table-bordered table-condensed">
        <thead>
        <tr>
            <th width="300">文件路径</th>
            <th width="300">文件名称</th>
            <th width="300">操作</th>
            <th width="300">上传进度</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
    <form:form id="searchForm" method="post" class="breadcrumb form-search">
        <ul class="ul-form ">
            <li class="btns"><input id="pickfiles" class="btn btn-primary" type="button" value="选择文件"/></li>
            <li class="btns"><input id="uploadfiles" class="btn btn-primary" type="button" value="批量上传"/></li>
            <li class="clearfix"></li>
        </ul>
    </form:form>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        var map = {};
        // Custom example logic
        var uploader = new plupload.Uploader({
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
                        uploader.start();
                        return false;
                    };
                },
                FileUploaded: function (up, file, info) {
                    var res = $.parseJSON(info.response);
                    if (res.status) {
                        $("#" + file.id + "btn").parent().html("上传完毕");
                        $("#" + file.id + "btn").remove();
                        $("#" + file.id + "tfn").html(res.fileUrl);
                    }
                },
                FilesAdded: function (up, files) {
                    plupload.each(files, function (file) {
                        map[file.id] = $("#fileType").val();
                        $("#uploadTable").append(
                            "<tr id='" + file.id + "tr'><td id='" + file.id + "tfn'>"
                            + "待生成" + "</td><td>" +
                            file.name + "</td><td><button class='btn btn-default' type='button' id='" +
                            file.id + "btn' v-fid='" + file.id + "'>删除</button> </td><td id='" +
                            file.id + "_progress'></td></tr>");
                    });
                },
                UploadProgress: function (up, file) {
                    $("#" + file.id + "_progress").html('<span>' + file.percent + "%</span>");
                },
                Error: function (up, err) {
                    alert(err.message);
                }
            }
        });
        uploader.init();
        $("#uploadTable").on("click", "button", function () {
            function del(id) {
                uploader.removeFile(id);
                $("#" + id + "tr").remove();
                map[id] = "";
            }

            del($(this).attr('v-fid'));
        });
    });
</script>
</body>
</html>