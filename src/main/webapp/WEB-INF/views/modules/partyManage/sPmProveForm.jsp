<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>上传管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/fileUp.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});

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
                    FileUploaded: function (up, file, info) {
                        var res = $.parseJSON(info.response);
                        if (res.status) {
                            $(".file_url").val(res.fileUrl);
                            $(".file_name").val(file.name);
                            var filea = "<a href='${ctx}/partyManage/public/downfile?fileUrl=" + res.fileUrl + "' role='button' class='btn'>下载</a>";
                            $(".file_name").after(filea);
                        }
                    },
                    FilesAdded: function (up, files) {
                        uploader.start();
                    },
                    UploadProgress: function (up, file) {

                    },
                    Error: function (up, err) {
                        alert(err.message);
                    }
                }
            });
            uploader.init();
		});
	</script>
</head>
<body>
<div id="container">
	<form:form id="inputForm" modelAttribute="sPmProve" action="${ctx}/partyManage/sPmProve/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<h3>证明材料表上传：</h3>
		<div class="control-group">
			<label class="control-label" style="font-size:20px;">图片上传</label>
			<span style="color:red;font-size:12px;"></span>
		</div>

		<div class="controls-group" id="viewImg">
            <label class="control-label">文件路径：</label>
            <div class="controls">
                <form:input readonly="true" path="fileUrl" htmlEscape="false" maxlength="255" class="input-xlarge file_url"/>
            </div>
            <label class="control-label">文件名：</label>
            <div class="controls">
                <form:input readonly="true" path="fileName" htmlEscape="false" maxlength="255" class="input-xlarge file_name"/>
            </div>
            <label class="control-label">上传操作人：</label>
            <div class="controls">
                <form:input readonly="true" path="uploader" htmlEscape="false" maxlength="255" class="input-xlarge uploader"/>
            </div>
            <label class="control-label">上传时间：</label>
            <div class="controls">
                <input name="uploadTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                       value="<fmt:formatDate value="${sPmProve.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
            </div>
		</div>

		<div class="controls-group" id="fileOperat">
            <div class="controls">
			    <input id="pickfiles" class="btn btn-primary" type="button" value="选择文件"/>
            </div>
			<%--<input id="uploadfiles" class="btn btn-primary" type="button" value="上传"/>--%>
		</div>


		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保存"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</div>
</body>
</html>